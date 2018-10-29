webpackJsonpvendor([13],{

/***/ 261:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_components_table_Table__ = __webpack_require__(25);
/**
 * Created by dongyue on 2017/5/6.
 * 演示Handler应用
 */



/* harmony default export */ __webpack_exports__["default"] = (new __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__["default"]({
    /**
     * 初始化常量
     * 注意：本Handler中，search方法中,zTree的ID已固定，如若更改zTreeId，请一起更新。
     * @returns {options1}
     */
    options1: function options1() {
        this.options = {
            leftId: 'leftcontent',
            rightId: 'rightcontent',
            roleListId: 'rolelistbind',
            roleListUrl: 'system/core/role/list',
            leftTreeId: 'orguserTree',
            submitForm: 'searchForm',
            leftTreeUrl: 'system/core/useradmin/orguseradmintree',
            map: {},
            lastIds: ''
        };
        return this;
    },

    resolveTpl: function resolveTpl(tpl) {
        return __webpack_require__(372)("./" + tpl + ".html");
    },

    onIndexed: function onIndexed() {
        this.options1();
        var main = this;

        //绑定全选事件
        $(document).on("click", "#quanxuan", function (e) {
            var ids = main.options.map;
            var xz = $(e.target).prop("checked");
            $(".qx").prop("checked", xz);
            $("#quanxuan").prop("checked", xz);
            var allRoles = document.getElementsByName("roles");
            if (xz) {
                for (var i = 0; i < allRoles.length; i++) {
                    ids[allRoles[i].id] = allRoles[i].id;
                }
            } else {
                for (var i = 0; i < allRoles.length; i++) {
                    delete ids[allRoles[i].id];
                }
            }
        });
        $(document).off("click", ".qx");
        $(document).on("click", ".qx", function () {
            var ids = main.options.map;
            var allRoles = document.getElementsByName("roles");
            var num = 0;
            for (var i = 0; i < allRoles.length; i++) {
                if (allRoles[i].checked) {
                    num = num + 1;
                }
            }
            if (num == allRoles.length) {
                $("#quanxuan").prop("checked", true);
            } else {
                $("#quanxuan").prop("checked", false);
            }
            var xz = $(this).prop("checked");
            if (xz) {
                ids[$(this).val()] = $(this).val();
            } else {
                delete ids[$(this).val()];
            }
        });
        this.initUserTree();
    },

    /**
     * 初始化用户树tree
     */
    initUserTree: function initUserTree() {
        var tree_setting = {
            check: {
                enable: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    if (treeNode.type == 'user') {
                        this.getAllroles(treeNode.id);
                        $("#currentNodeUserId").val(treeNode.id);
                        $("#currentUser").text(treeNode.name);
                        this.selUser(treeNode.id, treeNode.name, treeNode.approvalRoleType);
                    }
                }.bind(this),
                beforeClick: function (treeId, treeNode) {
                    if (treeNode.type == "org") {
                        return false;
                    }
                }.bind(this)
            },
            view: {
                fontCss: function fontCss(treeId, treeNode) {
                    return !!treeNode.highlight ? { color: "blue", "font-weight": "bold" } : {
                        color: "#000", "font-weight": "normal"
                    };
                }
            }
        };

        $.ajax({
            cache: false,
            async: false, // 同步加载
            type: "POST",
            url: this.options.leftTreeUrl,
            dataType: "json",
            success: function (data) {
                //更改用户节点图标
                var nodes = data.data;
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].type == 'user') {
                        nodes[i].iconSkin = "user";
                    }
                }
                //生成树
                $.fn.zTree.init($('#' + this.options.leftTreeId), tree_setting, nodes);
                //默认选中第一个用户
                var zTree = $.fn.zTree.getZTreeObj(this.options.leftTreeId);
                var nodes = zTree.getNodesByParam("type", "user");
                zTree.selectNode(nodes[0], true);
                zTree.expandNode(nodes[0].getParentNode(), true, false, false); //展开选中节点
                //保存按钮是否显示
                if (data.data.length == 0) {
                    $("#" + this.options.rightId + " #btnSure").attr('disabled', true);
                } else {
                    $("#" + this.options.rightId + " #btnSure").attr('disabled', false);
                }
                var defaultNode = nodes[0];
                $("#currentUser").text(defaultNode.name);
                $("#currentUserId").val(defaultNode.id);
                //获取全部的角色
                this.getAllroles(defaultNode.id);
                //获取该用户的角色
                this.selUser(defaultNode.id, defaultNode.name, defaultNode.approvalRoleType);
            }.bind(this)
        });
    },

    /**
     * 获取全部角色
     */
    getAllroles: function getAllroles() {
        var args = {
            tpl: "list",
            url: "/system/core/role/list",
            contentId: "list"
        };
        this.search(args);
    },

    /**
     * 点击用户，取得授权角色，放入角色缓存中
     */
    selUser: function selUser(userid, username, approvalRoleType) {
        if (!userid) {
            $("#" + this.options.rightId + " #btnSure").attr('disabled', true);
        } else {
            $("#" + this.options.rightId + " #btnSure").attr('disabled', false);
        }

        if (approvalRoleType == "1") {
            $("#" + this.options.rightId + " #btnSure").attr('disabled', true);
            $("#" + this.options.rightId + " #btnSure").html('待审批');
        } else {
            $("#" + this.options.rightId + " #btnSure").attr('disabled', false);
            $("#" + this.options.rightId + " #btnSure").html('保存');
        }

        $("#" + this.options.rightId + ' #page').val(1);
        $("#" + this.options.rightId + " #hidUserid").val(userid);
        $("#" + this.options.rightId + " #hidApprovalType").val(approvalRoleType);
        $("#" + this.options.rightId + " #spnCurUser").text(username);
        // 清除选中记录
        this.options.map = {};
        var ids = this.options.map;
        if (userid) {
            var url = "system/core/user/rolesbyuserid/" + userid;
            $.ajax({
                cache: false,
                async: true,
                type: "GET",
                url: url,
                dataType: "json",
                success: function (data) {
                    if (data.data.length > 0) {
                        setTimeout(function () {
                            for (var i = 0; i < data.data.length; i++) {
                                $("#" + data.data[i].id).attr('checked', true);
                                ids[data.data[i].id] = data.data[i].id;
                            }
                            //判断是否需要勾选全选框
                            var boo = true;
                            var allRoles = document.getElementsByName("roles");
                            for (var i = 0; i < allRoles.length; i++) {
                                if (ids[allRoles[i].id] == null) {
                                    boo = false;
                                }
                            }
                            $("#quanxuan").prop("checked", boo);
                            //结束
                        }, 100);
                    }
                    return;
                }.bind(this)
            });
        }
    },

    /**
     * 提交保存
     */
    bindRoles: function bindRoles() {
        var ids = this.options.map;
        //获取当前节点
        var zTree = $.fn.zTree.getZTreeObj(this.options.leftTreeId);
        var checkedNode = zTree.getSelectedNodes();
        var userid = checkedNode[0].id;
        //转换成ID串
        var roles = '';
        for (var i in ids) {
            roles = roles + i + ',';
        }
        /*20170925suntf
         if (roles == '') {
             layer.msg("没有选择角色！");
             return;
         }*/
        var param = { "userid": userid, "roleids": roles.toString() };
        var url = "system/core/authorize/binduserroles";
        $.ajax({
            cache: false,
            data: param,
            type: "POST",
            url: url,
            dataType: "json",
            success: function (data) {
                //this.initUserTree();      20170925suntf
            }.bind(this)
        });
    },

    /**
     * 取消选择
     */
    bindReset: function bindReset() {
        var zTree = $.fn.zTree.getZTreeObj(this.options.leftTreeId);
        var checkedNode = zTree.getSelectedNodes();
        this.getAllroles();
        this.selUser(checkedNode[0].id);
    },

    /**
     * 实现查询功能
     * @param args
     * {url:/demo/mybatisuser/pages-(必选),type:post-(默认),tpl:list-(默认),contentId:list-(默认)}
     */
    search: function search(args) {
        var main = this;
        args.form = args.form || 'searchForm';
        if (args.url) {
            args.contentId = args.contentId || config.listId;
            var form = $("#" + args.form);
            if (form.length > 0) {
                args.data = form.serialize();
                args.tpl = args.tpl || config.listTpl;
                return this.render(args).then(function (data) {
                    new __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_components_table_Table__["default"]({
                        pageNum: data.data.pageNum,
                        totalPages: data.data.totalPages,
                        totalSize: data.data.totalSize,
                        pageSize: data.data.pageSize,
                        buttonClickCallback: this.search.bind(this, args)
                    });
                }.bind(this)).then(setTimeout(function () {
                    var ids = main.options.map;
                    var allRoles = document.getElementsByName("roles");
                    for (var i = 0; i < allRoles.length; i++) {
                        if (ids[allRoles[i].id] != null) {
                            $("#" + allRoles[i].id).attr('checked', true);
                        }
                    }
                }, 100));
            } else {
                throw new Error("参数无效，DOM中无法找到id为[" + args.form + "]的Form！");
            }
        } else {
            throw new Error("参数无效，请传递如{url:/demo/mybatisuser/pages-(必选),type:post-(默认),tpl:list-(默认),contentId:list-(默认)}的JS对象");
        }
    },


    /**
     * zTree上部的查询框
     * @param element
     */
    searchZtree: function searchZtree(element) {
        var treeId = "orguserTree";
        var searchConditionId = "txtSearch";
        var searchCondition = $('#' + searchConditionId).val();
        var searchObj = {};
        searchObj.treeId = treeId;
        searchObj.condition = searchCondition;
        searchObj.type = "user";
        //获得选择的那个节点
        var nodeObj = this.treeSearchAndExpand(searchObj);
        if (nodeObj && nodeObj.id) {
            if (nodeObj.id) {
                // 查询全部的角色
                this.getAllroles();
                // 查询这个节点的角色
                this.selUser(nodeObj.id);
                $("#currentUser").text(nodeObj.name);
                $("#currentUserId").val(nodeObj.id);
            } else {}
        } else {
            layer.msg("没有查询到指定的用户！");
        }
    },

    /**
     * 树状列表查询后展示方法
     */
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
                // treeObj.selectNode(highlightNodes[i], true);
                highlightNodes[i].highlight = true;
                if (first) {
                    nodeObj = highlightNodes[i];
                    treeObj.selectNode(highlightNodes[i], true);
                    first = false;
                }
                treeObj.updateNode(highlightNodes[i]); // 级联选中
                treeObj.expandNode(highlightNodes[i].getParentNode(), true);
            }
        }
        return nodeObj;
    }
}));

/***/ }),

/***/ 372:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./index.html": 373,
	"./list.html": 374
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
webpackContext.id = 372;

/***/ }),

/***/ 373:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图表样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n\r\n<section class="content-header">\r\n    <h1>授权管理\r\n        <small>用户角色绑定</small>\r\n    </h1>\r\n\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <form id="searchForm">\r\n            <!--左侧树结构部分-->\r\n            <div class="col-sm-2">\r\n                <div style="min-height: 600px">\r\n                    <div class="input-group">\r\n                        <input type="text" id="txtSearch" class="form-control" placeholder="请输入用户名称"/>\r\n                        <span class="input-group-btn">\r\n                          <button id="btnSearch" type="button" class="btn  btn-primary"\r\n                                  e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchZtree">\r\n                              <i class="fa fa-search"></i>\r\n                          </button>\r\n                        </span>\r\n                    </div>\r\n\r\n                    <!--<div class="box" style="min-height: 600px;margin-top: 10px">\r\n                        <div class=""style="overflow-x: auto">\r\n                            <ul id="orguserTree" class="ztree" style=" min-height: 585px"></ul>\r\n                        </div>\r\n                    </div>-->\r\n                    <div class="box" style="margin-top: 10px">\r\n                        <div class="">\r\n                            <ul id="orguserTree" class="ztree" style=" max-height: 850px; overflow: auto;height:800px;"></ul>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <!--右侧列表部分-->\r\n            <div class="col-sm-10">\r\n                <div class="box">\r\n                    <div class="box-header">\r\n                        <input id="currentUserId" value="" name="currentUserId" hidden>\r\n                        <input id="flag" value="1" name="flag" hidden>\r\n                        <input type="hidden" name="search_EQ_roleType" value="1">\r\n                        为用户&nbsp;<span class="color-blue" style="color: #1E5E9C;" id="currentUser"></span>&nbsp;绑定角色\r\n                    </div>\r\n                    <div class="box-body">\r\n                        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                            <div id="list" class="row">\r\n                            </div>\r\n                        </div>\r\n                        <div class="row-fluid">\r\n                            <div class="zTree-box" style="width: 100%;overflow: auto">\r\n                                <div class="col-md-12 bind-role-list" id="rolelistbind"/>\r\n                            </div>\r\n                            <div class="box-footer col-sm-12">\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindRoles?type=post&form=searchForm&url=/system/core/authorize/userrole/binduserroles&contentType=application/x-www-form-urlencoded"\r\n                                        class="btn btn-instagram" id="save_btn" type="button" title="保存"\r\n                                        e-permission="core_authorize_binduserroles">\r\n                                    <i class="fa fa-floppy-o"></i>&nbsp;保存\r\n                                </button>\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindReset" class="btn btn-primary "\r\n                                        id="back_btn" type="button" title="返回">\r\n                                    <i class="fa fa-mail-reply"></i>&nbsp;取消\r\n                                </button>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 374:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, $escape = $imports.$escape;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-6"></div>\r\n            <div class="col-sm-6"></div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-sm-12" style="max-height:331px; overflow-x: auto">\r\n                <table id="example2" class="table table-bordered" style="min-width:240px;"\r\n                       role="grid" aria-describedby="example2_info">\r\n                    <thead>\r\n                    <tr>\r\n                        <th style="width:50px;"><input type="checkbox" class="icheckbox_minimal-blue checked" id="quanxuan"></th>\r\n                        <th style="width:170px;" class="leftth">角色名称</th>\r\n                        <th style="width:80px;">角色描述</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.totalSize == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd" colspan="2">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n                        <td><input type="checkbox" sign="checkbox" class="qx icheckbox_minimal-blue checked" id="';
            $$out += $escape(item.id);
            $$out += '" value="';
            $$out += $escape(item.id);
            $$out += '"\r\n                                   objid="';
            $$out += $escape(item.id);
            $$out += '" name="roles"></td>\r\n                        <td>';
            $$out += $escape(item.name);
            $$out += '</td>\r\n                        ';
            if (item.remark.length > 20) {
                $$out += '\r\n                        <td  class="lefttd" style="white-space: nowrap" title="';
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
            $$out += '\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n\r\n            </div>\r\n        </div>\r\n        <div class="row" id="pager">\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ })

});