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
		<title>教师-数据统计-我指导的学生成绩占比图</title>
		<tags:include_zznueg/>
		
		<style type="text/css">
			.box{
				padding: 70px;
			}
			#main1 {
				width: 49%;
				float: left;
				height: 520px;
				text-align: center;
				background-color: #FCFCFC;
			}
			
			#main2 {
				width: 49%;
				float: left;
				text-align: center;
				height: 520px;
				background-color: #FCFCFC;
			}
		</style>
		
		<script type="text/javascript">
			var title1 = ""; //"我指导的学生本次与上一次平均成绩对比图";	
			var title2 = ""; //"我指导的学生平均成绩与全校平均成绩对比图";
			
			$(function(){
				setIframeH();
				
				$.ajax({
					url : "zznueg/portal/teacherplatform/getMyStusSubScoresRatio",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	//var jsondata = eval( "(" + data + ")" ); //访问成功后返回的data已经是一个jsonObject对象，里面包含双引号等字符，因此不能再使用eval转换了
				    	//alert(data);
				    	drawBar(echarts,"main1",data,title1,null);  //柱状图
				    } 	
				});
				
				$.ajax({
					url : "zznueg/portal/teacherplatform/getMyAndAllAvgSubScoreCompare",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	drawBar(echarts,"main2",data,title2,null);  //柱状图
				    } 	
				});
				
			});	
			
			/* 设置iframe的高度 */
			function setIframeH() {
				var body = $(document.body);
				var iframe = $(parent.document.getElementById('parentIframe'));
				iframe.height(body.height()+600);
			}
		</script>	
		
	</head>

	<body>
		<div class="box clear">
			<div id="main1"></div>
			<div id="main2"></div>
		</div>
	</body>

</html>