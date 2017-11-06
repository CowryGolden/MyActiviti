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
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>"/>
    <meta charset="utf-8" />
    <title>郑州师范学院500错误页面</title>
    <tags:include/>
</head>
<body>
<div class="main">
    <div class="con">
        <div class="errorPic">
        <img src="resources/zznueg/images/error.gif"/>
        </div>
        <div class="errorNotes">
            <div class="re">
                <div class="title">抱歉，找不到您要的页面……</div>
                <dl>
                    <dt>1、可能是您访问的内容不存在</dt> 
                    <dt>2、可能是您访问的内容已过期</dt>
                    <dt>3、可能是您访问的网址有误</dt>
                </dl>
            </div>
           <ul>
                <li><a href="/" >返回首页</a></li>
                <li><a href="javascript:history.go(-1);">返回上一页</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
<%
/**
问题描述：jsp加载视频、pdf等文件时报错：
	严重: Servlet.service() for servlet spring-mvc threw exception  
	java.lang.IllegalStateException: getOutputStream() has already been called for this response  

原因分析：在JSP页面释放资源的时候，调用了ServetResponse.getWriter()方法 
	public java.io.PrintWriter getWriter() 
	Either this method or getOutputStream() may be called to write the body, not both 
	由于ServletResponse.getOutputStream()方法和该方法都有可能被调用，来输出JSP页面的内容，如果其中的一个方法被调用了，再调用另一个方法就会抛出异常。 
解决方案：在jsp页面最后加上如下两句话即可：  
*/

	out.clear(); //清空缓存的内容 
	out = pageContext.pushBody(); //·返回一个新的BodyContent(代表一个HTML页面的BODY部分内容） 
	                              //·保存JspWriter实例的对象out 
	                              //·更新PageContext的out属性的内容
%>
