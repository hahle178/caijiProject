webpackJsonp([2],[
/* 0 */,
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */,
/* 5 */,
/* 6 */,
/* 7 */,
/* 8 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = (__webpack_require__(0))(1);

/***/ }),
/* 9 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = (__webpack_require__(0))(12);

/***/ }),
/* 10 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = (__webpack_require__(0))(2);

/***/ }),
/* 11 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = (__webpack_require__(0))(24);

/***/ }),
/* 12 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = (__webpack_require__(0))(5);

/***/ }),
/* 13 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = (__webpack_require__(0))(4);

/***/ }),
/* 14 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = (__webpack_require__(0))(170);

/***/ }),
/* 15 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = (__webpack_require__(0))(13);

/***/ }),
/* 16 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/**
*dsfdsfdsfdsfds
 */
var html = null;
var count = 0;
/* unused harmony default export */ var _unused_webpack_default_export = ({
    addfield: function addfield() {
        count++;
        html = '<div class="box-body">\n' + '                            <!-- hidden input -->\n' + '                            <input type="hidden" name="versionNum"  class="form-control" >\n' + '                            <!-- text input -->\n' + '                            <div class="col-md-3">\n' + '                                <div class="form-group">\n' + '                                    <label><i class="fa"></i>&nbsp;字段名称</label>\n' + '                                    <input type="text" name="fieldName"  class="form-control"  datatype="*" placeholder="请输入字段名称" />\n' + '                                    <span class="help-block"></span>\n' + '                                </div>\n' + '                            </div>\n' + '                            <div class="col-md-3">\n' + '                                <div class="form-group">\n' + '                                    <label><i class="fa"></i>&nbsp;字段备注</label>\n' + '                                    <input type="text" name="fieldRemark"  class="form-control" datatype="*" placeholder="请输入字段备注" />\n' + '                                    <span class="help-block"></span>\n' + '                                </div>\n' + '                             </div>\n' + '                            <div class="col-md-3">\n' + '                                <div class="form-group">\n' + '                                    <label><i class="fa"></i>&nbsp;字段值</label>\n' + '                                    <input type="text" name="fieldValue"  class="form-control" datatype="*" placeholder="请输入字段值" />\n' + '                                    <span class="help-block"></span>\n' + '                                </div>\n' + '                            </div>\n' + '                            <div class="col-md-1">\n' + '                                <div class="form-group">\n' + '                                    <label><i class="fa"></i>&nbsp;操作</label>\n' + '                                     <button e-event="href:/exceltest/exceltest/bcpmessage/delField"  id="node' + count + '"' + '  class="btn btn-primary form-control"  type="button" title="删除">\n' + '                                        <i class="fa fa-remove"></i>&nbsp;删除\n' + '                                    </button>\n' + '                                    <span class="help-block"></span>\n' + '                                </div>\n' + '                            </div>\n' + '                        </div>';
        return html;
    },

    delfield: function delfield(node) {
        alert("1111111");
        /* $(node).parent().remove();*/
    }
});

/***/ }),
/* 17 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = (__webpack_require__(0))(25);

/***/ }),
/* 18 */,
/* 19 */,
/* 20 */,
/* 21 */,
/* 22 */,
/* 23 */,
/* 24 */,
/* 25 */,
/* 26 */,
/* 27 */,
/* 28 */,
/* 29 */,
/* 30 */,
/* 31 */,
/* 32 */,
/* 33 */,
/* 34 */,
/* 35 */,
/* 36 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_BaseHandler__ = __webpack_require__(9);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ezdev_pcview_Config__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ezdev_pcview_render_Renderer__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ezdev_pcview_ajax_AjaxRequest__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_ezdev_pcview_utils_HtmlUtils__ = __webpack_require__(13);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ezdev_pcview_utils_MiscUtils__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__common_test_Modifyfield__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_ezdev_pcview_components_table_Table__ = __webpack_require__(17);
var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

/**
 * Created by zhaofs on 2017/5/6.
 * 演示Handler应用
 */










var layerId = null;
var dataList = {};
var dataListLength = null;
var upfieldHeadName = null;

var _class = function (_BaseHandler) {
    _inherits(_class, _BaseHandler);

    function _class() {
        _classCallCheck(this, _class);

        return _possibleConstructorReturn(this, (_class.__proto__ || Object.getPrototypeOf(_class)).apply(this, arguments));
    }

    _createClass(_class, [{
        key: "resolveTpl",
        value: function resolveTpl(tpl) {
            return __webpack_require__(37)("./" + tpl + ".html");
        }
    }, {
        key: "goBack",
        value: function goBack() {
            window.location.href = '/#/exceltest/exceltest/bcpmessage/index';
        }
    }, {
        key: "upfileExcel",
        value: function upfileExcel(args) {
            var excelId = $("#upfile01").val();
            var $this = this;
            var data = {};
            data.excelId = excelId;
            args.data = data;
            __WEBPACK_IMPORTED_MODULE_3_ezdev_pcview_ajax_AjaxRequest__["default"].ajax(args).then(function (msg) {
                var udata = {};
                udata.data = msg.data;
                args.data = udata;
                args.url = undefined;
                if (msg.data.SUCCESS) {
                    $this.render(args);
                } else {
                    alert("出错了！");
                }
            });
        }
    }, {
        key: "formIndex",
        value: function formIndex(args) {
            var _this2 = this;

            args.tpl = args.tpl || "formIndex";
            if (args.tpl) {
                args.type = args.type || 'get';
                return this.render(args).then(function () {
                    _this2.addField();
                });
            } else {
                throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
            }
        }
    }, {
        key: "modifyHead",
        value: function modifyHead(args) {
            args.tpl = args.tpl || "modifyHead";
            if (args.tpl) {
                args.type = args.type || 'get';
                return this.render(args);
            } else {
                throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
            }
        }
    }, {
        key: "selfSubmit",
        value: function selfSubmit(args) {
            var _this3 = this;

            args.form = args.form || this.getContainerId('submitForm');
            if (args && args.url) {
                var form = $('#' + args.form);
                if (form.length > 0) {
                    return new Promise(function (resolve, reject) {
                        if (!form.Validform().check()) {
                            reject({ msg: "验证无效" });
                        } else {
                            __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().attr("disabled", "disabled");
                            args.contentType = args.contentType || 'application/json';
                            if (args.contentType.indexOf("json") >= 0) {
                                if (typeof args.submitType !== 'undefined' && args.submitType === 'oneToMany') {
                                    args.data = JSON.stringify(__WEBPACK_IMPORTED_MODULE_4_ezdev_pcview_utils_HtmlUtils__["default"].formToPageJson(form));
                                } else {
                                    args.data = JSON.stringify(__WEBPACK_IMPORTED_MODULE_4_ezdev_pcview_utils_HtmlUtils__["default"].formToJson(form)); //forms.serialize();
                                }
                            } else {
                                args.data = form.serialize();
                            }
                            _this3.ajaxResource(args).then(function (data) {
                                args.url = undefined;
                                args.data = undefined;
                                if (!args.jump) {
                                    args.tpl = args.tpl;
                                    if (args.tpl) {
                                        args.type = args.type || 'get';
                                        return _this3.render(args);
                                    } else {
                                        window.location.reload();
                                    }
                                } else {
                                    __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                                }
                                resolve(data);
                            }, function (data) {
                                __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                                reject(data);
                            });
                        }
                    });
                } else {
                    throw new Error("参数无效，DOM中无法找到id为[" + args.form + "]的Form！");
                }
            } else {
                throw new Error("参数无效，请传递如{url:/demo/mybatisuser-(必选),type:post-(默认),contentType:'application/json'-(默认)}的JS对象");
            }
        }
    }, {
        key: "selfSubmit1",
        value: function selfSubmit1(args) {
            var _this4 = this;

            args.form = args.form || this.getContainerId('submitForm');
            if (args && args.url) {
                var form = $('#' + args.form);
                if (form.length > 0) {
                    return new Promise(function (resolve, reject) {
                        if (!form.Validform().check()) {
                            reject({ msg: "验证无效" });
                        } else {
                            __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().attr("disabled", "disabled");
                            args.contentType = args.contentType || 'application/json';
                            if (args.contentType.indexOf("json") >= 0) {
                                if (typeof args.submitType !== 'undefined' && args.submitType === 'oneToMany') {
                                    args.data = JSON.stringify(__WEBPACK_IMPORTED_MODULE_4_ezdev_pcview_utils_HtmlUtils__["default"].formToPageJson(form));
                                } else {
                                    args.data = JSON.stringify(__WEBPACK_IMPORTED_MODULE_4_ezdev_pcview_utils_HtmlUtils__["default"].formToJson(form)); //forms.serialize();
                                }
                            } else {
                                args.data = form.serialize();
                            }
                            _this4.ajaxResource(args).then(function (data) {
                                args.url = undefined;
                                args.data = undefined;
                                if (!args.jump) {
                                    // args.tpl = args.tpl || "formIndex";
                                    if (args.tpl) {
                                        args.type = args.type || 'get';
                                        return _this4.render(args);
                                    }
                                } else {
                                    __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                                }
                                resolve(data);
                            }, function (data) {
                                __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                                reject(data);
                            });
                        }
                    });
                } else {
                    throw new Error("参数无效，DOM中无法找到id为[" + args.form + "]的Form！");
                }
            } else {
                throw new Error("参数无效，请传递如{url:/demo/mybatisuser-(必选),type:post-(默认),contentType:'application/json'-(默认)}的JS对象");
            }
        }
    }, {
        key: "headSubmit",
        value: function headSubmit(args) {
            var $this = this;
            var fieldHeadName = $("input[name='fieldHeadName']").val();
            if (fieldHeadName != null && fieldHeadName != "") {
                $this.submitHead(args);
            } else {
                layer.prompt({
                    formType: 2,
                    value: '',
                    title: '请输入自定义表头名称'
                }, function (value, index, elem) {
                    $("input[name='fieldHeadName']").val(value);
                    layer.closeAll();
                    $this.submitHead(args);
                });
            }
        }
    }, {
        key: "submitHead",
        value: function submitHead(args) {
            var $this = this;
            args.form = args.form || $this.getContainerId('submitForm');
            if (args && args.url) {
                var form = $('#' + args.form);
                if (form.length > 0) {
                    return new Promise(function (resolve, reject) {
                        if (!form.Validform().check()) {
                            reject({ msg: "验证无效" });
                        } else {
                            __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().attr("disabled", "disabled");
                            args.contentType = args.contentType || 'application/json';
                            if (args.contentType.indexOf("json") >= 0) {
                                if (typeof args.submitType !== 'undefined' && args.submitType === 'oneToMany') {
                                    args.data = JSON.stringify(__WEBPACK_IMPORTED_MODULE_4_ezdev_pcview_utils_HtmlUtils__["default"].formToPageJson(form));
                                } else {
                                    args.data = JSON.stringify(__WEBPACK_IMPORTED_MODULE_4_ezdev_pcview_utils_HtmlUtils__["default"].formToJson(form)); //forms.serialize();
                                }
                            } else {
                                args.data = form.serialize();
                            }

                            $this.ajaxResource(args).then(function (data) {
                                args.url = undefined;
                                args.data = data.data;
                                if (!args.jump) {
                                    args.tpl = args.tpl || "formIndex";
                                    if (args.tpl) {
                                        args.type = args.type || 'get';
                                        return $this.render(args).then(function () {
                                            dataList = data.data;
                                            dataListLength = data.data.list.size;
                                            $this.addFieldRow();
                                        });
                                    }
                                } else {
                                    __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                                }
                                resolve(data);
                            }, function (data) {
                                __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                                reject(data);
                            });
                        }
                    });
                } else {
                    throw new Error("参数无效，DOM中无法找到id为[" + args.form + "]的Form！");
                }
            } else {
                throw new Error("参数无效，请传递如{url:/demo/mybatisuser-(必选),type:post-(默认),contentType:'application/json'-(默认)}的JS对象");
            }
        }
    }, {
        key: "addField",
        value: function addField() {
            /*         $(".addField").append(modifyfield.addfield());
                     renderer.analyzeHtml($(".addField"));*/
            this.render({
                tpl: "headRow",
                position: "append",
                contentId: $('.addField'),
                data: { id: __WEBPACK_IMPORTED_MODULE_5_ezdev_pcview_utils_MiscUtils__["default"].guid() }
            });
        }
    }, {
        key: "addFieldRow",
        value: function addFieldRow() {
            this.render({
                tpl: "fieldRow",
                position: "append",
                contentId: $('.addRow'),
                data: { id: __WEBPACK_IMPORTED_MODULE_5_ezdev_pcview_utils_MiscUtils__["default"].guid(), list: dataList.list, length: dataListLength }
            });
        }
    }, {
        key: "addModifyFieldRow",
        value: function addModifyFieldRow(value) {
            this.render({
                tpl: "modifyHeadRow",
                position: "append",
                contentId: $('.addField'),
                data: { id: __WEBPACK_IMPORTED_MODULE_5_ezdev_pcview_utils_MiscUtils__["default"].guid(), head: value }
            });
        }
    }, {
        key: "addModifyDataRow",
        value: function addModifyDataRow() {
            this.render({
                tpl: "addModifyDataRow",
                position: "append",
                contentId: $('.addRow'),
                data: { id: __WEBPACK_IMPORTED_MODULE_5_ezdev_pcview_utils_MiscUtils__["default"].guid(), list: dataList.list, length: dataListLength }
            });
        }
    }, {
        key: "delField",
        value: function delField(args) {
            $("#" + args.id).remove();
        }

        //动态获取行

    }, {
        key: "ajaxGetRow",
        value: function ajaxGetRow(args) {
            $('.addField').empty();
            var $this = this;
            var selectHead = $(".selectHead").val();

            if (selectHead == "") {
                $(".addFieldRow").attr("disabled", true);
                $(".radioBTN").attr("disabled", true);
                return false;
            }
            $(".addFieldRow").attr("disabled", false);
            $(".radioBTN").attr("disabled", false);
            $(".radioBTN2").prop("checked", false);
            $(".radioBTN1").prop("checked", true);
            var dataHead = args.data || {};
            dataHead.selectHead = selectHead;
            args.data = dataHead;

            $this.ajaxResource(args).then(function (data) {
                args.url = undefined;
                data.data.forEach(function (value) {
                    $("input[name='fieldHeadName']").val(value.fieldHeadName);
                    $this.addModifyFieldRow(value);
                });
            }, function (data) {
                __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                reject(data);
            });
        }
    }, {
        key: "previewField",
        value: function previewField(args) {
            var _layer$open;

            var fieldHeadName = $("input[name='fieldHeadName']").val();
            if (fieldHeadName == "" || fieldHeadName == null) {
                fieldHeadName = $(".selectHead").val();
            }
            var $this = this;
            layerId = layer.open((_layer$open = {
                shade: 0,
                zIndex: 9999999,
                type: 1
            }, _defineProperty(_layer$open, "shade", 0.3), _defineProperty(_layer$open, "move", args.move), _defineProperty(_layer$open, "title", args.title || '预览'), _defineProperty(_layer$open, "area", [args.width || '50%', args.height || '85%']), _layer$open));
            args.contentId = $('#layui-layer' + layerId).find('.layui-layer-content');
            args.tpl = "recordRow";
            args.url = "/excelTest/exceltest/bcpmessage/previewHead";
            args.type = "get";
            args.contentType = "application/x-www-form-urlencoded;charset=UTF-8";
            var param = args.data || {};
            param.fieldHeadName = fieldHeadName;
            args.data = param;
            //获取预览数据行
            this.render(args).then(function (data) {
                new __WEBPACK_IMPORTED_MODULE_8_ezdev_pcview_components_table_Table__["default"]({
                    pageID: this.getContainerId('pager'),
                    sortID: this.getContainerId('sort'),
                    pageNum: data.data.pageNum,
                    totalPages: data.data.totalPages,
                    totalSize: data.data.totalSize,
                    pageSize: data.data.pageSize,
                    buttonClickCallback: this.search.bind(this, args)
                });
            }.bind(this));
        }
    }, {
        key: "appendHead",
        value: function appendHead(args) {
            var $this = this;
            if ($("input[name='fieldOption']:checked").val() == 1) {

                $('.addField').empty();
                var _$this = this;
                var selectHead = $(".selectHead").val();

                if (selectHead == "") {
                    $(".addFieldRow").attr("disabled", true);
                    $(".radioBTN").attr("disabled", true);
                    return false;
                }
                $(".addFieldRow").attr("disabled", false);
                $(".radioBTN").attr("disabled", false);
                var dataHead = args.data || {};
                dataHead.selectHead = selectHead;
                args.data = dataHead;

                _$this.ajaxResource(args).then(function (data) {
                    args.url = undefined;
                    data.data.forEach(function (value) {
                        $("input[name='fieldHeadName']").val(value.fieldHeadName);
                        _$this.addModifyFieldRow(value);
                        $(".delete-contro").attr("disabled", true);
                    });
                    $(".form-delete").attr("disabled", "disabled");
                }, function (data) {
                    __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                    reject(data);
                });
                $(".appendHead").attr("disabled", "disabled");
            } else {
                $(".appendHead").attr("disabled", false);
                $(".form-delete").attr("disabled", false);
            }
        }
    }, {
        key: "modifyDataRow",
        value: function modifyDataRow(args) {
            args.tpl = args.tpl || "modifyDataRow";
            if (args.tpl) {
                args.type = args.type || 'get';
                this.render(args);
            } else {
                throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
            }
        }

        //数据行修改

    }, {
        key: "dataRowModify1",
        value: function dataRowModify1(args) {
            var fieldHeadName = $("input[name='fieldHeadName']").val();
            var $this = this;
            args.contentId = $(".addField");
            args.tpl = "recordRow";
            args.url = "/excelTest/exceltest/bcpmessage/getPreViewHead";
            args.type = "get";
            args.contentType = "application/x-www-form-urlencoded;charset=UTF-8";
            var param = args.data || {};
            param.fieldHeadName = fieldHeadName;
            args.data = param;
            //获取预览数据行
            this.render(args).then(function (data) {}.bind(this));
        }

        //动态获取行

    }, {
        key: "dataRowModify",
        value: function dataRowModify(args) {
            var $this = this;
            var selectHead = $(".selectHead").val();
            $("input[name='fieldHeadName']").val(selectHead);
            if (selectHead == "") {
                $(".addFieldRow").attr("disabled", true);
                $(".radioBTN").attr("disabled", true);
                $('.addRow').empty();
                return false;
            }

            var dataHead = args.data || {};
            dataHead.fieldHeadName = selectHead;
            //获取被选中的单选框
            var fieldOption = $("input[type='radio']:checked").val();
            dataHead.fieldOption = fieldOption;
            args.data = dataHead;
            console.log(dataHead);
            if ($("input[name='fieldValue']").val()) {
                layer.alert('是否保存原信息', {
                    skin: 'layui-layer-molv' //样式类名  自定义样式
                    , closeBtn: 1 // 是否显示关闭按钮
                    , anim: 1 //动画类型
                    , btn: ['是的', '不了'] //按钮
                    , icon: 6 // icon
                    , yes: function yes() {
                        var dataHead1 = args.data || {};
                        dataHead1.fieldHeadName = $this.upfieldHeadName;
                        $("input[name='fieldHeadName']").val($this.upfieldHeadName);
                        //获取被选中的单选框
                        $this.fieldOption = $("input[type='radio']:checked").val();
                        dataHead1.fieldOption = $this.fieldOption;
                        args.data = dataHead1;
                        args.url = "/excelTest/exceltest/bcpmessage/getRowData";
                        args.type = "post";
                        // args.tpl = "upSuccess";
                        args.contentType = "application/x-www-form-urlencoded;charset=UTF-8";
                        console.log(args.data);
                        $this.selfSubmit1(args).then(function (v) {
                            layer.msg('保存成功');
                            $this.upfieldHeadName = selectHead;
                            $('.addRow').empty();
                            $(".addFieldRow").attr("disabled", false);
                            $(".radioBTN").attr("disabled", false);
                            $(".radioBTN2").prop("checked", false);
                            $(".radioBTN1").prop("checked", true);
                            console.log(args.data);
                            dataHead1.fieldHeadName = $(".selectHead").val();
                            args.data = dataHead1;
                            args.url = null;
                            console.log(args.data);
                            $("input[name='fieldHeadName']").val(selectHead);
                            args.url = "/excelTest/exceltest/bcpmessage/getPreViewHead";
                            $this.ajaxResource(args).then(function (data) {

                                args.url = undefined;
                                args.data = data.data;
                                if (!args.jump) {
                                    dataList = data.data;
                                    dataListLength = data.data.list.size;
                                    $this.addModifyDataRow();
                                    __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                                } else {
                                    __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                                }
                            }, function (data) {
                                __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                            });
                        }, function (e) {
                            layer.msg('保存失败');
                            console.log($this.upfieldHeadName);
                            $("select option[value='" + $this.upfieldHeadName + "']").prop("selected", "selected");
                        });
                    },
                    btn2: function btn2() {
                        $this.upfieldHeadName = selectHead;
                        layer.msg('切换成功');
                        $('.addRow').empty();
                        $(".addFieldRow").attr("disabled", false);
                        $(".radioBTN").attr("disabled", false);
                        $(".radioBTN2").prop("checked", false);
                        $(".radioBTN1").prop("checked", true);

                        $this.ajaxResource(args).then(function (data) {
                            args.url = undefined;
                            args.data = data.data;
                            if (!args.jump) {
                                dataList = data.data;
                                dataListLength = data.data.list.size;
                                $this.addModifyDataRow();
                            } else {
                                __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                            }
                        }, function (data) {
                            __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                        });
                    } });
            } else {
                $this.upfieldHeadName = selectHead;

                $('.addRow').empty();
                $(".addFieldRow").attr("disabled", false);
                $(".radioBTN").attr("disabled", false);
                $(".radioBTN2").prop("checked", false);
                $(".radioBTN1").prop("checked", true);
                $this.ajaxResource(args).then(function (data) {
                    args.url = undefined;
                    args.data = data.data;
                    if (!args.jump) {
                        dataList = data.data;
                        dataListLength = data.data.list.size;
                        $this.addModifyDataRow();
                    } else {
                        __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                    }
                }, function (data) {
                    __WEBPACK_IMPORTED_MODULE_6_ezdev_pcview_utils_EvenUtils__["default"].getCurrentEventTarget().removeAttr("disabled");
                });
            }
        }
    }]);

    return _class;
}(__WEBPACK_IMPORTED_MODULE_0_ezdev_pcview_handler_BaseHandler__["default"]);

/* harmony default export */ __webpack_exports__["default"] = (_class);
;

/***/ }),
/* 37 */
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./index.html": 47
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
webpackContext.id = 37;

/***/ }),
/* 38 */,
/* 39 */,
/* 40 */,
/* 41 */,
/* 42 */,
/* 43 */,
/* 44 */,
/* 45 */,
/* 46 */,
/* 47 */
/***/ (function(module, exports, __webpack_require__) {

var $imports = __webpack_require__(8);
module.exports = function ($data) {
    'use strict';
    $data = $data || {};
    var $$out = '', $escape = $imports.$escape, mcid = $data.mcid, data = $data.data, path = $data.path;
    $$out += '<!-- Content Header (Page header) -->\r\n<section class="content-header">\r\n    <h1>\r\n        上传Excel处理\r\n        <small>上传Excel处理</small>\r\n    </h1>\r\n</section>\r\n\r\n<!-- Main content -->\r\n<section class="content">\r\n\r\n    <div class="row">\r\n\r\n        <form id="';
    $$out += $escape(mcid);
    $$out += '_searchForm">\r\n            <div class="col-xs-12">\r\n                <div class="box">\r\n                    <div class="box-header">\r\n                        <h3 class="box-title"></h3>\r\n                    </div>\r\n                    <!-- /.box-header -->\r\n                    <div class="box-body">\r\n                        <div class="Tables_wrapper form-inline dt-bootstrap">\r\n\r\n                            <div class="row">\r\n                                <div class="col-sm-1">\r\n                                    <div class="Tables_length">\r\n                                    </div>\r\n                                </div>\r\n                                    <input type="hidden" name="sort" id="';
    $$out += $escape(mcid);
    $$out += '_sort" value="">\r\n                                    <div class="col-sm-1">\r\n                                </div>\r\n\r\n                                <div class="col-sm-1">\r\n                                        &nbsp;<a class="fa fa-plus btn btn-success"\r\n                                                 href="/attach/download/';
    $$out += $escape(data.downloadId);
    $$out += '" target="_blank">下载模板</a>\r\n                                </div>\r\n\r\n                                <div class="col-sm-4">\r\n                                    <e-attach class="form-control" id="attacher01"  name="attacher01"  e-pixels="800*600*maxWidthHeight"\r\n                                              e-single="false"  e-atr-id-saver="upfile01" e-atr-type="aLink" e-pixels="orginal"    >\r\n                                    </e-attach>\r\n                                </div>\r\n\r\n\r\n                                <div class="col-sm-1">\r\n                                    <button id="';
    $$out += $escape(mcid);
    $$out += '_confire" type="button" class="btn btn-block btn-success"\r\n                                            style="min-width:55px;" e-permission="exceltest_bcpmessage_add"\r\n                                            e-router="href:/';
    $$out += $escape(path);
    $$out += '/upfileExcel?url=/excelTest/exceltest/bcpmessage/upfile&tpl=upSuccess&contentType=application/x-www-form-urlencoded;charset=UTF-8">\r\n                                        <li class="fa fa-plus"></li>\r\n                                        &nbsp;确定上传\r\n                                    </button>\r\n                                </div>\r\n\r\n                            </div>\r\n\r\n                        </div>\r\n                    <div id="';
    $$out += $escape(mcid);
    $$out += '_list" class="row">\r\n\r\n                    </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <!-- /.box-body -->\r\n        </form>\r\n        <!-- /.col -->\r\n    </div>\r\n    <!-- /.row -->\r\n\r\n</section>\r\n<!-- /.content -->\r\n';
    return $$out;
};

/***/ })
]);