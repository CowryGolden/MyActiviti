<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="subjectInfo">
<%-- <div>${startDate} 至 ${endDate} now:${now} gtFlag:${gtFlag}</div> --%>
	<ul class="liebiaoUl">
		<c:choose>
			<c:when test="${flag == 'all'}">			
				<c:choose>
					<c:when test="${rtnMap.gmData.rows.size()>0}">					
						<c:forEach var="row" items="${rtnMap.gmData.rows}">														
							<li>
								<div class="liebiaoName">
									<span>${row.fileName}</span>									
								</div>
								<div class="liebiaoAttributes">
									<input name="pid" id="pid" type="hidden" value="${row.pid}"/>
									<input name="stuUserId" id="stuUserId" type="hidden" value="${row.stuUserId}"/>
									<input name="subjectId" id="subjectId" type="hidden" value="${row.subjectId}"/>
									<input name="uploadCount" id="uploadCount" type="hidden" value="${row.uploadCount}"/>
									<input name="uploadPath" id="uploadPath" type="hidden" value="${row.uploadPath}"/>
									
									<span>类别：${row.subjectName}</span>
									<span>上传者：${row.stuUserName}</span>
									<span>上传时间：${row.uploadTime}(第${row.uploadCount}次)</span>
									<input type="checkbox" id="liebiaoCheckbox" name="${row.pid}" style="display: none"/> <!-- 此复选框暂不用，隐藏掉 -->	
									<%-- 待优化，样式显示有问题
									<span>
										评分：
										<c:choose>
											<c:when test="${null == row.evalScore || '' == row.evalScore}"><font color=red>未评分<font></c:when>
											<c:otherwise>${row.evalScore}</c:otherwise>
										</c:choose>																		
									</span>
									<span>
										评分时间：
										<c:choose>
											<c:when test="${null == row.evalTime || '' == row.evalTime}"><font color=red>暂无<font></c:when>
											<c:otherwise>${row.evalTime.substring(0,10)}</c:otherwise>
										</c:choose>																				
									</span>
									 --%>
									<span>评分：${row.evalScore}</span>
									<span>评分时间：${row.evalTime.substring(0,10)}</span>			
									<c:choose>
										<c:when test="${gtFlag == '1'}">
											<div id="${row.pid}" class="liebiaoSubmit" onclick="javascript:alert('不在考试期间，不允许修改！');">修改</div>
										</c:when>
										<c:otherwise>
											<div id="${row.pid}" class="liebiaoSubmit" onclick="modClick(this)">修改</div>
											<div id="downloadFile" class="liebiaoSubmit" onclick="doesFileExist(this)">下载</div>
										</c:otherwise>
									</c:choose>
								</div>								
							</li>
						</c:forEach>					
					</c:when>
					<%-- <c:otherwise><li><div style="font-size:16px;font-weight:bold;">暂时没有数据！</div></li></c:otherwise> --%>
					<c:otherwise><li><div class="liebiaoName"><span>暂时没有数据！</span></div></li></c:otherwise>
				</c:choose>			
				<!-- 此处可以添加分页代码！！！待优化 -->
			</c:when>
			<c:otherwise>			
				<c:choose>
					<c:when test="${rtnMap.listData.size()>0}">					
						<c:forEach var="row" items="${rtnMap.listData}">														
							<li>
								<div class="liebiaoName">
									<span>${row.fileName}</span>
								</div>
								<div class="liebiaoAttributes">
									<input name="pid" id="pid" type="hidden" value="${row.pid}"/>
									<input name="stuUserId" id="stuUserId" type="hidden" value="${row.stuUserId}"/>
									<input name="subjectId" id="subjectId" type="hidden" value="${row.subjectId}"/>
									<input name="uploadCount" id="uploadCount" type="hidden" value="${row.uploadCount}"/>
									<input name="uploadPath" id="uploadPath" type="hidden" value="${row.uploadPath}"/>
									
									<span>类别：${row.subjectName}</span>
									<span>上传者：${row.stuUserName}</span>
									<span>上传时间：${row.uploadTime}(第${row.uploadCount}次)</span>
									<input type="checkbox" id="liebiaoCheckbox" name="${row.pid}" style="display: none"/>	<!-- 此复选框暂不用，隐藏掉 -->	
									<span>评分：${row.evalScore}</span>
									<span>评分时间：${row.evalTime.substring(0,10)}</span>				
									<c:choose>
										<c:when test="${gtFlag == '1'}">
											<div id="${row.pid}" class="liebiaoSubmit" onclick="javascript:alert('不在考试期间，不允许修改！');">修改</div>
										</c:when>
										<c:otherwise>
											<div id="${row.pid}" class="liebiaoSubmit" onclick="modClick(this)">修改</div>
											<div id="downloadFile" class="liebiaoSubmit" onclick="doesFileExist(this)">下载</div>
										</c:otherwise>
									</c:choose>
								</div>								
							</li>
						</c:forEach>
					</c:when>
					<%-- <c:otherwise><li><div style="font-size:16px;font-weight:bold;">暂时没有数据！</div></li></c:otherwise> --%>
					<c:otherwise><li><div class="liebiaoName"><span>暂时没有数据！</span></div></li></c:otherwise>					
				</c:choose>			
			</c:otherwise>	
		</c:choose>
	</ul>
</div>