<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>"/>

<style type="text/css">
	.weather,.timer {
		cursor: pointer;
	}
	.weatherForcast {
		position: absolute;
		width: 500px;	/* 简要预报信息1,2 div宽度-240px；简要预报信息3 div宽度-500；多功能预报信息div宽度-1000px; */
		height: 120px;	/* 简要预报信息1,2 div高度-100px；简要预报信息3 div高度-120；多功能预报信息div高度-800px; */
		background: #EDEDED;
	}
	/* 时钟网页定位 */
	.clock {
		position: absolute;
		/* position: fixed;
		top: 230px;
		right: 70px; */		/*要进行页面div绝对定位时打开注释*/
		background: #EDEDED;
	}	
}
	
</style>

<script type="text/javascript" charset="utf-8">
    var centerTabs;
    var tabsMenu;
    $(function () {
    	/****************Html5网络时钟定义****************/
    	var clock = $("#clock").clock({		//获取时钟对象，并设置时钟属性
    			width: 120,
    			height: 176,
    			theme: 't3'		//主题：t1-橙蓝灰；t2-青灰绿；t3-红灰黄
    	});	
		var data = clock.data('clock');		//获取始终控制数据对象
    	//data.pause();		//时钟暂停	
    	//data.start();		//时钟开始
    	//data.setTime(new Date());	//始终重新设定时间
    	/***********************************************/
    	
        tabsMenu = $('#tabsMenu').menu({
            onClick: function (item) {
                var curTabTitle = $(this).data('tabTitle');
                var type = $(item.target).attr('type');
                if (type === 'refresh') {
                    refreshTab(curTabTitle);
                    return;
                }
                if (type === 'close') {
                    var t = centerTabs.tabs('getTab', curTabTitle);
                    if (t.panel('options').closable) {
                        centerTabs.tabs('close', curTabTitle)
                    }
                    return;
                }
                var allTabs = centerTabs.tabs('tabs');
                var closeTabsTitle = [];
                $.each(allTabs,
                        function () {
                            var opt = $(this).panel('options');
                            if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
                                closeTabsTitle.push(opt.title)
                            } else if (opt.closable && type === 'closeAll') {
                                closeTabsTitle.push(opt.title)
                            }
                        });
                for (var i = 0; i < closeTabsTitle.length; i++) {
                    centerTabs.tabs('close', closeTabsTitle[i])
                }
            }
        });
        centerTabs = $('#centerTabs').tabs({
            tools: [{
                iconCls: 'icon-reload',
                handler: function () {
                    var href = $('#centerTabs').tabs('getSelected').panel('options').href;
                    if (href) {
                        var index = $('#centerTabs').tabs('getTabIndex', $('#centerTabs').tabs('getSelected'));
                        $('#centerTabs').tabs('getTab', index).panel('refresh')
                    } else {
                        var panel = $('#centerTabs').tabs('getSelected').panel('panel');
                        var frame = panel.find('iframe');
                        try {
                            if (frame.length > 0) {
                                for (var i = 0; i < frame.length; i++) {
                                    frame[i].contentWindow.document.write('');
                                    frame[i].contentWindow.close();
                                    frame[i].src = frame[i].src
                                }
                                if ($.browser.msie) {
                                    CollectGarbage()
                                }
                            }
                        } catch (e) {
                        }
                    }
                }
            },
            {
                iconCls: 'icon-no',
                handler: function () {
                    var index = $('#centerTabs').tabs('getTabIndex', $('#centerTabs').tabs('getSelected'));
                    var tab = $('#centerTabs').tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        $('#centerTabs').tabs('close', index)
                    } else {
                        $.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭', 'error')
                    }
                }
            },
            {
                iconCls: 'icon-color',
                handler: function () {
                    $('#theme').menu({
                        onClick: function (item) {
                            var cookiesColor1 = jqueryUtil.cookies.get("cookiesColor");
                            if (cookiesColor1 != item.id) {
                                jqueryUtil.cookies.set("cookiesColor", item.id, 30);
                                jqueryUtil.chgSkin(item.id, cookiesColor1)
                                //changeTheme(item.id);
                            }
                        }
                    });
                    $('#theme').menu('show', {
                        left: '91%',
                        top: 97
                    })
                }
            }],
            fit: true,
            border: false,
            onContextMenu: function (e, title) {
                e.preventDefault();
                tabsMenu.menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data('tabTitle', title)
            }
        });        
		
        //首页问候语
        getGreetings(); 
        //var timer = setTimeout("getGreetings()",2000);  //前置代码执行超时时，被跳过，来执行此段代码;重启服务器清除浏览器缓存才生效
        //setInterval("test()",2000);  //前置代码执行超时时，不被跳过，待前置执行完后再执行此段代码;重启服务器清除浏览器缓存才生效
        //window.clearTimeout(timer);
        
    });
    
    function addTab(node) {
        var nodes = node.split("||");
        if (centerTabs.tabs('exists', nodes[0])) {
            centerTabs.tabs('select', nodes[0])
        } else {
            centerTabs.tabs('add', {
                title: nodes[0],
                closable: true,
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
    
    function refreshTab(title) {
        var tab = centerTabs.tabs('getTab', title);
        centerTabs.tabs('update', {
            tab: tab,
            options: tab.panel('options')
        })
    }
    
    /** 
     * 更换EasyUI主题的方法 
     * @param themeName 主题名称 
     * 在resources/check/js/jqueryUtil.js中已经存在“切换皮肤”的方法：jqueryUtil.chgSkin(selectId, cookiesColor)
     * 在line:126已经调用切换皮肤的方法；该方法可以在line:127使用，也可以在line:310-317各个主题标签上使用；
     */
	//function changeTheme(themeName) {
    changeTheme = function(themeName) {  
        var $easyuiTheme = $('#easyuiTheme');  
        var url = $easyuiTheme.attr('href'); 
        //var href = url.substring(0, url.indexOf('public'));
        var href;
        if(themeName == 'default' || themeName == '' || themeName == null || themeName == 'null') {
        	//href += 'public/css/easyui.css';
        	href = '<%=basePath%>' + 'resources/public/css/easyui.css';
        } else {
        	//href += 'themes/' + themeName + '/easyui.css'; 
        	href = '<%=basePath%>' + 'resources/themes/' + themeName + '/easyui.css';
        }
        
        $easyuiTheme.attr('href', href);  
        var $iframe = $('iframe');  
        if ($iframe.length > 0) {  
            for ( var i = 0; i < $iframe.length; i++) {  
                var ifr = $iframe[i];
                if(JSON.stringify(ifr) != '{}') {
                	$(ifr).contents().find('#easyuiTheme').attr('href', href);
                }                  
            }  
        }
        
        $.cookie('easyuiThemeName', themeName, {
        	//记住cookie的天数
            expires : 7  
        });  
    }; 
	//下边的if条件是必须的，如果没有的话，改变主题后点击弹出别的iframe之后主题还是原来的。
	//cookiesColor为line:127设置的cookie名；增加以下代码后，切换主题后再刷新页面可以保持住不被替换为原来的；
    if($.cookie('cookiesColor')){  
		changeTheme($.cookie('cookiesColor'));  
	}
    
    
    //获取问候语
    function getGreetings() {
    	var now = new Date(), hour = now.getHours();
    	if (hour < 4) {
    		$("#greetings").html("深夜好！");
    	} else if (hour < 6) {
			$("#greetings").html("凌晨好！");
		} else if (hour < 9) {
			$("#greetings").html("早上好！");
		} else if (hour < 12) {
			$("#greetings").html("上午好！");
		} else if (hour < 14) {
			$("#greetings").html("中午好！");
		} else if (hour < 17) {
			$("#greetings").html("下午好！");
		} else if (hour < 19) {
			$("#greetings").html("傍晚好！");
		} else if (hour < 22) {
			$("#greetings").html("晚上好！");
		} else {
			$("#greetings").html("夜里好！");
		}
		//window.location.reload(); 
	}
    
    //显示Html5时钟，鼠标悬停在图标上时调用
    function showHtml5Click() {
    	$("#clock").show();
    }
    //隐藏Html5时钟，鼠标移除在图标上时调用
    function hideHtml5Click() {
    	$("#clock").hide();
    }
    //显示天气，鼠标悬停在图标上时调用
    function showWeather() {
    	$("#weatherForcast").show();
    }
    //隐藏天气，鼠标移除在图标上时调用
    function hideWeather() {
    	$("#weatherForcast").hide();
    }
</script>
<div id="centerTabs">
    <div iconCls="icon-home" title="首页" border="false" style="overflow: hidden;">
        <div class="mainindex">
            <div class="welinfo">
				<span class="weather" id="weather" onmouseover="showWeather();" onmouseout="hideWeather();">
					<img src="resources/core/images/sun.png" alt="天气"/></span> 
				<b>${shiroUser.userName} <label id="greetings">早上好</label>，欢迎使用评估管理信息系统</b>(${shiroUser.userEmail})
                <a href="javascript:void(0)">帐号设置</a>
            </div>
            <div class="weatherForcast" id="weatherForcast" style="display: none;" onmouseover="showWeather();" onmouseout="hideWeather();">
				<!-- <iframe id="fancybox-frame" name="fancybox-frame1506673363514" frameborder="0" scrolling="no" hspace="0" src="http://i.tianqi.com/index.php?c=code&a=getcode&id=19&h=93&w=345"></iframe> --> <!-- 简要预报信息1 -->
				<!-- <iframe id="fancybox-frame" name="fancybox-frame1506673781209" frameborder="0" scrolling="no" hspace="0"  src="http://i.tianqi.com/index.php?c=code&a=getcode&id=7&h=90&w=225"></iframe> --> <!-- 简要预报信息2 -->
				<iframe width="500" scrolling="no" height="120" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=19&icon=1&py=zhengzhou&temp=1&num=3&site=12"></iframe>  <!-- 简要预报信息3 -->
				<%-- <iframe id="multiWeather" name="multiWeather" width="1000px" height="800px" frameborder="0" scrolling="no" hspace="0"  src="<%=basePath%>resources/plugins/WeatherForcast/index.html"></iframe> --%> <!-- 多功能预报信息 -->
			</div>
            <div class="welinfo">
				<span class="timer" id="timer" onmouseover="showHtml5Click();" onmouseout="hideHtml5Click();">
					<img src="resources/core/images/time.png" alt="时间"/></span>
				<i>您上次登录的时间：${shiroUser.prevLogin}</i> 
				<i>IP地址：${shiroUser.prevIp}</i> 	  
				（不是您登录的？<a href="javascript:void(0)">请点这里</a>）
            </div>
            <div class="clock" id="clock" style="display: none;"></div>	<!-- html5时钟域 -->            
            <div class="xline"></div>
            <div class="box"></div>
            <div class="welinfo">
				<span><img src="resources/core/images/dp.png" alt="提醒"/></span> <b>信息管理系统介绍</b>
            </div>
            <ul class="infolist">
                <li><span>JDK版本:1.8</span></li>
                <li><span>主体技术:SpringMVC+Spring+MyBatis+Shiro+EasyUI+Druid+HTML5/CSS3</span></li>
                <li><span>Shiro提供给您细分到按钮的权限管理，更安全，更可靠</span></li>
                <li><span>SpringMVC提供RESTFUL访问地址</span></li>
                <li><span>EasyUI统一的后台管理页面风格</span></li>
                <li><span>Druid强大的数据库管理工具，在监控、可扩展性、稳定性和性能方面都有明显的优势</span></li>
                <li><span>Mybatis灵活使用的数据库操作纽带</span></li>
                <li><span>HTML5+css3.0界面UI，美观漂亮时尚、前沿</span></li>
                <li><span>Quartz定时任务处理</span></li>
            </ul>
        </div>
    </div>
</div>
<div id="tabsMenu" style="width: 120px; display: none;">
    <div type="refresh">刷新</div>
    <div class="menu-sep"></div>
    <div type="close">关闭</div>
    <div type="closeOther">关闭其他</div>
    <div type="closeAll">关闭所有</div>
</div>
<div id="theme" class="easyui-menu" style="width: 120px; display: none">
    <div id="default" data-options="iconCls:'icon-save'">default</div>  <!--  onclick="changeTheme('default');" -->
    <div id="black" data-options="iconCls:'icon-save'">black</div>  <!--  onclick="changeTheme('black');" -->
    <div id="bootstrap" data-options="iconCls:'icon-save'">bootstrap</div>  <!--  onclick="changeTheme('bootstrap');" -->
    <div id="gray" data-options="iconCls:'icon-save'">gray</div>  <!--  onclick="changeTheme('gray');" -->
    <div id="metro" data-options="iconCls:'icon-save'">metro</div>  <!--  onclick="changeTheme('metro');" -->
    <div id="metro-green" data-options="iconCls:'icon-save'">metro-green</div>  <!--  onclick="changeTheme('metro-green');" -->
    <div id="metro-orange" data-options="iconCls:'icon-save'">metro-orange</div>  <!--  onclick="changeTheme('metro-orange');" -->
    <div id="metro-red" data-options="iconCls:'icon-save'">metro-red</div>  <!--  onclick="changeTheme('metro-red');" -->
</div>