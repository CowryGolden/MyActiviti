<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="paperInfo">
	<c:choose>
		<c:when test="${mapData != null}">
			<div>
				<div style="text-align: center;font-size: 22px;line-height: 70px;">
					<input name="paperId" id="paperId" type="hidden" value="${mapData.paperId}"/>
					<strong style="font-size: 22px;">${mapData.paperTitle}</strong>
				</div>
				<div>
					您好，此次问卷是针对教师的综合能力判断，请根据您的实际感受填写，答案没有对错之分，感谢您的参与!
				</div>
			</div>
			<div>
			<form class="layui-form" action="">	
				<c:choose>							
					<c:when test="${listData != null && listData.size() > 0}">
						<c:forEach var="list" items="${listData}" varStatus="status">
							<c:if test="${list.questionCategory=='A00' && list.questionNum==1}">
								<div class="questionClass">一、${list.questCateName}</div>
							</c:if>
							<c:if test="${list.questionCategory=='B00' && list.questionNum==1}">
								<div class="questionClass">二、${list.questCateName}</div>
							</c:if>
							<c:if test="${list.questionCategory=='C00' && list.questionNum==1}">
								<div class="questionClass">三、${list.questCateName}</div>
							</c:if>
							<c:if test="${list.questionCategory=='D00' && list.questionNum==1}">
								<div class="questionClass">四、${list.questCateName}</div>
							</c:if>
							<c:if test="${list.questionCategory=='E00' && list.questionNum==1}">
								<div class="questionClass">五、${list.questCateName}</div>
							</c:if>
							<c:if test="${list.questionCategory=='F00' && list.questionNum==1}">
								<div class="questionClass">六、${list.questCateName}</div>
							</c:if>
							<c:if test="${list.questionCategory=='G00' && list.questionNum==1}">
								<div class="questionClass">七、${list.questCateName}</div>
							</c:if>
							<c:if test="${list.questionCategory=='H00' && list.questionNum==1}">
								<div class="questionClass">八、${list.questCateName}</div>
							</c:if>
							
							<label class="layui-form-label">${list.questionNum}、${list.questionContent}</label>
							<%-- 该道题的最高分值和标准答案，由于此类问卷为主观题调查问卷，没有对错之分，每个答案代表的分支不同；
							<input name="questionScore_${list.questionId}" id="questionScore_${list.questionId}" type="hidden" value="${list.questionScore}"/> 
							<input name="questionAnswer_${list.questionId}" id="questionAnswer_${list.questionId}" type="hidden" value="${list.questionAnswer}"/> 
							--%>							
							<div class="layui-form-item" id="${list.questionId}">  <!-- 增加猫链接以便没有选中时定位 -->
								<div class="layui-input-block">
									<c:if test="${list.questionType == 'S'}">  <!-- 题型判断；S-单选题，D-多选题，J-判断题 -->
										<c:if test="${list.optionAContent != null}">  <!-- data-sans表示标准答案，对客观题有用 -->
											<input type="radio" data-qtype="${list.questionCategory}" data-sans="${list.questionAnswer}" data-option="A" name="${list.questionId}" value="${list.optionAScore}" title="A、${list.optionAContent}" checked />												
										</c:if>
										<c:if test="${list.optionBContent != null}">
											<input type="radio" data-qtype="${list.questionCategory}" data-sans="${list.questionAnswer}" data-option="B" name="${list.questionId}" value="${list.optionBScore}" title="B、${list.optionBContent}" />	
										</c:if>
										<c:if test="${list.optionCContent != null}">
											<input type="radio" data-qtype="${list.questionCategory}" data-sans="${list.questionAnswer}" data-option="C" name="${list.questionId}" value="${list.optionCScore}" title="C、${list.optionCContent}" />	
										</c:if>
										<c:if test="${list.optionDContent != null}">
											<input type="radio" data-qtype="${list.questionCategory}" data-sans="${list.questionAnswer}" data-option="D" name="${list.questionId}" value="${list.optionDScore}" title="D、${list.optionDContent}" />	
										</c:if>
									</c:if>	
								</div>
							</div>													
						</c:forEach>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit lay-filter="formDemo" style="background-color: #4596E5;">立即提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</c:when>
					<c:otherwise><div class="questionClass">调查问卷内容暂时为空！</div></c:otherwise>								
				</c:choose>
			</form>	
			</div>				
		</c:when>
		<c:otherwise><div style="text-align: center;line-height: 70px;"><strong style="font-size: 22px;">暂无在线调查问卷！</strong></div></c:otherwise>
	</c:choose>
</div>

