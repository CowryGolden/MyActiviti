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
    
        $(function () {
        	// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
        	//var pp = $('#tt').tabs('getSelected');
        	//var tab = pp.panel('options').tab; // 相应的标签页（tab）对象 
        	//var selectorId = pp.attr("id");  //获取选中tab的id
        	//getPage(selectorId, "manage/userRole/userRoleOfView");
<%--  动态增加tabs默认定位到最后一个创建的tab上，前面的tab得手动刷新才能显示数据；这里没有进行严格判断，暂放弃该方式；       	
        	var node1,node2;
        	var title1 = "用户-角色",
        		iconCls1 = "icon-star",
        		url1 = "<%=basePath%>manage/userRole/userRoleOfView";
        	var title2 = "角色-用户",
	    		iconCls2 = "icon-love",
	    		url2 = "<%=basePath%>manage/userRole/roleUserOfView";	
        	node1 = title1 + "||" + iconCls1 + "||" + url1;
        	node2 = title2 + "||" + iconCls2 + "||" + url2;
        	//改由页面加载时动态创建(仿照菜单导航)，不再使用静态方式创建；
        	addTab(node1);
        	addTab(node2); --%>
        	
        	changeTabs();
        });
        
		//增加tab页
        function addTab(node) {
            var nodes = node.split("||");
            if ($("#tt").tabs('exists', nodes[0])) {
            	$("#tt").tabs('select', nodes[0])
            } else {
            	$("#tt").tabs('add', {
                    title: nodes[0],
                    closable: false,
                    iconCls: nodes[1],
                    content: "<iframe src=" + nodes[2] + " frameborder=\"0\" style=\"border:0;width:100%;height:99.4%;\"></iframe>",
                    tools: [{
                        iconCls: 'icon-mini-refresh',
                        handler: function () {
                            refreshTab(nodes[0])
                        }
                    }]
                })
            }
        }
        //刷新tab页
        function refreshTab(title) {
            var tab = $("#tt").tabs('getTab', title);
            $("#tt").tabs('update', {
                tab: tab,
                options: tab.panel('options')
            })
        }        
        
		
        //加载页面数据，废弃暂不用
        function getPage(divId, url) {
    		$.ajax({
        		url: url,
        		method: "POST",
        		async:false,
        		dataType: "html",
        		beforeSend: function () {
        			$.messager.progress({   //数据加载进度条
                        title: '提示',
                        text: '数据加载中，请稍后....'
                    });
        		},
        		success: function(data) {
        			$.messager.progress('close');  //加载完成关闭进度条
        			$("#" + divId).html(data); 
        			//alert("data=" + data);
        		}
        	});
    	}
        //改变iframe的源，重新加载数据
        function reloadIFrame(ifrId, url) {
        	$("#" + ifrId).attr("src", url);
        }
        
        //切换tab页时调用不同的方法获取数据；废弃暂不用
        function changeTabs() {
        	$('#tt').tabs({  //选中某个tabs时触发事件
        		border : false,
        		onSelect : function(title,index) {
        			//alert("index[" + index + "]=" + (index == '1'));  
        			//这里可以使用switch根据tab的index判断，也可以直接使用选中tab的id进行判断
        			switch (index) {
        				case 0 :
        					//getPage("userRoleTabs", "manage/userRole/userRoleOfView");
        					reloadIFrame("ifrUserRole", "<%=basePath%>manage/userRole/userRoleOfView");
        					break;
        				case 1 :
        					//getPage("roleUserTabs", "manage/userRole/roleUserOfView");
        					reloadIFrame("ifrRoleUser", "<%=basePath%>manage/userRole/roleUserOfView");
        					break;
        				default:
        					//getPage("userRoleTabs", "manage/userRole/userRoleOfView");
        					reloadIFrame("ifrUserRole", "<%=basePath%>manage/userRole/userRoleOfView");
        			} 
        			/* //也可以直接使用选中tab的id进行判断
        			var selectorId = $('#tt').tabs('getSelected').attr("id");
        			if(selectorId == "userRoleTabs") {
        				getPage("userRoleTabs", "manage/userRole/userRoleOfView");
        			} else {
        				alert("roleUserTabs");
        			} */
        		}
        	});
        }
        
        
    </script>
</head>

<body class="easyui-layout">

<div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
	<!-- 这里仍使用静态方式创建；页面加载时动态创建默认定位在最后一个tab，前面的tab得手动刷新才能显示数据 -->	
	<div id="userRoleTabs" title="用户-角色" data-options="iconCls:'icon-star',closable:false,tools:[{iconCls:'icon-mini-refresh',handler:function(){refreshTab('用户-角色')} }]" style="padding:20px;display:none;">
		<iframe id="ifrUserRole" src="<%=basePath%>manage/userRole/userRoleOfView" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>
	</div>
	<div id="roleUserTabs" title="角色-用户" data-options="iconCls:'icon-love',closable:false,tools:[{iconCls:'icon-mini-refresh',handler:function(){refreshTab('角色-用户')} }]" style="overflow:auto;padding:20px;display:none;">
		<iframe id="ifrRoleUser" src="<%=basePath%>manage/userRole/roleUserOfView" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>
	</div>
<!-- 	
	<div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">
		tab3
	</div> -->	
</div>

</body>
</html>
