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
		<title>教师平台-在线测评模板</title>
		<tags:include_zznueg/>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/teachOnlEval.css"/>
		
		<script type="text/javascript">
			
			$(function() {
				layui.use(['form', 'laydate'], function() {
					var form = layui.form(),
						laydate = layui.laydate;
					//监听提交
					form.on('submit(formDemo)', function(data) {
						layer.msg(JSON.stringify(data.field));
						return false;
					});
				});
				
				$("#preview").click(function() {
					//alert("display: " + $("#contentOverviewDiv").css("display"));
					if($("#contentOverviewDiv").css("display") == "none") {
						$("#contentOverviewDiv").fadeIn(100);
					} else {
						$("#contentOverviewDiv").fadeOut(200);
					}
					
				});
				
			});		
			
		</script>
		
	</head>

	<body>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="javascript:void(0);">教师平台</a></li>
				<li><a href="javascript:void(0);">在线测评模板</a></li>
			</ul>
		</div>
		<div class="neirong">
			<div>
				<div style="text-align: center;font-size: 22px;line-height: 70px;">
					<strong style="font-size: 22px;">模板</strong>
				</div>

				<div class="formsumbit">
					<form class="layui-form" action="">
						<div class="layui-form-item">
							<label class="layui-form-label templ">类型</label>
							<div class="layui-input-block input-field">
							  <select name="questionCategory" lay-verify="required">
						        <option value=""></option>
						        <option value="0">学习能力</option>
						        <option value="1">沟通能力</option>
						        <option value="2">亲和力</option>
						        <option value="3">抗压心力</option>
						        <option value="4">责任心</option>
						        <option value="5">随机反应能力</option>
						        <option value="6">数学运算能力</option>
						        <option value="7">心理承受能力</option>
						      </select>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label templ">题目</label>
							<div class="layui-input-block input-field">
								<input type="text" name="questionTitle" required lay-verify="required" placeholder="请输入题目" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label templ">选项A</label>
							<div class="layui-input-block input-field">
								<input type="text" name="optionAContent" required lay-verify="required" placeholder="请输入选项A的内容" autocomplete="off" class="layui-input"><span class="fenzhi">分值</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label templ">选项B</label>
							<div class="layui-input-block input-field">
								<input type="text" name="optionBContent" required lay-verify="required" placeholder="请输入选项B的内容" autocomplete="off" class="layui-input"><span class="fenzhi">分值</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label templ">选项C</label>
							<div class="layui-input-block input-field">
								<input type="text" name="optionCContent" required lay-verify="required" placeholder="请输入选项C的内容" autocomplete="off" class="layui-input"><span class="fenzhi">分值</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label templ">选项D</label>
							<div class="layui-input-block input-field">
								<input type="text" name="optionDContent" required lay-verify="required" placeholder="请输入选项D的内容" autocomplete="off" class="layui-input"><span class="fenzhi">分值</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label templ">正确答案</label>
							<div class="layui-input-block input-field">
								<input type="text" name="rightAnswer" required lay-verify="required" placeholder="请输入本题正确答案" autocomplete="off" class="layui-input"><span class="fenzhi">分值</span>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block input-field">
								<button class="layui-btn" lay-submit lay-filter="formDemo" title="提交表单">立即提交</button>
								<button type="reset" class="layui-btn layui-btn-primary" title="重置表单">重置</button>
								<span id="preview" class="layui-btn" style="background-color: #4596E5;" title="在线测评问卷预览">预览</span>
							</div>
						</div>
					</form>					
				</div>				
			</div>
<!-- 			
			<div>
				<div>
					<span id="preview" style="float: right;margin-right: 10%;" title="在线测评问卷预览">
						<button style="background-color: #009688;color: #fff;font-size: 14pt;border-radius: 4px;cursor: pointer;">预  览</button>
					</span>				
				</div>
			</div>
 -->			
			<div id="contentOverviewDiv" style="display: none;">
				<div style="text-align: center;font-size: 22px;line-height: 70px;">
					<strong style="font-size: 22px;">在线测评问卷预览</strong>
				</div>
				<div id="questionContentDiv">
					<div class="neirong">
						<div>
							<div style="text-align: center;font-size: 22px;line-height: 70px;">
								<strong style="font-size: 22px;">教师综合能力在线测评问卷</strong>
							</div>
							<div style="font-size: 12px;font-style: italic;">
								您好，此次问卷是针对教师的综合能力判断，请根据您的实际感受填写，答案没有对错之分，感谢您的参与!
							</div>
						</div>
						<div>
							<form class="layui-form" action="">
								<div class="questionClass">一、沟通能力</div>
								<label class="layui-form-label">1.在你的工作任务繁忙时，却有同事生病住院了，你会</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_A01" title="A.有空就去探望，没空就不去了">
										<input type="radio" name="onlSur_R_A01" title="B.只探望和你关系密切的同事">
										<input type="radio" name="onlSur_R_A01" title="C.尽量挤出时间多次探望">
									</div>
								</div>
								<label class="layui-form-label">2.工作或生活中有人对你产生依赖，你的态度</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_A02" title="A.避而远之，我不喜欢结交依赖性很强的朋友">
										<input type="radio" name="onlSur_R_A02" title="B.我喜欢别人依赖我的感觉">
										<input type="radio" name="onlSur_R_A02" title="C.一般情况我并不介意，但我希望彼此之间有一定的独立性">
									</div>
								</div>
								<label class="layui-form-label">3.当你跟上司正在讨论事情时，上司的秘书进来告诉你有电话，此时你会</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_A03" title="A.接电话，该说多久就说多久">
										<input type="radio" name="onlSur_R_A03" title="B.请上司的秘书跟对方说“不在”">
										<input type="radio" name="onlSur_R_A03" title="C.告诉对方，你现在开会，待会再回电">
									</div>
								</div>
								<!-- 
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="formDemo" style="background-color: #4596E5;">立即提交</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</div>
								</div> -->
							</form>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</body>
	

</html>