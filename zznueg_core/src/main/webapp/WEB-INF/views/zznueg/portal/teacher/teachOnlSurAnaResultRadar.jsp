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
		<title>教师平台-（在线测评）分析结果-雷达图</title>
		<tags:include_zznueg/>
		<style type="text/css">
			#main3, #main4, #main5, #main6 {
				width: 100%;
				height: 400px;
				text-align: center;
				margin: 20px auto;
			}
		</style>		
		
	</head>
	<body>
		<div id="main3"></div>
	</body>
	
	<script type="text/javascript">
		setIframeH();

		function setIframeH() {
			var body = $(document.body);
			var iframe = $(parent.document.getElementById('parentIframe'));
			iframe.height(body.height()+50);
		}
		
		var myChart3 = echarts.init(document.getElementById("main3"));
		var color3 = ['#4596e5', '#2ec7c9', '#b6a2de', '#5c9ded', '#36bd9b', '#22b7e5', '#7266bb', '#fe9331'];
		var option3 = {
			color: color3,
			tooltip: {},
			legend: {
				data: ['得分', '满分']
			},
			radar: {
				// shape: 'circle',
				indicator: [{
					name: '沟通能力',
					max: 100
				}, {
					name: '学习能力',
					max: 100
				}, {
					name: '亲和力',
					max: 100
				}, {
					name: '抗压力',
					max: 100
				}, {
					name: '责任心',
					max: 100
				}, {
					name: '随机反应能力',
					max: 100
				}, {
					name: '心理承受能力',
					max: 100
				}, {
					name: '数学运算能力',
					max: 100
				}]
			},
			series: [{
				name: '我的结构图VS标准教师',
				type: 'radar',
				areaStyle: {
					normal: {}
				},
				data: [{
					value: [80, 80, 80, 80, 80, 80, 80, 80],
					name: '得分'
				}, {
					value: [60, 90, 60, 60, 85, 70, 84, 69],
					name: '满分'
				}]
			}]
		};
		myChart3.setOption(option3);
	</script>
</html>
