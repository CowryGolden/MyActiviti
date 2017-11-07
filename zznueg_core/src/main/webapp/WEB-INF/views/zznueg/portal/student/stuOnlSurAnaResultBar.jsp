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
		<title>学生平台-（在线测评）分析结果-柱状图</title>
		<tags:include_zznueg/>
		<style type="text/css">
			#main3, #main4, #main5, #main6 {
				width: 100%;
				height: 400px;
				text-align: center;
				margin: 20px auto;
			}
		</style>
		
		<script type="text/javascript">
			$(function(){
				setIframeH();
				
				$.ajax({
					url : "zznueg/portal/studentplatform/getAnalysisResult4SEC",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	drawBar(echarts, "main4" ,data.echartsBar, null, null);  //柱状图
				    } 	
				});
				
			});
		
			/* 设置iframede的高度 */
			function setIframeH() {
				var body = $(document.body);
				var iframe = $(parent.document.getElementById('parentIframe'));
				iframe.height(body.height()+50);
			}
		</script>
	</head>
	<body>
		<div id="main4"></div>
	</body>
</html>
