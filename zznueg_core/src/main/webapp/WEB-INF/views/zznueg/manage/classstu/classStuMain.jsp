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
    <title>班级-学生视图</title>
    <tags:include/>
    <script type="text/javascript">
    	var $acaDeptClassTree;
    	
        $(function () {
        	$acaDeptClassTree = $('#acaDeptClassTree');
        	
        	$acaDeptClassTree.tree({
                url: 'zznueg/manage/classinfo/getAcaDeptClassTreeList',
                animate: true,
                //dnd: true,	//启用拖放效果
                onLoadSuccess: function (node, data) {
                    if (data.length > 0) {
                    	if(data[0].state == "open") {  //判断当前点击节点的开合状态，从而控制开合按钮的隐藏和显示
                			$("#expandAll").hide();
                			$("#collapseAll").show();
                		} else if(data[0].state == "closed") {
                			$("#expandAll").show();
                			$("#collapseAll").hide();
                		}
                    	if(data[0].type == "ACA") {                    		
                    		loadDeptInfoByAcaId(data[0].id);
                    	} else if(data[0].type == "DEPT") {
                    		loadClassInfoByDeptId(data[0].id);
                    	} else if(data[0].type == "CLASS") {
                    		loadStudentInfoByClassId(data[0].id);
                    	} else {
                    		loadDeptInfoByAcaId(data[0].id);
                    	}
                    }
                        
                },
                onClick: function (node) {
                	//alert("node.type=" + node.type);  //alert("node.state=" + node.state);
                	if(node.state == "open") {	//判断当前点击节点的开合状态，从而控制开合按钮的隐藏和显示
               			$("#expandAll").hide();
               			$("#collapseAll").show();
               		} else if(node.state == "closed") {
               			$("#expandAll").show();
               			$("#collapseAll").hide();
               		}
                	if(node.type == "ACA") {
                		loadDeptInfoByAcaId(node.id);
                	} else if(node.type == "DEPT") {
                		loadClassInfoByDeptId(node.id);
                	} else if(node.type == "CLASS") {
                		loadStudentInfoByClassId(node.id);
                	} else {
                		loadDeptInfoByAcaId(node.id);
                	}
                }
            });
        	
        	//展开全部树形菜单
            $("#expandAll").click(function () {
                var node = $acaDeptClassTree.tree('getSelected');                
                if (node) {
                	//这里一定要注意：属性可用'expand'或'expandAll'；但是easyui:tree的该属性参数为target，而easyui:treegrid的该属性参数为id
                	$acaDeptClassTree.tree('expand', node.target);  
                	//以上是执行节点的展开动作，执行完后该节点对于的“开合按钮”状态即为：显示“收缩”，隐藏“开启”
                	$("#expandAll").hide();
           			$("#collapseAll").show();
                } else {
                    $acaDeptClassTree.tree('expandAll');
                  	//以上是执行所有节点的展开动作，执行完后“开合按钮”状态即为：显示“收缩”，隐藏“开启”
                	$("#expandAll").hide();
           			$("#collapseAll").show();
                }
            });

            //收起全部树形菜单
            $("#collapseAll").click(function () {
                var node = $acaDeptClassTree.tree('getSelected');
                if (node) {                	
                	//这里一定要注意：属性可用'collapse'或'collapseAll'；但是easyui:tree的该属性参数为target，而easyui:treegrid的该属性参数为id
                    $acaDeptClassTree.tree('collapse', node.target);
                	//以上是执行节点的收缩动作，执行完后该节点对于的“开合按钮”状态即为：显示“开启”，隐藏“收缩”
                	$("#expandAll").show();
           			$("#collapseAll").hide();
                } else {
                    $acaDeptClassTree.tree('collapseAll');
                	//以上是执行所有节点的收缩动作，执行完后“开合按钮”状态即为：显示“开启”，隐藏“收缩”
                	$("#expandAll").show();
           			$("#collapseAll").hide();
                }
            });

            //刷新树形菜单
            $("#refresh").click(function () {
                $acaDeptClassTree.tree('reload');
            });

        });
        
		//点击“学院”节点时；根据学院ID查询系部信息
        function loadDeptInfoByAcaId(id) {
			//显示对应功能的检索框，将其余功能的隐藏；
			$('#deptSearchField').show();
        	$('#classSearchField').hide();
        	$('#stuSearchField').hide();     	
			
            $('#dg').datagrid({
                height: $(this).height() - 2,
                url: 'zznueg/manage/deptinfo/findDeptByAcaId/acaId=' + id,
                idField: 'deptId',
                title: '学院-系部信息',
                pagination: true,
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: true,
                singleSelect: true,
                columns: [[
                    {
                        field: 'deptName',
                        title: '系部名称',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'acaName',
                        title: '所属学院名称',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'deptLeader',
                        title: '系主任姓名',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'contact',
                        title: '联系方式',
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
		
        //点击“系部”节点时；根据系部ID查询班级信息
        function loadClassInfoByDeptId(id) {
        	//显示对应功能的检索框，将其余功能的隐藏；
        	$('#deptSearchField').hide();
        	$('#classSearchField').show();
        	$('#stuSearchField').hide();
			
            $('#dg').datagrid({
                height: $(this).height() - 2,
                url: 'zznueg/manage/classinfo/getClassInfoByDeptId/deptId=' + id,
                idField: 'classId',
                title: '系部-班级信息',
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
        
		//点击“班级”节点时；根据班级ID查询学生信息
        function loadStudentInfoByClassId(id) {
        	//显示对应功能的检索框，将其余功能的隐藏；
        	$('#deptSearchField').hide();
        	$('#classSearchField').hide();
			$('#stuSearchField').show();
			
            $('#dg').datagrid({
                height: $(this).height() - 2,
                url: 'zznueg/manage/teastuinfo/getAllStudentByPage',
                queryParams: {classId : id},
                idField: 'id',
                title: '班级-学生信息',
                pagination: true,
                rownumbers: true,
                animate: true,
                fitColumns: true,
                striped: true,
                border: true,
                singleSelect: true,
                columns: [[
                    {
                        field: 'tsno',
                        title: '学生学号',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'name',
                        title: '学生姓名',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'sex',
                        title: '性别',
                        width: parseInt($(this).width() * 0.1),
                        align: 'center',
                        formatter: function (value, row) {
                            if ("1" == row.sex) return "<font color=green>男<font>";
                            else return "<font color=red>女<font>";
                        },
                        editor: "text"
                    },
                    {
                        field: 'deptName',
                        title: '所属系部',
                        width: parseInt($(this).width() * 0.08),
                        align: 'center',
                        editor: "text"
                    },
                    {
                        field: 'className',
                        title: '所属班级',
                        width: parseInt($(this).width() * 0.08),
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
            $("#dg").treegrid('unselectAll');
        }
		
		//多条件检索
		function searchByMultiCondition() {
			var tsno = $("#tsnoOrName").val();
    		var name = $("#tsnoOrName").val();
    		var className = $("#classOrTeachName").val();
    		var teacherName = $("#classOrTeachName").val();
    		var deptName = $("#deptOrLeaderName").val();
    		var deptLeader = $("#deptOrLeaderName").val();
    		//查询参数直接添加在queryParams中    
	    	var queryParams = $("#dg").datagrid('options').queryParams;
	        queryParams.tsno = tsno;
	        queryParams.name = name;
	        queryParams.className = className;
	        queryParams.teacherName = teacherName;
	        queryParams.deptName = deptName;
	        queryParams.deptLeader = deptLeader;
	        $("#dg").datagrid('options').queryParams=queryParams;
	        $("#dg").datagrid('reload');   //重新加载datagrid的数据
		}

    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'west'" style="padding:10px 0px 0px 5px;width:250px;background-color: #eff8fc">
    <div data-options="region:'center',border:true">
        <div id="tbClass" class="easyui-layout">
            <ul class="toolbar" style="padding: 6px 2px;">
                <li id="expandAll" style="display: none;"><span><img
                        src="resources/core/images/open.png"/></span>展开
                </li>
                <li id="collapseAll" style="display: none;"><span><img
                        src="resources/core/images/close.png"/></span>收缩
                </li>
                <li id="refresh"><span><img
                        src="resources/core/images/refresh.png"/></span>刷新
                </li>
            </ul>
        </div>
        <ul id="acaDeptClassTree"/>
    </div>    
</div>
<div data-options="region:'center'" class="rightinfo">
    <div class="easyui-layout" id="tb">
        <ul class="toolbar"></ul>	<!-- 工具栏居左对齐 -->
        <ul class="toolbar1">	<!-- 工具栏居右对齐 -->
        	<li id="deptSearchField" style="display: none;"><input id="deptOrLeaderName" type="text" placeholder="输入系部或系主任名称搜索" /><img id="searchBtn" onclick="searchByMultiCondition()" src="resources/zznueg/img/search.png" /></li>
        	<li id="classSearchField" style="display: none;"><input id="classOrTeachName" type="text" placeholder="输入班级或班主任名称搜索" /><img id="searchBtn" onclick="searchByMultiCondition()" src="resources/zznueg/img/search.png" /></li>
			<li id="stuSearchField" style="display: none;"><input id="tsnoOrName" type="text" placeholder="输入学生学号或姓名搜索" /><img id="searchBtn" onclick="searchByMultiCondition()" src="resources/zznueg/img/search.png" /></li>
		</ul>
    </div>
    <table title="系部-班级-学生信息" class="easyui-datagrid" id="dg"></table>
    
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