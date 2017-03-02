<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
<head>
    <title>数据分析</title>
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
    <script src="<%=basePath%>/resources/js/henan.js"></script>
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
        .pager{height:30px;line-height:30px;font-size: 12px;margin: 25px 0px;text-align: center;color: #2E6AB1;overflow: hidden;}
        .pager a{border:1px solid #9AAFE5;color:#2E6AB1;margin:0px 5px;padding:5px 8px;text-decoration: none;}
        .pager a.hover,.pager a.active{background-color:#2E6AB1;border-color:#000080;color:#FFF;}
        .pager a.disabled{color:#C8CDD2;cursor:auto;}
    </style>

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
            <!--在线用户量变化趋势开始-->
            <div class="row span12">
                <legend contenteditable="true">在线用户变化趋势</legend>
                <form id="prj_form" >
                    <fieldset>
                        <div class="row form-group">
                            <div class="span12">
                                <label class="span10">
                                    项目名称：
                                    <select name="c_project" id="c_project" class="form-control" style="height:40px;">
                                    </select>
                                    统计日期：
                                    <input type="text" name="cday" id="cday" onclick="WdatePicker({readOnly:false,maxDate:'%y-%M-{%d-1}'})">
                                    <input class="btn btn_2 span1" style="margin-left: 20px;height:40px;margin-bottom:10px;" onclick="loadChart();" type="button" value="查询"/>
                                </label>
                            </div>
                        </div>
                    </fieldset>
                </form>
                <section class="span8">
                    <div  id="cmap" style="width:800px;height:500px;float:left"></div>
                    </section>
                    <div id="citylist" class="span3" style="margin-left:20px;width:200px;float:left;margin-top:20px;height: 500px;">
                        <table class="table table-striped" id="s_citylist">
                            <tr> <td style="text-align: center;font-weight: bold">市</td><td style="text-align: center;font-weight: bold">在线用户数</td></tr>
                        </table>
                    </div>
            </div>
            <!--在线用户量变化趋势结束-->
        </div>
    </div>
</div>
<footer>
    <div class="container">
        <div class="row">
            <div class="span12 text-center">
                &copy;  电子有限公司 &nbsp; | &nbsp; all Rights Reserved
            </div>
        </div>
    </div>
</footer>
<script type="text/javascript">
    //获取当前日期
    function getNowFormatDate() {
        var date = new Date();
        date.setDate(date.getDate()-1);
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
    };
    function setDate(){
        $("#cday").attr("value",getNowFormatDate());
    };
    setDate();
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
    var myChart = echarts.init(document.getElementById('cmap'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '全省各地区在线用户量分布图',
            subtext: '日数据',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['在线用户量']
        },
        visualMap: {
            left: 'left',
            top: 'bottom',
            max: 20000,
            text: ['高','低'],           // 文本，默认为数值文本
            calculable: true,
            inRange: {
                color: ['lightskyblue','yellow', 'orangered']
            }
        },
        series: [
            {
                name: '用户量',
                type: 'map',
                mapType: '河南',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:[]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.showLoading({text: '正在努力的读取数据中...'});
    myChart.hideLoading();
    //按小时在线用户量变化曲线
    function getMapData(project){
        //获取图表的options对象
        var options=myChart.getOption();
        //通过ajax获取数据
        $.ajax({
            url:'<%=basePath%>/getCityDayCount',
            type:'post',
            data:{
                "project":project,
                "day":$("#cday").val()
            },
            async:false,
            dataType:'json',
            success:function(result){
                if(result){
                    options.series[0].data=result;
                    myChart.setOption(options);
                    $(".s_city").remove();
                    for(var i=0;i<result.length;i++){
                        $("#s_citylist").append("<tr class='s_city'><td style='text-align: center;'>"+result[i].name+"</td><td style='text-align: right;'>"+result[i].value+"</td></tr>");
                    }
                }
            },
            error:function(errorMsg){
                alert("不好意思，图表正在加载中");
                myChart.hideLoading();
            }
        });
    }
    function loadChart(){
        var prj=$("#c_project").val();
        getMapData(prj);
    };
    loadChart();
</script>
</body>
</html>

