<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>"/>

<script type="text/javascript">
    $(function () {

        $("#form").form({
            url: "zznueg/manage/acainfo/saveOrUpdateAcademy",
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
        width: 170px;
        line-height: 16px;
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
        transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
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
                <legend><img src="resources/core/images/fromedit.png" style="margin-bottom: -3px;"/>学院编辑</legend>
                <input name="acaId" id="acaId" type="hidden"/>
                <input name="status" id="status" type="hidden"/>
                <table>
                    <tr>
                        <th>学院名称</th>
                        <td><input name="acaName" id="acaName" type="text" class="easyui-textbox easyui-validatebox"
                                   required="required"/>
                        </td>                        
                    </tr>
                    <tr>
                    	<th>学院负责人</th>
                        <td><input name="acaLeader" id="acaLeader" type="text" class="easyui-textbox easyui-validatebox" />
                        </td>                         
                    </tr>
                    <tr>
                    	<th>联系方式</th>
                        <td><input name="contact" id="contact" type="text" class="easyui-textbox easyui-validatebox" />
                        </td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td colspan="3"><textarea name="memo" id="memo" class="easyui-textbox"
                                                  style="width: 435px;height: 100px;"></textarea>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
