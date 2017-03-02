<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
<head>
    <title>性能监控</title>
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
            padding-top: 5px;
            padding-bottom: 5px;
            font-size: 12pt;
        }

        .table {
            font-size: 10pt;
        }

        #b_pageinfo, #pageinfo {
            text-align: center;
        }

        .pager {
            height: 30px;
            line-height: 30px;
            font-size: 12px;
            margin: 25px 0px;
            text-align: center;
            color: #2E6AB1;
            overflow: hidden;
        }

        .pager a {
            border: 1px solid #9AAFE5;
            color: #2E6AB1;
            margin: 0px 5px;
            padding: 5px 8px;
            text-decoration: none;
        }

        .pager a.hover, .pager a.active {
            background-color: #2E6AB1;
            border-color: #000080;
            color: #FFF;
        }

        .pager a.disabled {
            color: #C8CDD2;
            cursor: auto;
        }
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
            <!--性能监控列表开始-->
            <section class="span12">
                <div class="row span12">
                    <legend contenteditable="true">性能监控列表</legend>
                    <form id="black_form">
                        <fieldset>
                            <div class="row form-group">
                                <div class="span12">
                                    <label class="span10">
                                        日期区间：
                                        <input type="text" name="starttime" class="span2" id="b_starttime"
                                               onclick="WdatePicker({readOnly:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})">－
                                        <input type="text" name="endtime" class="span2" id="b_endtime"
                                               onclick="WdatePicker({readOnly:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'b_starttime\')}'})">
                                        用时(毫秒)≥
                                        <input type="text" name="count" id="b_count" value="1" class="span1">
                                        <input class="btn btn_2 span1"
                                               style="margin-left: 20px;height:40px;margin-bottom:10px;"
                                               onclick="getMonitorList('1','allTime');creatPageNum('allTime');"
                                               type="button"
                                               value="查询"/>
                                    </label>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                    <table class="table table-striped span10" id="b_blacklist">
                        <tr>
                            <td><a href="javascrpit:void(0)"
                                   onclick="getMonitorList('1','className');creatPageNum('className');">类名</a></td>
                            <td><a href="javascrpit:void(0)"
                                   onclick="getMonitorList('1','methodName');creatPageNum('methodName');">方法名</a></td>
                            <td><a href="javascrpit:void(0)"
                                   onclick="getMonitorList('1','allTime');creatPageNum('allTime');">时长（毫秒）</a></td>
                            <td><a href="javascrpit:void(0)"
                                   onclick="getMonitorList('1','createTime');creatPageNum('createTime');">
                                监控时间</a></td>
                        </tr>
                    </table>
                </div>
                <div id="b_pageinfo" class="row span10">
                    <input type="hidden" id="b_totalpage" class="btn btn-default btn-sm"/>
                </div>
            </section>
            <!--性能监控列表结束-->
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
</body>
<script type="text/javascript">

    //获取性能监控列表方法
    function getMonitorList(btn_val, descStr) {
        $(".b_blacklist").remove();
        if (btn_val == "1" || btn_val <= 1) {
            getBlackTotalPage("1", descStr);
            $("#b_pageinfo .pager").remove();
        }
        //通过ajax获取数据
        $.ajax({
            url: '<%=basePath%>/getMonitorList',
            type: 'post',
            data: {
                "count": $("#b_count").val(),
                "starttime": $("#b_starttime").val(),
                "endtime": $("#b_endtime").val(),
                "currentpage": btn_val,
                "descStr": descStr
            },
            async: false,
            dataType: 'json',
            success: function (result) {
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        $("#b_blacklist").append("<tr class='b_blacklist'><td>" + result[i].className + "</td><td>" + result[i].methodName + "</td><td>" + result[i].allTime + "</td><td>" + result[i].createTime + "</td></tr>");
                    }
                }
            },
            error: function (errorMsg) {
                alert("性能监控列表获取失败");
            }
        });
    }
    ;
    function getBlackTotalPage(btn_val, descStr) {
        $(".b_blacklist").remove();
        //通过ajax获取数据
        $.ajax({
            url: '<%=basePath%>/getMonitorTotalPage',
            type: 'post',
            data: {
                "count": $("#b_count").val(),
                "starttime": $("#b_starttime").val(),
                "endtime": $("#b_endtime").val(),
                "currentpage": btn_val,
                "descStr": descStr
            },
            async: false,
            dataType: 'text',
            success: function (result) {
                if (result) {
                    $("#b_totalpage").attr("value", result);
                }
            },
            error: function (errorMsg) {
                alert("性能监控列表获取失败");
            }
        });
    }

    function creatPageNum(descStr) {
        $("#b_pageinfo").pagination({
            count: parseInt($("#b_totalpage").val()) * 10, //总数
            size: 10, //每页数量
            index: 1,//当前页
            lrCount: 3,//当前页左右最多显示的数量
            lCount: 1,//最开始预留的数量
            rCount: 1,//最后预留的数量
            callback: function (options) {
                getMonitorList(options.index, descStr);
            }
        });
    }
</script>
</html>

