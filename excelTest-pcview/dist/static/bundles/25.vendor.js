webpackJsonpvendor([25],{

/***/ 238:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__ = __webpack_require__(18);
var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };



/* harmony default export */ __webpack_exports__["default"] = (new __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__["default"]({
    initHandler: function initHandler() {
        this.options = {
            leftId: 'leftcontent',
            rightId: 'rightcontent',
            postTreeId: 'post_tree',
            postTreeUrl: 'system/core/post/query',
            userTreeId: 'orguserTree',
            userTreeUrl: 'system/core/user/orgusertree'
        };
        return this;
    },
    resolveTpl: function resolveTpl(tpl) {
        return __webpack_require__(264)("./" + tpl + ".html");
    },
    initPostTree: function initPostTree() {
        var config = {
            check: {
                enable: false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "code",
                    pIdKey: "pCode",
                    rootPId: null
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    this.postNodeClick(treeNode.id, treeNode.name);
                }.bind(this),
                beforeClick: function (treeId, treeNode) {
                    if (treeNode.id == "root") {
                        return false;
                    }
                }.bind(this)
            },
            view: {
                fontCss: function fontCss(treeId, treeNode) {
                    return !!treeNode.highlight ? { color: "blue", "font-weight": "bold" } : { color: "#000", "font-weight": "normal" };
                }
            }
        };

        $.ajax({
            cache: false,
            async: false, // 同步加载
            type: "POST",
            url: this.options.postTreeUrl,
            dataType: "json",
            success: function (data) {
                $.fn.zTree.init($('#' + this.options.postTreeId), config, data.data);
                var zTree = $.fn.zTree.getZTreeObj(this.options.postTreeId);
                var nodes = zTree.getNodesByFilter(this.filter);
                // 默认选中第一个非根节点数据
                zTree.selectNode(nodes[0], true);
                zTree.expandNode(nodes[0].getParentNode(), true, false, false); //展开选中节点
                var postId = nodes[0].id;
                var postName = nodes[0].name;
                this.postNodeClick(postId, postName);
            }.bind(this)
        });
    },

    filter: function filter(node) {
        return node.id != 'root';
    },

    /**
     * 初始化用户树tree
     */
    initUserTree: function initUserTree() {
        var config = {
            check: {
                enable: true,
                chkboxType: { "Y": "ps", "N": "ps" }
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        $.ajax({
            cache: false,
            async: false, // 同步加载
            type: "POST",
            url: this.options.userTreeUrl,
            dataType: "json",
            success: function (data) {
                var nodes = data.data;
                $.each(nodes, function (i, item) {
                    if (item.type == 'user') {
                        item.iconSkin = "user";
                    }
                });
                $.fn.zTree.init($('#' + this.options.userTreeId), config, nodes);
                var zTree = $.fn.zTree.getZTreeObj(this.options.userTreeId);
                //zTree.expandAll(true);//全部展开
                var root = zTree.getNodesByFilter(function (node) {
                    return node.level == 0;
                }, true);
                zTree.expandNode(root, true, false, false); //默认展开第一级
            }.bind(this)
        });
    },

    /**
     * 点击岗位树
     */
    postNodeClick: function postNodeClick(postId, postName) {

        // 右侧显示 为 ** 岗位 绑定用户 值设置
        $("#currentPostId").val(postId);
        $("#currentPostName").text(postName);
        if (postId) {
            var url = "system/core/post/usersbinded/" + postId;
            $.ajax({
                cache: false,
                async: false, // 同步加载
                type: "GET",
                url: url,
                dataType: "json",
                success: function (data) {
                    var userTree = $.fn.zTree.getZTreeObj(this.options.userTreeId);
                    userTree.checkAllNodes(false); //用户树选择状态初始化
                    if (data.data.length > 0) {
                        $.each(data.data, function (i, item) {
                            var node = userTree.getNodeByParam("id", item.id);
                            if (node) {
                                node.checked = true;
                                userTree.expandNode(node.getParentNode(), true, false, false); //展开选中节点
                                userTree.updateNode(node, true); //父节点级联选中
                            }
                        }.bind(this));
                    }
                }.bind(this)
            });
        }
    },

    /**
     * 保存资源用户绑定信息
     */
    bindUsers: function bindUsers() {
        //右侧选中用户
        var treeObj = $.fn.zTree.getZTreeObj(this.options.userTreeId);
        var chknodes = treeObj.getCheckedNodes(true);
        var bindnodes = "";
        $.each(chknodes, function (i, item) {
            if (item.type == 'user') {
                bindnodes += item.id + ",";
            }
        });
        //左侧选中岗位
        var postId = $("#currentPostId").val();
        var postName = $("#currentPostName").text();
        if (postId == "" || (typeof postId === 'undefined' ? 'undefined' : _typeof(postId)) == undefined) {
            layer.msg('\u8BF7\u9009\u62E9\u7ED1\u5B9A\u7684\u5C97\u4F4D');
            return;
        }
        var param = { "postId": postId, userids: bindnodes };
        var url = "system/core/authorize/bindpostuser";
        $.ajax({
            cache: false,
            data: param,
            type: "POST",
            url: url,
            dataType: "json",
            success: function (data) {
                this.postNodeClick(postId, postName);
            }.bind(this)
        });
    },
    bindReset: function bindReset() {
        //默认选中第一个资源
        var postId = $("#currentPostId").val();
        var postName = $("#currentPostName").text();

        //显示用户的绑定角色
        this.postNodeClick(postId, postName);
    },
    onIndexed: function onIndexed() {
        this.initHandler();
        this.initUserTree();
        this.initPostTree();
    },
    searchZtree: function searchZtree(element) {
        var treeId = this.options.postTreeId;
        var searchConditionId = "txtSearch";
        var searchCondition = $('#' + searchConditionId).val();
        var searchObj = {};
        searchObj.treeId = treeId;
        // searchObj.type = 'resource,bizsys';
        searchObj.condition = searchCondition;
        //获得选择的那个节点
        var nodeObj = this.treeSearchAndExpand(searchObj);
        if (nodeObj && nodeObj.id) {
            this.postNodeClick(nodeObj.id, nodeObj.name);
            //this.selUser(nodeObj.id, nodeObj.name,nodeObj.approvalType);
        } else {
            layer.msg('\u6CA1\u6709\u67E5\u8BE2\u5230\u6307\u5B9A\u7684\u5C97\u4F4D\uFF01');
        }
    },

    /**
     * 树状列表查询后展示方法
     *
     * @param
     * searchObj,查询条件对象，内部含有treeId和condition属性。treeId树ID，condition是查询条件，树状列表上部查询框提供的节点名称查询条件
     * @param treeId树ID
     * @returns
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

/***/ 264:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./index.html": 265,
	"./list.html": 266
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
webpackContext.id = 264;

/***/ }),

/***/ 265:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n\r\n<section class="content-header">\r\n    <h1>授权管理\r\n        <small>岗位用户绑定</small>\r\n    </h1>\r\n\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <!--左侧树结构部分-->\r\n        <div class="col-md-2">\r\n            <div id="leftcontent">\r\n                <div class="input-group">\r\n                    <input type="text" id="txtSearch" class="form-control" placeholder="请输入岗位名称"/>\r\n                    <span class="input-group-btn">\r\n                          <button id="btnSearch" type="button" class="btn btn-primary"\r\n                                  e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchZtree">\r\n                              <i class="fa fa-search"></i>\r\n                          </button>\r\n                        </span>\r\n                </div>\r\n                <!--<div class="box" style="min-height: 600px;margin-top: 10px">\r\n                    <div class=""style="overflow-x: auto">\r\n                        <ul id="post_tree" class="ztree" style=" min-height: 585px"></ul>\r\n                    </div>\r\n                </div>-->\r\n                <div class="box" style="margin-top: 10px">\r\n                    <div>\r\n                        <ul id="post_tree" class="ztree" style=" max-height: 550px; overflow: auto;height:800px;"></ul>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class="col-md-10">\r\n            <div class="box" id="rightcontent"><!--style="min-height: 645px"-->\r\n                <div class="box-header">\r\n                    为岗位&nbsp;<span class="color-blue" style="color: #1E5E9C;" id="currentPostName"></span>&nbsp;绑定用户\r\n                    <input type="hidden" id="currentPostId">\r\n                </div>\r\n                <div class="box-body">\r\n                    <div class="zTree-box"> <!--style="overflow-x: auto"-->\r\n                        <div id="orguserTree" class="ztree" style="max-height: 580px; overflow: auto"></div>\r\n                    </div>\r\n                    <div class="box-footer col-md-12">\r\n                        <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindUsers" class="btn btn-instagram"\r\n                                id="save_btn" type="button" title="保存"\r\n                                e-permission="core_authorize_bindrescusers">\r\n                            <i class="fa fa-floppy-o"></i>&nbsp;保存\r\n                        </button>\r\n                        <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindReset" class="btn btn-primary "\r\n                                id="back_btn" type="button" title="取消">\r\n                            <i class="fa fa-mail-reply"></i>&nbsp;取消\r\n                        </button>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</section>';
    return $$out;
};

/***/ }),

/***/ 266:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '';
    $$out += '<!DOCTYPE html>\r\n<html lang="en">\r\n<head>\r\n    <meta charset="UTF-8">\r\n    <title>Title</title>\r\n</head>\r\n<body>\r\n\r\n</body>\r\n</html>';
    return $$out;
};

/***/ })

});