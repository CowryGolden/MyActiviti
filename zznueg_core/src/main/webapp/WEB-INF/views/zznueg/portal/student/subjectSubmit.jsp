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
		<title>学生-科目提交</title>
 		<tags:include/>
 		<!-- 样式有冲突，单独引入 -->
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/style.css"/>
		
		<script type="text/javascript">
			var $dg = $("#dg");
			
			$(function(){
				//页面加载时就显示全部信息
				loadAll();
				
				$(".liebiaoTop span").click(function(){
					$(".liebiaoTop span").attr("class", "hide")
					$(this).attr("class", "show");
					var stype = $(this).data("stype");
					if(stype == ("kmfl_all")) {
						//alert("全部");
						loadAll();
					} else if(stype == ("kmfl_weige")) {
						//alert("微格");
						getUpInfoBySubId("01");
					} else if(stype == ("kmfl_banshu")) {
						//alert("板书");
						getUpInfoBySubId("02");
					} else if(stype == ("kmfl_jiaoan")) {
						//alert("教案");
						getUpInfoBySubId("03");
					} else if(stype == ("kmfl_kejian")) {
						//alert("课件");
						getUpInfoBySubId("04");
					}
				});				
				
/* 				$(".liebiaoSubmit").click(function() {
					$(".tip").fadeIn(200);
					$(".sure").click(function() {
						$(".tip").fadeOut(100);
					});
					$(".cancel").click(function() {
						$(".tip").fadeOut(100);
					});
				}); */				
				
			});
			
			/**
			 * 判断要下载的文件是否存在；下载前先判断，如不存在则不进入downloadFile方法，存在时再执行下载方法
			 */
			function doesFileExist(clkObj) {
				var filePath = $(clkObj).parent().find("input[id='uploadPath']").val();
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
			
			/**
			 * 文件下载
			 */
			function downloadFile(clkObj) {
				var filePath = $(clkObj).parent().find("input[id='uploadPath']").val();
				<%-- filePath = "<%=basePath%>" + filePath;  --%>  //filePath已经为文件系统的绝对路径，不用再添加网络访问根路径
				//var filePath = "E:\\workspace\\ZZNUEvalGraduation\\WebContent\\upload\\2017\\09\\30\\642FF77FED0846628990EFB0EB55A421_教案一_20170817.pdf";
				
				/* 超链接方式文件下载(简单实用) */
				<%-- window.location.href = encodeURI("<%=basePath%>zznueg/portal/studentplatform/downloadFile?filePath=" + filePath); --%>
				
				/* AJAX方式文件下载(需要进行模拟表单提交，比较麻烦) */
				/**
				 * 实验：ajax方式下载文件时无法触发浏览器打开保存文件对话框，也就无法将下载的文件保存到硬盘上！
				 * 原因：ajax方式请求的数据只能存放在javascipt内存空间，可以通过javascript访问，但是无法保存到硬盘，因为javascript不能直接和硬盘交互，否则将是一个安全问题。
				 * 		JQuery的ajax函数的返回类型只有xml、text、json、html等类型，没有“流”类型，所以我们要实现ajax下载，不能够使用相应的ajax函数进行文件下载。
				 *		但可以用js生成一个form，用这个form提交参数，并返回“流”类型的数据。在实现过程中，页面也没有进行刷新。
				 */
/* 				此方式不可行！！！ 返回类型不为流类型，ajax方式请求的数据只能存放在javascipt内存空间，可以通过javascript访问，但是无法保存到硬盘。数据存在在返回的data对象中
				$.ajax({
			    	url : "zznueg/portal/studentplatform/downloadFile",
			    	type : "POST",
		        	async:false,
			   		data:{"filePath": filePath},
			    	success: function(data, status, xhr) {  //文件内容在data中只能读取对象，无法保存到文件系统
						alert("data=" + data + "\nstatus=" + status + "\nxhr=" + xhr);
			    	}
				});
 */				//下面是模拟模拟表单提交同步方式下载文件；能够弹出保存文件对话框；
				var downloadURL = "zznueg/portal/studentplatform/downloadFile";
				var form = $("<form>");				//定义一个form表单
				form.attr("style", "display:none");	//在form表单中添加查询参数
				form.attr("id", "downloadForm");
				form.attr("target", "");
				form.attr("method", "post");
				//form.attr("onsubmit", "javascript:return alert('下载成功！');");  //onsubmit是在提交表单前调用
				form.attr("action", downloadURL);			       

				var input = $("<input>");  
				input.attr("type","hidden");  
				input.attr("name","filePath"); 
				input.attr("value",filePath);			       

				$("body").append(form);				//将表单放置在web中
				form.append(input);					//将查询参数控件提交到表单上
				form.submit();						//表单提交 
				
				/** 验证文件是否下载成功  */  
				/* $("#downloadForm").ajaxForm(function(data){					
					alert("下载成功！");
				}); */
			}
			
			//修改
			function modClick(clkObj) {
				var clickId = $(clkObj).attr("id");		//获取点击对象的ID值，即列表行的pid，将该内容塞到隐藏标签中
				$("#modForm").find("input[name=pid]").val(clickId);  //当不同表单中有同名标签时用此方法；若唯一，用下面方法
				//$("#pid").val(clickId);
				
				//弹出修改对话框
				$(".tip").fadeIn(200);
				
				var oldSubjectId = $(clkObj).parent().find("input[id='subjectId']").val();
				//$("#subjectId1").
				/* $("#subjectId1").combobox({
					valueField: 'subjectId',   
        			textField: 'subjectName',
            		url:'zznueg/manage/subjectinfo/findAllSubjects',
            		method:'get',
            		success: function(key,value) {
   			        	alert(value);
   			        },
   			        
				}); */  //待优化，easyui-combobox在弹出界面无法正常显示
				$.ajax({
					url:'zznueg/manage/subjectinfo/findAllSubjects',
					method:'get',
					dataType: "json",
					success: function(data) {
   			        	//alert(data.toString());
   			        	$.each(data, function(key, val) {
   			        		if(val.subjectId == oldSubjectId) {
   			        			$("#modForm").find("#subjectId").append('<option value="' + val.subjectId + '" selected>' + val.subjectName + '</option>');
   			        		} else {
   			        			$("#modForm").find("#subjectId").append('<option value="' + val.subjectId + '">' + val.subjectName + '</option>');
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
			
			function loadAll() {
				$.ajax({
			         url : "zznueg/portal/studentplatform/getAllUploadInfoByStuUid",
			         type : "POST",
			         async:false,
			         dataType : "html",
			         data:{"page": "1", "rows": "20"},
			         success: function(data) {
			        	 $("#dg").html($(data).html());
			         }
			     });
			}
			
			function getUpInfoBySubId(subjectId) {
				$.ajax({
			         url : "zznueg/portal/studentplatform/getUpInfoBySubId",
			         type : "POST",
			         async:false,
			         dataType : "html",
			         data:{"subjectId": subjectId},
			         success: function(data) {
			        	 $("#dg").html($(data).html());
			         }
			     });
			}
			
		
			/**
			 * 将上传文件和保存上传信息到数据库两过程分离；分别放在两个方法里面，上传成功后再持久化信息
			 */
			//上传文件
			function uploadFile() {
				var subjectId = $('#subjectId').combobox('getValue'); 
				var subjectName = $('#subjectId').combobox('getText');
				var srcFilePath = $('#uploadFile').filebox('getValue');   //源文件目录      //alert("srcFilePath=" + srcFilePath);
				var file = $('input[name="uploadFile"][type="file"]').prop('files')[0];   //获取文件上传对象
				if (subjectId == null || subjectId == "" || subjectName == null || subjectName == "") {alert('错误，请选择科目！'); return;}
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
		          	//将文件名和文件大小显示在前端label文本中  
		            //document.getElementById('fileName').innerHTML = "<span style='color:Blue'>文件名: " + file.name + ',大小: ' + fileSize + "</span>";
		          	//$('#fileName').html("<span style='color:Blue'>文件名: " + file.name + ',大小: ' + fileSize + "</span>");
		          	//将科目名称和文件大小设置在隐藏标签中
		          	$("#subjectName").val(subjectName);
		          	$("#fileSize").val(fileSize);
		          	
		          	//获取form数据  
		            var formData = new FormData($("#uploadForm")[0]); 
		          	//var formData = $("#uploadForm").serialize();     //alert("formData=" + formData);
		            
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
		                	//上传过程中将按钮(a标签的onclick属性)设置成不可用，上传成功后解锁
		                	$("#doUploadFileBtn").removeAttr('onclick');
		                	
		                    $.messager.progress({   //开始上传文件打开进度条(这里的进度条不显示，待优化！！！)
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
		                	//alert("上传成功！result=" + JSON.stringify(returnInfo));
		                	//alert(returnInfo.jsonMsg.status);
		                	//alert(returnInfo.jsonMsg.message);
		                	$.messager.show({
		                        title: returnInfo.jsonMsg.title,
		                        msg: returnInfo.jsonMsg.message,
		                        timeout: 1000 * 2
		                    });
		                	//上传成功后将上传次数、上传路径和在线预览路径放入隐藏标签中
		                	$("#uploadCount").val(returnInfo.uploadCount);
		                	$("#uploadPath").val(returnInfo.uploadPath);
		                	$("#previewPath").val(returnInfo.previewPath);
		                	//alert("uploadCount=" + $("#uploadCount").val() + "\n uploadPath=" + $("#uploadPath").val());
		                	//上传成功后持久化信息
		                	if(returnInfo.jsonMsg.status) {
		                		saveUploadInfo();
		                		//上传动作完成后将按钮解锁(将a标签的onclick属性重置回来)
		                		$("#doUploadFileBtn").attr("onclick","uploadFile();");
		                	} else {
		                		$.messager.show({
			                        title: returnInfo.jsonMsg.title,
			                        msg: returnInfo.jsonMsg.message,
			                        timeout: 1000 * 2
			                    });
		                	}		                	
		                	
		                    //上传成功后将控件内容清空，并显示上传成功信息  
		                    $('#subjectId').combobox('setValue', '');
		                    $('#subjectId').combobox('setText', '');
		                    $('#uploadFile').filebox('setValue','');
		                    //$('#uploadInfo').html("<span style='color:Red'>" + returnInfo + "</span>");  
		                }, 
		                error: function (returnInfo) { 
		                	$.messager.progress('close');
		                	//alert("上传失败！result=" + returnInfo.toString());
		                    //上传失败时显示上传失败信息  
		                    //$('#uploadInfo').html("<span style='color:Red'>" + returnInfo + "</span>");  
		                	$.messager.show({
		                        title: returnInfo.jsonMsg.title,
		                        msg: returnInfo.jsonMsg.message,
		                        timeout: 1000 * 2
		                    });
		                }  
		            });
		          	
				} else {
					alert("文件类型不被允许！");  
		            //将错误信息显示在前端label文本中  
		            //document.getElementById('fileName').innerHTML = "<span style='color:Red'>错误提示:上传文件应该是.xlsx后缀而不应该是" + file_typename + ",请重新选择文件</span>"
				}
			}
					
			
			//上传成功后调用此方法持久化信息，这里uploadPath指的是上传成功后的文件路径+文件名；新增previewPath-在线预览路径
			function saveUploadInfo() {
				$("#uploadForm").form('submit',{
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
		                    //注意这里的datagrid，要和主页面的保持一致
		                    //$.modalDialog.openner.datagrid('reload');
		                    //$.modalDialog.handler.dialog('close');
		                    loadAll();
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
		                    loadAll();
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
				//var $modForm = $obj.parent().parent().parent().parent();   //上传按钮所在位置的写法，换为确定按钮后的写法如下
				var $modForm = $obj.parent().prev();
				//alert($modForm.find('input[name="modUploadFile"][type="file"]').prop('files')[0]);
				//alert($modForm[0]);
				
				var pid = $modForm.find("#pid").val();
				var optMethod = $modForm.find("#optMethod").val();
				var subjectId = $modForm.find("#subjectId").val();
				var subjectName = $modForm.find("#subjectId").find("option:selected").text();
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
		            //alert(formData.get("pid") + "=****=" + formData.get("subjectId"));
		            //alert(subjectId + "-" + subjectName + "-" + fileName);
		            
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
		                	//$("#uploadCount").val(returnInfo.uploadCount);  //修改不计次数
		                	//$("#uploadPath").val(returnInfo.uploadPath);
		                	$modForm.find("#uploadPath").val(returnInfo.uploadPath);
		                	$modForm.find("#previewPath").val(returnInfo.previewPath);  //新增在线预览路径
		                	//上传成功后持久化信息
		                	if(returnInfo.jsonMsg.status) {
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
		                    //$('#subjectId').combobox('setValue', '');
		                    //$('#subjectId').combobox('setText', '');
		                    $modForm.find('#uploadFile').filebox('setValue','');
		                }, 
		                error: function (returnInfo) { 
		                	//alert("失败了！！！")
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
		</script>
		
	</head>

	<body class="easyui-layout">

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="javascript:void(0)">学生平台</a></li>
				<li><a href="javascript:void(0)">科目提交</a></li>
			</ul>
		</div>

		<div class="rightinfo">
			<p class="rightP">
				<span>科目提交</span>
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
			<form id="uploadForm" method="post"  enctype="multipart/form-data">
				<input name="optMethod" id="optMethod" value="add" type="hidden" />
				<input name="uploadCount" id="uploadCount" type="hidden" />
				<input name="uploadPath" id="uploadPath" type="hidden" />
				<input name="previewPath" id="previewPath" type="hidden" />
				<div class="rightxinxi clear" style="background-color: #fff;">
					<span class="rightSpan">添加科目：</span>
					<span class="rightSpan">
						<!-- <select name="" style="border: 1px solid gainsboro;">
							<option value="01">微格</option>
							<option value="02">板书</option>
							<option value="03">教案</option>
							<option value="04">课件</option>
						</select> -->
						<input name="subjectId" id="subjectId" class="easyui-combobox easyui-validatebox"
                        	data-options="
                        		prompt:'--请选择科目--',
                        		valueField: 'subjectId',   
                    			textField: 'subjectName',
                        		url:'zznueg/manage/subjectinfo/findAllSubjects',
                        		method:'get',
                        		required:true
                            " />
						<input name="subjectName" id="subjectName" type="hidden" />
					</span>
					<span class="shang" style="width:200px;height:25px;">						
						<input class="easyui-filebox" id="uploadFile" name="uploadFile" data-options="prompt:'请选择资源...',buttonText:'选择'" style="width:200px;height:25px;" />
						<!-- <input class="next" type="button" id="uploadBtn" name="uploadBtn" value="上传"  onclick="#"/> -->	
						<input name="fileSize" id="fileSize" type="hidden" />		
					</span>
					<span class="next" style="width:100px;height:25px;">
						<c:choose>
							<c:when test="${gtFlag == '1'}">
								<a onclick="javascript:alert('不在考试期间，不允许上传！');" id="doUploadFileBtn"  class="easyui-linkbutton" style="width:100%;cursor:pointer;">上传</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" onclick="uploadFile()" id="doUploadFileBtn"  class="easyui-linkbutton" style="width:100%;cursor:pointer;">上传</a>  
							</c:otherwise>
						</c:choose>
					</span>
					
					<label id="fileName"></label>
					<label id="uploadInfo"></label>
				</div>
			</form>
			<div class="liebiao">
				<form id="searchForm" method="post">
					<div class="liebiaoTop">
						科目类别：
						<span class="show" name="all" data-stype='kmfl_all'>全部</span>
						<span class="hide" name="weige" data-stype='kmfl_weige'>微格</span>
						<span class="hide" name="banshu" data-stype='kmfl_banshu'>板书</span>
						<span class="hide" name="jiaoan" data-stype='kmfl_jiaoan'>教案</span>
						<span class="hide" name="kejian" data-stype='kmfl_kejian'>课件</span>
					</div>					
				</form>	
				<div id="dg" title="科目信息">
					
				</div>
			</div>
		</div>
		
		
		<div class="tip">
			<form id="modForm" method="post" enctype="multipart/form-data">
				<input name="pid" id="pid" type="hidden" />
				<input name="optMethod" id="optMethod" value="mod" type="hidden" />
				<!-- <input name="uploadCount" id="uploadCount" type="hidden" /> -->
				<input name="uploadPath" id="uploadPath" type="hidden" />
				<input name="previewPath" id="previewPath" type="hidden" />
				<div class="formbody">	
					<div class="formtitle"><span>修改信息</span></div>
					<ul class="forminfo">
						<li><label>科目</label>							
<!-- 							<input name="subjectId1" id="subjectId1" class="dfinput easyui-combobox easyui-validatebox" style="width:345px; height:32px;"
								data-options="" /><i></i> -->   <!-- 待优化，easyui-combobox在弹出界面无法正常显示 -->
                            
                            <select name="subjectId" id="subjectId" class="dfinput" disabled="true">
								<!-- <option value="01">微格</option>
								<option value="02">板书</option>
								<option value="03">教案</option>
								<option value="04">课件</option> -->
							</select>
                            <!-- <input name="subjectName" id="subjectName" type="hidden" /> -->
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
			<%-- $('.tablelist tbody tr:odd').addClass('odd');
			$(".liebiaoTop span").click(function() {
				$(".liebiaoTop span").attr("class", "hide")
				$(this).attr("class", "show");
			})  --%>
		</script>
		
	</body>

</html>
