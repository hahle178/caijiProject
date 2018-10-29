/**
 * Created by zhaofs on 2017/5/6.
 * 演示Handler应用
 */
import BaseHandler from "ezdev-pcview/handler/BaseHandler";
import htmlUtils from "ezdev-pcview/utils/HtmlUtils";
import miscUtils from "ezdev-pcview/utils/MiscUtils";
import eventUtils from "ezdev-pcview/utils/EvenUtils";

import  "./css/style.min.css";
import  "./css/my.css";
import  "./js/jstree";
//import  "./js/main";
import   "./js/echarts-all";
import ajaxRequest from "ezdev-pcview/ajax/AjaxRequest";
import Table from "ezdev-pcview/components/table/Table";


let treeData = [];
let layerId = null;

export default class extends BaseHandler{
    resolveTpl (tpl) {
        return require("./tpl/" + tpl + ".html");
    }

    goBack(){
        window.location.reload();
    }
    //资源库首页
    respository(args) {
        let $this = this;
        args.tpl = args.tpl || "respository";
        if (args.tpl) {
            args.type = args.type || 'get';
             this.render(args).then(()=>{
                  args.url="/excelTest/exceltest/kettleKRepository/repositoryList";
                 this.ajaxResource(args).then((data) => {
                     data.data.forEach(function (value) {
                           $this.respositoryList(value);
                     })

                 })
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }
    //增加资源库列表
    respositoryList(value){
        this.render({
            tpl:"respositoryList",
            position:"append",
            contentId:$('.addRow'),
            data:{id:miscUtils.guid(),value:value}
        })
    }
    //增加转换列表
    transList(transData){
        this.render({
            tpl:"transList",
            position:"append",
            contentId:$('.addRow'),
            data:{id:miscUtils.guid(),data:transData}
        })
    }
    //单个删除资源库列表
    deleteRow(args){
        $("#"+args.id).remove();
        args.url="/excelTest/exceltest/kettleKRepository/deleteByReposityName";
        let name = args.data || {};
        name.repositoryName = args.name;
        args.data = name;
        this.ajaxResource(args).then((data) => {
            layer.msg("删除成功");
        })
    }

    //增加数据库资源库
    respositoryAdd(args) {
        args.tpl = args.tpl || "respositoryAdd";
        if (args.tpl) {
            args.type = args.type || 'get';
            this.render(args).then(()=>{
                //this.respositoryList();
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }

   //测试连接数据库资源库
    checkSubmit(args) {
        args.form = args.form || this.getContainerId('submitForm');
        if (args && args.url) {
            let form = $('#' + args.form);
            if (form.length > 0) {
                return new Promise((resolve, reject) => {
                    if (!form.Validform().check()) {
                        reject({msg: "验证无效"});
                    } else {
                        //eventUtils.getCurrentEventTarget().attr("disabled", "disabled");
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
                            if (!args.jump) {
                                if (data.code == "0"){
                                    layer.msg("连接成功");
                                }else {
                                    layer.msg("连接失败，请检查参数重试");
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
   //保存数据库资源库
    addSubmit(args) {
        args.form = args.form || this.getContainerId('submitForm');
        if (args && args.url) {
            let form = $('#' + args.form);
            if (form.length > 0) {
                return new Promise((resolve, reject) => {
                    if (!form.Validform().check()) {
                        reject({msg: "验证无效"});
                    } else {
                        //eventUtils.getCurrentEventTarget().attr("disabled", "disabled");
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
                            if (!args.jump) {
                                if (data.code == "0"){
                                    layer.msg("保存成功");
                                }else {
                                    layer.msg("保存失败，请检查参数重试");
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

    //转换列表首页
    trans(args) {
        let $this = this;
        args.tpl = args.tpl || "trans";
        if (args.tpl) {
            args.type = args.type || 'get';
            this.render(args).then(()=>{
                args.url="/excelTest/exceltest/ktrans/transList";
                this.ajaxResource(args).then((data) => {
                    /*data.data.forEach(function (value) {
                        $this.transList(value);
                    })*/
                    $this.transList(data.data);

                })
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }
    //根据查询条件查询转换
    findBytransName(args) {
        let $this = this;
        args.tpl = args.tpl || "trans";
        if (args.tpl) {
            args.type = args.type || 'get';
            //获取查询条件值
            let findBytransName = $("#"+this.mcid+"_txtSearch").val(); //获取vlaue值
            let data = args.data || {};
            data.findBytransName = findBytransName;
            args.data = data;

            this.render(args).then(()=>{
                args.url="/excelTest/exceltest/ktrans/findBytransName";
                this.ajaxResource(args).then((data) => {
                    /*data.data.forEach(function (value) {
                        $this.transList(value);
                    })*/
                    $this.transList(data.data);
                })
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }
    //增加转换
    transAdd(args) {
        args.tpl = args.tpl || "transAdd";
        if (args.tpl) {
            args.type = args.type || 'get';
            this.render(args).then(()=>{
                treeData = null;
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }
    //资源库change事件
    getTransTree(args){
        let repositoryId = $(".selectRep").val();
        let repository = args.data || {};
        repository.repositoryId = repositoryId;
        args.data = repository;

        if (repositoryId != null && repositoryId != ""){
            this.ajaxResource(args).then((data) => {
                if(data.code == "0"){
                    treeData = data.data;
                }else{
                    layer.msg("请求失败！重新操作");
                }

            })
        }else{
            treeData = null;
        }
    }


    //选择转换click事件
    transClick(args){
        var $transRepositoryId = $("#transRepositoryId").val();
        if (treeData != null){
            var index = layer.open({
                type: 1,
                title: '请选择转换',
                skin: 'layui-layer-rim',
                content: '<div id="repositoryTree"><ul id="repositoryTreeUl"></ul></div>'
            });


            treeData.forEach(function (value) {
                if(value.id.startsWith("trans") ){
                    $("#repositoryTreeUl").append("<li>"+value.text+"</li>")
                }
            })

            $('#repositoryTree').jstree({
                "core" : {
                    "check_callback" : true, // enable all modifications
                },
                "plugins" : ["dnd","contextmenu"]
            }).bind('select_node.jstree', function (event,data) {
                console.log(data)
                var transNode = data.node;
                layer.close(index);
                $("#transPath").val(transNode.text);
            })

        }else if($transRepositoryId != null && treeData == null){
            layer.msg("请等待资源库加载");
        }else if($transRepositoryId == null){
            layer.msg("请先选择资源库");
        }
    }

    //启动转换
    startTrans(args){
        args.data = {};
        let $this = this;
        let zidingyiData  = {};
        zidingyiData.transPath = args.transPath;
        args.data = zidingyiData;
            this.ajaxResource(args).then((data) => {
                if(data.code == "0"){
                    layer.msg("启动成功！");
                }else{
                    layer.msg("请求失败！重新操作");
                }
            })
    }


    //作业列表首页
    job(args) {
        let $this = this;
        args.tpl = args.tpl || "job";
        if (args.tpl) {
            args.type = args.type || 'get';
            this.render(args).then(()=>{
                args.url="/excelTest/exceltest/kjob/jobList";
                this.ajaxResource(args).then((data) => {
                    data.data.forEach(function (value) {
                        $this.jobList(value);
                    })

                })
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }

    //根据查询条件查询作业
    findByJobName(args) {
        let $this = this;
        args.tpl = args.tpl || "job";
        if (args.tpl) {
            args.type = args.type || 'get';
            //获取查询条件值
            let findByJobName = $("#"+this.mcid+"_txtSearch").val(); //获取vlaue值
            let data = args.data || {};
            data.findByJobName = findByJobName;
            args.data = data;
            this.render(args).then(()=>{
                args.url="/excelTest/exceltest/kjob/findByJobName";
                this.ajaxResource(args).then((data) => {
                    data.data.forEach(function (value) {
                        $this.jobList(value);
                    })
                })
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }

    //job列表
    jobList(value){
        this.render({
            tpl:"jobList",
            position:"append",
            contentId:$('.addRow'),
            data:{id:miscUtils.guid(),value:value}
        })
    }

    //增加作业
    jobAdd(args) {
        args.tpl = args.tpl || "jobAdd";
        if (args.tpl) {
            args.type = args.type || 'get';
            this.render(args).then(()=>{
                treeData = null;
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }

    //资源库change事件
    getJobTree(args){
        let repositoryId = $(".selectRep").val();
        let repository = args.data || {};
        repository.repositoryId = repositoryId;
        args.data = repository;

        if (repositoryId != null && repositoryId != ""){
            this.ajaxResource(args).then((data) => {
                if(data.code == "0"){
                    treeData = data.data;
                }else{
                    layer.msg("请求失败！重新操作");
                }

            })
        }else{
            treeData = null;
        }
    }

    //选择作业click事件
    jobClick(args){
        var $jobRepositoryId = $("#jobRepositoryId").val();
        if (treeData != null){
            var index = layer.open({
                type: 1,
                title: '请选择转换',
                skin: 'layui-layer-rim',
                content: '<div id="repositoryTree"><ul id="repositoryTreeUl"></ul></div>'
            });


            treeData.forEach(function (value) {
                if(value.id.startsWith("job") ){
                    $("#repositoryTreeUl").append("<li>"+value.text+"</li>")
                }
            })

            $('#repositoryTree').jstree({
                "core" : {
                    "check_callback" : true, // enable all modifications
                },
                "plugins" : ["dnd","contextmenu"]
            }).bind('select_node.jstree', function (event,data) {
                console.log(data)
                var transNode = data.node;
                layer.close(index);
                $("#jobPath").val(transNode.text);
            })

        }else if($jobRepositoryId != null && treeData == null){
            layer.msg("请等待资源库加载");
        }else if($jobRepositoryId == null){
            layer.msg("请先选择资源库");
        }
    }

    //启动作业
    startJob(args){
        args.data = {};
        let $this = this;
        let zidingyiData  = {};
        zidingyiData.jobPath = args.jobPath;
        args.data = zidingyiData;
        this.ajaxResource(args).then((data) => {
            if(data.code == "0"){
                layer.msg("启动成功！");
            }else{
                layer.msg("请求失败！重新操作");
            }
        })
    }

    //作业日志
    jobLog(args){
        let $this = this;
        args.tpl = args.tpl || "jobLog";
        if (args.tpl) {
            args.type = args.type || 'get';
            this.render(args).then(()=>{
                args.url = "/excelTest/exceltest/kjob/getJobMonitorLog"
                this.ajaxResource(args).then((data) => {
                    $this.jobLogList(data);
                })
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }
    //转换日志
    transLog(args){
        let $this = this;
        args.tpl = args.tpl || "transLog";
        if (args.tpl) {
            args.type = args.type || 'get';
            this.render(args).then(()=>{
                args.url = "/excelTest/exceltest/ktrans/getTransMonitorLog"
                this.ajaxResource(args).then((data) => {
                    $this.transLogList(data);
                })
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }
    //transLog列表
    transLogList(value){
        console.log(value)
        this.render({
            tpl:"transLogList",
            position:"append",
            contentId:$('.addRow'),
            data:{value:value}
        })

        this.render({
            tpl:"transLogHead",
            position:"append",
            contentId:$('.addHead'),
            data:{value:value.data[0]}
        })
    }
    //jobLog列表
    jobLogList(value){
        console.log(value)
        this.render({
            tpl:"jobLogList",
            position:"append",
            contentId:$('.addRow'),
            data:{value:value}
        })

        this.render({
            tpl:"jobLogHead",
            position:"append",
            contentId:$('.addHead'),
            data:{value:value.data[0]}
        })
    }
    //转换日志详情
    transLogDetail(args){
        let $this = this;
        args.tpl = args.tpl || "transLogDetail";
        if (args.tpl) {
            args.type = args.type || 'get';
            args.url = undefined;
            this.render(args).then(()=>{
                /*args.data = {};
                let zidingyiData  = {};
                zidingyiData.transPath = args.transPath;
                args.data = zidingyiData;*/

                args.contentId = $('.addRow');
                args.tpl = "transLogDetailList";
                args.url="excelTest/exceltest/ktrans/transLogDetail";
                args.type="get";
                args.contentType="application/x-www-form-urlencoded;charset=UTF-8";
                let param = args.data || {};
                param.transPath = args.transPath;
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
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }

    //作业日志详情
    jobLogDetail(args){
        let $this = this;
        args.tpl = args.tpl || "jobLogDetail";
        if (args.tpl) {
            args.type = args.type || 'get';
            args.url = undefined;
            this.render(args).then(()=>{
                /*args.data = {};
                let zidingyiData  = {};
                zidingyiData.transPath = args.transPath;
                args.data = zidingyiData;*/

                args.contentId = $('.addRow');
                args.tpl = "jobLogDetailList";
                args.url="excelTest/exceltest/kjob/jobLogDetail";
                args.type="get";
                args.contentType="application/x-www-form-urlencoded;charset=UTF-8";
                let param = args.data || {};
                param.transPath = args.jobPath;
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
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }


    //transLogDetail列表
   /* transLogDetailList(value){
        console.log(value)
        this.render({
            tpl:"transLogDetailList",
            position:"append",
            contentId:$('.addRow'),
            data:{value:value}
        })
    }*/


    /**
     *读取log文件
     * @param args
     */
    recordLogDetail(args){
        let $this = this;
        layerId = layer.open({
            shade: 0,
            zIndex: 9999999,
            type: 1,
            shade: 0.3,
            move: args.move,
            title: args.title || '执行日志详情',
            area: [args.width || '50%', args.height || '85%'] //宽高
        });
        args.contentId = $('#layui-layer' + layerId).find('.layui-layer-content');
        args.tpl = "recordLog";
        args.type="get";
        args.contentType="application/x-www-form-urlencoded;charset=UTF-8";

        let param = args.data || {};
        param.logFilePath = args.logFilePath;
        args.data =  param;
        //获取预览数据行
        this.render(args).then(function (data) {
            console.log(data.data)
        }.bind(this));
    }

    /**
     * 监控统计
     * @param args
     */
    monitorCounter(args){
        let $this = this;
        args.tpl = args.tpl || "monitorCounter";
        if (args.tpl) {
            args.type = args.type || 'get';
            this.render(args).then(()=>{
                args.url = "/excelTest/exceltest/main/getKettleLine"
                this.ajaxResource(args).then((data) => {
                    //$this.transLogList(data);


                    this.render({
                        tpl:"monitorCounterHead",
                        position:"append",
                        contentId:$('.addHead'),
                        url:"/excelTest/exceltest/main/getMonitorCounter"
                    })

                    //console.log(data.data.trans);
                    var lineChart = echarts.init(document.getElementById('kettleLine'));
                    var lineoption = {
                        title : {
                            text: '7天内作业和转换的监控状况'
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['作业','转换']
                        },
                        xAxis : [
                            {
                                type : 'category',
                                boundaryGap : false,
                                data : data.data.legend
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value',
                                axisLabel : {
                                    formatter: '{value}'
                                }
                            }
                        ],
                        series : [
                            {
                                name:data.data.trans.name,
                                type:'line',
                                data:data.data.trans.data,
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最大值'},
                                        {type : 'min', name: '最小值'}
                                    ]
                                },
                            },
                            {
                                name:data.data.job.name,
                                type:'line',
                                data:data.data.job.data,
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最大值'},
                                        {type : 'min', name: '最小值'}
                                    ]
                                },
                            }
                        ]
                    };
                    lineChart.setOption(lineoption);
                    $(window).resize(lineChart.resize);
                })
            });
        } else {
            throw new Error("参数无效，请传递如{tpl:add-(默认),contentId:list-(默认)}的JS对象");
        }
    }

};