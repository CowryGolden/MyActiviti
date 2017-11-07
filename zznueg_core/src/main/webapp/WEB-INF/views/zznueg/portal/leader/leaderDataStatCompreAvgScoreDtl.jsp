<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="avgScoreDtl">
	<table class="tablelist" style="width: 98%; margin: 20px auto;">
		<tbody>
			<tr>
				<td>序号</td>
				<td>学院</td>
				<td>微格</td>
				<td>板书</td>
				<td>教案</td>
				<td>课件</td>
			</tr>
			<c:choose>
				<c:when test="${rtnList != null && rtnList.size() > 0}">
					<c:forEach var="list" items="${rtnList}" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td>${list.acaName}</td>
							<c:choose>
								<c:when test="${list.weigeAvg != null}">
									<td>${list.weigeAvg}</td>
								</c:when>
								<c:otherwise><td>--</td></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${list.banshuAvg != null}">
									<td>${list.banshuAvg}</td>
								</c:when>
								<c:otherwise><td>--</td></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${list.jiaoanAvg != null}">
									<td>${list.jiaoanAvg}</td>
								</c:when>
								<c:otherwise><td>--</td></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${list.kejianAvg != null}">
									<td>${list.kejianAvg}</td>
								</c:when>
								<c:otherwise><td>--</td></c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise><tr><td colspan="6"><div style="font-size:16px;font-weight:bold;">暂无数据！</div></td></tr></c:otherwise>
			</c:choose>			
		</tbody>
	</table>
</div>


