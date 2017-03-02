<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
<head>
    <title>项目详情</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap-responsive.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/style.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/mobilemenu.css">
    <link rel="icon" href="<%=basePath%>/resources/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="<%=basePath%>/resources/images/favicon.ico" type="image/x-icon"/>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jquery-latest.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jquery.session.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jquery.flexslider.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/script.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/echarts.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jqueryPage.js"></script>
    <style type="text/css">
        .form-group {
            padding-top:5px;
            padding-bottom:5px;
            font-size: 12pt;
        }
        .table{
            font-size: 10pt;
        }
        #r_pageinfo,#pageinfo{
            text-align: center;
        }
        .control-label{
            width:100px;
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
        //获取当前月份
        function getNowFormatMonth() {
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
            return currentmonth;
        }
    </script>
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
            <!--监控项目列表开始-->
            <div class="row span12">
                <legend contenteditable="true">监控项目列表</legend>
                <table class="table table-striped" id="s_rulelist">
                    <tr> <td>规则ID</td><td>是否有效</td><td>规则有效起期</td><td>规则有效止期</td><td>应用项目</td><td>消息内容</td><td>规则制定日期</td></tr>
                </table>
            </div>
            <div id="r_pageinfo"  class="row span12">
                <input type="hidden"  id="totalpage" class="btn btn-default btn-sm"/>
            </div>
            <!--监控项目列表结束-->
            <!--在线用户量变化趋势开始-->
            <div class="row span12">
                <legend contenteditable="true">在线用户变化趋势</legend>
                <form id="prj_form" >
                    <fieldset>
                        <div class="row form-group">
                            <div class="span12">
                                <label class="span6">
                                    项目名称：
                                    <select name="c_project" id="c_project">
                                    </select>
                                    <input class="btn btn_2 span1" style="margin-left: 20px;height:40px;margin-bottom:10px;" onclick="loadChart();" type="button" value="查询"/>
                                </label>
                            </div>
                        </div>
                    </fieldset>
                </form>
                <section class="span11"><div  id="onlineminutes" style="width:1100px;height:260px;float:left"></div></section>
                <section class="span5.5"><div  id="onlinehour" style="width: 530px;height:260px;float:left"></div></section>
                <section class="span5.5"><div  id="onlineday" style="width: 530px;height:260px;float:left"></div></section>
            </div>
            <!--在线用户量变化趋势结束-->
            <!--用户详细信息开始-->
            <div class="row span12">
                <legend contenteditable="true">用户信息查询</legend>
                <form id="msg_form">
                    <fieldset>
                        <div class="row form-group">
                            <div class="span12">
                                <label class="span6">
                                    项目名称：
                                    <select name="s_project" id="s_project">
                                    </select>
                                </label>
                                <label class="span8">
                                    日期区间：
                                    <input type="text" name="starttime" id="s_starttime" onclick="WdatePicker({readOnly:false})">－
                                    <input type="text" name="endtime" id="s_endtime" onclick="WdatePicker({readOnly:false,minDate:'#F{$dp.$D(\'s_starttime\')}'})">
                                    <input class="btn btn_2 span1" style="margin-left: 20px;height:40px;margin-bottom:10px;" onclick="getUserList(1);creatPageNum();" type="button" value="查询"/>
                                </label>
                                <label class="span8" style="color:red;">备注：最近登录时间在5分钟之内的才可单独制定推送消息
                                    </label>
                            </div>
                        </div>
                    </fieldset>
                </form>
                <div id="userlist">
                    <table class="table table-striped" id="s_userlist">
                        <tr> <td>用户ID</td><td>访问IP</td><td>归属市</td><td>最新访问时间</td><td>项目名称</td><td>客户端类型</td><td>客户端版本号</td><td>操作</td></tr>
                    </table>
                </div>
                <div id="u_pageinfo"  class="row span12">
                    <input type="hidden"  id="u_totalpage" class="btn btn-default btn-sm"/>
                </div>
            </div>
            <!--用户详细信息结束-->
        </div>
    </div>
</div>
<footer>
    <div class="container">
        <div class="row">
            <div class="span12 text-center">
                &copy; 电子有限公司 &nbsp; | &nbsp; all Rights Reserved
            </div>
        </div>
    </div>
</footer>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: -1;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="closed">
                    ×
                </button>
                <h5 class="modal-title" id="myModalLabel">
                    制定消息内容
                </h5>
            </div>
            <form id="rule_form" class="form-horizontal" role="form">
            <div class="modal-body">
                    <fieldset>
                        <div class="row">
                            <div class="span5 form-group">
                               <input type="hidden" class="form-control" id="userId" placeholder="用户ID"　name="userId">
                               <input type="hidden" class="form-control" id="project" placeholder="项目名称"　name="project">
                                <div class="form-group">
                                    <label for="msgTitle" class="col-sm-2 control-label">消息标题: </label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="msgTitle" placeholder="消息标题"　name="msgTitle">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="msg" class="col-sm-2 control-label">消息内容 : </label>
                                    <div class="col-sm-10">
                                        <textarea class="form-control  span3" rows="3"  id="msg" placeholder="推送消息内容"　name="msg"></textarea>
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
                            </div>
                        </div>
                    </fieldset>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" class="btn btn-primary" onclick="PostData();">
                    保存消息
                </button>
            </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
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
                        $("#c_project").append("<option value='"+result[i].project+"'>"+result[i].project+"</option>");
                    }
                }
            },
            error:function(errorMsg){
                alert("项目列表获取失败");
            }
        });
    };
    getPrjList();
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('onlinehour'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '当日在线用户变化曲线'
        },
        tooltip:{
            trigger: 'axis'
        },
        legend: {
            data:[]
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '用户量',
            type: 'line',
            data: []
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.showLoading({text: '正在努力的读取数据中...'});
    myChart.hideLoading();
    //按小时在线用户量变化曲线
    function getChartData(project){
        //获取图表的options对象
        var options=myChart.getOption();
        //通过ajax获取数据
        $.ajax({
            url:'<%=basePath%>/getHourCount',
            type:'post',
            data:{
                "project":project,
                "day":getNowFormatDate()
            },
            async:false,
            dataType:'json',
            success:function(result){
                if(result){
                    options.xAxis[0].data=result.xAxis;
                    options.series[0].data=result.series.data;
                    myChart.setOption(options);
                }
            },
            error:function(errorMsg){
                alert("不好意思，图表正在加载中");
                myChart.hideLoading();
            }
        });
    }
    // 基于准备好的dom，初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('onlineday'));
    // 指定图表的配置项和数据
    var option2 = {
        title: {
            text: '当月在线用户变化曲线'
        },
        tooltip:{
            trigger: 'axis'
        },
        legend: {
            data:[]
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '用户量',
            type: 'line',
            data: []
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option2);
    myChart2.showLoading({text: '正在努力的读取数据中...'});
    myChart2.hideLoading();
    //按天在线用户量变化曲线
    function getChartData2(project){
        //获取图表的options对象
        var options=myChart2.getOption();
        //通过ajax获取数据
        $.ajax({
            url:'<%=basePath%>/getDayCount',
            type:'post',
            data:{
                "project":project,
                "month":getNowFormatMonth()
            },
            async:false,
            dataType:'json',
            success:function(result){
                if(result){
                    options.xAxis[0].data=result.xAxis;
                    options.series[0].data=result.series.data;
                    myChart2.setOption(options);
                }
            },
            error:function(errorMsg){
                alert("不好意思，图表正在加载中");
                myChart2.hideLoading();
            }
        });
    };



</script>
<script type="text/javascript">
    getRuleList("1");
    //getUserList();
    //获取用户列表方法
    function getUserList(btn_val){
        $(".t_userlist").remove();
        if(btn_val=="1"){
            getUserTotalPage("1");
            $("#u_pageinfo .pager").remove();
        }
        //通过ajax获取数据
        $.ajax({
            url:'<%=basePath%>/getUserList',
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
                       var nowtime=new Date();
                        var creattime=new Date(result[i].createTime.replace(/-/g,"/"));
                        var mins=(nowtime-creattime)/(1000*60);
                        if(mins<=5){
                            $("#s_userlist").append("<tr class='t_userlist'><td>"+result[i].userId+"</td><td>"+result[i].requestIp+"</td><td>"+result[i].requestAddr+"</td><td>"
                                    +result[i].createTime+"</td><td>"+
                                    result[i].project+"</td><td>"+result[i].clientType+"</td><td>"+result[i].clientVersion+
                                    "</td><td id='sendMsg'><button class='btn btn-link btn-sm' data-toggle='modal' data-target='#myModal' onclick='setInfo(&quot;"+result[i].userId+"&quot;,&quot;"+result[i].project+"&quot;);'>发送消息</button></td></tr>");
                        }else{
                            $("#s_userlist").append("<tr class='t_userlist'><td>"+result[i].userId+"</td><td>"+result[i].requestIp+"</td><td>"+result[i].requestAddr+"</td><td>"
                                    +result[i].createTime+"</td><td>"+
                                    result[i].project+"</td><td>"+result[i].clientType+"</td><td>"+result[i].clientVersion+
                                    "</td><td id='sendMsg'><button class='btn btn-link btn-sm' data-toggle='modal' data-target='#myModal' disabled='disabled'>发送消息</button></td></tr>");
                        }

                    }
                }
            },
            error:function(errorMsg){
                alert("用户信息获取失败");
            }
        });
    };
    function creatPageNum(){
        $("#u_pageinfo").pagination({
            count: parseInt($("#u_totalpage").val()), //总数
            size: 10, //每页数量
            index: 1,//当前页
            lrCount:3,//当前页左右最多显示的数量
            lCount: 1,//最开始预留的数量
            rCount: 1,//最后预留的数量
            callback: function (options) {
                getUserList(options.index);
            }
        });
    }
    //获取规则列表方法
    function getRuleList(btn_val){
        $(".t_rulelist").remove();
        if(btn_val=="1"){
            getRuleTotalPage("1");
        }
        //通过ajax获取数据
        $.ajax({
            url:'<%=basePath%>/getRuleList',
            type:'POST',
            data:{
                "currentpage":btn_val
            },
            async:false,
            dataType:'json',
            success:function(result){
                if(result){
                    for(var i=0;i<result.length;i++){
                        $("#s_rulelist").append("<tr class='t_rulelist'><td>"+result[i].uuid+"</td><td>"+result[i].flag+"</td><td>"+result[i].beginTime+"</td><td>"
                                +result[i].endTime+"</td><td>"+
                                result[i].project+"</td><td>"+result[i].msg+"</td><td>"+result[i].createTime+"</td></tr>");
                    }
                }
            },
            error:function(errorMsg){
                alert("规则信息列表获取失败");
            }
        });
    }
    function getRuleTotalPage(btn_val){
        $.ajax({
            url:'<%=basePath%>/getRuleTotalPage',
            type:'POST',
            data:{
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
                alert("规则信息列表获取失败");
            }
        });
    }
    function getUserTotalPage(btn_val){
        $.ajax({
            url:'<%=basePath%>/getUserTotalPage',
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
                    $("#u_totalpage").attr("value",result);
                }
            },
            error:function(errorMsg){
                alert("用户信息获取失败");
            }
        });
    }

    //实时变化曲线
    var myChart0 = echarts.init(document.getElementById('onlineminutes'));
    var option0= {
        title: {
            text: '在线用户量实时变化曲线'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '用户量',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false
        }]
    };
    myChart0.setOption(option0);
    var c_data = [];//定义值数组
    var c_xaxis=[];//定义横轴数组
    function getChartData0(){
        var options0=myChart0.getOption();
        var p=$("#c_project").val();
        //初始化时加载24小时内的用户变化曲线
        $.ajax({
            url:'<%=basePath%>/getOneDayMinutesCount',
            type:'post',
            data:{
                "project":p
            },
            async:false,
            dataType:'json',
            success:function(result){
                if(result){
                    options0.xAxis[0].data=result.xAxis;
                    options0.series[0].data=result.series.data;
                    myChart0.setOption(options0);
                }
            },
            error:function(errorMsg){
                alert("不好意思，图表正在加载中");
                myChart0.hideLoading();
            }
        });
    };
    getChartData0();
    //获取每分钟数据,赋值给data和xaxis
    function getMinData() {
        var p=$("#c_project").val();
        $.ajax({
            url:'<%=basePath%>/getMinutesCount',
            type:'post',
            data:{
                "project":p
            },
            async:false,
            dataType:'json',
            success:function(result){
                if(result){
                    var c_data =myChart0.getOption().series[0].data;//定义值数组
                    var c_xaxis=myChart0.getOption().xAxis[0].data;//定义横轴数组
                    c_data.shift();
                    c_xaxis.shift();
                    c_xaxis.push(result.countTime);
                    c_data.push(result.onlineCounts);
                    var op=myChart0.getOption();
                    op.series[0].data=c_data;
                    op.xAxis[0].data=c_xaxis;
                    myChart0.setOption(op);
                }
            },
            error:function(errorMsg){
                //alert("不好意思，图表正在加载中");
                myChart0.hideLoading();
            }
        });
    };
    //自动加载图表
    var prj=$("#c_project").val();
    function loadChart(){
        var prj=$("#c_project").val();
        getChartData(prj);
        getChartData2(prj);
        window.setInterval(function () {
            getMinData();
        }, 1*60*1000);
    };
    $(function(){
        loadChart();
    });
</script>
<script>
    $("#r_pageinfo").pagination({
        count: parseInt($("#totalpage").val())*10, //总数
        size: 10, //每页数量
        index: 1,//当前页
        lrCount:3,//当前页左右最多显示的数量
        lCount: 1,//最开始预留的数量
        rCount: 1,//最后预留的数量
        callback: function (options) {
            getRuleList(options.index);
        }
    });
    //更新链接是否可用，url区域显示或隐藏
    $("#tt").click(function(){
        $("#d_updateUrl").show();
    });
    $("#ff").click(function(){
        $("#d_updateUrl").hide();
    });
    //传递userID和project
    function clearInfo(){
        $("#myModal").css("z-index","-100");
        $("#userId").removeAttr("value");
        $("#project").removeAttr("value");
    };
    //传递userID和project
    function setInfo(userId,prj){
        clearInfo();
        $("#myModal").css("z-index","100000");
        $("#userId").attr("value",userId);
        $("#project").attr("value",prj);
    };
    //提交表单信息
    function PostData() {
        if($("#msg").val()!==""||$("#msgTitle").val()!==""){
            $.ajax({
                type: "POST",
                url: "<%=basePath%>/saveMsg",
                async:false,
                dataType:'json',
                data :{
                    "userId":$("#userId").val(),
                    "project":$("#project").val(),
                    "time":$('#time input[name="time"]:checked').val(),
                    "update":$('#update input[name="update"]:checked').val(),
                    "updateUrl":$("#updateUrl").val(),
                    "close":$('#close input[name="close"]:checked').val(),
                    "flag":$('#flag input[name="flag"]:checked').val(),
                    "msgContent":$("#msg").val(),
                    "msgType":$('#msgType input[name="msgType"]:checked').val(),
                    "msgTitle":$("#msgTitle").val()
                }
            });
            alert("保存规则成功");
            $("#closed").click();
        }else{
            alert("信息不完整");
        }
    };
</script>
</body>
</html>

