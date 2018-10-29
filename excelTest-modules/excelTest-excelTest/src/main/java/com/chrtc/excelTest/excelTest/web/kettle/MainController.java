package com.chrtc.excelTest.excelTest.web.kettle;

import com.chrtc.common.base.domain.Paging;
import com.chrtc.common.base.result.CodeMsgBase;
import com.chrtc.common.base.result.Result;
import com.chrtc.common.base.result.ResultFactory;
import com.chrtc.common.log.annotation.EzdevModule;
import com.chrtc.common.log.annotation.EzdevOperation;
import com.chrtc.common.log.annotation.EzdevParam;
import com.chrtc.common.web.utils.UtilServlet;
import com.chrtc.common.web.utils.UtilWord;
import com.chrtc.excelTest.excelTest.domain.kettle.*;
import com.chrtc.excelTest.excelTest.service.kettle.*;
import com.chrtc.excelTest.excelTest.utils.kettle.CommonUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by AUTO on 2018-08-02 10:22:05.
 */
@RestController
@RequestMapping("/exceltest/main")
@SuppressWarnings("rawtypes")
public class MainController {

    @Autowired
    private KJobMonitorService jobMonitorService;
    @Autowired
    private KTransMonitorService transMonitorService;

    /**
     * 获取监控曲线
     * @param request
     * @return
     */
    @RequestMapping("/getKettleLine")
    public Result getKettleLine(HttpServletRequest request){
        //KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<String> dateList = new ArrayList<String>();
        for (int i = -6; i <= 0; i++){
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.DATE, i);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormat = simpleDateFormat.format(instance.getTime());
            dateList.add(dateFormat);
        }
        resultMap.put("legend", dateList);
        Map<String, Object> transLine = getTransLine();
        resultMap.put("trans", transLine);
        Map<String, Object> jobLine = getJobLine();
        resultMap.put("job", jobLine);
        return ResultFactory.create(resultMap);
        //return ResultDto.success(resultMap);
    }

    /**
     * @Title getTransLine
     * @Description 获取7天内转换的折线图
     * @return
     * @return Map<String,Object>
     */
    public Map<String, Object> getTransLine(){
        //KTransMonitor template = new KTransMonitor();
        //template.setAddUser(uId);
        List<KTransMonitor> kTransMonitorList = transMonitorService.findAll();
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        List<Integer> resultList = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++){
            resultList.add(i, 0);
        }
        if (kTransMonitorList != null && !kTransMonitorList.isEmpty()){
            for (KTransMonitor KTransMonitor : kTransMonitorList){
                String runStatus = KTransMonitor.getRunStatus();
                if (runStatus != null){
                    String[] startList = runStatus.split(",");
                    for (String startOnce : startList){
                        String[] startAndStopTime = startOnce.split(Constant.RUNSTATUS_SEPARATE);
                        if(startAndStopTime.length!=2)
                            continue;
                        //得到一次任务的起始时间和结束时间的毫秒值
                        resultList = CommonUtils.getEveryDayData(Long.parseLong(startAndStopTime[0]), Long.parseLong(startAndStopTime[1]), resultList);
                    }
                }
            }
        }
        resultMap.put("name", "转换");
        resultMap.put("data", resultList);
        return resultMap;
    }

    /**
     * @Title getTransLine
     * @Description 获取7天内作业的折线图
     * @return
     * @return Map<String,Object>
     */
    public Map<String, Object> getJobLine(){
        KJobMonitor template = new KJobMonitor();
        //template.setAddUser(uId);
        List<KJobMonitor> kJobMonitorList = jobMonitorService.findAll();
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        List<Integer> resultList = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++){
            resultList.add(i, 0);
        }
        if (kJobMonitorList != null && !kJobMonitorList.isEmpty()){
            for (KJobMonitor KJobMonitor : kJobMonitorList){
                String runStatus = KJobMonitor.getRunStatus();
                if (runStatus != null){
                    String[] startList = runStatus.split(",");
                    for (String startOnce : startList){
                        String[] startAndStopTime = startOnce.split(Constant.RUNSTATUS_SEPARATE);
                        if(startAndStopTime.length!=2)
                            continue;
                        //得到一次任务的起始时间和结束时间的毫秒值
                        resultList = CommonUtils.getEveryDayData(Long.parseLong(startAndStopTime[0]), Long.parseLong(startAndStopTime[1]), resultList);
                    }
                }
            }
        }
        resultMap.put("name", "作业");
        resultMap.put("data", resultList);
        return resultMap;
    }

    /**
     * 获取监控信息
     * @param request
     * @return
     */
    @RequestMapping("/getMonitorCounter")
    public Result getMonitorCounter(HttpServletRequest request){
        //KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);

        List<KTransMonitor> trans = transMonitorService.findAll();
        List<KJobMonitor> jobs = jobMonitorService.findAll();
        int transsize = trans.size();
        int jobssize = jobs.size();

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("transsize",transsize);
        resultMap.put("jobsize",jobssize);
        resultMap.put("all",transsize+jobssize);

        return ResultFactory.create(resultMap);
    }
}
