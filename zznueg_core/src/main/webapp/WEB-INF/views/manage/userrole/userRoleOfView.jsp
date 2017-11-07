<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="UTF-8">
    <title>用户-角色视图</title>
    <tags:include/>
    
	<script type="text/javascript">
		var oldRoleId;
		oldRoleId = $("#oldRoleId").val();
		
	    $(function () {
	    	getRoleListInfo(oldRoleId);  //加载角色下拉列表
	    	getUserList("manage/userRole/getUserRoleInfoByPage");  //加载表格数据
	    	
	    	
	    	$("#roleId").change(function(){ 
	    		//alert("roleId=" + $(this).children('option:selected').val());
	    		$("#oldRoleId").val($("#roleId").val());
	    		oldRoleId = $("#oldRoleId").val();
	    	});
	    	
	    });
	
		//加载表格数据
	    function getUserList(url) {
	        $("#dg").datagrid({
	            width: 'auto',
	            height: $(this).height() - 2,
	            url: url,
	            queryParams:{},
	            pagination: true,
	            animate: true,
	            rownumbers: true,
	            fitColumns: true,
	            striped: true,
	            border: true,
	            idField: 'userId',
	            singleSelect: true,
	            columns: [[
	                {
	                    field: 'userId',
	                    title: '用户序号',
	                    width: parseInt($(this).width() * 0.05),
	                    align: 'center',
	                    editor: "text",
	                    hidden: true
	                }, 
	                {
	                    field: 'account',
	                    title: '用户账号',
	                    width: parseInt($(this).width() * 0.1),
	                    align: 'center',
	                    editor: "text"
	                },
	                {
	                    field: 'userName',
	                    title: '用户名称',
	                    width: parseInt($(this).width() * 0.1),
	                    align: 'center',
	                    editor: "text"
	                },
	                {
	                    field: 'userDesc',
	                    title: '用户描述',
	                    width: parseInt($(this).width() * 0.1),
	                    align: 'center',
	                    editor: "text"
	                },
	                {
	                    field: 'userType',
	                    title: '用户类型',
	                    width: parseInt($(this).width() * 0.1),
	                    align: 'center',
	                    formatter: function (value, row) {
	                        if ("T" == row.userType) return "<font color=red>教师<font>";
	                        else if("S" == row.userType) return "<font color=green>学生<font>";
	                    },
	                    editor: "text"
	                },
	                {
	                    field: 'roleId',
	                    title: '角色Id',
	                    width: parseInt($(this).width() * 0.05),
	                    align: 'center',
	                    editor: "text",
	                    hidden: true
	                },
	                {
	                    field: 'roleName',
	                    title: '角色名称',
	                    width: parseInt($(this).width() * 0.1),
	                    align: 'center',
	                    editor: "text",
	                    styler: function(value, row, index){ //value-单元格的值；row-行对象；index-行序号；
	                    	//if(value == "超级管理员")	{return 'background-color:#ffee00;color:red;';}
	                    	return 'background-color:#E0FFFF;opacity:0.7;';  //opacity-透明度；透明度：ie下用filter:alpha(opacity=50);范围0到100，非ie下用opacity:0.5;范围0到1
	                    }
	                },
	                {
	                    field: 'roleDesc',
	                    title: '角色描述',
	                    width: parseInt($(this).width() * 0.1),
	                    align: 'center',
	                    editor: "text"
	                }
	            ]], toolbar: '#tb',
	            onLoadSuccess: function (data) {
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
	    }
	    
	    //加载角色下拉列表
	    function getRoleListInfo(oldRoleId) {
	    	$.ajax({
				url:'manage/role/findAllRoleList',
				method:'get',
				dataType: "json",
				success: function(data) {
		        	//alert(data.toString());
		        	$("#roleId").append('<option value="" selected="selected">--请选择--</ption>');
		        	$.each(data, function(key, val) {
		        		if(val.roleId == oldRoleId) {
		        			$("#roleId").append('<option value="' + val.roleId + '" selected>' + val.roleName + '</option>');
		        		} else {
		        			$("#roleId").append('<option value="' + val.roleId + '">' + val.roleName + '</option>');
		        		}
	              	});
				 
		        }
			});
	    }
	    
	    //多条件检索功能
	    function searchByMultiCondition() {
	       	var account = $("#account").val();
	   		var userName = $("#account").val();
	   		var roleId = $("#roleId").val();
	        //查询参数直接添加在queryParams中    
	    	var queryParams = $("#dg").datagrid('options').queryParams;
	        queryParams.account = account;
	        queryParams.userName = userName;
	        queryParams.roleId = roleId;
	        $("#dg").datagrid('options').queryParams=queryParams;
	        $("#dg").datagrid('reload');   //重新加载datagrid的数据
		}
	    
	</script>

</head>
<body class="easyui-layout">
<!-- 	<div data-options="region:'west'" style="padding:10px 0px 0px 5px;width:250px;background-color: #eff8fc">
	    <ul id="userRoleList" class="tree" style="display: block;">
	    	<li id="userOfRole">
	    		<div class="tree-node">
	    			<span class="tree-indent"></span>
	    			<span class="tree-icon tree-file icon-star"></span>
	    			<span class="tree-title">用户-角色</span>
	    		</div>    		
	    	</li>    	
	    	<li id="roleOfUser">
	    		<div class="tree-node">
	    			<span class="tree-indent"></span>
	    			<span class="tree-icon tree-file icon-love"></span>
	    			<span class="tree-title">角色-用户</span>
	    		</div>    		
	    	</li>
	    </ul>
	</div> -->
	<div data-options="region:'center'" class="rightinfo">
		<div class="easyui-layout" id="tb">			
			<ul class="toolbar1">
	        	<li><b>角色名称：</b>
	        		<input name="oldRoleId" id="oldRoleId" type="hidden" />
	        		<select id="roleId" style="height: 100%;" onchange="searchByMultiCondition()">
						<!-- <option value="" selected="selected">--请选择--</ption> -->
					</select>
	        	</li>
				<li><input id="account" type="text" placeholder="输入用户账号或名称搜索" /><img id="searchBtn" onclick="searchByMultiCondition()" src="resources/core/images/search1.png" /></li>
			</ul>
		</div>
	    <table class="tablelist" id="dg" title="用户-角色"></table>
	</div>
	
</body>
</html>