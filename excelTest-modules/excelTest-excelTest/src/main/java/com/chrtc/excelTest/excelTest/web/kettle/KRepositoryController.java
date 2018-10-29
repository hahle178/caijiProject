package com.chrtc.excelTest.excelTest.web.kettle;

import java.util.*;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import com.chrtc.excelTest.excelTest.domain.kettle.*;
import com.chrtc.excelTest.excelTest.service.kettle.KTransService;
import com.chrtc.excelTest.excelTest.service.kettle.KjobService;
import com.chrtc.excelTest.excelTest.utils.kettle.DataValidate;
import org.pentaho.di.core.exception.KettleException;
import org.springframework.beans.BeanUtils;
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
 * Created by AUTO on 2018-07-10 11:19:12.
 */
@RestController
@RequestMapping("/exceltest/kettleKRepository")
@SuppressWarnings("rawtypes")
@javax.annotation.Generated(value = "class KRepositoryController", date = "2018-07-10 11:19:12")
@EzdevModule(name = "k_repository")
public class KRepositoryController {

    @Autowired
    private KRepositoryService KRepositoryService;
    @Autowired
    private KTransService ktransService;
    @Autowired
    private KjobService kjobService;

    /**
    * 根据ID查询
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @EzdevOperation(name = "获取k_repository")
    public Result findOneById(@PathVariable @EzdevParam(name = "k_repositoryID") String id) {
        return ResultFactory.create(KRepositoryService.findOneById(id));
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
    @EzdevOperation(name = "分页获取k_repository")
	public Result findAllByPage(ServletRequest request,@RequestParam(defaultValue = "0") @EzdevParam(name = "页码")  int pageNumber,
            @RequestParam(defaultValue = "10") @EzdevParam(name = "条数")  int pageSize,@RequestParam(defaultValue = "create_date")  @EzdevParam(name = "排序字段")  String sort) {
		Map<String, Object> searchParams = UtilServlet.getParametersStartingWith(request);
		return ResultFactory.create(KRepositoryService.findAllByPage(searchParams, pageNumber, pageSize,
                    UtilWord.getDatabaseNameFromBeanName(sort)));
	}

    /**
    * 插入k_repository
    * @param entity
    * @return Result
    */
    @RequestMapping(method = RequestMethod.POST)
    @EzdevOperation(name = "插入k_repository")
    public Result add(@Valid @RequestBody @EzdevParam(name = "k_repository") KRepository entity) {
        return ResultFactory.create(KRepositoryService.add(entity));
    }

    /**
    * 根据ID更新k_repository
    * @param id
    * @param entity
    * @return Result
    */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @EzdevOperation(name = "更新k_repository")
    public Result update(@PathVariable @EzdevParam(name = "k_repositoryID") String id,@RequestBody @EzdevParam(name = "k_repository") KRepository entity) {
        entity.setId(id);
        return ResultFactory.create(KRepositoryService.update(entity));
    }
    /**
    * 根据ID删除k_repository
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @EzdevOperation(name = "删除k_repository")
	public Result delete(@PathVariable @EzdevParam(name = "k_repositoryID") String id) {
		KRepositoryService.delete(id);
		return ResultFactory.create(CodeMsgBase.SUCCESS_DELETE);
	}

    /**
    * 连接测试
    * @param id
    * @return Result
    */
    @RequestMapping(value = "/repositoryCheck")
	public Result repositoryCheck(KRepositoryDto kRepositoryDto) {
        if (DataValidate.AllNotEmpty(kRepositoryDto)){
            try {
                KRepository kRepository = new KRepository();
                BeanUtils.copyProperties(kRepositoryDto, kRepository);
                if (KRepositoryService.ckeck(kRepository)){
                    return ResultFactory.create(CodeMsgBase.SUCCESS);
                }else {
                    return ResultFactory.create(CodeMsgBase.FAILURE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResultFactory.create(CodeMsgBase.FAILURE);
            }
        }else {
            return ResultFactory.create(CodeMsgBase.FAILURE);
        }
    }
    /**
     * 保存kettle资源库连接信息
     * @param kRepository
     * @param request
     * @return
     */
    @RequestMapping("/repositoryAdd")
    public Result insertRepo(KRepository kRepository, KRepositoryDto kRepositoryDto,HttpServletRequest request){
        //KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        if(DataValidate.AllNotEmpty(kRepositoryDto)){
            KRepositoryService.deleteByReposityName(kRepository.getRepositoryName());
            int flag = KRepositoryService.insertRepo(kRepository);
            if (flag > 0) {
                return ResultFactory.create(CodeMsgBase.SUCCESS);
            } else {
                return ResultFactory.create(CodeMsgBase.FAILURE);
            }
        }else{
            return ResultFactory.create(CodeMsgBase.FAILURE);
        }
    }

    /**
     *查詢资源库列表
     * @param request
     * @return
     */
    @RequestMapping("/repositoryList")
    public Result getList(HttpServletRequest request){
        //KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        //BootTablePage list = null;
        //list = dataBaseRepositoryService.getList(offset, limit, kUser.getuId());
        List<KRepository> all = KRepositoryService.findAllList();
        return ResultFactory.create(all);
    }
    /**
     *删除资源库
     * @param request
     * @return
     */
    @RequestMapping("/deleteByReposityName")
    public void deleteByReposityName(String repositoryName ,HttpServletRequest request){
        ktransService.deleteByTransName(repositoryName);
        kjobService.deleteByJobName(repositoryName);
        KRepositoryService.deleteByReposityName(repositoryName);
    }
    /**
     *查詢资源库名称列表
     * @param request
     * @return
     */
    @RequestMapping("/repositoryName")
    public Result repositoryName(HttpServletRequest request){
        List<JSONObject> list = new ArrayList();
        List<KRepository> all = KRepositoryService.findAllList();
        for ( KRepository fn : all) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value",fn.getRepositoryName());
            jsonObject.put("code",fn.getId());
            list.add(jsonObject);
        }
        return ResultFactory.create(list);
    }

    /**
     *获取数据库资源库的树形菜单
     * @param repositoryId
     * @return
     */
    @RequestMapping("/getTransTree")
    public Result getTransTree(String repositoryId){
        try {
            List<RepositoryTree> repositoryTreeList = KRepositoryService.getTreeList(repositoryId);
            List<RepositoryTree> newRepositoryTreeList = new ArrayList<RepositoryTree>();
            for(RepositoryTree repositoryTree : repositoryTreeList){
                if ("0".equals(repositoryTree.getParent())){
                    repositoryTree.setParent("#");
                }
                if (repositoryTree.getId().indexOf("@") > 0){
                    repositoryTree.setIcon("none");
                }
                if (Constant.TYPE_JOB.equals(repositoryTree.getType())){
                    Map<String,String> stateMap = new HashMap<String, String>();
                    stateMap.put("disabled", "false");
                    repositoryTree.setState(stateMap);
                }
                newRepositoryTreeList.add(repositoryTree);
            }
            return ResultFactory.create(newRepositoryTreeList);
        } catch (KettleException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getJobTree")
    public Result getJobTree(String repositoryId){
        try {
            List<RepositoryTree> repositoryTreeList = KRepositoryService.getTreeList(repositoryId);
            List<RepositoryTree> newRepositoryTreeList = new ArrayList<RepositoryTree>();
            for(RepositoryTree repositoryTree : repositoryTreeList){
                if ("0".equals(repositoryTree.getParent())){
                    repositoryTree.setParent("#");
                }
                if (repositoryTree.getId().indexOf("@") > 0){
                    repositoryTree.setIcon("none");
                }
                if (Constant.TYPE_TRANS.equals(repositoryTree.getType())){
                    Map<String,String> stateMap = new HashMap<String, String>();
                    stateMap.put("disabled", "false");
                    repositoryTree.setState(stateMap);
                }
                newRepositoryTreeList.add(repositoryTree);
            }
            return ResultFactory.create(newRepositoryTreeList);
        } catch (KettleException e) {
            e.printStackTrace();
        }
        return null;
    }

}
