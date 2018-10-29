/**
 * Created by zhaofs on 2017/5/6.
 * 演示Handler应用
 */
import BaseHandler from "ezdev-pcview/handler/BaseHandler";
import config from "ezdev-pcview/Config";
import renderer from "ezdev-pcview/render/Renderer";
import ajaxRequest from "ezdev-pcview/ajax/AjaxRequest";
import htmlUtils from "ezdev-pcview/utils/HtmlUtils";
import miscUtils from "ezdev-pcview/utils/MiscUtils";
import eventUtils from "ezdev-pcview/utils/EvenUtils";
import modifyfield from "../../../common/test/Modifyfield";
import Table from "ezdev-pcview/components/table/Table";

let layerId = null;
let dataList = {};
let dataListLength = null;
let upfieldHeadName = null;
export default class extends BaseHandler{
    resolveTpl (tpl) {
        return require("./tpl/" + tpl + ".html");
    }

    goBack(){
    window.location.href = '/#/exceltest/exceltest/bcpmessage/index';
    }


    upfileExcel(args) {
        let excelId = $("#upfile01").val();
        let $this =this;
        let data = {};
        data.excelId = excelId;
        args.data = data;
        ajaxRequest.ajax(args).then(function (msg) {
            let udata = {};
            udata.data = msg.data;
            args.data = udata;
            args.url=undefined;
            if (msg.data.SUCCESS){
                $this.render(args);
            }else{
                alert("出错了！")
            }
        });
    }

    formIndex(args) {
        args.tpl = args.tpl || "formIndex";
        if (args.tpl) {
            args.type = args.type || 'get';
            return this.render(args).then(()=>{
                this.addField();
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }

    modifyHead(args) {
        args.tpl = args.tpl || "modifyHead";
        if (args.tpl) {
            args.type = args.type || 'get';
            return this.render(args);
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }



    selfSubmit(args) {
        args.form = args.form || this.getContainerId('submitForm');
        if (args && args.url) {
            let form = $('#' + args.form);
            if (form.length > 0) {
                return new Promise((resolve, reject) => {
                    if (!form.Validform().check()) {
                        reject({msg: "验证无效"});
                    } else {
                        eventUtils.getCurrentEventTarget().attr("disabled", "disabled");
                        args.contentType = args.contentType || 'application/json';
                        if (args.contentType.indexOf("json") >= 0) {
                            if (typeof(args.submitType) !== 'undefined' && args.submitType === 'oneToMany') {
                                args.data = JSON.stringify(htmlUtils.formToPageJson(form));
                            } else {
                                args.data = JSON.stringify(htmlUtils.formToJson(form)); //forms.serialize();
                            }
                        } else {
                            args.data = form.serialize()
                        }
                        this.ajaxResource(args).then((data) => {
                            args.url=undefined;
                            args.data=undefined;
                            if (!args.jump) {
                                args.tpl = args.tpl;
                                if (args.tpl) {
                                    args.type = args.type || 'get';
                                    return this.render(args);
                                }else{
                                    window.location.reload();
                                }
                            } else {
                                eventUtils.getCurrentEventTarget().removeAttr("disabled");
                            }
                            resolve(data);
                        }, (data) => {
                            eventUtils.getCurrentEventTarget().removeAttr("disabled");
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

    selfSubmit1(args) {

        args.form = args.form || this.getContainerId('submitForm');
        if (args && args.url) {
            let form = $('#' + args.form);
            if (form.length > 0) {
                return new Promise((resolve, reject) => {
                    if (!form.Validform().check()) {
                        reject({msg: "验证无效"});
                    } else {
                        eventUtils.getCurrentEventTarget().attr("disabled", "disabled");
                        args.contentType = args.contentType || 'application/json';
                        if (args.contentType.indexOf("json") >= 0) {
                            if (typeof(args.submitType) !== 'undefined' && args.submitType === 'oneToMany') {
                                args.data = JSON.stringify(htmlUtils.formToPageJson(form));
                            } else {
                                args.data = JSON.stringify(htmlUtils.formToJson(form)); //forms.serialize();
                            }
                        } else {
                            args.data = form.serialize()
                        }
                        this.ajaxResource(args).then((data) => {
                            args.url=undefined;
                            args.data=undefined;
                            if (!args.jump) {
                               // args.tpl = args.tpl || "formIndex";
                                if (args.tpl) {
                                    args.type = args.type || 'get';
                                    return this.render(args);
                                }
                            } else {
                                eventUtils.getCurrentEventTarget().removeAttr("disabled");
                            }
                            resolve(data);
                        }, (data) => {
                            eventUtils.getCurrentEventTarget().removeAttr("disabled");
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


    headSubmit(args) {
        let $this = this;
        let fieldHeadName = $("input[name='fieldHeadName']").val();
        if(fieldHeadName != null && fieldHeadName != ""){
            $this.submitHead(args);
        }else{
            layer.prompt({
                formType: 2,
                value: '',
                title: '请输入自定义表头名称'
            }, function(value, index, elem){
                $("input[name='fieldHeadName']").val(value)
                layer.closeAll();
                $this.submitHead(args);
            });
        }
    }

   submitHead(args){
       let $this = this;
        args.form = args.form || $this.getContainerId('submitForm');
        if (args && args.url) {
            let form = $('#' + args.form);
            if (form.length > 0) {
                return new Promise((resolve, reject) => {
                    if (!form.Validform().check()) {
                        reject({msg: "验证无效"});
                    } else {
                        eventUtils.getCurrentEventTarget().attr("disabled", "disabled");
                        args.contentType = args.contentType || 'application/json';
                        if (args.contentType.indexOf("json") >= 0) {
                            if (typeof(args.submitType) !== 'undefined' && args.submitType === 'oneToMany') {
                                args.data = JSON.stringify(htmlUtils.formToPageJson(form));
                            } else {
                                args.data = JSON.stringify(htmlUtils.formToJson(form)); //forms.serialize();
                            }
                        } else {
                            args.data = form.serialize()
                        }

                        $this.ajaxResource(args).then((data) => {
                            args.url=undefined;
                            args.data=data.data;
                            if (!args.jump) {
                                args.tpl = args.tpl || "formIndex";
                                if (args.tpl) {
                                    args.type = args.type || 'get';
                                    return $this.render(args).then(()=>{
                                        dataList = data.data;
                                        dataListLength = data.data.list.size;
                                        $this.addFieldRow();
                                    });
                                }
                            }else{
                                eventUtils.getCurrentEventTarget().removeAttr("disabled");
                            }
                            resolve(data);
                        }, (data) => {
                            eventUtils.getCurrentEventTarget().removeAttr("disabled");
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


    addField(){
/*         $(".addField").append(modifyfield.addfield());
         renderer.analyzeHtml($(".addField"));*/
        this.render({
            tpl:"headRow",
            position:"append",
            contentId:$('.addField'),
            data:{id:miscUtils.guid()}
        })
    }
    addFieldRow(){
        this.render({
            tpl:"fieldRow",
            position:"append",
            contentId:$('.addRow'),
            data:{id:miscUtils.guid(),list:dataList.list,length:dataListLength}
        })
    }
    addModifyFieldRow(value){
        this.render({
            tpl:"modifyHeadRow",
            position:"append",
            contentId:$('.addField'),
            data:{id:miscUtils.guid(),head:value}
        })
    }
    addModifyDataRow(){
        this.render({
            tpl:"addModifyDataRow",
            position:"append",
            contentId:$('.addRow'),
            data:{id:miscUtils.guid(),list:dataList.list,length:dataListLength}
        })
    }

    delField(args){
        $("#"+args.id).remove();
    }

    //动态获取行
    ajaxGetRow(args) {
        $('.addField').empty();
        let $this = this;
        let  selectHead = $(".selectHead").val();

        if(selectHead == ""){
            $(".addFieldRow").attr("disabled",true);
            $(".radioBTN").attr("disabled",true);
            return false;
        }
        $(".addFieldRow").attr("disabled",false);
        $(".radioBTN").attr("disabled",false);
        $(".radioBTN2").prop("checked",false);
        $(".radioBTN1").prop("checked",true);
        let dataHead = args.data || {};
        dataHead.selectHead = selectHead;
        args.data = dataHead;

        $this.ajaxResource(args).then((data) => {
            args.url=undefined;
            data.data.forEach(function (value) {
                $("input[name='fieldHeadName']").val(value.fieldHeadName)
                $this.addModifyFieldRow(value);
            })
        }, (data) => {
            eventUtils.getCurrentEventTarget().removeAttr("disabled");
            reject(data);
        })
    }


    previewField(args){
       let fieldHeadName =  $("input[name='fieldHeadName']").val();
       if(fieldHeadName == "" || fieldHeadName == null){
           fieldHeadName = $(".selectHead").val();
       }
       let $this = this;
        layerId = layer.open({
            shade: 0,
            zIndex: 9999999,
            type: 1,
            shade: 0.3,
            move: args.move,
            title: args.title || '预览',
            area: [args.width || '50%', args.height || '85%'] //宽高
        });
        args.contentId = $('#layui-layer' + layerId).find('.layui-layer-content');
        args.tpl = "recordRow";
        args.url="/excelTest/exceltest/bcpmessage/previewHead";
        args.type="get";
        args.contentType="application/x-www-form-urlencoded;charset=UTF-8";
        let param = args.data || {};
        param.fieldHeadName = fieldHeadName;
        args.data =  param;
        //获取预览数据行
        this.render(args).then(function (data) {
            new Table({
                pageID: this.getContainerId('pager'),
                sortID:this.getContainerId('sort'),
                pageNum: data.data.pageNum,
                totalPages: data.data.totalPages,
                totalSize: data.data.totalSize,
                pageSize: data.data.pageSize,
                buttonClickCallback: this.search.bind(this, args)
            });
        }.bind(this));
    }


    appendHead(args){
        let $this = this;
        if($("input[name='fieldOption']:checked").val() == 1){

            $('.addField').empty();
            let $this = this;
            let  selectHead = $(".selectHead").val();

            if(selectHead == ""){
                $(".addFieldRow").attr("disabled",true);
                $(".radioBTN").attr("disabled",true);
                return false;
            }
            $(".addFieldRow").attr("disabled",false);
            $(".radioBTN").attr("disabled",false);
            let dataHead = args.data || {};
            dataHead.selectHead = selectHead;
            args.data = dataHead;

            $this.ajaxResource(args).then((data) => {
                args.url=undefined;
                data.data.forEach(function (value) {
                    $("input[name='fieldHeadName']").val(value.fieldHeadName)
                    $this.addModifyFieldRow(value);
                    $(".delete-contro").attr("disabled",true);
                })
                $(".form-delete").attr("disabled","disabled");
            }, (data) => {
                eventUtils.getCurrentEventTarget().removeAttr("disabled");
                reject(data);
            })
            $(".appendHead").attr("disabled","disabled");
        }else{
            $(".appendHead").attr("disabled",false);
            $(".form-delete").attr("disabled",false);
        }
    }

    modifyDataRow(args){
        args.tpl = args.tpl || "modifyDataRow";
        if (args.tpl) {
            args.type = args.type || 'get';
            this.render(args);
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }

    //数据行修改
    dataRowModify1(args){
        let fieldHeadName =  $("input[name='fieldHeadName']").val();
        let $this = this;
        args.contentId = $(".addField");
        args.tpl = "recordRow";
        args.url="/excelTest/exceltest/bcpmessage/getPreViewHead";
        args.type="get";
        args.contentType="application/x-www-form-urlencoded;charset=UTF-8";
        let param = args.data || {};
        param.fieldHeadName = fieldHeadName;
        args.data =  param;
        //获取预览数据行
        this.render(args).then(function (data) {
        }.bind(this));
    }

    //动态获取行
    dataRowModify(args) {
        let $this = this;
        let  selectHead = $(".selectHead").val();
        $("input[name='fieldHeadName']").val(selectHead);
        if(selectHead == ""){
            $(".addFieldRow").attr("disabled",true);
            $(".radioBTN").attr("disabled",true);
            $('.addRow').empty();
            return false;
        }

        let dataHead = args.data || {};
        dataHead.fieldHeadName = selectHead;
        //获取被选中的单选框
        let fieldOption = $("input[type='radio']:checked").val();
        dataHead.fieldOption = fieldOption;
        args.data = dataHead;
        console.log(dataHead)
        if($("input[name='fieldValue']").val()){
            layer.alert('是否保存原信息', {
                skin: 'layui-layer-molv' //样式类名  自定义样式
                ,closeBtn: 1    // 是否显示关闭按钮
                ,anim: 1 //动画类型
                ,btn: ['是的','不了'] //按钮
                ,icon: 6    // icon
                ,yes:function(){
                    let dataHead1 =  args.data || {};
                    dataHead1.fieldHeadName = $this.upfieldHeadName;
                    $("input[name='fieldHeadName']").val($this.upfieldHeadName);
                    //获取被选中的单选框
                    $this.fieldOption = $("input[type='radio']:checked").val();
                    dataHead1.fieldOption = $this.fieldOption;
                    args.data = dataHead1;
                    args.url="/excelTest/exceltest/bcpmessage/getRowData";
                    args.type = "post";
                   // args.tpl = "upSuccess";
                    args.contentType = "application/x-www-form-urlencoded;charset=UTF-8";
                    console.log(args.data)
                    $this.selfSubmit1(args).then(function (v) {
                        layer.msg('保存成功')
                        $this.upfieldHeadName = selectHead;
                        $('.addRow').empty();
                        $(".addFieldRow").attr("disabled", false);
                        $(".radioBTN").attr("disabled", false);
                        $(".radioBTN2").prop("checked", false);
                        $(".radioBTN1").prop("checked", true);
                        console.log(args.data)
                        dataHead1.fieldHeadName =  $(".selectHead").val();
                        args.data = dataHead1;
                        args.url=null;
                        console.log(args.data)
                        $("input[name='fieldHeadName']").val(selectHead);
                        args.url="/excelTest/exceltest/bcpmessage/getPreViewHead";
                        $this.ajaxResource(args).then((data) => {

                            args.url = undefined;
                            args.data = data.data;
                            if (!args.jump) {
                                dataList = data.data;
                                dataListLength = data.data.list.size;
                                $this.addModifyDataRow();
                                eventUtils.getCurrentEventTarget().removeAttr("disabled");
                            } else {
                                eventUtils.getCurrentEventTarget().removeAttr("disabled");
                            }
                        }, (data) => {
                            eventUtils.getCurrentEventTarget().removeAttr("disabled");
                        });
                    },function (e) {
                        layer.msg('保存失败')
                        console.log($this.upfieldHeadName)
                        $("select option[value='"+$this.upfieldHeadName+"']").prop("selected", "selected");
                    })
                }
                ,btn2:function(){
                    $this.upfieldHeadName = selectHead;
                    layer.msg('切换成功')
                    $('.addRow').empty();
                    $(".addFieldRow").attr("disabled", false);
                    $(".radioBTN").attr("disabled", false);
                    $(".radioBTN2").prop("checked", false);
                    $(".radioBTN1").prop("checked", true);

                    $this.ajaxResource(args).then((data) => {
                        args.url = undefined;
                        args.data = data.data;
                        if (!args.jump) {
                            dataList = data.data;
                            dataListLength = data.data.list.size;
                            $this.addModifyDataRow();
                        } else {
                            eventUtils.getCurrentEventTarget().removeAttr("disabled");
                        }
                    }, (data) => {
                        eventUtils.getCurrentEventTarget().removeAttr("disabled");
                    });
                }});
        }else {
            $this.upfieldHeadName = selectHead;

            $('.addRow').empty();
            $(".addFieldRow").attr("disabled", false);
            $(".radioBTN").attr("disabled", false);
            $(".radioBTN2").prop("checked", false);
            $(".radioBTN1").prop("checked", true);
            $this.ajaxResource(args).then((data) => {
                args.url = undefined;
                args.data = data.data;
                if (!args.jump) {
                    dataList = data.data;
                    dataListLength = data.data.list.size;
                    $this.addModifyDataRow();

                } else {
                    eventUtils.getCurrentEventTarget().removeAttr("disabled");
                }
            }, (data) => {
                eventUtils.getCurrentEventTarget().removeAttr("disabled");
            });
        }
    }
};


