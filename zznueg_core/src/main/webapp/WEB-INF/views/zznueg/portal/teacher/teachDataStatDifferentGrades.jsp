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
		<title>教师-数据统计-不同等级人数占比</title>
		<tags:include_zznueg/>
		
		<style type="text/css">			
			#main1 {
				width: 40%;
				height: 500px;
				border: 1px solid #ECECEC;
				float: left;
				text-align: center;
			}
			
			#main3 {
				width: 100%;
				height: 400px;
			}
			
			#main2 {
				width: 59%;
				height: 500px;
				border: 1px solid #ECECEC;
				float: right;
				text-align: center;
			}
			
			#main2_tittle {
				height: 40px;
				line-height: 40px;
				font-size: 16px;
				background-color: #d4e4eb;
			}
			
			.tittle {
				height: 45px;
				background-color: #EFEFF5;
				border-left: 3px solid #3EAFE0;
				line-height: 45px;
				font-size: 17px;
				padding-left: 10px;
			}
			
			.tablelist {
				border: none;
			}
			
			.tablelist1 {
				border: none;
			}
			
			.tablelist1 tr {
				cursor: pointer;
			}
			
			.tablelist1 tbody tr td {
				text-indent: 0;
				height: 60px;
				border: none;
				font-size: 14px;
			}
			
			.tablelist tbody tr:nth-child(even) {
				background-color: #FCFCFC;
			}
			
			.layui-progress-bar {
				margin-right: 0;
			}
			
			.td_xueyuan {
				text-align: center;
				float: left;
			}
			
			.td_percentage {
				text-align: center;
			}
			
			.td_progress {
				text-align: center;
			}			
		</style>
		
		<script type="text/javascript">
			
			$(function(){
				setIframeH();
				layui.use(['element'], function() {
					var form = layui.element();
				});
				
				$.ajax({
					url : "zznueg/portal/teacherplatform/getDiffGradesPassRate4DST",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",		   
				    success : function(data,status,XMLHttpRequest) {
				    	//alert("*********>>>>" + data.myStus);
				    	var allStusPassRate = '暂无';
				    	var myStusPassRate = '暂无';
				    	var weigePassRate = '暂无';
				    	var banshuPassRate = '暂无';
				    	var jiaoanPassRate = '暂无';
				    	var kejianPassRate = '暂无';
				    		
				    	if(null != data.allStus) {
				    		allStusPassRate = Number(data.allStus.allStusPassRate).toPercent();
				    	}				    	
				    	if(null != data.myStus) {
				    		myStusPassRate = Number(data.myStus.myStusPassRate).toPercent();				    	
					    	weigePassRate = Number(data.myStus.weigePassRate).toPercent();
					    	banshuPassRate = Number(data.myStus.banshuPassRate).toPercent();
					    	jiaoanPassRate = Number(data.myStus.jiaoanPassRate).toPercent();
					    	kejianPassRate = Number(data.myStus.kejianPassRate).toPercent();
				    	}				    	
				    	
				    	$("#allStus").html(allStusPassRate);
				    	$("#myStus").html(myStusPassRate);
				    	$("#weigeRate").html(weigePassRate);
				    	$("#banshuRate").html(banshuPassRate);
				    	$("#jiaoanRate").html(jiaoanPassRate);
				    	$("#kejianRate").html(kejianPassRate);
				    	
				    	$("#allStusPro").attr("lay-percent", allStusPassRate);
				    	$("#myStusPro").attr("lay-percent", myStusPassRate);
				    	$("#weigeRatePro").attr("lay-percent", weigePassRate);
				    	$("#banshuRatePro").attr("lay-percent", banshuPassRate);
				    	$("#jiaoanRatePro").attr("lay-percent", jiaoanPassRate);
				    	$("#kejianRatePro").attr("lay-percent", kejianPassRate);
				    	
				    	$("#main2_tittle").html("全体学生各等级人数占比");
						$.ajax({
							url : "zznueg/portal/teacherplatform/getDifferentGrades",
						    type : "POST",
						    async:false,	 			     
						    dataType : "json",
						    data:{},
						    contentType: "application/json; charset=utf-8",
						    success : function(data) {
						    	drawPie(echarts, "main3", data.allStus);	//全体学生等级饼形图
						    } 	
						});
				    } 	
				});
				
				//全体学生等级饼形图
				$("tbody tr").eq(0).click(function(){
					$("#main2_tittle").html("全体学生各等级人数占比");
					$.ajax({
						url : "zznueg/portal/teacherplatform/getDifferentGrades",
					    type : "POST",
					    async:false,	 			     
					    dataType : "json",
					    data:{},
					    contentType: "application/json; charset=utf-8",
					    success : function(data) {
					    	drawPie(echarts, "main3", data.allStus);	//全体学生等级饼形图
					    } 	
					});
				});
				//我指导的学生等级饼形图
				$("tbody tr").eq(1).click(function(){
					$("#main2_tittle").html("我指导的学生各等级人数占比");
					$.ajax({
						url : "zznueg/portal/teacherplatform/getDifferentGrades",
					    type : "POST",
					    async:false,	 			     
					    dataType : "json",
					    data:{},
					    contentType: "application/json; charset=utf-8",
					    success : function(data) {
					    	drawPie(echarts, "main3", data.myStus);	//我指导的学生等级饼形图
					    } 	
					});
				});
				//我指导的学生各等级人数占比--微格科目
				$("tbody tr").eq(2).click(function(){
					$("#main2_tittle").html("我指导的学生各等级人数占比--微格科目");
					$.ajax({
						url : "zznueg/portal/teacherplatform/getDifferentGrades",
					    type : "POST",
					    async:false,	 			     
					    dataType : "json",
					    data:{},
					    contentType: "application/json; charset=utf-8",
					    success : function(data) {
					    	drawPie(echarts, "main3", data.weige);	//我指导的学生微格科目等级饼形图
					    } 	
					});
				});
				//我指导的学生各等级人数占比--板书科目
				$("tbody tr").eq(3).click(function(){
					$("#main2_tittle").html("我指导的学生各等级人数占比--板书科目");
					$.ajax({
						url : "zznueg/portal/teacherplatform/getDifferentGrades",
					    type : "POST",
					    async:false,	 			     
					    dataType : "json",
					    data:{},
					    contentType: "application/json; charset=utf-8",
					    success : function(data) {
					    	drawPie(echarts, "main3", data.banshu);	//我指导的学生板书科目等级饼形图
					    } 	
					});
				});
				//我指导的学生各等级人数占比--教案科目
				$("tbody tr").eq(4).click(function(){
					$("#main2_tittle").html("我指导的学生各等级人数占比--教案科目");
					$.ajax({
						url : "zznueg/portal/teacherplatform/getDifferentGrades",
					    type : "POST",
					    async:false,	 			     
					    dataType : "json",
					    data:{},
					    contentType: "application/json; charset=utf-8",
					    success : function(data) {
					    	drawPie(echarts, "main3", data.jiaoan);	//我指导的学生教案科目等级饼形图
					    } 	
					});
				});
				//我指导的学生各等级人数占比--课件科目
				$("tbody tr").eq(5).click(function(){
					$("#main2_tittle").html("我指导的学生各等级人数占比--课件科目");
					$.ajax({
						url : "zznueg/portal/teacherplatform/getDifferentGrades",
					    type : "POST",
					    async:false,	 			     
					    dataType : "json",
					    data:{},
					    contentType: "application/json; charset=utf-8",
					    success : function(data) {
					    	drawPie(echarts, "main3", data.kejian);	//我指导的学生课件科目等级饼形图
					    } 	
					});
				});
				
			});
			
			/* 设置iframe的高度 */
			function setIframeH() {
				var body = $(document.body);
				var iframe = $(parent.document.getElementById('parentIframe'));
				iframe.height(body.height()+50);
			}			
			
			/* 把数字格式化为百分数 */
			Number.prototype.toPercent = function(){
				return (Math.round(this * 10000)/100).toFixed(2) + '%';
			}
		</script>

	</head>

	<body>
		<div class="average clear">
			<div class="tittle2_select clear">
				<div class="shuoming">
					<p>注：不同等级：学生的每门课考试成绩分为四个等级，60分以下（不及格）、60-69分（及格）、70-79分（一般）、80-89分（良好）、90-100分（优秀）</p>
				</div>
				<div id="main1">
					<div class="tittle">
						学院成绩通过率
					</div>
					<table class="tablelist tablelist1">
						<tbody>
							<tr>
								<td>
									全体学生
								</td>
								<td style="width: 20%;">
									<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
										<div id="allStusPro" class="layui-progress-bar layui-bg-blue" lay-percent="80%" style="margin-right:0 ;"></div>
									</div>
								</td>
								<td id="allStus">
									<%-- ${jsonData.allStus.allStusPassRate}123<%=request.getAttribute("msg")==null %> --%>
								</td>
							</tr>
							<tr>
								<td>
									我指导的学生
								</td>
								<td style="width: 20%;">
									<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
										<div id="myStusPro" class="layui-progress-bar layui-bg-blue" lay-percent="60%" style="margin-right:0 ;"></div>
									</div>
								</td>
								<td id="myStus">
									<%-- <fmt:formatNumber type="percent" value="0.2727" maxFractionDigits="3" /> --%>
									<%-- ${jsonData.myStus.myStusPassRate} --%>
								</td>
							</tr>
							<tr>
								<td>
									微格
								</td>
								<td style="width: 20%;">
									<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
										<div id="weigeRatePro" class="layui-progress-bar layui-bg-blue" lay-percent="60%" style="margin-right:0 ;"></div>
									</div>
								</td>
								<td id="weigeRate">
									<%-- ${jsonData.myStus.weigePassRate} --%>
								</td>
							</tr>
							<tr>
								<td>
									板书
								</td>
								<td style="width: 20%;">
									<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
										<div id="banshuRatePro" class="layui-progress-bar layui-bg-blue" lay-percent="60%" style="margin-right:0 ;"></div>
									</div>
								</td>
								<td id="banshuRate">
									<%-- ${jsonData.myStus.banshuPassRate} --%>
								</td>
							</tr>
							<tr>
								<td>
									教案
								</td>
								<td style="width: 20%;">
									<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
										<div id="jiaoanRatePro" class="layui-progress-bar layui-bg-blue" lay-percent="60%" style="margin-right:0 ;"></div>
									</div>
								</td>
								<td id="jiaoanRate">
									<%-- ${jsonData.myStus.jiaoanPassRate} --%>
								</td>
							</tr>
							<tr>
								<td>
									课件
								</td>
								<td style="width: 20%;">
									<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
										<div id="kejianRatePro" class="layui-progress-bar layui-bg-blue" lay-percent="60%" style="margin-right:0 ;"></div>
									</div>
								</td>
								<td id="kejianRate">
									<%-- ${jsonData.myStus.kejianPassRate} --%>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
				<div id="main2">
					<div id="main2_tittle">
						全体学生各等级人数占比
					</div>
					<div id="main3"></div>
				</div>
			</div>
		</div>

	</body>
</html>