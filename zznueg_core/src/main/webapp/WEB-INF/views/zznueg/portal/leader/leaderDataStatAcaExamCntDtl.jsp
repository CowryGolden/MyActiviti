<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<div id="tbAcaExamCntDiv">
	<table class="tablelist" style="width: 85%; margin: 20px auto;">
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
						<tr id="${list.acaId}">
							<td>${status.index + 1}</td>
							<td>${list.acaName}</td>
							<c:choose>
								<c:when test="${list.weige != null}">
									<td>${list.weige}</td>
								</c:when>
								<c:otherwise><td>--</td></c:otherwise>	
							</c:choose>
							<c:choose>
								<c:when test="${list.banshu != null}">
									<td>${list.banshu}</td>
								</c:when>
								<c:otherwise><td>--</td></c:otherwise>	
							</c:choose>
							<c:choose>
								<c:when test="${list.jiaoan != null}">
									<td>${list.jiaoan}</td>
								</c:when>
								<c:otherwise><td>--</td></c:otherwise>	
							</c:choose>
							<c:choose>
								<c:when test="${list.kejian != null}">
									<td>${list.kejian}</td>
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