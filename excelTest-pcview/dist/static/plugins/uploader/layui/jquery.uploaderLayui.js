/*
 * jQuery uploader plugin
 * Version 1.0 (02/04/2017)
 * 20170204，uploader从cpub迁移到ezdev。
 *
 * @requires jQuery v1.2.6 or later
 * @requires ezdev-service-uploader  内含uploader的Controller用于上传/下载
 */
(function($) {
    //uploader/layui/upload/tpl/img
    let imgTpl = 'img';

    //uploaderLayui主函数，根据options里的config，生成uploader。
    $.fn.uploaderLayui = function(options) {

        //merge into a final option
        var opts = $.extend({}, $.fn.uploaderLayui.defaults, options);
        //获取config
        var config = opts.config;

        //如果已经存在上传控件，删除。注意，$(this)指的是<uploader>元素。
        var containerId = $(this).next().attr('id');
        var nextIsUdr = containerId == 'udr-main-'+config.uploaderId;//上传控件
        var nextIsUdrShowBack = containerId == 'udr-main-showback-'+config.uploaderId;//纯回显控件
        if( nextIsUdr || nextIsUdrShowBack ){
            $(this).next().remove();
        }

        //根据config生成上传元素（上传按钮、输入框等）或纯回显元素。
        var result = renderUploader(config);

        //把生成的元素放入页面，放在<uploader>元素后面
        $(this).after(result.uploader);

        //设置属性：uploader，值为它的configId
        $(this).attr('uploader',result.configId);

        //上传成功后刷新用的回调
        config.funRefreshUploader = refreshUploader;

        //注册完毕后的回调
        config.funAfterRegist = function (config) {
            //注册完毕后，添加img42按钮
            if( config.type == "img42" ) {
                appendImg42(config);
            }
        };

        //重新获取数据的回调
        config.fucRefreshData = getShowBackData;

        //返回它的configId
        return result.configId;
    };

    //默认值
    $.fn.uploaderLayui.defaults = {
        config:{
            inputWidth:'150px',
            impl:'layui'
        }
    };

    //上传控件的回显的刷新，用于上传成功后的刷新。
    function refreshUploader(config) {

        //子层。回显已上传附件列表的工作交由handler完成，不在这里做。
        if( config.isSon ){
            return;
        }

        //重新获取回显数据
        getShowBackData(config);

        var type = config.type;

        //显示上传图标，无输入框。第一次渲染需要根据id回显图像，上传成功后需要回显图像。
        if( type=="img42" ){
            showBackImg(config);
        }else
        //显示右侧链接，有输入框。第一次渲染需要根据id回显链接和回显输入框，上传成功后需要回显链接和回显输入框。
        if( type=="aLink" ){
            showBackInput(config);
            showBackALink(config);
        }else
        //同批次多个附件，有输入框。第一次渲染需要根据id回显输入框，上传成功后需要回显输入框。
        if( type=="dad" ){
            showBackInput(config);
        }
        //默认效果，有输入框。第一次渲染需要根据id回显输入框，上传成功后需要回显输入框。
        if( type=="default" ){
            showBackInput(config);
        }
    };

    //生成上传元素（layui的uploader按钮等），页面第一次渲染时调用。
    function renderUploader(config) {

        var result = {};

        var uploaderId = config.uploaderId;
        var type = config.type;

        if (!type) {
            type = "default";
        }

        //取回显数据，如，url、文件名等。并放入config。
        getShowBackData(config);

        //生成uploader的主div
        var uploaderStrStart = '<div ';
        var uploaderStrEnd = '></div>';
        var uploaderStrBody = ' class="upload-inside" ';
        if( config.isShowBack ){
            uploaderStrBody += 'id="udr-main-showback-' + uploaderId + '"';
        }else{
            uploaderStrBody += 'id="udr-main-' + uploaderId + '"';;
            if( type=="img42" ) {
                uploaderStrBody += ' style="width:42px;height:42px;position:relative;" ';
            }
        }
        var uploader = $(uploaderStrStart+uploaderStrBody+uploaderStrEnd);

        //显示右侧链接，有输入框。第一次渲染需要根据id回显链接和回显输入框，上传成功后需要回显链接和回显输入框。
        if (type == "aLink") {
            appendInput(uploader, config);
        } else
        //同批次多个附件，有输入框。第一次渲染需要根据id回显输入框，上传成功后需要回显输入框。
        if (type == "dad") {
            appendInput(uploader, config);
        }
        //默认效果，有输入框。第一次渲染需要根据id回显输入框，上传成功后需要回显输入框。
        if (type == "default") {
            appendInput(uploader, config);
        }
        //子层，无输入框。回显已上传附件列表的工作交由handler完成，不在这里做。
        if (type == "son") {

        }
        //图片式，无输入框。添加按钮和第一次回显工作交由afterRegist回调完成，不在这里做。
        if (type == "img42") {

        }

        //生成按钮（回显控件不需要生成）
        if( !config.isShowBack ){
            if (type == "dad") {
                //对于父子式上传控件，父层的上传按钮为单纯的button
                uploader.append('<button id="udr-file-'+uploaderId+'-button" class="uploader-div-button" >' +
                    '<span>选择文件</span>' +
                    '</button>');
            } else {
                //对于普通上传控件，上传按钮为layui规定的input
                var buttonStr = '<input type="file" name="infile" id="udr-file-' + uploaderId + '-button" uploaderId="' + uploaderId + '"';
                if( type == "img42" ){
                    //对于img42上传控件，上传按钮为uploader-img-inputFile类型。
                    buttonStr += ' class="uploader-img-inputFile" style="float:left;display:none"></input> ';
                }else{
                    //对于普通上传按钮，上传按钮为layui-upload-file类型。
                    buttonStr += ' class="layui-upload-file" style="float:left"></input> ';
                }
                uploader.append(buttonStr);
            }
        }

        //添加隐藏域，保存附件的唯一标识，用于存储和读取config
        var configId = "";
        if( config.isShowBack ){
            configId = uploaderId + "ShowBack"+guid();
        }else{
            configId = uploaderId;
        }
        config.configId = configId;
        uploader.append('<input type="hidden" id="'+configId+'"use="savedConfigId" value="'+configId+'" />');

        //生成右侧链接，有输入框。第一次渲染需要根据id回显链接和回显输入框，上传成功后需要回显链接和回显输入框。
        if (type == "aLink") {
            appendALink(uploader, config);
        }

        //生成aLink方式回显（纯回显控件）
        if (type == "aLink-showback"){
            appendALinkShowBack(uploader, config);
        }

        //生成img42方式回显（纯回显控件）
        if (type == "img42-showback"){
            appendImg42ShowBack(uploader, config);
        }

        //返回生成完毕的元素，和configId。
        result.uploader = uploader;
        result.configId = configId;

        return result;
    }

    //生成uuid的函数
    function guid() {
        return 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'.replace(/[x]/g, function(c) {
            var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
            return v.toString(16);
        });
    }

    /**
     * 获取回显数据，放入config中（config.showBackData）。
     */
    function getShowBackData(config) {

        //获取数据
        $.ajax({
            type : "get",
            url : "uploader/info/"+ config.uploaderId+"?id="+Math.random(),
            dataType : "json",
            async:false,
            success : function(data) {
                config.showBackDataFilled = true;
                if( !data || data.code != "0"){
                    config.showBackDataFilled = false;
                    return;
                }
                if( data.data == null ){
                    config.showBackDataFilled = false;
                    config.showBackIsMulty = config.isDad || config.isSon;
                    config.showBackAttaches = {};
                    config.showBackFileName = "";
                    config.showBackUrl = "";
                    return;
                }
                var attachArray = data.data;
                var containsMulty = false;
                if( $.isArray(attachArray) ){
                    containsMulty = attachArray.length > 1;
                }
                var isMulty = config.isDad || config.isSon || config.isMulty || containsMulty ;
                //同批次多个附件
                if( isMulty ){
                    var showBackFileName = "";
                    for(var i=0; i<attachArray.length; i++){
                        showBackFileName += attachArray[i].attachmentName;
                        if( i < attachArray.length - 1 ){
                            showBackFileName += ',';
                        }
                    }
                    config.showBackIsImg = false;
                    config.showBackIsMulty = true;
                    config.showBackFileName = showBackFileName;
                    config.showBackAttaches = attachArray;
                    config.showBackUrl = "uploader/"+config.uploaderId;
                }
                //单附件
                else{
                    config.showBackIsImg = false;
                    config.showBackIsMulty = false;
                    config.showBackFileName = attachArray[0].attachmentName;
                    config.showBackUrl = "uploader/"+config.uploaderId;
                    var suffixIndex = config.showBackFileName.lastIndexOf(".");
                    var totalLen = config.showBackFileName.length;
                    if( suffixIndex > 0 && suffixIndex < totalLen - 1 ){
                        config.showBackFileSuffix = config.showBackFileName.substr(suffixIndex+1).toLowerCase();
                        var sfx = config.showBackFileSuffix;
                        if (sfx == "jpg"
                            || sfx == "bmp"
                            || sfx == "png"
                            || sfx == "gif"
                            || sfx == "jpeg") {
                            config.showBackIsImg = true;
                        }
                    }
                }
            }.bind(this)
        });
    }

    //添加输入文本框，用于显示上传的文件的文件名
    function appendInput(udr,config){
        var uploaderId = config.uploaderId;
        var showBackFileName = config.showBackFileName?config.showBackFileName:"";

        var inputWidth = "";
        if( config.inputWidth ){
            inputWidth = " width:"+config.inputWidth+" ";
        }
        udr.append('<input type="text" id="udr-leftInput-'+uploaderId+'" class="uploader-input" ' +
            'use="showBackFileName" readonly="readonly" style="float: left; ' + inputWidth +
            'margin-right:5px;" placeholder=" " value="'+showBackFileName+'" >');
    }

    //添加回显链接，用于显示上传的文件的链接
    function appendALink(udr,config){
        //对于回显用的控件，如果数据为空，不显示
        if( !config.showBackDataFilled ){
            return;
        }
        var uploaderId = config.uploaderId;
        var showBackFileName = config.showBackFileName||config.showBackAttaches||"";
        var showBackUrl = config.showBackUrl;

        var aLinkDeleteBtn = $('<span id="udr-deleteSpan-'+uploaderId+'" style="position: absolute; z-index: 2; ' +
            'right:0px; margin-top:4px; cursor:pointer" ' +
            'e-event="href:/uploader/layui/upload/delete?url=/uploader/delete/" ' +uploaderId+
            '&code='+uploaderId+'&type=aLink" ' +
            '><a class="uploaderDelete" ' +
            'id="udr-aLinkDeleteBtn-'+uploaderId+'" ' +
            ' /></span>');//删除按钮，调用upload.js里的delete方法。

        //如果是图片，点击打开图片；如果不是，点击下载
        var titleStr = ",title:图片";
        var aLink = undefined;
        if( config.showBackIsImg && !config.showBackIsMulty ){
            aLink = $('<span id="udr-aLinkSpan-'+uploaderId+'" style="position: absolute; z-index: 1; margin-top:4px;">' +
                '<a id="udr-aLink-'+uploaderId+'" href="javascript:void(0);return false;" target="_blank" ' +
                'e-event="href:/uploader/layui/upload/dialog?data='+showBackUrl+'&tpl='+imgTpl+ titleStr + '"' +
                '>'+showBackFileName+'</a></span>');
        }else{
            aLink = $('<span id="udr-aLinkSpan-'+uploaderId+'" style="position: absolute; z-index: 1; margin-top:4px;">' +
                '<a id="udr-aLink-'+uploaderId+'" href="'+showBackUrl+'" target="_blank" ' +
                '>'+showBackFileName+'</a></span>');
        }

        udr.append(aLinkDeleteBtn);
        udr.append(aLink);
    }
    //添加回显链接，用于显示上传的文件的链接
    function appendALinkShowBack(udr,config){

        //对于回显用的控件，如果数据为空，不显示
        //我只是默默的抄写
        if( !config.showBackDataFilled ){
            return;
        }
        var uploaderId = config.uploaderId;
        var showBackFileName = config.showBackFileName||config.showBackAttaches||"";
        var showBackUrl = config.showBackUrl;
        var aLink = undefined;
        //如果是图片，点击打开图片；如果不是，点击下载
        var titleStr = ",title:图片";
        if( config.showBackIsImg && !config.showBackIsMulty ){
            aLink = $('<span id="udr-aLink-showbackSpan-'+uploaderId+'" style=" z-index: 1; margin-top:4px;">' +
                '<a id="udr-aLink-showback-'+uploaderId+'" href="javascript:void(0);return false;" target="_blank" ' +
                'e-event="href:/uploader/layui/upload/dialog?data='+showBackUrl+'&tpl='+imgTpl+ titleStr + '"' +
                '>'+showBackFileName+'</a></span>');
        }else{
            aLink = $('<span id="udr-aLink-showbackSpan-'+uploaderId+'" style=" z-index: 1; margin-top:4px;">' +
                '<a id="udr-aLink-showback-'+uploaderId+'" href="'+showBackUrl+'" target="_blank" ' +
                '>'+showBackFileName+'</a></span>');
        }

        udr.append(aLink);
    }

    //添加img42按钮
    function appendImg42(config){

        //添加img42按钮，指定其click动作，为layui注册后的上传按钮的动作（点击后上传）
        var clickAction = "$('input[id=udr-file-"+config.uploaderId+"-button]').click();";
        var uploaderId = config.uploaderId;
        var imgBtnDivStr = '<div id="uploader-img-buttonArray-showback-'+uploaderId+'">' +
            '<div class="uploader-img-button" style="position:absolute;top:0px;left:0px;" ' +
            'title="'+config.showBackFileName+'" onclick="'+clickAction+'"></div></div>';
        var imgBtnDiv = $(imgBtnDivStr);
        imgBtnDiv.find('.uploader-img-button').css({
            'background-image' : "url('" + config.showBackUrl + "')",
            'background-size' : '100% 100%',
            'text-indent' : '-9999px'
        });
        var saveConfigIdDiv = $('#udr-main-' + config.uploaderId).find('input[use="savedConfigId"]');
        saveConfigIdDiv.before(imgBtnDiv);
    }

    //添加回显图片，用于显示上传的文件的图片效果
    function appendImg42ShowBack(udr,config){

        //我只是默默的抄写
        if( !config.showBackDataFilled ){
            return;
        }

        var uploaderId = config.uploaderId;
        var showBackUrl = config.showBackUrl;
        var showBackFileName = config.showBackFileName;

        //添加imgBtn
        var titleStr = ",title:图片";
        var imgBtnStr = '<div id="uploader-img-buttonArray-showback-'+uploaderId+'"><div class="uploader-img-button" title="'+showBackFileName+'" style="position:absolute;top:0px;left:0px;" ' +
            'e-event="href:/uploader/layui/upload/dialog?data='+showBackUrl+'&tpl='+imgTpl+ titleStr + '"' +
            '></div></div>';
        var imgBtnDiv = $(imgBtnStr);
        var saveConfigIdDiv = udr.find("input[use='savedConfigId']");
        saveConfigIdDiv.before(imgBtnDiv);

        imgBtnDiv.find('.uploader-img-button').css({
            'background-image' : "url('"
            + showBackUrl + "')",
            'background-size' : '100% 100%',
            'text-indent' : '-9999px'
        });

    }

    //回显图像（图片式回显）
    function showBackImg(config){
        //对于回显，如果数据为空，不回显
        if( !config.showBackDataFilled ){
            return;
        }

        var id = guid();
        var uploaderId = config.uploaderId;
        //注：type="file" name="uploadify" 的input，也就是uploadify需要解析的input，
        //它的id为udr-file-uploaderId，其内的上传按钮的id为input的id+"-button"
        $('#udr-main-'+uploaderId).find('.uploader-img-button').css({
            'background-image' : "url('" + config.showBackUrl + "?id="+id+"')",
            'background-size' : '100% 100%',
            'text-indent' : '-9999px'
        });
    }

    //回显输入框
    function showBackInput(config){
        //对于回显，如果数据为空，不回显
        if( !config.showBackDataFilled ){
            return;
        }
        var uploaderId = config.uploaderId;
        var leftInput = $('#udr-leftInput-'+uploaderId);
        leftInput.val(config.showBackFileName);
        leftInput.attr('title', config.showBackFileName);
    }

    //回显链接
    function showBackALink(config){
        //对于回显，如果数据为空，不回显
        if( !config.showBackDataFilled ){
            return;
        }
        var uploaderId = config.uploaderId;
        var aLink = $('#udr-aLink-'+uploaderId);
        //如果已经被删除，重新添加
        if( aLink.length == 0 ){
            //主div
            var uploader = $('#udr-main-'+uploaderId);
            appendALink(uploader,config);
        }
        //所有上传按钮右边的回显，和所有纯回显
        aLink = $('#udr-aLink-'+uploaderId+',#udr-aLink-showback-'+uploaderId);
        aLink.text(config.showBackFileName);

        //如果是图片，点击打开图片；如果不是，点击下载

        if( config.showBackIsImg && !config.showBackIsMulty ){
            var titleStr = ",title:图片";
            var newEOption = 'data='+config.showBackUrl+'&tpl='+imgTpl+ titleStr + '';
            // aLink.attr('e-options', newEOption);
            var eEvent = aLink.attr('e-event');
            let  index = eEvent.indexOf("?");
            if(index>0){
                eEvent = eEvent.substring(0,index)+"?"+newEOption;
            }else {
                eEvent = eEvent+"?"+newEOption;
            }
            aLink.attr('e-event',eEvent);
        }else{
            aLink.attr('href', config.showBackUrl);
        }

    }

})(jQuery);











