webpackJsonpvendor([19],{

/***/ 246:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__ = __webpack_require__(18);
var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

/**
 * 用户IP地址绑定
 *
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
            leftTreeId: 'orguserTree',
            submitForm: 'searchForm',
            leftTreeUrl: 'system/core/user/orgusertree',
            map: {},
            lastIds: ''
        };
        return this;
    },

    resolveTpl: function resolveTpl(tpl) {
        return __webpack_require__(291)("./" + tpl + ".html");
    },

    onIndexed: function onIndexed() {
        this.options1();
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
                        this.getIpBinds(treeNode.id, treeNode.name);
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
                var defaultNode = nodes[0];
                var id = defaultNode.id;
                var name = defaultNode.name;
                //获取全部的角色
                this.getIpBinds(id, name);
            }.bind(this)
        });
    },

    getIpBinds: function getIpBinds(userId, userName) {
        var args = {
            tpl: "detail",
            url: "system//core/authorize/ipbinds/" + userId,
            contentId: "detail"
        };
        $("#currentUser").text(userName);
        this.render(args);
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
            var form = $('#' + args.form);
            if (form.length > 0) {
                args.data = form.serialize();
                args.tpl = args.tpl || config.listTpl;
                return this.render(args).then(function (data) {
                    new Table({
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
                // 查询这个节点的角色
                this.getIpBinds(nodeObj.id, nodeObj.name);
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
    },

    bindIp: function bindIp() {
        var ipAddress = $("#ip_address").val();
        if (ipAddress == "" || (typeof ipAddress === 'undefined' ? 'undefined' : _typeof(ipAddress)) == undefined) {
            lay.msg("请输入IP地址");
            return;
        }
        var zTree = $.fn.zTree.getZTreeObj(this.options.leftTreeId);
        var checkedNode = zTree.getSelectedNodes();
        var userid = checkedNode[0].id;
        var mac = $("#mac").val();
        var data = {};
        data.ipAddress = ipAddress;
        data.mac = mac;
        data.userid = userid;
        var url = "/system/core/authorize/bindip";
        $.ajax({
            cache: false,
            data: data,
            type: "POST",
            url: url,
            dataType: "json",
            success: function (data) {}.bind(this)
        });
    }
}));

/***/ }),

/***/ 291:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./detail.html": 292,
	"./index.html": 293
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
webpackContext.id = 291;

/***/ }),

/***/ 292:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $escape = $imports.$escape;
    $$out += '<div class="row">\r\n    <div class="col-sm-12">\r\n        <div class="box-body">\r\n            <form id="submitForm" role="form" class="form-horizontal">\r\n                <div class="box-body">\r\n                    <div class="form-group">\r\n                        <label class="">&nbsp;IP地址</label>\r\n                        <input type="text" name="ip_address" id="ip_address" placeholder="多个请用逗号分割"  class="form-control"\r\n                               ';
    if (data.user && data.user.ipAddress) {
        $$out += '\r\n                               value="';
        $$out += $escape(data.user.ipAddress);
        $$out += '"\r\n                               ';
    }
    $$out += ' />\r\n                    </div>\r\n                    <!--\r\n                    <div class="form-group " style="margin-top:10px;">\r\n                        <label>&nbsp;MAC地址</label>\r\n                        <input type="text" name="mac" id="mac"  placeholder="多个请用逗号分割" class="form-control"\r\n                               ';
    if (data.user && data.user.ipAddress) {
        $$out += '\r\n                               value="';
        $$out += $escape(data.user.mac);
        $$out += '"\r\n                               ';
    }
    $$out += ' />\r\n                    </div>\r\n                    -->\r\n                </div>\r\n            </form>\r\n        </div>\r\n    </div>\r\n</div>\r\n';
    return $$out;
};

/***/ }),

/***/ 293:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图表样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n\r\n<section class="content-header">\r\n    <h1>授权管理\r\n        <small>用户IP地址绑定</small>\r\n    </h1>\r\n\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <form id="searchForm">\r\n            <!--左侧树结构部分-->\r\n            <div class="col-sm-2">\r\n                <div style="min-height: 600px">\r\n                    <div class="input-group">\r\n                        <input type="text" id="txtSearch" class="form-control" placeholder="请输入用户名称"/>\r\n                        <span class="input-group-btn">\r\n                          <button id="btnSearch" type="button" class="btn  btn-primary"\r\n                                  e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchZtree">\r\n                              <i class="fa fa-search"></i>\r\n                          </button>\r\n                        </span>\r\n                    </div>\r\n\r\n                    <!--<div class="box" style="min-height: 600px;margin-top: 10px">\r\n                        <div class=""style="overflow-x: auto">\r\n                            <ul id="orguserTree" class="ztree" style=" min-height: 585px"></ul>\r\n                        </div>\r\n                    </div>-->\r\n                    <div class="box" style="margin-top: 10px">\r\n                        <div>\r\n                            <ul id="orguserTree" class="ztree" style="max-height: 550px; overflow: auto;height:800px;"></ul>\r\n                        </div>\r\n                    </div>\r\n\r\n                </div>\r\n            </div>\r\n            <!--右侧列表部分-->\r\n            <div class="col-sm-10">\r\n                <div class="box">\r\n                    <div class="box-header">\r\n                        <input id="currentUserId" value="" name="currentUserId" hidden>\r\n                        为用户&nbsp;<span class="color-blue" style="color: #1E5E9C;" id="currentUser"></span>&nbsp;绑定IP\r\n                    </div>\r\n                    <div class="box-body">\r\n                        <div id="detail">\r\n                        </div>\r\n                        <div class="row-fluid">\r\n                            <div class="box-footer col-sm-12">\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindIp"\r\n                                        class="btn btn-instagram" id="save_btn" type="button" title="保存设置">\r\n                                    <i class="fa fa-floppy-o"></i>&nbsp;保存设置\r\n                                </button>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ })

});