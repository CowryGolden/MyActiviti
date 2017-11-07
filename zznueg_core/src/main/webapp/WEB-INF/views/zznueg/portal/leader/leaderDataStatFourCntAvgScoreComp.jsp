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
		<title>领导-数据统计-四次考试平均成绩</title>
		<tags:include_zznueg/>
		
		<style type="text/css">
			.average {
				width: 100%;
			}
			
			#main1 {
				width: 100%;
				height: 500px;
				float: left;
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
			.shuoming{
				height: 25px;
				margin: 20px 0;
			}
		</style>
		
		<script type="text/javascript">
			var title1 = "四门科目考试每次的平均成绩对比（全体学生）";
			
			$(function() {
				setIframeH();
				$.ajax({
					url : "zznueg/portal/leaderplatform/getFourSubsAvgScoreByCnt",
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
		<div class="average clear">
			<div class="shuoming"></div>
			<div id="main1"></div>
		</div>
	</body>

</html>