/**
 * Title: ShiroUser.java
 * Package com.zzrenfeng.base.shiro
 * author  zhoujincheng
 * date 2015年9月14日 下午6:06:07
 * version V1.0
 * Copyright (c) 2015,zhoujincheng777@qq.com All Rights Reserved.
 */

package com.zzrenfeng.base.shiro;

import java.io.Serializable;

import com.zzrenfeng.base.entity.BaseDomain;

/**
 * ClassName: ShiroUser
 * Description: 登录用户权限
 * author zhoujincheng
 * date 2015年9月14日 下午6:06:07
 * 
 * zjc add:
 * date:20170816 14:49
 * desc:增加前端需要的字段，以便获取，如：用户名称，用户邮箱，用户电话号码等
 */

public class ShiroUser extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String account;
    //zjc add start
    private String userName;  	//用户名称
    private String userEmail;  	//用户电子邮箱  
    private String userPhone;  	//用户电话
    private String firstLogin;  //第一次登录时间
    private String prevLogin;  	//上一次登录时间
    private String prevIp;  	//上一次登录IP地址
    private String lastLogin;  	//最后一次登录时间
    private String loginCount;	//登录次数
    private Integer isOnline; 	//是否在线，1在线0不在线
    
	public ShiroUser() {
    	super();
    }
    
    public ShiroUser(String userId, String account) {
        super();
        this.userId = userId;
        this.account = account;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String getPrevLogin() {
		return prevLogin;
	}

	public void setPrevLogin(String prevLogin) {
		this.prevLogin = prevLogin;
	}

	public String getPrevIp() {
		return prevIp;
	}

	public void setPrevIp(String prevIp) {
		this.prevIp = prevIp;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 

}
