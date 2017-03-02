<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html lang="en-us">
<head>
    <title>信息中心</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap-responsive.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/style.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/mobilemenu.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrapValidator.css">
    <link rel="icon" href="<%=basePath%>/resources/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="<%=basePath%>/resources/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=basePath%>/resources/js/zTreeStyle/zTreeStyle.css" />
    <script type="text/javascript" src="<%=basePath%>/resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jquery.session.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jquery.flexslider.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/script.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/datepicker/bootstrapValidator.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jqueryPage.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/data.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jquery.ztree.excheck-3.5.js"></script>
    <!--[if lt IE 8]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
            <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0"
                 height="42" width="820"
                 alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."/>
        </a>
    </div>
    <![endif]-->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="/resources/js/html5.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/css/ie.css">
    <![endif]-->
    <style type="text/css">
        .form-group {
            padding-top:5px;
            padding-bottom:5px;
            font-size: 12pt;
        }
        .table{
            font-size: 10pt;
        }
        #pageinfo{
            text-align: center;
        }
        .pager{height:30px;line-height:30px;font-size: 12px;margin: 25px 0px;text-align: center;color: #2E6AB1;overflow: hidden;}
        .pager a{border:1px solid #9AAFE5;color:#2E6AB1;margin:0px 5px;padding:5px 8px;text-decoration: none;}
        .pager a.hover,.pager a.active{background-color:#2E6AB1;border-color:#000080;color:#FFF;}
        .pager a.disabled{color:#C8CDD2;cursor:auto;}
    </style>
    <script type="text/javascript">
        //获取当前日期
        function getNowFormatDate() {
            var date = new Date();
            var seperator1 = "-";
            var seperator2 = ":";
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = year + seperator1 + month + seperator1 + strDate;
            var currenttime = year + seperator1 + month + seperator1 + strDate
                    + " " + date.getHours() + seperator2 + date.getMinutes()
                    + seperator2 + date.getSeconds();
            var currentmonth = year + seperator1 + month;
            return currentdate;
        }
        function setDate(){
            $("#beginTime").attr("value",getNowFormatDate());
            $("#endTime").attr("value",getNowFormatDate());
        }
    </script>
</head>
<body>
<header class="">
    <div class="container">
        <div class="row">
            <div class="span12">
                <a class="logo" href="<%=basePath%>/manage/main">信息推送系统</a>
                <ul class="menu">
                    <li><a class="home" href="<%=basePath%>/manage/main">主页</a></li>
                    <li><a href="<%=basePath%>/manage/project">项目详情</a></li>
                    <li><a href="<%=basePath%>/manage/msgcenter">信息中心</a></li>
                    <li><a href="<%=basePath%>/manage/log">操作日志</a></li>
                    <li><a href="<%=basePath%>/manage/monitor">性能监控</a></li>
                    <li><a href="<%=basePath%>/manage/analyses">数据分析</a></li>
                    <li><a href="<%=basePath%>/manage/show">演示页面</a></li>
                </ul>
            </div>
        </div>
    </div><!--/container-->
</header>
<div id="contact">
    <div class="map_wrapper">
        <div class="container">
            <!--规则制定开始-->
            <section class="span12">
            <div class="row">
                <section class="span12">
                    <div class="span11">
                        <legend contenteditable="true">消息推送规则制定</legend>
                        <form id="rule_form" class="form-horizontal" role="form">
                            <fieldset>
                                <div class="row">
                                    <div class="span12 form-group">
                                        <div class="form-group">
                                            <label for="project" class="col-sm-2 control-label">项目名称 : </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="project" placeholder="项目名称"　name="project">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="msgTitle" class="col-sm-2 control-label">消息标题: </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="msgTitle" placeholder="消息标题"　name="msgTitle">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="msg" class="col-sm-2 control-label">消息内容 : </label>
                                            <div class="col-sm-10">
                                                <textarea class="form-control  span5" rows="3"  id="msg"
                                                          placeholder="推送消息内容"　name="msg"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="msgType" class="col-sm-2 control-label">消息类型 : </label>
                                            <div class="col-sm-10 form-control" id="msgType">
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="info" name="msgType" checked="checked">信息</label>
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="warning" name="msgType">警告</label>
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="error" name="msgType">错误</label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="rule1" class="col-sm-2 control-label">所属地区 : </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="rule1" id="rule1" data-toggle="modal" data-target="#menu_Modal" placeholder="所属地区">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="rule2" class="col-sm-2 control-label">税号集 : </label>
                                            <div class="col-sm-10">
                                                <textarea class="form-control  span5" rows="3"
                                                       id="rule2" placeholder="税号集"　name="rule2"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group" style="display:none;">
                                            <label for="rule3" class="col-sm-2 control-label">规则三 : </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="rule3" placeholder="规则三"　name="rule3">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="time" class="col-sm-2 control-label">频率调整 : </label>
                                            <div class="col-sm-10 form-control" id="time">
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="ok" name="time" checked="checked">默认</label>
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="up" name="time">高</label>
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="down" name="time"> 低</label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="update" class="col-sm-2 control-label">升级通知 : </label>
                                            <div class="col-sm-10 form-control" id="update">
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="false" name="update" checked="checked" id="ff"> 否</label>
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="true" name="update" id="tt"> 是</label>
                                            </div>
                                        </div>
                                        <div class="form-group" id="d_updateUrl" style="display:none;">
                                            <label for="updateUrl" class="col-sm-2 control-label">升级链接URL : </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="updateUrl" placeholder="升级链接URL"　name="updateUrl">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="close" class="col-sm-2 control-label">关闭形式 : </label>
                                            <div class="col-sm-10 form-control" id="close">
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="all" name="close" checked="checked"> 全部关闭</label>
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="own" name="close"> 部分关闭</label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="flag" class="col-sm-2 control-label">规则是否可用 : </label>
                                            <div class="col-sm-10 form-control" id="flag">
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="1" name="flag" checked="checked" id="y_use"> 是</label>
                                                <label class="radio-inline" style="display:inline;"><input type="radio" value="0" name="flag" id="n_use"> 否</label>
                                            </div>
                                        </div>
                                        <div class="form-group" id="d_content">
                                            <label for="b_time" class="col-sm-2 control-label">规则有效期 : </label>
                                            <div class="col-sm-10 form-control" id="b_time">
                                                <input type="text" name="beginTime" id="beginTime" onclick="WdatePicker({readOnly:false});" onfocus="WdatePicker({minDate:'%y-%M-{%d}'})">－
                                                <input type="text" name="endTime" id="endTime" onclick="WdatePicker({readOnly:false,minDate:'#F{$dp.$D(\'beginTime\')}'});">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10" style="text-align: center;">
                                                <button class="btn btn-default" onclick="PostData()" type="submit">保存规则</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </section>
            </div>
                </section>

            <!--规则制定结束-->
            <!--推送历史信息查询开始-->
            <section class="span12">
            <div class="row">
                <section class="span12">
                    <div class="span11">
                        <legend contenteditable="true">历史推送信息查询</legend>
                        <form id="msg_form" >
                            <fieldset>
                                <div class="row form-group">
                                    <div class="span12">
                                        <label class="span6">
                                            项目名称：
                                            <select name="s_project" id="s_project" class="form-control">
                                            </select>
                                        </label>
                                        <label class="span8">
                                            发送日期：
                                            <input type="text" name="starttime" id="s_starttime" onclick="WdatePicker({readOnly:false})">－
                                            <input type="text" name="endtime" id="s_endtime" onclick="WdatePicker({readOnly:false,minDate:'#F{$dp.$D(\'s_starttime\')}'})">
                                            <input class="btn btn_2 span1" style="margin-left: 20px;height:40px;margin-bottom:10px;"  onclick="getMsgList('1');creatPageNum();" type="button" value="查询"/>
                                        </label>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                        <!--推送历史信息列表展示-->
                        <div id="msglist">
                            <table class="table table-striped" id="t_msglist">
                               <tr> <td>用户ID</td><td>消息生成时间</td><td>消息发送时间</td><td>对应规则ID</td><td>消息内容</td></tr>
                            </table>
                        </div>
                        <div id="pageinfo">
                            <input type="hidden"  id="totalpage" class="btn btn-default btn-sm"/>
                        </div>
                    </div>
                </section>
            </div>
            </section>
            <!--推送历史信息查询结束-->
        </div>
    </div>
</div>
    <footer>
        <div class="container">
            <div class="row">
                <div class="span12 text-center">
                    &copy;电子有限公司 &nbsp; | &nbsp; all Rights Reserved
                </div>
            </div>
        </div>
    </footer>
<div class="modal fade bs-example-modal-lg" id="menu_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <ul id="treeDemo" class="ztree selling-tree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="showOrga">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    //获取消息列表
    $(document).ready(function(){
        $("#fbtn").trigger("click");
    });
    //设置初始时间
    setDate();
    //获取项目列表
    getPrjList();
    function getMsgList(btn_val){
        //$("#pageinfo input").removeClass("btn-primary");
        $(".msglist").remove();
        if(btn_val=="1"||btn_val<=1){
            getMsgTotalPage("1");
            $("#pageinfo .pager").remove();
        }
        //通过ajax获取数据
        $.ajax({
            url:'<%=basePath%>/getMsgList',
            type:'post',
            data:{
                "project":$("#s_project").val(),
                "starttime":$("#s_starttime").val()+" 00:00:00",
                "endtime":$("#s_endtime").val()+" 23:59:59",
                "currentpage":btn_val
            },
            async:false,
            dataType:'json',
            success:function(result){
                if(result){
                    for(var i=0;i<result.length;i++){
                        $("#t_msglist").append("<tr class='msglist'><td>"+result[i].userId+"</td><td>"+result[i].create_Timestamp+"</td><td>"
                                +result[i].send_Timestamp+"</td><td>"+
                                result[i].ruleUuid+"</td><td>"+result[i].msgContent+"</td></tr>");
                    }
                }
            },
            error:function(errorMsg){
                alert("数据获取失败");
            }
        });
    }
    function creatPageNum(){
        $("#pageinfo").pagination({
            count: parseInt($("#totalpage").val())*10, //总数
            size: 10, //每页数量
            index: 1,//当前页
            lrCount:3,//当前页左右最多显示的数量
            lCount: 1,//最开始预留的数量
            rCount: 1,//最后预留的数量
            callback: function (options) {
                getMsgList(options.index);
            }
        });
    }
    function getMsgTotalPage(btn_val){
        //通过ajax获取数据
        $.ajax({
            url:'<%=basePath%>/getMsgTotalPage',
            type:'post',
            data:{
                "project":$("#s_project").val(),
                "starttime":$("#s_starttime").val()+" 00:00:00",
                "endtime":$("#s_endtime").val()+" 23:59:59",
                "currentpage":btn_val
            },
            async:false,
            dataType:'text',
            success:function(result){
                if(result){
                    $("#totalpage").attr("value",result);
                }
            },
            error:function(errorMsg){
                alert("数据获取失败");
            }
        });
    }
    //页面加载自动获取项目列表
    function getPrjList(){
        $.ajax({
            url:'<%=basePath%>/getProjectList',
            type:'POST',
            data:{},
            async:false,
            dataType:'json',
            success:function(result){
                if(result){
                    for(var i=0;i<result.length;i++){
                        $("#s_project").append("<option value='"+result[i].project+"'>"+result[i].project+"</option>");
                    }
                }
            },
            error:function(errorMsg){
                alert("规则信息列表获取失败");
            }
        });
    }
    //提交表单信息
    function PostData() {
        if($("#project").val()!==""||$("#msg").val()!==""||$("#msgTitle").val()!==""||$("#rule1").val()!==""){
            $.ajax({
                type: "POST",
                url: "<%=basePath%>/saveRule",
                async:false,
                dataType:'json',
                data :{
                    "project":$("#project").val(),
                    "rule1":$("#rule1").val(),
                    "rule2":$("#rule2").val(),
                    "rule3":$("#rule3").val(),
                    "time":$('#time input[name="time"]:checked').val(),
                    "update":$('#update input[name="update"]:checked').val(),
                    "updateUrl":$("#updateUrl").val(),
                    "close":$('#close input[name="close"]:checked').val(),
                    "flag":$('#flag input[name="flag"]:checked').val(),
                    "beginTime":$("#beginTime").val()+" 00:00:00",
                    "endTime":$("#endTime").val()+" 23:59:59",
                    "msg":$("#msg").val(),
                    "msgType":$('#msgType input[name="msgType"]:checked').val(),
                    "msgTitle":$("#msgTitle").val()
                },
                success: function(msg) {
                    alert("保存规则成功");
                }
            });
        }else{
            alert("信息不完整");
        }
    }
    //更新链接是否可用，url区域显示或隐藏
    $("#tt").click(function(){
        $("#d_updateUrl").show();
    });
    $("#ff").click(function(){
        $("#d_updateUrl").hide();
    });
    //规则是否可用，日期选择区域显示和隐藏
    $("#y_use").click(function(){
        $("#d_content").show();
        setDate();
    });
    $("#n_use").click(function(){
        $("#d_content").hide();
    });
    $(function () {
        $('#rule_form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },

            fields: {
                project: {
                    message: '项目名称不能为空',
                    validators: {
                        notEmpty: {
                            message: '项目名称不能为空'
                        }
                    }
                },
                msg: {
                    validators: {
                        notEmpty: {
                            message: '消息内容不能为空'
                        }
                    }
                },
                msgTitle: {
                    validators: {
                        notEmpty: {
                            message: '消息标题不能为空'
                        }
                    }
                },
                endTime: {
                    validators: {
                        notEmpty: {
                            message: '请选择规则有效止期'
                        }
                    }
                },
                beginTime: {
                    validators: {
                        notEmpty: {
                            message: '请选择规则有限起期'
                        }
                    }
                }
            }
        });
    });
    function init(){
        var towns=new Array();
        var flag="1";
        function addTown(obj){
            var town=new Object();
            town.pId=obj.pId;
            town.id=obj.id;
            town.name=obj.name;
            towns[towns.length]=town;
        }
        function delTown(obj){
            for(var i=0;i<towns.length;i++){
                var town=towns[i];
                if(obj.id==town.id){
                    towns.splice(i,1);
                    break;
                }
            }
        }
        function zTreeOnCheck(event, treeId, treeNode) {
            var levelx =  treeNode.level;
            if(flag=="rule1"){
                if(levelx == 0){//市全选
                    if(treeNode.isParent){
                        var nodes = treeNode.children;
                        for (var index in nodes){
                            addTown(nodes[index]);
                        }
                    }else{
                        addTown(treeNode);
                    }

                }else if(levelx == 1){
                    addTown(treeNode);
                }
            }

        };
        var setting = {
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: { "Y": "ps", "N": "ps" }
            },
            callback: {
                onCheck: zTreeOnCheck
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        $("#rule1").click(function(){
            flag="rule1";
            $("#myModalLabel").text("地区选择");
            $.fn.zTree.init($("#treeDemo"), setting,citys);
            towns=new Array();

        });
        //第一类树形菜单点击"确认"按钮
        $("#showOrga").click(function(){
            $('#menu_Modal').modal('hide');
            if(flag=="rule1"){
                var names="";
                for(var index in towns){
                    var townName=towns[index].name;
                    var t=townName+",";
                    names=names+t;
                }
                $("#rule1").val(names);
            }

        });
    };
    function showScOrga(obj){
        $("#ScOrga").html("");
        var names="";
        for(var index in obj){
            var townName=obj[index].name;
            var t=townName+",";
            names=names+t;
        }
        $("#ScOrga").html(names);
    };
    init();
</script>
</html>

