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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>领导-学生概览</title>
		<tags:include/>
		<link href="resources/zznueg/css/style.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">			
			var $dg;
	        var $grid;
	        $(function() {
	        	$dg = $("#dg");
	        	$grid = $dg.datagrid({
	        		width: 'auto',
	                height: $(this).height() - 140,
	                url: "zznueg/portal/leaderplatform/getStudentOverview",
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
		                    field: 'acaName',
		                    title: '学院名',
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
		                    field: 'className',
		                    title: '班级名',
		                    width: parseInt($(this).width() * 0.1),
		                    align: 'center',
		                    editor: "text"
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
		                        if(minScore <= 100 && minScore >= 90) return "<font color=green>优秀<font>";
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
		                    editor: "text",
		                    hidden: true
		                }
		            ]],
		            toolbar: '#toolsbar',
		            onLoadSuccess: function(data){
		            	//可以添加加载成功后需要处理的内容
		            	//alert("data=" + JSON.stringify(data));
		                if (data == null || data.rows.length == 0) {
		                    $.messager.show({
		                        title: "提示",
		                        msg: "没有找到符合条件的数据",
		                        timeout: 1000 * 2
		                    });
		                }
		            },
		            onLoadError: function () {
		                $.messager.show({
		                    title: "错误",
		                    msg: "加载数据失败",
		                    timeout: 1000 * 2
		                });
		            }
	        	});
	        	
	        	
	        	
	        });
	        
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
				<li><a href="javascript:void(0);">领导平台</a></li>
				<li><a href="javascript:void(0);">学生概览</a></li>
			</ul>
		</div>

		<div data-options="region:'center'" class="rightinfo">
			<p class="rightP">
				<span>学生概览</span>
			</p>
			
			<div class="easyui-layout" id="tb">
				<ul class="toolbar"></ul>	<!-- 工具栏居左对齐 -->
				<ul class="toolbar1">	<!-- 工具栏居右对齐 -->
					<li><input id="stuNoOrName" type="text" placeholder="输入学生学号或姓名搜索" /><img id="searchBtn" onclick="searchByStuNo()" src="resources/zznueg/img/search.png" /></li>
				</ul>
			</div>
			<table id="dg" class="tablelist"></table>
		</div>
	</body>

</html>