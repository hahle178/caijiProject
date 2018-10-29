/***
 * @描述：下拉框类，继承Component
 * @author:create by wangchen 2017.7.26
 * @用途：可以根据定位目标元素和数据渲染下拉框
 * @使用中注意点如下：
 * 标签名称e-select
 * 标签中的属性：
 * e-option-value ===> 类optionValue属性，下拉框选项的value
 * e-option-text ===> 类optionText属性，下拉框选项的文字
 * e-value ===> 类中的value属性，默认值
 * e-target ===> 类中的target属性，用于级联的情况，target为要影像的下拉框的id，在级联情况会添加onchange事件把本下拉框的value传给target对象
 * 其他：都为html原生属性或者其他第三方组件使用属性
 * html中使用举例如下：
 <e-search
 class="form-control"
 name="searchName"
 id="searchId"
 e-placeholder="请输入检索内容"
 e-secrcurl="/demo2/manager/userdemo6/pages"
 e-contextid="contextId1212121"
 e-event="keyup"
 e-searcParameter="search_mobile"
 e-type="post"
 e-value=""
 datatype="*"
 >
 </e-search>

 */
import BaseComponent from "ezdev-pcview/components/BaseComponent";
import handlerUtils from "ezdev-pcview/handler/HandlerUtils";
import htmlUtils from "ezdev-pcview/utils/HtmlUtils";
import $ from 'jquery';
import  "select2/dist/css/select2.min.css";
import  "select2";
import ajaxRequest from "ezdev-pcview/ajax/AjaxRequest";

'use strict';
let __instance = null;
export default class ESearch extends BaseComponent {

    /**
     * 初始化构造函数
     */
    constructor() {
        super();
        this.name = 'e-search';
    }

    /**
     * 实例化对象
     * @returns {*}
     */
    static instance() {
        if (__instance == null) {
            __instance = new ESearch();
        }
        return __instance;
    }

    /** 在重新html前，设置参数。页面标签的属性参数会覆盖js接口设置的参数，子类有需要可以重写
     * @Override
     */
    setArgsBeforeRw(args, options) {
        args = super.setArgsBeforeRw(args, options);
        if (typeof(args.disblankvalue) != 'undefined' && (args.disblankvalue == 'false' || args.disblankvalue == false)) {
            args.disblankvalue = false;
        } else {
            args.disblankvalue = true;
        }
        return args;
    }
    /**
     * 获取dom模板
     * @Override
     */
    generateHtml(args) {
        console.log(JSON.stringify(args));
        let id=args.id;
        let name=args.name;
        let placeholder =args.placeholder || "请输入查询信息";
        let type = args.type;
        let contextId=args.contextid;
        let event = args.event;
        let secrcurl = args.secrcurl;
        let searcparameter =args.searcparameter;

        let html =` <div style="position: absolute;z-index: 999999;">` +
            `                    <div> `+
            `                       <div style="width: 100%;float: left;">` +
            `                            <input id=${id} style="width: 100%"  name=${name} type="text" class="form-control"  s-event=href:${event}?url=${secrcurl}&type=${type}&id=${id}&searcParameter=${searcparameter}&contextId=${contextId}  value="" placeholder=${placeholder}>` +
            `                       </div>` +
            `                     </div>` +
            `                      <div>` +
            `                          <div id=${contextId} style="width: 100%;min-height: 0; float: left;">` +
            `                          </div>` +
            `                      </div>` +
            `                </div>`;


        return html;
    }
}