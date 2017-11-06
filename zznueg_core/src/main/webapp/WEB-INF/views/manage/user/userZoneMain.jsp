<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="UTF-8">
    <title>用户中心——修改密码</title>
    <tags:include/>
    <script type="text/javascript">
    	var passwordNew1,passwordNew2; 
    	var chkOldPwdFlag = false;
    	var chkNewPwd1Flag = false;
        $(function () {
        	$("#passwordOld").focus();
        	checkOldPwd();
        	checkPassword1();
        	checkPassword2();
        });
        
        function modifyPwd() {
        	if(checkPasswords()) {
        		$.ajax({
					url : "manage/users/modifyPassword",
					type : "POST",
					data:{"password" : passwordNew1},
					success: function(data) {
						if(data.status) {
							reset();  //提交成功重置表单域							
						} 
						$.messager.show({
                            title: data.title,
                            msg: data.message,
                            timeout: 1000 * 2
                        });
					},
					error: function () {
                        $.messager.show({
                            title: "提示",
                            msg: "提交出错了！",
                            timeout: 1000 * 2
                        });
                    }
				});
        	} else {
        		alert("验证失败，请检验输入");
        		$("#passwordNew1").focus();
        	}
        }
        
        function checkPasswords() {
        	if(checkOldPwd() && checkPassword1() && checkPassword2()) {
        		return true;
        	} else {
        		return false;
        	}
        }
        
        //校验原密码
        function checkOldPwd() { 
        	$("#passwordOld").focusout(function () {        		
				var oldPassword = $("#passwordOld").val();
				if(oldPassword != null && oldPassword.length > 0) {
					$("#tipInfo").removeAttr("style");
	            	$("#tipInfo").html("");
	            	$.ajax({
						url : "manage/users/checkOldPassword",
						type : "POST",
						data:{"oldPassword" : oldPassword},
						success: function(data) {
							chkOldPwdFlag = data.status;
							if(data.status) {
								$("#tipInfo").removeAttr("style");
								$("#tipInfo").html(data.message);
								return true;
							} else {
								$("#passwordOld").focus();
								$("#tipInfo").attr("style","color: red;");
								$("#tipInfo").html(data.message);
								return false;
							}							
						}
	            	});
				} else {
					$("#passwordOld").focus();
					$("#tipInfo").attr("style","color: red;");
					$("#tipInfo").html("请输入原密码！");
					chkOldPwdFlag = false;
					return false;
				}				
			});
        	return chkOldPwdFlag;
        }
        
      	//校验密码1的输入
        function checkPassword1() {
        	passwordNew1 = $("#passwordNew1").val();
        	passwordNew2 = $("#passwordNew2").val();
        	var reg=/^[a-zA-Z]{1}([a-zA-Z0-9]|[*_]){5,30}$/;
        	$("#passwordNew1").change(function() {
            	$("#tipInfo1").removeAttr("style");
            	$("#tipInfo1").html("以字母开头，包含字母、数字和下划线，6-30个字符");
            	passwordNew1 = $("#passwordNew1").val();
            	passwordNew2 = $("#passwordNew2").val();            	
				if(passwordNew1.length < 6 || passwordNew1.length > 30) {
					$("#passwordNew1").focus();
					$("#tipInfo1").attr("style","color: red;");
					$("#tipInfo1").html("请输入6-30个字符！");
					return false;
				}				
				if(!reg.test(passwordNew1)) {
					$("#passwordNew1").focus();
					$("#tipInfo1").attr("style","color: red;");
					$("#tipInfo1").html("密码必须以字母开头，包含字母、数字和下划线！");
			        return false;
			    }
				//验证是否为默认密码：ture-不是默认密码；false-是默认密码
				$.ajax({
					url : "manage/users/isNotDefaultPwd",
					type : "POST",
					data:{"newPassword" : passwordNew1},
					success: function(data) {
						chkNewPwd1Flag = data.status;
						if(data.status) {
							$("#tipInfo1").removeAttr("style");
							$("#tipInfo1").html(data.message);
							return true;
						} else {
							$("#passwordNew1").focus();
							$("#tipInfo1").attr("style","color: red;");
							$("#tipInfo1").html(data.message);
							return false;
						}							
					}
            	});
				if(passwordNew2 != null && passwordNew2 != "") {  //防止密码1与密码2验证通过后修改了密码1
					if(passwordNew1 != passwordNew2) {
						$("#passwordNew1").focus();
						$("#tipInfo2").attr("style","color: red;");
						$("#tipInfo2").html("两次输入密码不一致！");
						return false;
					} else {
						$("#tipInfo2").removeAttr("style");
		            	$("#tipInfo2").html("");
					}					
				}
			});
        	
        	if(passwordNew1.length > 5 && passwordNew1.length < 31) {
        		if(reg.test(passwordNew1)) {
        			if(chkNewPwd1Flag) {
        				return true;
        			}        			
        		}
        	}
        	$("#passwordNew1").focus();
        	return false;
        }
       
		//校验密码2的输入
        function checkPassword2() {
        	passwordNew2 = $("#passwordNew2").val();
        	$("#passwordNew2").change(function() {             	
            	$("#tipInfo2").removeAttr("style");
            	$("#tipInfo2").html("");
            	passwordNew2 = $("#passwordNew2").val();
				if(passwordNew1 != passwordNew2) {
					$("#passwordNew2").focus();
					$("#tipInfo2").attr("style","color: red;");
					$("#tipInfo2").html("两次输入密码不一致！");
					return false;
				}
			});
        	if(passwordNew1 == passwordNew2) {
        		return true;
        	}
        	$("#passwordNew2").focus();
        	return false;
        }
        
        //重置所有输入域和表单域
        function reset() {
        	$("#tipInfo").removeAttr("style");
        	$("#tipInfo1").removeAttr("style");
        	$("#tipInfo2").removeAttr("style");
        	$("#tipInfo").html("");
        	$("#tipInfo1").html("以字母开头，包含字母、数字和下划线，6-30个字符");
        	$("#tipInfo2").html("");
        	$("input[type='password']").val("");
        	//$("#form")[0].reset();        	
        }
    </script>
</head>
<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li><a href="javascript:void(0);">系统管理</a></li>
		<li><a href="javascript:void(0);">用户中心</a></li>
	</ul>
</div>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
    <form id="form" method="post">
    	<fieldset>
    		<legend><img src="resources/core/images/fromedit.png" style="margin-bottom: -3px;"/> 密码修改</legend>
    		<input name="userId" id="userId" type="hidden"/>
            <input name="status" id="status" type="hidden"/>
            <table>
            	<tr style="height: 35px;">
            		<th>原&nbsp;&nbsp;密&nbsp;码：</th>
            		<td>
            			<input id="passwordOld" name="passwordOld" class="easyui-validatebox" style="height: 30px;"
                        	value="" type="password" required="required" placeholder="请输入原始密码" />
            		</td>
            		<td>
            			<i id="tipInfo"></i>
            		</td>
            	</tr>
            	<tr style="height: 35px;">
            		<th>新&nbsp;&nbsp;密&nbsp;码：</th>
            		<td>
            			<input id="passwordNew1" name="passwordNew1" class="easyui-validatebox" style="height: 30px;"
                        	value="" type="password" required="required" placeholder="请输入新密码"/>
            		</td>
            		<td>
            			<i id="tipInfo1">以字母开头，包含字母、数字和下划线，6-30个字符</i>
            		</td>
            	</tr>
            	<tr style="height: 35px;">
            		<th>确认密码：</th>
            		<td>
            			<input id="passwordNew2" name="passwordNew2" class="easyui-validatebox" style="height: 30px;"
                        	value="" type="password" required="required" placeholder="请再次输入新密码"/>
            		</td>
            		<td>
            			<i id="tipInfo2"></i>
            		</td>
            	</tr>
            	<tr>
            		<td>
            			<a href="javascript:void(0);" onclick="modifyPwd()" class="easyui-linkbutton" iconCls="icon-yes">确定</a>
            		</td>
            		<td>
            			<a href="javascript:void(0);" onclick="reset()" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
            		</td>
            	</tr>
            </table>    
    	</fieldset>
    </form>

    <div class="tip">
        <div class="tiptop">
            <span>提示信息</span><a></a>
        </div>
        <div class="tipinfo">
            <span><img src="resources/core/images/ticon.png"/></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>
        <div class="tipbtn">
            <input name="" type="button" class="sure" value="确定"/>&nbsp; <input
                name="" type="button" class="cancel" value="取消"/>
        </div>
    </div>
</div>
</body>
</html>