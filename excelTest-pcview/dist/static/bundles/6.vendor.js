webpackJsonpvendor([6],{

/***/ 254:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_components_ComponentManager__ = __webpack_require__(26);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ezdev_pcview_cache_CacheUtils__ = __webpack_require__(8);
var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

/**
 * Created by zhaofs on 2017/5/6.
 * 演示Handler应用
 */



/* harmony default export */ __webpack_exports__["default"] = (new __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__["default"]({
    resolveTpl: function resolveTpl(tpl) {
        return __webpack_require__(335)("./" + tpl + ".html");
    },
    /*
    * 界面树的相关参数
    * */
    initHandler: function initHandler() {
        this.options = {
            treeId: 'tree_id',
            searchFormId: 'form',
            saveFormId: 'saveForm',
            leftId: 'leftcontent',
            detailId: 'orgDetail',
            findTree: "system/core/organization/list",
            detailTree: "system/core/organization/get/",
            lastRowNum: 0
        };
    },
    /**
     * 初始化机构树
     */
    initTree: function initTree() {

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
                    var nodeid = treeNode.id;
                    this.triggerOperation(nodeid);
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
            url: this.options.findTree,
            dataType: "json",
            success: function (data) {
                $.fn.zTree.init($('#' + this.options.treeId), tree_setting, data.data);
                var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
                var node = zTree.getNodeByParam("id", "root");
                zTree.selectNode(node, false);
                var nodes = zTree.getSelectedNodes();
                var nodeid = nodes[0].id;
                this.triggerOperation(nodeid);
                //zTree.expandAll(true);
                zTree.expandNode(node, true, false, false); //默认展开第一级
            }.bind(this)
        });
    },
    /*
    * 查找
    * */
    searchZtree: function searchZtree(element) {
        var treeId = "tree_id";
        var searchConditionId = "txtSearch";
        // this.searchByName(treeId, searchConditionId, "");
        var searchCondition = $('#' + searchConditionId).val();
        var searchObj = {};
        searchObj.treeId = treeId;
        searchObj.condition = searchCondition;
        var nodeObj = this.treeSearchAndExpand(searchObj);
        if (nodeObj) {
            if (nodeObj.id) {
                this.triggerOperation(nodeObj.id, nodeObj.type);
            } else {
                layer.msg("\u6CA1\u6709\u67E5\u8BE2\u5230\u6307\u5B9A\u7684\u7EC4\u7EC7\uFF01");
            }
        }
    },
    /**
     * 点击树的节点查看机构详情
     */
    triggerOperation: function triggerOperation(nodeid) {
        var args = {
            tpl: "detail",
            url: "/system/core/organization/get/" + nodeid,
            contentId: "list"
        };
        this.details(args); //查看详情页面^_^
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
    /*
    * 增删改操作相关
    *
    * */
    /*
    *add方法调用完毕后，添加父节点信息
    * */
    onAdded: function onAdded(args) {
        /*取选中的节点的信息，新建操作中，这个选中的节点就是父节点！-start*/
        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
        var nodes = zTree.getSelectedNodes();
        var nodeid = nodes[0].id;
        var nodename = nodes[0].name;
        var nodeType = nodes[0].orgType;
        var orgTreeLevel = nodes[0].orgTreeLevel;
        var nodeOrgCode = nodes[0].orgCode;
        /*取选中的节点的信息，新建操作中，这个选中的节点就是父节点！-end*/
        //add_orgTreeLevel  add_parentId  add_parentName  add_orgCode --> ajaxurl
        //add.html页面赋值
        $("#add_orgTreeLevel").val((parseInt(orgTreeLevel) + 1).toString());
        $("#add_parentId").val(nodeid);
        $("#add_parentName").val(nodename);
        $("#add_orgCode").attr("ajaxurl", function (index, old) {
            var parentidIndex = old.indexOf("parentid=");
            var pp = old.substring(0, parentidIndex + 9);
            var parentID = pp + nodeid;
            return parentID;
        });

        //$("#").val();
    },
    /*在触发submit 的元素 e-event 中添加
    * 提交后触发
    *
    * */
    onSubmited: function onSubmited() {
        var errSize = $(".has-error").length;
        if (errSize < 1) {
            //添加完毕后，重新展开树
            this.initTree();
            // 选中编辑的数据
            var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
            var orgCode = $("#add_orgCode").val(); //【add】页面
            var orgCode_edit = $("#orgCode").val(); //【edit】页面
            if (orgCode == undefined) {
                orgCode = orgCode_edit;
            }
            var node = zTree.getNodeByParam("orgCode", orgCode);var node = zTree.getNodeByParam("orgCode", orgCode);
            zTree.selectNode(node, false); //选择节点
            this.triggerOperation(node.id); //查看详情
        }
    },
    goBack: function goBack(args) {
        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
        var nodes = zTree.getSelectedNodes();
        var nodeid = nodes[0].id;
        zTree.selectNode(nodes[0], false); //选择节点
        this.triggerOperation(nodeid);
    },
    /*
    * 删除组织机构重写dialog
    * 20170925
    * */
    /**
     * 弹出信息框
     * @param args
     * {dialog:confirm,type:post-(默认),isReload:true-(默认),url:/demo/mybatisuser/pages-(可选)}
     */
    dialog: function dialog(args) {
        var promise = null;
        switch (args.dialog) {
            case 'confirm':
                layer.confirm('你确定要执行此操作吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    layer.closeAll();
                    promise = this.ajaxResource(args).then(function (data) {
                        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
                        var nodes = zTree.getSelectedNodes();
                        var parentNode = nodes[0].getParentNode();
                        var nodeid = parentNode.id;
                        this.initTree();
                        zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
                        var node = zTree.getNodeByParam("id", nodeid);
                        zTree.selectNode(node, false); //选择节点
                        zTree.expandNode(node, true, false, false);
                        this.triggerOperation(node.id); //查看详情
                    }.bind(this));
                }.bind(this), function () {
                    layer.closeAll();
                });
                break;
            default:
                //设置shade,zIndex解决附件上传对话框弹出遮罩层的问题，修改人lvhuwei
                var layerId = layer.open({
                    shade: 0,
                    zIndex: 1,
                    type: 1,
                    move: args.move,
                    title: args.title || '信息',
                    area: [args.width || '800px', args.height || '600px'] //宽高
                });
                args.contentId = $('#layui-layer' + layerId).find('.layui-layer-content');
                promise = this.render(args);
                break;
        }
        return promise;
    },

    /**
     * 点击领导设定按钮
     * @param args
     */
    leaderset: function leaderset(args) {
        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
        var nodes = zTree.getSelectedNodes();
        var selectedId = "";
        if (nodes.length > 0) {
            var node = nodes[0];
            selectedId = node.id;
        }
        var url = "system/organization/leader/set/" + selectedId;
        args.tpl = "leaderset";
        args.contentId = "list";
        args.url = url;
        args.data = { orgId: selectedId };
        this.render(args);
    },
    /**
     * 保存领导设置
     * @param args
     */
    saveleaderset: function saveleaderset(args) {
        var agencyLeader = [];
        var parentLeader = [];
        var leaderType = [];
        var leaderTypeName = [];
        var parentLeaderType = [];
        var agencyLeaderComps = $("[name='leader_agency']");
        var parentLeaderComps = $("[name='leader_parent']");
        var leaderTypeComps = $("[name='leaderType']");
        var parentLeaderTypeComps = $("[name='parentLeaderType']");
        for (var i = 0; i < agencyLeaderComps.length; i++) {
            // 尚未加校验
            var agencyLeaderTmp = $(agencyLeaderComps[i]).val();
            var parentLeaderTmp = $(parentLeaderComps[i]).val();
            var leaderTypeTmp = $(leaderTypeComps[i]).val();
            var parentLeaderTypeTmp = $(parentLeaderTypeComps[i]).val();
            if (parentLeaderTypeTmp == "" || (typeof parentLeaderTypeTmp === "undefined" ? "undefined" : _typeof(parentLeaderTypeTmp)) == undefined) {
                parentLeaderTypeTmp = "";
            }
            if (agencyLeaderTmp == "" || (typeof agencyLeaderTmp === "undefined" ? "undefined" : _typeof(agencyLeaderTmp)) == undefined) {
                layer.msg("第" + (i + 1) + "行 本级领导未选择，请选择");
                return false;
            } else {
                agencyLeader.push(agencyLeaderTmp);
            }
            if (parentLeaderTmp == "" || (typeof parentLeaderTmp === "undefined" ? "undefined" : _typeof(parentLeaderTmp)) == undefined) {
                parentLeader.push(" ");
            } else {
                parentLeader.push(parentLeaderTmp);
            }
            if (leaderTypeTmp == "" || (typeof leaderTypeTmp === "undefined" ? "undefined" : _typeof(leaderTypeTmp)) == undefined) {
                layer.msg("第" + (i + 1) + "行 岗位未选择，请选择");
                return false;
            } else {
                leaderType.push(leaderTypeTmp);
                leaderTypeName.push($(leaderTypeComps[i]).find("option:selected").text());
            }
            parentLeaderType.push(parentLeaderTypeTmp);
        }
        var url = "system/organization/leader/save";
        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
        var nodes = zTree.getSelectedNodes();
        var selectedId = "";
        if (nodes.length > 0) {
            var node = nodes[0];
            selectedId = node.id;
        }

        var data = {};
        data.agency = agencyLeader.toString();
        data.parent = parentLeader.toString();
        data.leaderType = leaderType.toString();
        data.leaderTypeName = leaderTypeName.toString();
        data.parentLeaderType = parentLeaderType.toString();
        data.orgId = selectedId;
        $.ajax({
            cache: false,
            url: url,
            type: "POST",
            dataType: "json",
            sync: false,
            data: data,
            success: function (data) {
                this.triggerOperation(selectedId);
            }.bind(this),
            error: function error(XMLHttpRequest, _error, exception) {}
        });
    },
    /**
     * 增加一行
     * @param args
     */
    addleader: function addleader(args) {
        var currentRow = this.resetRowId() + 1;
        //let currentRow = this.options.lastRowNum+=1;
        var addStr = "<tr id=\"leader_" + currentRow + "\"></tr>";
        var options = Object.assign({
            contentId: "leader_" + currentRow,
            data: { rows: currentRow }
        }, args);
        $("#leaderset").append(addStr);
        this.render(options);
    },
    resetRowId: function resetRowId() {
        var res = void 0;
        var rows = $("#leaderset").find("tr");
        if (rows.length > 1) {
            for (var i = 1; i < rows.length; i++) {
                var obj = rows.get(i);
                var id_ = $(obj).attr("id");
                console.log("id is :" + id_);
                var temp_id = id_.split("_")[1];
                if (i == 1) {
                    res = temp_id;
                } else {
                    if (temp_id > res) {
                        res = temp_id;
                    }
                }
            }
        }
        return res;
    },
    /**
     * 删除一行
     * @param args
     */
    delRow: function delRow(args) {
        var rows = $("#leaderset tbody").find("tr").length;
        if (rows == 1) {
            layer.msg("已是最后一行，请直接编辑");
            return false;
        }
        var id = "leader_" + args.row;
        $("#" + id).remove();
    },

    leaderSelect: function leaderSelect(args) {
        var selected = args.data;
        // 选中人员账号
        var account = selected.account;
        var index = args.index;
        var id = "leader_type_" + index;
        this.renderLeaderType(id, account);
    },

    renderLeaderType: function renderLeaderType(id, account) {
        var select = __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_components_ComponentManager__["default"].getComObjectByDomId(id);
        select.updateArgs({ url: '/system/core/user/queryposts/' + account });
        select.refresh();
    },

    test1: function test1(args) {
        var url = "/test/wangzhen";
        var data = { "account": "dept_A_general" };
        $.ajax({
            cache: false,
            url: url,
            type: "GET",
            dataType: "json",
            sync: false,
            data: data,
            success: function (data) {
                //this.triggerOperation(selectedId);
            }.bind(this),
            error: function error(XMLHttpRequest, _error2, exception) {}
        });
    },
    test: function test(args) {
        var data = args.data;
        for (var i in data) {
            alert(i + " : " + data[i]);
        }
    },

    addParentLeaderType: function addParentLeaderType(args) {
        var data = args.data;
        var postId = data.postId;
        var index = args.index;
        $("#leader_parent_" + index + "_hid").after("<input type='hidden' name='parentLeaderType' value='" + postId + "' id='parent_leader_posttype_" + index + "'/>");
    }

}));

/***/ }),

/***/ 335:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./add.html": 336,
	"./addrow.html": 337,
	"./detail.html": 338,
	"./edit.html": 339,
	"./index.html": 340,
	"./leaderset.html": 341
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
webpackContext.id = 335;

/***/ }),

/***/ 336:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        新建机构\r\n        <small>新建机构</small>\r\n    </h1>\r\n</section>\r\n\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-xs-12" style="max-height: 600px; overflow: auto;">\r\n                <div class="box-body ">\r\n                    <form id="submitForm" role="form">\r\n                        <input type="hidden" name="id" value=""><!--机构ID-->\r\n                        <input type="hidden" id="add_orgTreeLevel" name="orgTreeLevel" value="">\r\n                        <div class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;上级机构</label>\r\n                            <input type="hidden" id="add_parentId" name="parentId" value=""><!--上级机构id-->\r\n                            <input type="text" name="parentName" id="add_parentName" disabled="disabled" value=""\r\n                                   datatype="s1-50" class="form-control" placeholder="上级机构名称"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;机构编码</label>\r\n                            <input type="text" name="orgCode" id="add_orgCode" datatype="/^[0-9A-Za-z_]{1,20}$/" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"\r\n                                   ajaxurl="/system/core/organization/checkorg?parentid=" errormsg="请填写1到20位英文\u3001数字和下划线\uFF01"  class="form-control"\r\n                                   placeholder="请输入机构编码"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <!-- <div class="form-group col-sm-6"><span><p></p></span></div>-->\r\n                        <div style="margin:0 auto;" class="form-group ">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;机构名称</label>\r\n                            <input type="text" name="orgName" class="form-control" id="orgName"\r\n                                   value="" datatype="s1-20" errormsg="请填写1到20位中文\u3001英文\u3001数字和下划线\uFF01" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"  placeholder="请输入机构名称">\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div style="margin:0 auto;" class="form-group">\r\n                            <label><i class="fa"></i>&nbsp;排序编号</label>\r\n                            <input type="text" name="sortNo" class="form-control" id="sortNo"\r\n                                   ignore="ignore" value="" placeholder="请输入排序编号" datatype="n0-4" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')">\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div style="margin:0 auto;" class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;机构描述</label>\r\n                            <textarea rows="5" cols="80" name="orgRemark" class="form-control"\r\n                                      id="orgRemark" ignore="ignore" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')" placeholder="请输入机构描述" datatype="*0-200"></textarea>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="box-footer col-sm-12">\r\n                            <!--href:/system/dictionary/search?url=/system/dictionary/pages-->\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/submit?type=post&url=/system/core/organization/save&contentType=application/x-www-form-urlencoded&jump=noGoBack&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="process_btn" type="button" title="保存信息"\r\n                                    >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn"\r\n                                    type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n\r\n                    </form>\r\n                </div>\r\n                <!-- /.box-body -->\r\n\r\n            <!--</div>-->\r\n            <!-- /.box -->\r\n        </div>\r\n        <!-- /.col -->\r\n    </div>\r\n\r\n    <!-- Your Page Content Here -->\r\n\r\n</section>\r\n<script>\r\n\r\n  /*  function fTrim(str)\r\n    {\r\n        alert(str);\r\n        str=str.replace(/(^\\s*)|(\\s*$)/g, "");\r\n        alert(str);\r\n        return str;\r\n    }*/\r\n\r\n<!-- /.content -->\r\n</script>';
    return $$out;
};

/***/ }),

/***/ 337:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, rows = $data.rows;
    $$out += '<td>\r\n    <e-poptreelist id="leader_agency_';
    $$out += $escape(rows);
    $$out += '" name="leader_agency"\r\n                   e-tree-url="/system/organization/leader/list"\r\n                   e-list-url="/system/core/user/leader/list"\r\n                   e-option-value="account" e-option-text="name"\r\n                   e-option-text-title="名称"  e-title="选择本级领导"\r\n                   e-data-id="id" e-data-parent-id="pId"\r\n                   e-display-columns="account,name,orgName"\r\n                   e-display-columnnames="登录账户,用户名称,所属机构"\r\n                   e-chkbox-type="1"\r\n                   e-expand-level="0"\r\n                   e-width="1000px" e-height="650px" e-com-height="600px"\r\n                   e-query-param = "search_LIKE_name"\r\n                   e-query-param-name = "用户名称"\r\n                   e-z-index="99"\r\n                   e-query-tree-list-param = "id:search_LIKE_organization.orgPath"\r\n                   e-on-selected="/system/organization/leaderSelect?index=';
    $$out += $escape(rows);
    $$out += '"\r\n    >\r\n    </e-poptreelist>\r\n</td>\r\n<td>\r\n    <e-poptreelist id="leader_parent_';
    $$out += $escape(rows);
    $$out += '"name="leader_parent"\r\n                   e-tree-url="/system/core/post/list"\r\n                   e-list-url="/system/core/user/query"\r\n                   e-option-value="account" e-option-text="name"\r\n                   e-option-text-title="名称"  e-title="选择上级领导"\r\n                   e-data-id="id" e-data-parent-id="pId"\r\n                   e-display-columns="account,name"\r\n                   e-display-columnnames="登录账户,用户名称"\r\n                   e-chkbox-type="1"\r\n                   e-expand-level="0"\r\n                   e-z-index="99"\r\n                   e-width="1000px" e-height="650px" e-com-height="600px"\r\n                   e-query-param = "search_LIKE_name"\r\n                   e-query-param-name = "用户名称"\r\n                   e-query-tree-list-param = "code:postId"\r\n                   e-on-selected="/system/organization/addParentLeaderType?index=';
    $$out += $escape(rows);
    $$out += '"\r\n    >\r\n    </e-poptreelist>\r\n</td>\r\n<td>\r\n    <e-select id="leader_type_';
    $$out += $escape(rows);
    $$out += '" datatype="*" class="form-control" name="leaderType" e-option-value="code" e-option-text="name" e-url="" e-type="get" datatype="*">\r\n    </e-select>\r\n</td>\r\n<td align="center">\r\n    <button e-permission="core_user_edit" type="button" class="btn btn-default" e-event="href:/system/organization/delRow?row=';
    $$out += $escape(rows);
    $$out += '"><i class="fa fa-remove"></i> 删除</button>\r\n</td>';
    return $$out;
};

/***/ }),

/***/ 338:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n\t<h1>\r\n\t\t机构详情\r\n\t\t<!--<small>机构详情</small>-->\r\n\r\n\t</h1>\r\n\r\n</section>\r\n\r\n<!-- Main content -->\r\n\r\n<section class="content">\r\n\t<div class="row">\r\n\t\t<div class="col-md-12">\r\n\t\t\t<!--<div class="box">-->\r\n            ';
    if (data.org.orgCode != 'root') {
        $$out += '\r\n\t\t\t<div class="inline pull-right"style="margin-right: 10px;">\r\n\t\t\t\t<button type="button" class="btn btn-danger btn-block"\r\n\t\t\t\t\t\te-event="href:/';
        $$out += $escape(path);
        $$out += '/dialog?dialog=confirm&type=get&url=/system/core/organization/delete/';
        $$out += $escape(data.org.id);
        $$out += '"\r\n\t\t\t\t\t\te-permission="core_organization_delete"\r\n\t\t\t\t\t\t';
        if (data.org.id == '0') {
            $$out += ' disabled="disabled"';
        }
        $$out += '>\r\n\t\t\t\t<i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删除\r\n\t\t\t\t</button>\r\n\t\t\t</div>\r\n            ';
    }
    $$out += '\r\n\t\t\t<div class="inline pull-right"style="margin-right: 10px;">\r\n\t\t\t\t<button type="button" class="btn btn-primary btn-block"\r\n\t\t\t\t\t\te-event="href:/';
    $$out += $escape(path);
    $$out += '/edit?url=/system/core/organization/get/';
    $$out += $escape(data.org.id);
    $$out += '&contentId=list"\r\n\t\t\t\t\t\te-permission="core_organization_edit"\r\n\t\t\t\t\t\t';
    if (data.org.id == '0') {
        $$out += ' disabled="disabled"';
    }
    $$out += '>\r\n\t\t\t\t<i class="glyphicon glyphicon-pencil"></i>&nbsp;&nbsp;编辑\r\n\t\t\t\t</button>\r\n\t\t\t</div>\r\n\t\t\t<div class="inline pull-right" style="margin-right: 10px;">\r\n\t\t\t\t<button type="button" class="btn btn-success btn-block"\r\n\t\t\t\t\t\te-event="href:/';
    $$out += $escape(path);
    $$out += '/add?contentId=list"  e-permission="core_organization_create">\r\n\t\t\t\t\t<i class="glyphicon glyphicon-plus"></i>&nbsp;&nbsp;新建\r\n\t\t\t\t</button>\r\n\t\t\t</div>\r\n\t\t\t<div class="inline pull-right" style="margin-right: 10px;">\r\n\t\t\t\t<button type="button" class="btn btn-success btn-block"\r\n\t\t\t\t\t\te-event="href:/';
    $$out += $escape(path);
    $$out += '/leaderset"  e-permission="core_organization_create">\r\n\t\t\t\t\t<i class="fa fa-cog"></i>&nbsp;&nbsp;设置领导\r\n\t\t\t\t</button>\r\n\t\t\t</div>\r\n\t\t\t<!--<div class="inline pull-right" style="margin-right: 10px;">-->\r\n\t\t\t\t<!--<button type="button" class="btn btn-success btn-block"-->\r\n\t\t\t\t\t\t<!--e-event="href:/system/organization/test1"  e-permission="core_organization_create">-->\r\n\t\t\t\t\t<!--<i class="fa fa-cog"></i>&nbsp;&nbsp;测试-->\r\n\t\t\t\t<!--</button>-->\r\n\t\t\t<!--</div>-->\r\n\t\t\t<!--</div>-->\r\n\t\t</div>\r\n\t</div>\r\n\t<div class="row">\r\n\t\t<div class="col-sm-12"><!--style="min-width:520px"-->\r\n\t\t\t<div class="">\r\n\t\t\t\t<!-- <div class="box box-warning  ">\r\n                     <div class="box-header with-border">\r\n                         <h3 class="box-title"></h3>\r\n                     </div>-->\r\n\t\t\t\t<!-- /.box-header -->\r\n\t\t\t\t<div class="box-body">\r\n\r\n\t\t\t\t\t<form id="submitForm" role="form">\r\n\t\t\t\t\t\t<!-- hidden input -->\r\n\t\t\t\t\t\t<!-- <input type="hidden" name="versionNum"  class="form-control" >-->\r\n\t\t\t\t\t\t<input type="hidden" name="id" value="';
    $$out += $escape(data.org.id);
    $$out += '"><!--机构ID-->\r\n\t\t\t\t\t\t<input type="hidden" id="orgTreeLevel" name="orgTreeLevel" value="';
    $$out += $escape(data.org.orgTreeLevel);
    $$out += '">\r\n\t\t\t\t\t\t<!-- text input -->\r\n\t\t\t\t\t\t';
    if (data.org.id != 'root') {
        $$out += '\r\n\t\t\t\t\t\t<div class="form-group ">\r\n\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;上级机构</label>\r\n\t\t\t\t\t\t\t<input type="hidden" id="parentId" name="parentId" value="';
        $$out += $escape(data.org.parentId);
        $$out += '"><!--上级机构id-->\r\n\t\t\t\t\t\t\t<input type="text" name="parentName" id="parentName" disabled="disabled"\r\n\t\t\t\t\t\t\t\t   value="';
        $$out += $escape(data.org.parentName);
        $$out += '" datatype="s1-50" class="form-control"\r\n\t\t\t\t\t\t\t\t   />\r\n\t\t\t\t\t\t\t<span class="help-block"></span>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t';
    }
    $$out += '\r\n\t\t\t\t\t\t<div class="form-group ">\r\n\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;机构编码</label>\r\n\t\t\t\t\t\t\t<!--e-event="href:/system/dictionary/checkDic?type=post&url=/system/core/dictionary/checkdic&parentid=0&id=,event:blur"-->\r\n\t\t\t\t\t\t\t<input type="text" name="orgCode" id="orgCode" datatype="s1-64" disabled="disabled"\r\n\t\t\t\t\t\t\t\t   value="';
    $$out += $escape(data.org.orgCode);
    $$out += '" class="form-control"/>\r\n\t\t\t\t\t\t\t<span class="help-block"></span>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class="form-group col-sm-12"><span><p></p></span></div>\r\n\r\n\t\t\t\t\t\t<div style="margin:0 auto;" class="form-group ">\r\n\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;机构名称</label>\r\n\t\t\t\t\t\t\t<input type="text" name="orgName" class="form-control" id="orgName"\r\n\t\t\t\t\t\t\t\t   value="';
    $$out += $escape(data.org.orgName);
    $$out += '" disabled="disabled" datatype="s1-50">\r\n\t\t\t\t\t\t\t<span class="help-block"></span>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div style="margin:0 auto;" class="form-group ">\r\n\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;排序编号</label>\r\n\t\t\t\t\t\t\t<input type="text" name="sortNo" class="form-control" id="sortNo"\r\n\t\t\t\t\t\t\t\t   ignore="ignore" value="';
    $$out += $escape(data.org.sortNo);
    $$out += '"  disabled="disabled" datatype="n0-4">\r\n\t\t\t\t\t\t\t<span class="help-block"></span>\r\n\t\t\t\t\t\t</div>\r\n\r\n\t\t\t\t\t\t<div class="form-group col-sm-12"><span><p></p></span></div>\r\n\t\t\t\t\t\t<div style="margin:0 auto;" class="form-group ">\r\n\t\t\t\t\t\t\t<label><i class="fa"></i>&nbsp;机构描述</label>\r\n\t\t\t\t\t\t\t<textarea rows="5" cols="80" name="orgRemark" class="form-control"\r\n\t\t\t\t\t\t\t\t\t  id="orgRemark" ignore="ignore" disabled="disabled" datatype="*0-100">';
    $$out += $escape(data.org.orgRemark);
    $$out += '</textarea>\r\n\t\t\t\t\t\t\t<span class="help-block"></span>\r\n\t\t\t\t\t\t</div>\r\n\r\n\t\t\t\t\t\t<!--<div class="box-footer col-sm-12">\r\n\t\t\t\t\t\t&lt;!&ndash;href:/system/dictionary/search?url=/system/dictionary/pages&ndash;&gt;\r\n\t\t\t\t\t\t&lt;!&ndash;<button e-event="href:/system/organization/submit?type=post&url=/system/core/organization/save&contentType=application/x-www-form-urlencoded&jump=noGoBack"\r\n                                class="btn btn-success" id="process_btn" type="button" title="保存信息"\r\n                                e-permission="core_dictionary_submit">\r\n                            <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                        </button>&ndash;&gt;\r\n\t\t\t\t\t\t<button e-event="href:/system/organization/goBack" class="btn btn-primary " id="back_btn"\r\n\t\t\t\t\t\t\t\ttype="button" title="返回">\r\n\t\t\t\t\t\t\t<i class="fa fa-undo"></i>&nbsp;返回\r\n\t\t\t\t\t\t</button>\r\n\t\t\t\t\t</div>-->\r\n\r\n\t\t\t\t\t</form>\r\n\t\t\t\t</div>\r\n\t\t\t\t<!-- /.box-body -->\r\n\t\t\t\t<!-- </div>-->\r\n\r\n\t\t\t</div>\r\n\t\t\t<!-- /.box -->\r\n\t\t</div>\r\n\t\t<!-- /.col -->\r\n\t</div>\r\n\r\n\r\n\t<!-- Your Page Content Here -->\r\n\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ }),

/***/ 339:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        机构编辑\r\n        <small>编辑机构信息</small>\r\n    </h1>\r\n</section>\r\n\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-xs-12" style="max-height: 600px; overflow: auto;">\r\n                <div class="box-body ">\r\n                    <form id="submitForm" role="form">\r\n                        <input type="hidden" name="id" value="';
    $$out += $escape(data.org.id);
    $$out += '"><!--机构ID-->\r\n                        <input type="hidden" id="orgTreeLevel" name="orgTreeLevel" value="';
    $$out += $escape(data.org.orgTreeLevel);
    $$out += '">\r\n                        ';
    if (data.org.id != 'root') {
        $$out += '\r\n                        <div class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;上级机构</label>\r\n                            <input type="hidden" id="parentId" name="parentId" value="';
        $$out += $escape(data.org.parentId);
        $$out += '"><!--上级机构id-->\r\n                            <input type="text" name="parentName" id="parentName" disabled="disabled"\r\n                                   value="';
        $$out += $escape(data.org.parentName);
        $$out += '" datatype="s1-50" class="form-control"\r\n                                   placeholder="上级机构名称"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        ';
    }
    $$out += '\r\n                        <div class="form-group ">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;机构编码</label>\r\n                            <!--e-event="href:/system/dictionary/checkDic?type=post&url=/system/core/dictionary/checkdic&parentid=0&id=,event:blur"-->\r\n                            <input type="text" name="orgCode" id="orgCode" datatype="/^[0-9A-Za-z_]{1,20}$/"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')" readonly="readonly"\r\n                                   value="';
    $$out += $escape(data.org.orgCode);
    $$out += '" class="form-control"\r\n                                   errormsg="请填写1到20位英文\u3001数字和下划线\uFF01"  placeholder="请输入机构编码"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div style="margin:0 auto;" class="form-group ">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;机构名称</label>\r\n                            <input type="text" name="orgName" class="form-control" id="orgName"\r\n                                   value="';
    $$out += $escape(data.org.orgName);
    $$out += '"  placeholder="请输入机构名称" datatype="s1-20"\r\n                                   errormsg="请填写1到20位中文\u3001英文\u3001数字和下划线\uFF01"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')">\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div style="margin:0 auto;" class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;排序编号</label>\r\n                            <input type="text" name="sortNo" class="form-control" id="sortNo"\r\n                                   ignore="ignore" value="';
    $$out += $escape(data.org.sortNo);
    $$out += '"  placeholder="请输入排序编号"\r\n                                   onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')" datatype="n0-4">\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-12"><span><p></p></span></div>\r\n                        <div style="margin:0 auto;" class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;机构描述</label>\r\n                            <textarea rows="5" cols="80" name="orgRemark" class="form-control"\r\n                                      id="orgRemark" ignore="ignore"  placeholder="请输入机构描述"\r\n                                      onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"  datatype="*0-200">';
    $$out += $escape(data.org.orgRemark);
    $$out += '</textarea>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="box-footer col-sm-12">\r\n                            <!--href:/system/dictionary/search?url=/system/dictionary/pages-->\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/submit?type=post&url=/system/core/organization/save&contentType=application/x-www-form-urlencoded&jump=noGoBack&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="process_btn" type="button" title="保存信息"\r\n                                   >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn"\r\n                                    type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n\r\n                    </form>\r\n                </div>\r\n                <!-- /.box-body -->\r\n                <!-- </div>-->\r\n\r\n            <!--</div>-->\r\n            <!-- /.box -->\r\n        </div>\r\n        <!-- /.col -->\r\n    </div>\r\n\r\n\r\n    <!-- Your Page Content Here -->\r\n\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ }),

/***/ 340:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n\r\n<section class="content-header">\r\n    <h1>\r\n        机构管理\r\n        <small>机构管理</small>\r\n    </h1>\r\n</section>\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-md-2">\r\n            <span style="display:none" id="initT" e-event="href:/';
    $$out += $escape(path);
    $$out += '/initTree,auto:true">\r\n                    </span>\r\n            <div class="input-group">\r\n                <input type="text" id="txtSearch" class="form-control" placeholder="请输入机构名称">\r\n                <span class="input-group-btn">\r\n                            <button id="btnSearch" class="btn btn-primary"\r\n                                    e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchZtree">\r\n                                <i class="fa fa-search"></i>\r\n                            </button>\r\n                </span>\r\n            </div>\r\n            <!--<div class="box" style="min-height: 600px;margin-top: 10px">\r\n                <div class="" >\r\n                    <ul id="tree_id" class="ztree" style="overflow-x: auto;min-height: 585px"></ul>\r\n                </div>\r\n            </div>-->\r\n            <div class="box" style="margin-top: 10px">\r\n                <div class="" >\r\n                    <ul id="tree_id" class="ztree" style="max-height: 550px; overflow: auto;height:800px;"></ul>\r\n                </div>\r\n            </div>\r\n        </div>\r\n\r\n        <div class="col-md-10">\r\n            <div class="box" style=""><!--min-height: 645px-->\r\n                <div id="list" class="con-right treeDetail"></div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 341:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, leader = $data.leader, i = $data.i, $escape = $imports.$escape;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        设置领导\r\n        <small>设置领导</small>\r\n    </h1>\r\n</section>\r\n\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-md-12">\r\n            <div class="inline pull-right"style="margin-right: 10px;">\r\n                <button type="button" class="btn btn-success btn-block"\r\n                        e-event="href:/system/organization/addleader?tpl=addrow"\r\n                        e-permission="core_organization_edit"\r\n                        >\r\n                <i class="fa fa-plus"></i>&nbsp;&nbsp;添加领导\r\n                </button>\r\n            </div>\r\n        </div>\r\n    </div>\r\n\r\n    <div class="row">\r\n        <div class="col-md-12">\r\n            <div class="box-body" style="max-height: 520px; overflow: auto;">\r\n                <table class="table table-bordered table-hover no-footer" id="leaderset">\r\n                    <thead>\r\n                        <tr>\r\n                            <th style="text-align:center;">本级领导</th>\r\n                            <th style="text-align:center;">上级领导</th>\r\n                            <th style="text-align:center;">岗位</th>\r\n                            <th style="text-align:center;">操作</th>\r\n                        </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.leaders && data.leaders.length > 0) {
        $$out += '\r\n                        ';
        $each(data.leaders, function (leader, i) {
            $$out += '\r\n                            <tr id="leader_';
            $$out += $escape(i);
            $$out += '">\r\n                                <td>\r\n                                    <e-poptreelist id="leader_agency_';
            $$out += $escape(i);
            $$out += '" name="leader_agency"\r\n                                                   e-tree-url="/system/organization/leader/list"\r\n                                                   e-list-url="/system/core/user/leader/list"\r\n                                                   e-option-value="account" e-option-text="name"\r\n                                                   e-option-text-title="名称"  e-title="选择本级领导"\r\n                                                   e-data-id="id" e-data-parent-id="pId"\r\n                                                   e-display-columns="account,name,orgName"\r\n                                                   e-display-columnnames="登录账户,用户名称,所属机构"\r\n                                                   e-chkbox-type="1"\r\n                                                   e-expand-level="0"\r\n                                                   e-value="';
            $$out += $escape(leader.agencyLeaderAccount);
            $$out += '"\r\n                                                   ';
            if (leader.agencyLeaderAccount) {
                $$out += '\r\n                                                   e-value-name-url="/system/core/user/getByLoginName?loginName=';
                $$out += $escape(leader.agencyLeaderAccount);
                $$out += '"\r\n                                                   ';
            }
            $$out += '\r\n                                                   e-width="1000px"\r\n                                                   e-height="650px"\r\n                                                   e-com-height="500px"\r\n                                                   e-z-index="99"\r\n                                                   e-query-param = "search_LIKE_name"\r\n                                                   e-query-param-name = "用户名称"\r\n                                                   e-query-tree-list-param = "id:search_LIKE_organization.orgPath"\r\n                                                   e-on-check="/system/organization/leaderSelect?index=';
            $$out += $escape(i);
            $$out += '"\r\n                                    >\r\n                                    </e-poptreelist>\r\n                                </td>\r\n                                <td>\r\n                                    <e-poptreelist id="leader_parent_';
            $$out += $escape(i);
            $$out += '" name="leader_parent"\r\n                                                   e-tree-url="/system/core/post/list"\r\n                                                   e-list-url="/system/core/user/query"\r\n                                                   e-option-value="account" e-option-text="name"\r\n                                                   e-option-text-title="名称"  e-title="选择上级领导"\r\n                                                   e-data-id="code" e-data-parent-id="pCode"\r\n                                                   e-display-columns="account,name"\r\n                                                   e-display-columnnames="登录账户,用户名称"\r\n                                                   e-chkbox-type="1"\r\n                                                   e-expand-level="0"\r\n                                                   e-z-index="99"\r\n                                                   e-value="';
            $$out += $escape(leader.parentLeaderAccount);
            $$out += '"\r\n                                                   ';
            if (leader.parentLeaderAccount) {
                $$out += '\r\n                                                   e-value-name-url="/system/core/user/getByLoginName?loginName=';
                $$out += $escape(leader.parentLeaderAccount);
                $$out += '"\r\n                                                    ';
            }
            $$out += '\r\n                                                    e-width="1000px" e-height="650px" e-com-height="600px"\r\n                                                    e-query-param = "search_LIKE_name"\r\n                                                    e-query-param-name = "用户名称"\r\n                                                    e-query-tree-list-param = "code:postId"\r\n                                                    e-on-selected="/system/organization/addParentLeaderType?index=';
            $$out += $escape(i);
            $$out += '"\r\n                                    >\r\n                                    </e-poptreelist>\r\n                                    <input type=\'hidden\' value=\'';
            $$out += $escape(leader.parentLeaderType);
            $$out += '\' name="parentLeaderType"/>\r\n                                </td>\r\n                                <td>\r\n                                    <e-select id="leader_type_';
            $$out += $escape(i);
            $$out += '" class="form-control" name="leaderType"  e-option-value="code" e-option-text="name" e-url="system/core/user/queryposts/';
            $$out += $escape(leader.agencyLeaderAccount);
            $$out += '" e-value=\'';
            $$out += $escape(leader.agencyLeaderType);
            $$out += '\' e-type="get" datatype="*">\r\n                                    </e-select>\r\n                                </td>\r\n                                <td align="center">\r\n                                    <button\r\n                                            e-permission="core_user_edit"\r\n                                            type="button" class="btn btn-default"  e-event="href:/system/organization/delRow?row=';
            $$out += $escape(i);
            $$out += '"><i class="fa fa-remove"></i> 删除\r\n                                    </button>\r\n                                </td>\r\n                            </tr>\r\n                        ';
        });
        $$out += '\r\n                    ';
    } else {
        $$out += '\r\n                        <tr id="leader_0">\r\n                            <td>\r\n                                <e-poptreelist id="leader_agency_0" name="leader_agency"\r\n                                               e-tree-url="/system/organization/leader/list"\r\n                                               e-list-url="/system/core/user/leader/list"\r\n                                               e-option-value="account" e-option-text="name"\r\n                                               e-option-text-title="名称"  e-title="选择本级领导"\r\n                                               e-data-id="id" e-data-parent-id="pId"\r\n                                               e-display-columns="account,name,orgName"\r\n                                               e-display-columnnames="登录账户,用户名称,所属机构"\r\n                                               e-chkbox-type="1"\r\n                                               e-expand-level="0"\r\n                                               e-z-index="99"\r\n                                               e-width="1000px" e-height="650px" e-com-height="500px"\r\n                                               e-query-param = "search_LIKE_name"\r\n                                               e-query-param-name = "用户名称"\r\n                                               e-query-tree-list-param = "id:search_LIKE_organization.orgPath"\r\n                                               e-on-selected="/system/organization/leaderSelect?index=0"\r\n                                >\r\n                                </e-poptreelist>\r\n                            </td>\r\n                            <td>\r\n                                <e-poptreelist id="leader_parent_0" name="leader_parent"\r\n                                               e-tree-url="/system/core/post/list"\r\n                                               e-list-url="/system/core/user/query"\r\n                                               e-option-value="account" e-option-text="name"\r\n                                               e-option-text-title="名称"  e-title="选择上级领导"\r\n                                               e-data-id="id" e-data-parent-id="pId"\r\n                                               e-display-columns="account,name"\r\n                                               e-display-columnnames="登录账户,用户名称"\r\n                                               e-chkbox-type="1"\r\n                                               e-expand-level="0"\r\n                                               e-z-index="99"\r\n                                               e-width="1000px" e-height="650px" e-com-height="600px"\r\n                                               e-query-param = "search_LIKE_name"\r\n                                               e-query-param-name = "用户名称"\r\n                                               e-query-tree-list-param = "code:postId"\r\n                                               e-on-selected="/system/organization/addParentLeaderType?index=0"\r\n                                >\r\n                                </e-poptreelist>\r\n\r\n                            </td>\r\n                            <td>\r\n                                <e-select id="leader_type_0" class="form-control" name="leaderType"  e-option-value="code" e-option-text="name" e-url="" e-value=\'sc\' e-type="get" e-event="event:change,href:/comtest/test" datatype="*">\r\n                                </e-select>\r\n                            </td>\r\n                            <td align="center">\r\n                                <button\r\n                                        e-permission="core_user_edit"\r\n                                        type="button" class="btn btn-default"  e-event="href:/system/organization/delRow?row=0"><i class="fa fa-remove"></i> 删除\r\n                                </button>\r\n                            </td>\r\n                        </tr>\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n\r\n                </table>\r\n            </div>\r\n            <div class="box-footer col-sm-12">\r\n                <button type="button" class="btn btn-success"\r\n                        e-event="href:/system/organization/saveleaderset"  e-permission="core_organization_create">\r\n                    <i class="fa fa-arrow-up"></i>&nbsp;&nbsp;保存设置\r\n                </button>\r\n                <button e-event="href:/system/organization/goBack?goUrl=/system/organization/add?contentId=list" class="btn btn-primary " id="back_btn"\r\n                        type="button" title="返回">\r\n                    <i class="fa fa-undo"></i>&nbsp;返回\r\n                </button>\r\n            </div>\r\n        </div>\r\n        <!-- /.col -->\r\n    </div>\r\n\r\n    <!-- Your Page Content Here -->\r\n\r\n</section>\r\n<script>\r\n\r\n    /*  function fTrim(str)\r\n      {\r\n          alert(str);\r\n          str=str.replace(/(^\\s*)|(\\s*$)/g, "");\r\n          alert(str);\r\n          return str;\r\n      }*/\r\n\r\n    <!-- /.content -->\r\n</script>';
    return $$out;
};

/***/ })

});