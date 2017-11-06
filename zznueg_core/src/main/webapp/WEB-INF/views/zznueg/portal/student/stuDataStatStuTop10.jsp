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
		<title>学生-数据统计-TOP10学生排行图</title>
		<tags:include_zznueg/>
		
		<script type="text/javascript">
			var title1 = "提升分数最高的学生";
			var title2 = "活跃度最高的学生TOP10";
			
			$(function(){
				setIframeH();
				
				/* 还未实现，待优化——提升分数最高的学生----start */
				var color3 = ['#4596e5', '#2ec7c9', '#b6a2de', '#5c9ded', '#36bd9b', '#22b7e5', '#7266bb', '#fe9331'];
				var myChart1 = echarts.init(document.getElementById("main1"));
				var option1 = {
						color: color3,
						title: {
							text: "提升分数最高的学生（虚拟）",
							left: "center"
						},
						tooltip: {
							trigger: 'axis', //弹框的显示与否
							axisPointer: {
								type: 'cross'
							}
						},
						xAxis: {
							data: ["张三", "李四", "王二", "李娜", "张华", "隔天", "刘科", "黄庆", "李萌", "李旭"]
						},
						yAxis: {
							type: 'value',
							name: '提升分数',
							min: 0,
							max: 400
						},
						series: [{
							name: '提升的分数',
							type: 'bar',
							data: [380, 370, 350, 340, 330, 300, 280, 270, 269, 266]
						}]
					}
					//使用刚指定的配置项和数据显示图表
				myChart1.setOption(option1);
				/* 还未实现，待优化——提升分数最高的学生----end */
				
				$.ajax({
					url : "zznueg/portal/studentplatform/getStuTop10ByActivity",
				    type : "POST",
				    async:false,	 			     
				    dataType : "json",
				    data:{},
				    contentType: "application/json; charset=utf-8",
				    success : function(data) {
				    	drawLine(echarts,"main2",data,title2); 
				    } 	
				});
				
			});
			
			/* 设置iframede的高度 */
			function setIframeH() {
				var body = $(document.body);
				var iframe = $(parent.document.getElementById('parentIframe'));
				iframe.height(body.height()+50);
			}	
		</script>
		
		<style type="text/css">
			.average {
				width: 95%;
				margin: 0 auto;
			}
			
			#main1 {
				width: 49%;
				height: 500px;
				float: left;
				text-align: center;
			}
			
			#main2 {
				width: 49%;
				height: 500px;
				float: right;
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
		</style>

	</head>

	<body>
		<div class="average clear">
			<div class="tittle2_select clear">
				<div class="shuoming">
					<p>注：1 全体学生最近两次考试成绩-----提升分数最高学生TOP10排名；</p>
					<p style="text-indent: 4em;">2 使用此系统平台，活跃度最高的TOP10（指用户在线时长已经登录频次）；</p>
				</div>
				<div id="main1"></div>
				<div id="main2"></div>
			</div>
		</div>
	</body>

</html>