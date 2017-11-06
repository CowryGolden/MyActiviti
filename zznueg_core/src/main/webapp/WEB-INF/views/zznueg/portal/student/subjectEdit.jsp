<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>"/>


<script type="text/javascript">
	$(function () {
		$("#form").form({
			
		});
		
	});

	/* $(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});
		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});
		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});
		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});
	}); */
</script>

<style>
<!--

-->
</style>


<div class="tip">
	<form id="form" method="post" enctype="multipart/form-data">
		<div class="formbody">	
			<div class="formtitle"><span>基本信息</span></div>
			<ul class="forminfo">
				<li><label>学号</label><input name="" type="text" class="dfinput" /><i></i></li>
				<li><label>标题</label><input name="" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
				<li><label>上传人</label><cite><input name="" type="text" class="dfinput" /></cite></li>
				<li><label>专业</label><input name="" type="text" class="dfinput" /></li>
				<li><label>时间</label><input name="" type="text" class="dfinput" /></li>
				<!--<li><label>&nbsp;</label><input name="" type="button" class="btn" value="添加" /></li>-->
			</ul>
		</div>
	</form>
<!-- 	
	<div class="tipbtn">
		<input name="" type="button" class="sure" value="确定" />&nbsp;
		<input name="" type="button" class="cancel" value="取消" />
	</div>
 -->
</div>