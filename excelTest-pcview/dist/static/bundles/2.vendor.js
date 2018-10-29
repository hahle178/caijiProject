webpackJsonpvendor([2],{

/***/ 257:
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
        return __webpack_require__(355)("./" + tpl + ".html");
    },
    binddatescope: function binddatescope(tpl) {},
    //初始化
    initHandler: function initHandler(args) {
        this.options = {
            dataScope: {},
            currentRoleID: ""
        };
    },
    /*打开资源绑定对话框*/
    showDataScopeDialog: function showDataScopeDialog(args) {
        var _this = this;

        this.options.dataScope = {}; //每次打开清空原有
        /*创建dialog*/
        var layerId = layer.open({
            shade: 0.3,
            zIndex: 999999,
            type: 1,
            move: args.move,
            title: ['绑定数据', 'font-size:18px;'],
            area: [args.width || '40%', args.height || '65%'], //宽高
            btn: ["\u7ED1\u5B9A", "\u53D6\u6D88"],
            yes: function () {
                layer.closeAll();
                this.bindDataScops();
            }.bind(this), btn2: function btn2(index, layero) {
                //return false 开启该代码可禁止点击该按钮关闭
            }
        });
        //   /system/core/role/get/402899425e5139db015e55cb28c30006  /system/core/role/get/
        this.ajaxResource({ url: args.url, type: "get" }).then(function (result) {
            _this.options.currentRoleID = result.data.id; //当前选择的角色
            $("#roleName").html(args.name);
            //原有dataScope 初始化
            var dataScopeTemp = _this.options.dataScope;
            $.each(result.data.dataScopes, function (key, val) {
                dataScopeTemp[this["id"]] = "";
            });
            //console.log("打开"+this.options.currentRoleID+"窗口！原有数据为：");
            //console.log(Object.keys(this.options.dataScope));
        }.bind(this)); //请求后台资源
        /*加载dataScopeIndex.html-start*/
        args.contentId = $('#layui-layer' + layerId).find('.layui-layer-content');
        args.tpl = "dataScopeIndex";
        args.url = "";
        this.render(args).then(function () {
            //初始化分页控件
            _this.initjqPagination();
            /*执行查询 数据*/
            _this.dataScopSearch();
        });

        /*加载dataScopeIndex.html-end*/
        /*dialog 按钮点击*/
        /* $(".btn-sure").click(function () {
             layer.closeAll();
         });
         $(".btn-cancel").click(function () {
             layer.closeAll();
         });*/
    },
    /*查询数据*/
    dataScopSearch: function dataScopSearch() {
        var args = {};
        args.form = "searchForm_dataScope";
        args.contentId = "dataScopeList";
        args.url = "/system/core/datascope/list";
        args.tpl = "dataScopeList";
        var form = $("#" + args.form);
        if (form.length > 0) {
            args.data = form.serialize();
            args.tpl = args.tpl || config.listTpl;
            return this.render(args).then(function (data) {
                var maxpage = parseInt(data.data.totalPages);
                var curpage = parseInt(data.data.pageNum);
                $(".pagination2").jqPagination("option", {
                    max_page: maxpage,
                    current_page: curpage,
                    trigger: false
                });
                //勾选选择框
                this.initCheckState();
            }.bind(this));
        } else {
            throw new Error("参数无效，DOM中无法找到id为[" + args.form + "]的Form！");
        }
    },
    //初始化分页控件
    initjqPagination: function initjqPagination() {
        $('.pagination2').jqPagination({
            current_page: 1, //设置当前页 默认为1
            max_page: 1,
            page_string: '第{current_page}页,共{max_page}页',
            paged: function (page) {
                $("#page").val(page);
                this.dataScopSearch();
            }.bind(this)
        });
    },
    /*初始化选择框*/
    initCheckState: function initCheckState() {
        //当前页面列表
        var checkedAll = true;
        var dataScopeTemp = this.options.dataScope;
        $(".dataScope").each(function () {
            var id = $(this).attr("id");
            var checkedFlat = true;
            if (dataScopeTemp.hasOwnProperty(id)) {
                $(this).prop("checked", true);
            } else {
                checkedFlat = false;
                $(this).prop("checked", false);
            };
            checkedAll = checkedAll && checkedFlat;
        });
        checkedAll ? $("#dataScopeSelectAll").prop("checked", true) : $("#dataScopeSelectAll").prop("checked", false);
    },
    /*列表选择框点击*/
    checkDataScope: function checkDataScope(args) {
        if ($("#" + args.id).prop("checked")) {
            this.options.dataScope[args.id] = "";
        } else {
            if (this.options.dataScope.hasOwnProperty(args.id)) {
                delete this.options.dataScope[args.id];
            }
        }
        //全选框是否勾选
        if ($(".dataScope").length == $(".dataScope:checked").length) {
            $("#dataScopeSelectAll").prop("checked", true);
        } else {
            $("#dataScopeSelectAll").prop("checked", false);
        }
        //console.log("当前数据："+Object.keys(this.options.dataScope));
    },
    /*全选/全不选*/
    dataScopeSelectAll: function dataScopeSelectAll() {
        var dataScopeTemp = this.options.dataScope;
        if ($("#dataScopeSelectAll").prop("checked")) {
            $(".dataScope").each(function () {
                var id = $(this).attr("id");
                $(this).prop("checked", true);
                dataScopeTemp[id] = "";
            });
        } else {
            $(".dataScope").each(function () {
                var id = $(this).attr("id");
                $(this).prop("checked", false);
                if (dataScopeTemp.hasOwnProperty(id)) {
                    delete dataScopeTemp[id];
                }
            });
        }
    },
    /*绑定所选数据*/
    bindDataScops: function bindDataScops(args) {
        //url格式：system/core/datascope/binddatascopes/this.options.currentRoleID/{dataScopesID}
        var dataScopeStr = Object.keys(this.options.dataScope);
        if (dataScopeStr.length == 0) {
            dataScopeStr = 0;
        }
        //console.log("绑定时的数据ids:"+dataScopeStr);
        var url = "system/core/datascope/binddatascopes/" + this.options.currentRoleID + "/" + dataScopeStr;
        $.ajax({
            cache: false,
            type: "get",
            url: url,
            dataType: "json",
            success: function (data) {
                //console.log(`绑定完毕`);
            }.bind(this)
        });
    }
}));

/***/ }),

/***/ 355:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./add.html": 356,
	"./dataScopeIndex.html": 357,
	"./dataScopeList.html": 358,
	"./detail.html": 359,
	"./edit.html": 360,
	"./index.html": 361,
	"./list.html": 362
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
webpackContext.id = 355;

/***/ }),

/***/ 356:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        新建角色\r\n        <small>新建角色</small>\r\n    </h1>\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-xs-12" style="min-width:520px">\r\n            <div class="box ">\r\n                <div class="box-header with-border">\r\n                    <h3 class="box-title">新增</h3>\r\n                </div>\r\n                <!-- /.box-header -->\r\n                <div class="box-body">\r\n                    <form id="submitForm" role="form">\r\n                        <!-- hidden input -->\r\n                        <input type="hidden" name="versionNum" class="form-control">\r\n                        <!-- text input -->\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span\r\n                                    style="color: red;font-weight: bolder">*</span>&nbsp;角色名称</label>\r\n                            <input type="text" name="name" class="form-control"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"\r\n                                   placeholder="请输入角色名称" datatype="s1-10" errormsg="请填写1到10位中文\u3001英文\u3001数字和下划线\uFF01"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div style="margin:0 auto;" class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span\r\n                                    style="color: red;font-weight: bolder">*</span>&nbsp;角色编码</label>\r\n                            <input type="text" name="code" class="form-control" placeholder="请输入角色编码"\r\n                                   datatype="/^[0-9A-Za-z_]{1,20}$/"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"\r\n                                   errormsg="请填写1到20位英文\u3001数字和下划线\uFF01"\r\n                                   ajaxurl="system/core/role/checkRoleCode?id="/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div style="margin:0 auto;" class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;角色描述</label>\r\n                            <textarea rows="5" name="remark" class="form-control" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"\r\n                                      placeholder="请输入角色描述"\r\n                                      ignore="ignore" datatype="*0-200"></textarea>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="box-footer col-sm-12">\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/submit?type=post&url=/system/core/role/save&contentType=application/x-www-form-urlencoded&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="process_btn" type="button" title="保存信息"\r\n                                    >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn"\r\n                                    type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n                    </form>\r\n                </div>\r\n                <!-- /.box-body -->\r\n            </div>\r\n            <!--    </div>-->\r\n            <!-- /.box -->\r\n        </div>\r\n        <!-- /.col -->\r\n    </div>\r\n    <!-- Your Page Content Here -->\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ }),

/***/ 357:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '';
    $$out += '<!-- Main content -->\r\n<!--左侧的分页-->\r\n<link href="common/css/pageStyle.css" rel="stylesheet">\r\n<script type="text/javascript" src="plugins/jQuery/jquery.jqpagination.js"></script>\r\n<section class="content" style="padding-top: 0px;padding-bottom: 0px;">\r\n    <div class="row">\r\n        <form id="searchForm_dataScope">\r\n            <div class="col-xs-12" ><!--table 列表形式x滑动条 style="overflow-x: auto"-->\r\n                <div class="box" ><!--table 列表形式x滑动条 style="min-width:670px;"-->\r\n                    <!-- /.box-header -->\r\n                    <h5 class="">为角色<span class="color-blue"  id="roleName" style="color: #1E5E9C;" ></span>&nbsp;绑定数据</h5>\r\n                 <!--   <div class="box-header">\r\n\r\n                    </div>-->\r\n                    <div class="box-body">\r\n                        <div id="dataScopeList" class="row">\r\n                        </div>\r\n                        <div class="row">\r\n                            <div class="gigantic pagination2 col-xs-2">\r\n                                <a href="#" class="first" data-action="first">&laquo;</a>\r\n                                <a href="#" class="previous" data-action="previous">&lsaquo;</a>\r\n                                <input type="text" readonly="readonly" />\r\n                                <a href="#" class="next" data-action="next">&rsaquo;</a>\r\n                                <a href="#" class="last" data-action="last">&raquo;</a>\r\n                            </div>\r\n                            <!-- 左侧分页 -->\r\n                            <input type=\'hidden\' id=\'page\' name=\'pageNumber\' value=\'1\'>\r\n\r\n                        </div>\r\n                       <!-- <div class="form-group m-t-15 ">\r\n                            <div class="col-sm-offset-3 col-sm-9" >\r\n                                <div><p></p></div>\r\n                                <button type="button" style=" margin-left: 10px; width: 100px;" class="btn btn-cancel btn-primary  inline pull-right"><i class="fa fa-mail-reply"></i>&nbsp;取消</button>\r\n                                <button type="button" class="btn btn-sure btn-success  inline pull-right" e-event="href:/system/role/bindDataScops" style="width: 100px;  margin-left: 10px; background-color: #00a65a"><i class="fa fa-arrow-up"></i>&nbsp;绑定</button>\r\n                            </div>\r\n                        </div>-->\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</section>';
    return $$out;
};

/***/ }),

/***/ 358:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path, data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-12"style="overflow-x: auto">\r\n                <table id="example2" class="table table-bordered table-hover no-footer"\r\n                       role="grid" aria-describedby="example2_info">\r\n                    <thead>\r\n                        <tr role="row">\r\n                            <th style="width:50px;"><input class="icheckbox_minimal-blue checked" type="checkbox" id="dataScopeSelectAll" e-event="href:/';
    $$out += $escape(path);
    $$out += '/dataScopeSelectAll"></th>\r\n                            <th  style="width:200px;"  sort="name">\r\n                                规则名称\r\n                            </th>\r\n                           <!-- <th  style="width:100px;"  sort="code">\r\n                                目标\r\n                            </th>\r\n                            <th  style="width:100px;"  sort="remark">\r\n                                条件\r\n                            </th>\r\n                            <th  style="width:100px;" class="">\r\n                                范围\r\n                            </th>-->\r\n                        </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                        ';
    if (data.content.length == 0) {
        $$out += '\r\n                        <tr>\r\n                            <td class="lefttd" colspan="2">没有数据</td>\r\n                        </tr>\r\n                        ';
    } else {
        $$out += '\r\n                        ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                        <tr role="row" class="odd">\r\n                            <td>\r\n                                <div>\r\n                                    <input type="checkbox" id="';
            $$out += $escape(item.id);
            $$out += '" class="dataScope icheckbox_minimal-blue checked" e-event="href:/';
            $$out += $escape(path);
            $$out += '/checkDataScope?id=';
            $$out += $escape(item.id);
            $$out += '">\r\n                                </div>\r\n                            </td>\r\n                            <td>';
            $$out += $escape(item.name);
            $$out += '</td>\r\n                           <!-- <td>';
            $$out += $escape(item.target);
            $$out += '</td>\r\n                            <td>';
            $$out += $escape(item.conditionName);
            $$out += '</td>\r\n                            <td>';
            $$out += $escape(item.typeName);
            $$out += '</td>-->\r\n                        </tr>\r\n                        ';
        });
        $$out += '\r\n                        ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ }),

/***/ 359:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        角色浏览\r\n        <small>浏览角色信息</small>\r\n    </h1>\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-sm-12" style="min-width:520px">\r\n            <div class="box ">\r\n                <div class="box-header with-border">\r\n                    <h3 class="box-title">查看</h3>\r\n                </div>\r\n                <div class="box-body">\r\n                    <form id="submitForm" role="form">\r\n                        <!-- text input -->\r\n                        <div class="form-group">\r\n                            <div class="form-group col-sm-6">\r\n                                <label><i class="fa"></i>&nbsp;角色名称\uFF1A</label>\r\n                                <input type="text" name="name" value="';
    $$out += $escape(data.name);
    $$out += '" class="form-control" readonly="readonly"\r\n                                       placeholder="请输入角色名称"/>\r\n                                <span class="help-block"></span>\r\n                            </div>\r\n                            <div class="form-group col-sm-6">\r\n                                <label><i class="fa"></i>&nbsp;角色编码\uFF1A</label>\r\n                                <input type="text" name="code" value="';
    $$out += $escape(data.code);
    $$out += '" class="form-control" readonly="readonly"\r\n                                       placeholder="请输入角色编码"/>\r\n                                <span class="help-block"></span>\r\n                            </div>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;角色描述\uFF1A</label>\r\n                            <textarea rows="5" name="remark" readonly="readonly" class="form-control" id="remark">';
    $$out += $escape(data.remark);
    $$out += '</textarea>\r\n                            <!--<input type="text" name="remark" value="';
    $$out += $escape(data.remark);
    $$out += '" readonly="readonly" class="form-control"\r\n                                   placeholder="请输入角色描述"/>-->\r\n                            <span class="help-block"></span>\r\n                            <div class="valid-info">&nbsp;</div>\r\n                        </div>\r\n                        <div class="box-footer col-sm-12">\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" type="button" class="btn btn-primary ">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n                    </form>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</section>';
    return $$out;
};

/***/ }),

/***/ 360:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        角色编辑\r\n        <small>编辑角色信息</small>\r\n    </h1>\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n\r\n\r\n    <div class="row">\r\n        <div class="col-xs-12" style="min-width:520px">\r\n            <div class="box ">\r\n\r\n                <!--   <div class="box box-warning  ">-->\r\n                <div class="box-header with-border">\r\n                    <h3 class="box-title">编辑</h3>\r\n                </div>\r\n                <!-- /.box-header -->\r\n                <div class="box-body">\r\n                    <form id="submitForm" role="form">\r\n\r\n                        <!-- hidden input -->\r\n                        <input type="hidden" name="versionNum" value="';
    $$out += $escape(data.versionNum);
    $$out += '" class="form-control">\r\n                        <!-- text input -->\r\n                        <input type="hidden" name="id" class="form-control" value="';
    $$out += $escape(data.id);
    $$out += '" readonly="readonly"/>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span\r\n                                    style="color: red;font-weight: bolder">*</span>&nbsp;角色名称</label>\r\n                            <input type="text" name="name" class="form-control" value="';
    $$out += $escape(data.name);
    $$out += '" placeholder="请输入角色名称"\r\n                                   datatype="s1-10" errormsg="请填写1到10位中文\u3001英文\u3001数字和下划线\uFF01" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')" />\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div style="margin:0 auto;" class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span\r\n                                    style="color: red;font-weight: bolder">*</span>&nbsp;角色编码</label>\r\n                            <input type="text" name="code" class="form-control" value="';
    $$out += $escape(data.code);
    $$out += '" readonly="readonly"\r\n                                   placeholder="请输入角色编码" datatype="/^[0-9A-Za-z_]{1,20}$/"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"\r\n                                   errormsg="请填写1到20位英文\u3001数字和下划线\uFF01"  />\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div style="margin:0 auto;" class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;角色描述</label>\r\n                            <textarea rows="5" name="remark" class="form-control" ignore="ignore"\r\n                                      placeholder="请输入角色描述" datatype="*0-200"\r\n                                      onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')" >';
    $$out += $escape(data.remark);
    $$out += '</textarea>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="box-footer col-sm-12">\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/submit?type=post&url=/system/core/role/save&contentType=application/x-www-form-urlencoded&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="save_btn" type="button" title="保存信息"\r\n                                    >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn"\r\n                                    type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n\r\n                    </form>\r\n                </div>\r\n                <!-- /.box-body -->\r\n\r\n\r\n            </div>\r\n            <!-- /.box -->\r\n        </div>\r\n        <!-- /.col -->\r\n    </div>\r\n\r\n\r\n    <!-- Your Page Content Here -->\r\n\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ }),

/***/ 361:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        角色管理\r\n        <small>角色管理</small>\r\n    </h1>\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <form id="searchForm">\r\n            <div class="col-xs-12">\r\n                <div class="box">\r\n                    <div class="box-header">\r\n                        <h3 class="box-title"></h3>\r\n                    </div>\r\n                    <!-- /.box-header -->\r\n                    <div class="box-body">\r\n                        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                            <div class="row">\r\n                                <div class="col-sm-5" style="min-width:350px;">\r\n                                    <label>角色名称\uFF1A\r\n                                        <div class="input-group date">\r\n                                            <input type="text" name="search_LIKE_name" class="form-control"\r\n                                                   style="">\r\n                                        </div>\r\n                                    </label>\r\n                                </div>\r\n                                <div class="col-sm-6">\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button id="add" type="button" class="btn btn-block btn-success"\r\n                                                e-permission="core_role_create"\r\n                                                e-router="href:/';
    $$out += $escape(path);
    $$out += '/add"> <!--style="min-width:100px"-->\r\n                                            <li class="fa fa-plus"></li>\r\n                                            &nbsp;新建\r\n                                        </button>\r\n                                    </div>\r\n\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button type="button" title="查询"\r\n                                                e-event="href:/';
    $$out += $escape(path);
    $$out += '/search?url=/system/core/role/list,auto:true"\r\n                                                class="btn btn-block btn-primary"> <!--style="min-width:100px"-->\r\n                                            <li class="fa fa-search-plus"></li>\r\n                                            &nbsp;查询\r\n                                        </button>\r\n                                    </div>\r\n                                </div>\r\n                                <input type="hidden" name="sort" id="sort" value="">\r\n\r\n\r\n                            </div>\r\n                        </div>\r\n                        <div id="list" class="row">\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 362:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, $escape = $imports.$escape, path = $data.path;
    $$out += '<div class="col-sm-12"  >\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer" >\r\n        <div class="row">\r\n            <div class="col-sm-6"></div>\r\n            <div class="col-sm-6"></div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-sm-12" style="max-height: 560px; overflow: auto;"><!--style="overflow-x: auto"-->\r\n                <table id="example2" class="table table-bordered table-hover no-footer " style="min-width:300px;"\r\n                       role="grid" aria-describedby="example2_info"><!--style="min-width:240px;"-->\r\n                    <thead>\r\n                    <tr role="row">\r\n                        <!--<th style="width:50px;"><input type="checkbox" id="selectAll"></th>-->\r\n                        <th style="width: 200px;">\r\n                            角色名称\r\n                        </th>\r\n                        <th style="width: 150px;">\r\n                            角色编码\r\n                        </th>\r\n                        <th style="width: 270px;">\r\n                            角色描述\r\n                        </th>\r\n                        <th>\r\n                            操作\r\n                        </th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.totalSize == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd" colspan="2">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n                        <td>';
            $$out += $escape(item.name);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.code);
            $$out += '</td>\r\n                        ';
            if (item.remark.length > 20) {
                $$out += '\r\n                      <td  class="lefttd" style="white-space: nowrap" title="';
                $$out += $escape(item.remark);
                $$out += '">';
                $$out += $escape(item.remark.substring(0, 20) + '...');
                $$out += '</td>\r\n                        ';
            } else {
                $$out += '\r\n                        <td  class="lefttd" style="white-space: nowrap" title="';
                $$out += $escape(item.remark);
                $$out += '">';
                $$out += $escape(item.remark);
                $$out += '</td>\r\n                        ';
            }
            $$out += '\r\n                        <td align="center-left">\r\n                            <button e-router="href:/';
            $$out += $escape(path);
            $$out += '/edit?url=/system/core/role/get/';
            $$out += $escape(item.id);
            $$out += '"\r\n                                    e-permission="core_role_edit"\r\n                                    type="button" class="btn btn-default"><i class="fa fa-pencil-square"></i> 编辑\r\n                            </button>\r\n                            <button e-event="href:/';
            $$out += $escape(path);
            $$out += '/dialog?dialog=confirm&type=get&url=/system/core/role/delete/';
            $$out += $escape(item.id);
            $$out += '"\r\n                                    type="button" e-permission="core_role_delete"\r\n                                    class="btn btn-default"><i class="fa fa-times"></i>删除\r\n                            </button>\r\n                            <button e-router="href:/';
            $$out += $escape(path);
            $$out += '/details?url=/system/core/role/get/';
            $$out += $escape(item.id);
            $$out += '&tpl=detail"\r\n                                                                        type="button" class="btn btn-default"><i class="fa fa-search"></i> 查看\r\n                            </button>\r\n                            <button e-event="href:/';
            $$out += $escape(path);
            $$out += '/showDataScopeDialog?url=/system/core/role/get/';
            $$out += $escape(item.id);
            $$out += '&name=';
            $$out += $escape(item.name);
            $$out += '"\r\n                                    type="button" class="btn btn-default" e-permission="core_authorize_bindrolebizsys" title="绑定数据"><i class="fa fa-link"></i> 绑定数据\r\n                            </button>\r\n                        </td><!--btn-link-->\r\n\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n        </div>\r\n        <!-- <div class="btnDiv">\r\n             <input tpl="core/role/deleteByIds" operation="showDialog" permission="core/role/deleteByIds"\r\n                    dialogtype="deletBatch" class="btn btn-delete" id="deleteMore" value="批量删除"\r\n                    type="button"/>\r\n         </div>-->\r\n        <div class="row" id="pager">\r\n\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ })

});