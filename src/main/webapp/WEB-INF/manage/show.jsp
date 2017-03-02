<%--
  Created by IntelliJ IDEA.
  User: dyenigma
  Date: 2016/10/11
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html lang="en-us">
<head>
    <title>演示页面</title>
    <meta charset="utf-8">
    <meta name="description" content="4everyone">
    <meta name="keywords" content="4everyone">
    <meta name="author" content="4everyone">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap-responsive.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/style.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/style.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/mobilemenu.css">
    <link rel="icon" href="<%=basePath%>/resources/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="<%=basePath%>/resources/images/favicon.ico" type="image/x-icon"/>
    <script type="text/javascript" src="<%=basePath%>/resources/js/jquery-1.7.1.min.js"></script>
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
    <script>
        $(function () {
            //每隔一段时间就调用,这里是1分钟,单位毫秒
            window.setInterval(getInfo, Math.random()*10* 60 * 1000);
            function getInfo() {
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>/user/save",
                    data: {
                        'Project': 'myProject',
                        'ClientType': 'Chrome',
                        'OtherData': '1001',
                        'UserId':Math.round(Math.random()*100000+431)+"A",
                        'ClientId': '8edfdfa75c104c22aa0a6sdb9e6ef4da',
                        'ClientVersion': '55.1',
                        'KeyFlag': '0'
                    },
                    dataType: 'json',
                    success: function (info) {
                        $("#show").html(JSON.stringify(info));
                    }
                });
            }
        });
    </script>
</head>
<body>

<header>
    <div class="container">
        <div class="row">
            <div class="span12">
                <a class="logo" href="<%=basePath%>/manage/main">信息推送系统</a>
                <button class="nav-button">menu</button>
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
<div class="container-fill">
    <div class="row_1" id="about" style="height: 450px;">
        这个页面用于演示定时访问远程接口，传送用户ID等相关信息，然后处理返回值

        <div id="show">

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
</div><!--/container-fill-->
</body>

</html>