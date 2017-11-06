<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String message_login = String.valueOf(request.getSession().getAttribute("message_login"));
    String status_login = String.valueOf(request.getSession().getAttribute("status_login"));
System.out.println("message_login====>>>>" + message_login);     
System.out.println("status_login====>>>>" + status_login);    
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8">
    <title>欢迎登录郑州师范学院评估系统</title>
    <link href="resources/core/css/style.css" rel="stylesheet" type="text/css"/>    
    <script src="resources/public/js/jquery.js" type="text/javascript"></script>
    <script src="resources/core/js/cloud.js" type="text/javascript"></script> <!-- 实现登录页面云朵飘动效果；该方法基于jQuery，置于jquery.js引入之后即可 -->
    
    <style>
        body {
            background: #1c77ac url(resources/core/images/light.png) no-repeat center top;
            overflow: hidden;
        }
    </style>

    <script type="text/javascript">
	    var passwordNew1,passwordNew2; 
		var loginStatus = "<%=status_login%>";
        $(function () {
            $('.loginbox').css({
                'position': 'absolute',
                'left': ($(window).width() - 692) / 2
            });

            $(window).resize(function () {
                $('.loginbox').css({
                    'position': 'absolute',
                    'left': ($(window).width() - 692) / 2
                });
            });

            $('#kaptchaImage').click(function() {//生成验证码
                $(this).hide().attr('src', 'captcha?' + Math.floor(Math.random() * 100)).fadeIn();
            });
            
            //加载时清空form所有域，然而下面的方法对chrome并无效，使用autocomplete="off"对新版的chrome也无效，
            //需要在非填充域加入display:none的标签，以便来填充此标签，具体参考：line-210,line-214；注意id和name一致性
            $("#form")[0].reset();
            //$("input[type='text']").val("");
            //$("input[type='password']").val("");
            
            $("input[name='verifyCode']").click(function() {
            	$("#msgField").html("");
            });            
            
            //首次登录，密码为初始密码，弹出修改对话框
            if(loginStatus == "0101") {  //密码为初始密码的登录状态码
            	showModPwdDiag();
            }
            //校验密码1和密码2的输入
			checkPassword1();
			checkPassword2();
			
  			//使用ajax方式提交，使用submit方式交互性不强
			$(".sure").click(function() {
				if(checkPassword()) {
					$.ajax({
						url : "modifyPassword",
						type : "POST",
						data:{"password" : passwordNew1},
						success: function(data) {
							alert("密码修改成功，请重新登录");
							<%	//密码修改成功后就将session中的status_login属性置为初始值
								//request.getSession().setAttribute("status_login", null); 
								request.getSession().removeAttribute("status_login");
							%>
							$(".modifyTip").fadeOut(100);
							$("#msgField").html("");
						}
					});
				} else {
					$("#passwordNew1").focus();
				}
									
			});
 
			$(".cancel").click(function() {
				$("#passwordNew1").val("");
	        	$("#passwordNew2").val("");
	        	clearTips("tipInfo1","以字母开头，包含字母、数字和下划线，6-30个字符");
	        	clearTips("tipInfo2","");
				$(".modifyTip").fadeOut(100);
				
				<%	//重设session中的status_login属性，设为初始状态
					request.getSession().setAttribute("status_login", null);
				%>
								
				window.location.href = "redirect:<%=basePath%>";
				$("#msgField").html("");
			});
            
        });
        
        //密码输入验证
        function checkPassword() {
        	if(checkPassword1() && checkPassword2()) {
        		//alert("校验成功，提交后台处理！");
        		return true;
        	} else { 
        		//alert("校验失败，不允许提交！");
        		return false;
        	}
        }        
        //校验密码1的输入
        function checkPassword1() {
        	passwordNew1 = $("#passwordNew1").val();
        	passwordNew2 = $("#passwordNew2").val();
        	var reg=/^[a-zA-Z]{1}([a-zA-Z0-9]|[*_]){5,30}$/;
        	$("#passwordNew1").change(function() {
        		clearTips("tipInfo1","以字母开头，包含字母、数字和下划线，6-30个字符");
            	passwordNew1 = $("#passwordNew1").val();
            	passwordNew2 = $("#passwordNew2").val();            	
				if(passwordNew1.length < 6 || passwordNew1.length > 30) {
					showTips("tipInfo1", "请输入6-30个字符！");
					return false;
				}
				if(!reg.test(passwordNew1)) {
					showTips("tipInfo1", "密码必须以字母开头，包含字母、数字和下划线！");
			        return false;
			    }
				if(passwordNew2 != null && passwordNew2 != "") {  //防止密码1与密码2验证通过后修改了密码1
					if(passwordNew1 != passwordNew2) {
						showTips("tipInfo2", "两次输入密码不一致！");
						return false;
					} else {
						clearTips("tipInfo2","");
					}					
				}
			});
        	
        	if(passwordNew1.length > 5 && passwordNew1.length < 31) {
        		if(reg.test(passwordNew1)) {
        			return true;
        		}
        	}
        	return false;
        }
        
      	//校验密码2的输入
        function checkPassword2() {
        	passwordNew2 = $("#passwordNew2").val();
        	$("#passwordNew2").change(function() {             	
        		clearTips("tipInfo2", "");
            	passwordNew2 = $("#passwordNew2").val();
				if(passwordNew1 != passwordNew2) {
					showTips("tipInfo2", "两次输入密码不一致！");
					return false;
				}
			});
        	if(passwordNew1 == passwordNew2) {
        		return true;
        	}
        	return false;
        }
        
      	//显示修改密码对话框
        function showModPwdDiag() {
        	$("#passwordNew1").val("");
        	$("#passwordNew2").val("");
        	clearTips("tipInfo1", "以字母开头，包含字母、数字和下划线，6-30个字符");
        	clearTips("tipInfo2");
			$(".modifyTip").fadeIn(200);
        }
      	
      	//显示提示；divId-要显示的divId，content-显示内容
      	function showTips(divId, content) {
      		$("#" + divId).attr("style","color: red;");
			$("#" + divId).html(content);
      	}
      	//清除提示；divId-要清除的divId，content-显示内容
      	function clearTips(divId, content) {
      		$("#" + divId).removeAttr("style");
			$("#" + divId).html(content);
      	}
        
    </script>
</head>
<body>
<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>
<div class="logintop">
    <span>欢迎登录郑州师范学院评估系统</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
		<!-- <li class="click">修改密码</li> -->
    </ul>
</div>
<form id="form" action="login" method="POST" autocomplete="off">
    <div class="loginbody">
        <span class="systemlogo"></span>
        <div class="loginbox loginbox2">
            <ul>
                <li>
                	<!-- <input style="display:none" id="account"/> --><!-- fake fields are a workaround for chrome autofill getting the wrong fields,fake username remembered -->
                	<input required="required" name="account" type="text" class="loginuser" value="" placeholder="请输入用户名" />
                </li>
                <li>
                	<!-- <input style="display:none" id="password"/> --><!-- fake fields are a workaround for chrome autofill getting the wrong fields,fake password remembered -->
                	<input required="required" name="password" type="password" class="loginpwd" value="" placeholder="请输入密码" onfocus="javaScript:this.value='';" />
                </li>
                <li class="yzm">
                	<span><input required="required" name="verifyCode" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span>
                    <cite><img src="captcha" width="114" height="46" id="kaptchaImage" style="margin-bottom: -3px"/></cite>
                    <p id="msgField" style="font-size: 9px;color: red;text-align: right;">${message_login}</p>
                </li>
                <li>
	                <input type="submit" class="loginbtn" value="登录"/>
	                <label><input name="remeberPwd" type="checkbox" value="yes" checked="checked"/>记住密码</label>
	                <label><a href="javascript:alert('请联系管理员！');">忘记密码？</a></label>
                </li>
            </ul>
        </div>
    </div>
</form>

<!-- 首次登陆修改密码弹出框 -->
<div class="modifyTip">
	<form id="modForm" method="post" autocomplete="off">
		<!-- <input name="optMethod" id="optMethod" value="add" type="hidden" /> -->
		<div class="formbody">	
			<div class="formtitle"><span>修改密码</span></div>
			<ul class="forminfo">
				<li><span style="font-size: 12px;font-style: italic;font-weight: bold;color: #F99331;margin-bottom: 40px;">温馨提示：密码不能为初始密码，请修改！</span></li>
				<li><label>新  密  码</label>
                    <input id="passwordNew1" name="passwordNew1" required="required" type="password" class="dfinput" autocomplete="off" /><i id="tipInfo1">以字母开头，包含字母、数字和下划线，不超过30位</i>
				</li>
				<li><label>确认密码</label>
                    <input id="passwordNew2" name="passwordNew2" required="required" type="password" class="dfinput" autocomplete="off" /><i id="tipInfo2"></i>
				</li>
			</ul>
		</div>
		<div class="tipbtn">
			<input id="sureBtn" name="sureBtn" type="button" class="sure" value="确定" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="cancelBtn" name="cancelBtn" type="button" class="cancel" value="取消" />
		</div>
	</form>	
</div>


<!-- <div class="loginbm">
	Copyright © 2015 www.4Tiro.com belongs to Zhoujincheng. All Rights Reserved.
</div> -->
<div class="loginbm">
	CopyRight 2012-2017 All Right Reserved    仁峰软件开发有限公司版权所有    豫ICP备05012254号豫ICP备14000520号
</div>
</body>
</html>
