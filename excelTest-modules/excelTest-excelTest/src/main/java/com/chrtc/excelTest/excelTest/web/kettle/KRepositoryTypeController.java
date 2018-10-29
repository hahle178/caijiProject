package com.chrtc.excelTest.excelTest.web.kettle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
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
import com.chrtc.excelTest.excelTest.domain.kettle.KRepositoryType;
import com.chrtc.excelTest.excelTest.service.kettle.KRepositoryTypeService;

/**
 * Created by AUTO on 2018-07-10 11:19:12.
 */
@RestController
@RequestMapping("/exceltest/kettle")
@SuppressWarnings("rawtypes")
@javax.annotation.Generated(value = "class KRepositoryTypeController", date = "2018-07-10 11:19:12")
@EzdevModule(name = "k_repository_type")
public class KRepositoryTypeController {

    @Autowired
    private KRepositoryTypeService KRepositoryTypeService;

    /**
    * 根据ID查询
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @EzdevOperation(name = "获取k_repository_type")
    public Result findOneById(@PathVariable @EzdevParam(name = "k_repository_typeID") String id) {
        return ResultFactory.create(KRepositoryTypeService.findOneById(id));
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
    @EzdevOperation(name = "分页获取k_repository_type")
	public Result findAllByPage(ServletRequest request,@RequestParam(defaultValue = "0") @EzdevParam(name = "页码")  int pageNumber,
            @RequestParam(defaultValue = "10") @EzdevParam(name = "条数")  int pageSize,@RequestParam(defaultValue = "create_date")  @EzdevParam(name = "排序字段")  String sort) {
		Map<String, Object> searchParams = UtilServlet.getParametersStartingWith(request);
		return ResultFactory.create(KRepositoryTypeService.findAllByPage(searchParams, pageNumber, pageSize,
                    UtilWord.getDatabaseNameFromBeanName(sort)));
	}

    /**
    * 插入k_repository_type
    * @param entity
    * @return Result
    */
    @RequestMapping(method = RequestMethod.POST)
    @EzdevOperation(name = "插入k_repository_type")
    public Result add(@Valid @RequestBody @EzdevParam(name = "k_repository_type") KRepositoryType entity) {
        return ResultFactory.create(KRepositoryTypeService.add(entity));
    }

    /**
    * 根据ID更新k_repository_type
    * @param id
    * @param entity
    * @return Result
    */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @EzdevOperation(name = "更新k_repository_type")
    public Result update(@PathVariable @EzdevParam(name = "k_repository_typeID") String id,@RequestBody @EzdevParam(name = "k_repository_type") KRepositoryType entity) {
        entity.setId(id);
        return ResultFactory.create(KRepositoryTypeService.update(entity));
    }
    /**
    * 根据ID删除k_repository_type
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @EzdevOperation(name = "删除k_repository_type")
	public Result delete(@PathVariable @EzdevParam(name = "k_repository_typeID") String id) {
		KRepositoryTypeService.delete(id);
		return ResultFactory.create(CodeMsgBase.SUCCESS_DELETE);
	}
    /**
     * 动态获取数据库资源库类型
     *
     * @param
     * @return Result
     */
    @RequestMapping(value = "/ajaxRepositoryType")
    public Result ajaxRepository() {
        List<JSONObject> list = new ArrayList();
        List<KRepositoryType> repositoryTypeList = KRepositoryTypeService.getRepositoryTypeList();

        for ( KRepositoryType fn : repositoryTypeList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value",fn.getRepositoryTypeDes());
            jsonObject.put("code",fn.getRepositoryTypeCode());
            list.add(jsonObject);
        }
        return ResultFactory.create(list);
    }

}
