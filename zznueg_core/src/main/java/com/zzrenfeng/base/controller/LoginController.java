
/**
 * Title: LoginController.java
 * Package com.zzrenfeng.base.controller
 * author zhoujincheng
 * date 2015年9月8日 下午3:37:14
 * version V1.0
 * Copyright (c) 2015,zhoujincheng777@qq.com All Rights Reserved.
 */


package com.zzrenfeng.base.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.code.kaptcha.Constants;
import com.zzrenfeng.base.entity.User;
import com.zzrenfeng.base.model.MenuModel;
import com.zzrenfeng.base.service.PermissionService;
import com.zzrenfeng.base.service.UserService;
import com.zzrenfeng.base.utils.IpUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.security.Md5Utils;

/**
 * author zhoujincheng
 * ClassName: LoginController
 * Description: 登录处理
 * date 2015年9月8日 下午3:37:14
 */
@Controller
public class LoginController {
    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;

    /**
     * param request
     * param return 参数
     * return String 返回类型
     * throws
     * Title: login
     * Description: 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
    	
		String rootURL = InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
		String resultPageURL = new String(rootURL);
		String account = request.getParameter("account");
		// MD5加密
		// 数据库中密码数据为98a6a31a7801d0418606e2d8cbc9d4a0，输入密码为system
		String password = request.getParameter("password");
		// 获取HttpSession中的验证码
		String verifyCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		// 获取用户请求表单中输入的验证码
		String submitCode = WebUtils.getCleanParam(request, "verifyCode");
		LOGGER.debug("用户账号[" + account + "]登录时输入的验证码为[" + submitCode + "],HttpSession中的验证码为[" + verifyCode + "]");
		if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(verifyCode, submitCode.toLowerCase())) {
			request.getSession().setAttribute("message_login", "验证码不正确"); // 将内容放于session中，request的生命周期仅在一次请求中生效，return后重新访问就无法获取原来的request属性内容了
			return rootURL;
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		LOGGER.debug("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		// 获取当前的Subject
		Subject subject = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			LOGGER.debug("对用户[" + account + "]进行登录验证..验证开始");			
			if (null != subject) {	//若已经存在登录验证通过的用户，先登出再进行令牌验证
				subject.logout(); 
				if (!subject.isAuthenticated()) {
					token.setRememberMe(true);
					subject.login(token);
				}
			}					
			LOGGER.debug("对用户[" + account + "]进行登录验证..验证通过");			
			
		} catch (UnknownAccountException uae) {
			LOGGER.debug("对用户[" + account + "]进行登录验证..验证未通过,未知账户");
			request.getSession().setAttribute("message_login", "未知账户");
		} catch (IncorrectCredentialsException ice) {
			LOGGER.debug("对用户[" + account + "]进行登录验证..验证未通过,错误的凭证");
			request.getSession().setAttribute("message_login", "密码不正确");
		} catch (LockedAccountException lae) {
			LOGGER.debug("对用户[" + account + "]进行登录验证..验证未通过,账户已锁定");
			request.getSession().setAttribute("message_login", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			LOGGER.debug("对用户[" + account + "]进行登录验证..验证未通过,错误次数过多");
			request.getSession().setAttribute("message_login", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			LOGGER.debug("对用户[" + account + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			request.getSession().setAttribute("message_login", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (subject.isAuthenticated()) {
			// 这里可以进行一些认证通过后的一些系统参数初始化操作
			
			/*
			 * ********验证是否为初始密码********
			 * 方案一：对用户令牌验证通过后，再进行密码是否为初始密码的验证，这样保证了用户初始令牌（用户名和初始密码）的正确性
			 * 通过后面的modifyPassword(request)方法验证，此种方案不是太理想，有安全性问题（目前使用的是该方案）
			 * 方案二：在登录成功前，通过用户输入的用户名和密码和数据库中的比对，匹配后再进行验证是否为初始密码的动作；
			 * 这样容易理解，但失去了shiro的意义
			 */
			if (!StringUtil.isEmpty(password) && password.equals(com.zzrenfeng.base.utils.Constants.DEFAULT_PASSWORD)) {
				request.getSession().setAttribute("message_login", "密码为初始密码");
				request.getSession().setAttribute("status_login", com.zzrenfeng.base.utils.Constants.STATUS_LOGIN_0101);
				LOGGER.debug("用户[" + account + "]的密码为初始密码，不能直接登录成功，需要修改");
				return rootURL;
			}
			LOGGER.debug("用户[" + account + "]登录认证通过.");
			
			// 登录成功后设置基本登录信息，如登录的时间等信息，方便前台页面显示
			String userId = com.zzrenfeng.base.utils.Constants.getCurrendUser().getUserId();
			User user = userService.selectByPrimaryKey(userId);
			if (user.getFirstLogin() == null || "".equals(user.getFirstLogin())) {
				user.setFirstLogin(new Date()); // 第一次登录时间
			}
			user.setPrevLogin(new Date()); // 由于没有记录登录日志表，因此上一次登录时间和ip，以及最后一次登录时间无法获取(暂存放当前登录的时间和IP)
			user.setPrevIp(IpUtil.getIpAddr(request));
			user.setLastLogin(new Date());
			if (user.getLoginCount() == null || "".equals(user.getLoginCount())) {
				user.setLoginCount("1"); // 首次登录成功
			} else {
				user.setLoginCount((Integer.parseInt(user.getLoginCount()) + 1) + ""); // 其余的将次数+1
			}
			user.setIsOnline(com.zzrenfeng.base.utils.Constants.USER_LOGIN_ONLINE); // 设置在线状态

			userService.updateByPrimaryKeySelective(user);

			request.getSession().setAttribute("currUser", account); // request.getSession().setAttribute("currUser", user);
			
			resultPageURL += "manage/main";
			LOGGER.debug("重定向URL：" + resultPageURL);
		} else {
			token.clear();
		}
		return resultPageURL;
    }

    /**
     * param    request
     * param return 参数
     * return String 返回类型
     * throws
     * Title: logout
     * Description: 用户登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        //重定向路径
        String result = "redirect:/manage/login";
//System.out.println("------------currUser.equals('null')>>>>" + (String.valueOf(request.getSession(false).getAttribute("currUser"))).equalsIgnoreCase("null")); 
        if(StringUtil.isEmpty(String.valueOf(request.getSession(false).getAttribute("currUser")))) {  //session不为空，但session中没有设置的参数，模拟session失效或超时时退出系统
        	return result;
        }       
    	//完全登出前获取当前用户
    	String userId = com.zzrenfeng.base.utils.Constants.getCurrendUser().getUserId();
        User user = userService.selectByPrimaryKey(userId);        
        
        SecurityUtils.getSubject().logout();       
        
        //这里可以处理登出事务，如：将用户的在线状态改为离线状态等
        user.setIsOnline(com.zzrenfeng.base.utils.Constants.USER_LOGOUT_OFFLINE);
        userService.updateByPrimaryKeySelective(user);    
       
        //false代表：不创建session对象，只是从request中获取。  
        HttpSession session = request.getSession(false); 
        if(session==null){  
            return result;  
        }
       
        /**
         * ********清除session中的属性********
		 * isNew()是指是否一个新创的session，当用户访问一个支持session的jsp网页时，session被创建，尽管有时session里面并没有任何东西。
		 * invalidate()是指清空session对象里的东西，并不指清除这个session对象本身。
		 * 所以，要判断一个session里面是否存在自己想要的东西（这个session是否有效），是不能用isNew()的， 应该用UserInfo
		 * userInfo=(UserInfo)session.getAttribute("USERINFO");if(userInfo!=null)之类的来判断。
         */
/*      // 移除session属性
        session.removeAttribute("status_login"); 
        session.removeAttribute("message_login");
        session.removeAttribute("currUser"); 
        session.removeAttribute("shiroUser"); */
        /*
         * invalidate()是指清空session对象里的东西，并不指清除这个session对象本身。
         * 将session中所有的属性置为无效并销毁；
         */
        session.invalidate();
        
        return result;
    }

    /**
     * param    response
     * param return 参数
     * return String 返回类型
     * throws
     * Title: getUsersMenu
     * Description:查询用户所有权限菜单
     */
    @ResponseBody
    @RequestMapping(value = "/getUsersMenu", produces = "application/json;charset=utf-8")
    public List<MenuModel> getUsersMenu() {
        return permissionService.createMenu();
    }
    
    /**
     * @功能描述：修改密码；有如下两种场景：
     * 			1、首次登录修改初始密码，修改成功后重定向到登录界面；
     * 			2、用户登录成功后自行修改密码，修改成功后返回当前页面；
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月21日 下午2:52:30
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    public String modifyPassword(HttpServletRequest request) {
    	String result = "";  //redirect:/manage/login
    	String password = request.getParameter("password").trim();
//System.out.println("*********************password====>>>>" + password);  
    	//第一次登录使用默认密码登录成功后再进行了验证是否为初始密码动作，因此在session中已经存在shiroUser对象，故可以按如下方式获取验证成功的用户属性
    	String userId = com.zzrenfeng.base.utils.Constants.getCurrendUser().getUserId();
        User user = userService.selectByPrimaryKey(userId);
        user.setPassword(Md5Utils.hash(password));
    
		try {
			if (userService.persistenceUser(user)) {				
				//result = "redirect:/manage/login";
				return logout(request);  //由于先前使用默认密码登录成功后，再进行了验证初始密码动作，因此重新登录需要先登出，否则session中为旧的信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return result;
    }
    

}
