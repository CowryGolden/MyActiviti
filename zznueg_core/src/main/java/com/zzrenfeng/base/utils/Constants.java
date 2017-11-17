/**
 * @Title: Constants.java
 * @Package com.zzrenfeng.base.utils
 * @author zhoujincheng
 * @date 2015年9月14日 下午6:07:30
 * @version V1.0
 * Copyright (c) 2015,zhoujincheng777@qq.com All Rights Reserved.
 */

package com.zzrenfeng.base.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.zzrenfeng.base.shiro.ShiroUser;

/**
 * author zhoujincheng
 * ClassName: Constants
 * Description:常用常量定义
 * date 2015年9月14日 下午6:07:30
 */

public class Constants {

    //正整数的正则表达式，用于StringUtil.compareRegex(regex,str)方法中
    public static final String REGEX_INTEGER = "^\\d+$";

    public static final String LOGIN_SESSION_DATANAME = "users";
    public static final String LOGIN_URL = "login";
    public static final String LOGIN_SUCCESS_URL = "index";
    public static final String LOGIN_LOGIN_OUT_URL = "loginout";
    public static final String LOGIN_MSG = "loginMsg";
    public static final String USERNAME_IS_NULL = "用户名为空!";
    public static final String LOGIN_IS_EXIST = "该用户已登录!";
    public static final String UNKNOWN_SESSION_EXCEPTION = "异常会话!";
    public static final String UNKNOWN_ACCOUNT_EXCEPTION = "账号错误!";
    public static final String INCORRECT_CREDENTIALS_EXCEPTION = "密码错误!";
    public static final String LOCKED_ACCOUNT_EXCEPTION = "账号已被锁定，请与系统管理员联系!";
    public static final String INCORRECT_CAPTCHA_EXCEPTION = "验证码错误!";
    public static final String AUTHENTICATION_EXCEPTION = "您没有授权!";
    public static final String UNKNOWN_EXCEPTION = "出现未知异常,请与系统管理员联系!";
    public static final String TREE_GRID_ADD_STATUS = "add";
    public static final String POST_DATA_SUCCESS = "操作成功!";
    public static final String POST_DATA_FAIL = "提交失败!";    
    public static final String GET_SQL_LIKE = "%";
    //zjc add
    public static final String MSG_GET_DATA_SUCESS = "获取数据成功！";
    public static final String MSG_GET_DATA_FAILURE = "获取数据失败！";
    public static final String MSG_GET_NULL_DATA = "获取数据为空！";
    public static final String MSG_EMPTY_DATA = "暂无数据！";
   
    public static final String EXCEPTION_MSG_GET_DATA = "获取数据异常！";
    
    //默认icon
    public static final String DEFAULT_ICON = "icon-default";
    public static final String COMPANY_ICON = "icon-company";
    public static final String DIVISION_ICON = "icon-flower";
    public static final String ACADEMY_ICON = "icon-company";
    public static final String STAR_ICON = "icon-star";
    public static final String LOVE_ICON = "icon-love";
    public static final String COLOR_ICON = "icon-color";
    public static final String POWER_ICON = "icon-power";
    //默认密码
    public static final String DEFAULT_PASSWORD = "111111";
    //默认分页，每页数据量
    public static final Integer DEFAULT_LENGTH = 10;

    //有效标记，E为有效，I为无效
    public static final String PERSISTENCE_STATUS = "E";
    public static final String PERSISTENCE_DELETE_STATUS = "I";

    public static final String SYSTEM_ADMINISTRATOR = "system";
    public static final String NULL_STRING = "";
    public static final String IS_DOT = ".";
    public static final String HQL_LIKE = "like";
    public static final String TEXT_TYPE_PLAIN = "text/plain";
    public static final String TEXT_TYPE_HTML = "text/html";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    //菜单标记，O代表是操作，不能有子菜单，M为菜单
    public static final String PMSN_O = "O";
    public static final String PMSN_M = "M";

    //是否叶节点，open为是，closed为否
    public static final String TREE_STATUS_OPEN = "open";
    public static final String TREE_STATUS_CLOSED = "closed";


    public static final String IS_EXT_SUBMENU = " 菜单正在被角色使用或可能包含子菜单!";
    public static final String SHIRO_USER = "shiroUser";
    public static final String LOGS_INSERT = "insert:";
    public static final String LOGS_INSERT_TEXT = "插入:";
    public static final String LOGS_INSERT_NAME = "insertLogs";
    public static final String LOGS_UPDATE = "update:";
    public static final String LOGS_UPDATE_TEXT = "更新:";
    public static final String LOGS_UPDATE_NAME = "updateLogs";
    public static final String LOGS_DELETE = "delete:";
    public static final String LOGS_DELETE_TEXT = "删除:";
    public static final String LOGS_DELETE_NAME = "deleteLogs";
    public static final String LOGS_TB_NAME = "Log";

    public static final String FILE_SUFFIX_SQL = ".sql";
    public static final String FILE_SUFFIX_ZIP = ".zip";
    
    /**
	 * 通用字符串常量"0"
	 */
	public final static String COMM_CONST_STRING_0 = "0";
	/**
	 * 通用字符串常量"1"
	 */
	public final static String COMM_CONST_STRING_1 = "1";
	/**
	 * 通用常量-整型数字：0
	 */
	public final static int COMM_CONST_INTEGER_0 = 0;
	/**
	 * 通用常量-整型数字：1
	 */
	public final static int COMM_CONST_INTEGER_1 = 1;
	
    /**
     * 用户类型-教师
     */
    public static final String USER_TYPE_TEACHER = "T";
    /**
     * 用户类型-学生
     */
    public static final String USER_TYPE_STUDENT = "S";
    
    /**
     * 是否在线：1-在线，1在线0离线
     */
    public static final Integer USER_LOGIN_ONLINE = 1; 
    /**
     * 是否在线：0-离线，1在线0离线
     */
    public static final Integer USER_LOGOUT_OFFLINE = 0;
    /**
     * 服务器ip地址和端口号
     */
    public static String SERVER_ADDRESS = null;
    
	//zjc add 登录状态码
    /**
     * 登录成功
     */
    public static final String STATUS_LOGIN_0000 = "0000";
    /**
     * 登录失败
     */
    public static final String STATUS_LOGIN_9999 = "9999";
    /**
     * 用户名错误
     */
    public static final String STATUS_LOGIN_0001 = "0001";
    /**
     * 密码错误
     */
    public static final String STATUS_LOGIN_0002 = "0002";
    /**
     * 验证码错误
     */
    public static final String STATUS_LOGIN_0003 = "0003";
    /**
     * 密码输入错误次数超限
     */
    public static final String STATUS_LOGIN_0004 = "0004";
    /**
     * 密码为初始密码
     */
    public static final String STATUS_LOGIN_0101 = "0101";
    /**
     * 账户失效
     */
    public static final String STATUS_LOGIN_0102 = "0102";
    /**
     * 账户超过有效期
     */
    public static final String STATUS_LOGIN_0103 = "0103";
    /**
     * 账户被禁用
     */
    public static final String STATUS_LOGIN_0104 = "0104";
    /**
     * 账户欠费
     */
    public static final String STATUS_LOGIN_0201 = "0201";
    
    
    /**
     * 字符串yes
     */
    public static final String COMM_STR_YES = "yes";
    /**
     * 字符串no
     */
    public static final String COMM_STR_NO = "no";
    

    private Constants() {
    }

    /**
     * param @return 参数
     * return ShiroUser 返回类型
     * throws
     * Title: getCurrendUser
     * Description: 获取当前登录用户实体类
     */
    public static ShiroUser getCurrendUser() {
        Subject subject = SecurityUtils.getSubject();
        return (ShiroUser) subject.getSession().getAttribute(SHIRO_USER);
    }

}
