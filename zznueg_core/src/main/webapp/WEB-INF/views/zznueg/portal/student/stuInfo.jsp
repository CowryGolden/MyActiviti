<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="studentInfo">
	<span class="rightImg" id="stuImg"><img src="resources/zznueg/images/i07.png"/></span>
	<span class="rightSpan" id="stuName">${rtnMap.stuName}</span>
	<span class="rightSpan" id="acaName">${rtnMap.acaName}</span>
	<span class="rightSpan" id="deptName">${rtnMap.deptName}</span>
	<span class="rightSpan" id="className">${rtnMap.className}</span>
	<!-- <span class="shang" id="prevBtn">上一位</span>
	<span class="next" id="nextBtn">下一位</span> -->
</div>