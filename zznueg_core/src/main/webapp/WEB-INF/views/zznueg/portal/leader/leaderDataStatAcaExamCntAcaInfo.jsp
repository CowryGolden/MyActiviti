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

<div id="ckAcaInfoDiv">
	<ul class="clear">
		<c:choose>
			<c:when test="${rtnList != null && rtnList.size() > 0}">
				<c:forEach var="list" items="${rtnList}" varStatus="status">
					<li>
						<input type="checkbox" name="acaName" id="${list.acaId}" value="${list.acaId}" /> <span>${list.acaName}</span>
					</li>
				</c:forEach>
			</c:when>
			<c:otherwise><li><div style="font-size:16px;font-weight:bold;">暂无数据！</div></li></c:otherwise>
		</c:choose>
		<span style="color: #4596E5;float: right; margin-top: 13px;">*(最多可同时选择5个学院)</span>
		<span class="xysubmit" onclick="">提交</span>
	</ul>
</div>