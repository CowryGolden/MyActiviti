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
		<title>领导-数据统计-综合分数最高TOP10</title>
		<tags:include_zznueg/>
		
		
		<style type="text/css">
			.average {
				width: 95%;
				margin: 0 auto;
			}
			
			#main1 {
				width: 100%;
				height: 500px;
				text-align: center;
			}
			
			.tittle {
				height: 45px;
				background-color: #F2F2F2;
				border-left: 3px solid #3EAFE0;
				line-height: 45px;
				font-size: 17px;
				margin: 20px 0;
				padding-left: 10px;
			}
			.shuoming{
				height: 25px;
				margin: 20px 0;
			}
		</style>
		
		<script type="text/javascript">
			var title1 = "综合分数最高的学生TOP10";
			
			$(function() {
				setIframeH();
				
				$.ajax({
					url : "zznueg/portal/leaderplatform/getCompreScoreTop10",
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
				iframe.height(body.height() + 50);
			}
		</script>

	</head>

	<body>
		<div class="average">
			<div class="shuoming"></div>
			<div id="main1"> </div>
		</div>
	</body>

</html>