package com.zzrenfeng.base.shiro;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzrenfeng.base.entity.Permission;
import com.zzrenfeng.base.entity.User;
import com.zzrenfeng.base.service.PermissionService;
import com.zzrenfeng.base.service.UserService;
import com.zzrenfeng.base.utils.DateUtil;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 
 * ClassName: LoginRealm
 * Description: 自定义的指定Shiro验证用户登录的类
 * author zhoujincheng
 * date 2015年9月8日 下午12:01:00
 *
 */
public class LoginRealm extends AuthorizingRealm {

	private final Logger LOGGER = LoggerFactory.getLogger(LoginRealm.class);

	private static final String ALGORITHM = "MD5";

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	public LoginRealm() {
		super();
	}

	/**
	 * 验证当前登录的Subject 本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 * 
	 * zjc modify:
	 * date:20170816 15:07
	 * desc:line 71 start;修改登录权限用户的属性，增加前端所需要的字段，以便获取
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {

		// 这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		LOGGER.debug("验证当前Subject时获取到token为"
				+ ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));

		User users = userService.getUserByName(token.getUsername());

		// Shiro完成对比逻辑,返回和令牌相关的正确的验证信息，第一个参数填登录用户名,第二个参数填合法的登录密码
		if (users != null) {
			//ShiroUser shiroUser = new ShiroUser(users.getUserId(), users.getAccount());
			ShiroUser shiroUser = new ShiroUser();
			shiroUser.setUserId(users.getUserId());
			shiroUser.setAccount(users.getAccount());
			shiroUser.setUserName(users.getUserName());
			shiroUser.setUserEmail(users.getUserEmail());
			shiroUser.setUserPhone(users.getUserPhone());
			shiroUser.setFirstLogin(users.getFirstLogin() == null ? "" : DateUtil.getDefaultLongDateStr(users.getFirstLogin()));
			shiroUser.setPrevLogin(users.getPrevLogin() == null ? "" : DateUtil.getDefaultLongDateStr(users.getPrevLogin()));
			shiroUser.setPrevIp(users.getPrevIp());
			shiroUser.setLastLogin(users.getLastLogin() == null ? "" : DateUtil.getDefaultLongDateStr(users.getLastLogin()));
			shiroUser.setLoginCount(users.getLoginCount());
			shiroUser.setIsOnline(users.getIsOnline());
			shiroUser.setCreated(users.getCreated());
			shiroUser.setCreater(users.getCreater());
			shiroUser.setLastmod(users.getLastmod());
			shiroUser.setModifyer(users.getModifyer());
			
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(shiroUser, users.getPassword(), getName());
			this.setSession("shiroUser", shiroUser);
			return authcInfo;
		} else {
			throw new AuthenticationException();
		}

		// 没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常

	}

	/**
	 * 为当前登录的Subject授予角色和权限 ,该方法的调用时机为需授权资源被访问时
	 * 并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
	 * 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
	 * 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
		String username = shiroUser.getAccount();
		if (username != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			// 查询用户授权信息
			List<Permission> perList = permissionService.getShiro("O");

			if (perList != null && perList.size() != 0) {
				for (Permission permission : perList) {
					info.addStringPermission(permission.getPmsnCode());
				}
				return info;
			}
		}
		return null;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	@PostConstruct
	public void initCredentialsMatcher() {// MD5加密
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 *
	 * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject subject = SecurityUtils.getSubject();
		if (null != subject) {
			Session session = subject.getSession();
			LOGGER.debug("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}
