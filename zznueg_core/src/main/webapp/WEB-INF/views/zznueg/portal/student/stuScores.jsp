<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>学生-学生成绩</title>
		<tags:include/>
		<link href="resources/zznueg/css/style.css" rel="stylesheet" type="text/css" />
		
		<style type="text/css">
			.rightUl li ul li .imgLogo {
				width: 50px;
				margin-top: 15px;
			}
			
			.rightUl .rightLi ul li .imgLeixing {}
		</style>
		
		<script type="text/javascript">
			$(function() {
				loadStuInfo();
				loadStudentScoresDtl();
			});
			
			/* $(document).ready(function() {
				loadStuInfo();
			}); */
			//加载学生基本信息
			function loadStuInfo() {
				$.ajax({
					url : "zznueg/portal/studentplatform/getStuInfoBySuid",
					method : "POST",
					async : false,
					dataType : "html",
					success : function(data) {
						/* $("#stuName").html(data.stuName);
		            	$("#acaName").html(data.acaName);
		            	$("#deptName").html(data.deptName);
		            	$("#className").html(data.className);
						alert("++++data====" + data); */
						$("#stuInfoDiv").html($(data).html());
					}
				});
			}
			
			//加载学生科目成绩信息
			function loadStudentScoresDtl() {
				$.ajax({
					url: "zznueg/portal/studentplatform/getStudentScores4Stu",
            		method: "POST",
            		async:false,
            		dataType: "html",
            		success: function(data) {
            			 $("#stuScoresDiv").html($(data).html());
            		}
				});
			}
			
			//学生成绩节目修改上传信息
			function modifySubject(clkObj) {
				var clickId = $(clkObj).attr("id");		//获取点击对象的ID值，即列表行的pid，将该内容塞到隐藏标签中
				//此处相当于新增因此不用设置pid的值
				//$("#modForm").find("input[name=pid]").val(clickId);  //当不同表单中有同名标签时用此方法；若唯一，用下面方法
				
				//弹出修改对话框
				$(".tip").fadeIn(200);
				
				var oldSubjectId = $(clkObj).parent().find("input[id='subjectId']").val();
				$.ajax({
					url:'zznueg/manage/subjectinfo/findAllSubjects',
					method:'get',
					dataType: "json",
					success: function(data) {
   			        	//alert(data.toString());
   			        	$.each(data, function(key, val) {
   			        		if(val.subjectId == oldSubjectId) {
   			        			$("#modForm").find("#subjectId1").append('<option value="' + val.subjectId + '" selected>' + val.subjectName + '</option>');
   			        		} else {
   			        			$("#modForm").find("#subjectId1").append('<option value="' + val.subjectId + '">' + val.subjectName + '</option>');
   			        		}
                       	});
						 
   			        }
				});				
				
				/* $(".sure").click(function() {
					$(".tip").fadeOut(100);
				}); */
				$(".cancel").click(function() {
					$(".tip").fadeOut(100);
				});
			}		
			
			//持久化修改资料，被modUpload(obj)方法调用
			function modUploadInfo() {
				$("#modForm").form('submit',{
		            url: "zznueg/portal/studentplatform/saveUploadInfo",
		            onSubmit: function () {		            	
		                $.messager.progress({
		                    title: '提示',
		                    text: '数据处理中，请稍后....'
		                });
		                var isValid = true; //$(this).form('validate');
		                //alert($(this).form('validate'));
		                if (!isValid) {
		                	$.messager.progress('close');
		                }
		                return isValid; 
		            },
		            success: function (result) {
		                $.messager.progress('close');
		                result = eval("(" + result + ")");
		                if (result.status) {
		                	//将弹出层隐藏
		                	$(".tip").fadeOut(100);
		                    //修改信息持久化成功后将成绩信息重新加载
		                    loadStudentScoresDtl();
		                    $.messager.show({
		                        title: result.title,
		                        msg: result.message,
		                        timeout: 1000 * 2
		                    });
		                } else {
		                    $.messager.show({
		                        title: result.title,
		                        msg: result.message,
		                        timeout: 1000 * 2
		                    });
		                }
		            }
		        });
			}
			
			//修改上传资料并提交修改内容并调用modUploadInfo()方法持久化信息
			function modUpload(obj) {
				var $obj = $(obj);
				var $modForm = $obj.parent().prev();
				
				//var pid = $modForm.find("#pid").val();  //此处相当于新增因此不在设置pid
				var optMethod = $modForm.find("#optMethod").val();
				var subjectId = $modForm.find("#subjectId1").val();
				var subjectName = $modForm.find("#subjectId1").find("option:selected").text();				
				$modForm.find("#subjectId").val(subjectId);
				$modForm.find("#subjectName").val(subjectName);
				
				var srcFilePath = $modForm.find('#uploadFile').filebox('getValue');   //源文件目录      //alert("srcFilePath=" + srcFilePath);
				var file = $modForm.find('input[name="uploadFile"][type="file"]').prop('files')[0];   //获取文件上传对象
				//if (subjectId == null || subjectId == "" || subjectName == null || subjectName == "") {alert('错误，请选择科目！'); return;}
				if (file == null) { alert('错误，请选择文件！'); return; } 
			 	//获取文件名称  
		      	var fileName = file.name;  
		        //获取文件类型名称(文件后缀名)，原始文件后缀名
		      	var src_file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length); 
		      	//将原始文件后缀名统一转换为小写，再进行比较
		      	var file_typename = src_file_typename.toLocaleLowerCase();
		      	
		     	//这里限定上传文件文件类型必须为(.doc|.docx|.xls|.xlsx|.ppt|.pptx|.pdf|.mp4|.flv|.swf|.zip|.rar)，如果文件类型不符，提示错误信息  
				if(subjectId == '01' || subjectId == '02') {
					if (!(file_typename == '.swf'|| file_typename == '.flv' || file_typename == '.mp4')) {
						alert("错误，该科目类型只能传视频格式的文件！");
						return;
					}
				}
		      	
		      	if(subjectId == '03' || subjectId == '04') {
					if(!(file_typename == '.zip' || file_typename == '.rar' || file_typename == '.pdf'
							|| file_typename == '.ppt' || file_typename == '.pptx' || file_typename == '.xls'
							|| file_typename == '.xlsx' || file_typename == '.doc' || file_typename == '.docx')) {
						alert("错误，该科目类型不能传视频格式的文件！");
						return;
					}
				} 
		      	
		      	if (file_typename == '.zip' || file_typename == '.rar' || file_typename == '.swf'
					|| file_typename == '.flv' || file_typename == '.mp4' || file_typename == '.pdf'
					|| file_typename == '.ppt' || file_typename == '.pptx' || file_typename == '.xls'
					|| file_typename == '.xlsx' || file_typename == '.doc' || file_typename == '.docx') {
		      		//计算文件大小  
		          	var fileSize = 0; 
		          	//如果文件大小大于1024字节X1024字节，则显示文件大小单位为MB，否则为KB  
		          	if (file.size > 1024 * 1024) {  
		          		fileSize = Math.round(file.size * 100 / (1024 * 1024)) / 100;  	
		          		if (fileSize > 100) {  
			            	alert('错误，文件超过100MB，禁止上传！'); return;  
			            }
		          		fileSize = fileSize.toString() + 'MB'; 
			        } else {  
			            fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';  
			        }  
		          	
		          	$modForm.find("#fileSize").val(fileSize);
		          	
		          	//获取form数据  
		            var formData = new FormData($modForm[0]);   //alert($("#modForm").serializeArray());
		            formData.append("subjectId", subjectId);
		            formData.append("subjectName", subjectName);
		            
		          	//调用apicontroller后台action方法，将form数据传递给后台处理。contentType必须设置为false,否则chrome和firefox不兼容  
		            $.ajax({
		                url:'zznueg/portal/studentplatform/uploadFile',  
		                type: 'POST',  
		                data: formData,  
		                async: false,  
		                cache: false,  
		                contentType: false, 
		                processData: false, 
		                beforeSend: function () {
		                	//上传过程中将按钮设置成不可用，上传成功后解锁
		                	$modForm.find("#sureBtn").attr("disabled", true); 
		                	$modForm.find("#cancelBtn").attr("disabled", true); 
		                    $.messager.progress({   //开始上传文件打开进度条(这里的进度条不现实，待优化！！！)
		                        title: '提示',
		                        text: '文件上传中，请稍后....'
		                    });
		                    
		                    var isValid = true; //$("#uploadForm").form('validate'); ==return false
			                if (!isValid) {
			                	$.messager.progress('close');
			                }
			                return isValid; 
		                },
		                success: function (returnInfo) {  //returnInfo为json对象
		                	$.messager.progress('close');  //上传完成关闭进度条
		                	$.messager.show({
		                        title: returnInfo.jsonMsg.title,
		                        msg: returnInfo.jsonMsg.message,
		                        timeout: 1000 * 2
		                    });
		                	//上传成功后将上传次数和上传路径放入隐藏标签中
		                	$modForm.find("#uploadCount").val(returnInfo.uploadCount);
		                	$modForm.find("#uploadPath").val(returnInfo.uploadPath);
		                	//上传成功后持久化信息
		                	if(returnInfo.jsonMsg.status) {
		                		//文件上传服务器成功后，调用持久化方法持久化修改信息
		                		modUploadInfo();
		                		//上传成功后将按钮解锁(两种写法)  //$modForm.find("#sureBtn").attr("disabled", false); 
		                		$modForm.find("#sureBtn").removeAttr("disabled"); 			                	
			                	$modForm.find("#cancelBtn").removeAttr("disabled"); 
		                	} else {
		                		$.messager.show({
			                        title: returnInfo.jsonMsg.title,
			                        msg: returnInfo.jsonMsg.message,
			                        timeout: 1000 * 2
			                    });
		                	}		                	
		                	
		                    //上传成功后将控件内容清空，并显示上传成功信息  
		                    $modForm.find('#uploadFile').filebox('setValue','');
		                }, 
		                error: function (returnInfo) { 
		                	$.messager.progress('close');
		                	$.messager.show({
		                        title: returnInfo.jsonMsg.title,
		                        msg: returnInfo.jsonMsg.message,
		                        timeout: 1000 * 2
		                    });
		                }  
		            });
		          	
				} else {
					alert("文件类型不被允许！");  
				}
			}
			
			//视频预览
			function showPreview4Video(clkObj) {
				//alert("double click me to show video");
				var $clkObj = $(clkObj);
				//var uploadPid = $clkObj.find("#uploadPid").val();
				//var evalScore = $clkObj.find("#evalScore").val();
				var uploadPath = $clkObj.find("#uploadPath").val();				
				var stuUserId = $clkObj.find("#stuUserId").val();
				//由于上传路径uploadPath中含有特殊字符因此对整个URL使用encodeURI进行重新编码，或者使用escape或encodeURIComponent对每个参数单独重新编码
				window.location.href = encodeURI("<%=basePath%>zznueg/portal/studentplatform/videoPlay4Preview?uploadPath=" + uploadPath + "&stuUserId=" + stuUserId);
			}
			//PDF文档预览
			function showPreview4Pdf(clkObj) {
				//alert("double click me to show pdf");
				var $clkObj = $(clkObj); //alert($clkObj.attr("id"));
				var uploadPath = $clkObj.find("#uploadPath").val();
				var stuUserId = $clkObj.find("#stuUserId").val();
				//由于上传路径uploadPath中含有特殊字符因此对整个URL使用encodeURI进行重新编码，或者使用escape或encodeURIComponent对每个参数单独重新编码
				window.location.href = encodeURI("<%=basePath%>zznueg/portal/studentplatform/pdfViewer4Preview?uploadPath=" + uploadPath + "&stuUserId=" + stuUserId);								
			}
		</script>
	</head>

	<body>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="javascript:void(0);">学生平台</a></li>
				<li><a href="javascript:void(0);">学生成绩</a></li>
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
			<div class="rightxinxi clear" id="stuInfoDiv"></div>			
			<div id="stuScoresDiv"></div>			
		</div>

		<!-- 弹出修改框（这里的修改相当于新增） -->
		<div class="tip">
			<form id="modForm" method="post" enctype="multipart/form-data">
				<!-- <input name="pid" id="pid" type="hidden" /> -->
				<input name="optMethod" id="optMethod" value="add" type="hidden" />
				<input name="uploadCount" id="uploadCount" type="hidden" />
				<input name="uploadPath" id="uploadPath" type="hidden" />
				<div class="formbody">	
					<div class="formtitle"><span>修改信息</span></div>
					<ul class="forminfo">
						<li><label>科目</label>
                            <select name="subjectId1" id="subjectId1" class="dfinput" disabled="true">
								<!-- <option value="01">微格</option>
								<option value="02">板书</option>
								<option value="03">教案</option>
								<option value="04">课件</option> -->
							</select>
							<input name="subjectId" id="subjectId" type="hidden" />
                            <input name="subjectName" id="subjectName" type="hidden" />
						</li>
						<li><label>资源</label>
							<input id="uploadFile" name="uploadFile" data-options="prompt:'请选择资源...',buttonText:'选择'" class="dfinput easyui-filebox" style="width:345px; height:32px;"/><i></i>
							<input name="fileSize" id="fileSize" type="hidden" />	
						</li>
						
						<li><label>&nbsp;</label><!-- <input name="modUploadBtn" type="button" class="btn" value="上传"  onclick="modUpload(this)" /> -->
						</li>
					</ul>
				</div>
			</form>

			<div class="tipbtn">
				<input id="sureBtn" name="sureBtn" type="button" class="sure" value="确定" onclick="modUpload(this)" />&nbsp;
				<input id="cancelBtn" name="cancelBtn" type="button" class="cancel" value="取消" />
			</div>
		</div>

		<script type="text/javascript">
			$('.tablelist tbody tr:odd').addClass('odd');
		</script>
	</body>
</html>