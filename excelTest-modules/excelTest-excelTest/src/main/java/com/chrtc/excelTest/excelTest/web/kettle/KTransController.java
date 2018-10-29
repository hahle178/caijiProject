package com.chrtc.excelTest.excelTest.web.kettle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.FieldRowMessage;
import com.chrtc.excelTest.excelTest.domain.kettle.*;
import com.chrtc.excelTest.excelTest.service.kettle.KRepositoryService;
import com.chrtc.excelTest.excelTest.service.kettle.KTransMonitorService;
import com.chrtc.excelTest.excelTest.service.kettle.KTransRecordService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.LoggingBuffer;
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
import com.chrtc.excelTest.excelTest.service.kettle.KTransService;

/**
 * Created by AUTO on 2018-07-11 16:06:50.
 */
@RestController
@RequestMapping("/exceltest/ktrans")
@SuppressWarnings("rawtypes")
@javax.annotation.Generated(value = "class KTransController", date = "2018-07-11 16:06:50")
@EzdevModule(name = "k_trans")
public class KTransController {

    @Autowired
    private KTransService KTransService;
    @Autowired
    private KRepositoryService KRepositoryService;
    @Autowired
    private KTransMonitorService kTransMonitorService;
    @Autowired
    private KTransRecordService kTransRecordService;
    /**
    * 根据ID查询
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @EzdevOperation(name = "获取k_trans")
    public Result findOneById(@PathVariable @EzdevParam(name = "k_transID") String id) {
        return ResultFactory.create(KTransService.findOneById(id));
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
    @EzdevOperation(name = "分页获取k_trans")
	public Result findAllByPage(ServletRequest request,@RequestParam(defaultValue = "0") @EzdevParam(name = "页码")  int pageNumber,
            @RequestParam(defaultValue = "10") @EzdevParam(name = "条数")  int pageSize,@RequestParam(defaultValue = "create_date")  @EzdevParam(name = "排序字段")  String sort) {
		Map<String, Object> searchParams = UtilServlet.getParametersStartingWith(request);
		return ResultFactory.create(KTransService.findAllByPage(searchParams, pageNumber, pageSize,
                    UtilWord.getDatabaseNameFromBeanName(sort)));
	}

    /**
    * 插入k_trans
    * @param entity
    * @return Result
    */
    @RequestMapping(method = RequestMethod.POST)
    @EzdevOperation(name = "插入k_trans")
    public Result add(@Valid @RequestBody @EzdevParam(name = "k_trans") KTrans entity) {
        return ResultFactory.create(KTransService.add(entity));
    }

    /**
    * 根据ID更新k_trans
    * @param id
    * @param entity
    * @return Result
    */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @EzdevOperation(name = "更新k_trans")
    public Result update(@PathVariable @EzdevParam(name = "k_transID") String id,@RequestBody @EzdevParam(name = "k_trans") KTrans entity) {
        entity.setId(id);
        return ResultFactory.create(KTransService.update(entity));
    }
    /**
    * 根据ID删除k_trans
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @EzdevOperation(name = "删除k_trans")
	public Result delete(@PathVariable @EzdevParam(name = "k_transID") String id) {
		KTransService.delete(id);
		return ResultFactory.create(CodeMsgBase.SUCCESS_DELETE);
	}

    /**
     * 增加转换
     * @param kTrans
     * @param request
     * @return
     */
    @RequestMapping("/transAdd")
    public Result insert(KTrans kTrans, HttpServletRequest request){
        KTransService.deleteByTransName(kTrans.getTransPath());
        int flag = KTransService.insertTran(kTrans);
        if (flag > 0) {
            return ResultFactory.create(CodeMsgBase.SUCCESS);
        } else {
            return ResultFactory.create(CodeMsgBase.FAILURE);
        }
        }

    /**
     *转换列表
     * @param kTrans
     * @param request
     * @return
     */
    @RequestMapping("/transList")
    public Result transList(KTrans kTrans, HttpServletRequest request){
        List all = KTransService.findAllList();
        return ResultFactory.create(all);
    }

    /**
     * 根据条件查找转换
     * @param logFilePath
     * @return
     * @throws IOException
     */
    @RequestMapping("/findBytransName")
    public Result findBytransName(String findBytransName) throws IOException {
        List<KTrans> bytransName = KTransService.findBytransName(findBytransName);
        return ResultFactory.create(bytransName);
    }



    /**
     * 执行转换
     * @param args
     * @throws Exception
     */
    @RequestMapping("/runTrans")
    public  Result repositoryName(String transPath) throws Exception {
        //根据repositoryName得到转换信息
        KTrans kTrans  =  KTransService.findByTransPath(transPath);
        String transRepositoryId = kTrans.getTransRepositoryId();
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
        //选择转换
        TransMeta transMeta=repository.loadTransformation(transPath,directoryInterface,null,true,null);
        Trans trans=new Trans(transMeta);
        //添加监控
        Date transStopDate = null;
        Date transStartDate = null;
        String logText = null;
        String logChannelId = trans.getLogChannelId();
        LoggingBuffer appender = KettleLogStore.getAppender();
        try{
            //addMonitor(transPath);
            String exception = null;

            transStartDate = new  Date();
            trans.execute(null);
            trans.waitUntilFinished();//等待直到数据结束
            transStopDate = new Date();
            logText = appender.getBuffer(logChannelId, true).toString();
            addMonitor(transPath,transStartDate,transStopDate);
        }catch (Exception e) {
            logText = e.getMessage();
            addRecord(transPath,transStartDate,transStopDate,logText,"2");
            return ResultFactory.create(CodeMsgBase.FAILURE);
        }
        addRecord(transPath,transStartDate,transStopDate,logText,"1");
        return ResultFactory.create(CodeMsgBase.SUCCESS);
    }

    /**
     * 删除转换
     * @param
     * @param
     * @return
     */
    @RequestMapping("/deleteTrans")
    public Result deleteTrans(String transPath, HttpServletRequest request){
        KTransService.deleteByTransName(transPath);
        return ResultFactory.create(CodeMsgBase.SUCCESS);
    }
    /**
     * 获取转换监控日志
     * @param
     * @param
     * @return
     */
    @RequestMapping("/getTransMonitorLog")
    public Result getTransMonitorLog(){
        //KTransService.deleteByTransName(transPath);
        List<KTransMonitor> all = kTransMonitorService.findAll();
        LinkedList<KTransLogVO> linkedList = new LinkedList();

        //记录编号
        Integer counter = 0;
        Integer allNum = 0;
        Integer successNum = 0;
        Integer failNum = 0;

        for ( KTransMonitor km :all) {
            KTransLogVO ltransLogVO = new KTransLogVO();
            //转换名称
            ltransLogVO.setTransName(km.getMonitorTrans());
            //转换执行成功次数
            ltransLogVO.setTransSuccess(Integer.parseInt(km.getMonitorSuccess()));
            //转换执行失败次数
            ltransLogVO.setTransFail(Integer.parseInt(km.getMonitorFail()));
            //记录编号
            counter++;
            ltransLogVO.setTransNo(counter+"");
            //总次数
            allNum = allNum + Integer.parseInt(km.getMonitorSuccess()) + Integer.parseInt(km.getMonitorFail());
            //总成功次数
            successNum = successNum + Integer.parseInt(km.getMonitorSuccess());
            //总失败次数
            failNum = failNum + Integer.parseInt(km.getMonitorFail());

            linkedList.add(ltransLogVO);
        }

        for (int i = 0;i<linkedList.size();i++){
            linkedList.get(i).setTransAllNum(allNum);
            linkedList.get(i).setTransAllSuccess(successNum);
            linkedList.get(i).setTransAllFail(failNum);
        }

        return ResultFactory.create(linkedList);
    }

    /**
     * @Title addMonitor
     * @Description 添加转换监控
     * @return void
     */
    private void addMonitor( String transId,Date startTime,Date stopTime){
        KTransMonitor oneById = kTransMonitorService.findOneByMonitorTrans(transId);

        if(oneById == null){
            KTransMonitor kTransMonitor = new KTransMonitor();
            kTransMonitor.setMonitorTrans(transId);
            kTransMonitor.setMonitorSuccess("0");
            kTransMonitor.setMonitorFail("0");
            StringBuilder runStatusBuilder = new StringBuilder();
            runStatusBuilder.append(startTime.getTime()).append(Constant.RUNSTATUS_SEPARATE).append(stopTime.getTime());
            kTransMonitor.setRunStatus(runStatusBuilder.toString());
            kTransMonitor.setMonitorStatus("1");
            KTransMonitor add = kTransMonitorService.add(kTransMonitor);
        }
    }


    /**
     * @Title addRecord
     * @Description 添加转换记录
     * @return void
     */
    private void addRecord( String transId,Date startTime,Date stopTime,String logText,String status) throws IOException {
        KTransMonitor oneById = kTransMonitorService.findOneById(transId);

        KTransRecord kTransRecord = new KTransRecord();
        kTransRecord.setRecordTrans(transId);

        // 拼接logfilepath
        String allLogFilePath = "F:"+ File.separator+"log"+File.separator+"trans"+File.separator+new Date().getTime()+".txt";

        kTransRecord.setLogFilePath(allLogFilePath);
        kTransRecord.setRecordStatus(status);
        kTransRecord.setStartTime(startTime);
        kTransRecord.setStopTime(stopTime);

        // 将日志信息写入文件
        File file = new File(allLogFilePath);
        if(!file.exists()){//如果文件夹不存在
            file.createNewFile();
        }
        FileUtils.writeStringToFile(file, logText, Constant.DEFAULT_ENCODING, false);
        // 写入转换运行记录到数据库
        kTransRecordService.add(kTransRecord);

        if(status == "1"){// 证明成功
            //成功次数加1
            KTransMonitor kTransMonitor = new KTransMonitor();
            KTransMonitor oneByMonitorTrans = kTransMonitorService.findOneByMonitorTrans(transId);
            kTransMonitor  = oneByMonitorTrans;
            kTransMonitor.setMonitorSuccess(Integer.parseInt(oneByMonitorTrans.getMonitorSuccess())+1+"");

            if(!oneByMonitorTrans.getRunStatus().equals(startTime.getTime()+"-"+stopTime.getTime())) {
                StringBuilder runStatusBuilder = new StringBuilder();
                runStatusBuilder.append(oneByMonitorTrans.getRunStatus()).append(",").append(startTime.getTime()).append(Constant.RUNSTATUS_SEPARATE).append(stopTime.getTime());
                kTransMonitor.setRunStatus(runStatusBuilder.toString());
            }

            kTransMonitorService.update(kTransMonitor);

        }else if (status == "2"){// 证明失败
            //失败次数加1
            KTransMonitor kTransMonitor = new KTransMonitor();
            KTransMonitor oneByMonitorTrans = kTransMonitorService.findOneByMonitorTrans(transId);
            kTransMonitor.setMonitorFail(Integer.parseInt(oneByMonitorTrans.getMonitorFail())+1+"");

            if(!oneByMonitorTrans.getRunStatus().equals(startTime.getTime()+"-"+stopTime.getTime())) {
                StringBuilder runStatusBuilder = new StringBuilder();
                runStatusBuilder.append(oneByMonitorTrans.getRunStatus()).append(",").append(startTime.getTime()).append(Constant.RUNSTATUS_SEPARATE).append(stopTime.getTime());
                kTransMonitor.setRunStatus(runStatusBuilder.toString());
            }

            kTransMonitorService.update(kTransMonitor);
        }
    }
    /**
     * @Title
     * @Description
     * @return void
     */
    @RequestMapping("/transLogDetail")
    public Result transLogDetail(String transPath, ServletRequest request,@RequestParam(defaultValue = "0")   int pageNumber, @RequestParam(defaultValue = "10")   int pageSize, HttpServletResponse responese, @RequestParam(defaultValue = "create_date")    String sort){
        Map<String, Object> searchParams = new HashMap();
        searchParams.put("record_trans",transPath);
        //分页返回
        Paging<KTransRecord> allByPage = kTransRecordService.findAllByPage(searchParams, pageNumber, pageSize, UtilWord.getDatabaseNameFromBeanName(sort));
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
