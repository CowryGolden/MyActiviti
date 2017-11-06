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
		<title>领导-数据统计-不同学院综合平均分对比图</title>
		<tags:include_zznueg/>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/leaderDataStatCompreAvgScoreComp.css" />
		
		<script type="text/javascript">
			var acaIds = "";	//选择查询的学院ID数组字符串
			
			$(function() {
				setIframeH();  //设置iframe高度
				layui.use(['element'], function() {
					var form = layui.element();
				});
				loadAcaCheckbox();	//加载复选框数据
				loadAcaSubScores(acaIds);  //加载tablelist数据
				loadStackBar(acaIds);  //加载堆叠柱状图
				
				//点击选择学院按钮弹出复选框div
				$(".tittle2_select #xuanze").click(function() {
					$(".tittle2_select ul").slideToggle();
				});
				//点击提交按钮处理
				$(".xysubmit").click(function() {
					var checkValues = [];
					//由于复选框一般选中的是多个,所以可以循环输出 
					$("input[type='checkbox']:checked").each(function() {
						checkValues.push($(this).val());						 
					});
					acaIds = checkValues.join(",");  //数组转字符串
					
					loadAcaSubScores(acaIds);  //根据传递参数加载tablelist数据
					loadStackBar(acaIds);  //根据传递参数加载堆叠柱状图
					
					$(".tittle2_select ul").slideToggle();
					$("input[type='checkbox']").removeAttr("checked");  //提交后移除选择内容
				});	
				
				//复选框选择超过5个提示
				$('input[type=checkbox]').click(function(){
					if($("input[name='acaName']:checked").length > 5) {
						$(this).removeAttr("checked");
						alert("最多选择5个学院!")
					}
				});				
				
			});	
			
			/* 加载学院选择复选框数据  */
			function loadAcaCheckbox() {
				$.ajax({
            		url: "zznueg/portal/leaderplatform/getAcaCheckboxData4ComAvg",
            		method: "POST",
            		async: false,
            		dataType: "html",
            		data: {},
            		success: function(data) {
            			//$("#checkboxDiv").html($(data).html()); 
            			$("#xuanze").after($(data).html());    //在选择div后面追加        			
            		}
            	});
			}
			
			/* 加载堆叠柱状图 */
			function loadStackBar(acaIds) {
				$.ajax({
					url : "zznueg/portal/leaderplatform/getCompreAvgScoreGroupbyAcaId4SEC",
				    method : "POST",
				    async : false,	 			     
				    dataType : "json",
				    data : {"acaIds" : acaIds},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	drawStackBar(echarts,"main3",data);  //堆叠柱状图
				    } 	
				});
			}
			
			/* 加载不同学院不同科目的平均分，tablelist数据 */
			function loadAcaSubScores(acaIds) {
				$.ajax({
            		url: "zznueg/portal/leaderplatform/getCompreAvgScoreGroupbyAcaId4DST",
            		method: "POST",
            		async:false,
            		dataType: "html",
            		data: {"acaIds" : acaIds},
            		success: function(data) {
            			$("#tbDataDiv").html($(data).html()); 
            		}
            	});
			}
			
			/* 设置iframe的高度 */
			function setIframeH() {
				var body = $(document.body);
				var iframe = $(parent.document.getElementById('parentIframe'));
				iframe.height(body.height() * 1.5);
			}
		</script>
	</head>

	<body>
		<div class="average clear">
			<div class="tittle2_select clear">
				<div id="xuanze"> 学院院系选择 </div>
				<div id="checkboxDiv"></div>				
			</div>
		
			<div id="main3"></div>
			
			<div class="Export"><a href="#">导出CSV文件</a></div>
			<div id="tbDataDiv"></div>			
		</div>	
	</body>

</html>