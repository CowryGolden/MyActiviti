package com.zzrenfeng.base.utils;

import com.alibaba.fastjson.JSON;
import com.zzrenfeng.base.shiro.ShiroUser;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * 日志工具类
 * <p>
 * author zhoujincheng
 * create 2016/3/1 9:53
 */
public class LogUtils {


    public static final Logger ERROR_LOG = LoggerFactory.getLogger("es-error");
    public static final Logger ACCESS_LOG = LoggerFactory.getLogger("es-access");

    /**
     * 记录访问日志
     * [username][jsessionid][ip][accept][UserAgent][url][params][Referer]
     * <p>
     * param request
     */
    public static void logAccess(HttpServletRequest request) {
        String username = getUsername();
        String jsessionId = request.getRequestedSessionId();
        String ip = IpUtil.getIpAddr(request);
        String accept = request.getHeader("accept");
        String userAgent = request.getHeader("User-Agent");
        String url = request.getRequestURI();
        String params = getParams(request);
        String headers = getHeaders(request);

        StringBuilder s = new StringBuilder();
        s.append(getBlock(username));
        s.append(getBlock(jsessionId));
        s.append(getBlock(ip));
        s.append(getBlock(accept));
        s.append(getBlock(userAgent));
        s.append(getBlock(url));
        s.append(getBlock(params));
        s.append(getBlock(headers));
        s.append(getBlock(request.getHeader("Referer")));
        getAccessLog().info(s.toString());
    }

    /**
     * 记录异常错误
     * 格式 [exception]
     * <p>
     * param message
     * param e
     */
    public static void logError(String message, Throwable e) {
        String username = getUsername();
        StringBuilder s = new StringBuilder();
        s.append(getBlock("exception"));
        s.append(getBlock(username));
        s.append(getBlock(message));
        ERROR_LOG.error(s.toString(), e);
    }

    /**
     * 记录页面错误
     * 错误日志记录 [page/eception][username][statusCode][errorMessage][servletName][uri][exceptionName][ip][exception]
     * <p>
     * param request
     */
    public static void logPageError(HttpServletRequest request) {
        String username = getUsername();

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");


        if (statusCode == null) {
            statusCode = 0;
        }

        StringBuilder s = new StringBuilder();
        s.append(getBlock(t == null ? "page" : "exception"));
        s.append(getBlock(username));
        s.append(getBlock(statusCode));
        s.append(getBlock(message));
        s.append(getBlock(IpUtil.getIpAddr(request)));

        s.append(getBlock(uri));
        s.append(getBlock(request.getHeader("Referer")));
        StringWriter sw = new StringWriter();

        while (t != null) {
            t.printStackTrace(new PrintWriter(sw));
            t = t.getCause();
        }
        s.append(getBlock(sw.toString()));
        getErrorLog().error(s.toString());

    }


    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }


    protected static String getParams(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        return JSON.toJSONString(params);
    }


    private static String getHeaders(HttpServletRequest request) {
        Map<String, List<String>> headers = new HashMap<>();
        Enumeration<String> namesEnumeration = request.getHeaderNames();
        while (namesEnumeration.hasMoreElements()) {
            String name = namesEnumeration.nextElement();
            Enumeration<String> valueEnumeration = request.getHeaders(name);
            List<String> values = new ArrayList<>();
            while (valueEnumeration.hasMoreElements()) {
                values.add(valueEnumeration.nextElement());
            }
            headers.put(name, values);
        }
        return JSON.toJSONString(headers);
    }


    protected static String getUsername() {
//System.out.println("++++++++++++++++" + ((ShiroUser)SecurityUtils.getSubject().getPrincipal()).getUserName());   	
        return ((ShiroUser)SecurityUtils.getSubject().getPrincipal()).getUserName();
    }

    public static Logger getAccessLog() {
        return ACCESS_LOG;
    }

    public static Logger getErrorLog() {
        return ERROR_LOG;
    }
    
    /**
     * @功能描述：获取当前执行的类名
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月24日 上午11:23:30
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @return
     */
    public static String getCurrExeClassName() {
    	return Thread.currentThread().getStackTrace()[3].getClassName();
    }
    /**
     * @功能描述：获取当前执行的方法名
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月24日 上午11:23:34
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @return
     * 
     */
    public static String getCurrExeMethodName() {
    	return Thread.currentThread().getStackTrace()[3].getMethodName();
    }
    /**
     * @功能描述：获取当前执行代码的行数
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月24日 上午11:24:45
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @return
     */
    public static int getCurrExeMethodLineNum() {
    	return Thread.currentThread().getStackTrace()[3].getLineNumber();
    }
    /**
     * @功能描述：获取当前执行法类及方法的概要信息
     * 			形如：
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月24日 上午11:43:31
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @return
     * 
     * ********************************
     * getStackTrace堆栈信息如下：
     * 0--java.lang.Thread.getStackTrace(?) (line:1559)
     * 1--com.zzrenfeng.base.utils.LogUtils.getCurrExeMethodName(?) (line:179)
     * 2--com.zzrenfeng.base.utils.LogUtils.getCurrExeMethodBriefInfo(?) (line:217)
     * 3--com.zzrenfeng.zznueg.controller.SubjectInfoController.findAllSubInfoByPage(?) (line:89)
     * ********************************
     */
    public static String getCurrExeMethodBriefInfo() {
    	return getCurrExeClassName() + "." +
    			getCurrExeMethodName() + "(?) (line:" +
    			getCurrExeMethodLineNum() + ") is executed!";
    }
    /**
     * @功能描述：获取当前执行法类及方法的简要汇总信息
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月24日 上午11:27:46
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @return
     */
/*    public static String getCurrExeMethodSumInfo() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("===========当前类执行简要信息如下:===========" + "\n");
    	sb.append("CurrClassName:");
    	sb.append(getCurrExeClassName());
    	sb.append("\n");
    	sb.append("CurrMethodName:");
    	sb.append(getCurrExeMethodName());
    	sb.append("\n");
    	sb.append("CurrMethodLineNum:");
    	sb.append(getCurrExeMethodLineNum());
    	sb.append("\n");
    	sb.append("========================================" + "\n");
    	
    	return sb.toString();
    }*/
}
