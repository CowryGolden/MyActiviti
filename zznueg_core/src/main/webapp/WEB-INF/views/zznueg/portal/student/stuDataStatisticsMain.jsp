<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<title>学生-数据统计-主页</title>
		<tags:include_zznueg/>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/stuDatastatistics.css"/>
	</head>

	<body>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="javascript:void(0);">学生平台</a></li>
				<li><a href="javascript:void(0);">数据统计</a></li>
			</ul>
		</div>
		<div class="mainindex2">
			<div class="focus clear" id="focus">
				<div class="right">
					<ul>
						<li class="active"><i class="i1"></i><br />总得分</li>
						<li><i class="i2"></i><br />成绩记录</li>
						<li><i class="i3"></i><br />成绩对比</li>
						<li><i class="i4"></i><br />成绩雷达图</li>
						<li><i class="i5"></i><br />TOP10学生排行图</li>
					</ul>
				</div>
			</div>
			<div class="data">
				<iframe src="zznueg/portal/studentplatform/getStuScoresAndRanksSum4Stat" width="100%" height="100%" id="parentIframe"></iframe>
			</div>
		</div>
	</body>
	<script type="text/javascript" charset="utf-8" src="resources/zznueg/js/stuDataStatistics.js"></script>
</html>