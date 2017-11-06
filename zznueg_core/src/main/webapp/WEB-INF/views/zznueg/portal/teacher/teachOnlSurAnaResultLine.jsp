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
		<title>教师平台-（在线测评）分析结果-折线图</title>
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
		<div id="main5"></div>
	</body>

	<script type="text/javascript">
		setIframeH();

		function setIframeH() {
			var body = $(document.body);
			var iframe = $(parent.document.getElementById('parentIframe'));
			iframe.height(body.height()+50);
		}
		var color3 = ['#4596e5', '#2ec7c9', '#b6a2de', '#5c9ded', '#36bd9b', '#22b7e5', '#7266bb', '#fe9331'];
		var myChart5 = echarts.init(document.getElementById("main5"));
		var option5 = {
				color: color3,
				legend: {
					y: '0',
					data: ['得分', '满分']
				},
				tooltip: {
					trigger: 'axis', //弹框的显示与否
					axisPointer: {
						type: 'cross'
					}
				},
				xAxis: {
					data: ["沟通能力", "学习能力", "亲和力", "抗压力", "责任心", "随机反应能力", "心理承受能力", "数学运算能力"]
				},
				yAxis: {
					type: 'value',
					name: "次数",
					min: 0,
					max: 60
				},
				series: [{
					type: 'line',
					name: "得分",
					data: [20, 25, 15, 18, 19, 16, 20, 22]
				}, {
					type: 'line',
					name: "满分",
					data: [25, 50, 50, 20, 20, 25, 20, 25]
				}]
			}
			//使用刚指定的配置项和数据显示图表
		myChart5.setOption(option5);
	</script>
	
</html>
