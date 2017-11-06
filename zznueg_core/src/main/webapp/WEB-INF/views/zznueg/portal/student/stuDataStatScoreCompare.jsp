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
		<title>学生-数据统计-成绩对比</title>
		<tags:include_zznueg/>	
		
		<style type="text/css">
			.average {
				width: 95%;
				margin:  0 auto;
			}
			
			#main1 {
				height: 500px;
				width: 100%;
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
			
			.tablelist tr td {
				border: 1px solid gainsboro;
			}
			
			.paiming ul li {
				line-height: 50px;
				text-align: center;
				padding: 0 5px;
				float: left;
			}
			
			.paiming ul li div {
				margin-left: 15px;
			}			
		</style>
		
		<script type="text/javascript">
			var title1 = "我的成绩对比图";
			
			$(function(){
				setIframeH();
				
				$.ajax({
					url : "zznueg/portal/studentplatform/getSubScoreCompare",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	drawBar(echarts,"main1",data,title1,"center");  //我的成绩对比图
				    } 	
				});
				
			});
			
			/* 设置iframe的高度 */
			function setIframeH() {
				var body = $(document.body);
				var iframe = $(parent.document.getElementById('parentIframe'));
				iframe.height(body.height()+50);
			}	
		</script>
		
	</head>

	<body>
		<div class="average clear">
			<div class="tittle2_select clear">
				<div class="shuoming">
					<p>
						注：我的本次考试成绩和全体学生平均成绩做对比；
					</p>
				</div>
				<div id="main1"></div>
			</div>
		</div>
	</body>

</html>