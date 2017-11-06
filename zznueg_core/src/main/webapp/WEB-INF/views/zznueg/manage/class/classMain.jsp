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
    <title>班级管理</title>
    <tags:include/>
    <script type="text/javascript">
        $(function () {

            $('#acaDeptList').tree({
                url: 'zznueg/manage/classinfo/getAcaDeptTreeList',
                animate: true,
                onLoadSuccess: function (node, data) {
                    //if (data.length > 0) {loadAcaDept(data[0].id)};
                	loadClassInfo(null);  //首次加载成功（没有点击树形结构任何节点）时，显示所有班级信息
                },
                onClick: function (node) {
                	//alert("node.type=" + node.type);
                	if(node.type == "ACA") {
                		$.messager.show({
                            title: "提示",
                            msg: "学院下面没有班级，请选择要系部!",
                            timeout: 1000 * 2
                        });
                	} else if(node.type == "DEPT") {
                		loadClassInfo(node.id);
                	} else {
                		$.messager.show({
                            title: "提示",
                            msg: "节点类型为[node.type=" + node.type + "]下面没有班级，请选择要系部!",
                            timeout: 1000 * 2
                        });
                	}                	
                }
            });
            
            //增加
            $("#classAdd").click(function () {
                var data = $("#acaDeptList").tree('getSelected');
                if (data == null || data.type != "DEPT") {
                    $.messager.show({
                        title: "提示",
                        msg: "学院下面不能添加班级，请选择要添加班级的系部!",
                        timeout: 1000 * 2
                    });
                } else {
                    $.modalDialog({
                        title: "添加班级",
                        width: 600,
                        height: 300,
                        href: "zznueg/manage/classinfo/classEditDlg",
                        onLoad: function () {
                            var f = $.modalDialog.handler.find("#form");
                            f.form("load", {
                                "deptId": data.id,
                                "deptName": data.text
                            });
                        },
                        buttons: [{
                            text: '保存',
                            iconCls: 'icon-yes',
                            handler: function () {
                                $.modalDialog.openner = $("#dg").treegrid(); //因为添加成功之后，需要刷新这个treegrid，所以先预定义好
                                var f = $.modalDialog.handler.find("#form");
                                f.submit();
                            }
                        }, {
                            text: '取消',
                            iconCls: 'icon-no',
                            handler: function () {
                                $.modalDialog.handler.dialog('destroy');
                                $.modalDialog.handler = undefined;
                            }
                        }]
                    });
                }
            });
            
            //修改
            $("#classEdit").click(function () {
            	editClass();
            });
            
          	//删除
            $("#classDel").click(function () {
                var node = $("#dg").treegrid('getSelected');
                if (node) {
                    $.messager.confirm("提示", "确定要删除记录吗?",
                            function (result) {
                                if (result) {
                                    var request = $.ajax({
                                        url: "zznueg/manage/classinfo/delClassById",
                                        data: {
                                            'classId': node.classId
                                        },
                                        method: "POST",
                                        dataType: "JSON"
                                    });

                                    request.done(function (rsp) {
                                        if (rsp.status) {
                                            $("#dg").treegrid('remove', node.classId);
                                        }
                                        $.messager.show({
                                            title: rsp.title,
                                            msg: rsp.message,
                                            timeout: 1000 * 2
                                        });
                                    });

                                    request.fail(function () {
                                        $.messager.show({
                                            title: "提示",
                                            msg: "提交错误了！",
                                            timeout: 1000 * 2
                                        });
                                    });
                                }
                            });
                } else {
                    $.messager.show({
                        title: "提示",
                        msg: "请选择一行记录!",
                        timeout: 1000 * 2
                    });
                }
            });
          	

        });
        
        //编辑班级信息；将该方法独立出来，就是为了方便点击编辑按钮或双击行记录时能共同调用；
        function editClass() {
        	var row = $("#dg").treegrid('getSelected');
            if (row) {
                $.modalDialog({
                    title: "编辑班级",
                    width: 600,
                    height: 300,
                    href: "zznueg/manage/classinfo/classEditDlg",
                    onLoad: function () {
                        var f = $.modalDialog.handler.find("#form");
                        f.form("load", row);
                    },
                    buttons: [{
                        text: '保存',
                        iconCls: 'icon-yes',
                        handler: function () {
                            $.modalDialog.openner = $("#dg").treegrid(); //因为添加成功之后，需要刷新这个treegrid，所以先预定义好
                            var f = $.modalDialog.handler.find("#form");
                            f.submit();
                        }
                    }, {
                        text: '取消',
                        iconCls: 'icon-no',
                        handler: function () {
                            $.modalDialog.handler.dialog('destroy');
                            $.modalDialog.handler = undefined;
                        }
                    }]
                });
            } else {
                $.messager.show({
                    title: "提示",
                    msg: "请选择一行记录!",
                    timeout: 1000 * 2
                });
            }
        }
		
        //加载班级信息
        function loadClassInfo(id) {
            $('#dg').datagrid({
            	width: 'auto',
            	height: $(this).height() - 2,
                //url: 'zznueg/manage/classinfo/getClassInfoByDeptId/deptId=' + id,
                url: 'zznueg/manage/classinfo/getClassInfoByPage',
                queryParams: {"deptId" : id},
                idField: 'classId',
                pagination: true,
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: true,
                singleSelect: true,
                columns: [[
                    {
                        field: 'className',
                        title: '班级名称',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'deptName',
                        title: '所属系名称',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'teacherName',
                        title: '班主任姓名',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'memo',
                        title: '备注',
                        width: parseInt($(this).width() * 0.12),
                        align: 'center',
                        editor: "text"
                    }
                ]], toolbar: '#tb',
                onDblClickRow: editClass,	//双击行记录时弹出编辑对话框
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
            $("#dg").treegrid('unselectAll');
        }
        
		//多条件检索
		function searchByMultiCondition() {
    		var className = $("#classOrTeachName").val();
    		var teacherName = $("#classOrTeachName").val();
    		//查询参数直接添加在queryParams中    
	    	var queryParams = $("#dg").datagrid('options').queryParams;
	        queryParams.className = className;
	        queryParams.teacherName = teacherName;
	        $("#dg").datagrid('options').queryParams=queryParams;
	        $("#dg").datagrid('reload');   //重新加载datagrid的数据
		}

    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'west'" style="padding:10px 0px 0px 5px;width:250px;background-color: #eff8fc">
    <ul id="acaDeptList"/>
</div>
<div data-options="region:'center'" class="rightinfo">
    <div class="easyui-layout" id="tb">
        <ul class="toolbar">
            <shiro:hasPermission name="classAdd">
                <li id="classAdd"><span><img
                        src="resources/core/images/t01.png"/></span>添加
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="classEdit">
                <li id="classEdit"><span><img
                        src="resources/core/images/t02.png"/></span>修改
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="classDel">
                <li id="classDel"><span><img
                        src="resources/core/images/t03.png"/></span>删除
                </li>
            </shiro:hasPermission>
        </ul>
        <ul class="toolbar1">	<!-- 工具栏居右对齐 -->
        	<li><input id="classOrTeachName" type="text" placeholder="输入班级或班主任名称搜索" /><img id="searchBtn" onclick="searchByMultiCondition()" src="resources/zznueg/img/search.png" /></li>
		</ul>
    </div>
    <table title="班级信息" class="easyui-datagrid" id="dg"></table>
    <div class="tip">
        <div class="tiptop">
            <span>提示信息</span><a></a>
        </div>
        <div class="tipinfo">
            <span><img src="resources/core/images/ticon.png"/></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>
        <div class="tipbtn">
            <input name="" type="button" class="sure" value="确定"/>&nbsp; <input
                name="" type="button" class="cancel" value="取消"/>
        </div>
    </div>
</div>
</body>
</html>