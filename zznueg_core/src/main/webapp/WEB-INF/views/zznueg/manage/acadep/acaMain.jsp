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
    <title>学院管理</title>
    <tags:include/>
    <script type="text/javascript">
        var $dg;
        var $grid;
        $(function () {
			//gridModel数据填充
            $dg = $("#dg");
            $grid = $dg.datagrid({
                width: 'auto',
                height: $(this).height() - 2,
                url: "zznueg/manage/acainfo/findAllAcaInfoByPage",
                queryParams:{},
                pagination: true,
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: true,
                idField: 'acaId',
                singleSelect: true,
                columns: [[
                    {
                        field: 'acaName',
                        title: '学院名称',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'acaLeader',
                        title: '学院负责人',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'contact',
                        title: '联系方式',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'memo',
                        title: '备注',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: "text"
                    }
                ]], 
                toolbar: '#tb',
                onDblClickRow: editAca,		//双击行记录弹出编辑对话框
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
            
            //增加
            $("#addAca").click(function () {
                $.modalDialog({
                    title: "添加学院",
                    width: 600,
                    height: 400,
                    href: "zznueg/manage/acainfo/acaEditDlg",
                    buttons: [{
                        text: '保存',
                        iconCls: 'icon-yes',
                        handler: function () {
                            $.modalDialog.openner = $grid;
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
                    }
                    ]
                });
            });

            //修改
            $("#editAca").click(function () {
            	editAca();
            });
            
            //删除
            $("#delAca").click(function () {
                var node = $dg.datagrid('getSelected');
                if (node) {
                	var rowIndex = $dg.datagrid('getRowIndex', node);
                    $.messager.confirm("提示", "确定要删除记录吗?",
                    	function (result) {
                        	if (result) {
                            	$.ajax({
                                	url: "zznueg/manage/acainfo/delAcademy",
                                	data: {
                                    	'acaId': node.acaId
                                    },
                                 	method: "POST",
                                	dataType: "JSON",
                                    success: function (rsp) {
                                    	if (rsp.status) {
                                        	$dg.datagrid('deleteRow', rowIndex);
                                        }
                                        $.messager.show({
                                        	title: rsp.title,
                                            msg: rsp.message,
                                            timeout: 1000 * 2
                                        });
                                    },
                                    error: function () {
                                    	$.messager.show({
                                        	title: "提示",
                                            msg: "提交错误了！",
                                            timeout: 1000 * 2
                                        });
                                    }
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
        
        //将编辑学院方法独立出来，方便点击修改按钮或鼠标双击行时使用
        function editAca() {
        	var row = $dg.datagrid('getSelected');
            if (row) {
                $.modalDialog({
                    title: "修改学院",
                    width: 600,
                    height: 400,
                    href: "zznueg/manage/acainfo/acaEditDlg",
                    onLoad: function () {
                        var f = $.modalDialog.handler.find("#form");
                        f.form("load", row);
                    },
                    buttons: [{
                        text: '修改',
                        iconCls: 'icon-yes',
                        handler: function () {
                            $.modalDialog.openner = $grid;
                            var f = $.modalDialog.handler.find("#form");
                            f.submit();
                        }
                    },
                        {
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
        
        //多条件检索功能
        function searchByMultiCondition() {
        	var acaName = $("#acaNameOrLeader").val();
    		var acaLeader = $("#acaNameOrLeader").val();
    		//查询参数直接添加在queryParams中    
	    	var queryParams = $("#dg").datagrid('options').queryParams;
	        queryParams.acaName = acaName;
	        queryParams.acaLeader = acaLeader;
	        $("#dg").datagrid('options').queryParams=queryParams;
	        $("#dg").datagrid('reload');   //重新加载datagrid的数据
        }
        
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'center'" class="rightinfo">
    <div class="easyui-layout" id="tb">
        <ul class="toolbar">
            <shiro:hasPermission name="acaAdd">
                <li id="addAca"><span><img
                        src="resources/core/images/t01.png"/></span>添加
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="acaEdit">
                <li id="editAca"><span><img
                        src="resources/core/images/t02.png"/></span>修改
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="acaDel">
                <li id="delAca"><span><img
                        src="resources/core/images/t03.png"/></span>删除
                </li>
            </shiro:hasPermission>
        </ul>
        <ul class="toolbar1">
			<li><input id="acaNameOrLeader" type="text" placeholder="输入学院或负责人名称搜索" /><img id="searchBtn" onclick="searchByMultiCondition()" src="resources/zznueg/img/search.png" /></li>
		</ul>
    </div>
    <table class="tablelist" id="dg" title="学院信息"></table>

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