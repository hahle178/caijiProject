webpackJsonpvendor([22],{

/***/ 241:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__ = __webpack_require__(18);
/**
 * Created by zhaofs on 2017/5/6.
 * 演示Handler应用
 */


/* harmony default export */ __webpack_exports__["default"] = (new __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__["default"]({
    options1: function options1() {
        this.options = {
            leftId: 'leftcontent',
            rightId: 'rightcontent',
            leftTreeId: 'rescTree',
            leftTreeUrl: 'system/core/resource/list',
            rightTreeId: 'orguserTree',
            rightTreeid: 'orguserTree',
            rightTreeUrl: 'system/core/user/orgusertree',
            lastIds: ''
        };
        return this;
    },

    resolveTpl: function resolveTpl(tpl) {
        return __webpack_require__(273)("./" + tpl + ".html");
    },

    onIndexed: function onIndexed() {
        this.options1();
        this.initRescTree();
        this.initUserTree();
    },

    /**
     * 资源树
     */
    initRescTree: function initRescTree(args) {

        var tree_setting = {
            check: {
                enable: false
                /* chkboxType: { "Y" : "ps", "N" : "ps" }*/
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    //if(treeNode.type=='resource'){
                    this.selResc(treeNode.id, treeNode.name);
                    //}
                }.bind(this),
                beforeClick: function (treeId, treeNode) {
                    //								if(treeNode.type=="bizsys"){
                    //									return false;
                    //								}
                    if (treeNode.type == "root") {
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
            url: this.options.leftTreeUrl,
            dataType: "json",
            success: function (data) {
                $.fn.zTree.init($('#' + this.options.leftTreeId), tree_setting, data.data);
                var zTree = $.fn.zTree.getZTreeObj(this.options.leftTreeId);
                //zTree.expandAll(true);
                if (data.data.length == 0) {
                    $("#" + this.options.rightId + " #btnSure").attr('disabled', true);
                } else {
                    $("#" + this.options.rightId + " #btnSure").attr('disabled', false);
                }
            }.bind(this)
        });
    },
    /**
     * 初始化用户树tree
     */
    initUserTree: function initUserTree() {
        var tree_setting = {
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
            url: this.options.rightTreeUrl,
            dataType: "json",
            success: function (data) {
                var nodes = data.data;
                $.each(nodes, function (i, item) {
                    if (item.type == 'user') {
                        item.iconSkin = "user";
                    }
                    //						if(item.type=='org'){
                    //							item.nocheck=true;
                    //						}
                });
                $.fn.zTree.init($('#' + this.options.rightTreeId), tree_setting, nodes);
                var zTree = $.fn.zTree.getZTreeObj(this.options.rightTreeId);
                //zTree.expandAll(true);//全部展开
                var root = zTree.getNodesByFilter(function (node) {
                    return node.level == 0;
                }, true);
                zTree.expandNode(root, true, false, false); //默认展开第一级

                //默认选中第一个用户
                var rescTree = $.fn.zTree.getZTreeObj(this.options.leftTreeId);
                var nodes = rescTree.getNodesByParam("type", "bizsys");
                rescTree.selectNode(nodes[0], true);
                rescTree.expandNode(nodes[0].getParentNode(), true, false, false);
                //显示用户的绑定角色
                this.selResc(nodes[0].id, nodes[0].name);
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
     * 点击资源，取得授权用户，更新用户树
     */
    selResc: function selResc(rescid, rescname) {
        if (!rescid) {
            $("#" + this.options.rightId + " #btnSure").attr('disabled', true);
        } else {
            $("#" + this.options.rightId + " #btnSure").removeAttr('disabled');
        }
        $("#" + this.options.rightId + " #hidRescid").val(rescid);
        $("#" + this.options.rightId + " #spnCurResc").text(rescname);

        if (rescid) {
            var url = "system/core/resource/usersbyrescid/" + rescid;
            $.ajax({
                cache: false,
                async: false, // 同步加载
                type: "GET",
                url: url,
                dataType: "json",
                success: function (data) {
                    var zTree = $.fn.zTree.getZTreeObj(this.options.rightTreeId);
                    zTree.checkAllNodes(false); //用户数选择状态初始化
                    if (data.data.length > 0) {
                        $.each(data.data, function (i, item) {
                            var node = zTree.getNodeByParam("id", item.id);
                            if (node) {
                                node.checked = true;
                                //										zTree.updateNode(node,true);//父节点级联选中
                                zTree.updateNode(node, true); //父节点级联选中
                                zTree.expandNode(node.getParentNode(), true, false, false); //展开选中节点
                            }
                        }.bind(this));
                    }
                }.bind(this)
            });
        }
    },

    //     searchRoleList: function () {
    //         var data = $("#" + this.options.searchFormId).serialize();
    //         Utils.ajaxByPost({
    //             url: this.options.roleListUrl,
    //             tplUrl: 'system/core/authorize/rolelistbind',
    //             contentId: this.options.roleListId,
    //             data: data,
    //             calback: function (data) {
    //                 //没有数据时处理最大页数
    //                 var maxpage = parseInt(data.data.totalPages);
    //                 var curpage = parseInt(data.data.number) + 1;
    //                 if (maxpage == 0) {
    //                     maxpage = 1;
    //                 }
    //                 //回写最大页数
    //                 $(".pagination").jqPagination("option", {
    //                     max_page: maxpage,
    //                     current_page: curpage,
    //                     trigger: false
    //                 });
    //
    //                 $('#' + this.options.roleListId + "input").iCheck({
    //                     handle: 'checkbox',
    //                     checkboxClass: 'icheckbox_square-blue',
    //                     radioClass: 'iradio_square-blue',
    //                     increaseArea: '20%' // optional
    //                 });
    //
    //
    //                 var rolechk = $('#' + this.options.roleListId + " input:checkbox:not([id='selectAll'])");
    //                 var main = this;
    //                 //显示资源的绑定角色
    //                 if (main.options.map.size() > 0) {
    //                     //设置选中用户的状态
    //                     rolechk.each(function () {
    //                         if (main.options.map.containsKey($(this).attr('objid'))) {
    //                             $(this).iCheck('check');
    //                         }
    //                     });
    //                 }
    //                 //添加checkbox的选中事件，如果选中则加入缓存中
    //                 rolechk.on('ifChecked', function (event) {
    //                     var chk = event.target;
    //                     main.options.map.put($(chk).attr('objid'), $(chk).attr('objname'));
    //                 });
    //                 //添加checkbox的选中事件，如果选中则加入缓存中
    //                 rolechk.on('ifUnchecked', function (event) {
    //                     var chk = event.target;
    //                     main.options.map.remove($(chk).attr('objid'));
    //                 });
    //                 //全选操作
    //                 var selectAllChk = $('#' + this.options.roleListId + " input:checkbox#selectAll");
    //                 selectAllChk.on('ifChecked', function (event) {
    //                     rolechk.iCheck('check');
    // //						  rolechk.each(function(){
    // //							  main.options.map.put($(this).attr('objid'),$(this).attr('objname'));
    // //						  });
    //
    //                 });
    //                 selectAllChk.on('ifUnchecked', function (event) {
    //                     rolechk.iCheck('uncheck');
    // //						  rolechk.each(function(){
    // //							  main.options.map.remove($(this).attr('objid'));
    // //						  });
    //                 });
    //             }.bind(this)
    //         });
    //     },


    searchNodes: function searchNodes() {},

    /**
     * ------------------------------------zTree上部的查询框和查询----------------------------------
     * TODO 获取选择的节点
     * TODO 查询全部的角色
     * TODO 查询这个节点的角色
     */

    /**
     * zTree上部的查询框
     * @param element
     */
    searchZtree: function searchZtree(element) {
        var treeId = this.options.leftTreeId;
        var searchConditionId = "txtSearch";
        var searchCondition = $('#' + searchConditionId).val();
        var searchObj = {};
        searchObj.treeId = treeId;
        searchObj.type = 'resource,bizsys';
        searchObj.condition = searchCondition;
        //获得选择的那个节点
        var nodeObj = this.treeSearchAndExpand(searchObj);
        if (nodeObj && nodeObj.id) {
            this.selResc(nodeObj.id, nodeObj.name);
            //this.selUser(nodeObj.id, nodeObj.name,nodeObj.approvalType);
        } else {
            layer.msg('\u6CA1\u6709\u67E5\u8BE2\u5230\u6307\u5B9A\u7684\u8D44\u6E90\uFF01');
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
    },
    /**
     * 保存资源用户绑定信息
     */
    bindUsers: function bindUsers() {
        //右侧选中用户
        var treeObj = $.fn.zTree.getZTreeObj(this.options.rightTreeId);
        var chknodes = treeObj.getCheckedNodes(true);
        var bindnodes = "";
        $.each(chknodes, function (i, item) {
            if (item.type == 'user') {
                bindnodes += item.id + ",";
            }
        });
        //左侧选中资源
        var rescid = $("#" + this.options.rightId + " #hidRescid").val();
        if (rescid.length == 0) {
            //TODO:替换为标准提示框
            layer.msg('\u8BF7\u9009\u62E9\u7ED1\u5B9A\u7684\u8D44\u6E90');
            //alert('');
            return;
        }
        var param = { "rescid": rescid, userids: bindnodes };
        var url = "system/core/authorize/bindrescusers";
        $.ajax({
            cache: false,
            data: param,
            type: "POST",
            url: url,
            dataType: "json",
            success: function (data) {}.bind(this)
        });
        //			var url="core/resource/bindusers/"+rescid+"/"+bindnodes;
        //			$.ajax({
        //				cache : false,
        //				type : "GET",
        //				url : url,
        //				dataType : "json",
        //				success : function(data) {
        //
        //				}.bind(this)
        //			});
    },
    bindReset: function bindReset() {
        //			//默认选中第一个资源
        var rescid = $("#" + this.options.rightId + " #hidRescid").val();
        var rescname = $("#" + this.options.rightId + " #spnCurResc").text();
        //显示用户的绑定角色
        this.selResc(rescid, rescname);
    }
}));

/***/ }),

/***/ 273:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./index.html": 274,
	"./list.html": 275
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
webpackContext.id = 273;

/***/ }),

/***/ 274:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n\r\n<section class="content-header">\r\n    <h1>授权管理\r\n        <small>资源用户绑定</small>\r\n    </h1>\r\n\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <!--左侧树结构部分-->\r\n        <div class="col-md-2">\r\n            <div id="leftcontent">\r\n                <div class="input-group">\r\n                    <input type="text" id="txtSearch" class="form-control" placeholder="请输入资源名称"/>\r\n                    <span class="input-group-btn">\r\n                          <button id="btnSearch" type="button" class="btn btn-primary"\r\n                                  e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchZtree">\r\n                              <i class="fa fa-search"></i>\r\n                          </button>\r\n                        </span>\r\n                </div>\r\n                <!--<div class="box" style="min-height: 600px;margin-top: 10px">\r\n                    <div class=""style="overflow-x: auto">\r\n                        <ul id="rescTree" class="ztree" style=" min-height: 585px"></ul>\r\n                    </div>\r\n                </div>-->\r\n                <div class="box" style="margin-top: 10px">\r\n                    <div class="">\r\n                        <ul id="rescTree" class="ztree" style="max-height: 550px; overflow: auto;height:800px;"></ul>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class="col-md-10">\r\n            <div class="box" id="rightcontent"> <!--style="min-height: 645px"-->\r\n                <div class="box-header">\r\n                    为资源&nbsp;<span class="color-blue" style="color: #1E5E9C;" id="spnCurResc"></span>&nbsp;绑定用户\r\n                    <input type="hidden" id="hidRescid">\r\n                </div>\r\n                <div class="box-body">\r\n                    <div class="zTree-box">\r\n                        <div id="orguserTree" class="ztree" style="max-height: 580px; overflow-x: auto"></div>\r\n                    </div>\r\n                    <div class="box-footer col-md-12">\r\n                        <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindUsers" class="btn btn-instagram"\r\n                                id="save_btn" type="button" title="保存"\r\n                                e-permission="core_authorize_bindrescusers">\r\n                            <i class="fa fa-floppy-o"></i>&nbsp;保存\r\n                        </button>\r\n                        <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindReset" class="btn btn-primary "\r\n                                id="back_btn" type="button" title="取消">\r\n                            <i class="fa fa-mail-reply"></i>&nbsp;取消\r\n                        </button>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <!--右侧列表部分-->\r\n        <!--<div class="col-xs-10">\r\n            <div class="box">\r\n                <div class="box-body">\r\n                    <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                        为用户<span class="color-blue" id="spnCurUser"></span>绑定角色\r\n                        <div class="con-bottom-left zTree-box">\r\n                            <ul id="rescTree" class="ztree"></ul>\r\n                        </div>\r\n                    </div>\r\n                    <div class="col-sm-1">\r\n                        <button id="btnSure" type="button" class="btn btn-block btn-success"\r\n                                e-event="href:/system/authorize/userresc/bindRescs">保存\r\n                        </button>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>-->\r\n\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 275:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, $escape = $imports.$escape;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-6"></div>\r\n            <div class="col-sm-6"></div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-sm-12">\r\n                <table id="example2" class="table table-bordered table-hover dataTable no-footer"\r\n                       role="grid" aria-describedby="example2_info">\r\n                    <thead>\r\n                    <tr>\r\n                        <th style="width:50px;"><input type="checkbox" id="selectAll"></th>\r\n                        <th style="width:170px;" class="leftth">角色名称</th>\r\n                        <th style="width:80px;">角色描述</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.totalSize == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd" colspan="2">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n                        <td><input type="checkbox" sign="checkbox" id="';
            $$out += $escape(item.id);
            $$out += '" value="';
            $$out += $escape(item.id);
            $$out += '"></td>\r\n                        <td>';
            $$out += $escape(item.name);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.remark);
            $$out += '</td>\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n\r\n            </div>\r\n        </div>\r\n        <div class="row" id="pager">\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ })

});