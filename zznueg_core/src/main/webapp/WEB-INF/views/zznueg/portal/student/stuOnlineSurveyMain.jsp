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
		<title>学生平台-在线测评</title>
		<tags:include_zznueg/>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/teachOnlEval.css"/>
		
		<script type="text/javascript">
		
			$(function() {
				loadOnlineSurvey();  //加载调查问卷
				
				layui.use(['form', 'laydate'], function() {
					var form = layui.form(),
						laydate = layui.laydate;					
					//监听提交
					form.on('submit(formDemo)', function(data) {
						var paperId = $("#paperId").val();
						var selValues = [];		//每一个选中的radio所有数据（包括自定义数据）json对象数组
						//var jsonStr = JSON.stringify(data.field);  //选中的radio的name-value键值对
						//layer.msg(jsonStr);
						$("input[type='radio']:checked").each(function() { //循环所有被选中的radio
						//$("input[type='radio']").each(function() { //循环所有radio	
							if($(this).attr("checked")) {
								//selValues.push($(this).val());
								//selValues.push($(this).attr("data-sans"));
								var jsonObj = {};  //将每一个选中的radio封装成json对象
								var questionId = $(this).attr("name").replace(/(^\s*)|(\s*$)/g, "");  //相当于trim()
								var questionCategory = $(this).attr("data-qtype").replace(/(^\s*)|(\s*$)/g, "");  //问题分类
								var studentAnswer = $(this).attr("data-option").replace(/(^\s*)|(\s*$)/g, "");  //学生所选择的答案
								var stuAnswerScore = $(this).val().replace(/(^\s*)|(\s*$)/g, "");  //学生选择答案的分值
								var questionAnswer = $(this).attr("data-sans").replace(/(^\s*)|(\s*$)/g, ""); //题目的标准答案
								jsonObj.questionId = questionId;
								jsonObj.questionCategory = questionCategory;
								jsonObj.studentAnswer = studentAnswer;
								jsonObj.stuAnswerScore = stuAnswerScore;
								jsonObj.questionAnswer = questionAnswer;
								selValues.push(jsonObj);  //将选中的radio对象放于数组中
							} else {
								//待优化，定位到没选中的题目
							}
							
						});
						//alert("serializeArray=" + JSON.stringify($("form").serializeArray()));
						var formData = JSON.stringify(selValues);
						saveSurveyPaperInfo(paperId, formData);
						return false;
					});
				});
				
			});
			
			/* 加载调查问卷 */
			function loadOnlineSurvey() {
				$.ajax({
            		url: "zznueg/portal/studentplatform/getOnlineEvalPaperInfo",
            		method: "POST",
            		async: false,
            		dataType: "html",
            		data: {},
            		success: function(data) {
            			$("#surveyPaperDiv").html($(data).html());
            		}
            	});
			}
			
			/* 保存学生提交的在线测评记录 */
			function saveSurveyPaperInfo(paperId, formData) {
				$.ajax({
            		url: "zznueg/portal/studentplatform/saveStuEvalRecordInfo",
            		method: "POST",
            		async: false,
            		dataType: "json",
            		data: {
            			"paperId" : paperId,
            			"formData" : formData
            		},
            		success: function(data) {
            			layer.msg(data.message);
            			$(".layui-form")[0].reset();  //提交成功后将form表单所有对象全部复原
            		},
            		error : function(data) {
            			layer.msg(data.message);
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
				<li><a href="javascript:void(0);">在线测评</a></li>
			</ul>
		</div>
		<div id="surveyPaperDiv" class="neirong"></div>
	</body>	

</html>