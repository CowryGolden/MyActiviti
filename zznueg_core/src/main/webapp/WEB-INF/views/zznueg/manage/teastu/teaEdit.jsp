<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>"/>
<script type="text/javascript">
    /**
     * Created by zhoujincheng on 20170802
     */
     $(function () {
    	//增加如下代码后combotree可以正常显示，否则显示为空，对象可以为（#acaId/#acaName 或 #prntId/#prntName）
     	$("#acaId").combotree({
             onSelect: function (node) {
                 $("#acaName").val(node.text);
             }
         });
     	//增加如下代码后combobox可以级联显示，否则显示为空
     	$("#classId").combobox({
             onSelect: function (data) {
                 $("#className").val(data.text);
             }
         });
       
 /*      var tempId = $("#tempId").val();
         if (tempId == "M") {
             $("#prntId").combotree("disable");
         } */
    	 
         $("#form").form({
             url: "zznueg/manage/teastuinfo/saveOrUpdateTeachStu",
             onSubmit: function () {
                 $.messager.progress({
                     title: '提示',
                     text: '数据处理中，请稍后....'
                 });
                 var isValid = $(this).form('validate');
                 if (!isValid) {
                     $.messager.progress('close');
                 }
                 return isValid;
             },
             success: function (result) {
                 $.messager.progress('close');
                 result = eval("(" + result + ")");
                 if (result.status) {
                     //注意这里的datagrid，要和主页面的保持一致
                     $.modalDialog.openner.datagrid('reload');
                     $.modalDialog.handler.dialog('close');
                     $.messager.show({
                         title: result.title,
                         msg: result.message,
                         timeout: 1000 * 2
                     });
                 } else {
                     $.messager.show({
                         title: result.title,
                         msg: result.message,
                         timeout: 1000 * 2
                     });
                 }
             }
         });
     });
</script>

<style>
    .easyui-textbox {
        height: 18px;
        line-height: 16px;
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
        transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
    }

    .easyui-combobox, .easyui-combotree, .easyui-textbox {
        width: 170px;
    }

    textarea:focus, input[type="text"]:focus {
        border-color: rgba(82, 168, 236, 0.8);
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(82, 168, 236, 0.6);
        outline: 0 none;
    }

    table {
        background-color: transparent;
        border-collapse: collapse;
        border-spacing: 0;
        max-width: 100%;
    }

    fieldset {
        border: 0 none;
        margin: 0;
        padding: 0;
    }

    legend {
        -moz-border-bottom-colors: none;
        -moz-border-left-colors: none;
        -moz-border-right-colors: none;
        -moz-border-top-colors: none;
        border-image: none;
        border: 0 none #E5E5E5;
        border-bottom: 1px solid;
        color: #999999;
        line-height: 20px;
        display: block;
        margin-bottom: 10px;
        padding: 0;
        width: 100%;
    }

    input, textarea {
        font-weight: normal;
    }

    table, th, td {
        text-align: left;
        padding: 6px;
    }
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
<%--    <input name="tempId" id="tempId" type="hidden" value="<%=request.getParameter("tempId") %>"/>  --%> 
        <form id="form" method="post">
            <fieldset>
                <legend><img src="resources/core/images/fromedit.png" style="margin-bottom: -3px;"/> 教师编辑
                </legend>
                <input name="id" id="id" type="hidden"/>
                <input name="status" id="status" type="hidden"/>
                <table>
                	<tr>
                		<th>教师工号</th>
                        <td><input name="tsno" id="tsno" placeholder="请输入教师工号" class="easyui-textbox easyui-validatebox"
                                   type="text" required="required"/></td>
                        <th>教师姓名</th>
                        <td><input name="name" id="name" placeholder="请输入教师姓名" class="easyui-textbox easyui-validatebox"
                                   type="text" required="required"/></td>           
                	</tr>
                	<tr>
                        <th>性别</th>
                        <td>
                            <select id="sex" name="sex" class="easyui-combobox" data-options="required:true">
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </td>
                        <th>用户类型</th>
                        <td><select id="userType" name="userType" class="easyui-combobox" data-options="required:true">
                            <option value="T">教师</option>
                            <!-- <option value="S">学生</option> -->
                        </select>
                        </td>
                    </tr> 
                    <tr>
                        <th>所属系部</th>
                        <td>
                            <input name="deptId" id="deptId" class="easyui-combotree"
                            		data-options="
                                   		prompt:'请选择系部',
                                   		url:'zznueg/manage/classinfo/getAcaDeptTreeList',method:'get',
                                   		valueField:'deptId',
                                   		textField:'deptName',
                                   		required:true,
                                   		onSelect: function(node) {
                                   			$('#classId').combobox('clear');
                                   			$('#classId').combobox('reload','zznueg/manage/classinfo/getClassInfoByDeptId/deptId='+node.id)
                                   		}" />
                            <input name="deptName" id="deptName" type="hidden"/>
                        </td>
                        <th>负责班级</th>
                        <td>
                            <input name="classId" id="classId" class="easyui-combobox" 
                            		data-options="
                            	   		prompt:'请先选择系部',
                            	   		valueField:'classId',
                                		textField:'className',
                                		required:true
                            	   " />
                            <input name="className" id="className" type="hidden"/>
                        </td>
                    </tr>
                    
                </table>
            </fieldset>
        </form>
    </div>
</div>
