<%--
  Created by IntelliJ IDEA.
  User: dyenigma
  Date: 2016/9/23
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
<head>
    <title>404 error page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style>
        html,body,div,h1{margin:0;padding:0;border:0;font-size:100%;font:inherit;vertical-align:baseline;}
        img{max-width:100%;}
        body {
            background-size: 100%;
            font-family: 'open_sanslight';
            font-size: 100%;
            background: url(<%=basePath%>/resources/images/bg.jpg) no-repeat fixed 100% 100%;
            background-size: cover;
        }
        .wrap
        {
            width:70%;
            margin:5.2% auto 4% auto;
        }
        .logo
        {
            padding: 1em;
            text-align: center;
            padding: 1% 1% 5% 1%;
        }
        .logo h1{
            display: block;
            padding: 2em 0em;
        }
        .logo span{
            font-size: 2em;
            color:#fff;
        }
        .logo span img{
            width:40px;
            height: 40px;
            vertical-align:bottom;
            margin: 0px 10px;
        }
    </style>
</head>
<body>
<div class="wrap">
    <div class="content">
        <div class="logo">
            <h1><img src="<%=basePath%>/resources/images/404.png"/></h1>
            <span><img src="<%=basePath%>/resources/images/signal.png"/>Oops! The Page you requested was not found!</span>
        </div>
    </div>
</div>
</body>
</html>