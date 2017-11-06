<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    
    String pageFlag = request.getParameter("pageFlag");
    if(pageFlag == null) {pageFlag = "";}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>教师-学生评分-视频播放</title>
		<tags:include/>
		<link href="resources/zznueg/css/style.css" rel="stylesheet" type="text/css" />
		
		<style type="text/css">
			/*打分区域样式*/			
			.scoring {
				width: 229px;
				height: 194px;
				background-color: #eaeff3;
				position: fixed;
				top: 150px;
				right: 100px;
			}
			
			.zzsfLogo {
				position: absolute;
				top: -48px;
				left: 78px;
			}
			
			.zzsfPeople,
			.zzsfBack {
				position: absolute;
				top: 10px;
			}
			
			.zzsfPeople {
				left: 20px;
			}
			
			.zzsfBack {
				right: 20px;
			}
			
			.onlineScoring {
				text-align: center;
				line-height: 50px;
				margin-top: 50px;
				color: gray;
			}
			
			.scorInput {
				text-align: center;
			}
			
			.selects {
				width: 40px;
				height: 20px;
				text-align: center;
				margin: 0 auto;
			}
			
			.scorButton1,
			.scorButton {
				height: 25px;
				width: 70px;
				background-color: #36b3df;
				color: #fff;
				margin: 20px auto;
				text-align: center;
				font-size: 14px;
				line-height: 25px;
				border: 1px solid gainsboro;
				cursor: pointer;
			}
			
			.scorButton1,.scorButton:hover {
				border: 1px solid #36b3df
			}
		</style>
		
		<script type="text/javascript">
			var pageFlag = ""; //学生科目评估页面，双击科目记录跳转至此页面-此标志位页面来源标志，初始化为空字符串；
			//此标志是为了若是从概览页面一直跳转而来，还应能原路返回
			pageFlag = "<%=pageFlag%>";
		
			$(function() {
				$(".scorButton").click(function() {
					var pid = $("#uploadPid").val();
					var evalScore = $("#evalScore").val();
					var stuUserId = $("#stuUserId").val();
					//alert("stuUserId=" + stuUserId);
					if(evalScore == null || evalScore == "") {
						alert("请输入评估分数！");
						$("#evalScore").val("");
						$("#evalScore").focus();
						return;
					}
					if(evalScore < 0 || evalScore > 100) {
						alert("错误，评分必须在【0-100】之间！");
						return;
					}
					
					$.ajax({
						url: "zznueg/portal/teacherplatform/saveEvalScore",
						method: "POST",
						dataType: "json",
						async:false,
	            		data: {
	            			"pid": pid,
	            			"evalScore": evalScore
	            		},
	            		success: function(data) {            			
	            			if(data.status) {		            				
	            				$.messager.show({
	    	                        title: data.title,
	    	                        msg: data.message,
	    	                        timeout: 1000 * 2
	    	                    });
	            				if(pageFlag == "fromStuOverview") {  //从“学生概览”页面跳转而来，返回时重新返回该界面
	            					window.location.href="<%=basePath%>zznueg/portal/teacherplatform/studentDetail?stuUserId=" + stuUserId + "&pageFlag=fromStuOverview";
	            				} else {
	            					window.location.href="<%=basePath%>zznueg/portal/teacherplatform/studentDetail?stuUserId=" + stuUserId + "&pageFlag=fromVideoPlay";
	            				}
	            			}
	           			},
	           			error: function(data) {
	           				$.messager.show({
		                        title: data.title,
		                        msg: data.message,
		                        timeout: 1000 * 2
		                    });
	           			}
					});
					
				});
				
			});
			
			//返回上一层界面且定位学生记录
			function goBack() {
				var stuUserId = $("#stuUserId").val();
				if(pageFlag == "fromStuOverview") {  //从“学生概览”页面跳转而来，返回时重新返回该界面
					window.location.href="<%=basePath%>zznueg/portal/teacherplatform/studentDetail?stuUserId=" + stuUserId + "&pageFlag=fromStuOverview";
				} else {
					window.location.href="<%=basePath%>zznueg/portal/teacherplatform/studentDetail?stuUserId=" + stuUserId + "&pageFlag=fromVideoPlay";
				}
			}
			
			//不在考试期间时点击“提交”按钮的信息提示，没有被使用
			function tipsMsg() {
				if(confirm('不在考试期间，不能进行评分！')) {
					goBack();
				} else {
					//alert("I am here standing!");
				}
			}
			
		</script>
		
	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">首页</a></li>
				<li><a href="#">管理员信息</a></li>
				<li><a href="#">学生信息</a></li>
			</ul>
		</div>
		<div class="rightinfo">
			<div>
				<!-- <video src="resources/zznueg/images/安防.mp4" controls="controls" width="100%" height="100%">您的浏览器不支持video标签</video> -->
				<video src="${param.previewPath}" controls="controls" width="100%" height="100%">您的浏览器不支持video标签</video>
			</div>
			<div class="scoring">
				<img class="zzsfLogo" src="resources/zznueg/generic/web/images/zzsfLogo.png" />
				<!-- history.go(-1)（或：history.back()）;返回后刷新了父页面，该种方式不能定位记录
				<img class="zzsfBack" src="resources/zznueg/generic/web/images/back.png" onclick="javascript:history.go(-1);"/> -->
				<img class="zzsfBack" src="resources/zznueg/generic/web/images/back.png" onclick="goBack();"/>
				<img class="zzsfPeople" src="resources/zznueg/generic/web/images/people.png" />
				<div class="onlineScoring">
					<strong><img src="resources/zznueg/generic/web/images/scoring.png"/>在线打分</strong>
				</div>
				<div class="scorInput">
					<input id="uploadPid" value="${param.uploadPid}" type="hidden"/>
					<input id="stuUserId" value="${param.stuUserId}" type="hidden"/>
					<input style="display:none" name="evalScore"/>	<!-- 伪造隐藏域，以防chrome浏览器自动填充 -->
					<c:choose>
						<c:when test="${gtFlag == 1}"> <!-- 不在考试期间处理 -->
							<input id="evalScore" name="evalScore" type="text" maxlength="3" disabled="disabled"
								value="${param.evalScore}" onfocus="javaScript:this.value='';" autocomplete="off"
								onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" 
								onblur="this.v();" placeholder="" class="selects" />
						</c:when>
						<c:otherwise>
							<input id="evalScore" name="evalScore" type="text" maxlength="3" 
								value="${param.evalScore}" onfocus="javaScript:this.value='';" autocomplete="off"
								onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" 
								onblur="this.v();" placeholder="" class="selects" />
						</c:otherwise>
					</c:choose>
				</div>
				<c:choose>
					<c:when test="${gtFlag == 1}"> <!-- 不在考试期间处理 -->
						<div class="scorButton1" onclick="javaScript:alert('不在考试期间，不能进行评分！');">提交</div>
					</c:when>
					<c:otherwise>
						<div class="scorButton">提交</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</body>	

</html>
