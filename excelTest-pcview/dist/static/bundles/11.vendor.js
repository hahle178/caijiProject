webpackJsonpvendor([11],{

/***/ 252:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_BaseHandler__ = __webpack_require__(12);
var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _get = function get(object, property, receiver) { if (object === null) object = Function.prototype; var desc = Object.getOwnPropertyDescriptor(object, property); if (desc === undefined) { var parent = Object.getPrototypeOf(object); if (parent === null) { return undefined; } else { return get(parent, property, receiver); } } else if ("value" in desc) { return desc.value; } else { var getter = desc.get; if (getter === undefined) { return undefined; } return getter.call(receiver); } };

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }



var _class = function (_BaseHandler) {
    _inherits(_class, _BaseHandler);

    function _class() {
        _classCallCheck(this, _class);

        return _possibleConstructorReturn(this, (_class.__proto__ || Object.getPrototypeOf(_class)).apply(this, arguments));
    }

    _createClass(_class, [{
        key: "resolveTpl",
        value: function resolveTpl(tpl) {
            return __webpack_require__(320)("./" + tpl + ".html");
        }
    }, {
        key: "render",
        value: function render(args) {

            if (args.tpl == "list") {
                args.data = {
                    "data": {
                        "content": [{
                            "id": "1",
                            "name": "用户管理", // 模块名称
                            "createName": "张三", // 操作人姓名
                            "createBy": "zhangsan", // 操作人账号
                            "createDate": "2018-05-25 16:25:00", // 操作时间
                            "approvalProcess": "领导审批", // 审批流程
                            "approvalBy": "李四", // 审批人
                            "IPAddress": "192.168.1.10", //IP地址
                            "operationName": "删除用户", //操作名称
                            "operationParam": "delete", //操作参数
                            "operationResult": "test1用户已删除", //操作结果
                            "updateName": null,
                            "updateBy": null,
                            "updateDate": "2018-05-25 16:25:00",
                            "versionNum": null,
                            "delFlag": "0",
                            "tenantId": null,
                            "obligateA": null,
                            "obligateB": null,
                            "obligateC": null,
                            "obligateD": null,
                            "obligateE": null
                        }, {
                            "id": "2",
                            "name": "字典管理", // 模块名称
                            "createName": "李四", // 操作人姓名
                            "createBy": "lisi", // 操作人账号
                            "createDate": "2018-05-25 16:25:00", // 操作时间
                            "approvalProcess": "审批通过", // 审批流程
                            "approvalBy": "王五", // 审批人
                            "IPAddress": "192.168.1.187", //IP地址
                            "operationName": "添加字典项", //操作名称
                            "operationParam": "save", //操作参数
                            "operationResult": "添加成功", //操作结果
                            "updateName": null,
                            "updateBy": null,
                            "updateDate": "2018-05-25 16:25:00",
                            "versionNum": null,
                            "delFlag": "0",
                            "tenantId": null,
                            "obligateA": null,
                            "obligateB": null,
                            "obligateC": null,
                            "obligateD": null,
                            "obligateE": null
                        }],
                        "firstPage": 1,
                        "prePage": 0,
                        "nextPage": 0,
                        "lastPage": 1,
                        "isFirstPage": true,
                        "isLastPage": true,
                        "hasPreviousPage": false,
                        "hasNextPage": false,
                        "navigatePages": 1,
                        "navigatepageNums": [1],
                        "pageNum": 1,
                        "pageSize": 1000,
                        "size": 2,
                        "totalSize": 2,
                        "totalPages": 1
                    }
                };
            } else if (args.tpl == "detail") {
                args.data = {
                    "data": {
                        "id": "1",
                        "name": "用户管理", // 模块名称
                        "createName": "张三", // 操作人姓名
                        "createBy": "zhangsan", // 操作人账号
                        "createDate": "2018-05-25 16:25:00", // 操作时间
                        "approvalProcess": "领导审批", // 审批流程
                        "approvalBy": "李四", // 审批人
                        "IPAddress": "192.168.1.10", //IP地址
                        "operationName": "删除用户", //操作名称
                        "operationParam": "delete", //操作参数
                        "operationResult": "test1用户已删除", //操作结果
                        "updateName": null,
                        "updateBy": null,
                        "updateDate": "2018-05-25 13:35:00",
                        "versionNum": null,
                        "delFlag": "0",
                        "tenantId": null,
                        "obligateA": null,
                        "obligateB": null,
                        "obligateC": null,
                        "obligateD": null,
                        "obligateE": null
                    }
                };
            }
            return _get(_class.prototype.__proto__ || Object.getPrototypeOf(_class.prototype), "render", this).call(this, args);
        }
    }]);

    return _class;
}(__WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_BaseHandler__["default"]);

/* harmony default export */ __webpack_exports__["default"] = (_class);

/***/ }),

/***/ 320:
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./detail.html": 321,
	"./index.html": 322,
	"./list.html": 323
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
webpackContext.id = 320;

/***/ }),

/***/ 321:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, data = $data.data, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<link href="common/css/style.css" rel="stylesheet"><!--ztree图标样式-->\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.all-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.core-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.excheck-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exedit-3.5.js"></script>\r\n<script type="text/javascript" src="plugins/ztree/jquery.ztree.exhide-3.5.js"></script>\r\n<link href="plugins/ztree/zTreeStyle.css" rel="stylesheet">\r\n<section class="content-header">\r\n    <h1>\r\n        业务日志-日志详情\r\n        <small>日志详情</small>\r\n    </h1>\r\n</section>\r\n<section class="content">\r\n    <div class="row-fluid">\r\n        <div class="col-xs-12" style="min-width:520px">\r\n            <div class="box ">\r\n                <div class="box-header with-border">\r\n                    <h3 class="box-title"></h3>\r\n                </div>\r\n                <div class="box-body ">\r\n                    <form id="submitForm" role="form">\r\n\r\n                        <input type="hidden" name="id" value="';
    $$out += $escape(data.id);
    $$out += '">\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;模块名称</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.name);
    $$out += '" class="form-control" disabled/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;操作人姓名</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.createName);
    $$out += '" class="form-control" disabled/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;操作人账号</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.createBy);
    $$out += '" class="form-control" disabled/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;审批流程</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.approvalProcess);
    $$out += '" class="form-control"\r\n                                   disabled/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;审批人</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.approvalBy);
    $$out += '" class="form-control" disabled/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;操作时间</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.createDate);
    $$out += '" class="form-control" disabled/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;IP地址</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.IPAddress);
    $$out += '" class="form-control" disabled/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;操作名称</label>\r\n                            <input type="text" name="name" value="';
    $$out += $escape(data.operationName);
    $$out += '" class="form-control"\r\n                                   disabled/>\r\n                            <span class="help-block"></span>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;操作参数</label>\r\n                            <textarea rows="5" cols="55" name="value" class="form-control" disabled>';
    $$out += $escape(data.operationParam);
    $$out += '</textarea>\r\n                        </div>\r\n\r\n                        <div class="form-group col-sm-6">\r\n                            <label><i class="fa"></i>&nbsp;操作结果</label>\r\n                            <textarea rows="5" cols="55" name="value" class="form-control" disabled>';
    $$out += $escape(data.operationResult);
    $$out += '</textarea>\r\n                        </div>\r\n                        <div class="box-footer col-sm-12">\r\n\r\n                            <button e-router="href:/';
    $$out += $escape(path);
    $$out += '/index" class="btn btn-primary " id="back_btn" type="button"\r\n                                    title="返回">\r\n                                <i class="fa fa-undo"></i>&nbsp;返回\r\n                            </button>\r\n                        </div>\r\n\r\n                    </form>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n\r\n</section>\r\n';
    return $$out;
};

/***/ }),

/***/ 322:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        业务日志-业务日志查看\r\n        <small>业务日志查看</small>\r\n    </h1>\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n    <div class="row">\r\n        <form id="searchForm">\r\n            <div class="col-xs-12"><!--table 列表形式x滑动条 style="overflow-x: auto"-->\r\n                <div class="box">\r\n                    <div class="box-header">\r\n                        <h3 class="box-title"></h3>\r\n                    </div>\r\n                    <!-- /.box-header -->\r\n                    <div class="box-body">\r\n                        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n                            <div class="row">\r\n                                <div class="col-sm-5" style="min-width:350px;">\r\n                                    <label>模块名称\uFF1A\r\n                                        <div class="input-group date">\r\n                                            <input type="text" id="name" name="search_LIKE_name" class="form-control"\r\n                                                   style="min-width:120px;">\r\n                                        </div>\r\n                                    </label>\r\n                                </div>\r\n                                <div class="col-sm-6">\r\n                                    <div class="inline pull-right" style="margin-right: 10px;">\r\n                                        <button type="button" title="查询"\r\n                                                e-event="href:/';
    $$out += $escape(path);
    $$out += '/render?tpl=list&contentId=list,auto:true"\r\n                                                class="btn btn-block btn-primary">\r\n                                            <li class="fa fa-search-plus"></li>\r\n                                            &nbsp;查询\r\n                                        </button>\r\n                                    </div>\r\n                                </div>\r\n                            </div>\r\n                        </div>\r\n                        <div id="list" class="row">\r\n\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</section>';
    return $$out;
};

/***/ }),

/***/ 323:
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(1);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', data = $data.data, $each = $imports.$each, item = $data.item, i = $data.i, $escape = $imports.$escape, path = $data.path;
    $$out += '<div class="col-sm-12">\r\n    <div class="Tables_wrapper form-inline dt-bootstrap no-footer">\r\n        <div class="row">\r\n            <div class="col-sm-6"></div>\r\n            <div class="col-sm-6"></div>\r\n        </div>\r\n        <div class="row">\r\n            <div class="col-sm-12" style="max-height: 449px; overflow: auto">\r\n                <table id="example2" class="table table-bordered table-hover no-footer"\r\n                       style="min-width:300px; table-layout: fixed;"\r\n                       role="grid" aria-describedby="example2_info">\r\n                    <thead>\r\n                    <tr role="row">\r\n                        <th style="width:200px;">\r\n                            模块\r\n                        </th>\r\n                        <th style="width:200px;">\r\n                            操作名称\r\n                        </th>\r\n                        <th style="width:200px;">\r\n                            操作人姓名\r\n                        </th>\r\n                        <th style="width:200px;">\r\n                            操作人账号\r\n                        </th>\r\n                        <th style="width:200px;">\r\n                            IP地址\r\n                        </th>\r\n                        <th style="width:200px;">\r\n                            操作\r\n                        </th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ';
    if (data.content.length == 0) {
        $$out += '\r\n                    <tr>\r\n                        <td class="lefttd" colspan="2">没有数据</td>\r\n                    </tr>\r\n                    ';
    } else {
        $$out += '\r\n                    ';
        $each(data.content, function (item, i) {
            $$out += '\r\n                    <tr role="row" class="odd">\r\n\r\n                        <td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"\r\n                            title="';
            $$out += $escape(item.name);
            $$out += '">';
            $$out += $escape(item.name);
            $$out += '\r\n                        </td>\r\n                        <td>';
            $$out += $escape(item.operationName);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.createName);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.createBy);
            $$out += '</td>\r\n                        <td>';
            $$out += $escape(item.IPAddress);
            $$out += '</td>\r\n\r\n                        <td align="center-left">\r\n                            <button e-router="href:/';
            $$out += $escape(path);
            $$out += '/render?tpl=detail"\r\n                                    type="button" class="btn btn-default"><i class="fa fa-search"></i> 查看\r\n                            </button>\r\n                        </td>\r\n                    </tr>\r\n                    ';
        });
        $$out += '\r\n                    ';
    }
    $$out += '\r\n                    </tbody>\r\n                </table>\r\n\r\n            </div>\r\n        </div>\r\n        <div class="row" id="pager">\r\n            <div class="col-sm-5">\r\n                <div class="dataTables_info" role="status" aria-live="polite">共2条\uFF0C1页</div>\r\n            </div>\r\n            <div class="col-sm-7">\r\n                <div class="dataTables_paginate paging_simple_numbers"><input type="hidden" id="pager_page"\r\n                                                                              name="pageNumber" value="1"><input\r\n                        type="hidden" id="currentPageNumber" name="currentPageNumber" value="1">\r\n                    <ul class="pagination">\r\n                        <li class="paginate_button empty"><a href="javascript:void(0)" aria-controls="example2"\r\n                                                             data-dt-idx="0" tabindex="0">首页</a></li>\r\n                        <li class="paginate_button empty"><a href="javascript:void(0)" aria-controls="example2"\r\n                                                             data-dt-idx="0" tabindex="0">&lt;</a></li>\r\n                        <li class="paginate_button active"><a href="javascript:void(0)" aria-controls="example2"\r\n                                                              data-dt-idx="0" tabindex="0">1</a></li>\r\n                        <li class="paginate_button empty"><a href="javascript:void(0)" aria-controls="example2"\r\n                                                             data-dt-idx="0" tabindex="0">&gt;</a></li>\r\n                        <li class="paginate_button empty"><a href="javascript:void(0)" aria-controls="example2"\r\n                                                             data-dt-idx="0" tabindex="0">尾页</a></li>\r\n                        <li>&nbsp;每页显示\uFF1A</li>\r\n                        <li><select id="pager_pageSize" name="pageSize" class="form-control">\r\n                            <option value="10">10</option>\r\n                            <option value="50">50</option>\r\n                            <option value="100">100</option>\r\n                        </select></li>\r\n                    </ul>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</div>';
    return $$out;
};

/***/ })

});