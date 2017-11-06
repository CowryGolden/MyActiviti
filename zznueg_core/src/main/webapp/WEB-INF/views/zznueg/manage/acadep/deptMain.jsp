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
    <title>系部管理</title>
    <tags:include/>
    <script type="text/javascript">
        var $dg;
        var $grid;
        $(function () {
            $dg = $("#dg");
            $grid = $dg.treegrid();
            $('#deptList').tree({
                url: 'zznueg/manage/acainfo/getAllAcaInfo',
                loadFilter: function (rows) {
                    return convert(rows);
                },
                animate: true,
                onLoadSuccess: function (node, data) {
                    if (data.length > 0)
                        loadDept(data[0].id);
                },
                onClick: function (node) {
                    loadDept(node.id);
                }
            });
            
            //添加
            $("#addDept").click(function () {
                var data = $("#deptList").tree('getSelected');

                if (data == null) {
                    $.messager.show({
                        title: "提示",
                        msg: "请先选择要添加系部的学院!",
                        timeout: 1000 * 2
                    });
                    return;
                } else {
                    var row = $dg.treegrid('getSelected');
                    $.modalDialog({
                        title: "添加系部",
                        width: 600,
                        height: 400,
                        href: "zznueg/manage/deptinfo/deptEditDlg/acaId=" + data.id,
                        onLoad: function () {
                            if (row) {
                                var f = $.modalDialog.handler.find("#form");
                                f.form("load", {
                                    "prntId": row.deptId,
                                    "acaId": data.id,
                                    "acaName": data.text
                                });
                            } else {
                                var f = $.modalDialog.handler.find("#form");
                                f.form("load", {
                                    "acaId": data.id,
                                    "acaName": data.text
                                });
                            }
                        },
                        buttons: [{
                            text: '保存',
                            iconCls: 'icon-yes',
                            handler: function () {
                                $.modalDialog.openner = $grid; //因为添加成功之后，需要刷新这个treegrid，所以先预定义好
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
                }
            });
            
            //编辑/更新
            $("#editDept").click(function () {
                var row = $dg.treegrid('getSelected');
                var data = $("#deptList").tree('getSelected');
                if (row) {
                    $.modalDialog({
                        title: "编辑系部",
                        width: 600,
                        height: 400,
                        href: "zznueg/manage/deptinfo/deptEditDlg/acaId=" + data.id,
                        onLoad: function () {
                            var f = $.modalDialog.handler.find("#form");
                            f.form("load", row);
                        },
                        buttons: [{
                            text: '编辑',
                            iconCls: 'icon-yes',
                            handler: function () {
                                $.modalDialog.openner = $grid; //因为添加成功之后，需要刷新这个treegrid，所以先预定义好
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
            });
			

        });

        function loadDept(id) {
            $('#dg').treegrid({
            	width: 'auto',
                height: $(this).height() - 2,
                url: 'zznueg/manage/deptinfo/findDeptByAcaId/acaId=' + id,
                queryParams:{},
                idField: 'deptId',
                treeField: 'deptName',
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: true,
                onLoadSuccess: function (row, data) {
	            	//alert("data=" + JSON.stringify(data));
	                if (data == null || data.length == 0) {
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

        //easyUI-tree:格式化简单格式的JSON数据
        function convert(rows) {
            function exists(rows, prntId) {
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i].acaId == prntId) return true;
                }
                return false;
            }

            var nodes = [];
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (!exists(rows, row.prntId)) {
                    nodes.push({
                        id: row.acaId,
                        text: row.acaName,
                        iconCls: row.acaIcon
                    });
                }
            }

            var toDo = [];
            for (var i = 0; i < nodes.length; i++) {
                toDo.push(nodes[i]);
            }
            while (toDo.length) {
                var node = toDo.shift();
                for (var i = 0; i < rows.length; i++) {
                    var row = rows[i];
                    if (row.prntId == node.id) {
                        var child = {id: row.acaId, text: row.acaName, iconCls: row.acaIcon};
                        if (node.children) {
                            node.children.push(child);
                        } else {
                            node.children = [child];
                        }
                        toDo.push(child);
                    }
                }
            }
            return nodes;
        }
        
		//多条件检索功能，注意这里使用的是treegrid，而不是datagrid，避免关键字使用错误！！！
        function searchByMultiCondition() {
        	var deptName = $("#deptNameOrLeader").val();
    		var deptLeader = $("#deptNameOrLeader").val();
    		//查询参数直接添加在queryParams中    
	    	var queryParams = $("#dg").treegrid('options').queryParams;		//这里使用的是treegrid，一定要不要误用为datagrid，下同！！！
	        queryParams.deptName = deptName;
	        queryParams.deptLeader = deptLeader;
	        $("#dg").treegrid('options').queryParams=queryParams;
	        $("#dg").treegrid('reload');   //重新加载treegrid的数据
        }

    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'west'" style="padding:10px 0px 0px 5px;width:220px;background-color: #eff8fc">
    <ul id="deptList"/>
</div>
<div data-options="region:'center'" class="rightinfo">
    <div class="easyui-layout" id="tb">
        <ul class="toolbar">
            <shiro:hasPermission name="deptAdd">
                <li id="addDept"><span><img
                        src="resources/core/images/t01.png"/></span>添加
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="deptEdit">
                <li id="editDept"><span><img
                        src="resources/core/images/t02.png"/></span>修改
                </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="deptDel">
                <li id="delDept"><span><img
                        src="resources/core/images/t03.png"/></span>删除
                </li>
            </shiro:hasPermission>
        </ul>
        <ul class="toolbar1">
			<li><input id="deptNameOrLeader" type="text" placeholder="输入系部或系主任名称搜索" /><img id="searchBtn" onclick="searchByMultiCondition()" src="resources/zznueg/img/search.png" /></li>
		</ul>
    </div>
    <table title="系部信息" class="easyui-treegrid" id="dg" data-options="toolbar: '#tb'">
        <thead>
        <tr>
            <th data-options="field:'deptName'" width="20%" align="center">系名称</th>
            <th data-options="field:'acaName'" width="15%" align="center">所属学院</th>
            <th data-options="field:'deptLeader'" width="25%" align="center">系主任</th>
            <th data-options="field:'contact'" width="15%" align="center">联系方式</th>
            <th data-options="field:'memo'" width="23%" align="center">备注</th>
        </tr>
        </thead>
    </table>

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
            <input name="" type="button" class="sure" value="确定"/>&nbsp; 
            <input name="" type="button" class="cancel" value="取消"/>
        </div>
    </div>
</div>
</body>
</html>