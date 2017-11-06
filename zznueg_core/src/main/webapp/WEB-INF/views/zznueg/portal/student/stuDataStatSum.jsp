<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>学生-数据统计-总得分</title>
		<tags:include_zznueg/>
		
		<style type="text/css">
			.average {
				width: 95%;
				margin:  0 auto;
			}
			
			#main1 {
				height: 500px;
				text-align: center;
			}
			
			.tittle {
				height: 45px;
				background-color: #F2F2F2;
				border-left: 3px solid #3EAFE0;
				line-height: 45px;
				font-size: 17px;
				margin: 20px 0;
				padding-left: 10px;
			}
			
			.tablelist tr td {
				border: 1px solid gainsboro;
			}
			.paiming ul li{
				line-height: 50px;
				text-align: center;
				padding: 0 5px;
				float: left;
			}
			.paiming ul li div{
				margin-left: 15px;
			}
		</style>

	</head>

	<body>
		<div class="average clear">
			<div class="tittle2_select clear">
				<div class="tittle">
					数据与统计
				</div>
				<div id="main1">
				
				<div class="paiming">
					<c:choose>
						<c:when test="${rtnMap.totalMapData.zong != null && rtnMap.totalMapData.zong != ''}">
							<ul class="clear">
								<li>
									<div>
										<span>总得分：</span>
										<img src="" alt="" />
										<span>${rtnMap.totalMapData.zong}分</span>
									</div>
								</li>
								<li>
									<div>
										<span>满分：</span>
										<img src="" alt="" />
										<span>400分</span>
									</div>
								</li>
								<li>
									<div>
										<span>排名：</span>
										<span style="display: inline-block;height: 20px;padding:0 10px;background-color: #3EAFE0;color: #fff;float: right;margin-top: 15px;line-height: 20px;">第 ${rtnMap.totalMapData.zongRank} 名</span>
									</div>
								</li>						
							</ul>
						</c:when>
						<c:otherwise><ul class="clear"><li><div style="font-size:16px;font-weight:bold;">暂无数据！</div></li></ul></c:otherwise>
					</c:choose>					
				</div>

					<table class="tablelist tablelist1">
						<thead>
							<tr>
								<th>科目</th>
								<th>得分</th>
								<th>排名图</th>
								<th>排名</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${rtnMap.subListData != null && rtnMap.subListData.size() > 0}">
									<c:forEach var="list" items="${rtnMap.subListData}">
										<c:choose>
											<c:when test="${list.subjectId != null && list.subjectId != ''}">
												<tr>
													<c:if test="${list.subjectId=='01'}">
														<td>微格</td>
														<td>${list.evalScore}</td>
														<td style="width: 20%;">
															<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
																<div class="layui-progress-bar layui-bg-blue" lay-percent="<fmt:formatNumber value='${(list.totalCount + 1 - list.rank)/(list.totalCount)}' type='percent'/>" style="margin-right:0 ;"></div>
															</div>
														</td>
														<td>第 ${list.rank} 名</td>
													</c:if>
												</tr>
												<tr>
													<c:if test="${list.subjectId=='02'}">
														<td>板书</td>
														<td>${list.evalScore}</td>
														<td style="width: 20%;">
															<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
																<div class="layui-progress-bar layui-bg-blue" lay-percent="<fmt:formatNumber value='${(list.totalCount + 1 - list.rank)/(list.totalCount)}' type='percent'/>" style="margin-right:0 ;"></div>
															</div>
														</td>
														<td>第 ${list.rank} 名</td>
													</c:if>
												</tr>
												<tr>
													<c:if test="${list.subjectId=='03'}">
														<td>教案</td>
														<td>${list.evalScore}</td>
														<td style="width: 20%;">
															<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
																<div class="layui-progress-bar layui-bg-blue" lay-percent="<fmt:formatNumber value='${(list.totalCount + 1 - list.rank)/(list.totalCount)}' type='percent'/>" style="margin-right:0 ;"></div>
															</div>
														</td>
														<td>第 ${list.rank} 名</td>
													</c:if>
												</tr>
												<tr>
													<c:if test="${list.subjectId=='04'}">
														<td>课件</td>
														<td>${list.evalScore}</td>
														<td style="width: 20%;">
															<div class="layui-progress  td_progress" style="width: 120px;display: inline-block;">
																<div class="layui-progress-bar layui-bg-blue" lay-percent="<fmt:formatNumber value='${(list.totalCount + 1 - list.rank)/(list.totalCount)}' type='percent'/>" style="margin-right:0 ;"></div>
															</div>
														</td>
														<td>第 ${list.rank} 名</td>
													</c:if>
												</tr>
											</c:when>
											<c:otherwise><tr><td colspan="4"><div style="font-size:16px;font-weight:bold;">暂无数据！</div></td></tr></c:otherwise>
										</c:choose>						
									</c:forEach>
								</c:when>
								<c:otherwise><tr><td colspan="4"><div style="font-size:16px;font-weight:bold;">暂无数据！</div></td></tr></c:otherwise>
							</c:choose>
						</tbody>
						
					</table>
					
				</div>
			</div>
		</div>

	</body>
	
	
	<script type="text/javascript">
		layui.use(['element'], function() {
			var form = layui.element();
		})
		setIframeH();

		function setIframeH() {
			var body = $(document.body);
			var iframe = $(parent.document.getElementById('parentIframe'));
			iframe.height(body.height()+50);
		}
	</script>

</html>