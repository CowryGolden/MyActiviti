<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="tableListDiv">
	<table class="tablelist tablelist2">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th width="20%">综合能力</th>
				<th width="10%">得分</th>
				<th width="10%">满分</th>
				<th width="50%">测评结果与建议</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${rtnList != null && rtnList.size() > 0}">
					<c:forEach var="list" items="${rtnList}" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td>${list.questionCategoryName}</td>
							<td>${list.cateStuTotalScore}</td>
							<td>${list.cateMaxTotalScore}</td>
							<c:if test="${list.questionCategory == 'A00'}">
								<td>15~40分：学习能力一般；</td>
							</c:if>
							<c:if test="${list.questionCategory == 'B00'}">
								<td>8~20分：有一定的沟通能力；</td>
							</c:if>
							<c:if test="${list.questionCategory == 'C00'}">
								<td>15~40分：有一定的亲和力；</td>
							</c:if>
							<c:if test="${list.questionCategory == 'D00'}">
								<td>7~14分：抗压能力较好；</td>
							</c:if>
							<c:if test="${list.questionCategory == 'E00'}">
								<td>7~14分：责任心较好；</td>
							</c:if>
							<c:if test="${list.questionCategory == 'F00'}">
								<td>10分已下随机反应能力较差；</td>
							</c:if>
							<c:if test="${list.questionCategory == 'G00'}">
								<td>10分以下运算能力较差；</td>
							</c:if>
							<c:if test="${list.questionCategory == 'H00'}">
								<td>7~14分：承受力较好；</td>
							</c:if>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise><tr id="noData"><td colspan="5"><div style="font-size:16px;font-weight:bold;">您还没有填写在线测评问卷，暂无数据！</div></td></tr></c:otherwise>
			</c:choose>		
		</tbody>
	</table>
</div>