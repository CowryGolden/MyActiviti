<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<title>领导-数据统计-不及格人数对比</title>
		<tags:include_zznueg/>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/leaderDataStatFailStuCountComp.css" />
		
		<script type="text/javascript">
			var title1 = "全体学生不及格人数对比图";
		
			$(function() {
				setIframeH();
				
				$.ajax({
					url : "zznueg/portal/leaderplatform/getCurrAndPrevFialStuCountComp",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	drawBar(echarts,"main1",data,title1,"center");  //柱状图
				    } 	
				});
				
			});
			
			/* 设置iframe的高度 */
			function setIframeH() {
				var body = $(document.body);
				var iframe = $(parent.document.getElementById('parentIframe'));
				iframe.height(body.height() + "50");
			}
		</script>
		
	</head>

	<body>
		<div class="average">
			<div class="shuoming">
				<p>注：上次考试学生成绩：每个学生每门课有4次考核机会，根据自己意愿使用考核次数，最后单科成绩按最高分数计算</p>
			</div>
			<div id="main1"> </div>
		</div>
	</body>

</html>