webpackJsonpvendor([0],{

/***/ 263:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_Config__ = __webpack_require__(2);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ezdev_pcview_components_table_Table__ = __webpack_require__(25);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ezdev_pcview_utils_EvenUtils__ = __webpack_require__(13);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_ezdev_pcview_utils_HtmlUtils__ = __webpack_require__(4);






/* harmony default export */ __webpack_exports__["default"] = (new __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__["default"]({

    resolveTpl: function resolveTpl(tpl) {
        return __webpack_require__(382)("./" + tpl + ".html");
    },
    onIndexed: function onIndexed() {
        this.render({
            type: 'get',
            url: '/system/core/useradmin/tabs',
            tpl: 'tabs',
            contentId: 'systemuseradmin_tabs-div'
        });
    },
    toTab: function toTab(args) {
        if ($("#" + args.contentId).find(".table").length < 1) {
            this.render(args).then(function (data) {}.bind(this));
        }
    },
    search: function search(args) {
        args.form = args.form || 'searchForm';
        if (args.url) {
            args.contentId = args.contentId || __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_Config__["default"].listId;

            var form = $("#" + args.form);
            if (form.length > 0) {
                args.data = form.serialize();
                args.tpl = args.tpl || __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_Config__["default"].listTpl;

                return this.render(args).then(function (data) {
                    new __WEBPACK_IMPORTED_MODULE_2_ezdev_pcview_components_table_Table__["default"]({
                        pageID: args.contentId + "-pager",
                        sortID: args.contentId + "-sort",
                        pageNum: data.data.pageNum,
                        totalPages: data.data.totalPages,
                        totalSize: data.data.totalSize,
                        pageSize: data.data.pageSize,
                        buttonClickCallback: this.search.bind(this, args)
                    });
                }.bind(this));
            } else {
                throw new Error("参数无效，DOM中无法找到id为[" + args.form + "]的Form！");
            }
        } else {
            throw new Error("参数无效，请传递如{url:/demo/mybatisuser/pages-(必选),type:post-(默认),tpl:list-(默认),contentId:list-(默认)}的JS对象");
        }
    },

    onAdded: function onAdded() {
        var tempThis = this;
        this.initTree({
            treeId: 'systemuseradmin_treeContent',
            url: '/system/core/useradmin/orgUserTree',
            check: {
                enable: false
            },
            treeClick: tempThis.treeClick
        });
    },
    treeClick: function treeClick(event, treeId, treeNode) {
        if (treeNode.type == "user") {
            if ($("#user_" + treeNode.id).length < 1) {
                var arr = ['<li>', '<a href="javascript:;"><i class="fa fa-user"></i>', treeNode.name, '(', treeNode.code, ')', '', '<input type="hidden" id="user_', treeNode.id, '" name="userIds" value="', treeNode.id, '" />', '<div class="tools">X</div>', '</a>', '</li>'];
                $("#systemuseradmin_selectedUserUl").append(arr.join(''));
                $("#systemuseradmin_selectedUserCount").text($("#systemuseradmin_selectedUserUl li").length);
                $("#systemuseradmin_selectedUserUl li .tools").unbind("click");
                $("#systemuseradmin_selectedUserUl li .tools").bind("click", function () {
                    $(this).parent().parent().remove();
                    $("#systemuseradmin_selectedUserCount").text($("#systemuseradmin_selectedUserUl li").length);
                });
            }
        }
    },
    initTree: function initTree(param) {
        var args = {
            url: param.url,
            type: 'get'
        };
        var setting = {
            check: param.check,
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: param.treeClick
            },
            view: {
                fontCss: function fontCss(treeId, treeNode) {
                    return !!treeNode.highlight ? { color: "blue", "font-weight": "bold" } : {
                        color: "#000",
                        "font-weight": "normal"
                    };
                }
            }
        };
        this.ajaxResource(args).then(function (data) {
            if (data.data && data.data.length > 0) {
                var zTree = $.fn.zTree.init($("#" + param.treeId), setting, data.data);
                var root = zTree.getNodesByFilter(function (node) {
                    return node.level == 0;
                }, true);
                zTree.expandNode(root, true, false, false); //默认展开第一级
                if (param.checked) {
                    var chkNodes = zTree.getCheckedNodes(true);
                    $.each(chkNodes, function (key, item) {
                        zTree.expandNode(item.getParentNode(), true, false, false);
                    });
                }
            }
        });
    },
    searchZtree: function searchZtree(element) {
        var treeId = "treeContent";
        var searchConditionId = "txtSearch";
        var searchCondition = $('#' + searchConditionId).val();
        var searchObj = {};
        searchObj.treeId = treeId;
        searchObj.condition = searchCondition;
        searchObj.type = "user";
        var nodeObj = this.treeSearchAndExpand(searchObj);
    },
    treeSearchAndExpand: function treeSearchAndExpand(searchObj) {
        var treeObj = $.fn.zTree.getZTreeObj(searchObj.treeId);
        // <1>. 先把全部节点更新为普通样式
        var treeNodes = treeObj.transformToArray(treeObj.getNodes());
        for (var i = 0; i < treeNodes.length; i++) {
            treeNodes[i].highlight = false;
            treeObj.updateNode(treeNodes[i]);
        }
        // <2>.得到模糊匹配搜索条件的节点数组集合
        var highlightNodes = new Array();

        if (searchObj.condition != "") {
            highlightNodes = treeObj.getNodesByParamFuzzy("name", searchObj.condition, null);
        } else {
            return;
        }
        // <3>.高亮显示并展示【指定节点s】
        var nodeObj = {};
        if (highlightNodes != null && highlightNodes.length > 0) {
            treeObj.cancelSelectedNode();
            var first = true;
            for (var i = 0; i < highlightNodes.length; i++) {
                if (searchObj.type && searchObj.type.indexOf(highlightNodes[i].type) < 0) {
                    continue;
                }
                if (first) {
                    nodeObj = highlightNodes[i];
                    treeObj.selectNode(highlightNodes[i], true);
                    first = false;
                }
                highlightNodes[i].highlight = true;
                treeObj.updateNode(highlightNodes[i]); // 级联选中
                treeObj.expandNode(highlightNodes[i].getParentNode(), true);
            }
        }
        return nodeObj;
    },
    submitForm: function submitForm(args) {
        if ($("#systemuseradmin_selectedUserUl li :hidden[name='userIds']").length > 0) {
            this.submit(args);
        } else {
            layer.alert("请先选择人员！", {
                icon: 2
            });
        }
    },
    adminSet: function adminSet(args) {
        args.type = args.type || 'get';
        return this.render(args);
    },
    onAdminSeted: function onAdminSeted() {
        var tempThis = this;
        var id = $("#systemuseradmin_adminId").val();
        this.initTree({
            treeId: 'systemuseradmin_organizationTree',
            url: '/system/core/useradmin/adminOrgTree/' + id,
            check: {
                enable: true,
                chkboxType: { "Y": "ps", "N": "ps" }
            },
            checked: true
        });
        this.initTree({
            treeId: 'systemuseradmin_resourceTree',
            url: '/system/core/useradmin/adminResourceTree/' + id,
            check: {
                enable: true,
                chkboxType: { "Y": "ps", "N": "ps" }
            },
            checked: true
        });
    },
    setScope: function setScope(args) {
        var organizationTree = $.fn.zTree.getZTreeObj("systemuseradmin_organizationTree");
        var resourceTree = $.fn.zTree.getZTreeObj("systemuseradmin_resourceTree");
        var orgnodes = organizationTree.getCheckedNodes(true);
        var resnodes = resourceTree.getCheckedNodes(true);
        if (orgnodes.length == 0 && resnodes.length == 0) {
            layer.alert("请至少选择一条数据！", {
                icon: 2
            });
            return;
        }
        var orgIds = [],
            resIds = [];
        $.each(orgnodes, function (key, item) {
            orgIds.push(item.id);
        });
        $.each(resnodes, function (key, item) {
            resIds.push(item.id);
        });

        args.data = { "orgIds": orgIds.join(","), "resIds": resIds.join(","), "adminId": $("#systemuseradmin_adminId").val() };
        var tempThis = this;
        this.ajaxResource(args).then(function () {
            tempThis.goBack();
        });
    },
    auditAdminSet: function auditAdminSet(args) {
        args.type = args.type || 'get';
        return this.render(args);
    },
    onAuditAdminSeted: function onAuditAdminSeted() {
        var tempThis = this;
        var id = $("#systemuseradmin_adminId").val();
        this.initTree({
            treeId: 'systemuseradmin_organizationTree',
            url: '/system/core/useradmin/adminOrgTree/' + id,
            check: {
                enable: true,
                chkboxType: { "Y": "ps", "N": "ps" }
            },
            checked: true
        });
    },
    setAuditScope: function setAuditScope(args) {
        var organizationTree = $.fn.zTree.getZTreeObj("systemuseradmin_organizationTree");
        var orgnodes = organizationTree.getCheckedNodes(true);
        if (orgnodes.length == 0) {
            layer.alert("请至少选择一条数据！", {
                icon: 2
            });
            return;
        }
        var orgIds = [];
        $.each(orgnodes, function (key, item) {
            orgIds.push(item.id);
        });
        args.data = { "orgIds": orgIds.join(","), "adminId": $("#systemuseradmin_adminId").val() };
        var tempThis = this;
        this.ajaxResource(args).then(function () {
            tempThis.goBack();
        });
    },
    onDetailsed: function onDetailsed() {
        var tempThis = this;
        var id = $("#systemuseradmin_adminId").val();
        if ($("#systemuseradmin_organizationTree").length > 0) {
            this.initTree({
                treeId: 'systemuseradmin_organizationTree',
                url: '/system/core/useradmin/adminOrgTree/' + id,
                check: {
                    enable: true,
                    chkboxType: { "Y": "ps", "N": "ps" }
                },
                checked: true
            });
        }
        if ($("#systemuseradmin_resourceTree").length > 0) {
            this.initTree({
                treeId: 'systemuseradmin_resourceTree',
                url: '/system/core/useradmin/adminResourceTree/' + id,
                check: {
                    enable: true,
                    chkboxType: { "Y": "ps", "N": "ps" }
                },
                checked: true
            });
        }
    }
}));

/***/ }),

/***/ 382:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./add.html": 383,
	"./adminSet.html": 384,
	"./auditAdmin.html": 385,
	"./auditAdminList.html": 386,
	"./auditAdminSet.html": 387,
	"./details.html": 388,
	"./index.html": 389,
	"./list.html": 390,
	"./safeAdmin.html": 391,
	"./safeAdminList.html": 392,
	"./tabs.html": 393
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
webpackContext.id = 382;

/***/ }),

/***/ 383:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data;
    $$out += '<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n<section class="content-header">\r\n    <h1>\r\n        管理员设置\r\n        <small>新增管理员账号</small>\r\n    </h1>\r\n</section>\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-xs-12" style="min-width:520px">\r\n\r\n            <form id="';
    $$out += $escape(mcid);
    $$out += '_submitForm" class="form-horizontal" role="form">\r\n                <input type="hidden" name="adminType" value="';
    $$out += $escape(data.adminType);
    $$out += '" />\r\n                <input type="hidden" name="adminLevel" value="';
    $$out += $escape(data.adminLevel);
    $$out += '" />\r\n                <!--';
    if (data.isLevel) {
        $$out += '\r\n                <div class="form-group">\r\n                    <div class="col-lg-12">\r\n                        <select name="adminLevel" class="form-control" datatype="*" nullmsg="请选择管理员级别">\r\n                            <option value="">-&#45;&#45;请选择管理员级别-&#45;&#45;</option>\r\n                            <option value="1">一级管理员</option>\r\n                            <option value="2">二级管理员</option>\r\n                        </select>\r\n                        <span class="help-block"></span>\r\n                    </div>\r\n                </div>\r\n                ';
    }
    $$out += '-->\r\n                <div class="row">\r\n                    <div class="col-md-8">\r\n                        <div class="input-group">\r\n                            <input id="';
    $$out += $escape(mcid);
    $$out += '_txtSearch" class="form-control" placeholder="请输入用户名称" type="text">\r\n                            <span class="input-group-btn">\r\n                                        <button id="';
    $$out += $escape(mcid);
    $$out += '_btnSearch" type="button" class="btn btn-primary" e-event="href:/system/useradmin/searchZtree">\r\n                                            <i class="fa fa-search"></i>\r\n                                        </button>\r\n                                    </span>\r\n                        </div>\r\n                        <div class="box box-default" style="margin-top: 10px">\r\n                            <div class="box-body">\r\n\r\n                                <ul id="';
    $$out += $escape(mcid);
    $$out += '_treeContent" class="ztree" style="min-height: 520px;">\r\n                                </ul>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                    <div class="col-md-4">\r\n                        <div class="box box-danger">\r\n                            <div class="box-header with-border">\r\n                                已选人员(<span id="';
    $$out += $escape(mcid);
    $$out += '_selectedUserCount" style="color: #dd4b39;">0</span>)\r\n                            </div>\r\n                            <!-- /.box-header -->\r\n                            <div class="box-body" style="min-height:540px;max-height:540px;overflow-y:auto;">\r\n                                <ul id="';
    $$out += $escape(mcid);
    $$out += '_selectedUserUl" class="nav nav-pills nav-stacked nav-select-user">\r\n                                    <!--<li>\r\n                                        <a href="javascript:;"><i class="fa fa-user"></i>超级管理员</a>\r\n                                        <div class="tools"><a href="javascript:;">X</a></div>\r\n                                    </li>-->\r\n                                </ul>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <div class="row">\r\n                    <div class="col-xs-12" style="text-align: center;">\r\n                        <button e-event="href:/system/useradmin/submitForm?type=post&url=/system/core/useradmin/save&contentType=application/x-www-form-urlencoded" class="btn btn-success" id="';
    $$out += $escape(mcid);
    $$out += '_process_btn" type="button" title="保存信息">\r\n                            <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                        </button>\r\n\r\n                        <button e-event="href:/system/useradmin/goBack" class="btn btn-primary " id="';
    $$out += $escape(mcid);
    $$out += '_back_btn" type="button" title="返回">\r\n                            <i class="fa fa-undo"></i>&nbsp;返回\r\n                        </button>\r\n                    </div>\r\n                </div>\r\n            </form>\r\n\r\n        </div>\r\n    </div>\r\n</section>';
    return $$out;
};

/***/ }),

/***/ 384:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data;
    $$out += '<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n<section class="content-header">\r\n    <h1>\r\n        管理员设置\r\n        <small>职责范围设置</small>\r\n    </h1>\r\n</section>\r\n<section class="content">\r\n    <form id="';
    $$out += $escape(mcid);
    $$out += '_submitForm" class="form-horizontal" role="form">\r\n        <input type="hidden" id="';
    $$out += $escape(mcid);
    $$out += '_adminId" name="id" value="';
    $$out += $escape(data.id);
    $$out += '" />\r\n        <div class="row">\r\n            <div class="col-md-6">\r\n                <div class="box box-default" style="margin-top: 10px">\r\n                    <div class="box-header with-border">\r\n                        设置机构范围\r\n                    </div>\r\n                    <div class="box-body">\r\n\r\n                        <ul id="';
    $$out += $escape(mcid);
    $$out += '_organizationTree" class="ztree" style="min-height: 520px;">\r\n                        </ul>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class="col-md-6">\r\n                <div class="box box-default" style="margin-top: 10px">\r\n                    <div class="box-header with-border">\r\n                        设置资源范围\r\n                    </div>\r\n                    <div class="box-body">\r\n                        <ul id="';
    $$out += $escape(mcid);
    $$out += '_resourceTree" class="ztree" style="min-height: 520px;">\r\n                        </ul>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-xs-12" style="text-align: center;">\r\n                <button e-event="href:/system/useradmin/setScope?type=post&url=/system/core/useradmin/setScope" class="btn btn-success" type="button" title="保存信息">\r\n                    <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                </button>\r\n\r\n                <button e-event="href:/system/useradmin/goBack" class="btn btn-primary " id="';
    $$out += $escape(mcid);
    $$out += '_back_btn" type="button" title="返回">\r\n                    <i class="fa fa-undo"></i>&nbsp;返回\r\n                </button>\r\n            </div>\r\n        </div>\r\n    </form>\r\n</section>';
    return $$out;
};

/***/ }),

/***/ 385:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data;
    $$out += '<form id="';
    $$out += $escape(mcid);
    $$out += '_auditAdmin-content-form">\r\n    <div class="box-body">\r\n        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n            <div class="row">\r\n                <div class="col-sm-2" style="min-width: 320px;">\r\n                    <label>\r\n                        登录账号\uFF1A\r\n                        <div class="input-group date">\r\n                            <input type="text" name="search_LIKE_user.account" class="form-control" style="min-width:120px;">\r\n                        </div>\r\n                    </label>\r\n                </div>\r\n                <div class="col-sm-2" style="min-width: 320px;">\r\n                    <label>\r\n                        用户名称\uFF1A\r\n                        <div class="input-group date">\r\n                            <input type="text" name="search_LIKE_user.name" class="form-control" style="min-width:120px;">\r\n                        </div>\r\n                    </label>\r\n                </div>\r\n                <div class="col-sm-4">\r\n                    ';
    if (data.isLevel) {
        $$out += '\r\n                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                        <button type="button" class="btn btn-block btn-success dropdown-toggle" style="min-width:100px;"\r\n                                data-toggle="dropdown" aria-expanded="false">\r\n                            <li class="fa fa-plus"></li>\r\n                            新建\r\n                            <span class="fa fa-caret-down"></span>\r\n                        </button>\r\n                        <ul class="dropdown-menu" role="menu">\r\n                            <li><a href="javascript:;" e-router="href:/system/useradmin/add?url=/system/core/useradmin/add/2/1">审计管理员</a></li>\r\n                            <li><a href="javascript:;" e-router="href:/system/useradmin/add?url=/system/core/useradmin/add/2/2">二级审计员</a></li>\r\n                        </ul>\r\n                    </div>\r\n                    ';
    } else {
        $$out += '\r\n                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                        <button type="button" class="btn btn-block btn-success" style="min-width:100px;"\r\n                                e-router="href:/system/useradmin/add?url=/system/core/useradmin/add/2/1&tpl=add">\r\n                            <li class="fa fa-plus"></li>\r\n                            新建\r\n                        </button>\r\n                    </div>\r\n                    ';
    }
    $$out += '\r\n                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                        <input type="hidden" name="search_EQ_adminType" value="2" />\r\n                        <button type="button" title="查询" class="btn btn-block btn-primary" style="min-width:100px;"\r\n                                e-event="href:/system/useradmin/search?url=/system/core/useradmin/list&tpl=auditAdminList&form=';
    $$out += $escape(mcid);
    $$out += '_auditAdmin-content-form&contentId=';
    $$out += $escape(mcid);
    $$out += '_auditAdmin-content-list,auto:true">\r\n                            <li class="fa fa-search-plus"></li>\r\n                            查询\r\n                        </button>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n    <div id="';
    $$out += $escape(mcid);
    $$out += '_auditAdmin-content-list" class="row">\r\n\r\n    </div>\r\n</form>';
    return $$out;
};

/***/ }),

/***/ 386:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-6"></div>\r\n            <div class="col-sm-6"></div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-sm-12" style="overflow-x: auto">\r\n                <table id="';
    $$out += $escape(mcid);
    $$out += '_example2" class="table table-bordered table-hover no-footer"  style="min-width:240px;"\r\n                       role="grid" aria-describedby="example2_info">\r\n                    <thead>\r\n                    <tr>\r\n                        <!--<th style="width:50px;"><input type="checkbox" id="selectAll"></th>-->\r\n                        <th class="leftth">登录账户</th>\r\n                        <th class="leftth">用户名称</th>\r\n                        ';
    if (data.isLevel) {
        $$out += '\r\n                        <th class="leftth">级别</th>\r\n                        ';
    }
    $$out += '\r\n                        <th style="width:350px;">操作</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.totalSize == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd no-oper" colspan="';
        if (data.isLevel) {
            $$out += '4';
        } else {
            $$out += '3';
        }
        $$out += '">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n                        <!--<td><input type="checkbox" sign="checkbox" value="';
            $$out += $escape(item.id);
            $$out += '"></td>-->\r\n                        <td class="lefttd no-oper">';
            $$out += $escape(item.user.account);
            $$out += '</td>\r\n                        <td class="lefttd">';
            $$out += $escape(item.user.name);
            $$out += '</td>\r\n                        ';
            if (data.isLevel) {
                $$out += '\r\n                        <td>\r\n                            ';
                if (item.adminLevel == '2') {
                    $$out += '\r\n                            二级\r\n                            ';
                } else {
                    $$out += '\r\n                            系统级\r\n                            ';
                }
                $$out += '\r\n                        </td>\r\n                        ';
            }
            $$out += '\r\n                        <!--操作-->\r\n                        <td align="center-left">\r\n                            <button e-router="href:/system/useradmin/details?url=/system/core/useradmin/details/';
            $$out += $escape(item.id);
            $$out += '&tpl=details" type="button" class="btn btn-default">\r\n                                <i class="fa fa-search"></i> 查看\r\n                            </button>\r\n                            ';
            if (data.isLevel && item.adminLevel == '2') {
                $$out += '\r\n                            <button e-router="href:/system/useradmin/auditAdminSet?url=/system/core/useradmin/adminSet/';
                $$out += $escape(item.id);
                $$out += '&tpl=auditAdminSet" type="button" class="btn btn-default">\r\n                                <i class="fa fa-cog"></i> 职责范围\r\n                            </button>\r\n                            ';
            }
            $$out += '\r\n                            <button e-event="href:/system/useradmin/dialog?dialog=confirm&type=get&url=/system/core/useradmin/delete/';
            $$out += $escape(item.id);
            $$out += '" type="button" class="btn btn-default">\r\n                                <i class="fa fa-times"></i> 删除\r\n                            </button>\r\n                        </td>\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n        </div>\r\n        <div class="row" id="';
    $$out += $escape(mcid);
    $$out += '_auditAdmin-content-list-pager">\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ }),

/***/ 387:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data;
    $$out += '<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n<section class="content-header">\r\n    <h1>\r\n        管理员设置\r\n        <small>职责范围设置</small>\r\n    </h1>\r\n</section>\r\n<section class="content">\r\n    <form id="';
    $$out += $escape(mcid);
    $$out += '_submitForm" class="form-horizontal" role="form">\r\n        <input type="hidden" id="';
    $$out += $escape(mcid);
    $$out += '_adminId" name="id" value="';
    $$out += $escape(data.id);
    $$out += '" />\r\n        <div class="row">\r\n            <div class="col-md-12">\r\n                <div class="box box-default" style="margin-top: 10px">\r\n                    <div class="box-header with-border">\r\n                        设置机构范围\r\n                    </div>\r\n                    <div class="box-body">\r\n\r\n                        <ul id="';
    $$out += $escape(mcid);
    $$out += '_organizationTree" class="ztree" style="min-height: 520px;">\r\n                        </ul>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-xs-12" style="text-align: center;">\r\n                <button e-event="href:/system/useradmin/setAuditScope?type=post&url=/system/core/useradmin/setAuditScope" class="btn btn-success" type="button" title="保存信息">\r\n                    <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                </button>\r\n\r\n                <button e-event="href:/system/useradmin/goBack" class="btn btn-primary " id="';
    $$out += $escape(mcid);
    $$out += '_back_btn" type="button" title="返回">\r\n                    <i class="fa fa-undo"></i>&nbsp;返回\r\n                </button>\r\n            </div>\r\n        </div>\r\n    </form>\r\n</section>';
    return $$out;
};

/***/ }),

/***/ 388:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data;
    $$out += '<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n<input type="hidden" id="';
    $$out += $escape(mcid);
    $$out += '_adminId" name="adminId" value="';
    $$out += $escape(data.userAdmin.id);
    $$out += '" />\r\n<section class="content-header">\r\n    <h1>\r\n        管理员设置\r\n        <small>管理员信息查看</small>\r\n    </h1>\r\n</section>\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-xs-12" style="min-width:520px">\r\n            <div class="box box-warning">\r\n                <div class="box-header with-border">\r\n                    查看详情\r\n                </div>\r\n                <!-- /.box-header -->\r\n                <div class="box-body">\r\n\r\n                    <div class="row" style="padding:5px;">\r\n                        <label class="col-xs-2" style="text-align: right;">登录账号\uFF1A</label>\r\n                        <div class="col-xs-4">';
    $$out += $escape(data.userAdmin.user.account);
    $$out += '</div>\r\n                        <label class="col-xs-2" style="text-align: right;">用户名称\uFF1A</label>\r\n                        <div class="col-xs-4">';
    $$out += $escape(data.userAdmin.user.name);
    $$out += '</div>\r\n                    </div>\r\n                    <div class="row" style="padding:5px;">\r\n                        <label class="col-xs-2" style="text-align: right;">所属机构\uFF1A</label>\r\n                        <div class="col-xs-4">';
    $$out += $escape(data.orgName);
    $$out += '</div>\r\n                    </div>\r\n                    ';
    if (data.isLevel || data.isTripartite) {
        $$out += '\r\n                    <div class="row" style="padding:5px;">\r\n                        ';
        if (data.isTripartite) {
            $$out += '\r\n                        <label class="col-xs-2" style="text-align: right;">管理员类型\uFF1A</label>\r\n                        <div class="col-xs-4">\r\n                            ';
            if (data.userAdmin.adminType == '0') {
                $$out += '\r\n                            管理员\r\n                            ';
            } else if (data.userAdmin.adminType == '1') {
                $$out += '\r\n                            安全员\r\n                            ';
            } else {
                $$out += '\r\n                            审计员\r\n                            ';
            }
            $$out += '\r\n                        </div>\r\n                        ';
        }
        $$out += '\r\n                        ';
        if (data.isLevel) {
            $$out += '\r\n                        <label class="col-xs-2" style="text-align: right;">管理员级别\uFF1A</label>\r\n                        <div class="col-xs-4">\r\n                            ';
            if (data.userAdmin.adminLevel == '2') {
                $$out += '\r\n                            二级\r\n                            ';
            } else {
                $$out += '\r\n                            系统级\r\n                            ';
            }
            $$out += '\r\n                        </div>\r\n                        ';
        }
        $$out += '\r\n                    </div>\r\n                    ';
    }
    $$out += '\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n    ';
    if (data.isLevel && data.userAdmin.adminLevel == '2') {
        $$out += '\r\n    <div class="row">\r\n        ';
        if (data.userAdmin.adminType != '2') {
            $$out += '\r\n        <div class="col-xs-6">\r\n            <div class="box box-default" style="margin-top: 10px">\r\n                <div class="box-header with-border">\r\n                    机构范围\r\n                </div>\r\n                <div class="box-body">\r\n\r\n                    <ul id="';
            $$out += $escape(mcid);
            $$out += '_organizationTree" class="ztree" style="min-height: 520px;">\r\n                    </ul>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class="col-xs-6">\r\n            <div class="box box-default" style="margin-top: 10px">\r\n                <div class="box-header with-border">\r\n                    资源范围\r\n                </div>\r\n                <div class="box-body">\r\n\r\n                    <ul id="';
            $$out += $escape(mcid);
            $$out += '_resourceTree" class="ztree" style="min-height: 520px;">\r\n                    </ul>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        ';
        } else {
            $$out += '\r\n        <div class="col-xs-12">\r\n            <div class="box box-default" style="margin-top: 10px">\r\n                <div class="box-header with-border">\r\n                    机构范围\r\n                </div>\r\n                <div class="box-body">\r\n\r\n                    <ul id="';
            $$out += $escape(mcid);
            $$out += '_organizationTree" class="ztree" style="min-height: 520px;">\r\n                    </ul>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        ';
        }
        $$out += '\r\n    </div>\r\n    ';
    }
    $$out += '\r\n    <div class="row">\r\n        <div class="col-xs-12" style="text-align: center;">\r\n            <button e-event="href:/system/useradmin/goBack" class="btn btn-primary " id="';
    $$out += $escape(mcid);
    $$out += '_back_btn" type="button" title="返回">\r\n                <i class="fa fa-undo"></i>&nbsp;返回\r\n            </button>\r\n        </div>\r\n    </div>\r\n</section>';
    return $$out;
};

/***/ }),

/***/ 389:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        管理员设置\r\n        <small>管理员设置</small>\r\n    </h1>\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <div id="';
    $$out += $escape(mcid);
    $$out += '_tabs-div" class="col-xs-12">\r\n\r\n        </div>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 390:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-6"></div>\r\n            <div class="col-sm-6"></div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-sm-12" style="overflow-x: auto">\r\n                <table id="';
    $$out += $escape(mcid);
    $$out += '_example2" class="table table-bordered table-hover no-footer"  style="min-width:240px;"\r\n                       role="grid" aria-describedby="example2_info">\r\n                    <thead>\r\n                    <tr>\r\n                        <!--<th style="width:50px;"><input type="checkbox" id="selectAll"></th>-->\r\n                        <th class="leftth ">登录账户</th>\r\n                        <th class="leftth">用户名称</th>\r\n                        ';
    if (data.isLevel) {
        $$out += '\r\n                        <th class="leftth">级别</th>\r\n                        ';
    }
    $$out += '\r\n                        <th style="width:350px;">操作</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.totalSize == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd no-oper" colspan="';
        if (data.isLevel) {
            $$out += '4';
        } else {
            $$out += '3';
        }
        $$out += '">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n                        <!--<td><input type="checkbox" sign="checkbox" value="';
            $$out += $escape(item.id);
            $$out += '"></td>-->\r\n                        <td class="lefttd no-oper">';
            $$out += $escape(item.user.account);
            $$out += '</td>\r\n                        <td class="lefttd">';
            $$out += $escape(item.user.name);
            $$out += '</td>\r\n                        ';
            if (data.isLevel) {
                $$out += '\r\n                        <td>\r\n                            ';
                if (item.adminLevel == '2') {
                    $$out += '\r\n                            二级\r\n                            ';
                } else {
                    $$out += '\r\n                            系统级\r\n                            ';
                }
                $$out += '\r\n                        </td>\r\n                        ';
            }
            $$out += '\r\n                        <!--操作-->\r\n                        <td align="center-left">\r\n                            <button e-router="href:/system/useradmin/details?url=/system/core/useradmin/details/';
            $$out += $escape(item.id);
            $$out += '&tpl=details" type="button" class="btn btn-default">\r\n                                <i class="fa fa-search"></i> 查看\r\n                            </button>\r\n                            ';
            if (data.isLevel && item.adminLevel == '2') {
                $$out += '\r\n                            <button e-router="href:/system/useradmin/adminSet?url=/system/core/useradmin/adminSet/';
                $$out += $escape(item.id);
                $$out += '&tpl=adminSet" type="button" class="btn btn-default">\r\n                                <i class="fa fa-cog"></i> 职责范围\r\n                            </button>\r\n                            ';
            }
            $$out += '\r\n                            <button e-event="href:/system/useradmin/dialog?dialog=confirm&type=get&url=/system/core/useradmin/delete/';
            $$out += $escape(item.id);
            $$out += '" type="button" class="btn btn-default">\r\n                                <i class="fa fa-times"></i> 删除\r\n                            </button>\r\n                        </td>\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n        </div>\r\n        <div class="row" id="';
    $$out += $escape(mcid);
    $$out += '_admin-content-list-pager">\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ }),

/***/ 391:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data;
    $$out += '<form id="';
    $$out += $escape(mcid);
    $$out += '_safeAdmin-content-form">\r\n    <div class="box-body">\r\n        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n            <div class="row">\r\n                <div class="col-sm-2" style="min-width: 320px;">\r\n                    <label>\r\n                        登录账号\uFF1A\r\n                        <div class="input-group date">\r\n                            <input type="text" name="search_LIKE_user.account" class="form-control" style="min-width:120px;">\r\n                        </div>\r\n                    </label>\r\n                </div>\r\n                <div class="col-sm-2" style="min-width: 320px;">\r\n                    <label>\r\n                        用户名称\uFF1A\r\n                        <div class="input-group date">\r\n                            <input type="text" name="search_LIKE_user.name" class="form-control" style="min-width:120px;">\r\n                        </div>\r\n                    </label>\r\n                </div>\r\n                <div class="col-sm-4">\r\n                    ';
    if (data.isLevel) {
        $$out += '\r\n                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                        <button type="button" class="btn btn-block btn-success dropdown-toggle" style="min-width:100px;"\r\n                                data-toggle="dropdown" aria-expanded="false">\r\n                            <li class="fa fa-plus"></li>\r\n                            新建\r\n                            <span class="fa fa-caret-down"></span>\r\n                        </button>\r\n                        <ul class="dropdown-menu" role="menu">\r\n                            <li><a href="javascript:;" e-router="href:/system/useradmin/add?url=/system/core/useradmin/add/1/1">安全管理员</a></li>\r\n                            <li><a href="javascript:;" e-router="href:/system/useradmin/add?url=/system/core/useradmin/add/1/2">二级安全员</a></li>\r\n                        </ul>\r\n                    </div>\r\n                    ';
    } else {
        $$out += '\r\n                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                        <button type="button" class="btn btn-block btn-success" style="min-width:100px;"\r\n                                e-router="href:/system/useradmin/add?url=/system/core/useradmin/add/1/1&tpl=add">\r\n                            <li class="fa fa-plus"></li>\r\n                            新建\r\n                        </button>\r\n                    </div>\r\n                    ';
    }
    $$out += '\r\n                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                        <input type="hidden" name="search_EQ_adminType" value="1" />\r\n                        <button type="button" title="查询" class="btn btn-block btn-primary" style="min-width:100px;"\r\n                                e-event="href:/system/useradmin/search?url=/system/core/useradmin/list&tpl=safeAdminList&form=';
    $$out += $escape(mcid);
    $$out += '_safeAdmin-content-form&contentId=';
    $$out += $escape(mcid);
    $$out += '_safeAdmin-content-list,auto:true">\r\n                            <li class="fa fa-search-plus"></li>\r\n                            查询\r\n                        </button>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n    <div id="';
    $$out += $escape(mcid);
    $$out += '_safeAdmin-content-list" class="row">\r\n\r\n    </div>\r\n</form>';
    return $$out;
};

/***/ }),

/***/ 392:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-6"></div>\r\n            <div class="col-sm-6"></div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-sm-12" style="overflow-x: auto">\r\n                <table id="';
    $$out += $escape(mcid);
    $$out += '_example2" class="table table-bordered table-hover no-footer"  style="min-width:240px;"\r\n                       role="grid" aria-describedby="example2_info">\r\n                    <thead>\r\n                    <tr>\r\n                        <!--<th style="width:50px;"><input type="checkbox" id="selectAll"></th>-->\r\n                        <th class="leftth ">登录账户</th>\r\n                        <th class="leftth">用户名称</th>\r\n                        ';
    if (data.isLevel) {
        $$out += '\r\n                        <th class="leftth">级别</th>\r\n                        ';
    }
    $$out += '\r\n                        <th style="width:350px;">操作</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.totalSize == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd no-oper" colspan="';
        if (data.isLevel) {
            $$out += '4';
        } else {
            $$out += '3';
        }
        $$out += '">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n                        <!--<td><input type="checkbox" sign="checkbox" value="';
            $$out += $escape(item.id);
            $$out += '"></td>-->\r\n                        <td class="lefttd no-oper">';
            $$out += $escape(item.user.account);
            $$out += '</td>\r\n                        <td class="lefttd">';
            $$out += $escape(item.user.name);
            $$out += '</td>\r\n                        ';
            if (data.isLevel) {
                $$out += '\r\n                        <td>\r\n                            ';
                if (item.adminLevel == '2') {
                    $$out += '\r\n                            二级\r\n                            ';
                } else {
                    $$out += '\r\n                            系统级\r\n                            ';
                }
                $$out += '\r\n                        </td>\r\n                        ';
            }
            $$out += '\r\n                        <!--操作-->\r\n                        <td align="center-left">\r\n                            <button e-router="href:/system/useradmin/details?url=/system/core/useradmin/details/';
            $$out += $escape(item.id);
            $$out += '&tpl=details" type="button" class="btn btn-default">\r\n                                <i class="fa fa-search"></i> 查看\r\n                            </button>\r\n                            ';
            if (data.isLevel && item.adminLevel == '2') {
                $$out += '\r\n                            <button e-router="href:/system/useradmin/adminSet?url=/system/core/useradmin/adminSet/';
                $$out += $escape(item.id);
                $$out += '&tpl=adminSet" type="button" class="btn btn-default">\r\n                                <i class="fa fa-cog"></i> 职责范围\r\n                            </button>\r\n                            ';
            }
            $$out += '\r\n                            <button e-event="href:/system/useradmin/dialog?dialog=confirm&type=get&url=/system/core/useradmin/delete/';
            $$out += $escape(item.id);
            $$out += '" type="button" class="btn btn-default">\r\n                                <i class="fa fa-times"></i> 删除\r\n                            </button>\r\n                        </td>\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n        </div>\r\n        <div class="row" id="';
    $$out += $escape(mcid);
    $$out += '_safeAdmin-content-list-pager">\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ }),

/***/ 393:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data;
    $$out += '<div class="nav-tabs-custom">\r\n    <ul class="nav nav-tabs">\r\n        <li class="active">\r\n            <a href="#';
    $$out += $escape(mcid);
    $$out += '_admin-content" data-toggle="tab">系统管理员</a>\r\n        </li>\r\n        ';
    if (data.isTripartite) {
        $$out += '\r\n        <li>\r\n            <a href="#';
        $$out += $escape(mcid);
        $$out += '_safeAdmin-content" data-toggle="tab" e-event="href:/system/useradmin/toTab?type=get&url=/system/core/useradmin/tabs&contentId=';
        $$out += $escape(mcid);
        $$out += '_safeAdmin-content&tpl=safeAdmin">安全管理员</a>\r\n        </li>\r\n        <li>\r\n            <a href="#';
        $$out += $escape(mcid);
        $$out += '_auditAdmin-content" data-toggle="tab" e-event="href:/system/useradmin/toTab?type=get&url=/system/core/useradmin/tabs&contentId=';
        $$out += $escape(mcid);
        $$out += '_auditAdmin-content&tpl=auditAdmin">审计管理员</a>\r\n        </li>\r\n        ';
    }
    $$out += '\r\n    </ul>\r\n    <div class="tab-content">\r\n        <div id="';
    $$out += $escape(mcid);
    $$out += '_admin-content" class="tab-pane active">\r\n            <form id="';
    $$out += $escape(mcid);
    $$out += '_admin-content-form">\r\n                <div class="box-body">\r\n                    <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                        <div class="row">\r\n                            <div class="col-sm-2" style="min-width: 320px;">\r\n                                <label>\r\n                                    登录账号\uFF1A\r\n                                    <div class="input-group date">\r\n                                        <input type="text" name="search_LIKE_user.account" class="form-control" style="min-width:120px;">\r\n                                    </div>\r\n                                </label>\r\n                            </div>\r\n                            <div class="col-sm-2" style="min-width: 320px;">\r\n                                <label>\r\n                                    用户名称\uFF1A\r\n                                    <div class="input-group date">\r\n                                        <input type="text" name="search_LIKE_user.name" class="form-control" style="min-width:120px;">\r\n                                    </div>\r\n                                </label>\r\n                            </div>\r\n                            <div class="col-sm-4">\r\n                                ';
    if (data.isLevel) {
        $$out += '\r\n                                <!--<div class="inline pull-right btn-group" style="margin-right: 10px;">\r\n                                    <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-expanded="false">\r\n                                        <i class="fa fa-cog"></i>\r\n                                        批量设置\r\n                                        <span class="fa fa-caret-down"></span>\r\n                                    </button>\r\n                                    <ul class="dropdown-menu" role="menu">\r\n                                        <li><a href="javascript:;">管理员级别\uFF08系统级\u3001二级\uFF09</a></li>\r\n                                        <li><a href="javascript:;" e-event="href:/system/useradmin/dialog?tpl=setOrg">二级管理员权限职责\uFF1A机构范围</a></li>\r\n                                        <li><a href="javascript:;" e-event="href:/system/useradmin/dialog?tpl=setFunc">二级管理员权限职责\uFF1A功能范围</a></li>\r\n                                        <li><a href="javascript:;">二级管理员权限职责\uFF1A角色范围</a></li>\r\n                                    </ul>\r\n                                </div>-->\r\n                                <div class="inline pull-right" style="margin-right: 10px;">\r\n                                    <button type="button" class="btn btn-block btn-success dropdown-toggle" style="min-width:100px;"\r\n                                            data-toggle="dropdown" aria-expanded="false">\r\n                                        <li class="fa fa-plus"></li>\r\n                                        新建\r\n                                        <span class="fa fa-caret-down"></span>\r\n                                    </button>\r\n                                    <ul class="dropdown-menu" role="menu">\r\n                                        <li><a href="javascript:;" e-router="href:/system/useradmin/add?url=/system/core/useradmin/add/0/1">系统管理员</a></li>\r\n                                        <li><a href="javascript:;" e-router="href:/system/useradmin/add?url=/system/core/useradmin/add/0/2">二级管理员</a></li>\r\n                                    </ul>\r\n                                </div>\r\n                                ';
    } else {
        $$out += '\r\n                                <div class="inline pull-right" style="margin-right: 10px;">\r\n                                    <button type="button" class="btn btn-block btn-success" style="min-width:100px;"\r\n                                            e-router="href:/system/useradmin/add?url=/system/core/useradmin/add/0/1&tpl=add">\r\n                                        <li class="fa fa-plus"></li>\r\n                                        新建\r\n                                    </button>\r\n                                </div>\r\n                                ';
    }
    $$out += '\r\n\r\n                                <div class="inline pull-right" style="margin-right: 10px;">\r\n                                    <input type="hidden" name="search_EQ_adminType" value="0" />\r\n                                    <button type="button" title="查询" class="btn btn-block btn-primary" style="min-width:100px;"\r\n                                            e-event="href:/system/useradmin/search?url=/system/core/useradmin/list&form=';
    $$out += $escape(mcid);
    $$out += '_admin-content-form&contentId=';
    $$out += $escape(mcid);
    $$out += '_admin-content-list,auto:true">\r\n                                        <li class="fa fa-search-plus"></li>\r\n                                        查询\r\n                                    </button>\r\n                                </div>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <div id="';
    $$out += $escape(mcid);
    $$out += '_admin-content-list" class="row">\r\n\r\n                </div>\r\n            </form>\r\n        </div>\r\n        <div id="';
    $$out += $escape(mcid);
    $$out += '_safeAdmin-content" class="tab-pane">\r\n\r\n        </div>\r\n        <div id="';
    $$out += $escape(mcid);
    $$out += '_auditAdmin-content" class="tab-pane">\r\n\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ })

});