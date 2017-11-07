<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    
    String stuUserId = request.getParameter("stuUserId");
    String pageFlag = request.getParameter("pageFlag");
    if(stuUserId == null) {
    	//System.out.println("JSP###########stuUserId====>>>>我是空对象");
    	stuUserId = "";  //若学生ID对象为空就将其置为空字符串，以便后续判断
    }
    if(pageFlag == null) {pageFlag = "";}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>教师-学生评估</title>
		<tags:include/>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/style.css" />
		
		<style type="text/css">
			.rightUl li ul li .imgLogo {
				width: 50px;
				margin-top: 15px;
			}			
			.rightUl .rightLi ul li .imgLeixing {}
			#backRegion a {
				display:inline-block;
				width:58px;
				line-height:28px;
				text-align:center;
				background-color:#36b3df;
				color:#fff;
				border-radius: 3px;
				float:right;
				margin:10px 0;
			}
		</style>
		
		
		<script type="text/javascript">
			var $dg;
	        var $grid;
	        var rowsCount;  //定义全局变量，学生行记录总数
	        var stuUserId = "";  //概览页面，点击详情请求参数-定位学生ID,初始化为空字符串
	        var pageFlag = ""; //概览页面，点击详情请求参数-页面来源标志,初始化为空字符串
	        stuUserId = "<%=stuUserId%>";   //通过jsp的方法获取ModelAndView.addObject()，传递来的参数
	        pageFlag = "<%=pageFlag%>";
	        
	        $(function() {	        	
	        	$dg = $("#dg");
	        	$grid = $dg.datagrid({
	        		width: 'auto',
	                height: $(this).height() - 600,
	                url: "zznueg/portal/teacherplatform/getStuListByTcid4Teach", 
	                pagination: false,
	                rownumbers: true,
	                animate: true,
	                fitColumns: true,
	                striped: true,
	                border: true,
	                idField: 'stuUserId',
	                singleSelect: true,
	                columns: [[
						{
						    field: 'stuNo',
						    title: '学号',
						    width: parseInt($(this).width() * 0.1),
						    align: 'center',
						    editor: "text"
						},
		                {
		                    field: 'stuName',
		                    title: '学生姓名',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text"
		                },
		                {
		                    field: 'stuSex',
		                    title: '性别',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    formatter: function (value, row) {
		                        if ("1" == row.stuSex) return "<font color=green>男<font>";
		                        else if ("0" == row.stuSex) return "<font color=red>女<font>";
		                    },
		                    editor: "text"
		                },
		                {
						    field: 'acaId',
						    title: '学院ID',
						    width: parseInt($(this).width() * 0.1),
						    align: 'center',
						    editor: "text"
						},
		                {
		                    field: 'acaName',
		                    title: '学院名',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text"
		                },
		                {
						    field: 'deptId',
						    title: '系ID',
						    width: parseInt($(this).width() * 0.1),
						    align: 'center',
						    editor: "text"
						},
		                {
		                    field: 'deptName',
		                    title: '系名',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text"
		                },
		                {
						    field: 'classId',
						    title: '班级ID',
						    width: parseInt($(this).width() * 0.1),
						    align: 'center',
						    editor: "text"
						},
		                {
		                    field: 'className',
		                    title: '班级名',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text"
		                },
		                {
		                    field: 'teachUserId',
		                    title: '教师ID',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text"
		                }
		            ]],
		            onLoadSuccess: function(data){
		            	var rows = data.rows;  //所有行数据
		            	var row;  //行数据（一行的所有数据）
		            	var rowIndex;  //行索引（从0开始，即行号-1）
		            	rowsCount = rows.length;  //总行数（总记录数）
		            	if("" != stuUserId) {  //学生ID不为空，说明是从概览页面的详情重定向过来，记录定位到该学生即可
		            		$.each(data.rows, function(key, val) {
		            			//alert("data["+ key + "]" + val.stuUserId);
		            			if(stuUserId == val.stuUserId) {
		            				row = val;  //定位行数据
		            				//row = $dg.datagrid('getSelected');  //选中行的数据对象
			            			rowIndex = $dg.datagrid('getRowIndex',row);
			            			$dg.datagrid('selectRow', rowIndex);  //此时行索引即为选中的行索引，非行号
			            			return;
			            		}
		            		});	
		            	} else { //传递的学生ID为空说明是评分页面直接加载，定位到第一条记录，即不是从概览页面的详情重定向过来		            		
		            		$dg.datagrid('selectRow',0);  //加载成功后默认选择第一行数据
			            	row = $dg.datagrid('getSelected');  //选中行的数据对象
			            	rowIndex = $dg.datagrid('getRowIndex',row);  //选中行的行号
			            	//stuUserId为空隐藏“返回”按钮区域；//此种判断方法不合理，废弃；增加页面定位标志pageFlag，以此来判断页面来源
			            	//$("#backRegion").hide();
		            	}
		            	
		            	if("" != pageFlag) {  //增加页面定位标志pageFlag，以此来判断页面来源，从而进行页面“返回”按钮定位
		            		if(pageFlag == "fromStuOverview") {  //从“学生概览”页面跳转而来，返回时重新返回该界面
		            			$("#backRegion").show();
		            			$("#backRegion").click(function() {
		            				window.location.href="<%=basePath%>zznueg/portal/teacherplatform/studentOverview?stuUserId=" + stuUserId + "&pageFlag=fromStudentEvaluation";
		            			});
		            		} else {  //从其他页面跳转或返回过来的统一隐藏“返回”按钮
		            			$("#backRegion").hide();
		            		}
		            	} else {
		            		//pageFlag为空隐藏“返回”按钮区域
			            	$("#backRegion").hide();
		            	}
		            	
		            	//加载成功后默认显示第一条记录对应的学生信息
		            	$("#stuName").html(row.stuName);
		            	$("#acaName").html(row.acaName);
		            	$("#deptName").html(row.deptName);
		            	$("#className").html(row.className);
		            	
		            	loadStuUpInfoBySuid(row.stuUserId);
		            }
	        	});
	        	
	        	//点击“上一位”触发事件
	        	$("#prevBtn").click(function() {
	        		
	        		var row = $dg.datagrid('getSelected');  //获取已经选中行的数据对象
	        		var rowIndex = -1;//初始化行号，将行号定位在表格数据之外
	        		if(!row) {
	        			$dg.datagrid('selectRow',0);//没有初始化选中行数据时，默认选中第一行
	        			row = $dg.datagrid('getSelected');  //重新获取选中行的数据对象
	        		}
	        		
	        		rowIndex = $dg.datagrid('getRowIndex',row);  //获取已经选中行的行号
	        		
	        		rowIndex -= 1;   //点击“上一位”时行号减1
	        		$dg.datagrid('selectRow' ,rowIndex);//重新定位选中的行记录
	        		row = $dg.datagrid('getSelected');  //重新获取选中行的数据对象
	        		
	        		if(rowIndex < 0) {
	        			alert("已经是第一位了，没有上一位了！");
	        			//此时将记录定位在第第一位
	        			$dg.datagrid('selectRow', rowIndex + 1);
	        			row = $dg.datagrid('getSelected');
	        		}
	        		$("#stuName").html(row.stuName);
	            	$("#acaName").html(row.acaName);
	            	$("#deptName").html(row.deptName);
	            	$("#className").html(row.className);
	            	
	            	loadStuUpInfoBySuid(row.stuUserId);
	            	
	        	});
	        	
	        	//点击“下一位”触发事件
				$("#nextBtn").click(function() {
					
					var row = $dg.datagrid('getSelected');  //获取已经选中行的数据对象
	        		var rowIndex = -1;//初始化行号，将行号定位在表格数据之外
	        		if(!row) {
	        			$dg.datagrid('selectRow',0);//没有初始化选中行数据时，默认选中第一行
	        			row = $dg.datagrid('getSelected');  //重新获取选中行的数据对象
	        		}
	        		
	        		rowIndex = $dg.datagrid('getRowIndex',row);  //获取已经选中行的行号
	        		
	        		rowIndex += 1;   //点击“下一位”时行号加1
	        		$dg.datagrid('selectRow' ,rowIndex);//重新定位选中的行记录
	        		row = $dg.datagrid('getSelected');  //重新获取选中行的数据对象
	        		
	        		if(rowIndex >= rowsCount) {
	        			alert("已经是最后一位了，没有下一位了！");
	        			//此时将记录定位在第最后一位
	        			$dg.datagrid('selectRow', rowIndex - 1);
	        			row = $dg.datagrid('getSelected');
	        		}
	        		$("#stuName").html(row.stuName);
	            	$("#acaName").html(row.acaName);
	            	$("#deptName").html(row.deptName);
	            	$("#className").html(row.className);
	            		            	
	            	loadStuUpInfoBySuid(row.stuUserId);
	            	
	            	//alert("总行数（总记录数）=" + rowsCount);  //全局变量，直接使用即可
	            	//alert("rowIndex=" + rowIndex);
	        		//alert("selectedRowData=[" + row.stuUserId + ", " + row.stuNo + ", " + row.stuName + "]");
	        	});	        	
				
				
	        });
		
	        //通过学生用户ID获取学生上传的资料信息
			function loadStuUpInfoBySuid(stuUserId) {
				$.ajax({
            		url: "zznueg/portal/teacherplatform/getUploadListBySuid4Teach",
            		method: "POST",
            		async:false,
            		dataType: "html",
            		data: {"stuUserId": stuUserId},
            		success: function(data) {
            			$("#infoDiv").html($(data).html()); 
            		}
            		/* error: function(data) {
            			$.messager.show({
	                        title: data.jsonMsg.title,
	                        msg: data.jsonMsg.message,
	                        timeout: 1000 * 2
	                    });
            		} */
            	});
			}
			
			//提交评分
			function submitScore(clkObj){				
				var clickId = $(clkObj).attr("id");	  //获取点击对象的ID值，即列表行的pid
				var $input = $(clkObj).parent().prev().find("#evalScore");
				var evalScore = $input.val();
				/* if($input.prop("disabled")) {  //打开此段代码后将success中的 $(clkObj).removeAttr('onclick');注释掉
					alert("警告，不可重复提交！");
					return;
				} */
				if(null == evalScore || "" == evalScore) {
					alert("请输入评估分数！");
					$input.focus();
					return;
				}
				if(evalScore < 0 || evalScore > 100) {
					alert("错误，评分必须在【0-100】之间！");
					return;
				}
				
				$.ajax({
					url: "zznueg/portal/teacherplatform/saveEvalScore",
					method: "POST",
					dataType: "json",
					async:false,
            		data: {
            			"pid": clickId,
            			"evalScore": evalScore
            		},
            		success: function(data) {            			
            			if(data.status) {
            				$input.attr("disabled","disabled");
            				$(clkObj).removeAttr('onclick');
            				
            				$.messager.show({
    	                        title: data.title,
    	                        msg: data.message,
    	                        timeout: 1000 * 2
    	                    });
            			}
           			},
           			error: function(data) {
           				$.messager.show({
	                        title: data.title,
	                        msg: data.message,
	                        timeout: 1000 * 2
	                    });
           			}
				});
				
			}
	        
			//视频预览
			function showPreview4Video(clkObj) {
				//alert("double click me to show video");
				var $clkObj = $(clkObj);
				var uploadPid = $clkObj.find("#uploadPid").val();
				//var uploadPath = $clkObj.find("#uploadPath").val();  //修改为下面的在线预览路径
				var previewPath = $clkObj.find("#previewPath").val();
				var evalScore = $clkObj.find("#evalScore").val();
				var stuUserId = $clkObj.find("#stuUserId").val();
				//由于在线预览路径previewPath中含有特殊字符因此对整个URL使用encodeURI进行重新编码，或者使用escape或encodeURIComponent对每个参数单独重新编码
				if(pageFlag == "fromStuOverview") {  //从“学生概览”页面跳转而来，返回时重新返回该界面
					window.location.href = encodeURI("<%=basePath%>zznueg/portal/teacherplatform/videoPlay4Preview?uploadPid=" + uploadPid + "&previewPath=" + previewPath + "&evalScore=" + evalScore + "&stuUserId=" + stuUserId + "&pageFlag=fromStuOverview");
				} else {
					window.location.href = encodeURI("<%=basePath%>zznueg/portal/teacherplatform/videoPlay4Preview?uploadPid=" + uploadPid + "&previewPath=" + previewPath + "&evalScore=" + evalScore + "&stuUserId=" + stuUserId + "&pageFlag=fromStudentEvaluation");
				}
				
			}
			//PDF文档预览
			function showPreview4Pdf(clkObj) {
				//alert("double click me to show pdf");
				var $clkObj = $(clkObj); //alert($clkObj.attr("id"));
				var uploadPid = $clkObj.find("#uploadPid").val();
				//var uploadPath = $clkObj.find("#uploadPath").val();  //修改为下面的在线预览路径
				var previewPath = $clkObj.find("#previewPath").val();
				var evalScore = $clkObj.find("#evalScore").val();
				var stuUserId = $clkObj.find("#stuUserId").val();
				//由于在线预览路径previewPath中含有特殊字符因此对整个URL使用encodeURI进行重新编码，或者使用escape或encodeURIComponent对每个参数单独重新编码
				if(pageFlag == "fromStuOverview") {  //从“学生概览”页面跳转而来，返回时重新返回该界面
					window.location.href = encodeURI("<%=basePath%>zznueg/portal/teacherplatform/pdfViewer4Preview?uploadPid=" + uploadPid + "&previewPath=" + previewPath + "&evalScore=" + evalScore + "&stuUserId=" + stuUserId + "&pageFlag=fromStuOverview");
				} else {
					window.location.href = encodeURI("<%=basePath%>zznueg/portal/teacherplatform/pdfViewer4Preview?uploadPid=" + uploadPid + "&previewPath=" + previewPath + "&evalScore=" + evalScore + "&stuUserId=" + stuUserId + "&pageFlag=fromStudentEvaluation");
				}
			}
			
			/**
			 * 文件下载
			 * 注：这里直接调用学生平台的下载方法，按规范应该在教师平台中单独写下载方法
			 */
			function downloadFile(clkObj) {
				//var $input = $(clkObj).parent().parent().find("#uploadPath");
				//var uploadPath = $input.val();
				//var filePath = uploadPath;
				var filePath = $(clkObj).parent().parent().find("#uploadPath").val();
				/* 超链接方式文件下载(简单实用) */
				window.location.href = encodeURI("<%=basePath%>zznueg/portal/studentplatform/downloadFile?filePath=" + filePath);
			}
			
			/**
			 * 判断要下载的文件是否存在；下载前先判断，如不存在则不进入downloadFile方法，存在时再执行下载方法
			 * 注：这里直接调用学生平台的下载方法，按规范应该在教师平台中单独写下载方法
			 */
			function doesFileExist(clkObj) {
				var filePath = $(clkObj).parent().parent().find("#uploadPath").val();
				$.ajax({
					url : "zznueg/portal/studentplatform/doesFileExist",
					type : "POST",
					async : false,
					dataType : "json",
					data:{"filePath": filePath},
					success: function(data) {
			        	//alert("status=" + data.status + ",message=" + data.message);
						if(data.status) {
							downloadFile(clkObj);  //文件存在，再进行下载
						} else {
							$.messager.show({
		                        title: data.title,
		                        msg: data.message,
		                        timeout: 1000 * 2
		                    });
						}
					}
				});
			}
			
		</script>
	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="javascript:void(0)">教师平台</a></li>
				<li><a href="javascript:void(0)">学生评估</a></li>
			</ul>
		</div>

		<div class="rightinfo">
			<p class="rightP">
				<span>考试中心</span>
			</p>
			<div class="shuoming">
				<!-- 说明：本次考试起止日期为：2017-09-01 00:00:00 至 2017-12-31 23:59:59，请合理安排时间！ -->
				<c:choose>
					<c:when test="${gtFlag == '1'}">
						<p style="color: red;font-weight: bold;">提示：已经过了考试时间，上次考试期限为：${startDate} 至 ${endDate}</p>
					</c:when>
					<c:otherwise>
						<p>提示：本次考试起止日期为：${startDate} 至 ${endDate}，请合理安排时间！</p>
					</c:otherwise>
				</c:choose>	
			</div>
			<p id="backRegion" style="display: none;"><span><a href="javascript:void(0);">返回</a></span></p>
			
			<div class="easyui-layout" id="tb" style="display: none;"> <!-- style="display: none;" -->
				<table class="tablelist" id="dg" title="学生列表"></table> <!-- 存放教师教授的学生列表，测试成功后隐藏此列表 -->
			</div>
			
			<div class="rightxinxi clear">
				<span class="rightImg" id="stuImg"><img src="resources/zznueg/images/i07.png"/></span>
				<span class="rightSpan" id="stuName">暂无</span>
				<span class="rightSpan" id="acaName">暂无</span>
				<span class="rightSpan" id="deptName">暂无</span>
				<span class="rightSpan" id="className">暂无</span>
				<span class="shang" id="prevBtn">上一位</span>
				<span class="next" id="nextBtn">下一位</span>
			</div>
			
			<div id="infoDiv"></div>
		</div>
<!-- 
		<script type="text/javascript">
			/* $('.tablelist tbody tr:odd').addClass('odd');
			
			
			//alert($("#tbData").children("tr").length);//获取tbody下面tr个数
			//alert($("#tbData tr:eq(0)").find("td").length); //获取第一个tr下面td的个数
    		//var trArray = $("#tbData").children("tr");  //获取tr对象数组，find()和children()两个方法都可以获取； 
   			/* var tr_tdCount;
   			for(var i = 0; i < trArray.length; i++) {    
   				tr_tdCount = trArray.eq(i).find("td").length;  //获取第i个tr下面td的个数
   				if(tr_tdCount > 1) {
   					trArray.eq(i).click(function(){ //双击行触发事件             					
       					alert(trArray.eq(i).find("td").eq(0).attr('id'));
        			});
				}         				
   			} */    
			
			$("#dg tbody tr").eq(0).click(function(){
				alert("test table row clicked");
			}); */
			
			/* $("tbody tr").eq(0).click(function(){
				alert("test first row clicked");
				//window.location.href = "zznueg/portal/teacherplatform/videoPlay"
			});
			$("tbody tr").eq(1).click(function(){
				window.location.href = "zznueg/portal/teacherplatform/videoPlay"
			});			
			$("tbody tr").eq(2).click(function(){
				window.location.href = "zznueg/portal/teacherplatform/pdfViewer"
			});
			$("tbody tr").eq(3).click(function(){
				window.location.href = "zznueg/portal/teacherplatform/pdfViewer"
			});	 */		
		</script>
 -->
	</body>

</html>