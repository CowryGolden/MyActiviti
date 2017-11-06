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
	<title>教师平台-（在线测评）分析结果-主页</title>
	<tags:include_zznueg/>		
	<link rel="stylesheet" type="text/css" href="resources/zznueg/css/online.css"/>
		
	<script type="text/javascript">
		
		$(function() {
			layui.use(['element'], function() {
				var form = layui.element();
			});
			$(".qiehuan span").click(function(){
				$(".qiehuan span").removeAttr("class","show");
				$(this).addClass("show");
			});
			$(".qiehuan span").eq(0).click(function() {
				$("#tu iframe").attr("src", "zznueg/portal/teacherplatform/teachOnlSurAnaResultRadar");
			});
			$(".qiehuan span").eq(1).click(function() {
				$("#tu iframe").attr("src", "zznueg/portal/teacherplatform/teachOnlSurAnaResultBar");
			});
			$(".qiehuan span").eq(2).click(function() {
				$("#tu iframe").attr("src", "zznueg/portal/teacherplatform/teachOnlSurAnaResultLine");
			});
			$(".qiehuan span").eq(3).click(function() {
				$("#tu iframe").attr("src", "zznueg/portal/teacherplatform/teachOnlSurAnaResultStockBar");
			});
		});		
		
	</script>	
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="javascript:void(0);">教师平台</a></li>
			<li><a href="javascript:void(0);">分析结果</a></li>
		</ul>
	</div>
	<div class="average">
		<div id="main2">
<!-- 		
			<div class="tittle2">
				<select name="">
						<option value="选择学生">选择学生</option>
						<option value="">张三</option>
						<option value="">李四</option>
					</select>
			</div>
			<p style="color: #4596E5;padding-left: 8px;text-align: left;line-height: 40px;font-size: 22px;margin: 20px 0;">* 得分结果分析</p>
			<table class="tablelist tablelist2">
				<thead>
					<tr>
						<th width="100px;">综合能力</th>
						<th>得分</th>
						<th>满分</th>
						<th>测评结果与建议</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>学习能力</td>
						<td>20</td>
						<td>50</td>
						<td>15~40分：学习能力一般；</td>
					</tr>
					<tr>
						<td>沟通能力</td>
						<td>18</td>
						<td>25</td>
						<td>8~20分：有一定的沟通能力；</td>
					</tr>
					<tr>
						<td>亲和力</td>
						<td>34</td>
						<td>50</td>
						<td>15~40分：有一定的亲和力；</td>
					</tr>
					<tr>
						<td>抗压心</td>
						<td>9</td>
						<td>20</td>
						<td>7~14分：抗压能力较好；</td>
					</tr>
					<tr>
						<td>责任心</td>
						<td>11</td>
						<td>20</td>
						<td>7~14分：责任心较好；</td>
					</tr>
					<tr>
						<td>随机反应能力</td>
						<td>14</td>
						<td>25</td>
						<td>10分一下随机反应能力较差；</td>
					</tr>
					<tr>
						<td>数学运算能力</td>
						<td>15</td>
						<td>25</td>
						<td>10分一下运算能力较差；</td>
					</tr>
					<tr>
						<td>心理承受能力</td>
						<td>12</td>
						<td>20</td>
						<td>7~14分：承受力较好；</td>
					</tr>

				</tbody>
			</table>
			<div class="qiehuan">
				<span class="show">雷达图</span>
				<span>柱状图</span>
				<span>折线图</span>
				<span>条形图</span>
			</div>
			<div id="tu">
				<iframe src="zznueg/portal/teacherplatform/teachOnlSurAnaResultRadar" width="100%" height="450px" id="parentIframe"></iframe>
			</div>
			<div id="main4"></div>
			<div id="main5"></div>
			<div id="main6"></div>
 -->			
			<div style="color: #4596E5;padding-left: 8px;text-align: left;line-height: 40px;font-size: 22px;margin: 20px 0;">* 建议</div>
			<div class="Suggest">
				<div class="SuggestDiv">
					<img src="resources/zznueg/img/suggest.png" />
				</div>
<!-- 				
				<div class="SuggestDiv">
					<p style="font-size: 14px;color:#3eafe0;text-align: left;">* （如果4门科目的分数都超过平均分80分出现下面的建议）</p>
					<div class="suggestGood">
						<img src="resources/zznueg/img/zan.png" />
						<p style="">继续加油</p>
					</div>
				</div>
 -->				
			</div>

		</div>
	</div>

</body>
</html>