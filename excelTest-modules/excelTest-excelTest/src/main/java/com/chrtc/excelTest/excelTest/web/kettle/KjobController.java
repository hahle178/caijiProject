package com.chrtc.excelTest.excelTest.web.kettle;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.kettle.*;
import com.chrtc.excelTest.excelTest.service.kettle.KJobMonitorService;
import com.chrtc.excelTest.excelTest.service.kettle.KJobRecordService;
import com.chrtc.excelTest.excelTest.service.kettle.KjobService;
import org.apache.commons.io.FileUtils;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.LoggingBuffer;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result jobList(Kjob kTrans, HttpServletRequest request){
        List<Kjob> kjobs = KjobService.findAllList();
        Map all = new LinkedHashMap();
        all.put("kjobs",kjobs);
        for (Kjob k : kjobs) {
            String jobPath = k.getJobPath();
        }
        return ResultFactory.create(all);
    }

    /**
     * 根据名称查找作业
     * @param findBytransName
     * @return
     * @throws IOException
     */
    @RequestMapping("/findByJobName")
    public Result findByJobName(String findByJobName) throws IOException {
        List<Kjob> byJobName = KjobService.findByJobName(findByJobName);
        return ResultFactory.create(byJobName);
    }

    /**
     * 增加作业
     * @param
     * @param
     * @return
     */
    @RequestMapping("/jobAdd")
    public Result insert(Kjob kjob, HttpServletRequest request){
        KjobService.deleteByJobName(kjob.getJobPath());
        int flag = KjobService.insertJob(kjob);
        if (flag > 0) {
            return ResultFactory.create(CodeMsgBase.SUCCESS);
        } else {
            return ResultFactory.create(CodeMsgBase.FAILURE);
        }
    }

    /**
     * 执行作业
     * @param args
     * @throws Exception
     */
    @RequestMapping("/runJob")
    public  Result repositoryName(String jobPath) throws Exception {
        //根据repositoryName得到转换信息
        Kjob kjob  =  KjobService.findByJobPath(jobPath);
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
        //选择作业
        JobMeta jobMeta = repository.loadJob(jobPath, directoryInterface, null, null);
        Job job = new Job(repository, jobMeta);

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
            job.waitUntilFinished();//等待直到数据结束
            jobStopDate = new Date();
            logText = appender.getBuffer(logChannelId, true).toString();
            addMonitor(jobPath,jobStartDate,jobStopDate);
        }catch (Exception e) {
            logText = e.getMessage();
            addRecord(jobPath,jobStartDate,jobStopDate,logText,"2");
            return ResultFactory.create(CodeMsgBase.FAILURE);
        }

        addRecord(jobPath,jobStartDate,jobStopDate,logText,"1");
        return ResultFactory.create(CodeMsgBase.SUCCESS);
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
        String allLogFilePath = "F:"+ File.separator+"log"+File.separator+"job"+File.separator+new Date().getTime()+".txt";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start =formatter.format(startTime);
        String end =formatter.format(stopTime);

        kJobRecord.setLogFilePath(allLogFilePath);
        kJobRecord.setRecordStatus(status);
        kJobRecord.setStartTime(start);
        kJobRecord.setStopTime(end);

        // 将日志信息写入文件
        File file = new File(allLogFilePath);
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
    public Result jobLogDetail(String transPath, ServletRequest request, @RequestParam(defaultValue = "0")   int pageNumber, @RequestParam(defaultValue = "10")   int pageSize, HttpServletResponse responese, @RequestParam(defaultValue = "create_date")    String sort){
        Map<String, Object> searchParams = new HashMap();
        searchParams.put("record_trans",transPath);
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
        //读取磁盘文件
        String content = FileUtils.readFileToString(new File(logFilePath), Constant.DEFAULT_ENCODING);
        String[] split = content.split("\\r\\n");
        return ResultFactory.create(split);
    }
}
