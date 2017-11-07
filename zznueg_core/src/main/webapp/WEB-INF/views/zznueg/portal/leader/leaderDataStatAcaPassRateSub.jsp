<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<div id="passRateSub">
	<table class="tablelist">
		<tbody>
			<tr>
				<td>科目</td>
				<td>通过率</td>
				<td>优秀</td>
				<td>良好</td>
				<td>一般</td>
				<td>及格</td>
				<td>不及格</td>
			</tr>
			<c:choose>
				<c:when test="${passRate != null && passRate.size() > 0}">
					<tr>
						<td>微格</td>
						<td><fmt:formatNumber value='${1 - passRate.weigeERate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.weigeARate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.weigeBRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.weigeCRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.weigeDRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.weigeERate}' type='percent' minFractionDigits='2'/></td>
					</tr>
					<tr>
						<td>板书</td>
						<td><fmt:formatNumber value='${1 - passRate.banshuERate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.banshuARate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.banshuBRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.banshuCRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.banshuDRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.banshuERate}' type='percent' minFractionDigits='2'/></td>
					</tr>
					<tr>
						<td>教案</td>
						<td><fmt:formatNumber value='${1 - passRate.jiaoanERate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.jiaoanARate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.jiaoanBRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.jiaoanCRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.jiaoanDRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.jiaoanERate}' type='percent' minFractionDigits='2'/></td>
					</tr>
					<tr>
						<td>课件</td>
						<td><fmt:formatNumber value='${1 - passRate.kejianERate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.kejianARate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.kejianBRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.kejianCRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.kejianDRate}' type='percent' minFractionDigits='2'/></td>
						<td><fmt:formatNumber value='${passRate.kejianERate}' type='percent' minFractionDigits='2'/></td>
					</tr>
				</c:when>
				<c:otherwise><tr><td colspan="7"><div style="font-size:16px;font-weight:bold;">暂无数据！</div></td></tr></c:otherwise>
			</c:choose>			
		</tbody>
	</table>
</div>
