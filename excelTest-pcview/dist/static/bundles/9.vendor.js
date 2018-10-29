webpackJsonpvendor([9],{

/***/ 255:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_components_ComponentManager__ = __webpack_require__(26);


/* harmony default export */ __webpack_exports__["default"] = (new __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_Handler__["default"]({

    initHandler: function initHandler() {
        this.options = {
            treeId: "tree_id",
            contentId: "list",
            findTree: "/system/core/post/query"
        };
    },

    resolveTpl: function resolveTpl(tpl) {
        return __webpack_require__(342)("./" + tpl + ".html");
    },

    initTree: function initTree() {
        var treeConfig = {
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
                // 初始化树组件
                $.fn.zTree.init($('#' + this.options.treeId), treeConfig, data.data);
                // 选中树的第一级的第一个节点
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
    onIndexed: function onIndexed() {
        this.initHandler();
        this.initTree();
        //select.reWriteHtml(dataArgs);
    },
    onEdited: function onEdited() {
        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
        var selectedNodes = zTree.getSelectedNodes();
        var selectedNode = selectedNodes[0];
        if (selectedNode.id != 'root') {
            var parentLevel = selectedNode.getParentNode().level;
            this.renderLeaderLevel("level", parentLevel);
        }
    },
    triggerOperation: function triggerOperation(nodeid) {
        var args = {
            tpl: "detail",
            url: "/system/core/post/get/" + nodeid,
            contentId: "list"
        };
        this.details(args); //查看详情页面^_^
    },

    /**
     * 跳转到新增页面后的初始化方法
     *
     */
    onAdded: function onAdded() {
        /*取选中的节点的信息，新建操作中，这个选中的节点就是父节点！-start*/
        var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
        var selectedNodes = zTree.getSelectedNodes();
        var selectedNode = selectedNodes[0];
        var nodeName = selectedNode.name;
        var nodeCode = selectedNode.code;
        var parentLevel = selectedNode.level;
        /*取选中的节点的信息，新建操作中，这个选中的节点就是父节点！-end*/
        //add_orgTreeLevel  add_parentId  add_parentName  add_orgCode --> ajaxurl
        //add.html页面赋值
        var url = $("#level").attr("e-url") + "&parentLevel=" + parentLevel;
        $("#level").attr("e-url", url);

        $("#parentCode").val(nodeCode);
        $("#parentName").val(nodeName);
        $("#parentLevel").val(parentLevel);
        this.renderLeaderLevel("level", parentLevel);
    },
    renderLeaderType: function renderLeaderType(id, account) {
        var select = __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_components_ComponentManager__["default"].getComObjectByDomId(id);
        //select.reWriteHtml(dataArgs);
        var getUrl = '/system/core/user/queryposts/' + account;
        select.updateArgs({
            url: getUrl
        });
        select.refresh();
    },
    onSubmited: function onSubmited() {
        var errSize = $(".has-error").length;
        if (errSize < 1) {
            //添加完毕后，重新展开树
            this.initTree();
            // 选中编辑的数据
            var zTree = $.fn.zTree.getZTreeObj(this.options.treeId);
            var code = $("#code").val(); //【add】页面
            var node = zTree.getNodeByParam("code", code);
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
    searchZtree: function searchZtree(element) {
        var treeId = this.options.treeId;
        var searchConditionId = "txtSearch";
        var searchCondition = $('#' + searchConditionId).val();
        var searchObj = {};
        searchObj.treeId = treeId;
        // searchObj.type = 'resource,bizsys';
        searchObj.condition = searchCondition;
        //获得选择的那个节点
        var nodeObj = this.treeSearchAndExpand(searchObj);
        if (nodeObj) {
            if (nodeObj && nodeObj.id) {
                this.triggerOperation(nodeObj.id, nodeObj.type);
                this.selUser(nodeObj.id, nodeObj.name, nodeObj.approvalType);
            } else {
                layer.msg("\u6CA1\u6709\u67E5\u8BE2\u5230\u6307\u5B9A\u7684\u5C97\u4F4D\uFF01");
            }
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
    /*
    delete : function(params){
        let id = params.id;
        let zTree = $.fn.zTree.getZTreeObj("tree_id");
        let selectNode = zTree.getNodeByParam("id",id);
        if(selectNode != null){
            let parentNode = selectNode.getParentNode();
            let args = {};
            args.dialog = "confirm";
            args.type = "get";
            args.url = "/system/core/post/delete/"+params.id;
            args.isReload = 'false';
            let flag = false;
            try{
                this.dialog(args);
                flag = true;
            }catch(e){
              }
            if(flag){
                zTree.removeNode(selectNode);
            }
            this.triggerOperation(parentNode.id);//查看详情
        }
    },
    */
    delete: function _delete(args) {
        var promise = null;
        switch (args.dialog) {
            case 'confirm':
                layer.confirm('你确定要执行此操作吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    layer.closeAll();
                    promise = this.ajaxResource(args).then(function (data) {
                        var id = args.id;
                        var zTree = $.fn.zTree.getZTreeObj("tree_id");
                        var selectNode = zTree.getNodeByParam("id", id);
                        if (selectNode != null) {
                            var parentNode = selectNode.getParentNode();
                            zTree.removeNode(selectNode);
                            zTree.selectNode(parentNode, true); //选择节点
                            this.triggerOperation(parentNode.id); //查看详情
                        }
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
    renderLeaderLevel: function renderLeaderLevel(id, parentLevel) {
        // cacheUtils.push('gwType',null);
        // $('#'+id).removeAttr("e-url");
        // alert(id);
        var select = __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_components_ComponentManager__["default"].getComObjectByDomId(id);
        //select.reWriteHtml(dataArgs);
        var getUrl = '/system/core/dictionary/getSonDicsByCode?code=post_level&parentLevel=' + parentLevel;
        select.updateArgs({
            url: getUrl
        });
        select.refresh();
    }
}));

/***/ }),

/***/ 342:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./add.html": 343,
	"./detail.html": 344,
	"./edit.html": 345,
	"./index.html": 346
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
webpackContext.id = 342;

/***/ }),

/***/ 343:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        新建岗位\r\n        <small>新建岗位</small>\r\n    </h1>\r\n\r\n</section>\r\n\r\n<!-- Main content -->\r\n\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-sm-12" style="max-height: 600px; overflow: auto;"><!--style="min-width:520px"-->\r\n            <div class="">\r\n                <!-- <div class="box box-warning  ">\r\n                     <div class="box-header with-border">\r\n                         <h3 class="box-title"></h3>\r\n                     </div>-->\r\n                <!-- /.box-header -->\r\n                <div class="box-body">\r\n                    <form id="submitForm" role="form">\r\n                        <!-- hidden input -->\r\n\r\n                        <!-- text input -->\r\n                        <div class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;上级岗位</label>\r\n                            <input type="hidden" id="parentLevel" name="parentLevel"/>\r\n                            <input type="hidden" id="parentCode" name="parentCode"><!--上级机构id-->\r\n                            <input type="text" name="parentName" id="parentName" readonly datatype="s1-50" class="form-control"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group " style="margin-top:10px;">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;岗位编码</label>\r\n                            <input type="text" name="code" id="code" datatype="/^[0-9A-Za-z_]{1,20}$/" class="form-control" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"\r\n                                   ajaxurl="/system/core/post/checkcode" errormsg="请填写1到20位英文\u3001数字和下划线\uFF01"\r\n                                   placeholder="请输入岗位编码" ajaxurl="system/core/post/checkPostCode?id="\r\n                            />\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div style="margin-top:10px;" class="form-group ">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;岗位名称</label>\r\n                            <input type="text" name="name" class="form-control" id="name"\r\n                                   datatype="s1-20" errormsg="请填写1到20位中文\u3001英文\u3001数字和下划线\uFF01" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"  placeholder="请输入岗位名称">\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div style="margin-top:10px;" class="form-group ">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;岗位级别</label>\r\n                            <e-select id="level" name="level" class="form-control" e-unique="gwType" e-option-value="code"  e-option-text="name" e-url="system/core/dictionary/getSonDicsByCode?code=post_level" e-type="get" datatype="*">\r\n                            </e-select>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div style="margin-top:10px;" class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;岗位描述</label>\r\n                            <textarea rows="5" cols="80" id="remark" name="remark" class="form-control" datatype="*0-200" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')" placeholder="请输入岗位描述"></textarea>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n\r\n\r\n                        <div class="box-footer col-sm-12">\r\n                            <!--href:/system/dictionary/search?url=/system/dictionary/pages-->\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/submit?type=post&url=/system/core/post/save&contentType=application/x-www-form-urlencoded&jump=noGoBack&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="process_btn" type="button" title="保存信息"\r\n                            >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn"\r\n                                    type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n\r\n                    </form>\r\n                </div>\r\n                <!-- /.box-body -->\r\n                <!-- </div>-->\r\n\r\n            </div>\r\n            <!-- /.box -->\r\n        </div>\r\n        <!-- /.col -->\r\n    </div>\r\n\r\n\r\n    <!-- Your Page Content Here -->\r\n\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ }),

/***/ 344:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $escape = $imports.$escape;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        岗位管理\r\n        <small>岗位详情</small>\r\n\r\n    </h1>\r\n\r\n</section>\r\n\r\n<!-- Main content -->\r\n\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-md-12">\r\n            <!--<div class="box">-->\r\n            <!--root节点是不允许删除的-->\r\n            ';
    if (data.post.id != 'root') {
        $$out += '\r\n                <div class="inline pull-right"style="margin-right: 10px;">\r\n                    <button type="button" class="btn btn-danger btn-block"\r\n                            e-event="href:/system/post/delete?id=';
        $$out += $escape(data.post.id);
        $$out += '&dialog=confirm&type=get&url=/system/core/post/delete/';
        $$out += $escape(data.post.id);
        $$out += '&isReload=false"\r\n                            e-permission="core_organization_delete"\r\n                            ';
        if (data.post.id == '0') {
            $$out += ' disabled="disabled"';
        }
        $$out += '>\r\n                    <i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删除\r\n                    </button>\r\n                </div>\r\n            ';
    }
    $$out += '\r\n            <div class="inline pull-right"style="margin-right: 10px;">\r\n                <button type="button" class="btn btn-primary btn-block"\r\n                        e-event="href:/system/post/edit?url=/system/core/post/get/';
    $$out += $escape(data.post.id);
    $$out += '&contentId=list"\r\n                        e-permission="core_organization_edit"\r\n                        ';
    if (data.post.id == '0') {
        $$out += ' disabled="disabled"';
    }
    $$out += '>\r\n                <i class="glyphicon glyphicon-pencil"></i>&nbsp;&nbsp;编辑\r\n                </button>\r\n            </div>\r\n\r\n            <div class="inline pull-right" style="margin-right: 10px;">\r\n                <button type="button" class="btn btn-success btn-block"\r\n                        e-event="href:/system/post/add?contentId=list&parentLevel=';
    $$out += $escape(data.post.level);
    $$out += '"  e-permission="core_organization_create">\r\n                    <i class="glyphicon glyphicon-plus"></i>&nbsp;&nbsp;新建\r\n                </button>\r\n            </div>\r\n            <!--</div>-->\r\n        </div>\r\n    </div>\r\n    <div class="row">\r\n        <div class="col-sm-12"><!--style="min-width:520px"-->\r\n            <div class="">\r\n                <!-- <div class="box box-warning  ">\r\n                     <div class="box-header with-border">\r\n                         <h3 class="box-title"></h3>\r\n                     </div>-->\r\n                <!-- /.box-header -->\r\n                <div class="box-body">\r\n\r\n                    <form id="submitForm" role="form">\r\n                        <!-- hidden input -->\r\n                        <!-- <input type="hidden" name="versionNum"  class="form-control" >-->\r\n                        <input type="hidden" name="id" value="';
    $$out += $escape(data.post.id);
    $$out += '"><!--机构ID-->\r\n                        <!-- text input -->\r\n                        ';
    if (data.post.id != 'root') {
        $$out += '\r\n                        <div class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;上级岗位</label>\r\n                            <input type="hidden" id="parentCode" name="parentCode" value="';
        $$out += $escape(data.post.parentCode);
        $$out += '"><!--上级机构id-->\r\n                            <input type="text" name="parentName" id="parentName" disabled="disabled"\r\n                                   value="';
        $$out += $escape(data.post.parentName);
        $$out += '" datatype="s1-50" class="form-control"\r\n                            />\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        ';
    }
    $$out += '\r\n                        <div class="form-group " style="margin-top:10px;">\r\n                            <label><i class="fa"></i>&nbsp;岗位编码</label>\r\n                            <!--e-event="href:/system/dictionary/checkDic?type=post&url=/system/core/dictionary/checkdic&parentid=0&id=,event:blur"-->\r\n                            <input type="text" name="code" id="code" datatype="s1-50" disabled="disabled"\r\n                                   value="';
    $$out += $escape(data.post.code);
    $$out += '" class="form-control"/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div style="margin-top:10px;" class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;岗位名称</label>\r\n                            <input type="text" name="name" class="form-control" id="name"\r\n                                   value="';
    $$out += $escape(data.post.name);
    $$out += '" disabled="disabled" datatype="s1-50">\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        ';
    if (data.post.id != 'root') {
        $$out += '\r\n                        <div style="margin-top:10px;" class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;岗位级别</label>\r\n                            <e-select   id="level" name="level" class="form-control"  disabled e-value="';
        $$out += $escape(data.post.level);
        $$out += '" e-option-value="code" e-option-text="name"  e-url="system/core/dictionary/getSonDicsByCode?code=post_level" e-type="get" datatype="*" >\r\n                            </e-select>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        ';
    }
    $$out += '\r\n\r\n                        <div style="margin-top:10px;" class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;岗位描述</label>\r\n                            <textarea rows="5" cols="80" id="remark" name="remark" class="form-control" disabled="disabled" datatype="s0-125">';
    $$out += $escape(data.post.remark);
    $$out += '</textarea>\r\n                        </div>\r\n\r\n\r\n\r\n                        <!--<div class="box-footer col-sm-12">\r\n                        &lt;!&ndash;href:/system/dictionary/search?url=/system/dictionary/pages&ndash;&gt;\r\n                        &lt;!&ndash;<button e-event="href:/system/organization/submit?type=post&url=/system/core/organization/save&contentType=application/x-www-form-urlencoded&jump=noGoBack"\r\n                                class="btn btn-success" id="process_btn" type="button" title="保存信息"\r\n                                e-permission="core_dictionary_submit">\r\n                            <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                        </button>&ndash;&gt;\r\n                        <button e-event="href:/system/organization/goBack" class="btn btn-primary " id="back_btn"\r\n                                type="button" title="返回">\r\n                            <i class="fa fa-undo"></i>&nbsp;返回\r\n                        </button>\r\n                    </div>-->\r\n\r\n                    </form>\r\n                </div>\r\n                <!-- /.box-body -->\r\n                <!-- </div>-->\r\n\r\n            </div>\r\n            <!-- /.box -->\r\n        </div>\r\n        <!-- /.col -->\r\n    </div>\r\n\r\n\r\n    <!-- Your Page Content Here -->\r\n\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ }),

/***/ 345:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        岗位编辑\r\n        <small>编辑岗位信息</small>\r\n    </h1>\r\n\r\n</section>\r\n\r\n<!-- Main content -->\r\n\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-sm-12" style="max-height: 520px; overflow: auto;"><!--style="min-width:520px"-->\r\n            <div class="">\r\n                <!-- <div class="box box-warning  ">\r\n                     <div class="box-header with-border">\r\n                         <h3 class="box-title"></h3>\r\n                     </div>-->\r\n                <!-- /.box-header -->\r\n                <div class="box-body">\r\n                    <form id="submitForm" role="form">\r\n                        <!-- hidden input -->\r\n                        <input type="hidden" name="id" value="';
    $$out += $escape(data.post.id);
    $$out += '">\r\n\r\n                        <!-- text input -->\r\n                        ';
    if (data.post.id != 'root') {
        $$out += '\r\n                        <div class="form-group ">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;上级岗位</label>\r\n                            <input type="hidden" id="parentCode" name="parentCode" value="';
        $$out += $escape(data.post.parentCode);
        $$out += '"><!--上级机构id-->\r\n                            <input type="text" name="parentName" id="parentName" readonly class="form-control" value="';
        $$out += $escape(data.post.parentName);
        $$out += '"/>\r\n                        </div>\r\n                        ';
    }
    $$out += '\r\n                        <div class="form-group " style="margin-top:10px;">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;岗位编码</label>\r\n                            <input type="text" name="code" id="code" class="form-control"\r\n                                   errormsg="请填写1到20位英文\u3001数字和下划线\uFF01" readonly="readonly"\r\n                                   placeholder="请输入岗位编码"\r\n                                   value="';
    $$out += $escape(data.post.code);
    $$out += '"\r\n                            />\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div style="margin-top:10px;" class="form-group ">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;岗位名称</label>\r\n                            <input type="text" name="name" class="form-control" id="name"\r\n                                   datatype="s1-20" errormsg="请填写1到20位中文\u3001英文\u3001数字和下划线\uFF01" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')"  placeholder="请输入岗位名称"\r\n                                   value="';
    $$out += $escape(data.post.name);
    $$out += '"\r\n                            />\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        ';
    if (data.post.id != 'root') {
        $$out += '\r\n                        <div style="margin-top:10px;" class="form-group ">\r\n                            <label><i class="fa"></i><span style="color: red;font-weight: bolder">*</span>&nbsp;岗位级别</label>\r\n                            <e-select id="level" name="level" class="form-control" e-unique="gwType" e-value="';
        $$out += $escape(data.post.level);
        $$out += '" e-option-value="code" e-option-text="name" e-url="system/core/dictionary/getSonDicsByCode?code=post_level" e-type="get" datatype="*1-50">\r\n\r\n                            </e-select>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        ';
    }
    $$out += '\r\n                        <div style="margin-top:10px;" class="form-group ">\r\n                            <label><i class="fa"></i>&nbsp;岗位描述</label>\r\n                            <textarea rows="5" cols="80" id="remark" name="remark" class="form-control" datatype="*0-200" onchange="this.value=this.value.replace(/(^\\s*)|(\\s*$)/g,\'\')" placeholder="请输入岗位描述">';
    $$out += $escape(data.post.remark);
    $$out += '</textarea>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="box-footer col-sm-12">\r\n                            <!--href:/system/dictionary/search?url=/system/dictionary/pages-->\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/submit?type=post&url=/system/core/post/save&contentType=application/x-www-form-urlencoded&jump=noGoBack&goUrl=/';
    $$out += $escape(path);
    $$out += '/index"\r\n                                    class="btn btn-success" id="process_btn" type="button" title="保存信息"\r\n                            >\r\n                                <i class="fa fa-arrow-up"></i>&nbsp;保存信息\r\n                            </button>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/goBack?goUrl=/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn"\r\n                                    type="button" title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n\r\n                    </form>\r\n                </div>\r\n                <!-- /.box-body -->\r\n                <!-- </div>-->\r\n\r\n            </div>\r\n            <!-- /.box -->\r\n        </div>\r\n        <!-- /.col -->\r\n    </div>\r\n\r\n\r\n    <!-- Your Page Content Here -->\r\n\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ }),

/***/ 346:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n\r\n<section class="content-header">\r\n    <h1>\r\n        岗位管理\r\n        <small>岗位管理</small>\r\n    </h1>\r\n</section>\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-md-2">\r\n            <span style="display:none" id="initT" e-event="href:/';
    $$out += $escape(path);
    $$out += '/initTree,auto:true">\r\n            </span>\r\n            <div class="input-group">\r\n                <input type="text" id="txtSearch" class="form-control" placeholder="请输入岗位名称">\r\n                <span class="input-group-btn">\r\n                            <button id="btnSearch" class="btn btn-primary"\r\n                                    e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchZtree">\r\n                                <i class="fa fa-search"></i>\r\n                            </button>\r\n                </span>\r\n            </div>\r\n            <!--<div class="box" style="min-height: 600px;margin-top: 10px">\r\n                <div class="" >\r\n                    <ul id="tree_id" class="ztree" style="overflow-x: auto;min-height: 585px"></ul>\r\n                </div>\r\n            </div>-->\r\n            <div class="box" style="margin-top: 10px">\r\n                <div class="" >\r\n                    <ul id="tree_id" class="ztree" style="overflow: auto; max-height: 550px;height:800px;"></ul>\r\n                </div>\r\n            </div>\r\n        </div>\r\n\r\n        <div class="col-md-10">\r\n            <div class="box"><!--min-height: 645px-->\r\n                <div id="list" class="con-right treeDetail"></div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ })

});