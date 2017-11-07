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
    <title>科目管理</title>
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
                url: "zznueg/manage/subjectinfo/findAllSubInfoByPage",
                queryParams:{},
                pagination: true,
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: true,
                idField: 'subjectId',
                singleSelect: true,
                columns: [[
                    {
                        field: 'subjectName',
                        title: '科目名称',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'subjectCategory',
                        title: '科目类型',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        formatter: function (value, row) {
                            if ("00" == row.subjectCategory) return "<font color=green>视频<font>";
                            else if ("01" == row.subjectCategory) return "<font color=green>文档<font>";
                            else return "<font color=red>未知<font>";
                        },
                        editor: "text"
                    },
                    {
                        field: 'isValid',
                        title: '有效标志',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        formatter: function (value, row) {
                            if ("1" == row.isValid) return "<font color=green>有效<font>";
                            else return "<font color=red>无效<font>";
                        },
                        editor: {
                            type: 'checkbox',
                            options: {
                                on: 'Y',
                                off: 'N'
                            }
                        }
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
                onDblClickRow: modSubject,	//双击行事件，弹出修改对话框
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
            
            //增加科目
            $("#addSubject").click(function () {
                $.modalDialog({
                    title: "添加科目",
                    width: 600,
                    height: 400,
                    href: "zznueg/manage/subjectinfo/subEditDlg",
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

            //修改科目
            $("#modSubject").click(function () {
            	modSubject();
            });
            
            //删除科目
            $("#delSubject").click(function () {
                var node = $dg.datagrid('getSelected');
                if (node) {
                	var rowIndex = $dg.datagrid('getRowIndex', node);
                    $.messager.confirm("提示", "确定要删除记录吗?",
                    	function (result) {
                        	if (result) {
                            	$.ajax({
                                	url: "zznueg/manage/subjectinfo/delSubject",
                                	data: {
                                    	'subjectId': node.subjectId
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
        
        //将编辑科目方法独立出来，方便点击修改按钮或鼠标双击行时使用
        function modSubject() {
        	var row = $dg.datagrid('getSelected');
            if (row) {
                $.modalDialog({
                    title: "修改科目",
                    width: 600,
                    height: 400,
                    href: "zznueg/manage/subjectinfo/subEditDlg",
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
	       	var subjectName = $("#subjectName").val();
	   		var subjectCategory = $("#subjectCategory").val();
	   		var isValid = $("#isValid").val();
	        //查询参数直接添加在queryParams中    
	    	var queryParams = $("#dg").datagrid('options').queryParams;
	        queryParams.subjectName = subjectName;
	        queryParams.subjectCategory = subjectCategory;
	        queryParams.isValid = isValid;
	        $("#dg").datagrid('options').queryParams=queryParams;
	        $("#dg").datagrid('reload');   //重新加载datagrid的数据
		}
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'center'" class="rightinfo">
    <div class="easyui-layout" id="tb">
        <ul class="toolbar">
            <shiro:hasPermission name="subjectAdd">
                <li id="addSubject"><span><img
                        src="resources/core/images/t01.png"/></span>添加
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="subjectMod">
                <li id="modSubject"><span><img
                        src="resources/core/images/t02.png"/></span>修改
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="subjectDel">
                <li id="delSubject"><span><img
                        src="resources/core/images/t03.png"/></span>删除
                </li>
            </shiro:hasPermission>
        </ul>
        <ul class="toolbar1">
        	<li><b>科目类型：</b>
        		<select id="subjectCategory" style="height: 100%;" onchange="searchByMultiCondition()">
					<option value="" selected="selected">--请选择--</ption>
					<option value="00">视频</option>
                    <option value="01">文档</option>
				</select>
        	</li>
        	<li><b>有效标志：</b>
        		<select id="isValid" style="height: 100%;" onchange="searchByMultiCondition()">
					<option value="" selected="selected">--请选择--</ption>
					<option value="0">无效</option>
                    <option value="1">有效</option>
				</select>
        	</li>
			<li><input id="subjectName" type="text" placeholder="输入科目名称搜索" /><img id="searchBtn" onclick="searchByMultiCondition()" src="resources/core/images/search1.png" /></li>
		</ul>
    </div>
    <table class="tablelist" id="dg" title="科目信息"></table>

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