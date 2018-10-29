webpackJsonpvendor([5],{

/***/ 262:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_layout_navbars_user_EAttach__ = __webpack_require__(164);
function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

/**
 * Created by dongyue on 2017/5/6.
 * 演示Handler应用
 */



var orgId = "root";

/* harmony default export */ __webpack_exports__["default"] = (new __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__["default"]({
    options1: function options1() {
        this.options = {
            treeId: 'orgTree',
            formId: 'form',
            listId: 'list',
            pageId: 'pager',
            findTree: "system/core/organization/list",
            getTree: "system/core/user/list",
            contentId: 'content',
            rescTreeid: 'rescTree',
            rescTreeUrl: 'core/resource/list',
            roleListId: 'rolelistbind',
            roleListUrl: 'core/role/list',
            map: {},
            selectedNode: {}, //selectedNode
            ifAdd: ''
        };
        return this;
    },

    resolveTpl: function resolveTpl(tpl) {
        return __webpack_require__(375)("./" + tpl + ".html");
    },
    initialize: function initialize(options) {
        this.setOptions(options);
        this.addEvent("inited", this.initTree);
    },
    onIndexed: function onIndexed() {
        /*20170926suntf*/
        var treeObj = $.fn.zTree.getZTreeObj("orgTree");
        if (treeObj != null && treeObj != undefined && treeObj != "undefined") {
            var node = treeObj.getSelectedNodes();
            //this.options.selectedNode=node[0].id;//20170926suntf
            console.log(node[0].id);
        }
        if (window.location.href.indexOf("?") == -1) {
            this.options.selectedNode.id = "root";
            this.initTree();
        } else {
            this.initTree();
        }
        if ($("#add").css('display') == 'none') {
            this.options.ifAdd = '1';
        } else {
            this.options.ifAdd = '0';
        }

        // let percentage = 10;
        // let interval = 1000;
        // let ajaxUrl = "system/batch/user/progressinfo";
        // setInterval(function () {
        //     /*
        //     percentage =  percentage+10;
        //     $("#progressbar").css("width",percentage+"%");
        //     if(percentage > 100){
        //         percentage = 0;
        //     }
        //     */
        //     $.ajax({
        //         cache: false,
        //         type: "POST",
        //         url: this.options.findTree,
        //         dataType: "json",
        //         success: function (data) {
        //             $.fn.zTree.init($('#' + this.options.treeId), this.ztreeEditSetting(), data.data);
        //             var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
        //             let nodeId="root";
        //             let orgCode=$("#orgCode").val();
        //             if(orgCode!=""){
        //                 nodeId=orgCode;
        //             }
        //             var node = zTree.getNodeByParam("id", nodeId);
        //             if(this.options.selectedNode){
        //                 this.options.selectedNode=node;
        //             }
        //             var selectNode = zTree.getNodeByParam("id", nodeId);
        //             zTree.selectNode(selectNode, false);
        //             zTree.expandNode(selectNode, true, false, false);
        //
        //         }.bind(this)
        //     });
        // },1000);
    },
    onEdited: function onEdited(args) {
        this.initEditTree();
    },
    /**
     * Index页面组织树初始化
     */
    initTree: function initTree() {
        //this.options1();
        $.ajax({
            cache: false,
            type: "POST",
            url: this.options.findTree,
            dataType: "json",
            success: function (data) {

                $.fn.zTree.init($('#' + this.options.treeId), this.ztreeSetting(), data.data);
                var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
                var nodeId = "root";
                if (this.options.selectedNode && this.options.selectedNode.id && this.options.selectedNode.id != "") {
                    nodeId = this.options.selectedNode.id;
                }
                var node = zTree.getNodeByParam("id", nodeId);
                zTree.selectNode(node, false);
                //zTree.expandAll(true);
                zTree.expandNode(node, true, false, false); //默认展开第一级
                this.ztreeClick(node);
            }.bind(this)
        });
    },

    /**
     * 组织机构树配置
     */
    ztreeSetting: function ztreeSetting() {
        //this.options1();
        return _defineProperty({
            check: {
                enable: false
            },
            view: {
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    orgId = treeNode.id;

                    treeNode.id == "root" ? $("#orgPath").val("") : $("#orgPath").val("#" + treeNode.id);
                    $("#orgName").val(treeNode.name);
                    var childTree = {};
                    childTree.url = "/system/core/user/list";
                    childTree.contentId = "list";
                    // this.search($('#' + this.options.contentId + ' [operation="search"]'));
                    this.search(childTree);
                    this.showOrgInfo(orgId);
                    $("#add").attr("e-router", "href:/system/user/add?orgId=" + treeNode.id + "&orgName=" + treeNode.name);
                    if (this.options.ifAdd == '0') {
                        if (treeNode.id == "root") {
                            $("#add").hide();
                        } else {
                            $("#add").show();
                        }
                    }
                }.bind(this)
            }
        }, "view", {
            fontCss: function fontCss(treeId, treeNode) {
                return !!treeNode.highlight ? { color: "blue", "font-weight": "bold" } : {
                    color: "#000",
                    "font-weight": "normal"
                };
            }
        });
    },
    ztreeClick: function ztreeClick(treeNode) {
        treeNode.id == "root" ? $("#orgPath").val("") : $("#orgPath").val("#" + treeNode.id);
        $("#orgName").val(treeNode.name);
        var childTree = {};
        childTree.url = "/system/core/user/list";
        childTree.contentId = "list";
        // this.search($('#' + this.options.contentId + ' [operation="search"]'));
        this.search(childTree);
        this.showOrgInfo(treeNode.id);
        $("#add").attr("e-router", "href:/system/user/add?orgId=" + treeNode.id + "&orgName=" + treeNode.name);
        if (this.options.ifAdd == '0') {
            if (treeNode.id == "root") {
                $("#add").hide();
            } else {
                $("#add").show();
            }
        }
    },
    /**
     * Edit页面点击ztree的操作
     * @returns {{check: {enable: boolean}, view: {selectedMulti: boolean}, data: {simpleData: {enable: boolean}}, callback: {onClick: *}, view: {selectedMulti: boolean}}}
     */
    ztreeEditSetting: function ztreeEditSetting() {
        //this.options1();
        return _defineProperty({
            check: {
                enable: false
            },
            view: {
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
                    $("[name = 'orgId']").val(treeNode.id);
                    $("#orgName").val(treeNode.name);
                    this.options.selectedNode = treeNode;
                    var selectNode = zTree.getNodeByParam("id", treeNode.id);
                    zTree.selectNode(selectNode, false);
                    zTree.expandNode(selectNode, true, false, false); //默认展开root
                }.bind(this)
            }
        }, "view", {
            fontCss: function fontCss(treeId, treeNode) {
                return !!treeNode.highlight ? { color: "blue", "font-weight": "bold" } : {
                    color: "#000",
                    "font-weight": "normal"
                };
            }
        });
    },

    /**
     * Edit页面组织树初始化
     */
    initEditTree: function initEditTree() {
        $.ajax({
            cache: false,
            type: "POST",
            url: this.options.findTree,
            dataType: "json",
            success: function (data) {
                $.fn.zTree.init($('#' + this.options.treeId), this.ztreeEditSetting(), data.data);
                var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
                var nodeId = "root";
                var orgCode = $("#orgCode").val();
                if (orgCode != "") {
                    nodeId = orgCode;
                }
                var node = zTree.getNodeByParam("id", nodeId);
                if (this.options.selectedNode) {
                    this.options.selectedNode = node;
                }
                var selectNode = zTree.getNodeByParam("id", nodeId);
                zTree.selectNode(selectNode, false);
                zTree.expandNode(selectNode, true, false, false);
            }.bind(this)
        });
    },

    /**
     * 新建用户
     * @author dongyue
     */
    onAdded: function onAdded(args) {
        $("#orgName").attr("readonly", true);
        $("#orgId").val(args.args.orgId);
        $("#orgCode").val(args.args.orgId);
        $("#orgName").val(args.args.orgName);
    },
    initHandler: function initHandler() {
        this.options1();
    },

    onSubmited: function onSubmited(args) {
        if ($('.Validform_wrong').length > 0) {
            return;
        }
        var orgId = $('#orgCode').val();
        window.location.href = '#/system/user/index?=' + orgId + '_' + Math.random();
        this.options.selectedNode.id = orgId;
    },
    onGoBacked: function onGoBacked() {
        var orgId = $('#orgCode').val();
        window.location.href = '#/system/user/index?=' + orgId + '_' + Math.random();
        this.options.selectedNode.id = orgId;
    },
    batchAdd: function batchAdd(args) {
        // 批量用户导入
        this.layerId = layer.open({
            type: 1,
            move: args.move,
            title: args.title || '批量导入',
            area: [args.width || '800px', args.height || '600px'] //宽高
        });

        args.contentId = $('#layui-layer' + this.layerId).find('.layui-layer-content');
        var that = this;
        this.render(args).then(function () {
            var atcArgs = {
                el: '#batchEtach',
                domopt: "append",
                suffix: "xls,xlsx",
                limit: '3',
                single: 'true',
                uploadUrl: 'system/batch/user/upload',
                uploadSuc: function uploadSuc(file) {
                    layer.closeAll();
                    that.checkProgress();
                }
            };
            var attach = new __WEBPACK_IMPORTED_MODULE_1__common_layout_navbars_user_EAttach__["default"]();
            attach.reWriteHtml(atcArgs);
        });
    },
    checkProgress: function checkProgress() {
        var that = this;
        var args = {
            url: "/system/batch/user/checkProgress",
            type: "POST"
        };
        var num = window.setInterval(function () {

            that.ajaxResource(args).then(function (data) {
                var flag = false;
                for (var i = 0; i < data.data.length; i++) {
                    var batchImport = data.data[i];
                    var id = batchImport.id;
                    if (batchImport.errorCount + batchImport.readCount != batchImport.recordTotalCount) {
                        flag = true;
                    } else {
                        var tArgs = {
                            url: "/system/batch/user/deleteUserBatchImport/" + id,
                            type: "GET"
                        };
                        that.ajaxResource(tArgs);
                    }
                }
                if (flag) {
                    $("#batch_info").show();
                } else {
                    $("#batch_info").hide();
                    layer.msg("处理完成");
                    clearInterval(num);
                    that.reload();
                }
            });
        }, 1000);
    },
    /**
     * 做标记处理，如标记为领导
     * @param args
     */
    doflag: function doflag(args) {
        var _this = this;

        args.data = { orgId: orgId };
        this.ajaxResource(args).then(function () {
            _this.reload();
        });
    },

    showOrgInfo: function showOrgInfo(orgId) {
        /*
        this.ajaxResource({
            url: `/system/core/user/getOrg/${orgId}`,
            type: "get"
        }).then((data) => {
            if (data.data && data.data.name) {
                $("#org-info-show").text(`当前组织领导为：` + data.data.name);
            }else{
                $("#org-info-show").text("");
            }
          });
        */
    },

    delete: function _delete(args) {

        var layerid = layer.confirm('你确定要执行此操作吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var _this2 = this;

            layer.close(layerid);
            promise = this.ajaxResource(args).then(function () {
                //刷新查询页面
                _this2.search({ url: '/system/core/user/list', auto: true });
            });
        }.bind(this), function () {
            layer.close(layerid);
        });
    }
}));

/***/ }),

/***/ 375:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./add.html": 376,
	"./batchAdd.html": 377,
	"./detail.html": 378,
	"./edit.html": 379,
	"./index.html": 380,
	"./list.html": 381
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
webpackContext.id = 375;

/***/ }),

/***/ 376:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        新建用户\r\n        <small>新建用户</small>\r\n    </h1>\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-xs-12" style="min-width:520px">\r\n            <div class="box ">\r\n                <div class="box-header with-border">\r\n                    <h3 class="box-title">新增</h3>\r\n                </div>\r\n                <!-- /.box-header -->\r\n                <div class="box-body">\r\n                    <form id="submitForm" role="form">\r\n                        <input type="hidden" name="id" value="">\r\n                        <input type="hidden" name="code" value="">\r\n                        <!-- text input -->\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span\r\n                                    style="color: red;font-weight: bolder">*</span>&nbsp;登录账户</label>\r\n                            <input type="text" name="account" class="form-control" placeholder="请输入账户名称"\r\n                                   ajaxurl="system/core/user/checkuser" datatype="/^[0-9A-Za-z_]{1,15}$/"\r\n                                   errormsg="请填写1到15位英文\u3001数字和下划线\uFF01"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span\r\n                                    style="color: red;font-weight: bolder">*</span>&nbsp;用户名称</label>\r\n                            <input type="text" name="name" class="form-control" placeholder="请输入用户名称"\r\n                                   datatype="s1-10" errormsg="请填写1到10位中文\u3001英文\u3001数字和下划线\uFF01" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;所属机构</label>\r\n                            <input type="hidden" class="form-control" id="orgId" name="orgId" placeholder=" "\r\n                                   value="">\r\n                            <input type="hidden" class="form-control" id="orgCode"  value="">\r\n                            <input type="text" id="orgName" name="orgName" class="form-control"\r\n                                   placeholder="请输入机构名称"\r\n                                   value="" />\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;办公电话</label>\r\n                            <input type="text" name="phoneNo" class="form-control" ignore="ignore" placeholder="请输入办公电话"\r\n                                   datatype="phone" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i> &nbsp;手机号码</label>\r\n                            <input type="text" name="mobilePhone" class="form-control" ignore="ignore"\r\n                                   placeholder="请输入手机号码" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"\r\n                                   datatype="/^1[3|4|5|6|7|8|9]{1}[0-9]{9}$/" errormsg="请填写正确的11位手机号码\uFF01"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;联系地址</label>\r\n                            <input type="text" name="address" class="form-control" ignore="ignore" placeholder="请输入联系地址"\r\n                                   datatype="/^[a-zA-Z0-9-\\\u2014_\\u4e00-\\u9fa5]{1,30}$/" errormsg="请填写0到30位中文\u3001英文\u3001数字\u3001中划线和下划线\uFF01"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i> &nbsp;电子邮箱</label>\r\n                            <input type="text" name="email" class="form-control" ignore="ignore" placeholder="请输入电子邮箱"\r\n                                   datatype="e,*0-50" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;用户状态</label>\r\n                            <e-select class="form-control" name="status" e-option-value="code" e-type="get"\r\n                                      e-option-text="name" datatype="*" e-value="1"\r\n                                      e-url="/system/core/dictionary/getSonDicsByCode?code=bizstatus">\r\n                                <option value="">请选择</option>\r\n                            </e-select>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;备注信息</label>\r\n                            <textarea rows="5" name="remark" class="form-control" ignore="ignore"\r\n                                      placeholder="请输入备注信息" datatype="*0-200"\r\n                                      onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"></textarea>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="box-footer col-sm-12">\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/submit?type=post&url=/system/core/user/save&contentType=application/x-www-form-urlencoded&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="process_btn" type="button" title="保存信息"\r\n                                    >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary "\r\n                                    id="back_btn" type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n                    </form>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 377:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '';
    $$out += '<div class="container-fluid">\r\n    <div class="row">\r\n        <div class="col-xs-12">\r\n            <form id="infoForm" role="form" class="form-horizontal">\r\n                <div id="batchEtach" style="margin-left:10px;"></div>\r\n            </form>\r\n            <a href="/导入模板.xlsx" >下载模版</a>\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ }),

/***/ 378:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        用户浏览\r\n        <small>浏览用户信息</small>\r\n    </h1>\r\n\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n\r\n\r\n    <div class="row">\r\n        <div class="col-sm-12" style="min-width:520px">\r\n            <div class="box ">\r\n\r\n                <div class="box-header with-border">\r\n                    <h3 class="box-title">查看</h3>\r\n                </div>\r\n                <!-- /.box-header -->\r\n                <div class="box-body">\r\n                    <form id="submitForm" role="form">\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;登录账户</label>\r\n                            <input type="text" value="';
    $$out += $escape(data.user.account);
    $$out += '" class="form-control"\r\n                                   readonly disabled/>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;用户名称</label>\r\n                            <input type="text" value="';
    $$out += $escape(data.user.name);
    $$out += '" class="form-control" readonly\r\n                                   disabled/>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;启用状态</label>\r\n                            ';
    if (data.user.status == '1') {
        $$out += '\r\n                            <input type="text" class="form-control" value="启用" disabled readonly>\r\n                            ';
    } else if (data.user.status == '0') {
        $$out += '\r\n                            <input type="text" class="form-control" value="停用" disabled readonly>\r\n                            ';
    }
    $$out += '\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <input type="hidden" id="orgCode" value="';
    $$out += $escape(data.user.orgId);
    $$out += '"/>\r\n                            <label><i class="fa"></i>&nbsp;所属机构</label>\r\n                            <input type="text" name="code" value="';
    $$out += $escape(data.user.orgName);
    $$out += '" class="form-control"\r\n                                   readonly disabled/>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;办公电话</label>\r\n                            <input type="text" name="code" value="';
    $$out += $escape(data.user.phoneNo);
    $$out += '" class="form-control"\r\n                                   readonly disabled/>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;手机号码</label>\r\n                            <input type="text" name="code" value="';
    $$out += $escape(data.user.mobilePhone);
    $$out += '" class="form-control"\r\n                                   readonly disabled/>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;联系地址</label>\r\n                            <input type="text" name="code" value="';
    $$out += $escape(data.user.address);
    $$out += '" class="form-control"\r\n                                   readonly disabled/>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;电子邮箱</label>\r\n                            <input type="text" name="code" value="';
    $$out += $escape(data.user.email);
    $$out += '" class="form-control"\r\n                                   readonly disabled/>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;备注信息</label>\r\n                            <textarea rows="5" name="code" class="form-control"\r\n                                      readonly disabled>';
    $$out += $escape(data.user.remark);
    $$out += '</textarea>\r\n                        </div>\r\n                        <div class="box-footer col-sm-12">\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary "\r\n                                    id="back_btn" type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n                    </form>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</section>\r\n\r\n';
    return $$out;
};

/***/ }),

/***/ 379:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n<section class="content-header">\r\n    <h1>\r\n        用户编辑\r\n        <small>编辑用户信息</small>\r\n    </h1>\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <!--<div class="col-xs-2">\r\n            <div class="box" style="overflow-x: auto;min-height: 585px">\r\n                <div class="con-bottom-left zTree-box">\r\n                    <ul id="orgTree" class="ztree"></ul>\r\n                </div>\r\n            </div>\r\n        </div>-->\r\n        <div class="col-xs-2">\r\n            <div class="box">\r\n                <div class="con-bottom-left zTree-box">\r\n                    <ul id="orgTree" class="ztree" style="overflow: auto; max-height: 570px; height:800px;"></ul>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class="col-xs-10" style="min-width:520px">\r\n            <div class="box">\r\n                <div class="box-header with-border">\r\n                    <h3 class="box-title">编辑</h3>\r\n                </div>\r\n                <!-- /.box-header -->\r\n                <div class="box-body" style="max-height: 620px; overflow: auto;">\r\n                    <form id="submitForm" role="form">\r\n                        <input name="id" value="';
    $$out += $escape(data.user.id);
    $$out += '" hidden>\r\n                        <!-- text input -->\r\n                        <div class="form-group">\r\n                            <div class="form-group col-sm-6">\r\n                                <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;登陆账户</label>\r\n                                <input type="text" name="account" placeholder="请输入登录账户" value="';
    $$out += $escape(data.user.account);
    $$out += '"\r\n                                       class="form-control"\r\n                                       readonly datatype="/^[0-9A-Za-z_]{1,15}$/" errormsg="请填写1到10位英文\u3001数字和下划线\uFF01"\r\n                                       onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"/>\r\n                                <span class="help-block"></span>\r\n                            </div>\r\n                            <div class="form-group col-sm-6">\r\n                                <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;用户名称</label>\r\n                                <input type="text" name="name" placeholder="请输入用户名称" value="';
    $$out += $escape(data.user.name);
    $$out += '"\r\n                                       class="form-control"\r\n                                       datatype="s1-10" errormsg="请填写1到10位中文\u3001英文\u3001数字和下划线\uFF01" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"/>\r\n                                <span class="help-block"></span>\r\n                            </div>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i><span\r\n                                    style="color: red;font-weight: bolder">*</span>&nbsp;用户状态</label>\r\n                            <e-select class="form-control" name="status" e-option-value="code"\r\n                                      e-value=\'';
    $$out += $escape(data.user.status);
    $$out += '\'\r\n                                      e-option-text="name" datatype="*"\r\n                                      e-url="/system/core/dictionary/getSonDicsByCode?code=bizstatus">\r\n                            </e-select>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <input type="text" id="orgCode" value="';
    $$out += $escape(data.user.orgId);
    $$out += '" name="orgId" hidden>\r\n                            <label><i class="fa"></i>&nbsp;所属机构</label>\r\n                            <input type="text" id="orgName" name="orgName" value="';
    $$out += $escape(data.user.orgName);
    $$out += '"\r\n                                   class="form-control" disabled/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;办公电话</label>\r\n                            <input type="text" name="phoneNo" placeholder="请输入办公电话" value="';
    $$out += $escape(data.user.phoneNo);
    $$out += '"\r\n                                   class="form-control"\r\n                                   ignore="ignore" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"\r\n                                   datatype="phone"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;手机号码</label>\r\n                            <input type="text" name="mobilePhone" placeholder="请输入手机号码"\r\n                                   value="';
    $$out += $escape(data.user.mobilePhone);
    $$out += '" datatype="/^1[3|4|5|6|7|8|9]{1}[0-9]{9}$/"\r\n                                   errormsg="请填写正确的11位手机号码\uFF01" ignore="ignore"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"\r\n                                   class="form-control"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;联系地址</label>\r\n                            <input type="text" name="address" placeholder="请输入联系地址" value="';
    $$out += $escape(data.user.address);
    $$out += '"\r\n                                   class="form-control" ignore="ignore"\r\n                                   datatype="/^[a-zA-Z0-9-\\\u2014_\\u4e00-\\u9fa5]{1,30}$/"\r\n                                   errormsg="请填写0到30位中文\u3001英文\u3001数字\u3001中划线和下划线\uFF01"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;电子邮箱</label>\r\n                            <input type="text" name="email" placeholder="请输入电子邮箱" value="';
    $$out += $escape(data.user.email);
    $$out += '"\r\n                                   class="form-control" ignore="ignore"\r\n                                   datatype="e,*0-50" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;备注信息</label>\r\n                            <textarea rows="6" placeholder="请输入备注信息" name="remark" class="form-control"\r\n                                      ignore="ignore" datatype="*0-200"\r\n                                      onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')">';
    $$out += $escape(data.user.remark);
    $$out += '</textarea>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="box-footer col-sm-12">\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/submit?type=post&url=/system/core/user/save&contentType=application/x-www-form-urlencoded&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="process_btn" type="button" title="保存信息"\r\n                            >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary "\r\n                                    id="back_btn" type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n                    </form>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 380:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n<div id="batch_info" class="callout callout-warning" style="margin-bottom:0;display:none;">\r\n    <p>正在批量导入中...</p>\r\n</div>\r\n<section class="content-header" id="content-header">\r\n    <h1>用户管理\r\n        <small>用户管理</small>\r\n    </h1>\r\n\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <form id="searchForm">\r\n            <div class="col-sm-2">\r\n                <!--<div class="box" style="min-height: 644px;">\r\n                    <div class="con-bottom-left zTree-box" style="margin-top: 10px;margin-left: 3px ">\r\n                        <div class="" >\r\n                        <ul id="orgTree" class="ztree" style="overflow-x: auto;min-height: 620px"></ul>\r\n                        </div>\r\n                    </div>\r\n                    &lt;!&ndash;<div id="list" class="con-bottom-right list"></div>&ndash;&gt;\r\n                </div>-->\r\n                <div class="box">\r\n                    <div class="con-bottom-left zTree-box" style="margin-top: 10px; margin-left: 3px ">\r\n                        <div class="" >\r\n                            <ul id="orgTree" class="ztree" style="max-height: 570px; overflow: auto;height:1000px;"></ul>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class="col-sm-10"><!--table 列表形式x滑动条  style="overflow-x: auto"-->\r\n                <div class="box" ><!--table 列表形式x滑动条  style="min-width:240px;"-->\r\n                    <div class="box-header">\r\n                        <h3 class="box-title"></h3>\r\n                    </div>\r\n                    <!-- /.box-header -->\r\n                    <div class="box-body">\r\n                        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                            <div class="row">\r\n                                <!--<div class="col-sm-2" style=" min-width:330px;">\r\n                                    <label>登录账户\uFF1A\r\n                                        <div class="input-group date">\r\n                                            <input type="text" name="search_LIKE_account" class="form-control"\r\n                                                   style="min-width:120px;">\r\n                                        </div>\r\n                                    </label>\r\n                                </div>-->\r\n\r\n                                <div class="col-sm-5" style=" min-width:330px;">\r\n                                    <label>用户名称\uFF1A\r\n                                        <div class="input-group date">\r\n                                            <input type="text" name="search_LIKE_name" class="form-control"\r\n                                                   style="min-width:120px;">\r\n                                        </div>\r\n                                    </label>\r\n                                </div>\r\n\r\n                                <!--<div class="col-sm-1" style=" min-width:242px;">\r\n                                    <label>用户状态\uFF1A\r\n                                        <div class="input-group date">\r\n                                            <select name="search_EQ_status" class="form-control">\r\n                                                <option value="">\u2014请选择\u2014</option>\r\n                                                <option value="1">启用</option>\r\n                                                <option value="0">停用</option>\r\n                                            </select>\r\n                                        </div>\r\n                                    </label>\r\n                                </div>-->\r\n\r\n                                <input type="hidden" id="orgPath"\r\n                                       name="search_LIKE_organization.orgPath" value="">\r\n                                <div class="col-sm-6">\r\n\r\n                                    <!-- <div class="Tables_length">-->\r\n\r\n                                    <!-- <div class="Tables_length">-->\r\n                                    <!--\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button type="button" title="批量导入"\r\n                                                e-event="href:/';
    $$out += $escape(path);
    $$out += '/batchAdd?tpl=batchAdd&title=批量导入&width=600px&height=250px"\r\n                                                class="btn btn-block btn-success" style="min-width:100px;">\r\n                                            <li class="fa fa-plus"></li>\r\n                                            &nbsp;批量导入\r\n                                        </button>\r\n                                    </div>\r\n                                    -->\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button id="add" type="button" class="btn btn-block btn-success"\r\n                                                e-permission="core_user_create"\r\n                                                e-router="href:/';
    $$out += $escape(path);
    $$out += '/add"><!--style="min-width:100px;"-->\r\n                                            <li class="fa fa-plus"></li>\r\n                                            &nbsp;新建\r\n                                        </button>\r\n                                    </div>\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button type="button" title="查询"\r\n                                                e-event="href:/';
    $$out += $escape(path);
    $$out += '/search?url=/system/core/user/list,auto:false"\r\n                                                class="btn btn-block btn-primary"> <!--style="min-width:100px;"-->\r\n                                            <li class="fa fa-search-plus"></li>\r\n                                            &nbsp;查询\r\n                                        </button>\r\n                                    </div>\r\n                                </div>\r\n                                <input type="hidden" name="sort" id="sort" value="">\r\n\r\n                            </div>\r\n                        </div>\r\n                        <div id="list" class="row">\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 381:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, $escape = $imports.$escape, path = $data.path;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-6"></div>\r\n            <div class="col-sm-6"></div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-sm-12" style="max-height: 560px; overflow: auto;">\r\n                <table id="example2" class="table table-bordered table-hover no-footer"  style="min-width:240px; table-layout: fixed;"\r\n                       role="grid" aria-describedby="example2_info">\r\n                    <thead>\r\n                    <tr>\r\n                        <!--<th style="width:50px;"><input type="checkbox" id="selectAll"></th>-->\r\n                        <th style="width:140px;" class="leftth ">登录账户</th>\r\n                        <th style="width:140px;" class="leftth">用户名称</th>\r\n                        <th style="width:100px;">用户状态</th>\r\n                        <th style="width:120px;">所属机构</th>\r\n                        <th style="width:350px;">操作</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.totalSize == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd" colspan="2">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n\r\n                        <!--<td><input type="checkbox" sign="checkbox" value="';
            $$out += $escape(item.id);
            $$out += '"></td>-->\r\n                        <!--应用名称-->\r\n                        <td class="lefttd" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;" title="';
            $$out += $escape(item.account);
            $$out += '">';
            $$out += $escape(item.account);
            $$out += '</td>\r\n                        <!--应用编码-->\r\n                        <td class="lefttd" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;" title="';
            $$out += $escape(item.name);
            $$out += '">';
            $$out += $escape(item.name);
            $$out += '</td>\r\n                        <!--启用状态-->\r\n                        <td>\r\n                            ';
            if (item.status == '1') {
                $$out += '\r\n                            启用\r\n                            ';
            } else {
                $$out += '\r\n                            <font color="red">停用</font>\r\n                            ';
            }
            $$out += '\r\n                        </td>\r\n                        <!--注册日期-->\r\n                        <td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;" title="';
            $$out += $escape(item.orgName);
            $$out += '">';
            $$out += $escape(item.orgName);
            $$out += '</td>\r\n                        <!--操作-->\r\n                        <td align="center-left">\r\n                            <button e-router="href:/';
            $$out += $escape(path);
            $$out += '/edit?url=/system/core/user/get/';
            $$out += $escape(item.id);
            $$out += '"\r\n                                    e-permission="core_user_edit"\r\n                                    type="button" class="btn btn-default"><i class="fa fa-pencil-square"></i> 编辑\r\n                            </button>\r\n                            <button e-event="href:/';
            $$out += $escape(path);
            $$out += '/delete?dialog=confirm&type=get&url=/system/core/user/delete/';
            $$out += $escape(item.id);
            $$out += '&isReload=false"\r\n                                    type="button" e-permission="core_user_delete"\r\n                                    class="btn btn-default"><i class="fa fa-times"></i>删除\r\n                            </button>\r\n                            <button e-router="href:/';
            $$out += $escape(path);
            $$out += '/details?url=/system/core/user/get/';
            $$out += $escape(item.id);
            $$out += '&tpl=detail"\r\n\r\n                                    type="button" class="btn btn-default"><i class="fa fa-search"></i> 查看\r\n                            </button>\r\n                            <button e-event="href:/';
            $$out += $escape(path);
            $$out += '/dialog?url=/system/core/user/resetpwd/';
            $$out += $escape(item.id);
            $$out += '&type=get&dialog=confirm"\r\n                                    e-permission="core_user_resetpwd"\r\n                                    type="button" class="btn btn-default"><i class="fa fa-key"></i> 重置密码\r\n                            </button>\r\n                        </td>\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n        </div>\r\n        <div class="row" id="pager">\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ })

});