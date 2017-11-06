<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>"/>
<script type="text/javascript">
    $(function () {

        $("#form").form({
            url: "zznueg/manage/deptinfo/saveOrUpdateDeptInfo",
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
                    $.modalDialog.openner.treegrid('reload');
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
        width: 170px;
        line-height: 16px;
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
        transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
    }

    .easyui-combobox {
        height: 18px;
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
        <form id="form" method="post">
            <fieldset>
                <legend><img src="resources/core/images/fromedit.png" style="margin-bottom: -3px;"/> 系部编辑
                </legend>
                <input name="deptId" id="divId" type="hidden"/>
                <input name="status" id="status" type="hidden"/>
                <table>
                    <tr>
                        <th>系名称</th>
                        <td><input name="deptName" id="deptName" placeholder="请输入系部名称"
                                   class="easyui-textbox easyui-validatebox" type="text" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                    	<th>系主任</th>
                        <td><input name="deptLeader" id="deptLeader" type="text" class="easyui-textbox easyui-validatebox"/>
                        </td>                        
                        <th>联系方式</th>
                        <td><input name="contact" id="contact" type="text" class="easyui-textbox easyui-validatebox"/>
                        </td>                        
                    </tr>
                    <tr>
                        <th>所属学院</th>
                        <td><input id="acaId" name="acaId" type="hidden"/>
                            <input id="acaName" name="acaName" type="text" data-options="readonly:true"
                                   class="easyui-textbox easyui-validatebox"/>
                        </td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td colspan="3"><textarea class="easyui-textbox" name="memo"
                                                  style="width: 420px;height: 100px;"></textarea></td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
