/*
* jQuery uploader plugin
* Version 1.0 (02/04/2017)
* 20170204，uploader从cpub迁移到ezdev。
*
* @requires jQuery v1.2.6 or later
* @requires ezdev-service-uploader  内含uploader的Controller用于上传/下载
*/
(function($) {

    //uploader主函数
    $.fn.uploader = function(options) {

        //merge into a final option
        var opts = $.extend({}, $.fn.uploader.defaults, options);
        var config = opts.config;

        var uploaderId = config.bid;
        //如果使用者自定义了bid，使用bid作为附件的唯一标识；如果未定义，生成。
        if( !uploaderId ){
            uploaderId= guid();
        }

        //双层上传的子层，需要获取父层的uploaderId
        config.isNormal = false;
        config.isSon = false;
        config.isDad = false;
        //子层
        if( config.type == "son" ){
            config.isSon = true;
        }else
        //父层
        if( config.type == "dad" ){
            config.isDad = true;
        }
        //普通
        else{
            config.isNormal = true;
        }

        //把uploaderId设置回config
        config.uploaderId = uploaderId;

        //虽然使用了each，但，调用uploader()时，请务必保证调用者为一个元素。
        //iterator all selected elements
        this.each(function() {

            var containerId = $(this).next().attr('id');
            if( containerId == 'udr-main-'+config.uploaderId ){
                $(this).next().remove();
            }

            //生成上传元素，即uploadify的按钮等。
            var udr = renderUploader(config);
            //放入页面，放在<uploader >元素后面
            $(this).after(udr);

        });

        //uploader的唯一标识
        return uploaderId;
    };

    //默认值
    $.fn.uploader.defaults = {
        config:{
            inputWidth:'150px'
        }
    };

    //uploader辅助函数：刷新回显
    $.fn.uploaderRefreshShowBack = function(config) {

        var type = config.type;
        //子层。回显已上传附件列表的工作交由handler完成，不在这里做。
        if( type=="son" ){
            return;
        }

        //随时回显开始

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

        //随时回显结束

    };

    //uploader辅助函数：获取回显数据
    $.fn.uploaderGetShowBackData = function(config) {
        getShowBackData(config);
    }

    // 生成上传元素，即uploadify的按钮等。
    function renderUploader(config) {

        var uploaderId = config.uploaderId;
        var type = config.type;

        if( !type ){
            type = "default";
        }

        //初次回显开始
        //取回显数据（url:"attach/info/uploaderId"），
        // 如，url、文件名。并放入config（config.showBackData）
        getShowBackData(config);

        //生成uploader的主div
        var uploader = $('<div id="udr-main-'+uploaderId+'" class="upload-inside" ></div>');

        //显示上传图标，无输入框。第一次渲染需要根据id回显图像，上传成功后需要回显图像。
        if( type=="img42" ){
            showBackImg(config);
        }else
        //显示右侧链接，有输入框。第一次渲染需要根据id回显链接和回显输入框，上传成功后需要回显链接和回显输入框。
        if( type=="aLink" ){
            appendInput(uploader,config);
            appendALink(uploader,config);
        }else
        //同批次多个附件，有输入框。第一次渲染需要根据id回显输入框，上传成功后需要回显输入框。
        if( type=="dad" ){
            appendInput(uploader,config);
        }
        //默认效果，有输入框。第一次渲染需要根据id回显输入框，上传成功后需要回显输入框。
        if( type=="default" ){
            appendInput(uploader,config);
        }
        //子层，无输入框。回显已上传附件列表的工作交由handler完成，不在这里做。
        if( type=="son" ){

        }
        //初次回显结束

        //添加uploadify控件  此控件将被转换成flash-object等多个控件。
    	uploader.append('<input id="udr-file-'+uploaderId+'" type="file" name="uploadify" style="float:left"  /> ' );

    	//添加隐藏域，保存附件的唯一标识
    	uploader.append('<input id="udr-saveBid-'+uploaderId+'" type="hidden" use="saveBid" value="'+uploaderId+'" />');

        return uploader;
    }

    //生成uuid的函数
    function guid() {
        return 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'.replace(/[x]/g, function(c) {
            var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
            return v.toString(16);
        });
    }

    //添加输入文本框，用于显示上传的文件的文件名
    function appendInput(udr,config){
        var uploaderId = config.uploaderId;
        var showBackFileName = config.showBackFileName?config.showBackAttaches:"";

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
        var showBackFileName = config.showBackFileName?config.showBackAttaches:"";
        var showBackUrl = config.showBackUrl;

        var aLinkDeleteBtn = $('<span id="udr-deleteSpan-'+uploaderId+'" style="position: absolute; z-index: 2; ' +
                                'right:0px; margin-top:4px; cursor:pointer" ' +
                                'e-handle="event:click,href:/uploader/uploadify/upload/delete" ' +
                                'e-options="url:/uploader/delete/'+uploaderId+',code:'+uploaderId+',type:aLink" ' +
                                '><a class="uploaderDelete" ' +
                                'id="udr-aLinkDeleteBtn-'+uploaderId+'" ' +
                                ' /></span>');//删除按钮，调用upload.js里的delete方法。
        var aLink = $('<span id="udr-aLinkSpan-'+uploaderId+'" style="position: absolute; z-index: 1; margin-top:4px;">' +
                        '<a id="udr-aLink-'+uploaderId+'" href="'+showBackUrl+'" target="_blank" >'+showBackFileName+'</a></span>');

        udr.append(aLinkDeleteBtn);
        udr.append(aLink);
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
                var isMulty = config.isDad || config.isSon;
                //同批次多个附件
                if( isMulty ){
                    var showBackFileName = "";;
                    for(var i=0; i<attachArray.length; i++){
                        showBackFileName += attachArray[i].attachmentName;
                        if( i < attachArray.length - 1 ){
                            showBackFileName += ',';
                        }
                    }
                    config.showBackIsMulty = true;
                    config.showBackFileName = showBackFileName;
                    config.showBackAttaches = attachArray;
                }
                //单附件
                else{
                    config.showBackIsMulty = false;
                    config.showBackFileName = attachArray[0].attachmentName;
                    config.showBackUrl = "uploader/"+config.uploaderId;
                }
            }.bind(this)
        });
    }

    //回显图像（图片式回显）
    function showBackImg(config){
        //对于回显，如果数据为空，不回显
        if( !config.showBackDataFilled ){
            return;
        }
        var uploaderId = config.uploaderId;
        //注：type="file" name="uploadify" 的input，也就是uploadify需要解析的input，
        //它的id为udr-file-uploaderId，其内的上传按钮的id为input的id+"-button"
        $('#udr-file-'+uploaderId+'-button').css({
            'background-image' : "url('"
            + config.showBackUrl + "')",
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
        if( aLink.length == 0 ){
            //主div
            var uploader = $('#udr-main-'+uploaderId);
            appendALink(uploader,config);
            aLink = $('#udr-aLink-'+uploaderId);
        }
        aLink.text(config.showBackFileName);
        aLink.attr('href', config.showBackUrl);
    }

})(jQuery);





