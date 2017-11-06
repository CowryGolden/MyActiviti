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
		<title>教师平台-（在线测评）分析结果-柱状图</title>
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
		<div id="main4"></div>
	</body>
	
	<script type="text/javascript">
		setIframeH();

		function setIframeH() {
			var body = $(document.body);
			var iframe = $(parent.document.getElementById('parentIframe'));
			iframe.height(body.height()+50);
		}
		
		
		var color3 = ['#4596e5', '#2ec7c9', '#b6a2de', '#5c9ded', '#36bd9b', '#22b7e5', '#7266bb', '#fe9331'];
		var myChart4 = echarts.init(document.getElementById("main4"));
		var option4 = {
				color: color3,
				tooltip: {
					trigger: 'axis', //弹框的显示与否
					axisPointer: { // 坐标轴指示器，坐标轴触发有效
						type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend: {
					data: ['得分', '满分']
				},
				xAxis: {
					data: ["沟通能力", "学习能力", "亲和力", "抗压力", "责任心", "随机反应能力", "心理承受能力", "数学运算能力"]
				},
				yAxis: {
					type: 'value',
					min: 0,
					max: 100
				},
				series: [{
					name: '得分',
					type: 'bar',
					data: [20, 25, 15, 18, 19, 16, 20, 22],
					barGap: '0%'
				}, {
					name: '满分',
					type: 'bar',
					data: [25, 50, 50, 20, 20, 25, 20, 25]
				}]
			}
			//使用刚指定的配置项和数据显示图表
		myChart4.setOption(option4);
	</script>
	
</html>
