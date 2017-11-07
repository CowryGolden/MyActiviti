<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="rightPinggu">
<table id="imgtable" class="imgtable">
	<thead>
		<tr>
			<th width="100px;">缩略图</th>
			<th>文件名称</th>
			<th>文件类型</th>
			<th>最后上传时间</th>
			<th>成绩打分（满100）</th>
			<th>操作</th>
		</tr>
	</thead>	
	<tbody id="tbData">
		<c:choose>
			<c:when test="${rtnList != null && rtnList.size() > 0}">	
				<c:forEach var="list" items="${rtnList}">					
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
						
						<!-- 将上传id、上传路径、上传学生ID等值放于隐藏标签中 -->
						<input id="uploadPid" value="${list.uploadPid}" type="hidden"/>
						<input id="uploadPath" value="${list.uploadPath}" type="hidden"/>
						<input id="stuUserId" value="${list.uploadStuUserId}" type="hidden"/>
						<input id="previewPath" value="${list.previewPath}" type="hidden"/>
						
						<td>${list.subjectName}</td>
						
						<c:if test="${list.subjectId=='01' || list.subjectId=='02'}">
							<td><img style="width: 16px;" src="resources/zznueg/img/shipin.png"/></td>
						</c:if>
						<c:if test="${list.subjectId=='03' || list.subjectId=='04'}">
							<td><img style="width: 16px;" src="resources/zznueg/img/word.png"/></td>
						</c:if>
						
						<td>${list.uploadTime} (第${list.uploadCount}次)</td>
						
						<c:choose>
							<c:when test="${gtFlag == 1}">  <!-- 不在考试期间处理 -->
								<td class="rightUl"><input id="evalScore" disabled="disabled" value="${list.evalScore}" type="text" maxlength="3" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" class="selects" /></td>
								<td><a id="${list.uploadPid}" class="submit" href="javascript:void(0)" onclick="javascript:alert('不在考试期间，不能进行评分！');">提交</a></td>
							</c:when>
							<c:otherwise>
								<td class="rightUl"><input id="evalScore" value="${list.evalScore}" type="text" maxlength="3" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" class="selects" /></td>
								<td>
									<a id="${list.uploadPid}" class="submit" href="javascript:void(0)" onclick="submitScore(this)">提交</a> | 
									<a id="downloadFile" class="submit" href="javascript:void(0)" onclick="doesFileExist(this)">下载</a>
								</td>
							</c:otherwise>
						</c:choose>						
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise><tr><td colspan="6"><div style="font-size:16px;font-weight:bold;">该学生还没有上传资料！</div></td></tr></c:otherwise>
		</c:choose>
	</tbody>
</table>
</div>

