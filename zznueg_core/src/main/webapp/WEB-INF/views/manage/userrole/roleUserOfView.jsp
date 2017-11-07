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
    <title>角色-用户视图</title>
    <tags:include/>
    <script type="text/javascript">

        $(function () {
            $('#roleList').tree({
                url: "manage/userRole/getRoleTreeList",
                animate: true,
                onClick: function (node) {
                	//alert("roleId=" + node.id);
                    var url = "manage/userRole/getUsersByRole/roleId=" + node.id;
                    getUserList(url);
                }
            });

            getUserList("manage/userRole/getUserRoleInfoByPage");  //加载表格数据
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
	                    	return 'background-color:#F0FFF0;opacity:0.7;';  //opacity-透明度；透明度：ie下用filter:alpha(opacity=50);范围0到100，非ie下用opacity:0.5;范围0到1
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
		
		//多条件检索功能
	    function searchByMultiCondition() {
	       	var account = $("#account").val();
	   		var userName = $("#account").val();
	        //查询参数直接添加在queryParams中    
	    	var queryParams = $("#dg").datagrid('options').queryParams;
	        queryParams.account = account;
	        queryParams.userName = userName;
	        $("#dg").datagrid('options').queryParams=queryParams;
	        $("#dg").datagrid('reload');   //重新加载datagrid的数据
		}
		
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'west'" style="padding:10px 0px 0px 5px;width:250px;background-color: #eff8fc">
    <span><b>角色列表</b></span>
    <hr style="height:5px;border:none;border-top:1px dashed #0066CC;" />
    <ul id="roleList"/>
</div>
<div data-options="region:'center'" class="rightinfo">
	<div class="easyui-layout" id="tb">			
		<ul class="toolbar1">
			<li><input id="account" type="text" placeholder="输入用户账号或名称搜索" /><img id="searchBtn" onclick="searchByMultiCondition()" src="resources/core/images/search1.png" /></li>
		</ul>
	</div>
    <table class="tablelist" id="dg" title="用户列表"></table>
</div>
</body>
</html>