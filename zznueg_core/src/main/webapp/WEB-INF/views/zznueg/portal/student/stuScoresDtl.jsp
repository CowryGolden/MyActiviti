<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="rightPinggu">
<table class="imgtable">
	<thead>
		<tr>
			<th width="100px;">缩略图</th>
			<th>文件名称</th>
			<th>文件类型</th>
			<th>最后上传时间</th>
			<th>科目成绩（满100）</th>
			<th>操作</th>
		</tr>
	</thead>	
	<tbody id="tbData">
		<c:choose>
			<c:when test="${listData != null && listData.size() > 0}">
				<c:set var="totalScores" value="0"></c:set>	 <!-- 设置求和初始值 -->
				<c:forEach var="list" items="${listData}">
					<c:set var="totalScores" value="${totalScores + list.evalScore}"></c:set>  <!-- 迭代累加 -->
						<c:if test="${list.subjectId=='01'}">
					<tr id="${list.subjectId}" ondblclick="showPreview4Video(this);">
							<td class="imgtd"><img src="resources/zznueg/img/weige.png" /></td>
						</c:if>
						<c:if test="${list.subjectId=='02'}">
					<tr id="${list.subjectId}" ondblclick="showPreview4Video(this);">
							<td class="imgtd"><img src="resources/zznueg/img/banshu.png" /></td>
						</c:if>
						<c:if test="${list.subjectId=='03'}">
					<tr id="${list.subjectId}" ondblclick="showPreview4Pdf(this);">
							<td class="imgtd"><img src="resources/zznueg/img/jiaoan.png" /></td>
						</c:if>
						<c:if test="${list.subjectId=='04'}">
					<tr id="${list.subjectId}" ondblclick="showPreview4Pdf(this);">
							<td class="imgtd"><img src="resources/zznueg/img/kejian.png" /></td>
						</c:if>
						
						<td>${list.subjectName}</td>
						
						<c:if test="${list.subjectId=='01' || list.subjectId=='02'}">
							<td><img style="width: 16px;" src="resources/zznueg/img/shipin.png"/></td>
						</c:if>
						<c:if test="${list.subjectId=='03' || list.subjectId=='04'}">
							<td><img style="width: 16px;" src="resources/zznueg/img/word.png"/></td>
						</c:if>
						
						<td>${list.uploadTime} (第${list.uploadCount}次)</td>
						<td class="rightUl"><input id="evalScore" value="${list.evalScore}" type="text" maxlength="3" class="selects" readonly="readonly" disabled="disabled"/></td>
						<td>
							<input name="pid" id="pid" type="hidden" value="${list.uploadPid}"/>
							<input name="stuUserId" id="stuUserId" type="hidden" value="${list.uploadStuUserId}"/>
							<input name="subjectId" id="subjectId" type="hidden" value="${list.subjectId}"/>
							<input name="uploadCount" id="uploadCount" type="hidden" value="${list.uploadCount}"/>
							<input name="uploadPath" id="uploadPath" type="hidden" value="${list.uploadPath}"/>
							<c:choose>
								<c:when test="${gtFlag == '1'}">
									<a id="${list.uploadPid}" class="submit" href="javascript:void(0)" onclick="javascript:alert('不在考试期间，不允许修改！');">修改</a>
								</c:when>
								<c:otherwise>
									<a id="${list.uploadPid}" class="submit" href="javascript:void(0)" onclick="modifySubject(this)">修改</a>
								</c:otherwise>
							</c:choose>							
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						总分：${totalScores}
						(
							<c:if test="${0*4 <= totalScores && 60*4 > totalScores}"><font color=red>不及格<font></c:if>
							<c:if test="${60*4 <= totalScores && 70*4 > totalScores}"><font color=green>及格<font></c:if>
							<c:if test="${70*4 <= totalScores && 80*4 > totalScores}"><font color=green>一般<font></c:if>
							<c:if test="${80*4 <= totalScores && 90*4 > totalScores}"><font color=green>良好<font></c:if>
							<c:if test="${90*4 <= totalScores && 100*4 >= totalScores}"><font color=green>优秀<font></c:if>
						)
					</td>
					<td></td>
				</tr>
			</c:when>
			<c:otherwise><tr><td colspan="6"><div style="font-size:16px;font-weight:bold;">您还没有上传资料！</div></td></tr></c:otherwise>
		</c:choose>
	</tbody>
</table>
</div>

