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
		<title>领导-数据统计-平均成绩对比</title>
		<tags:include_zznueg/>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/leaderDataStatAvgScoreComp.css" />
		
		<script type="text/javascript">
			var title1 = "各科平均与单科最高成绩对比图";
			var title2 = "本次与上次考试平均成绩对比图";
		
			$(function() {
				setIframeH();
				
				$.ajax({
					url : "zznueg/portal/leaderplatform/getAvgSubScoreCompare",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	drawBar(echarts,"main1",data,title1,"center");  //柱状图
				    } 	
				});
				
				$.ajax({
					url : "zznueg/portal/leaderplatform/getCurrAndPrevAvgSubScoreCompare",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	drawBar(echarts,"main2",data,title2,"center");  //柱状图
				    } 	
				});
				
			});
			
			/* 设置iframe的高度 */
			function setIframeH() {
				var body = $(document.body);
				var iframe = $(parent.document.getElementById('parentIframe'));
				iframe.height(body.height() + 50);
			}
		</script>
	</head>

	<body>
		<div class="average clear">
			<div class="tittle2_select clear">
				<div class="shuoming">
					<p>注：1 每个学生每门课有4次考核机会，根据自己意愿使用考核次数，最后单科成绩按最高分数计算</p>
					<p style="text-indent: 4em;">2 每次考试，每个学生每门课有4次考核机会</p>
 
				</div>
				<div id="main1"></div>
				<div id="main2"></div>
			</div>
		</div>
	</body>

</html>