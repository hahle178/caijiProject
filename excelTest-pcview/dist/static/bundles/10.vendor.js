webpackJsonpvendor([10],{

/***/ 242:
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
            return __webpack_require__(276)("./" + tpl + ".html");
        }
    }, {
        key: "indexAfter",
        value: function indexAfter() {

            this.options = {
                roleListTpl: 'roles',
                roleListId: this.mcid + "_roles",
                roleListUrl: 'system/core/role/list',
                conditionListTpl: 'conditions',
                conditionListId: this.mcid + "_conditions",
                conditionListUrl: 'system/condition/list',
                levelListTpl: 'levels',
                levelListId: this.mcid + "_levels",
                map: {},
                selectedRole: {}
            };
            this.initRoleList();
        }
    }, {
        key: "initRoleList",
        value: function initRoleList() {
            var _this2 = this;

            var args = {
                contentId: this.options.roleListId,
                data: "search_LIKE_name=&page=1",
                form: this.mcid + "_searchForm",
                tpl: this.options.roleListTpl,
                url: this.options.roleListUrl
            };

            $('.pagination2').jqPagination({
                current_page: 1,
                max_page: 1,
                page_string: '第{current_page}页,共{max_page}页',
                paged: function paged(page) {
                    $("#" + _this2.mcid + "_page").val(page);
                    _this2.searchRoles(args).then(function () {
                        $("#" + _this2.options.roleListId + " tr:first").click();
                    });
                }
            });
        }
    }, {
        key: "searchRolesAfter",
        value: function searchRolesAfter() {

            $("#" + this.options.roleListId + " tr:first").click();
        }
    }, {
        key: "searchRoles",
        value: function searchRoles(args) {

            args.form = this.mcid + "_searchForm";
            if (args.url) {
                args.contentId = this.options.roleListId;
                var form = $("#" + args.form);
                if (form.length > 0) {
                    args.data = form.serialize();
                    args.tpl = this.options.roleListTpl;
                    return this.render(args).then(function (data) {
                        var maxpage = parseInt(data.data.totalPages);
                        var curpage = parseInt(data.data.pageNum);
                        $(".pagination2").jqPagination("option", {
                            max_page: maxpage,
                            current_page: curpage,
                            trigger: false
                        });
                    });
                } else {
                    throw new Error("参数无效，DOM中无法找到id为[" + args.form + "]的Form！");
                }
            } else {
                throw new Error("参数无效，请传递如{url:/demo/mybatisuser/pages-(必选),type:post-(默认),tpl:list-(默认),contentId:list-(默认)}的JS对象");
            }
        }
    }, {
        key: "searchConditions",
        value: function searchConditions(args) {

            args.objid = this.currentRoleId;
            args.objname = this.currentRoleName;
            this.selRole(args);
        }
    }, {
        key: "backToLevel",
        value: function backToLevel(args) {

            this.selRole({ url: "/system/core/role/get/" + this.options.selectedRole.id });
            $("#" + this.mcid + "_bindLevel").show();
            $("#" + this.mcid + "_bindConditions").hide();
        }
    }, {
        key: "selLevel",
        value: function selLevel(args) {
            var _this3 = this;

            this.getAllConditions().then(function () {

                var roleid = args.objid;

                // 清除选中记录
                _this3.options.map = {};

                var ids = _this3.options.map;

                if (roleid) {
                    var url = "system/core/role/bigdataconditionsbyroleid/" + roleid;
                    $.ajax({
                        cache: false,
                        async: false, // 同步加载
                        type: "GET",
                        url: url,
                        dataType: "json",
                        success: function success(data) {

                            if (data.data.length > 0) {

                                for (var i = 0; i < data.data.length; i++) {

                                    $("#" + data.data[i].id).attr('checked', true);
                                    ids[data.data[i].id] = data.data[i].id;
                                }
                            }

                            $("#" + _this3.mcid + "_bindLevel").hide();
                            $("#" + _this3.mcid + "_bindConditions").show();
                        }
                    });
                }
            });
        }
    }, {
        key: "selRole",
        value: function selRole(args) {
            var _this4 = this;

            var levelArgs = {
                tpl: this.options.levelListTpl,
                contentId: this.options.levelListId,
                // data: {data: {bigDataType: args.bigDataType, id: args.objid, name: args.objname}}
                url: args.url,
                type: "get"
            };

            this.render(levelArgs).then(function (data) {

                _this4.options.selectedRole = { id: data.data.id };

                _this4.currentRoleId = data.data.id;
                _this4.currentRoleName = data.data.name;

                var roleid = data.data.id;
                var rolename = data.data.name;

                //点击过后的tr变成蓝色，其他tr恢复原色
                $("#" + roleid).addClass("active").siblings().removeClass('active');

                if (!roleid) {
                    $("#" + _this4.mcid + "_btnSure").attr('disabled', true);
                } else {
                    $("#" + _this4.mcid + "_btnSure").attr('disabled', false);
                }

                $("#" + _this4.mcid + "_currentRoleId").val(roleid);
                $("#" + _this4.mcid + "_currentRoleName").text(rolename);

                $("#" + _this4.mcid + "_bindLevel").show();
                $("#" + _this4.mcid + "_bindConditions").hide();
            });
        }

        /**
         * 取消按钮
         */

    }, {
        key: "bindReset",
        value: function bindReset() {
            var _this5 = this;

            this.getAllConditions().then(function () {

                _this5.selLevel({ objid: _this5.options.selectedRole.id });
            });
            // this.selRole({url:`/system/core/role/get/${this.options.selectedRole.id}`});
        }
    }, {
        key: "resetLevel",
        value: function resetLevel() {

            this.selRole({ url: "/system/core/role/get/" + this.options.selectedRole.id });
        }

        /**
         * 获取全部数据表
         */

    }, {
        key: "getAllConditions",
        value: function getAllConditions() {
            var args = {
                tpl: this.options.conditionListTpl,
                url: this.options.conditionListUrl,
                contentId: this.options.conditionListId
            };
            return this.search(args);
        }

        /**
         * 提交保存
         */

    }, {
        key: "bindConditions",
        value: function bindConditions() {
            var _this6 = this;

            var conditions = '';

            $('table input[type="checkbox"]').each(function () {

                if ($(this).prop("checked")) {

                    conditions += $(this).val() + ',';
                }
            });

            var roleId = this.options.selectedRole.id;

            var param = { "roleid": roleId, "conditionsid": conditions };
            var url = "system/core/authorize/bindrolebigdataconditions";
            $.ajax({
                cache: false,
                data: param,
                type: "POST",
                url: url,
                dataType: "json",
                success: function success(data) {
                    _this6.initRoleList();
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
            var _this7 = this;

            args.form = this.mcid + "_conditionForm";
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
                            buttonClickCallback: _this7.search.bind(_this7, args)
                        });
                    });
                } else {
                    throw new Error("参数无效，DOM中无法找到id为[" + args.form + "]的Form！");
                }
            } else {
                throw new Error("参数无效，请传递如{url:/demo/mybatisuser/pages-(必选),type:post-(默认),tpl:list-(默认),contentId:list-(默认)}的JS对象");
            }
        }
    }, {
        key: "saveLevel",
        value: function saveLevel(args) {
            var _this8 = this;

            var type = $('[name="type"]:checked').val();
            var url = "system/core/role/bigdatatype/" + this.options.selectedRole.id + "/" + type;
            $.ajax({
                cache: false,
                type: "GET",
                url: url,
                dataType: "json",
                success: function success(data) {
                    _this8.initRoleList();
                }
            });
        }
    }]);

    return _class;
}(__WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_BaseHandler__["default"]);

/* harmony default export */ __webpack_exports__["default"] = (_class);

/***/ }),

/***/ 276:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./conditions.html": 277,
	"./index.html": 278,
	"./levels.html": 279,
	"./roles.html": 280
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
webpackContext.id = 276;

/***/ }),

/***/ 277:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, $escape = $imports.$escape, mcid = $data.mcid;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-12">\r\n                <table class="table table-bordered">\r\n                    <thead>\r\n                    <tr>\r\n                        <th><input type="checkbox" class="icheckbox_minimal-blue checked"></th>\r\n                        <th>名称</th>\r\n                        <th>所属数据表</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
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
            $$out += $escape(item.name);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.collection.name);
            $$out += '</td>\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n\r\n            </div>\r\n        </div>\r\n        <div class="row" id="';
    $$out += $escape(mcid);
    $$out += '_pager">\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ }),

/***/ 278:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, path = $data.path;
    $$out += '<section class="content-header">\r\n    <h1>授权管理\r\n        <small>大数据规则绑定</small>\r\n    </h1>\r\n</section>\r\n<section class="content">\r\n    <div class="row">\r\n        <div class="col-sm-2">\r\n            <form id="';
    $$out += $escape(mcid);
    $$out += '_searchForm">\r\n                <div class="con-left">\r\n                    <div class="zTree-box srcoll-box">\r\n                        <div class="input-group" style="width: 100%">\r\n                            <input type="text" id="';
    $$out += $escape(mcid);
    $$out += '_txtSearch" name="search_LIKE_name" class="form-control"\r\n                                   placeholder="请输入角色名称">\r\n                            <input type="hidden" name="flag" value="1">\r\n                            <!--<input type="hidden" name="search_EQ_bigDataType" value="1">-->\r\n                            <span class="input-group-btn">\r\n                                <button id="';
    $$out += $escape(mcid);
    $$out += '_btnSearch" type="button" class="btn btn-primary"\r\n                                        e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchRoles?url=/system/core/role/list,auto:true">\r\n                                    <i class="fa fa-search"></i>\r\n                                </button>\r\n                                <div e-event="href:/';
    $$out += $escape(path);
    $$out += '/initRoleList,auto:true"></div>\r\n\t\t\t\t\t\t     </span>\r\n                        </div>\r\n                        <div class="box" style="min-height: 600px;margin-top: 10px">\r\n                            <div class="authorize-role-list scroll-box" id="';
    $$out += $escape(mcid);
    $$out += '_roles" style="overflow-x: auto"></div>\r\n                            <div class="gigantic pagination2">\r\n                                <a href="#" class="first" data-action="first">&laquo;</a>\r\n                                <a href="#" class="previous" data-action="previous">&lsaquo;</a>\r\n                                <input type="text" readonly="readonly"/>\r\n                                <a href="#" class="next" data-action="next">&rsaquo;</a>\r\n                                <a href="#" class="last" data-action="last">&raquo;</a>\r\n                            </div>\r\n                            <input type=\'hidden\' id=\'';
    $$out += $escape(mcid);
    $$out += '_page\' name=\'pageNumber\' value=\'1\'>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </form>\r\n        </div>\r\n\r\n        <div class="col-sm-10">\r\n            <form id="';
    $$out += $escape(mcid);
    $$out += '_conditionForm">\r\n                <div class="box">\r\n                    <div class="box-header">\r\n                        <input type="hidden" id="';
    $$out += $escape(mcid);
    $$out += '_currentRoleId">\r\n                        为资源&nbsp;<span class="color-blue" id="';
    $$out += $escape(mcid);
    $$out += '_currentRoleName"></span>&nbsp;绑定数据\r\n                    </div>\r\n\r\n                    <div id="';
    $$out += $escape(mcid);
    $$out += '_bindLevel" class="box-body">\r\n                        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                            <div id="';
    $$out += $escape(mcid);
    $$out += '_levels" class="row"></div>\r\n                        </div>\r\n                        <div class="row-fluid">\r\n                            <div class="box-footer col-sm-12">\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/saveLevel"\r\n                                        class="btn btn-instagram" id="';
    $$out += $escape(mcid);
    $$out += '_saveLevel_btn" type="button" title="保存">\r\n                                    <i class="fa fa-floppy-o"></i>&nbsp;保存\r\n                                </button>\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/resetLevel" class="btn btn-primary"\r\n                                        id="';
    $$out += $escape(mcid);
    $$out += '_resetLevel_btn" type="button" title="取消">\r\n                                    <i class="fa fa-mail-reply"></i>&nbsp;取消\r\n                                </button>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n\r\n\r\n                    <div id="';
    $$out += $escape(mcid);
    $$out += '_bindConditions" class="box-body" style="display:none;">\r\n                        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                            <div class="row">\r\n                                <input type="hidden" name="search_EQ_collection.type" value="1">\r\n                                <div class="col-sm-6">\r\n                                    <label>所属大数据\uFF1A\r\n                                        <div class="input-group date">\r\n\r\n                                            <e-select class="form-control" name="search_EQ_collection.code"\r\n                                                      e-option-value="code"\r\n                                                      e-option-text="name" e-url="/system/collection/list"\r\n                                                      e-url-ajax-data="search_EQ_type:1"\r\n                                                      e-type="post" datatype="*">\r\n                                            </e-select>\r\n                                        </div>\r\n                                    </label>\r\n                                </div>\r\n                                <div class="col-sm-6">\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button type="button" class="btn btn-warning btn-block"\r\n                                                title="返回" e-event="href:/';
    $$out += $escape(path);
    $$out += '/backToLevel">\r\n                                            <li class="fa fa-undo"></li>\r\n                                            &nbsp;返回\r\n                                        </button>\r\n                                    </div>\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button type="button" title="查询"\r\n                                                e-event="href:/';
    $$out += $escape(path);
    $$out += '/searchConditions"\r\n                                                class="btn btn-block btn-primary"> <!--style="min-width:100px;"-->\r\n                                            <li class="fa fa-search-plus"></li>\r\n                                            &nbsp;查询\r\n                                        </button>\r\n                                    </div>\r\n                                </div>\r\n                            </div>\r\n                            <div id="';
    $$out += $escape(mcid);
    $$out += '_conditions" class="row"></div>\r\n                        </div>\r\n                        <div class="row-fluid">\r\n                            <div class="box-footer col-sm-12">\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindConditions"\r\n                                        class="btn btn-instagram" id="';
    $$out += $escape(mcid);
    $$out += '_save_btn" type="button" title="保存"\r\n                                        e-permission="core_authorize_bindroleconditions">\r\n                                    <i class="fa fa-floppy-o"></i>&nbsp;保存\r\n                                </button>\r\n                                <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/bindReset" class="btn btn-primary"\r\n                                        id="';
    $$out += $escape(mcid);
    $$out += '_back_btn" type="button" title="取消">\r\n                                    <i class="fa fa-mail-reply"></i>&nbsp;取消\r\n                                </button>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </form>\r\n        </div>\r\n    </div>\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 279:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $escape = $imports.$escape, path = $data.path;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-12">\r\n                <table class="table table-bordered">\r\n                    <thead>\r\n                    <tr>\r\n                        <th width="15%"></th>\r\n                        <th>级别</th>\r\n                        <th width="10%">操作</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    <tr role="row" class="odd">\r\n                        <td><input type="radio" name="type" value="0"\r\n                                   class="icheckbox_minimal-blue checked" ';
    if (data.bigDataType == 0) {
        $$out += 'checked';
    }
    $$out += '></td>\r\n                        <td>A级</td>\r\n                        <td></td>\r\n                    </tr>\r\n                    <tr role="row" class="odd">\r\n                        <td><input type="radio" name="type" value="1"\r\n                                   class="icheckbox_minimal-blue checked" ';
    if (data.bigDataType == 1) {
        $$out += 'checked';
    }
    $$out += '></td>\r\n                        <td>B级</td>\r\n                        <td>\r\n                            <button e-event="href:/';
    $$out += $escape(path);
    $$out += '/selLevel?objname=';
    $$out += $escape(data.name);
    $$out += '&objid=';
    $$out += $escape(data.id);
    $$out += '"\r\n                                    type="button" class="btn btn-default"><i class="fa fa-pencil-square"></i> 编辑\r\n                            </button>\r\n                        </td>\r\n                    </tr>\r\n                    <tr role="row" class="odd">\r\n                        <td><input type="radio" name="type" value="2"\r\n                                   class="icheckbox_minimal-blue checked" ';
    if (data.bigDataType == 2) {
        $$out += 'checked';
    }
    $$out += '></td>\r\n                        <td>C级</td>\r\n                        <td></td>\r\n                    </tr>\r\n                    </tbody>\r\n                </table>\r\n\r\n            </div>\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ }),

/***/ 280:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, $escape = $imports.$escape, path = $data.path;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-12">\r\n                <table class="table table-bordered table-hover dataTable no-footer">\r\n                    <tbody>\r\n                    ';
    if (data.content.length == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd" colspan="2">没有查询到指定的角色!</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd"\r\n                        e-event="href:/';
            $$out += $escape(path);
            $$out += '/selRole?url=/system/core/role/get/';
            $$out += $escape(item.id);
            $$out += '"\r\n                        id="';
            $$out += $escape(item.id);
            $$out += '">\r\n                        <td class="lefttd active">\r\n                            <a class="asst_magt_btn_look" href="javascript:void(0);">\r\n                                ';
            $$out += $escape(item.name);
            $$out += '\r\n                            </a>\r\n                        </td>\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ })

});