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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>教师-数据统计-主页</title>
		<tags:include_zznueg/>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/teachDataStatistics.css"/>
	</head>

	<body>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="javascript:void(0);">教师平台</a></li>
				<li><a href="javascript:void(0);">数据统计</a></li>
			</ul>
		</div>
		<div class="mainindex2">
			<div class="focus clear" id="focus">
				<div class="right">
					<ul>
						<li class="active"><i class="i1"></i><br />不同等级人数占比</li>
						<li><i class="i2"></i><br />我指导的学生成绩占比图</li>
					</ul>
				</div>
			</div>
			<div class="data">
				<iframe src="zznueg/portal/teacherplatform/dataStatDiffGradesRate" width="100%" height="100%" id="parentIframe"></iframe>
			</div>
		</div>
	</body>
	<script src="resources/zznueg/js/teachDataStatistics.js" type="text/javascript" charset="utf-8"></script>

</html>