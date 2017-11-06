<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<title>领导-数据统计-不同学院成绩通过率</title>
		<tags:include_zznueg/>
		<link rel="stylesheet" type="text/css" href="resources/zznueg/css/leaderDataStatAcaPassRateMain.css" />
		
		<script type="text/javascript">
			$(function() {
				setIframeH();
				layui.use(['element'], function() {
					var form = layui.element();
				});
				loadAcaPassRateOrder();	 //已经加载过初始化饼图，不需要再按下面方法加载（重复加载）  			
				/*//页面加载成功后下拉列表初始选中的选项
				var initSeledValue = $("#acaId option:selected").val();
				var initSeledName = $("#acaId option:selected").attr("name");
				alert("initSeledValue=" + initSeledValue + "\ninitSeledName=" + initSeledName);
				loadPie(initSeledValue, initSeledName); //页面加载时，加载第一条记录的学院综合通过率饼形图 */
				
				//下拉列表改变选中时触发
				$("#acaId").change(function() {
					var selValue = $(this).children("option:selected").val(); //这是selected的值
					var selName = $(this).children("option:selected").attr("name");
					var selText = $(this).children("option:selected").text();
					//alert("selValue=" + selValue + "\nselName=" + selName);
					loadPie(selValue, selName, selText);  //加载选中项目的饼形图
				});
				
				
				//初始化学院下拉框（该种方式不符合需求，暂废弃）
				/* $.ajax({
					url : 'zznueg/portal/leaderplatform/getAcaInfo4Selection',
					method : 'get',
					data : {},
					dataType : "json",
					success : function(data) {
   			        	//alert(data.toString());
   			        	$.each(data, function(index, dataObj) {
   			        		//alert(dataObj.acaId);
   			        		$("#acaId").append('<option value="' + dataObj.acaId + '">' + dataObj.acaName + '</option>');   			        		
                       	});				 
   			        }
				}); */
			});	
			
			/* 加载学院各科目通过率及综合通过率，tablelist数据 */
			function loadSubPassRateTable(acaId) {
				$.ajax({
            		url: "zznueg/portal/leaderplatform/getAcaSubPassRateDataTable",
            		method: "POST",
            		async: false,
            		dataType: "html",
            		data: {"acaId":acaId},
            		success: function(data) {
            			$("#tbSubPassRateDiv").html($(data).html());
            		}
            	});
			}
			
			/* 加载饼形图  */
			function loadPie(subId, acaId, title) {
				$.ajax({
					url: "zznueg/portal/leaderplatform/getAcaSubPassRatePie",
					method: "POST",
				    async:false,	 			     
				    dataType: "json",
				    data:{
				    	"subId":subId,
				    	"acaId":acaId
				    },
				    contentType: "application/json; charset=utf-8",
				    success: function(data) {
				    	if(subId == "01") { //微格通过率
				    		drawPie1(echarts, "main3", data.weige, title, false);	//饼形图；false-不显示原始数据，只显示百分比；true(或null)-显示原始数据和百分比
				    	} else if(subId == "02") { //板书通过率
				    		drawPie1(echarts, "main3", data.banshu, title, false);
				    	} else if(subId == "03") { //教案通过率
				    		drawPie1(echarts, "main3", data.jiaoan, title, false);
				    	} else if(subId == "04") { //课件通过率
				    		drawPie1(echarts, "main3", data.kejian, title, false);
				    	} else { //学院综合通过率
				    		drawPie1(echarts, "main3", data.zong, title, false);
				    	}				    	
				    } 	
				});				
			}
			
			
			/* 点击学院成绩通过率排名列表中的一行时显示该学院的综合通过率及各科目详细通过率信息 */
			function showAcaPassRateInfo(trObj) {
				var acaId = $(trObj).attr("id");
				var acaName = $(trObj).find("#acaName").text();
				//alert("acaName=" + acaName);
				$("#acaId").empty();    //加载前先将下拉列表清空，避免数据重叠追加
				$("#acaId").append('<option value="' + acaId + '" name="' + acaId + '" selected>' + acaName + '通过率</option>');
				$("#acaId").append('<option value="01" name="' + acaId + '">' + acaName + '微格通过率</option>');
				$("#acaId").append('<option value="02" name="' + acaId + '">' + acaName + '板书通过率</option>');
				$("#acaId").append('<option value="03" name="' + acaId + '">' + acaName + '教案通过率</option>');
				$("#acaId").append('<option value="04" name="' + acaId + '">' + acaName + '课件通过率</option>');				
				//获取每次加载不同学院时初始选中的选项内容
				var firstSeledValue = $("#acaId option:selected").val();		//subId
				var firstSeledName = $("#acaId option:selected").attr("name");	//acaId
				var firstSeledText = $("#acaId option:selected").text();		//pie title
				//alert("firstSeledValue=" + firstSeledValue + "\nfirstSeledName=" + firstSeledName);
				loadPie(firstSeledValue, firstSeledName, firstSeledText);	//加载饼形图
				$("#tbTitleDiv").html(acaName + "各科通过率");
				loadSubPassRateTable(acaId);				//加载科目通过率表格
			}
			
			/* 加载学院成绩通过率排名，tablelist数据 */
			function loadAcaPassRateOrder() {
				$.ajax({
            		url: "zznueg/portal/leaderplatform/getAcaPassRateSumGroupbyAcaId",
            		method: "POST",
            		async:false,
            		dataType: "html",
            		data: {},
            		success: function(data) {
            			$("#tbDataDiv").html($(data).html());
            			//alert($("#tbDataDiv tbody tr").eq(0).html());
            			//表格加载成功后触发第一行数据选中“点击”事件
            			$("#tbDataDiv tbody tr").eq(0).trigger("click");
            		}
            	});
			}
			
			/* 设置iframe的高度 */
			function setIframeH() {
				var body = $(document.body);
				var iframe = $(parent.document.getElementById('parentIframe'));
				iframe.height(body.height()*2);
			}
		</script>
	</head>

	<body>
		<div class="average clear">

			<div id="main1">
				<div class="tittle">
					学院成绩通过率排名
				</div>
				<div id="tbDataDiv"></div>				
			</div>
			
			<div id="main2">
				<div class="tittle2">
					<select id="acaId" name="acaName">
						<!-- <option value="外语学院通过率">外语学院通过率</option>
						<option value="">外语学院微格通过率</option>
						<option value="">外语学院教案通过率</option>
						<option value="">外语学院板书通过率</option>
						<option value="">外语学院课件通过率</option> -->
					</select>
				</div>
				<div id="main3"></div>
				
				<div id="tbTitleDiv" style="background-color: #f2f2f2;text-align: center;line-height: 40px;">各科通过率</div>
				<div id="tbSubPassRateDiv"></div>
			</div>
			
		</div>
	</body>

</html>