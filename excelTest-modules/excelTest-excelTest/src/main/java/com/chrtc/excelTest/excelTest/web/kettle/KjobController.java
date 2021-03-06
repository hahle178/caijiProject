package com.chrtc.excelTest.excelTest.web.kettle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.*;
import com.chrtc.excelTest.excelTest.service.kettle.KJobMonitorService;
import com.chrtc.excelTest.excelTest.service.kettle.KJobRecordService;
import com.chrtc.excelTest.excelTest.service.kettle.KjobService;
import com.chrtc.excelTest.excelTest.utils.kettle.JDBCUtils;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.io.FileUtils;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.LoggingBuffer;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.RepositoryDirectory;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.chrtc.common.log.annotation.EzdevModule;
import com.chrtc.common.log.annotation.EzdevOperation;
import com.chrtc.common.log.annotation.EzdevParam;

import com.chrtc.common.base.result.CodeMsgBase;
import com.chrtc.common.base.result.Result;
import com.chrtc.common.base.result.ResultFactory;
import com.chrtc.common.web.utils.UtilServlet;
import com.chrtc.common.web.utils.UtilWord;
import com.chrtc.excelTest.excelTest.service.kettle.KRepositoryService;

/**
 * Created by AUTO on 2018-08-02 10:22:05.
 */
@RestController
@RequestMapping("/exceltest/kjob")
@SuppressWarnings("rawtypes")
@javax.annotation.Generated(value = "class KjobController", date = "2018-08-02 10:22:05")
@EzdevModule(name = "k_job")
public class KjobController {

    @Autowired
    private KjobService KjobService;
    @Autowired
    private KRepositoryService KRepositoryService;
    @Autowired
    private KJobMonitorService kJobMonitorService;
    @Autowired
    private KJobRecordService kJobRecordService;

    private LinkedHashMap<String,Job> jobMap= new LinkedHashMap();

    @Value("${scheduler.jdbc.dbdriver}")
    private   String DBDRIVER;// 驱动类类名
    @Value("${scheduler.jdbc.table}")
    private   String TABLE;// 表名
    @Value("${scheduler.jdbc.dburl}")
    private   String DBURL;// 连接URL
    @Value("${scheduler.jdbc.dbuser}")
    private   String DBUSER; // 数据库用户名
    @Value("${scheduler.jdbc.dbpassword}")
    private  String DBPASSWORD; // 数据库密码
    @Value("${kettle.ip}")
    private  String  KETTLEIP; // kettle服务器远程配置


    /**
    * 根据ID查询
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @EzdevOperation(name = "获取k_job")
    public Result findOneById(@PathVariable @EzdevParam(name = "k_jobID") String id) {
        return ResultFactory.create(KjobService.findOneById(id));
    }

    /**
    * 多参数分页查询
    * @param request
    * @param pageNumber
    * @param pageSize
    * @param sort
    * @return Result
    */
    @RequestMapping(value = "/pages", method = RequestMethod.POST)
    @EzdevOperation(name = "分页获取k_job")
	public Result findAllByPage(ServletRequest request,@RequestParam(defaultValue = "0") @EzdevParam(name = "页码")  int pageNumber,
            @RequestParam(defaultValue = "10") @EzdevParam(name = "条数")  int pageSize,@RequestParam(defaultValue = "create_date")  @EzdevParam(name = "排序字段")  String sort) {
		Map<String, Object> searchParams = UtilServlet.getParametersStartingWith(request);
		return ResultFactory.create(KjobService.findAllByPage(searchParams, pageNumber, pageSize,
                    UtilWord.getDatabaseNameFromBeanName(sort)));
	}

    /**
    * 插入k_job
    * @param entity
    * @return Result
    */
    @RequestMapping(method = RequestMethod.POST)
    @EzdevOperation(name = "插入k_job")
    public Result add(@Valid @RequestBody @EzdevParam(name = "k_job") Kjob entity) {
        return ResultFactory.create(KjobService.add(entity));
    }

    /**
    * 根据ID更新k_job
    * @param id
    * @param entity
    * @return Result
    */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @EzdevOperation(name = "更新k_job")
    public Result update(@PathVariable @EzdevParam(name = "k_jobID") String id,@RequestBody @EzdevParam(name = "k_job") Kjob entity) {
        entity.setId(id);
        return ResultFactory.create(KjobService.update(entity));
    }
    /**
    * 根据ID删除k_job
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @EzdevOperation(name = "删除k_job")
	public Result delete(@PathVariable @EzdevParam(name = "k_jobID") String id) {
		KjobService.delete(id);
		return ResultFactory.create(CodeMsgBase.SUCCESS_DELETE);
	}

    /**
     *作业列表
     * @param
     * @param
     * @return
     */
    @RequestMapping("/jobList")
        public Result jobLogDetail(Kjob kTrans, ServletRequest request, @RequestParam(defaultValue = "0")   int pageNumber, @RequestParam(defaultValue = "10")   int pageSize, HttpServletResponse responese, @RequestParam(defaultValue = "create_date")    String sort){
           // List<Kjob> kjobs = KjobService.findAllByPage();
            Map<String, Object> searchParams = new HashMap();
            Paging<Kjob> allByPage = KjobService.findAllByPage(searchParams, pageNumber, pageSize, UtilWord.getDatabaseNameFromBeanName(sort));
           // Map all = new LinkedHashMap();
           // all.put("kjobs",allByPage);
            for (Kjob k : allByPage.getContent()) {
                String jobPath = k.getJobPath();
            }
            return ResultFactory.create(allByPage);
    }

    /**
     * 根据名称查找作业
     * @param findBytransName
     * @return
     * @throws IOException
     */
    @RequestMapping("/findByJobName")
        public Result findByJobName(String findByJobName, ServletRequest request, @RequestParam(defaultValue = "0")   int pageNumber, @RequestParam(defaultValue = "10")   int pageSize, HttpServletResponse responese, @RequestParam(defaultValue = "create_date")    String sort){
        // List<Kjob> byJobName = KjobService.findByJobName(findByJobName);
        Map<String, Object> searchParams = new HashMap();
        searchParams.put("findByJobName",findByJobName);
        Paging<Kjob> allByPage = KjobService.findAllByPage(searchParams, pageNumber, pageSize, UtilWord.getDatabaseNameFromBeanName(sort));
       // Map all = new LinkedHashMap();
        //all.put("kjobs",allByPage);
        return ResultFactory.create(allByPage);
    }

    /**
     * 增加作业
     * @param
     * @param
     * @return
     */
    @RequestMapping("/jobAdd")
    public Result insert(Kjob kjob,String jobId1, HttpServletRequest request){
        KjobService.deleteByJobName(kjob.getJobPath(),jobId1);
        kjob.setJobDescription(jobId1);
        int flag = KjobService.insertJob(kjob);
        if (flag > 0) {
            return ResultFactory.create(CodeMsgBase.SUCCESS);
        } else {
            return ResultFactory.create(CodeMsgBase.FAILURE);
        }
    }
    @RequestMapping("/stopJob")
    public Result stopJob(String jobPath){
        JSONObject jsonObject = new JSONObject();
        if (!jobMap.containsKey(jobPath)){
            jsonObject.put("result",0);
            return ResultFactory.create(jsonObject);
        }else{
            jobMap.get(jobPath).stopAll();
            jobMap.remove(jobPath);
            jsonObject.put("result",1);
            return ResultFactory.create(jsonObject);
        }
    }

    /**
     * 执行作业
     * @param args
     * @throws Exception
     */
    @RequestMapping("/runJob")
    public  Result repositoryName(String jobPath,String jobId1) throws Exception {
            //根据repositoryName得到转换信息
            Kjob kjob  =  KjobService.findByJobPath(jobPath,jobId1);
            String transRepositoryId = kjob.getJobRepositoryId();
            KRepository KRepository = KRepositoryService.findOneById(transRepositoryId);
            //初始化环境
            KettleEnvironment.init();
            //创建DB资源库
            KettleDatabaseRepository repository=new KettleDatabaseRepository();
            DatabaseMeta databaseMeta=new DatabaseMeta(KRepository.getRepositoryName(),KRepository.getRepositoryType(),KRepository.getDatabaseAccess(),KRepository.getDatabaseHost(),KRepository.getDatabaseName(),KRepository.getDatabasePort(),KRepository.getDatabaseUsername(),KRepository.getDatabasePassword());
            //选择资源库
            //资源库元对象
            KettleDatabaseRepositoryMeta repositoryInfo = new KettleDatabaseRepositoryMeta();
            repositoryInfo.setConnection(databaseMeta);
            repository.init(repositoryInfo);
            //连接资源库
            repository.connect("admin","admin");
            RepositoryDirectoryInterface directoryInterface=repository.loadRepositoryDirectoryTree();
        RepositoryDirectoryInterface d = null;
            JobMeta jobMeta = null;
            if (!jobId1.equals("0")){
                    List<RepositoryDirectoryInterface> children = directoryInterface.getChildren();
                    if(children.size() >0 ){
                        for (RepositoryDirectoryInterface  r :children) {
                            ObjectId objectId = r.getObjectId();
                            if(r.getObjectId().getId().equals(jobId1)){
                                d = r;
                                break;
                            }else{
                                List<RepositoryDirectoryInterface> children1 = r.getChildren();
                                if(children1.size() >0){
                                    for (RepositoryDirectoryInterface  r1 :children1) {
                                        if(r1.getObjectId().getId().equals(jobId1)){
                                            d = r1;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                //选择作业
                jobMeta = repository.loadJob(jobPath, d, null, null);
                }else{
                //选择作业
                jobMeta = repository.loadJob(jobPath, directoryInterface, null, null);
            }
            Job job = new Job(repository, jobMeta);
/*            jobMap.put(jobPath,job);
        Job ss = jobMap.get(jobPath);*/
        //添加监控
        Date jobStopDate = null;
        Date jobStartDate = null;
        String logText = null;
        String logChannelId = job.getLogChannelId();
        LoggingBuffer appender = KettleLogStore.getAppender();
        try {
            String exception = null;
            jobStartDate = new  Date();
            job.start();
            jobMap.put(jobPath,job);
            Job ss = jobMap.get(jobPath);
            job.waitUntilFinished();//等待直到数据结束
            jobStopDate = new Date();
            logText = appender.getBuffer(logChannelId, true).toString();
            addMonitor(jobPath,jobStartDate,jobStopDate);
        }catch (Exception e) {
            logText = e.getMessage();
            addRecord(jobPath,jobStartDate,jobStopDate,logText,"2");
            return ResultFactory.create(CodeMsgBase.FAILURE);
        }
        if(jobMap.containsKey(jobPath)){
            jobMap.remove(jobPath);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result","0");
            addRecord(jobPath,jobStartDate,jobStopDate,logText,"1");
            return ResultFactory.create(jsonObject);
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result","1");
            addRecord(jobPath,jobStartDate,jobStopDate,logText,"1");
            return ResultFactory.create(jsonObject);
        }
    }
    //远程执行作业
    @RequestMapping("/remoterunJob")
    public  Result remoterunJob(String jobPath,String jobId1) throws Exception {
//根据repositoryName得到转换信息
        Kjob kjob = KjobService.findByJobPath(jobPath, jobId1);
        String transRepositoryId = kjob.getJobRepositoryId();
        KRepository KRepository = KRepositoryService.findOneById(transRepositoryId);
        //初始化环境
        KettleEnvironment.init();
        //创建DB资源库
        KettleDatabaseRepository repository = new KettleDatabaseRepository();
        DatabaseMeta databaseMeta = new DatabaseMeta(KRepository.getRepositoryName(), KRepository.getRepositoryType(), KRepository.getDatabaseAccess(), KRepository.getDatabaseHost(), KRepository.getDatabaseName(), KRepository.getDatabasePort(), KRepository.getDatabaseUsername(), KRepository.getDatabasePassword());
        //选择资源库
        //资源库元对象
        KettleDatabaseRepositoryMeta repositoryInfo = new KettleDatabaseRepositoryMeta();
        repositoryInfo.setConnection(databaseMeta);
        repository.init(repositoryInfo);
        //连接资源库
        repository.connect("admin", "admin");
        RepositoryDirectoryInterface directoryInterface = repository.loadRepositoryDirectoryTree();
        RepositoryDirectoryInterface d = null;
        String path = null;
        JobMeta jobMeta = null;
        if (!jobId1.equals("0")) {
            List<RepositoryDirectoryInterface> children = directoryInterface.getChildren();
            if (children.size() > 0) {
                for (RepositoryDirectoryInterface r : children) {
                    ObjectId objectId = r.getObjectId();
                    if (r.getObjectId().getId().equals(jobId1)) {
                        path = r.getPath();
                        break;
                    } else {
                        List<RepositoryDirectoryInterface> children1 = r.getChildren();
                        if (children1.size() > 0) {
                            for (RepositoryDirectoryInterface r1 : children1) {
                                if (r1.getObjectId().getId().equals(jobId1)) {
                                    path = r1.getPath();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }else{
            path = directoryInterface.getPath();
        }
        LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<>();
        objectObjectLinkedHashMap.put("ip",KETTLEIP);
        objectObjectLinkedHashMap.put("path",path);

        return ResultFactory.create(objectObjectLinkedHashMap);
    }

    //远程执行作业
    @RequestMapping("/remotestatus")
    public  Result remotestatus() throws Exception {
        return ResultFactory.create(KETTLEIP);
    }

    /**
     * @Title addMonitor
     * @Description 添加作业监控
     * @return void
     */
    private void addMonitor( String jobId,Date startTime,Date stopTime){
        KJobMonitor oneById = kJobMonitorService.findOneByMonitorJob(jobId);
        if(oneById == null){
            KJobMonitor kJobMonitor = new KJobMonitor();
            kJobMonitor.setMonitorJob(jobId);
            kJobMonitor.setMonitorSuccess("0");
            kJobMonitor.setMonitorFail("0");
            StringBuilder runStatusBuilder = new StringBuilder();
            runStatusBuilder.append(startTime.getTime()).append(Constant.RUNSTATUS_SEPARATE).append(stopTime.getTime());
            kJobMonitor.setRunStatus(runStatusBuilder.toString());
            kJobMonitor.setMonitorStatus("1");
            kJobMonitorService.add(kJobMonitor);
        }
    }

    /**
     * 获取作业监控日志
     * @param
     * @param
     * @return
     */
    @RequestMapping("/getJobMonitorLog")
    public Result getJobMonitorLog(){
        //KTransService.deleteByTransName(transPath);
        List<KJobMonitor> all = kJobMonitorService.findAll();
        LinkedList<KJobLogVO> linkedList = new LinkedList();
        //记录编号
        Integer counter = 0;
        Integer allNum = 0;
        Integer successNum = 0;
        Integer failNum = 0;
        for ( KJobMonitor km :all) {
            KJobLogVO jobLogVO = new KJobLogVO();
            //转换名称
            jobLogVO.setJobName(km.getMonitorJob());
            //转换执行成功次数
            jobLogVO.setJobSuccess(Integer.parseInt(km.getMonitorSuccess()));
            //转换执行失败次数
            jobLogVO.setJobFail(Integer.parseInt(km.getMonitorFail()));
            //记录编号
            counter++;
            jobLogVO.setJobNo(counter+"");
            //总次数
            allNum = allNum + Integer.parseInt(km.getMonitorSuccess()) + Integer.parseInt(km.getMonitorFail());
            //总成功次数
            successNum = successNum + Integer.parseInt(km.getMonitorSuccess());
            //总失败次数
            failNum = failNum + Integer.parseInt(km.getMonitorFail());
            linkedList.add(jobLogVO);
        }
        for (int i = 0;i<linkedList.size();i++){
            linkedList.get(i).setJobAllNum(allNum);
            linkedList.get(i).setJobAllSuccess(successNum);
            linkedList.get(i).setJobAllFail(failNum);
        }
        return ResultFactory.create(linkedList);
    }
    /**
     * @Title addRecord
     * @Description 添加转换记录
     * @return void
     */
    private void addRecord( String jobId,Date startTime,Date stopTime,String logText,String status) throws IOException {
        KJobMonitor oneById = kJobMonitorService.findOneById(jobId);

        KJobRecord kJobRecord = new KJobRecord();
        kJobRecord.setRecordJob(jobId);

        // 拼接logfilepath
        String allLogFilePath = "E:"+ File.separator+"log"+File.separator+"job"+File.separator+new Date().getTime()+".txt";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start =formatter.format(startTime);
        String end =formatter.format(stopTime);

        kJobRecord.setLogFilePath(allLogFilePath);
        kJobRecord.setRecordStatus(status);
        kJobRecord.setStartTime(start);
        kJobRecord.setStopTime(end);

        // 将日志信息写入文件
        File file = new File(allLogFilePath);
        if(!file.getParentFile().exists()){//如果文件夹不存在
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){//如果文件夹不存在
            file.createNewFile();
        }
        FileUtils.writeStringToFile(file, logText, Constant.DEFAULT_ENCODING, false);
        // 写入作业运行记录到数据库
        kJobRecordService.add(kJobRecord);

        if(status == "1"){// 证明成功
            //成功次数加1
            KJobMonitor kJobMonitor = new KJobMonitor();
            KJobMonitor oneByMonitorJob = kJobMonitorService.findOneByMonitorJob(jobId);

            kJobMonitor  = oneByMonitorJob;
            kJobMonitor.setMonitorSuccess(Integer.parseInt(oneByMonitorJob.getMonitorSuccess())+1+"");

            if(!oneByMonitorJob.getRunStatus().equals(startTime.getTime()+"-"+stopTime.getTime())){
                StringBuilder runStatusBuilder = new StringBuilder();
                runStatusBuilder.append(oneByMonitorJob.getRunStatus()).append(",").append(startTime.getTime()).append(Constant.RUNSTATUS_SEPARATE).append(stopTime.getTime());
                kJobMonitor.setRunStatus(runStatusBuilder.toString());
            }
            kJobMonitorService.update(kJobMonitor);


        }else if (status == "2") {// 证明失败
            //失败次数加1
            KJobMonitor kJobMonitor = new KJobMonitor();
            KJobMonitor oneByMonitorJob = kJobMonitorService.findOneByMonitorJob(jobId);
            kJobMonitor.setMonitorFail(Integer.parseInt(oneByMonitorJob.getMonitorFail()) + 1 + "");

            if(!oneByMonitorJob.getRunStatus().equals(startTime.getTime()+"-"+stopTime.getTime())){
                StringBuilder runStatusBuilder = new StringBuilder();
                runStatusBuilder.append(oneByMonitorJob.getRunStatus()).append(",").append(startTime.getTime()).append(Constant.RUNSTATUS_SEPARATE).append(stopTime.getTime());
                kJobMonitor.setRunStatus(runStatusBuilder.toString());
            }

            kJobMonitorService.update(kJobMonitor);
        }
    }

    /**
     * @Title 作业日志详情
     * @Description
     * @return void
     */
    @RequestMapping("/jobLogDetail")
    public Result jobLogDetail(String jobPath, ServletRequest request, @RequestParam(defaultValue = "0")   int pageNumber, @RequestParam(defaultValue = "10")   int pageSize, HttpServletResponse responese, @RequestParam(defaultValue = "create_date")    String sort){
        Map<String, Object> searchParams = new HashMap();
        searchParams.put("record_job",jobPath);
        //分页返回
        Paging<KJobRecord> allByPage = kJobRecordService.findAllByPage(searchParams, pageNumber, pageSize, UtilWord.getDatabaseNameFromBeanName(sort));
        return ResultFactory.create(allByPage);
    }

    /**
     * @Title 查看日志详情
     * @Description
     * @return void
     */
    @RequestMapping("/recordLogDetail")
    public Result recordLogDetail(String logFilePath) throws IOException {
        File loginFile=  new File(logFilePath);
        //判断文件是否存在
        if (!loginFile.getParentFile().exists()){
            loginFile.getParentFile().mkdirs();
        }
        if(!loginFile.exists()){
            loginFile.createNewFile();
            FileOutputStream fs=new FileOutputStream(loginFile);
            OutputStreamWriter os=new OutputStreamWriter(fs);
            os.write("文件已不存在！");
            os.close();
        }
        //读取磁盘文件
        String content = FileUtils.readFileToString(loginFile, Constant.DEFAULT_ENCODING);
        String[] split = content.split("\\r\\n");
        return ResultFactory.create(split);
    }

    /**
     * 获取定时设置
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping("/scheduler")
    public Result scheduler(String id) throws IOException, ClassNotFoundException, SQLException {
        JSONObject jsonObject = new JSONObject();
        //获取连接
        Connection con = JDBCUtils.getConnection(DBDRIVER, DBURL, DBUSER, DBPASSWORD);

        //获得执行者对象
        String sql = "select CODE,VALUE_NUM,VALUE_STR  from "+TABLE +" where ID_JOB =  " + id;
        PreparedStatement ps = con.prepareStatement(sql);
        //获得结果集
        ResultSet rs = ps.executeQuery();
        //结果集处理，
        StringBuilder stringBuilder = new StringBuilder();

        while(rs.next()){
            String s  =   rs.getString("CODE");
            if(s.equals("repeat")){
                stringBuilder.append(rs.getString("VALUE_STR")+",");
            }
            if(s.equals("schedulerType") | s.equals("intervalSeconds") | s.equals("intervalMinutes") | s.equals("hour") | s.equals("minutes") | s.equals("weekDay") | s.equals("dayOfMonth")){
                stringBuilder.append(rs.getString("VALUE_NUM")+",");
            }
        }
        //释放资源
        rs.close();
        ps.close();
        con.close();
        if (stringBuilder.length() > 0)
            stringBuilder.deleteCharAt(stringBuilder.length() - 1); //调用 字符串的deleteCharAt() 方法,删除最后一个多余的逗号
        jsonObject.put("scheduler",stringBuilder);
        return ResultFactory.create(jsonObject);
    }

    /**
     * 获取定时设置
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping("/schedulerAdd")
    public Result schedulerAdd(String schedulerid,String repeat,String schedulerType,String  intervalSeconds,String intervalMinutes,String hour,String minutes,String weekDay,String dayOfMonth) throws IOException, ClassNotFoundException, SQLException {
        //获取连接
        Connection con = JDBCUtils.getConnection(DBDRIVER, DBURL, DBUSER, DBPASSWORD);
        if(Integer.parseInt(repeat) == 0){
            repeat = "N";
        }else{
            repeat = "Y";
        }
        if(schedulerType.equals("")){
            return ResultFactory.create(new CodeMsgBase("5000", "请选择定时类型"));
        }else if(Integer.parseInt(schedulerType) == 0 ){
            /*
                修改是否重复
             */
            String r = "UPDATE "+TABLE+" SET  VALUE_STR = ?  where CODE = 'REPEAT' AND ID_JOB = ?";
            PreparedStatement psREPEAT = con.prepareStatement(r);

            //设置占位符值
           // psREPEAT.setString(1,TABLE);
            psREPEAT.setString(1,repeat);
            psREPEAT.setString(2,schedulerid);
            //获得结果集
            psREPEAT.executeUpdate();

            /*
                修改定时类型
             */
            String s = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'SCHEDULERTYPE' AND ID_JOB = ?";
            PreparedStatement psschedulerType = con.prepareStatement(s);

            //设置占位符值
           // psschedulerType.setString(1,TABLE);
            psschedulerType.setString(1,schedulerType);
            psschedulerType.setString(2,schedulerid);
            //获得结果集
            psschedulerType.executeUpdate();
            //释放资源
            psREPEAT.close();
            psschedulerType.close();
            con.close();
        }else if(Integer.parseInt(schedulerType) == 1 ){
            /*
                修改是否重复
             */
            String r = "UPDATE "+TABLE+" SET  VALUE_STR = ?  where CODE = 'REPEAT' AND ID_JOB = ?";
            PreparedStatement psREPEAT = con.prepareStatement(r);
            //设置占位符值
            psREPEAT.setString(1,repeat);
            psREPEAT.setString(2,schedulerid);
            //获得结果集
            psREPEAT.executeUpdate();
            /*
                修改定时类型
             */
            String s = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'SCHEDULERTYPE' AND ID_JOB = ?";
            PreparedStatement psschedulerType = con.prepareStatement(s);
            //设置占位符值
            // psschedulerType.setString(1,TABLE);
            psschedulerType.setString(1,schedulerType);
            psschedulerType.setString(2,schedulerid);
            //获得结果集
            psschedulerType.executeUpdate();
            /*
                修改以秒计算的间隔
             */
            String i = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'INTERVALSECONDS' AND ID_JOB = ?";
            PreparedStatement psINTERVALSECONDS = con.prepareStatement(i);
            //设置占位符值
            psINTERVALSECONDS.setString(1,intervalSeconds);
            psINTERVALSECONDS.setString(2,schedulerid);
            //获得结果集
            psINTERVALSECONDS.executeUpdate();
            /*
                修改以分钟计算的间隔
             */
            String iter = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'INTERVALMINUTES' AND ID_JOB = ?";
            PreparedStatement psINTERVALMINUTES = con.prepareStatement(iter);
            //设置占位符值
            psINTERVALMINUTES.setString(1,intervalMinutes);
            psINTERVALMINUTES.setString(2,schedulerid);
            //获得结果集
            psINTERVALMINUTES.executeUpdate();


            //释放资源
            psREPEAT.close();
            psschedulerType.close();
            psINTERVALSECONDS.close();
            psINTERVALMINUTES.close();
            con.close();
        }else if(Integer.parseInt(schedulerType) == 2 ){
            /*
                修改是否重复
             */
            String r = "UPDATE "+TABLE+" SET  VALUE_STR = ?  where CODE = 'REPEAT' AND ID_JOB = ?";
            PreparedStatement psREPEAT = con.prepareStatement(r);
            //设置占位符值
            psREPEAT.setString(1,repeat);
            psREPEAT.setString(2,schedulerid);
            //获得结果集
            psREPEAT.executeUpdate();
            /*
                修改定时类型
             */
            String s = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'SCHEDULERTYPE' AND ID_JOB = ?";
            PreparedStatement psschedulerType = con.prepareStatement(s);
            //设置占位符值
            // psschedulerType.setString(1,TABLE);
            psschedulerType.setString(1,schedulerType);
            psschedulerType.setString(2,schedulerid);
            //获得结果集
            psschedulerType.executeUpdate();
            /*
                修改每天几点
             */
            String  h = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'HOUR' AND ID_JOB = ?";
            PreparedStatement psHOUR = con.prepareStatement(h);
            //设置占位符值
            psHOUR.setString(1,hour);
            psHOUR.setString(2,schedulerid);
            //获得结果集
            psHOUR.executeUpdate();
            /*
                修改每天几点几分
             */
            String m = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'MINUTES' AND ID_JOB = ?";
            PreparedStatement psMINUTES = con.prepareStatement(m);
            //设置占位符值
            psMINUTES.setString(1,minutes);
            psMINUTES.setString(2,schedulerid);
            //获得结果集
            psMINUTES.executeUpdate();


            //释放资源
            psREPEAT.close();
            psschedulerType.close();
            psHOUR.close();
            psMINUTES.close();
            con.close();
        }else if(Integer.parseInt(schedulerType) == 3 ){
            /*
                修改是否重复
             */
            String r = "UPDATE "+TABLE+" SET  VALUE_STR = ?  where CODE = 'REPEAT' AND ID_JOB = ?";
            PreparedStatement psREPEAT = con.prepareStatement(r);
            //设置占位符值
            psREPEAT.setString(1,repeat);
            psREPEAT.setString(2,schedulerid);
            //获得结果集
            psREPEAT.executeUpdate();
            /*
                修改定时类型
             */
            String s = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'SCHEDULERTYPE' AND ID_JOB = ?";
            PreparedStatement psschedulerType = con.prepareStatement(s);
            //设置占位符值
            // psschedulerType.setString(1,TABLE);
            psschedulerType.setString(1,schedulerType);
            psschedulerType.setString(2,schedulerid);
            //获得结果集
            psschedulerType.executeUpdate();
            /*
                修改每天几点
             */
            String  h = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'HOUR' AND ID_JOB = ?";
            PreparedStatement psHOUR = con.prepareStatement(h);
            //设置占位符值
            psHOUR.setString(1,hour);
            psHOUR.setString(2,schedulerid);
            //获得结果集
            psHOUR.executeUpdate();
            /*
                修改每天几点几分
             */
            String m = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'MINUTES' AND ID_JOB = ?";
            PreparedStatement psMINUTES = con.prepareStatement(m);
            //设置占位符值
            psMINUTES.setString(1,minutes);
            psMINUTES.setString(2,schedulerid);
            //获得结果集
            psMINUTES.executeUpdate();
            /*
                修改每周几
             */
            String w = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'WEEKDAY' AND ID_JOB = ?";
            PreparedStatement psWEEKDAY = con.prepareStatement(w);
            //设置占位符值
            psWEEKDAY.setString(1,weekDay);
            psWEEKDAY.setString(2,schedulerid);
            //获得结果集
            psWEEKDAY.executeUpdate();

            //释放资源
            psREPEAT.close();
            psschedulerType.close();
            psHOUR.close();
            psMINUTES.close();
            psWEEKDAY.close();
            con.close();
        }else if(Integer.parseInt(schedulerType) == 4 ){
            /*
                修改是否重复
             */
            String r = "UPDATE "+TABLE+" SET  VALUE_STR = ?  where CODE = 'REPEAT' AND ID_JOB = ?";
            PreparedStatement psREPEAT = con.prepareStatement(r);
            //设置占位符值
            psREPEAT.setString(1,repeat);
            psREPEAT.setString(2,schedulerid);
            //获得结果集
            psREPEAT.executeUpdate();
            /*
                修改定时类型
             */
            String s = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'SCHEDULERTYPE' AND ID_JOB = ?";
            PreparedStatement psschedulerType = con.prepareStatement(s);
            //设置占位符值
            // psschedulerType.setString(1,TABLE);
            psschedulerType.setString(1,schedulerType);
            psschedulerType.setString(2,schedulerid);
            //获得结果集
            psschedulerType.executeUpdate();
            /*
                修改每天几点
             */
            String  h = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'HOUR' AND ID_JOB = ?";
            PreparedStatement psHOUR = con.prepareStatement(h);
            //设置占位符值
            psHOUR.setString(1,hour);
            psHOUR.setString(2,schedulerid);
            //获得结果集
            psHOUR.executeUpdate();
            /*
                修改每天几点几分
             */
            String m = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'MINUTES' AND ID_JOB = ?";
            PreparedStatement psMINUTES = con.prepareStatement(m);
            //设置占位符值
            psMINUTES.setString(1,minutes);
            psMINUTES.setString(2,schedulerid);
            //获得结果集
            psMINUTES.executeUpdate();
            /*
                修改月份
             */
            String d = "UPDATE "+TABLE+" SET  VALUE_NUM = ? where CODE = 'DAYOFMONTH' AND ID_JOB = ?";
            PreparedStatement psDAYOFMONTH = con.prepareStatement(d);
            //设置占位符值
            psDAYOFMONTH.setString(1,dayOfMonth);
            psDAYOFMONTH.setString(2,schedulerid);
            //获得结果集
            psDAYOFMONTH.executeUpdate();

            //释放资源
            psREPEAT.close();
            psschedulerType.close();
            psHOUR.close();
            psMINUTES.close();
            psDAYOFMONTH.close();
            con.close();
        }
        return ResultFactory.create(CodeMsgBase.SUCCESS);
    }
}
