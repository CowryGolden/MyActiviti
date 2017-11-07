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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>学生-学生成绩-视频播放</title>
		<tags:include/>
		<link href="resources/zznueg/css/style.css" rel="stylesheet" type="text/css" />
		
		<style type="text/css">
			/*打分区域样式*/			
			.scoring {
				width: 1px;
				height: 1px;
				background-color: #eaeff3;
				position: fixed;
				top: 150px;
				right: 100px;
			}
			
			.zzsfLogo {
				position: absolute;
				top: -88px;
				left: -50px;
			}
			
			.zzsfPeople,
			.zzsfBack {
				position: absolute;
				top: -90px;
				left: 10px;
			}
			
			.zzsfPeople {
				left: 20px;
			}
			
			.zzsfBack {
				right: 20px;
				width: 22px;
				cursor: pointer;
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
			
			.scorButton:hover {
				border: 1px solid #36b3df
			}
		</style>
		
		<script type="text/javascript">
			$(function() {
				$(".scorButton").click(function() {					
					
				});
				
			});
			
			//返回上一层界面且定位学生记录
			function goBack() {
				var stuUserId = $("#stuUserId").val();
				window.location.href="<%=basePath%>zznueg/portal/studentplatform/studentDetail?stuUserId=" + stuUserId;
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
				<!-- history.go(-1)（或：history.back()）;返回后刷新了父页面，该种方式不能定位记录（学生平台不涉及多个学生，两种方法都可以使用） -->
				<img class="zzsfBack" alt="返回图片" title="点我返回" src="resources/zznueg/generic/web/images/back.png" onclick="javascript:history.go(-1);"/>
				<!-- <img class="zzsfBack" src="resources/zznueg/generic/web/images/back.png" onclick="goBack();"/> -->
				<div class="scorInput">
					<input id="stuUserId" value="${param.stuUserId}" type="hidden"/>
				</div>
				<!-- <div class="scorButton">返回</div> -->
			</div>
		</div>
	</body>	

</html>
