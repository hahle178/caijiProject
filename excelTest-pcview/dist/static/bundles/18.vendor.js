webpackJsonpvendor([18],{

/***/ 247:
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
            leftTreeId: 'orguserTree',
            leftTreeUrl: 'system/core/user/orgusertree',
            rightTreeid: 'rescTree',
            rightTreeUrl: 'system/core/resource/list',
            lastIds: ''
        };
        return this;
    },

    resolveTpl: function resolveTpl(tpl) {
        return __webpack_require__(294)("./" + tpl + ".html");
    },

    onIndexed: function onIndexed() {
        this.options1();
        this.initUserTree();

        this.initRescTree();
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
                        this.selUser(treeNode.id, treeNode.name, treeNode.approvalRoleType);
                        // this.searchRoleList();
                    }
                }.bind(this),
                beforeClick: function (treeId, treeNode) {
                    if (treeNode.type == "org") {
                        return false;
                    }
                }.bind(this) /*,
                             view: {
                                fontCss: function (treeId, treeNode) {
                                    return (!!treeNode.highlight) ? {color: "blue", "font-weight": "bold"} : {
                                        color: "#000", "font-weight": "normal"
                                    };
                                }
                             }*/
            } };

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
                var zTree = $.fn.zTree.getZTreeObj(this.options.leftTreeId);
                /* var nodes = zTree.getNodesByParam("type", "user");
                 zTree.selectNode(nodes[0], true);
                 zTree.expandNode(nodes[0].getParentNode(), true, false, false);  //展开选中节点
                 //保存按钮是否显示
                 if (data.data.length == 0) {
                     $("#" + this.options.rightId + " #btnSure").attr('disabled', true);
                 } else {
                     $("#" + this.options.rightId + " #btnSure").attr('disabled', false);
                 }
                 var defaultNode = nodes[0];
                 this.getAllroles(defaultNode.id);//获取全部的角色
                 this.selUser(defaultNode.id,defaultNode.name,defaultNode.approvalRoleType);*/
                if (data.data.length == 0) {
                    $("#" + this.options.rightId + " #btnSure").attr('disabled', true);
                } else {
                    $("#" + this.options.rightId + " #btnSure").attr('disabled', false);
                }
                //获取默认人员的角色并显示
            }.bind(this)
        });
    },
    /**
     * 点击角色，取得授权用户，更新用户tree
     */
    initRescTree: function initRescTree(args) {

        var tree_setting = {
            check: {
                enable: true,
                chkboxType: { "Y": "ps", "N": "s" }
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
                //资源数初始化操作
                $.fn.zTree.init($('#' + this.options.rightTreeid), tree_setting, data.data);
                var zTree = $.fn.zTree.getZTreeObj(this.options.rightTreeid);
                //zTree.expandAll(true);
                var root = zTree.getNodesByFilter(function (node) {
                    return node.level == 0;
                }, true);
                zTree.expandNode(root, true, false, false); //默认展开第一级
                //用户树操作。默认选中第一个用户
                var zTree = $.fn.zTree.getZTreeObj(this.options.leftTreeId);
                var nodes = zTree.getNodesByParam("type", "user");
                zTree.selectNode(nodes[0], true);
                this.selUser(nodes[0].id, nodes[0].name, nodes[0].approvalType);
                zTree.expandNode(nodes[0].getParentNode(), true, false, false); //展开选中节点
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
        /*  alert(approvalType);
          if(approvalType == "1"){
                $("#"+this.options.rightId+" #btnSure").attr('disabled',true);
              $("#"+this.options.rightId+" #btnSure").html('待审批');
            }else{
              $("#"+this.options.rightId+" #btnSure").attr('disabled',false);
              $("#"+this.options.rightId+" #btnSure").html('保存');
          }*/

        $("#" + this.options.rightId + " #hidUserid").val(userid);
        $("#" + this.options.rightId + " #spnCurUser").text(username);
        //$("#"+this.options.rightId+" #hidApprovalType").val(approvalType);
        if (userid) {
            var url = "system/core/user/rescsbyuserid/" + userid;
            $.ajax({
                cache: false,
                async: false, // 同步加载
                type: "GET",
                url: url,
                dataType: "json",
                success: function (data) {
                    var zTree = $.fn.zTree.getZTreeObj(this.options.rightTreeid);
                    zTree.checkAllNodes(false); //用户数选择状态初始化
                    if (data.data.length > 0) {
                        $.each(data.data, function (i, item) {

                            var node = zTree.getNodeByParam("id", item.id);
                            if (node) {
                                node.checked = true;
                                //zTree.updateNode(node,true);//级联选中
                                zTree.updateNode(node);
                                zTree.expandNode(node.getParentNode(), true, false, false); //展开选中节点
                            }
                        }.bind(this));
                    }

                    var chknodes = zTree.getCheckedNodes(true);

                    var nodes = "";
                    $.each(chknodes, function (i, item) {
                        if (item.type != 'root') {
                            nodes += item.id + ",";
                        }
                    });

                    this.options.lastIds = nodes;
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
            this.selUser(nodeObj.id, nodeObj.name, nodeObj.approvalType);
        } else {
            layer.msg('\u6CA1\u6709\u67E5\u8BE2\u5230\u6307\u5B9A\u7684\u7528\u6237\uFF01');
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
     * 保存用户资源绑定信息
     */
    bindRescs: function bindRescs() {
        //选中资源
        var treeObj = $.fn.zTree.getZTreeObj(this.options.rightTreeid);
        var chknodes = treeObj.getCheckedNodes(true);
        var bindnodes = "";
        $.each(chknodes, function (i, item) {
            if (item.type != 'root') {
                bindnodes += item.id + ",";
            }
        });
        if (this.options.lastIds == bindnodes) {
            layer.msg("绑定无变化，不可提交。");
            return;
        }
        //选中用户
        var userid = $("#" + this.options.rightId + " #hidUserid").val();
        if (userid.length == 0) {
            //$('#deleteMore').removeClass('active').attr("disabled", true);
            //TODO:替换为标准提示框
            //alert('请选择绑定的用户');
            layer.msg("请选择绑定的用户！");
            return;
        }
        var param = { "userid": userid, "rescids": bindnodes };
        var url = "system/core/authorize/binduserrescs";
        $.ajax({
            cache: false,
            data: param,
            type: "POST",
            url: url,
            dataType: "json",
            success: function (data) {
                layer.msg("绑定成功！");
                /* 20170925suntf
                this.initUserTree();
                this.initRescTree();
                  */
            }.bind(this)
        });
    },
    bindReset: function bindReset() {
        //默认选中第一个用户
        var rescid = $("#" + this.options.rightId + " #hidUserid").val();
        var approvalType = $("#" + this.options.rightId + " #hidApprovalType").val();
        var rescname = $("#" + this.options.rightId + " #spnCurUser").text();
        //显示用户的绑定角色
        this.selUser(rescid, rescname, approvalType);
    }
}));

/***/ }),

/***/ 294:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./index.html": 295,
	"./list.html": 296
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
webpackContext.id = 294;

/***/ }),

/***/ 295:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n\r\n<section class="content-header">\r\n    <h1>授权管理\r\n        <small>用户资源绑定</small>\r\n    </h1>\r\n\r\n</section>\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <form id="searchForm">\r\n            <!--左侧树结构部分-->\r\n            <div class=col-md-2>\r\n                <div style="min-height: 600px">\r\n                    <div class="input-group">\r\n                        <input type="text" id="txtSearch" class="form-control" placeholder="请输入用户名称"/>\r\n                        <span class="input-group-btn">\r\n                          <button id="btnSearch" type="button" class="btn btn-primary"\r\n                                  e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchZtree">\r\n                              <i class="fa fa-search"></i>\r\n                          </button>\r\n                        </span>\r\n                    </div>\r\n                    <!--<div class="box" style="min-height: 600px;margin-top: 10px">\r\n                        <div class=""style="overflow-x: auto">\r\n                            <ul id="orguserTree" class="ztree" style=" min-height: 585px"></ul>\r\n                        </div>\r\n                    </div>-->\r\n                    <div class="box" style="margin-top: 10px">\r\n                        <div >\r\n                            <ul id="orguserTree" class="ztree" style="max-height: 550px; overflow: auto;height:800px;"></ul>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class="col-md-10"> <!--style="min-height: 645px"-->\r\n                <div class="box" id="rightcontent"><!--style="min-height: 645px"-->\r\n                    <div class="box-header">\r\n                        为用户&nbsp;<span class="color-blue" style="color: #1E5E9C;" id="spnCurUser"></span>&nbsp;绑定资源\r\n                        <input type="hidden" id="hidRoleid">\r\n                    </div>\r\n                    <div class="box-body">\r\n                        <input type="hidden" id="hidUserid">\r\n                        <input type="hidden" id="hidApprovalType">\r\n                        <div class="row-fluid">\r\n                            <div class="zTree-box">\r\n                                <div id="rescTree" class="ztree" style="max-height: 580px; overflow: auto"></div>\r\n                            </div>\r\n                            <div class="box-footer col-sm-12">\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindRescs" class="btn btn-instagram"\r\n                                        id="save_btn" type="button" title="保存"\r\n                                        e-permission="core_authorize_binduserrescs">\r\n                                    <i class="fa fa-floppy-o"></i>&nbsp;保存\r\n                                </button>\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindReset" class="btn btn-primary "\r\n                                        id="back_btn" type="button" title="取消">\r\n                                    <i class="fa fa-mail-reply"></i>&nbsp;取消\r\n                                </button>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 296:
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