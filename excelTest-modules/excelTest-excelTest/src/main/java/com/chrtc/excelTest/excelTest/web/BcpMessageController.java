package com.chrtc.excelTest.excelTest.web;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import com.alibaba.fastjson.JSONObject;
import com.chrtc.attach.common.domain.FileAttachment;
import com.chrtc.common.base.domain.Paging;
import com.chrtc.excelTest.excelTest.domain.*;
import com.chrtc.excelTest.excelTest.mapper.BcpMessageMapper;
import com.chrtc.excelTest.excelTest.service.ExcelFileService;
import com.chrtc.excelTest.excelTest.service.FieldRowMessageService;
import com.chrtc.excelTest.excelTest.service.impl.DataMappingXmlServiceImpl;
import com.chrtc.excelTest.excelTest.utils.BcpUtil;
import com.chrtc.excelTest.excelTest.utils.CompressedFileUtil;
import com.chrtc.excelTest.excelTest.utils.ExcelUtil;
import com.google.common.base.Joiner;
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
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
import com.chrtc.attach.common.service.AttachService;

import com.chrtc.excelTest.excelTest.service.BcpMessageService;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by AUTO on 2018-06-08 14:03:31.
 */
@RestController
@RequestMapping("/exceltest/bcpmessage")
@SuppressWarnings("rawtypes")
@javax.annotation.Generated(value = "class BcpMessageController", date = "2018-06-08 14:03:31")
@EzdevModule(name = "bcpMessage")
public class BcpMessageController {
    @Autowired
    private DataMappingXmlServiceImpl dataMappingXmlService;
    @Autowired
    private BcpMessageMapper bcpMessageMapper;
    @Autowired
    private AttachService attachService;
    @Autowired
    private BcpMessageService BcpMessageService;
    @Autowired
    private FieldRowMessageService fieldRowMessageService;

    @Value("${ezdev.attach.config.local.dir}")
    private  String dir;
    @Value("${ezdev.attach.config.local.dumpdir}")
    private  String dumpdir;


    /**
     *五位自增序列号
     */
    private static int sn = 10001;
    public synchronized int getNextSN() {
        return ++sn;
    }
    public int getCurrentSN() {
        return sn;
    }

    /**
    * 根据ID查询
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @EzdevOperation(name = "获取bcpMessage")
    public Result findOneById(@PathVariable @EzdevParam(name = "bcpMessageID") String id) {

        return ResultFactory.create(BcpMessageService.findOneById(id));

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
    @EzdevOperation(name = "分页获取bcpMessage")
	public Result findAllByPage(ServletRequest request,@RequestParam(defaultValue = "0") @EzdevParam(name = "页码")  int pageNumber,
            @RequestParam(defaultValue = "10") @EzdevParam(name = "条数")  int pageSize,@RequestParam(defaultValue = "create_date")  @EzdevParam(name = "排序字段")  String sort) {
		Map<String, Object> searchParams = UtilServlet.getParametersStartingWith(request);
		return ResultFactory.create(BcpMessageService.findAllByPage(searchParams, pageNumber, pageSize,
                    UtilWord.getDatabaseNameFromBeanName(sort)));
	}

    /**
    * 插入bcpMessage
    * @param entity
    * @return Result
    */
    @RequestMapping(method = RequestMethod.POST)
    @EzdevOperation(name = "插入bcpMessage")
    public Result add(@Valid @RequestBody @EzdevParam(name = "bcpMessage") ExcelEntity entity) {
        entity.getCapturetime();
        entity.getContent();
        entity.getUrl();
        entity.getDatatype();

        return ResultFactory.create(CodeMsgBase.SUCCESS_SAVE);
    }

    /**
    * 根据ID更新bcpMessage
    * @param id
    * @param entity
    * @return Result
    */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @EzdevOperation(name = "更新bcpMessage")
    public Result update(@PathVariable @EzdevParam(name = "bcpMessageID") String id,@RequestBody @EzdevParam(name = "bcpMessage") BcpMessage entity) {
        entity.setId(id);
        return ResultFactory.create(BcpMessageService.update(entity));
    }
    /**
    * 根据ID删除bcpMessage
    * @param id
    * @return Result
    */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @EzdevOperation(name = "删除bcpMessage")
	public Result delete(@PathVariable @EzdevParam(name = "bcpMessageID") String id) {
		BcpMessageService.delete(id);
		return ResultFactory.create(CodeMsgBase.SUCCESS_DELETE);
	}
    /**
     * 动态获取文件名
     * @param
     * @return Result
     */
    @RequestMapping(value = "/ajaxGetBcpName")
    public Result  ajaxGetBcpName() throws Exception {
        List<JSONObject> list = new ArrayList();

        List<String> fileName = BcpUtil.getFileName(File.separator+"home"+File.separator+"EXCEL");
        for (String fn:fileName) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value",fn);
            jsonObject.put("code",fn);
            list.add(jsonObject);
        }
        return ResultFactory.create(list);
    }
    /**
     * 动态获取表头
     * @param
     * @return Result
     */
    @RequestMapping(value = "/ajaxGetHeadName")
    public Result  ajaxGetHeadName() throws Exception {
        List<JSONObject> list = new ArrayList();

        List<FieldRowMessage> byGroup = fieldRowMessageService.findByGroup();

        for (FieldRowMessage fn:byGroup) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value",fn.getFieldheadname());
            jsonObject.put("code",fn.getFieldheadname());
            list.add(jsonObject);
        }
        return ResultFactory.create(list);
    }
    /**
     * 动态获取表头所属的行
     * @param
     * @return Result
     */
    @RequestMapping(value = "/ajaxGetRow")
    public Result  ajaxGetRow(String selectHead) throws Exception {
        JSONObject jsonObject = new JSONObject();

        List<FieldRowMessage> list = fieldRowMessageService.findByHead(selectHead);
        LinkedList<DefineHead2> defineHeads = new LinkedList<DefineHead2>();
            String fieldname = list.get(0).getFieldname();
            String fieldeng = list.get(0).getFieldeng();
            String fieldremark = list.get(0).getFieldremark();
            String fieldlength = list.get(0).getFieldlength();
            String fieldheadname = list.get(0).getFieldheadname();

            String[] fn = fieldname.split(",");
            String[] fe = fieldeng.split(",");
            String[] fr = fieldremark.split(",");
            String[] fl = fieldlength.split(",");

            for(int i=0; i<fn.length;i++){
                DefineHead2 defineHead = new DefineHead2();
                defineHead.setFieldName(fn[i]);
                defineHead.setFieldEng(fe[i]);
                defineHead.setFieldRemark(fr[i]);
                defineHead.setFieldLength(fl[i]);
                defineHead.setFieldHeadName(fieldheadname);
                defineHeads.add(defineHead);
            }

        return ResultFactory.create(defineHeads);
    }
    /**
     * 上传excel生成bcp文件和索引文件
     * @param
     * @return Result
     */
    @RequestMapping(value = "/upfile")
    public Result  readExcel(String excelId, @Valid  ExcelEntity excelEntity,HttpServletResponse responese) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try{
        LinkedList bcpMessages = null;
        BcpMessage bcpMessage = null;
        if (excelId != null && excelId != "" ){
            //判断上传的文件是什么格式
            List<FileAttachment> list = attachService.list(excelId);
            String c = list.get(0).getAttachmentPathStore();
            String attachmentName = list.get(0).getAttachmentName();
            HashMap<String, Object> objectObjectHashMap = new LinkedHashMap<>();
            //截取字符串，获取本地存储文件名
            String[] split = c.split("/");
            String s = split[1];
            //获取文件后缀名，判断是什么文件
            String houzui = s.split("\\.")[1];
            String filePath="";
            if(houzui.equals("xls") | houzui.equals("xlsx")){
                //读取excel文件，生成bcp文件
                bcpMessages = BcpMessageService.readExcelAndOut(excelId);
                //判断文件夹是否存在
                filePath=File.separator+ "home"+File.separator +"EXCEL";
                mkDirs(filePath);
                //生成xml文件
//                String xmlPath =File.separator+"home"+File.separator +"EXCEL" + File.separator + "AQ_ZIP_INDEX.xml";
//                //BcpMessageService.createIndexXml(xmlPath,bcpMessages);
                //生成压缩文件
                BcpMessageService.createZIP("EXCEL");
            }else if(houzui.equals("csv") | houzui.equals("CSV")){
                //读取csv文件，生成bcp文件
                bcpMessages = BcpMessageService.readCSVAndOut(excelId);
                //判断文件夹是否存在
                filePath=File.separator+ "home"+File.separator +"CSV";
                mkDirs(filePath);
                //生成xml文件
//                String xmlPath = File.separator+ "home"+File.separator +"CSV" + File.separator + "AQ_ZIP_INDEX.xml";
//                BcpMessageService.createIndexXml(xmlPath,bcpMessages);
                //生成压缩文件
                BcpMessageService.createZIP("CSV");
            }else if(houzui.equals("dmp") | houzui.equals("DMP")){
                //解析dmp文件
                String code1 = "cmd"+" /k start "+ dumpdir +"/dmp.bat " + attachmentName;
                Process exec = Runtime.getRuntime().exec(code1);
                Process process = exec;
            }else if(houzui.equals("txt")|houzui.equals("TXT")){
                //读取txt文件,生成bcp文件
                bcpMessages = BcpMessageService.readTXTAndOut(excelId);
                //判断文件夹是否存在
                filePath=File.separator+ "home"+File.separator +"TXT";
                mkDirs(filePath);
//                String xmlPath = File.separator+ "home"+File.separator +"TXT" + File.separator + "AQ_ZIP_INDEX.xml";
//                BcpMessageService.createIndexXml1(xmlPath,bcpMessages);
                BcpMessageService.createZIP1("TXT");
            }else if(houzui.equals("xml")|houzui.equals("XML")){
                bcpMessages = BcpMessageService.readXMLAndOut(excelId);
                //判断文件夹是否存在
                filePath=File.separator+ "home"+File.separator +"XML";
                mkDirs(filePath);
//                String xmlPath = File.separator+ "home"+File.separator +"XML" + File.separator + "AQ_ZIP_INDEX.xml";
//                BcpMessageService.createIndexXml(xmlPath,bcpMessages);
                BcpMessageService.createZIP("XML");
            }
        }else{
            //接收表单数据，生成bcp文件
            bcpMessage = BcpMessageService.getEntityCreateBcp(excelEntity);
            //生成xml文件
            String xmlPath = File.separator+ "home"+File.separator +"FIELD" + File.separator + "AQ_ZIP_INDEX.xml";
            BcpMessageService.createIndexXml(xmlPath,bcpMessages);
            //生成压缩文件
            BcpMessageService.createZIP("FIELD");
        }

        jsonObject.put("SUCCESS",true);
        }catch(Exception  e){
            jsonObject.put("SUCCESS",false);
            e.printStackTrace();
        }
        return ResultFactory.create(jsonObject);
    }
    @RequestMapping(value = "/testtest")
    public Result  testtest(@Valid Test test, HttpServletResponse responese) throws Exception {
        JSONObject jsonObject = new JSONObject();
        LinkedList bcpMessages = null;
        BcpMessage bcpMessage = null;
        //接收表单数据，生成bcp文件
        bcpMessages = BcpMessageService.getEntityCreateBcp1(test);
        //生成xml文件
        String xmlPath = File.separator+ "home"+File.separator +"FIELD" + File.separator + "AQ_ZIP_INDEX.xml";
        BcpMessageService.createIndexXml(xmlPath,bcpMessages);
        //生成压缩文件
        try{
        BcpMessageService.createZIP("FIELD");
        jsonObject.put("SUCCESS",true);
        }catch(Exception  e){
            jsonObject.put("SUCCESS",false);
            e.printStackTrace();
        }
        return ResultFactory.create(jsonObject);
    }

    /**
     * 模板下载
     * @param
     * @return Result
     */
    @RequestMapping(value = "/downLoad")
    public Result downLoad(HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        //注册驱动，反射方式加载
        Class.forName("com.mysql.jdbc.Driver");
        //设置url
        String url = "jdbc:mysql://192.168.1.90:3306/test-demo?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
        //设置用户名
        String username = "root";
        //设置密码
        String password = "123";
        //获得连接对象
        Connection con = DriverManager.getConnection(url, username, password);
        //获得执行者对象
        String sql = "select attachment_id from file_attachment where attachment_name like '%模板%'";
        PreparedStatement ps = con.prepareStatement(sql);
        //获得结果集
        ResultSet rs = ps.executeQuery();
        //结果集处理，
        StringBuilder stringBuilder = new StringBuilder();

        while(rs.next()){
            stringBuilder.append(rs.getString("attachment_id")+",");
        }
        //释放资源
        rs.close();
        ps.close();
        con.close();
        if (stringBuilder.length() > 0)
            stringBuilder.deleteCharAt(stringBuilder.length() - 1); //调用 字符串的deleteCharAt() 方法,删除最后一个多余的逗号
        jsonObject.put("downloadId",stringBuilder);
        return ResultFactory.create(jsonObject);
    }
    /**
     * 接收字段属性
     * @param
     * @return Result
     */
    @RequestMapping(value = "/defineHead")
    public Result defineHead(@Valid DefineHead defineHead, HttpServletResponse responese) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String[] fieldName = defineHead.getFieldName();
        String[] fieldEng = defineHead.getFieldEng();
        String[] fieldLength = defineHead.getFieldLength();
        String[] fieldRemark = defineHead.getFieldRemark();
        String[] fieldHeadName = defineHead.getFieldHeadName();
        String[] fieldOption = null;
        if (defineHead.getFieldOption() != null){
             fieldOption = defineHead.getFieldOption();
        }

        LinkedList<InputValue> list = new LinkedList<>();
        
        for (int j = 0; j<fieldName.length; j++){
            InputValue inputValue = new InputValue();
            inputValue.setFieldName(fieldName[j]);
            inputValue.setFieldEng(fieldEng[j]);
            inputValue.setFieldRemark(fieldRemark[j]);
            inputValue.setFieldLength(fieldLength[j]);
            inputValue.setFieldHeadName(fieldHeadName[0]);

            if (defineHead.getFieldOption() != null){
                inputValue.setFieldOption(fieldOption[0]);
            }
            list.add(inputValue);
        }
        jsonObject.put("list",list);
        return ResultFactory.create(jsonObject);
    }
    /**
     * 处理提交的自定义行数据
     * @param
     * @return Result
     */
    @RequestMapping(value = "/getRowData")
    public Result getRowData(@Valid FieldVO fieldVO, HttpServletResponse responese) throws Exception {
        JSONObject jsonObject = new JSONObject();
        LinkedList bcpMessages = null;
        BcpMessage bcpMessage = null;
        //存储表单数据
        storRow(fieldVO);

        //接收表单数据，生成bcp文件
        if(fieldVO.getFieldOption().length >0){
            if(fieldVO.getFieldOption()[0].equals("1") ){
                //追加
                bcpMessages = BcpMessageService.getEntityCreateBcp2(fieldVO,1);
            }else{
                //新建
                bcpMessages = BcpMessageService.getEntityCreateBcp2(fieldVO,0);
            }
        }else{
            //新建
            bcpMessages = BcpMessageService.getEntityCreateBcp2(fieldVO,0);
        }
        /*if(fieldVO.getFieldOption()[0].equals("1") ){
            //追加
            bcpMessages = BcpMessageService.getEntityCreateBcp2(fieldVO,1);
        }else{

            bcpMessages = BcpMessageService.getEntityCreateBcp2(fieldVO,0);
        }*/

        //生成xml文件
        String xmlPath =  File.separator +"home"+File.separator +"FIELD" + File.separator + "AQ_ZIP_INDEX.xml";
        BcpMessageService.createIndexXml(xmlPath,bcpMessages);

        //生成压缩文件
        try{
            BcpMessageService.createZIP("FIELD");
            jsonObject.put("SUCCESS",true);
        }catch(Exception  e){
            jsonObject.put("SUCCESS",false);
            e.printStackTrace();
        }
        return ResultFactory.create(CodeMsgBase.SUCCESS,jsonObject);
    }

    /**
     * 数据行存储
     * @param
     * @return void
     */
    public void  storRow(FieldVO fieldVO) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Integer count = 0;
        StringBuilder sFieldName = new StringBuilder();
        StringBuilder sFieldEng = new StringBuilder();
        StringBuilder sFieldRemark = new StringBuilder();
        StringBuilder sFieldLength = new StringBuilder();
        StringBuilder sFieldValue = new StringBuilder();
        StringBuilder sFieldSize = new StringBuilder();
        StringBuilder sFieldHeadName = new StringBuilder();
        //表头
        sFieldHeadName.append(fieldVO.getFieldHeadName()[0]);
        //追加还是覆盖 0:追加  1:覆盖
        if(fieldVO.getFieldOption().length >0){
            String option = fieldVO.getFieldOption()[0];
            //删除同名表头数据
            if(option.equals("")  || Integer.parseInt(option) == 0){
                fieldRowMessageService.deleteByFieldName(sFieldHeadName.toString());
            }
        }

        for(int j = 0;j<fieldVO.getFieldValue().length;j++ ){
            count++;
            sFieldName.append(fieldVO.getFieldName()[j]+",");
            sFieldEng.append(fieldVO.getFieldEng()[j]+",");
            sFieldRemark.append(fieldVO.getFieldRemark()[j]+",");
            sFieldLength.append(fieldVO.getFieldLength()[j]+",");
            sFieldValue.append(fieldVO.getFieldValue()[j]+",");
            sFieldSize.append(fieldVO.getFieldSize()[0]+",");

            if(count >= Integer.parseInt(fieldVO.getFieldSize()[0])){
                count = 0;
                FieldRowMessage fieldRowMessage = new FieldRowMessage();

                fieldRowMessage.setFieldname(sFieldName.toString().substring(0,sFieldName.toString().lastIndexOf(",")));
                fieldRowMessage.setFieldeng(sFieldEng.toString().substring(0,sFieldEng.toString().lastIndexOf(",")));
                fieldRowMessage.setFieldremark(sFieldRemark.toString().substring(0,sFieldRemark.toString().lastIndexOf(",")));
                fieldRowMessage.setFieldlength(sFieldLength.toString().substring(0,sFieldLength.toString().lastIndexOf(",")));
                fieldRowMessage.setFieldvalue(sFieldValue.toString().substring(0,sFieldValue.toString().lastIndexOf(",")));
                fieldRowMessage.setFieldsize(sFieldSize.toString().substring(0,sFieldSize.toString().lastIndexOf(",")));
                fieldRowMessage.setFieldheadname(sFieldHeadName.toString());

                //插入数据库
                //fieldRowMessageService.deleteByFieldName(sFieldHeadName.toString());
                fieldRowMessageService.add(fieldRowMessage);
                sFieldName = new StringBuilder();
                sFieldEng = new StringBuilder();
                sFieldRemark = new StringBuilder();
                sFieldLength = new StringBuilder();
                sFieldValue = new StringBuilder();
                sFieldSize = new StringBuilder();
            }
        }
    }

    /**
     * 预览数据处理
     * @param
     * @return Result
     */
    @RequestMapping(value = "/previewHead")
    public Result previewHead( String  fieldHeadName, ServletRequest request,@RequestParam(defaultValue = "0")   int pageNumber,
                  @RequestParam(defaultValue = "10")   int pageSize,HttpServletResponse responese,@RequestParam(defaultValue = "create_date")    String sort) throws Exception {

        Map<String, Object> searchParams = new HashMap();
        searchParams.put("fieldHeadName",fieldHeadName);
        //分页返回
        Paging<FieldRowMessage> allByPage = fieldRowMessageService.findAllByPage(searchParams, pageNumber, pageSize, UtilWord.getDatabaseNameFromBeanName(sort));

        //自定义返回content
        LinkedList linkedList = new LinkedList();
        for (FieldRowMessage fieldRowMessage:allByPage.getContent()) {
            String fieldvalue = fieldRowMessage.getFieldvalue();
            String[] split = fieldvalue.split(",");
            String fieldname = fieldRowMessage.getFieldname();
            String[] split1 = fieldname.split(",");
            String fieldHeadname = fieldRowMessage.getFieldheadname();
            String[] split2 = fieldHeadname.split(",");

            FieldVO fieldVO = new FieldVO();
            fieldVO.setFieldValue(split);
            fieldVO.setFieldName(split1);
            fieldVO.setFieldHeadName(split2);
            linkedList.add(fieldVO);
        }
        allByPage.setContent(linkedList);
        return ResultFactory.create(allByPage);
    }
    /**
     * 获取浏览表头
     * @param
     * @return Result
     */
    @RequestMapping(value = "/getPreViewHead")
    public Result getPreViewHead( @Valid DefineHead defineHead,String  fieldHeadName, ServletRequest request, HttpServletResponse responese ) throws Exception {
        JSONObject jsonObject = new JSONObject();
        LinkedList<InputValue> list = new LinkedList<>();

        List<FieldRowMessage> byHead = fieldRowMessageService.findByHead(fieldHeadName);
        FieldRowMessage fieldRowMessage = byHead.get(0);
        String fn = fieldRowMessage.getFieldname();
        String[] fieldName = fn.split(",");
        String fe = fieldRowMessage.getFieldeng();
        String[] fieldEng = fe.split(",");
        String fl = fieldRowMessage.getFieldlength();
        String[] fieldLength = fl.split(",");
        String fr = fieldRowMessage.getFieldremark();
        String[] fieldRemark = fr.split(",");
        String fh = fieldRowMessage.getFieldheadname();
        String[] fieldheadname = fh.split(",");

        String[] fieldOption = null;
        if (defineHead.getFieldOption() != null){
            fieldOption = defineHead.getFieldOption();
        }
        for (int j = 0; j<fieldName.length; j++){
            InputValue inputValue = new InputValue();
            inputValue.setFieldName(fieldName[j]);
            inputValue.setFieldEng(fieldEng[j]);
            inputValue.setFieldRemark(fieldRemark[j]);
            inputValue.setFieldLength(fieldLength[j]);
            inputValue.setFieldHeadName(fieldHeadName);

            if (defineHead.getFieldOption() != null){
                inputValue.setFieldOption(fieldOption[0]);
            }
            list.add(inputValue);
        }
        jsonObject.put("list",list);
        return ResultFactory.create(jsonObject);
    }
    public void mkDirs(String filePath){
        File fileP=new File(filePath);
        //如果文件夹不存在就创建文件夹
        if (!fileP.exists()){
            fileP.mkdirs();
        }
    }
}
