<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    
    String stuUserId = request.getParameter("stuUserId");
    String pageFlag = request.getParameter("pageFlag");
    if(stuUserId == null) {stuUserId = "";}
    if(pageFlag == null) {pageFlag = "";}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>教师-学生概览</title>
		<tags:include/>
		<link href="resources/zznueg/css/style.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">
			var $dg;
	        var $grid;
	        var rowsCount;  //定义全局变量，学生行记录总数
	        var stuUserId = "";  //概览页面，点击详情请求参数-定位学生ID,初始化为空字符串
	        var pageFlag = ""; //概览页面，点击详情请求参数-页面来源标志,初始化为空字符串
	        stuUserId = "<%=stuUserId%>";   //通过jsp的方法获取ModelAndView.addObject()，传递来的参数
	        pageFlag = "<%=pageFlag%>";
	        
	        $(function() {
	        	$dg = $("#dg");
	        	$grid = $dg.datagrid({
	        		width: 'auto',
	                height: $(this).height() - 230,
	                url: "zznueg/portal/teacherplatform/getStudentOverview",
	                queryParams:{},
	                pagination: true,
	                rownumbers: true,
	                animate: true,
	                fitColumns: true,
	                striped: true,
	                border: true,
	                idField: 'stuUserId',
	                singleSelect: true,
	                columns: [[
   		                {
		                    field: 'stuName',
		                    title: '学生姓名',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text"
		                },
						{
						    field: 'stuNo',
						    title: '学号',
						    width: parseInt($(this).width() * 0.1),
						    align: 'center',
						    editor: "text"
						},
		                {
		                    field: 'stuSex',
		                    title: '性别',
		                    width: parseInt($(this).width() * 0.05),
		                    align: 'center',
		                    formatter: function (value, row) {
		                        if ("1" == row.stuSex) return "<font color=green>男<font>";
		                        else if ("0" == row.stuSex) return "<font color=red>女<font>";
		                    },
		                    editor: "text"
		                },
		                {
						    field: 'acaId',
						    title: '学院ID',
						    width: parseInt($(this).width() * 0.1),
						    align: 'center',
						    editor: "text",
						    hidden: true
						},
		                {
		                    field: 'acaName',
		                    title: '学院名',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text",
						    hidden: true
		                },
		                {
						    field: 'deptId',
						    title: '系ID',
						    width: parseInt($(this).width() * 0.1),
						    align: 'center',
						    editor: "text",
						    hidden: true
						},
		                {
		                    field: 'deptName',
		                    title: '系名',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text"
		                },
		                {
						    field: 'classId',
						    title: '班级ID',
						    width: parseInt($(this).width() * 0.1),
						    align: 'center',
						    editor: "text",
						    hidden: true
						},
		                {
		                    field: 'className',
		                    title: '班级名',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text"
		                },
		                {
		                    field: 'teachUserId',
		                    title: '教师ID',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text",
						    hidden: true
		                },
		                {
		                    field: 'weige',
		                    title: '微格',
		                    width: parseInt($(this).width() * 0.05),
		                    align: 'center',
		                    formatter: function (value, row) {
		                        if ("" == row.weige || null == row.weige) return "<font color=black>--<font>";
		                        else return "<font color=black>"+ row.weige + "<font>";
		                    },
		                    editor: "text"
		                },
		                {
		                    field: 'banshu',
		                    title: '板书',
		                    width: parseInt($(this).width() * 0.05),
		                    align: 'center',
		                    formatter: function (value, row) {
		                        if ("" == row.banshu || null == row.banshu) return "<font color=black>--<font>";
		                        else return "<font color=black>"+ row.banshu + "<font>";
		                    },
		                    editor: "text"
		                },
		                {
		                    field: 'jiaoan',
		                    title: '教案',
		                    width: parseInt($(this).width() * 0.05),
		                    align: 'center',
		                    formatter: function (value, row) {
		                        if ("" == row.jiaoan || null == row.jiaoan) return "<font color=black>--<font>";
		                        else return "<font color=black>"+ row.jiaoan + "<font>";
		                    },
		                    editor: "text"
		                },
		                {
		                    field: 'kejian',
		                    title: '课件',
		                    width: parseInt($(this).width() * 0.05),
		                    align: 'center',
		                    formatter: function (value, row) {
		                        if ("" == row.kejian || null == row.kejian) return "<font color=black>--<font>";
		                        else return "<font color=black>"+ row.kejian + "<font>";
		                    },
		                    editor: "text"
		                },
		                {
		                    field: 'zong',
		                    title: '总分',
		                    width: parseInt($(this).width() * 0.05),
		                    align: 'center',
		                    formatter: function (value, row) {
		                        if ("" == row.zong || null == row.zong) return "<font color=black>--<font>";
		                        else return "<font color=black>"+ row.zong + "<font>";
		                    },
		                    editor: "text"
		                },
		                {
		                    field: 'dengji',
		                    title: '等级',
		                    width: parseInt($(this).width() * 0.05),
		                    align: 'center',
		                    formatter: function (value, row) {
		                    	//等级根据四科综合判断，所有科目按最低分数的科目判断决定等级，如：有一科不及格综合等级即为不及格
		                    	var zong = Number(row.zong);
		                        if ("" == row.zong || null == row.zong) return "<font color=black>--<font>";
		                    	//定义数组
		                        var array = [Number(row.weige), Number(row.banshu), Number(row.jiaoan), Number(row.kejian)];
		                        //获取数组中的最小值
		            			var minScore = Math.min.apply(null, array);	            			
		            			//alert("****minScore****" + minScore);
		                        if(0 <= minScore && 60 > minScore) return "<font color=red>不及格<font>";
		                        if(60 <= minScore && 70 > minScore) return "<font color=green>及格<font>";
		                        if(70 <= minScore && 80 > minScore) return "<font color=green>一般<font>";
		                        if(80 <= minScore && 90 > minScore) return "<font color=green>良好<font>";
		                        if(minScore >= 90 && minScore <= 100) return "<font color=green>优秀<font>";
		                        /* 此方法不合理废弃不用
		                        if(0*4 <= zong && 60*4 > zong) return "<font color=red>不及格<font>";
		                        if(60*4 < zong && 70*4 > zong) return "<font color=green>及格<font>";
		                        if(70*4 < zong && 80*4 > zong) return "<font color=green>一般<font>";
		                        if(80*4 < zong && 90*4 > zong) return "<font color=green>良好<font>";
		                        if(zong <= 4*100 && zong > 90*4) return "<font color=green>优秀<font>"; */
		                    },
		                    editor: "text"
		                },
		                {
		                    field: 'caozuo',
		                    title: '操作',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    formatter: function (value, row) {
		                        if("" != row.stuUserId || null != row.stuUserId) {
		                        	return "<a id='" + row.stuUserId + "' href='javascript:void(0);' onclick=showStudentDetail(this);>详情</a>";
		                        }
		                    },
		                    editor: "text"
		                }
		            ]],
		            onLoadSuccess: function(data){
		            	//可以添加加载成功后需要处理的内容
		            	//加载成功后根据页面传递参数从而定位记录
		            	var rows = data.rows;  //所有行数据
		            	var row;  //行数据（一行的所有数据）
		            	var rowIndex;  //行索引（从0开始，即行号-1）
		            	rowsCount = rows.length;  //总行数（总记录数）
		            	if("" != stuUserId) {  //学生ID不为空，说明是从概览页面的详情重定向过来，记录定位到该学生即可
		            		$.each(data.rows, function(key, val) {
		            			//alert("data["+ key + "]" + val.stuUserId);
		            			if(stuUserId == val.stuUserId) {
		            				row = val;  //定位行数据
		            				//row = $dg.datagrid('getSelected');  //选中行的数据对象
			            			rowIndex = $dg.datagrid('getRowIndex',row);
			            			$dg.datagrid('selectRow', rowIndex);  //此时行索引即为选中的行索引，非行号
			            			return;
			            		}
		            		});	
		            	} else { //传递的学生ID为空说明是评分页面直接加载，定位到第一条记录，即不是从概览页面的详情重定向过来		            		
		            		$dg.datagrid('selectRow',0);  //加载成功后默认选择第一行数据
			            	row = $dg.datagrid('getSelected');  //选中行的数据对象
			            	rowIndex = $dg.datagrid('getRowIndex',row);  //选中行的行号
		            	}
		            }
	        	});
	        });
	        
	        //显示学生详情
	        function showStudentDetail(clkObj) {
	        	var clickId = $(clkObj).attr("id");	  //获取点击对象的ID值
	        	/**
	        	 * 在chrome和firefox下，window.location.href采用相对上下文根地址的方式是可用的，而IE采用的是相对当前地址的即在当前地址中再增加相对访问路径
	        	 * 如：输入window.location.href='home/sign_in'，则在chrome和firefox浏览器中出现"http://example.com/home/sign_in"(正确地址);
	        	 *    而在IE下却出现404（访问路径大致为"http://example.com/XXX/XXX/home/sign_in"(无效地址，因此报404)，其中"XXX/XXX"为父窗口路径）；
	        	 *	     于是在这里使用“绝对路径”就可以解决IE方位出现404的问题
	        	 * zjc add: 增加参数pageFlag=fromStuOverview，以此来控制“返回”按钮页面定位
	        	 */
	        	window.location.href="<%=basePath%>zznueg/portal/teacherplatform/studentDetail?stuUserId=" + clickId + "&pageFlag=fromStuOverview";
	        }
	        
			//通过学生学号或学生姓名进行模糊搜索
        	function searchByStuNo() {
        		var stuNo = $("#stuNoOrName").val();
        		var stuName = $("#stuNoOrName").val();
    	        //查询参数直接添加在queryParams中    
    	    	var queryParams = $("#dg").datagrid('options').queryParams;
    	        queryParams.stuNo = stuNo;  
    	        queryParams.stuName = stuName;
    	        $("#dg").datagrid('options').queryParams=queryParams;
    	        $("#dg").datagrid('reload');   //重新加载datagrid的数据
    	    }
		</script>
	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="javascript:void(0)">教师平台</a></li>
				<li><a href="javascript:void(0)">学生概览</a></li>
			</ul>
		</div>

		<div class="rightinfo">
<!-- 
			<div class="tools">
				<ul class="toolbar">
					<li class="click"><span><img src="images/t01.png" /></span>添加用户</li>
					<li class="click"><span><img src="images/t02.png" /></span>批量添加</li>
				</ul>
			</div>
 -->
 			<p class="rightP">
				<span>学生概览</span>
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
			<div id="toolsbar" class="tools">
<!-- 
				<ul class="toolbar">
					<li class="click"><span><img src="resources/zznueg/images/t01.png" /><a href="form.html"></a></span>添加用户</li>
				</ul>
 -->
				<ul class="toolbar1">
					<li><input id="stuNoOrName" type="text" placeholder="输入学生学号或姓名搜索" /><img id="searchBtn" onclick="searchByStuNo()" src="resources/zznueg/img/search.png" /></li>
				</ul>
			</div>
			<div class="easyui-layout" id="tb">
				<table id="dg" class="tablelist"></table>
			</div>
			
<!-- 
			<div class="pagin">
				<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
				<ul class="paginList">
					<li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
					<li class="paginItem"><a href="javascript:;">1</a></li>
					<li class="paginItem current"><a href="javascript:;">2</a></li>
					<li class="paginItem"><a href="javascript:;">3</a></li>
					<li class="paginItem"><a href="javascript:;">4</a></li>
					<li class="paginItem"><a href="javascript:;">5</a></li>
					<li class="paginItem more"><a href="javascript:;">...</a></li>
					<li class="paginItem"><a href="javascript:;">10</a></li>
					<li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
				</ul>
			</div>
 -->
			
		</div>

		<script type="text/javascript">
			$('.tablelist tbody tr:odd').addClass('odd');
		</script>

	</body>

</html>