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
		<title>教师平台-在线测评题库-主页面</title>
		<tags:include_zznueg/>
		<script type="text/javascript" src="resources/zznueg/js/jquery-1.8.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/teachOnlineEvalQuestionLib.css"/>
		
		<script type="text/javascript">
			$(function () {
			    var jobs = $('#vocation .list>.vocation');
			    jobs.on('click',function () {
			        var job = $(this);
			        if (job.next('li').is(':hidden')) {
			            job.css('background-position', 'right 2px').next('li').slideDown();
			        }
			        else {
			            job.css('background-position', 'right -70px').next('li').slideUp();
			        }
			    });
			    
			    
			    layui.use(['form', 'laydate'], function() {
					var form = layui.form(),
						laydate = layui.laydate;
					//监听提交
					form.on('submit(formDemo)', function(data) {
						layer.msg(JSON.stringify(data.field));
						return false;
					});
				});
			    
			    
			    
			    
			});
		</script>
		
	</head>
	
	<body>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="javascript:void(0);">教师平台</a></li>
				<li><a href="javascript:void(0);">在线测评题库</a></li>
			</ul>
		</div>
		<div id="vocation" class="join">
		    <div style="text-align: center;font-size: 22px;line-height: 70px;">
		    	<strong style="font-size: 22px;">在线测评题库</strong>
		    </div>
		    <ul class="listTop">
		    	<li class="title">
		            <span class="txal_l">测评问卷标题</span>
		            <span class="txal_c">是否启用</span>
		            <span class="txal_r">查看详情</span>
		        </li>
		        
		    </ul>
		    <ul class="list">
		        <li class="vocation">
		            <span class="txal_l">教师综合能力在线测评问卷 </span>
		            <span class="txal_c">是</span>
		            <span class="txal_r">点击查看</span>
		        </li>
		        <li class="detail">
		        
		           <div class="neirong">
						<div>
							<div style="text-align: center;font-size: 22px;line-height: 70px;">
								<strong style="font-size: 22px;">教师综合能力在线测评问卷</strong>
							</div>
							<div>
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
								<label class="layui-form-label">4.一位朋友邀请你参加生日聚会，可是任何一位来宾你都不认识</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_A04" title="A.你借故拒绝，告诉他自己已经有约了">
										<input type="radio" name="onlSur_R_A04" title="B.你会提早去帮忙筹备聚会，然后离开">
										<input type="radio" name="onlSur_R_A04" title="C.你非常乐意借此机会认识更多的朋友">
									</div>
								</div>
								<label class="layui-form-label">5.有位员工连续四次在周末向你要求他想提前下班，此时你会说</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_A05" title="A.我不能再容许你早退了，你要顾及他人的想法">
										<input type="radio" name="onlSur_R_A05" title="B.今天不行，下午四点我要开会">
										<input type="radio" name="onlSur_R_A05" title="C.你对我们相当重要，我需要你的帮助，特别是在周末">
									</div>
								</div>
			
								<div class="questionClass">二、学习能力</div>
								<label class="layui-form-label">1.我习惯记下阅读中的不懂之处</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B01" title="A.是">
										<input type="radio" name="onlSur_R_B01" title="B.不是">
										<input type="radio" name="onlSur_R_B01" title="C.无法确定">
									</div>
								</div>
								<label class="layui-form-label">2.我注意归纳并写出学习中的要点</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B02" title="A.是">
										<input type="radio" name="onlSur_R_B02" title="B.不是">
										<input type="radio" name="onlSur_R_B02" title="C.无法确定">
									</div>
								</div>
								<label class="layui-form-label">3.我经常查阅字典、手册等工具书</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B03" title="A.是">
										<input type="radio" name="onlSur_R_B03" title="B.不是">
										<input type="radio" name="onlSur_R_B03" title="C.无法确定">
									</div>
								</div>
								<label class="layui-form-label">4.认为重要的内容，我各位注意听讲和理解</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B04" title="A.是">
										<input type="radio" name="onlSur_R_B04" title="B.不是">
										<input type="radio" name="onlSur_R_B04" title="C.无法确定">
									</div>
								</div>
								<label class="layui-form-label">5.我善于吸取别人好的学习方法</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B05" title="A.是">
										<input type="radio" name="onlSur_R_B05" title="B.不是">
										<input type="radio" name="onlSur_R_B05" title="C.无法确定">
									</div>
								</div>
								<label class="layui-form-label">6.我喜欢观察实物或参考有关资料进行学习</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B06" title="A.是">
										<input type="radio" name="onlSur_R_B06" title="B.不是">
										<input type="radio" name="onlSur_R_B06" title="C.无法确定">
									</div>
								</div>
								<label class="layui-form-label">7.我能够制定出切实可行的学习计划</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B07" title="A.是">
										<input type="radio" name="onlSur_R_B07" title="B.不是">
										<input type="radio" name="onlSur_R_B07" title="C.无法确定">
									</div>
								</div>
								<label class="layui-form-label">8.我喜欢了解自己不知道的东西</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B08" title="A.是">
										<input type="radio" name="onlSur_R_B08" title="B.不是">
										<input type="radio" name="onlSur_R_B08" title="C.无法确定">
									</div>
								</div>
								<label class="layui-form-label">9.遇到自己不知道的事情，我能主动地请教他人</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B09" title="A.是">
										<input type="radio" name="onlSur_R_B09" title="B.不是">
										<input type="radio" name="onlSur_R_B09" title="C.无法确定">
									</div>
								</div>
								<label class="layui-form-label">10.我能够较快地掌握新的工作方法</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_B10" title="A.是">
										<input type="radio" name="onlSur_R_B10" title="B.不是">
										<input type="radio" name="onlSur_R_B10" title="C.无法确定">
									</div>
								</div>
								<div class="questionClass">三、亲和力</div>
								<label class="layui-form-label">1.你擅长说笑话、讲趣事</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C01" title="A.是的">
										<input type="radio" name="onlSur_R_C01" title="B.不一定">
										<input type="radio" name="onlSur_R_C01" title="C.不是的">
									</div>
								</div>
								<label class="layui-form-label">2.多数人认为你是一个说话风趣的人</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C02" title="A.是的">
										<input type="radio" name="onlSur_R_C02" title="B.不一定">
										<input type="radio" name="onlSur_R_C02" title="C.不是的">
									</div>
								</div>
								<label class="layui-form-label">3.喜欢看电影或参加其他娱乐活动</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C03" title="A.是的">
										<input type="radio" name="onlSur_R_C03" title="B.不一定">
										<input type="radio" name="onlSur_R_C03" title="C.不是的">
									</div>
								</div>
								<label class="layui-form-label">4.和一般人相比，你的朋友很多</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C04" title="A.是的">
										<input type="radio" name="onlSur_R_C04" title="B.不一定">
										<input type="radio" name="onlSur_R_C04" title="C.不是的">
									</div>
								</div>
								<label class="layui-form-label">5.不到万不得已，你会避免参加应酬性活动</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C05" title="A.是的">
										<input type="radio" name="onlSur_R_C05" title="B.不一定">
										<input type="radio" name="onlSur_R_C05" title="C.不是的">
									</div>
								</div>
								<label class="layui-form-label">6.单独和异性谈话时，总显得不太自然</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C06" title="A.是的">
										<input type="radio" name="onlSur_R_C06" title="B.不一定">
										<input type="radio" name="onlSur_R_C06" title="C.不是的">
									</div>
								</div>
								<label class="layui-form-label">7.喜欢向朋友讲述一些你个人有趣的经历</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C07" title="A.是的">
										<input type="radio" name="onlSur_R_C07" title="B.不一定">
										<input type="radio" name="onlSur_R_C07" title="C.不是的">
									</div>
								</div>
								<label class="layui-form-label">8.你爱穿朴素的衣服，不欣赏华丽的服装</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C08" title="A.是的">
										<input type="radio" name="onlSur_R_C08" title="B.不一定">
										<input type="radio" name="onlSur_R_C08" title="C.不是的">
									</div>
								</div>
								<label class="layui-form-label">9.你认为安静的自娱自乐远远胜过热闹的宴会</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C09" title="A.是的">
										<input type="radio" name="onlSur_R_C09" title="B.不一定">
										<input type="radio" name="onlSur_R_C09" title="C.不是的">
									</div>
								</div>
								<label class="layui-form-label">10.通常人们认为你是一个活跃热情的人</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_C10" title="A.是的">
										<input type="radio" name="onlSur_R_C10" title="B.不一定">
										<input type="radio" name="onlSur_R_C10" title="C.不是的">
									</div>
								</div>
			
								<div class="questionClass">
									四、抗压力
								</div>
								<label class="layui-form-label">1.因为发生了某些没有预料的事，你感到心烦</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D01" title="A.是">
										<input type="radio" name="onlSur_R_D01" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">2.你感觉到你不能控制你生活中的重要事情</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D02" title="A.是">
										<input type="radio" name="onlSur_R_D02" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">3.你常常感到紧张和压力</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D03" title="A.是">
										<input type="radio" name="onlSur_R_D03" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">4.你常常不能成功地应对生活中有威胁性的争吵。</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D04" title="A.是">
										<input type="radio" name="onlSur_R_D04" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">5.你觉得不能成功地应对生活中所发生的重要变化。</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D05" title="A.是">
										<input type="radio" name="onlSur_R_D05" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">6.你发现你不能应付你必须去做的所有事情</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D06" title="A.是">
										<input type="radio" name="onlSur_R_D06" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">7.因为事情都是发生在你能控制的范围之外，你会因此而烦恼</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D07" title="A.是">
										<input type="radio" name="onlSur_R_D07" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">8.你发现你自己常在考虑自己必须完成的那些事情</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D08" title="A.是">
										<input type="radio" name="onlSur_R_D08" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">9.若你刚买了一天的鞋穿了一天的就裂口，你会气愤、痛苦的抱怨</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D09" title="A.是">
										<input type="radio" name="onlSur_R_D09" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">10.当父母因为学习责备你而使你感到压力很大时，你不会和他们争吵，一个人压抑情感。</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_D10" title="A.是">
										<input type="radio" name="onlSur_R_D10" title="B.否">
									</div>
								</div>
								<div class="questionClass">
									五、责任心
								</div>
								<label class="layui-form-label">1.你觉得自己非常有良心吗？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E01" title="A.是">
										<input type="radio" name="onlSur_R_E01" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">2.你经常准时赴约吗</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E02" title="A.是">
										<input type="radio" name="onlSur_R_E02" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">3.你是否认为值得做的事就一定要做好？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E03" title="A.是">
										<input type="radio" name="onlSur_R_E03" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">4.你是否总能收到别人的信赖。</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E04" title="A.是">
										<input type="radio" name="onlSur_R_E04" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">5.你是否从不食言，尽管诺言很难实现。</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E05" title="A.是">
										<input type="radio" name="onlSur_R_E05" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">6.你收到来信后，是否立即回信</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E06" title="A.是">
										<input type="radio" name="onlSur_R_E06" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">7.你能无愧地说你比大多数人守信用吗</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E07" title="A.是">
										<input type="radio" name="onlSur_R_E07" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">8.当早上需要在某一钟点起来时，你上闹钟吗</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E08" title="A.是">
										<input type="radio" name="onlSur_R_E08" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">9.你是否认为人应该劳于先而后享乐</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E09" title="A.是">
										<input type="radio" name="onlSur_R_E09" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">10.你是否宁愿寻找果皮箱，也不把废物随手扔在马路上</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_E10" title="A.是">
										<input type="radio" name="onlSur_R_E10" title="B.否">
									</div>
								</div>
								<div class="questionClass">
									六、随机反应能力
								</div>
								<label class="layui-form-label">1.在宾馆里，顾客说：瞧，你把我的新衣服洒上了水，怎么办！你作为服务员回答（）</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_F01" title="A.谁叫你走路不长眼睛。讨厌！">
										<input type="radio" name="onlSur_R_F01" title="B.对不起：请用毛巾擦一下吧。">
										<input type="radio" name="onlSur_R_F01" title="C.真糟糕：怎么办好呢">
									</div>
								</div>
								<label class="layui-form-label">2.在饭店酒桌上，顾客：这杯子没有洗干净，下面还有手印呢！你作为服务员回答（）</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_F02" title="A.洗干净了，用不着担心">
										<input type="radio" name="onlSur_R_F02" title="B.真抱歉">
										<input type="radio" name="onlSur_R_F02" title="C.对比起，我来换一个">
									</div>
								</div>
								<label class="layui-form-label">3.在公共汽车站牌前，因人多而没有挤上去，你的朋友：等一会再上吧!你回答（）？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_F03" title="A.老是这样子会一直乘不上车的">
										<input type="radio" name="onlSur_R_F03" title="B.是的，再等等下一班车吧">
										<input type="radio" name="onlSur_R_F03" title="C.高峰期总是这样，真讨厌">
									</div>
								</div>
								<label class="layui-form-label">4.在公共汽车上，由于人多相互拥挤，有人对你说：不要挤，对此你作何反应（）</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_F04" title="A.人多，没办法，请你向前靠些好了">
										<input type="radio" name="onlSur_R_F04" title="B.对不起">
										<input type="radio" name="onlSur_R_F04" title="C.真是的，我也不想挤">
									</div>
								</div>
								<label class="layui-form-label">5.与恋人约会时，他因来晚了对你说：啊，我来迟了。你作何回答（）</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_F05" title="A.真不礼貌，稀里糊涂的">
										<input type="radio" name="onlSur_R_F05" title="B.不必介意，不必介意">
										<input type="radio" name="onlSur_R_F05" title="C.你是我喜爱的人嘛">
									</div>
								</div>
								<div class="questionClass">
									七、心理承受能力
								</div>
								<label class="layui-form-label">1.你是否喜欢冒险和刺激？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G01" title="A.是">
										<input type="radio" name="onlSur_R_G01" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">2.你生活在使你感到快乐和温暖的集体里吗？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G02" title="A.是">
										<input type="radio" name="onlSur_R_G02" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">3.你是否认为家人需要你？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G03" title="A.是">
										<input type="radio" name="onlSur_R_G03" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">4.你是否有一些无话不说的知心朋友</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G04" title="A.是">
										<input type="radio" name="onlSur_R_G04" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">5.大部分时间你对未来充满信心？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G05" title="A.是">
										<input type="radio" name="onlSur_R_G05" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">6.你是否每周至少进行一次所喜欢的体育活动，如登山，打球等？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G06" title="A.是">
										<input type="radio" name="onlSur_R_G06" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">7.你认为上司（或老师）喜欢你吗？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G07" title="A.是">
										<input type="radio" name="onlSur_R_G07" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">8.你相信自己能战胜任何挫折吗？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G08" title="A.是">
										<input type="radio" name="onlSur_R_G08" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">9.你是否常与同事交流看法？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G09" title="A.是">
										<input type="radio" name="onlSur_R_G09" title="B.否">
									</div>
								</div>
								<label class="layui-form-label">10.即使在困难时，你还是相信困难终将过去吗？</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_G10" title="A.是">
										<input type="radio" name="onlSur_R_G10" title="B.否">
									</div>
								</div>
								<div class="questionClass">
									八、数学运算能力
								</div>
								<label class="layui-form-label">1.783.5+52.96-35.18-15.5+58.35的值是</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_H01" title="A.845.15">
										<input type="radio" name="onlSur_R_H01" title="B.846.24">
										<input type="radio" name="onlSur_R_H01" title="C.844.13">
										<input type="radio" name="onlSur_R_H01" title="D.845.66">
									</div>
								</div>
			
								<label class="layui-form-label">2.一老人对一孩子说："你父亲的年龄是你的四倍，而我比你父亲大50岁，年龄正是你上小学时的15倍，你上小学时是你2岁弟弟年龄的3倍。“这个孩子现在年龄是</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_H02" title="A.8岁">
										<input type="radio" name="onlSur_R_H02" title="B.10岁">
										<input type="radio" name="onlSur_R_H02" title="C.12岁">
										<input type="radio" name="onlSur_R_H02" title="D.13岁">
									</div>
								</div>
								<label class="layui-form-label">3.某学生在一次考试中，语文、数学、外语三门学科的平均成绩为80分，物理、化学两门学科的平均成绩为85分，则该生这五门学科的平均成绩是</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_H03" title="A.81">
										<input type="radio" name="onlSur_R_H03" title="B.82">
										<input type="radio" name="onlSur_R_H03" title="C.82.5">
										<input type="radio" name="onlSur_R_H03" title="D.83">
									</div>
								</div>
								<label class="layui-form-label">4.甲用1000元人民币购买了一手股票，随即他将这手股票转卖给乙，获利10%，乙而后又将这手股票反转卖给甲，但乙损失了10%，最后甲按乙卖给自己的价格的九折将这手股票又卖给了乙，则在上述股票交易中</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_H04" title="A.甲刚好盈亏平衡">
										<input type="radio" name="onlSur_R_H04" title="B.甲盈利1元">
										<input type="radio" name="onlSur_R_H04" title="C.甲盈利9元">
										<input type="radio" name="onlSur_R_H04" title="D.甲亏本1.1元">
									</div>
								</div>
								<label class="layui-form-label">5.有一个棱长为4的正方体容器，倒满水后，再把容器中的水倒入一个长为8，宽为2的长方体容器中，刚好盛满。请问这个长方体容器的高是</label>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input type="radio" name="onlSur_R_H05" title="A.2">
										<input type="radio" name="onlSur_R_H05" title="B.3">
										<input type="radio" name="onlSur_R_H05" title="C.4">
										<input type="radio" name="onlSur_R_H05" title="D.5">
									</div>
								</div>
			
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="formDemo" style="background-color: #4596E5;">立即提交</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</div>
								</div>
							</form>
						</div>
			
					</div>
					
		        </li>
		        
		        <li class="vocation dark">
		            <span class="txal_l">教师综合能力在线测评问卷 </span>
		            <span class="txal_c">否</span>
		            <span class="txal_r">点击查看</span>
		        </li>
		        <li class="detail">
		            <div class="neirong">
						<div>
							<div style="text-align: center;font-size: 22px;line-height: 70px;">
								<strong style="font-size: 22px;">教师综合能力在线测评问卷</strong>
							</div>
							<div>
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
							</form>
						</div>
					</div>	
		        </li>
		    </ul>
		</div>
	</body>
	
</html>