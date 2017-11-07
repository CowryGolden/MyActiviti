<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<div id="passRateSum">
	<table class="tablelist tablelist1">
		<tbody>
			<c:choose>
				<c:when test="${rtnList != null && rtnList.size() > 0}">
					<c:forEach var="list" items="${rtnList}" varStatus="status">
						<tr id="${list.acaId}" onclick="showAcaPassRateInfo(this);">
							<td>${status.index + 1}</td>
							<td id="acaName">${list.acaName}</td>
							<td style="width: 20%;">
								<div class="layui-progress  td_progress" style="width: 120px;margin-left:100px;display: inline-block;">
									<div class="layui-progress-bar layui-bg-blue" lay-percent="<fmt:formatNumber value='${list.zongPassRate}' type='percent' minFractionDigits='2'/>" style="margin-right:0 ;"></div>
								</div>
							</td>
							<td><fmt:formatNumber value='${list.zongPassRate}' type='percent' minFractionDigits='2'/></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise><tr><td colspan="3"><div style="font-size:16px;font-weight:bold;">暂无数据！</div></td></tr></c:otherwise>
			</c:choose>			
		</tbody>
	</table>
</div>