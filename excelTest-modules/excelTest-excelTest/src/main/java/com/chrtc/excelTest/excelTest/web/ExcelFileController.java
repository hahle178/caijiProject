package com.chrtc.excelTest.excelTest.web;

import java.util.Map;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

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
import com.chrtc.excelTest.excelTest.domain.ExcelFile;
import com.chrtc.excelTest.excelTest.service.ExcelFileService;

/**
 * Created by AUTO on 2018-06-12 11:05:30.
 */
@RestController
@RequestMapping("/excelTest/excelfile")
@SuppressWarnings("rawtypes")
@javax.annotation.Generated(value = "class ExcelFileController", date = "2018-06-12 11:05:30")
@EzdevModule(name = "excelFile")
public class ExcelFileController {

    @Autowired
    private ExcelFileService excelFileService;

    /**
    * 根据ID查询
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @EzdevOperation(name = "获取excelFile")
    public Result findOneById(@PathVariable @EzdevParam(name = "excelFileID") String id) {
        return ResultFactory.create(excelFileService.findOneById(id));
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
    @EzdevOperation(name = "分页获取excelFile")
	public Result findAllByPage(ServletRequest request,@RequestParam(defaultValue = "0") @EzdevParam(name = "页码")  int pageNumber,
            @RequestParam(defaultValue = "10") @EzdevParam(name = "条数")  int pageSize,@RequestParam(defaultValue = "create_date")  @EzdevParam(name = "排序字段")  String sort) {
		Map<String, Object> searchParams = UtilServlet.getParametersStartingWith(request);
		return ResultFactory.create(excelFileService.findAllByPage(searchParams, pageNumber, pageSize,
                    UtilWord.getDatabaseNameFromBeanName(sort)));
	}

    /**
    * 插入excelFile
    * @param entity
    * @return Result
    */
    @RequestMapping(method = RequestMethod.POST)
    @EzdevOperation(name = "插入excelFile")
    public Result add(@Valid @RequestBody @EzdevParam(name = "excelFile") ExcelFile entity) {
        return ResultFactory.create(excelFileService.add(entity));
    }

    /**
    * 根据ID更新excelFile
    * @param id
    * @param entity
    * @return Result
    */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @EzdevOperation(name = "更新excelFile")
    public Result update(@PathVariable @EzdevParam(name = "excelFileID") String id,@RequestBody @EzdevParam(name = "excelFile") ExcelFile entity) {
        entity.setId(id);
        return ResultFactory.create(excelFileService.update(entity));
    }
    /**
    * 根据ID删除excelFile
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @EzdevOperation(name = "删除excelFile")
	public Result delete(@PathVariable @EzdevParam(name = "excelFileID") String id) {
		excelFileService.delete(id);
		return ResultFactory.create(CodeMsgBase.SUCCESS_DELETE);
	}

}
