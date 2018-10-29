webpackJsonpvendor([7],{

/***/ 250:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__ = __webpack_require__(18);
/**
 * Created by zhaofs on 2017/5/6.
 * 演示Handler应用
 */


/* harmony default export */ __webpack_exports__["default"] = (new __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__["default"]({
    resolveTpl: function resolveTpl(tpl) {
        console.log("模板！！！！：：" + tpl);
        return __webpack_require__(306)("./" + tpl + ".html");
    },
    //index调用完毕
    onIndexed: function onIndexed(args) {},
    //编辑
    onEdited: function onEdited(args) {
        //初始化 options
        this.options = {
            treeId: 'tree_id',
            formId: 'form',
            listId: 'list',
            pageId: 'pager'
        };
        //调用生成树
        var thisObj = this;
        //setTimeout(function(){
        //this.buildEditer($('select[name="conditionType"]').val(),$('select[name="type"]').val());
        var conditionTypeBackup = $('select[name="type"]').html();
        var conditionTypeBackupTrue = $('select[name="conditionType"]').html();
        var targetBackup = $('input[name="target"]').val();

        this.targetTypeChange(conditionTypeBackup, targetBackup);

        this.valueTypeChange();

        this.buildEditer($('select[name="conditionType"]').val(), $('select[name="type"]').val());

        $('select[name="targetType"]').change(function () {

            this.targetTypeChange(conditionTypeBackup, targetBackup);
            this.buildEditer($('select[name="conditionType"]').val(), $('select[name="type"]').val());
            this.valueTypeChange();
        }.bind(this));

        $('select[name="conditionType"],select[name="type"]').change(function () {

            $('textarea[name="value"]').val('');
            this.valueTypeChange();
            this.buildEditer($('select[name="conditionType"]').val(), $('select[name="type"]').val());
        }.bind(this));
        //}.bind(this),100);
    },
    //添加
    onAdded: function onAdded(args) {
        this.options = {
            treeId: 'tree_id',
            formId: 'form',
            listId: 'list',
            pageId: 'pager'
        };
        //调用生成树
        //setTimeout(function(){
        //this.buildEditer($('select[name="conditionType"]').val(),$('select[name="type"]').val());
        var conditionTypeBackup = $('select[name="type"]').html();
        var conditionTypeBackupTrue = $('select[name="conditionType"]').html();
        console.log(conditionTypeBackupTrue);
        var targetBackup = $('input[name="target"]').val();

        this.targetTypeChange(conditionTypeBackup, targetBackup);

        this.valueTypeChange();

        this.buildEditer($('select[name="conditionType"]').val(), $('select[name="type"]').val());

        $('select[name="targetType"]').change(function () {

            this.targetTypeChange(conditionTypeBackup, targetBackup);
            this.buildEditer($('select[name="conditionType"]').val(), $('select[name="type"]').val());
        }.bind(this));

        $('select[name="conditionType"],select[name="type"]').change(function () {

            $('textarea[name="value"]').val('');
            this.valueTypeChange();
            this.buildEditer($('select[name="conditionType"]').val(), $('select[name="type"]').val());
        }.bind(this));
        // }.bind(this),100);
    },
    goBackB: function goBackB(args) {
        $("#searchForm #zdpid").val(0);
        this.search(args);
        $("#goBackButton").hide();
    },
    //编辑时右侧树
    buildEditer: function buildEditer(conditionType, valueType) {
        $.fn.zTree.destroy(this.options.treeId);
        var rangeUrl;
        if (valueType == "0") {
            rangeUrl = "system/core/organization/list";
        } else if (valueType == "1") {
            rangeUrl = "system/core/user/orgusertree";
        } else {
            return;
        }

        switch (conditionType) {

            case "001":case "002":case "003":case "004":case "005":case "006":case "017":
                var tree_setting = {
                    check: {
                        enable: true,
                        chkboxType: { "Y": "", "N": "" }
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onCheck: function (event, treeId, treeNode) {
                            if (conditionType != "017") {
                                $('#submitForm [name="value"]').empty();
                                $('#submitForm [name="value"]').val(treeNode.id);
                            } else {

                                var treeObj = $.fn.zTree.getZTreeObj(this.options.treeId);
                                var chknodes = treeObj.getCheckedNodes(true);
                                var bindnodes = "";
                                chknodes.forEach(function (item, i) {
                                    bindnodes += item.id + ",";
                                });
                                $('#submitForm [name="value"]').empty();
                                $('#submitForm [name="value"]').val(bindnodes);
                            }
                        }.bind(this)
                    }
                };
                if (conditionType != "017") {
                    tree_setting.check.chkStyle = "radio";
                    tree_setting.callback.beforeCheck = function (treeId, treeNode) {
                        if (treeNode.checked) {
                            return;
                        }
                        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
                        var nodes = zTree.getCheckedNodes(true);
                        $.each(nodes, function (i, n) {
                            n.checked = false;
                        });
                        zTree.refresh();
                    }.bind(this);
                } else {
                    tree_setting.check.chkStyle = "checkbox";
                }

                $.ajax({
                    cache: false,
                    async: false, // 同步加载
                    type: "POST",
                    url: rangeUrl,
                    dataType: "json",
                    success: function (data) {
                        var nodes = data.data;
                        if (valueType == "1") {
                            nodes.forEach(function (item, i) {
                                if (item.type == 'user') {
                                    item.iconSkin = "user";
                                } else {
                                    item.nocheck = true;
                                }
                            });
                        }

                        $.fn.zTree.init($('#' + this.options.treeId), tree_setting, nodes);
                        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);

                        var value = $('#submitForm [name="value"]').val();
                        if (value.indexOf(',') == -1) {
                            var node = zTree.getNodeByParam("id", value);
                            if (node) {
                                node.checked = true;
                                zTree.updateNode(node);
                            }
                        } else {
                            var values = value.split(',');
                            values.forEach(function (id) {

                                var node = zTree.getNodeByParam("id", id);
                                if (node) {
                                    node.checked = true;
                                    zTree.updateNode(node);
                                }
                            });
                        }
                        zTree.expandAll(true);
                    }.bind(this)
                });
                break;
        }
    },
    targetTypeChange: function targetTypeChange(conditionTypeBackup, targetBackup) {

        switch ($('select[name="targetType"]').val()) {
            case "0":
                $('select[name="type"]').empty().html(conditionTypeBackup);
                $('select[name="type"] option').each(function () {

                    if ($(this).val() != "0" && $(this).val() != "3" && $(this).val() != "4" && $(this).val() != "5" && $(this).val() != "6") {
                        $(this).remove();
                    }
                });

                $('input[name="target"]').val('*.orgId');
                $('input[name="target"]').parents('div.form-group').hide();
                //20170915suntf【目标】非自定义时，隐藏【 自定义范围】
                $('textarea[name="value"]').parents('div.form-group').hide();
                //$('textarea[name="value"]').val('');
                $('.con-bottom-right').show();
                break;
            case "1":
                $('select[name="type"]').empty().html(conditionTypeBackup);
                $('select[name="type"] option').each(function () {
                    if ($(this).val() != "1" && $(this).val() != "7") {
                        $(this).remove();
                    }
                });
                $('.con-bottom-right').show();
                $('input[name="target"]').val('*.createBy');
                $('input[name="target"]').parents('div.form-group').hide();
                //20170915suntf【目标】非自定义时，隐藏【 自定义范围】
                $('textarea[name="value"]').parents('div.form-group').hide();
                //$('textarea[name="value"]').val('');
                break;
            case "2":
                $('select[name="type"]').empty().html(conditionTypeBackup);
                $('input[name="target"]').val(targetBackup);
                $('input[name="target"]').parents('div.form-group').show();
                break;
        }
    },
    valueTypeChange: function valueTypeChange() {

        switch ($('select[name="type"]').val()) {
            case "0":case "1":
                //$('select[name="conditionType"]').removeAttr("disabled");
                $('textarea[name="value"]').parents('div.form-group').hide();
                $('.con-bottom-right').show();
                break;
            case "2":
                //$('select[name="conditionType"]').removeAttr("disabled");
                $('textarea[name="value"]').parents('div.form-group').show();
                $('.con-bottom-right').hide();
                break;
            case "3":case "7":
                $('select[name="conditionType"]').val('001');
                //$('select[name="conditionType"]').attr("disabled","disabled");
                $('textarea[name="value"]').parents('div.form-group').hide();
                $('.con-bottom-right').hide();
                break;
            case "4":case "5":case "6":
                $('select[name="conditionType"]').val('017');
                //$('select[name="conditionType"]').attr("disabled","disabled");
                $('textarea[name="value"]').parents('div.form-group').hide();
                $('.con-bottom-right').hide();
                break;
        }
    },
    checkSubmit: function checkSubmit(args) {
        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
        if (zTree != null && zTree.getCheckedNodes().length == '0') {
            layer.msg("请选择数据范围");
        } else {
            this.submit(args);
        }
    }
}));

/***/ }),

/***/ 306:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./add.html": 307,
	"./detail.html": 308,
	"./edit.html": 309,
	"./index.html": 310,
	"./list.html": 311
};
function webpackContext(req) {
	return __webpack_require__(webpackContextResolve(req));
};
function webpackContextResolve(req) {
	var id = map[req];
	if(!(id + 1)) // check for number or string
		throw new Error("Cannot find module '" + req + "'.");
	return id;
};
webpackContext.keys = function webpackContextKeys() {
	return Object.keys(map);
};
webpackContext.resolve = webpackContextResolve;
module.exports = webpackContext;
webpackContext.id = 306;

/***/ }),

/***/ 307:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n\r\n<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        新建数据\r\n        <small>新建数据</small>\r\n    </h1>\r\n\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row-fluid">\r\n        <div class="col-sm-6">\r\n            <div class="box">\r\n                <div class="box-body">\r\n                    <form id="submitForm" role="form">\r\n\r\n                        <!-- hidden input -->\r\n                        <input type="hidden" name="id" value="';
    $$out += $escape(data.datascope.id);
    $$out += '">\r\n                        <!-- text input -->\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;数据名称</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.datascope.name);
    $$out += '" class="form-control"\r\n                                   placeholder="请输入数据名称" datatype="/^[^ ]{1,10}$/"  errormsg="请填写1到10位不含空格的内容"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;目标</label>\r\n                            <select id="targetType" name="targetType" class="form-control">\r\n                                ';
    $each(data.targetType, function (item, i) {
        $$out += '\r\n                                ';
        if (i == data.datascope.targetType) {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '" selected>';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        } else {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '">';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        }
        $$out += '\r\n                                ';
    });
    $$out += '\r\n                            </select>\r\n\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;自定义目标</label>\r\n                            <input type="text" name="target"\r\n                                   class="form-control"  placeholder="请输入目标字段"\r\n                                   value="';
    $$out += $escape(data.datascope.target);
    $$out += '" datatype="/^[^ ]{1,50}$/"><span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;条件</label>\r\n                            <select id="conditionType" name="conditionType" class="form-control">\r\n                                ';
    $each(data.conditionType, function (item, i) {
        $$out += '\r\n                                ';
        if (i == data.datascope.conditionType) {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '" selected>';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        } else {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '">';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        }
        $$out += '\r\n                                ';
    });
    $$out += '\r\n                            </select>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;范围</label>\r\n                            <select id="type" name="type" class="form-control">\r\n                                ';
    $each(data.valueType, function (item, i) {
        $$out += '\r\n                                ';
        if (i == data.datascope.type) {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '" selected>';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        } else {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '">';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        }
        $$out += '\r\n                                ';
    });
    $$out += '\r\n                            </select>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;自定义范围</label>\r\n                            <textarea rows="5" cols="55" name="value" class="form-control"\r\n                                      ignore="ignore" datatype="/^[^ ]{0,200}$/">';
    $$out += $escape(data.datascope.value);
    $$out += '</textarea>\r\n                        </div>\r\n                        <div class="box-footer col-sm-12">\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/checkSubmit?type=post&url=/system/core/datascope/save&contentType=application/x-www-form-urlencoded&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="save_btn" type="button" title="保存信息"\r\n                                    >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn"\r\n                                    type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n\r\n                    </form>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class="col-sm-6">\r\n            <div class="box con-bottom-right">\r\n                <div class="box-header">\r\n                    <div>\r\n                        选择数据范围\r\n                    </div>\r\n                </div>\r\n                <div class="box-body"  style="overflow-x: auto">\r\n                    <!--<div class="form-group col-sm-12"><span><p></p></span></div>-->\r\n                    <div class="form-group col-sm-12" style="min-width:400px;">\r\n                        <ul id="tree_id" class="ztree "></ul>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 308:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n\t<h1>\r\n\t\t数据管理\r\n\t\t<small>编辑</small>\r\n\t</h1>\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n\t<div class="row-fluid">\r\n\t\t<div class="col-xs-6" style="min-width:520px">\r\n\t\t\t<div class="box ">\r\n\t\t\t\t<!--<div class="box box-warning  ">-->\r\n\t\t\t\t\t<div class="box-header with-border">\r\n\t\t\t\t\t\t<h3 class="box-title"></h3>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<!-- /.box-header -->\r\n\t\t\t\t\t<div class="box-body ">\r\n\t\t\t\t\t\t<form id="submitForm" role="form">\r\n\r\n\t\t\t\t\t\t\t<!-- hidden input -->\r\n\t\t\t\t\t\t\t<input type="hidden" name="id" value="';
    $$out += $escape(data.datascope.id);
    $$out += '">\r\n\t\t\t\t\t\t\t<!-- text input -->\r\n\t\t\t\t\t\t\t<div class="form-group col-sm-6">\r\n\t\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;名称</label>\r\n\t\t\t\t\t\t\t\t<input type="text" name="name" value="';
    $$out += $escape(data.datascope.name);
    $$out += '" class="form-control" placeholder="请输入规则名称" datatype="*1-50" />\r\n\t\t\t\t\t\t\t\t<span class="help-block"></span>\r\n\t\t\t\t\t\t\t</div>\r\n\r\n\t\t\t\t\t\t\t<div class="form-group col-sm-6">\r\n\t\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;目标</label>\r\n\t\t\t\t\t\t\t\t<select id="targetType" name="targetType" class="form-control">\r\n\t\t\t\t\t\t\t\t\t';
    $each(data.targetType, function (item, i) {
        $$out += '\r\n\t\t\t\t\t\t\t\t\t';
        if (i == data.datascope.targetType) {
            $$out += '\r\n\t\t\t\t\t\t\t\t\t<option value="';
            $$out += $escape(i);
            $$out += '" selected>';
            $$out += $escape(item);
            $$out += '</option>\r\n\t\t\t\t\t\t\t\t\t';
        } else {
            $$out += '\r\n\t\t\t\t\t\t\t\t\t<option value="';
            $$out += $escape(i);
            $$out += '">';
            $$out += $escape(item);
            $$out += '</option>\r\n\t\t\t\t\t\t\t\t\t';
        }
        $$out += '\r\n\t\t\t\t\t\t\t\t\t';
    });
    $$out += '\r\n\t\t\t\t\t\t\t\t</select>\r\n\r\n\t\t\t\t\t\t\t\t<span class="help-block"></span>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class="form-group col-sm-6">\r\n\t\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;自定义目标</label>\r\n\t\t\t\t\t\t\t\t<input type="text" name="target"\r\n\t\t\t\t\t\t\t\t\t   class="form-control" ignore="ignore" placeholder="请输入目标字段"\r\n\t\t\t\t\t\t\t\t\t   value="';
    $$out += $escape(data.datascope.target);
    $$out += '" datatype="*1-50"><span class="help-block"></span>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class="form-group col-sm-6">\r\n\t\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;条件</label>\r\n\t\t\t\t\t\t\t\t<select id="conditionType" name="conditionType" class="form-control">\r\n\t\t\t\t\t\t\t\t\t';
    $each(data.conditionType, function (item, i) {
        $$out += '\r\n\t\t\t\t\t\t\t\t\t';
        if (i == data.datascope.conditionType) {
            $$out += '\r\n\t\t\t\t\t\t\t\t\t<option value="';
            $$out += $escape(i);
            $$out += '" selected>';
            $$out += $escape(item);
            $$out += '</option>\r\n\t\t\t\t\t\t\t\t\t';
        } else {
            $$out += '\r\n\t\t\t\t\t\t\t\t\t<option value="';
            $$out += $escape(i);
            $$out += '">';
            $$out += $escape(item);
            $$out += '</option>\r\n\t\t\t\t\t\t\t\t\t';
        }
        $$out += '\r\n\t\t\t\t\t\t\t\t\t';
    });
    $$out += '\r\n\t\t\t\t\t\t\t\t</select>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class="form-group col-sm-6">\r\n\t\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;范围</label>\r\n\t\t\t\t\t\t\t\t<select id="type" name="type" class="form-control">\r\n\t\t\t\t\t\t\t\t\t';
    $each(data.valueType, function (item, i) {
        $$out += '\r\n\t\t\t\t\t\t\t\t\t';
        if (i == data.datascope.type) {
            $$out += '\r\n\t\t\t\t\t\t\t\t\t<option value="';
            $$out += $escape(i);
            $$out += '" selected>';
            $$out += $escape(item);
            $$out += '</option>\r\n\t\t\t\t\t\t\t\t\t';
        } else {
            $$out += '\r\n\t\t\t\t\t\t\t\t\t<option value="';
            $$out += $escape(i);
            $$out += '">';
            $$out += $escape(item);
            $$out += '</option>\r\n\t\t\t\t\t\t\t\t\t';
        }
        $$out += '\r\n\t\t\t\t\t\t\t\t\t';
    });
    $$out += '\r\n\t\t\t\t\t\t\t\t</select>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class="form-group col-sm-6">\r\n\t\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;自定义范围</label>\r\n\t\t\t\t\t\t\t\t<textarea rows="5" cols="55" name="value" class="form-control"\r\n\t\t\t\t\t\t\t\t\t\t  ignore="ignore" datatype="*0-200">';
    $$out += $escape(data.datascope.value);
    $$out += '</textarea>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class="box-footer col-sm-12">\r\n\t\t\t\t\t\t\t\t<button e-event="href:/';
    $$out += $escape(path);
    $$out += '/submit?type=post&url=/system/core/datascope/save&contentType=application/x-www-form-urlencoded&goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-instagram" id="save_btn" type="button"  title="保存"\r\n\t\t\t\t\t\t\t\t\t\te-permission="core_dictionary_submit">\r\n\t\t\t\t\t\t\t\t\t<i class="fa fa-floppy-o"></i>&nbsp;保存\r\n\t\t\t\t\t\t\t\t</button>\r\n\t\t\t\t\t\t\t\t<button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn" type="button" title="返回">\r\n\t\t\t\t\t\t\t\t\t<i class="fa fa-undo"></i>&nbsp;返回\r\n\t\t\t\t\t\t\t\t</button>\r\n\t\t\t\t\t\t\t</div>\r\n\r\n\t\t\t\t\t\t</form>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<!-- /.box-body -->\r\n\t\t\t\t<!--</div>-->\r\n\r\n\t\t\t</div>\r\n\t\t\t<!-- /.box -->\r\n\t\t</div>\r\n\t\t<div style="width: 50%;float:right;min-height: 400px" class="con-bottom-right box box-solid">\r\n\t\t\t<div class="zTree-box" style="width: 100%">\r\n\t\t\t\t<div class="col-sm-12">\r\n\t\t\t\t\t<div class="box box-solid">\r\n\t\t\t\t\t\t<div class="authorize-title pull-left">\r\n\t\t\t\t\t\t\t数据范围\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</div>\r\n\t\t\t\t<div class="form-group col-sm-12"><span><p></p></span></div>\r\n\r\n\t\t\t\t<!--<div class="box box-solid">\r\n                    <div class="box box-solid">\r\n                        <div id="valueTree" class="ztree scroll-box" style="min-height:400px"></div>\r\n                    </div>\r\n                </div>-->\r\n\t\t\t\t<div class="form-group col-sm-12">\r\n\r\n\t\t\t\t\t<ul id="tree_id" class="ztree "></ul>\r\n\t\t\t\t</div>\r\n\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t\t<!-- /.col -->\r\n\t</div>\r\n\r\n\r\n\t<!-- Your Page Content Here -->\r\n\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ }),

/***/ 309:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n\r\n<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        数据编辑\r\n        <small>编辑数据信息</small>\r\n    </h1>\r\n\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n\r\n\r\n    <div class="row-fluid">\r\n        <div class="col-xs-6" style="min-width:520px">\r\n            <div class="box ">\r\n\r\n                <!-- <div class="box box-warning  ">-->\r\n                <div class="box-header with-border">\r\n                    <h3 class="box-title"></h3>\r\n                </div>\r\n                <!-- /.box-header -->\r\n                <div class="box-body ">\r\n                    <form id="submitForm" role="form">\r\n\r\n                        <!-- hidden input -->\r\n                        <input type="hidden" name="id" value="';
    $$out += $escape(data.datascope.id);
    $$out += '">\r\n                        <!-- text input -->\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;数据名称</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.datascope.name);
    $$out += '" class="form-control"\r\n                                   placeholder="请输入数据名称" datatype="/^[^ ]{1,10}$/"  errormsg="请填写1到10位不含空格的内容"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;目标</label>\r\n                            <select id="targetType" name="targetType" class="form-control">\r\n                                ';
    $each(data.targetType, function (item, i) {
        $$out += '\r\n                                ';
        if (i == data.datascope.targetType) {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '" selected>';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        } else {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '">';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        }
        $$out += '\r\n                                ';
    });
    $$out += '\r\n                            </select>\r\n\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;自定义目标</label>\r\n                            <input type="text" name="target"\r\n                                   class="form-control"  placeholder="请输入目标字段"\r\n                                   value="';
    $$out += $escape(data.datascope.target);
    $$out += '" datatype="/^[^ ]{1,50}$/"><span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;条件</label>\r\n                            <select id="conditionType" name="conditionType" class="form-control">\r\n                                ';
    $each(data.conditionType, function (item, i) {
        $$out += '\r\n                                ';
        if (i == data.datascope.conditionType) {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '" selected>';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        } else {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '">';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        }
        $$out += '\r\n                                ';
    });
    $$out += '\r\n                            </select>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;范围</label>\r\n                            <select id="type" name="type" class="form-control">\r\n                                ';
    $each(data.valueType, function (item, i) {
        $$out += '\r\n                                ';
        if (i == data.datascope.type) {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '" selected>';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        } else {
            $$out += '\r\n                                <option value="';
            $$out += $escape(i);
            $$out += '">';
            $$out += $escape(item);
            $$out += '</option>\r\n                                ';
        }
        $$out += '\r\n                                ';
    });
    $$out += '\r\n                            </select>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;自定义范围</label>\r\n                            <textarea rows="5" cols="55" name="value" class="form-control"\r\n                                      ignore="ignore" datatype="/^[^ ]{0,200}$/">';
    $$out += $escape(data.datascope.value);
    $$out += '</textarea>\r\n                        </div>\r\n                        <div class="box-footer col-sm-12">\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/checkSubmit?type=post&url=/system/core/datascope/save&contentType=application/x-www-form-urlencoded&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="save_btn" type="button" title="保存信息"\r\n                                    >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn"\r\n                                    type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n\r\n                    </form>\r\n                </div>\r\n                <!-- /.box-body -->\r\n                <!-- </div>-->\r\n\r\n            </div>\r\n            <!-- /.box -->\r\n        </div>\r\n        <div class="col-sm-6">\r\n            <div class="box con-bottom-right">\r\n                <div class="box-header">\r\n                    <div>\r\n                        选择数据范围\r\n                    </div>\r\n                </div>\r\n                <div class="box-body"  style="overflow-x: auto">\r\n                    <!--<div class="form-group col-sm-12"><span><p></p></span></div>-->\r\n                    <div class="form-group col-sm-12" style="min-width:400px;">\r\n                        <ul id="tree_id" class="ztree "></ul>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <!-- /.col -->\r\n    </div>\r\n\r\n\r\n    <!-- Your Page Content Here -->\r\n\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ }),

/***/ 310:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        数据管理\r\n        <small>数据管理</small>\r\n    </h1>\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <form id="searchForm">\r\n            <div class="col-xs-12" ><!--table 列表形式x滑动条 style="overflow-x: auto"-->\r\n                <div class="box" ><!--table 列表形式x滑动条 style="min-width:670px;"-->\r\n                    <div class="box-header">\r\n                        <h3 class="box-title"></h3>\r\n                    </div>\r\n                    <!-- /.box-header -->\r\n                    <div class="box-body">\r\n                        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                            <div class="row">\r\n                                <div class="col-sm-5" style="min-width:350px;">\r\n                                    <label>数据名称\uFF1A\r\n                                        <div class="input-group date">\r\n                                            <input type="text" id="name" name="search_LIKE_name" class="form-control"\r\n\r\n                                                   style="min-width:120px;">\r\n\r\n                                        </div>\r\n                                    </label>\r\n                                </div>\r\n                                <div class="col-sm-6">\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button id="add" type="button" class="btn btn-block btn-success"\r\n                                                 e-permission="core_datascope_create"\r\n                                                e-router="href:/';
    $$out += $escape(path);
    $$out += '/add?url=/system/core/datascope/get/new"><!--style="min-width:100px;"-->\r\n                                            <li class="fa fa-plus"></li>\r\n                                            &nbsp;新建\r\n                                        </button>\r\n                                    </div>\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button type="button" title="查询"\r\n                                                e-event="href:/';
    $$out += $escape(path);
    $$out += '/search?url=/system/core/datascope/list,auto:true"\r\n                                                class="btn btn-block btn-primary"><!--style="min-width:100px;"-->\r\n                                            <li class="fa fa-search-plus"></li>\r\n                                            &nbsp;查询\r\n                                        </button>\r\n                                    </div>\r\n                                </div>\r\n                            </div>\r\n                        </div>\r\n                        <div id="list" class="row">\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</section>';
    return $$out;
};

/***/ }),

/***/ 311:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, $escape = $imports.$escape, path = $data.path;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-6"></div>\r\n            <div class="col-sm-6"></div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-sm-12" style="max-height: 560px; overflow: auto">\r\n                <table id="example2" class="table table-bordered table-hover no-footer" style="min-width:300px; table-layout: fixed;"\r\n                       role="grid" aria-describedby="example2_info">\r\n                    <thead>\r\n                    <tr role="row">\r\n                        <!-- <th style="width:50px;"><div class="checkbox">  <input class="checked" type="checkbox" id="selectAll"> </div></th>-->\r\n                        <th  style="width:120px;"  sort="name">\r\n                            数据名称\r\n                        </th>\r\n                        <th  style="width:100px;"  sort="code">\r\n                            目标\r\n                        </th>\r\n                        <th  style="width:100px;"  sort="remark">\r\n                            条件\r\n                        </th>\r\n                        <th  style="width:100px;" class="">\r\n                            范围\r\n                        </th>\r\n                        <th  style="width:400px;" class="">\r\n                            操作\r\n                        </th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.content.length == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd" colspan="2">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n                        <td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;" title="';
            $$out += $escape(item.name);
            $$out += '">';
            $$out += $escape(item.name);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.target);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.conditionName);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.typeName);
            $$out += '</td>\r\n\r\n\r\n                        <td align="center-left">\r\n                            <button e-router="href:/';
            $$out += $escape(path);
            $$out += '/edit?url=/system/core/datascope/get/';
            $$out += $escape(item.id);
            $$out += '"\r\n                                    e-permission="core_datascope_edit"\r\n                                    type="button" class="btn btn-default"><i class="fa fa-pencil-square"></i> 编辑\r\n                            </button>\r\n                            <button e-event="href:/';
            $$out += $escape(path);
            $$out += '/dialog?dialog=confirm&type=get&url=/system/core/datascope/delete/';
            $$out += $escape(item.id);
            $$out += '"\r\n                                    type="button" e-permission="core_datascope_delete"\r\n                                    class="btn btn-default"><i class="fa fa-times"></i>删除\r\n                            </button>\r\n                            <!--<button e-router="href:/system/datascope/details?url=/system/core/datascope/get/';
            $$out += $escape(item.id);
            $$out += '"-->\r\n                                    <!--type="button" class="btn btn-default"><i class="fa fa-search"></i> 查看 &lt;!&ndash; e-permission="core_dictionary_details"&ndash;&gt;-->\r\n                            <!--</button>-->\r\n\r\n                        </td>\r\n\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n\r\n                <!-- <div class="btnDiv">\r\n                     <input tpl="core/dictionary/deletBatch" operation="showDialog" permission="core_dictionary_deleteids"\r\n                            dialogtype="deletBatch" class="bbtn" id="deleteMore" value="批量删除" type="button" />\r\n                 </div>-->\r\n                <!--   <button e-event="href:/system/dictionary/dialog?dialog=confirm&type=get&url=/system/core/dictionary/delete/"\r\n                           type="button" e-permission="core_dictionary_delete"\r\n                           class="btn btn-default"><i class="fa fa-times"></i>批量删除\r\n                   </button>-->\r\n\r\n            </div>\r\n        </div>\r\n        <div class="row" id="pager"></div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ })

});