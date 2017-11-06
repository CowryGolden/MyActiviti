<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>系统管理——系统设置</title>
    <tags:include/>   
    <script language="javascript" type="text/javascript" src="<%=basePath%>resources/plugins/My97DatePicker/WdatePicker.js"></script>
    
    <script type="text/javascript">
    	var startDate,endDate; 
    	var chkStartFlag = false;
    	var chkEndFlag = false;
    	//日期格式正则表达式，正确格式为：yyyy-MM-dd HH:mm:ss
    	var reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\s+(2[0-3]|[0-1]\d):[0-5]\d:[0-5]\d$/;  
    	var regExp = new RegExp(reg);
    	
        $(function () {
        	checkStartDate();
        	checkEndDate();
        });
        
        //保存时间设置
        function saveDate() {
        	if(checkStartDate() && checkEndDate()) {
        		//alert("校验通过，允许更新");
        		$.ajax({
					url : "manage/system/saveOrUpdateSysSetting",
					type : "POST",
					data:{
						"startDate" : startDate,
						"endDate" : endDate
					},
					success: function(data) {
						if(data.status) {
							reset();  //提交成功重置表单域							
						} 						
						operTipMsg(data.title, data.message);
					},
					error: function () {
						operTipMsg("提示", "提交出错了！");
                    }
				});
        	} else {
        		alert("校验失败，请检查输入");
        	}
        }
        
        //校验开始日期
        function checkStartDate() {
        	$("#start").datetimebox({
            	onChange : function() {
            		//alert($('#start').datebox('getValue'));
            		startDate = $('#start').datebox('getValue');
            		if(null != startDate && startDate.length > 0) {
            			removeTips("tipInfo");
            			if(!regExp.test(startDate)){
            				showTips("tipInfo", "日期格式不正确，正确格式为：yyyy-MM-dd HH:mm:ss");
            				chkStartFlag = false;
            				return false;
            			} else {
            				removeTips("tipInfo");
            				chkStartFlag = true;
            				return true;
            			}
            			
            		} else {
            			showTips("tipInfo", "开始日期不能为空！");
            			chkStartFlag = false;
            			return false;
            		}
            	}
        	});
        	return chkStartFlag;
        }
        
        //校验结束日期
        function checkEndDate() {
        	$("#end").datetimebox({
            	onChange : function() {
            		startDate = $('#start').datebox('getValue');
            		endDate = $('#end').datebox('getValue');
            		if(null != endDate && endDate.length > 0) {
            			removeTips("tipInfo1");
            			if(!regExp.test(endDate)){
            				showTips("tipInfo1", "日期格式不正确，正确格式为：yyyy-MM-dd HH:mm:ss");
            				chkEndFlag = false;
            				return false;
            			} else {
            				if(startDate > endDate) {
            					showTips("tipInfo1", "开始日期不能大于结束日期");
                				chkEndFlag = false;
                				return false;
            				} else {
            					removeTips("tipInfo1");
            					chkEndFlag = true;
                				return true;
            				}            				
            			}            			
            		} else {
            			showTips("tipInfo1", "结束日期不能为空！");
            			chkEndFlag = false;
            			return false;
            		}
            	}
        	});
        	return chkEndFlag;
        } 
        
        //操作提示划出框
        function operTipMsg(title, msg) {
        	$.messager.show({
                title: title,
                msg: msg,
                timeout: 1000 * 2
            });
        }
        
        //显示提示
        function showTips(divId, text) {
        	$("#"+divId).attr("style","color: red;");
        	$("#"+divId).html(text);
        }
        //清除提示
        function removeTips(divId) {
        	$("#"+divId).removeAttr("style");
        	$("#"+divId).html("");
        }
        
        //重置所有输入域和表单域
        function reset() {
        	removeTips("tipInfo");
        	removeTips("tipInfo1");
        	$("input[type='text']").val("");
        	//$("#form")[0].reset();        	
        }
    </script>
</head>
<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li><a href="javascript:void(0);">系统管理</a></li>
		<li><a href="javascript:void(0);">系统设置</a></li>
	</ul>
</div>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
    <form id="form" method="post">
    	<fieldset>
    		<legend><img src="resources/core/images/fromedit.png" style="margin-bottom: -3px;"/> 评估时间设置</legend>
    		<input name="userId" id="userId" type="hidden"/>
            <input name="status" id="status" type="hidden"/>
            <table>
            	<tr style="height: 35px;">
            		<th>评估开始日期：</th>
            		<td>
            			<!-- <input id="start" name="start" class="Wdate easyui-validatebox" style="height: 30px;"
                        	onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="" type="text" required="required" placeholder="开始日期" /> -->
            			<input id="start" name="start" class="easyui-datetimebox easyui-validatebox" style="height: 30px;"
                        	value="" type="text" required="required" placeholder="开始日期" />
            		</td>
            		<td>
            			<i id="tipInfo"></i>
            		</td>
            	</tr>
            	<tr style="height: 35px;">
            		<th>评估结束日期：</th>
            		<td>
            			<input id="end" name="end" class="easyui-datetimebox easyui-validatebox" style="height: 30px;"
                        	value="" type="text" required="required" placeholder="结束日期" />
            		</td>
            		<td>
            			<i id="tipInfo1"></i>
            		</td>
            	</tr>
            	<tr>
            		<td>
            			<a href="javascript:void(0);" onclick="saveDate()" class="easyui-linkbutton" iconCls="icon-yes">保存</a>
            		</td>
            		<td>
            			<a href="javascript:void(0);" onclick="reset()" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
            		</td>
            	</tr>
            </table>    
    	</fieldset>
    </form>

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