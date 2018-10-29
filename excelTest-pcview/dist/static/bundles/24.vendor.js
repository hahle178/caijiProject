webpackJsonpvendor([24],{

/***/ 239:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_BaseHandler__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_components_table_Table__ = __webpack_require__(25);
var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }




var _class = function (_Handler) {
    _inherits(_class, _Handler);

    function _class() {
        _classCallCheck(this, _class);

        return _possibleConstructorReturn(this, (_class.__proto__ || Object.getPrototypeOf(_class)).apply(this, arguments));
    }

    _createClass(_class, [{
        key: "resolveTpl",
        value: function resolveTpl(tpl) {
            return __webpack_require__(267)("./" + tpl + ".html");
        }
    }, {
        key: "indexAfter",
        value: function indexAfter() {

            this.options = {
                leftTreeId: this.mcid + "_rescTree",
                leftTreeUrl: 'system/core/resource/list',
                collectionListTpl: 'list',
                collectionListId: this.mcid + "_list",
                collectionListUrl: 'system/collection/list',
                map: {}
            };
            this.initRescTree();
        }

        /**
         * 初始化资源树
         */

    }, {
        key: "initRescTree",
        value: function initRescTree() {
            var _this2 = this;

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
                    onClick: function onClick(event, treeId, treeNode) {
                        if (treeNode.type === 'resource') {
                            _this2.getAllCollections();
                            _this2.selResc(treeNode.id);
                            $("#" + _this2.mcid + "_currentRescName").text(treeNode.name);
                        }
                    }
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
                async: false,
                type: "POST",
                url: this.options.leftTreeUrl,
                dataType: "json",
                success: function success(data) {

                    //更改用户节点图标
                    var nodes = data.data;

                    //生成树
                    $.fn.zTree.init($("#" + _this2.options.leftTreeId), tree_setting, nodes);

                    //默认选中第一个用户
                    _this2.zTree = $.fn.zTree.getZTreeObj(_this2.options.leftTreeId);

                    nodes = _this2.zTree.getNodesByParam("type", "bizsys");
                    _this2.zTree.selectNode(nodes[0], true);

                    //展开选中节点
                    _this2.zTree.expandNode(nodes[0].getParentNode(), true, false, false);

                    //保存按钮是否显示
                    if (data.data.length === 0) {
                        $("#" + _this2.mcid + "_save_btn").attr('disabled', true);
                    } else {
                        $("#" + _this2.mcid + "_save_btn").attr('disabled', false);
                    }

                    var defaultNode = nodes[0];

                    //获取全部的数据表
                    _this2.getAllCollections();
                    _this2.selResc(defaultNode.id);
                    $("#" + _this2.mcid + "_currentRescName").text(defaultNode.name);
                }
            });
        }

        /**
         * 取消按钮
         */

    }, {
        key: "bindReset",
        value: function bindReset() {
            var checkedNode = this.zTree.getSelectedNodes();
            this.getAllCollections();
            this.selResc(checkedNode[0].id);
        }

        /**
         * 获取全部数据表
         */

    }, {
        key: "getAllCollections",
        value: function getAllCollections() {
            var args = {
                tpl: this.options.collectionListTpl,
                url: this.options.collectionListUrl,
                contentId: this.options.collectionListId
            };
            this.search(args);
        }

        /**
         * 点击树节点，执行的方法
         */

    }, {
        key: "selResc",
        value: function selResc(rescid) {

            if (!rescid) {
                $("#" + this.mcid + "_btnSure").attr('disabled', true);
            } else {
                $("#" + this.mcid + "_btnSure").attr('disabled', false);
            }

            $("#" + this.mcid + "_currentRescId").val(rescid);

            // 清除选中记录
            this.options.map = {};

            var ids = this.options.map;
            if (rescid) {

                $.ajax({
                    cache: false,
                    async: true,
                    type: "GET",
                    url: "system/core/resource/collectionsbyrescid/" + rescid,
                    dataType: "json",
                    success: function success(data) {
                        if (data.data.length > 0) {

                            for (var i = 0; i < data.data.length; i++) {

                                $("#" + data.data[i].id).attr('checked', true);
                                ids[data.data[i].id] = data.data[i].id;
                            }
                        }
                    }
                });
            }
        }

        /**
         * 提交保存
         */

    }, {
        key: "bindCollections",
        value: function bindCollections() {
            var _this3 = this;

            var colections = '';
            $('table input[type="checkbox"]').each(function () {

                if ($(this).prop("checked")) {

                    colections += $(this).val() + ',';
                }
            });

            var checkedNode = this.zTree.getSelectedNodes();
            var rescId = checkedNode[0].id;

            var param = { "rescid": rescId, "collectionsid": colections };
            var url = "system/core/authorize/bindresccollections";
            $.ajax({
                cache: false,
                data: param,
                type: "POST",
                url: url,
                dataType: "json",
                success: function success(data) {
                    _this3.initRescTree();
                }
            });
        }

        /**
         * 实现查询功能
         * @param args
         * {url:/demo/mybatisuser/pages-(必选),type:post-(默认),tpl:list-(默认),contentId:list-(默认)}
         */

    }, {
        key: "search",
        value: function search(args) {
            var _this4 = this;

            args.form = this.mcid + "_searchForm";
            if (args.url) {

                var form = $("#" + args.form);

                if (form.length > 0) {

                    args.data = form.serialize();

                    return this.render(args).then(function (data) {
                        new __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_components_table_Table__["default"]({
                            pageNum: data.data.pageNum,
                            totalPages: data.data.totalPages,
                            totalSize: data.data.totalSize,
                            pageSize: data.data.pageSize,
                            buttonClickCallback: _this4.search.bind(_this4, args)
                        });
                    });
                } else {
                    throw new Error("参数无效，DOM中无法找到id为[" + args.form + "]的Form！");
                }
            } else {
                throw new Error("参数无效，请传递如{url:/demo/mybatisuser/pages-(必选),type:post-(默认),tpl:list-(默认),contentId:list-(默认)}的JS对象");
            }
        }

        /**
         * zTree上部的查询框
         * @param element
         */

    }, {
        key: "searchZtree",
        value: function searchZtree() {

            var searchConditionId = this.mcid + "_txtSearch";
            var searchCondition = $('#' + searchConditionId).val();
            var searchObj = {};

            searchObj.condition = searchCondition;
            searchObj.type = "resource,bizsys";

            //获得选择的那个节点
            var nodeObj = this.treeSearchAndExpand(searchObj);
            if (nodeObj && nodeObj.id) {
                if (nodeObj.id) {
                    this.getAllCollections();
                    this.selResc(nodeObj.id);
                    $("#" + this.mcid + "_currentRescName").text(nodeObj.name);
                } else {}
            } else {
                layer.msg("没有查询到指定的资源！");
            }
        }

        /**
         * 树状列表查询后展示方法
         *
         * @param
         * searchObj,查询条件对象，内部含有treeId和condition属性。treeId树ID，condition是查询条件，树状列表上部查询框提供的节点名称查询条件
         * @param treeId树ID
         * @returns
         */

    }, {
        key: "treeSearchAndExpand",
        value: function treeSearchAndExpand(searchObj) {

            var treeObj = this.zTree;

            var treeNodes = treeObj.transformToArray(treeObj.getNodes());
            for (var i = 0; i < treeNodes.length; i++) {
                treeNodes[i].highlight = false;
                treeObj.updateNode(treeNodes[i]);
            }

            var highlightNodes = [];

            if (searchObj.condition !== "") {
                highlightNodes = treeObj.getNodesByParamFuzzy("name", searchObj.condition, null);
            } else {
                return;
            }

            var nodeObj = {};
            if (highlightNodes != null && highlightNodes.length > 0) {
                treeObj.cancelSelectedNode();
                var first = true;
                for (var _i = 0; _i < highlightNodes.length; _i++) {
                    if (searchObj.type && searchObj.type.indexOf(highlightNodes[_i].type) < 0) {
                        continue;
                    }

                    highlightNodes[_i].highlight = true;
                    if (first) {
                        nodeObj = highlightNodes[_i];
                        treeObj.selectNode(highlightNodes[_i], true);
                        first = false;
                    }
                    treeObj.updateNode(highlightNodes[_i]); // 级联选中
                    treeObj.expandNode(highlightNodes[_i].getParentNode(), true);
                }
            }
            return nodeObj;
        }
    }]);

    return _class;
}(__WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_BaseHandler__["default"]);

/* harmony default export */ __webpack_exports__["default"] = (_class);

/***/ }),

/***/ 267:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./index.html": 268,
	"./list.html": 269
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
webpackContext.id = 267;

/***/ }),

/***/ 268:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path, mcid = $data.mcid;
    $$out += '<section class="content-header">\r\n    <h1>授权管理\r\n        <small>资源数据绑定</small>\r\n    </h1>\r\n</section>\r\n<section class="content">\r\n    <div class="row">\r\n        <form id="searchForm">\r\n            <div class="col-sm-2">\r\n                <div>\r\n                    <div class="input-group">\r\n                        <input type="text" id="txtSearch" class="form-control" placeholder="请输入资源名称"/>\r\n                        <span class="input-group-btn">\r\n                          <button id="btnSearch" type="button" class="btn btn-primary"\r\n                                  e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchZtree">\r\n                              <i class="fa fa-search"></i>\r\n                          </button>\r\n                        </span>\r\n                    </div>\r\n                    <div class="box">\r\n                        <div style="overflow-x: auto">\r\n                            <ul id="';
    $$out += $escape(mcid);
    $$out += '_rescTree" class="ztree" style="min-height: 585px"></ul>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n            <div class="col-sm-10">\r\n                <div class="box">\r\n                    <div class="box-header">\r\n                        <input type="hidden" id="currentRescId">\r\n                        为资源&nbsp;<span class="color-blue" id="';
    $$out += $escape(mcid);
    $$out += '_currentRescName"></span>&nbsp;绑定数据\r\n                    </div>\r\n                    <div class="box-body">\r\n                        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                            <div id="';
    $$out += $escape(mcid);
    $$out += '_list" class="row"></div>\r\n                        </div>\r\n                        <div class="row-fluid">\r\n                            <div class="box-footer col-sm-12">\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindCollections"\r\n                                        class="btn btn-instagram" id="';
    $$out += $escape(mcid);
    $$out += '_save_btn" type="button" title="保存"\r\n                                        e-permission="core_authorize_bindresccollections">\r\n                                    <i class="fa fa-floppy-o"></i>&nbsp;保存\r\n                                </button>\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindReset" class="btn btn-primary"\r\n                                        id="back_btn" type="button" title="取消">\r\n                                    <i class="fa fa-mail-reply"></i>&nbsp;取消\r\n                                </button>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 269:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, $escape = $imports.$escape;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-12">\r\n                <table class="table table-bordered">\r\n                    <thead>\r\n                    <tr>\r\n                        <th><input type="checkbox" class="icheckbox_minimal-blue checked"></th>\r\n                        <th>表名</th>\r\n                        <th>名称</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.totalSize == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd" colspan="3">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n                        <td><input type="checkbox" id="';
            $$out += $escape(item.id);
            $$out += '" value="';
            $$out += $escape(item.id);
            $$out += '"\r\n                                   class="icheckbox_minimal-blue checked"\r\n                                   objid="';
            $$out += $escape(item.id);
            $$out += '" name="collections"></td>\r\n                        <td>';
            $$out += $escape(item.code);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.name);
            $$out += '</td>\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n\r\n            </div>\r\n        </div>\r\n        <div class="row" id="pager">\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ })

});