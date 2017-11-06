<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
/*  此种从session中获取试卷ID的方式不妥，改由直接从数据库实时获取的方式
	String paperId = String.valueOf(request.getSession().getAttribute("paperId"));
    paperId = paperId == null ? "BFC50772953C11E7ACED3497F6DC7496" : paperId; */
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>"/>
		<meta charset="UTF-8">
		<title>学生平台-（在线测评）分析结果-主页</title>
		<tags:include_zznueg/>		
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/online.css"/>
		
		<script type="text/javascript">
			$(function() {				
				layui.use(['element'], function() {
					var form = layui.element();
				});
				//加载分析结果表格数据
				loadAnalysisTableData();
				
				$(".qiehuan span").click(function(){
					$(".qiehuan span").removeAttr("class","show");
					$(this).addClass("show");
				});
				$(".qiehuan span").eq(0).click(function(){
					$("#tu iframe").attr("src","zznueg/portal/studentplatform/stuOnlSurAnaResultRadar");
				});
				$(".qiehuan span").eq(1).click(function(){
					$("#tu iframe").attr("src","zznueg/portal/studentplatform/stuOnlSurAnaResultBar");
				});
				$(".qiehuan span").eq(2).click(function(){
					$("#tu iframe").attr("src","zznueg/portal/studentplatform/stuOnlSurAnaResultLine");
				});
				$(".qiehuan span").eq(3).click(function(){
					$("#tu iframe").attr("src","zznueg/portal/studentplatform/stuOnlSurAnaResultStackBar");
				});
			});
			
			/* 加载分析结果表格数据 */
			function loadAnalysisTableData() {
				$.ajax({
            		url: "zznueg/portal/studentplatform/getCategorySumScore4DST",
            		method: "POST",
            		async: false,
            		dataType: "html",
            		data: {},
            		success: function(data) {            			
            			$("#analysisResultDiv").html($(data).html());
            			//alert(typeof($(data).find("#noData")) == "object");
            			//alert(JSON.stringify($(data).find("#noData").html()) == "null");
            			if(JSON.stringify($(data).find("#noData").html()) == "null") {	//只有调查问卷“得分结果分析”列表有数据时才显示建议内容，否则不显示
            				$("#tipsDiv").show();
            			}
            		}
            	});
			}
				
		</script>
	
	</head>

	<body>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="javascript:void(0);">学生平台</a></li>
				<li><a href="javascript:void(0);">分析结果</a></li>
			</ul>
		</div>
		<div class="average">

			<div id="main2">
				<p style="color: #4596E5;padding-left: 8px;text-align: left;line-height: 40px;font-size: 22px;margin: 20px 0;">* 得分结果分析</p>
				<div id="analysisResultDiv"></div>
				
				<div class="qiehuan">
					<span class="show">雷达图</span>
					<span>柱状图</span>
					<span>折线图</span>
					<span>条形图</span>
				</div>
				<div id="tu">
					<iframe src="zznueg/portal/studentplatform/stuOnlSurAnaResultRadar" width="100%" height="450px" id="parentIframe"></iframe>
				</div>
				
				<div id="main4"></div>
				<div id="main5"></div>
				<div id="main6"></div>
				
				<div id="tipsDiv" style="display: none;">
					<div style="color: #4596E5;padding-left: 8px;text-align: left;line-height: 40px;font-size: 22px;margin: 20px 0;">* 建议</div>
					<div class="Suggest">
						<div class="SuggestDiv">
							<img src="resources/zznueg/img/suggest.png" />
						</div>
						
						<div class="SuggestDiv" style="margin-top: 5%;">
							<p style="font-size: 14px;color:#3eafe0;text-align: left;"><!-- * （如果4门科目的分数都超过平均分80分出现下面的建议） --></p>
							<div class="suggestGood">
								<img src="resources/zznueg/img/zan.png" />
								<p style="">继续加油</p>
							</div>
						</div>					
					</div>
				</div>
				
			</div>
		</div>

	</body>

</html>