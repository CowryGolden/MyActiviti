<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>郑州师范学院评估系统主界面</title>
    <tags:include/>
    <link rel="stylesheet" href="resources/plugins/Html5Clock/css/clock.css"/>
    <!-- <script type="text/javascript" src="resources/plugins/Html5Clock/js/jquery-2.1.1.min.js"></script> -->  <!-- 有冲突，tags中已存在，不再引入 -->
	<script type="text/javascript" src="resources/plugins/Html5Clock/js/clock-1.1.0.min.js"></script>
	<script type="text/javascript" src="resources/public/js/jquery.cookie.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false"
     style="height: 88px; overflow: hidden;background:url(resources/core/images/topbg.png) repeat-x;"
     href="manage/mTop"></div>
<div data-options="region:'west',split:true,title:'导航菜单'" style="width: 220px;" href="manage/mLeft" ></div>
<div data-options="region:'south',border:false" style="height: 16px !important" href="manage/mFoot"></div>
<div data-options="region:'center',plain:true" style="overflow: hidden; border: 0px" href="manage/mCenter"></div>
</body>
</html>
