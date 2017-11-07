<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
		<title>学生-数据统计-成绩雷达图</title>
		<tags:include_zznueg/>	
		
		<style type="text/css">
			#main1 {
				height: 500px;
				width: 100%;
				text-align: center;
				margin-top: 20px;
			}
			
			.tittle {
				height: 45px;
				background-color: #F2F2F2;
				border-left: 3px solid #3EAFE0;
				line-height: 45px;
				font-size: 17px;
				padding-left: 10px;
			}
		</style>

		<script type="text/javascript">
			var title1 = "本次考试成绩的雷达图";
			
			$(function(){
				setIframeH();
				
				$.ajax({
					url : "zznueg/portal/studentplatform/getScoresRadar",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	drawRadar(echarts,"main1",data,title1);  //本次考试成绩的雷达图
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
		<div class="average">
			<div class="tittle2_select clear">
				<div class="tittle">
					本次考试成绩的雷达图
				</div>
				<div id="main1"></div>
			</div>
		</div>
	</body>

</html>


