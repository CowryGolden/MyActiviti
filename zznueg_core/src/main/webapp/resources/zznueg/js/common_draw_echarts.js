
	/**
	 * Created by zhoujincheng 
	 * 
	 * 实现类似jsp中<%=basePath%>的功能，获取项目根目录有助于url的填写
	 * 
	 * 使用方法，用类似引用jQuery的方法引入本插件
	 * 
	 * 定义一个全局的变量，然后的ready方法内部掉用本方法，获得basePath
	 */

	 function basePath(){
	    //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
	    var curWwwPath = window.document.location.href;
	    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
	    var pathName = window.document.location.pathname;
	    var pos = curWwwPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8080
	    var localhostPath = curWwwPath.substring(0, pos);
	    //获取带"/"的项目名，如：/ems
	    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	    //获取项目的basePath   http://localhost:8080/ems/
	    var basePath=localhostPath+projectName+"/";
	    return basePath;
	};
	//========================================统计图公用开始===============================	
	//var colorDefault = ['#4596e5', '#2ec7c9', '#b6a2de', '#5c9ded', '#36bd9b', '#22b7e5', '#7266bb', '#fe9331'];
	var colorDefault = ['#4596e5', '#22b7e5', '#b6a2de', '#fe9331', '#36bd9b', '#7266bb', '#5c9ded'];
	
	/**
	 * 柱状图画图封装工具类
	 * @param ec		echarts对象
	 * @param drawDivId	画图div域ID
	 * @param data		json对象数据
	 * @param title		图表标题
	 * @param align		标题对齐方式（null/left或者default为左对齐，center居中，right右对齐）
	 */
	function drawBar(ec, drawDivId, data, title, align) {
		var color = colorDefault;
		var myChart = ec.init(document.getElementById(drawDivId));
		var option = {
				color: color,
				title: {
					text: title
				},
				tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend : {
	                data : []
	            },
			    xAxis : [
			        {
			        	type : 'category',
			        	data : []
		            }
		        ],
		        yAxis : [
	                {
	                    type : 'value'
	                }
	            ],
	            series : []
		};
		
		if(align == null || align == "default" || align == "left") {
			option.title.left = "left";	//标题居左
			option.legend.y = "top";	//图例顶端居中
		} else if(align == "center") {
			option.title.left = "center";//标题居中
			option.legend.y = "bottom";	//图例顶端居中
			option.tooltip.axisPointer.type = "cross";  //坐标轴指示器，为“十字”交叉样式
		} else if (align == "right") {
			option.title.left = "right";	//标题居右
			option.legend.y = "top";	//图例顶端居中
		}
		
		var jsonobj = data;
        //给图例序列标题赋值
        option.legend.data = jsonobj.legend;
        //读取横坐标值
        option.xAxis[0].data = jsonobj.axis;
        var series_arr = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
            option.series.push(series_arr[i]);
        } 
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
        // 为echarts对象加载数据
        myChart.setOption(option); 
	}
	
	/**
	 * @desc 堆叠柱状图画图封装工具类
	 * @autor zjc
	 * @date 20170904 15:21
	 * @param ec		echarts对象
	 * @param drawDivId	画图div域ID
	 * @param data		json对象数据
	 */
	function drawStackBar(ec, drawDivId, data) {
		var color = colorDefault;
		var myChart = ec.init(document.getElementById(drawDivId));
		var option = {
				color: color,
				/*title: {
					text: title
				},*/
				tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend : {
	                data : []
	            },
	            grid: {
					left: '3%',
					right: '4%',
					bottom: '3%',
					containLabel: true
				},
		        xAxis : [
	                {
	                    type : 'value'
	                }
	            ],
			    yAxis : [
			        {
			        	type : 'category',
			        	data : []
		            }
		        ],
	            series : []
		};
		
		var jsonobj = data;
        //给图例序列标题赋值
        option.legend.data = jsonobj.legend;
        //读取横坐标值
        option.yAxis[0].data = jsonobj.axis;
        var series_arr = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
            //option.series.push(series_arr[i]);
        	option.series.push({         // 驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据
                name: series_arr[i].name,
                type: series_arr[i].type,
                stack: '总量',
                data: series_arr[i].data,
                //图形样式，可设置图表内图形的默认样式和强调样式（悬浮时样式）：
                label: {
					normal: {
						show: true,
						position: 'insideRight'
					}
				} 
            });
        } 
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
        // 为echarts对象加载数据
        myChart.setOption(option); 
	}
	
	
	
	/**
	 * 雷达图画图公用类（标准雷达图）
	 * @param ec		echarts对象
	 * @param drawDivId	画图div域ID
	 * @param data		json对象数据
	 * @param title		图表标题
	 */
	function drawRadar(ec, drawDivId, data,title) {
		var color = colorDefault;
		var myChart = ec.init(document.getElementById(drawDivId));
		var option = { //可以去官网上根据每个案例不同的option去写各种图形
				color: color,
				title: {   //标题
                    text: title,
                    x: 'center'
                },
                tooltip: {   //提示框，鼠标悬浮交互时的信息提示
                    //trigger: 'axis'
                },
                legend: {    //图例，每个图表最多仅有一个图例
                	x: 'left',
                    y: '7%',
                    data: []
                },
                radar: [{    //雷达坐标，指示器 
                    indicator: []
                }],
                series: []
        };
		
		var jsonobj = data;
        //给图标标题赋值（在jsp页面中赋值，不再从后台Service中赋值）
       	//option.title.text = jsonobj.titletext;
        option.radar[0].indicator = jsonobj.indicatorData;
        option.legend.data = jsonobj.legend;
        var series_arr = jsonobj.seriesList;
        //option.series = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
            //option.series.push(jsonobj.seriesList[i]);
        	//console.log(series_arr[i].name);
        	//console.log(series_arr[i].type);
        	//console.log(series_arr[i].data);
        	option.series.push({         // 驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据
                name: series_arr[i].name,
                type: series_arr[i].type,
                data: series_arr[i].data,
                itemStyle:{//图形样式，可设置图表内图形的默认样式和强调样式（悬浮时样式）：
                    normal: {
                        areaStyle: {
                            type: 'default'
                        }
                    }
                }
            });
        }
        
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
        // 为echarts对象加载数据
        myChart.setOption(option); 
    }
	
	
	/**
	 * 无标题雷达图画图公用类（标准雷达图，图例在顶部居中）
	 * @param ec		echarts对象
	 * @param drawDivId	画图div域ID
	 * @param data		json对象数据
	 */
	function drawRadarNoTitle(ec, drawDivId, data) {
		var color = colorDefault;
		var myChart = ec.init(document.getElementById(drawDivId));
		var option = { //可以去官网上根据每个案例不同的option去写各种图形
				color: color,
                tooltip: {   //提示框，鼠标悬浮交互时的信息提示
                    //trigger: 'axis'
                },
                legend: {
                    data: []
                },
                radar: [{    //雷达坐标，指示器 
                    indicator: []
                }],
                series: []
        };
		
		var jsonobj = data;
        option.radar[0].indicator = jsonobj.indicatorData;
        option.legend.data = jsonobj.legend;
        var series_arr = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
        	option.series.push({         // 驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据
                name: series_arr[i].name,
                type: series_arr[i].type,
                data: series_arr[i].data,
                itemStyle:{//图形样式，可设置图表内图形的默认样式和强调样式（悬浮时样式）：
                    normal: {
                        areaStyle: {
                            type: 'default'
                        }
                    }
                }
            });
        }        
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
        // 为echarts对象加载数据
        myChart.setOption(option); 
    }
	
	
	/**
	 * 雷达图画图公用类（无维度指示器）
	 * @param ec		echarts对象
	 * @param drawDivId	画图div域ID
	 * @param data		json对象数据
	 * @param title		图表标题
	 */
	function drawRadarNoDim(ec, drawDivId, data, title) {
		var color = colorDefault;
		var myChart = ec.init(document.getElementById(drawDivId));
		var option = { //可以去官网上根据每个案例不同的option去写各种图形
				color: color,
				title: {   //标题
                    text: title,
                    x: 'center'
                },
                tooltip: {   //提示框，鼠标悬浮交互时的信息提示
                    show: true,
                    trigger: 'axis'
                },
                legend: {    //图例，每个图表最多仅有一个图例
                    x: 'left',
                    y: '7%',
                    data: []
                },
                polar: [{    //极坐标 
                    indicator: [],
                    radius: 100,      
                    startAngle: 120   // 改变雷达图的旋转度数
                }],
                series: []
        };
		
		var jsonobj = data;
        //给图标标题赋值（在jsp页面中赋值，不再从后台Service中赋值）
       	//option.title.text = jsonobj.titletext;
        option.polar[0].indicator = jsonobj.indicatorData;
        option.legend.data = jsonobj.legend;
        var series_arr = jsonobj.seriesList;
        //option.series = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
            //option.series.push(jsonobj.seriesList[i]);
        	console.log(series_arr[i].name);
        	console.log(series_arr[i].type);
        	console.log(series_arr[i].data);
        	option.series.push({         // 驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据
                name: series_arr[i].name,
                type: series_arr[i].type,
                data: series_arr[i].data,
                itemStyle:{//图形样式，可设置图表内图形的默认样式和强调样式（悬浮时样式）：
                    normal: {
                        areaStyle: {
                            type: 'default'
                        }
                    }
                }
               
            });
        }
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
        // 为echarts对象加载数据
        myChart.setOption(option); 
    }
	
	
	
	/**
	 * 折线图画图公用类
	 * @param ec		echarts对象
	 * @param drawDivId	画图div域ID
	 * @param data		json对象数据
	 * @param title		图表标题
	 */
	function drawLine(ec, drawDivId, data, title) {
        var myChart = ec.init(document.getElementById(drawDivId));
        var option = {
    		color: colorDefault,
			title: {
				text: title,
				left: "center"
			},	
            tooltip : {
            	trigger: 'axis', //弹框的显示与否
				axisPointer: {
					type: 'cross'
				}
            },
            legend : {
            	left: 'right',
                data : []
            },
            xAxis : [ {
                type : 'category',
                data : [],
                axisLabel:{
                    interval:0 
                }
            }],
            yAxis : [ {
                type : 'value'
            } ],
            series : []
        };
        
        var jsonobj = data;
        //给图标标题赋值
        option.legend.data = jsonobj.legend;
        //读取横坐标值
        option.xAxis[0].data =jsonobj.axis;
        var series_arr = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
            option.series.push(series_arr[i]);
        } 
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
         // 为echarts对象加载数据
        myChart.setOption(option); 
    }
	
	/**
	 * 无标题折线图（图例居中）画图公用类
	 * @param ec		echarts对象
	 * @param drawDivId	画图div域ID
	 * @param data		json对象数据
	 * @param title		图表标题
	 */
	function drawLineNoTitle(ec, drawDivId, data) {
        var myChart = ec.init(document.getElementById(drawDivId));
        var option = {
    		color: colorDefault,
			/*title: {
				text: title,
				left: "center"
			},*/	
            tooltip : {
            	trigger: 'axis', //弹框的显示与否
				axisPointer: {
					type: 'cross'
				}
            },
            legend : {
            	left: 'center',
            	y: '0',
                data : []
            },
            xAxis : [ {
            	type : 'category',
                data : [],
                axisLabel:{
                	interval:0 
            	}
            }],
            yAxis : [ {
                type : 'value'
            } ],
            series : []
        };
        
        var jsonobj = data;
        //给图标标题赋值
        option.legend.data = jsonobj.legend;
        //读取横坐标值
        option.xAxis[0].data =jsonobj.axis;
        var series_arr = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
            option.series.push(series_arr[i]);
        } 
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
         // 为echarts对象加载数据
        myChart.setOption(option); 
    }
	
	/**
	 * 饼状图画图公用类
	 * @param ec
	 * @param drawid
	 * @param data
	 */
	function drawPie(ec, drawDivId, data) {
        var myChart = ec.init(document.getElementById(drawDivId));
        var option = {
        		color: colorDefault,  
        		/*title : {
        	        text: '销售额（万元）',
        	        //subtext: '纯属虚构',
        	        x:'center',
        	        y: 'top'
        	    },*/
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    /*legend: {
        	        orient: 'vertical',
        	        x: 'left',
        	        y:"7%",
        	        data: []
        	    },*/
        	    series : [] 
        	};
        
       var jsonobj = data;
        //给图例及标题赋值
        //option.legend.data = jsonobj.legend;
    	//option.title.text = jsonobj.titletext;
        var series_arr = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
            //option.series.push(jsonobj.seriesList[i]);
        	option.series.push({
                name:series_arr[i].name,
                type:series_arr[i].type,
                radius : '55%',
                center: ['50%', '55%'],
                data:series_arr[i].data,
                itemStyle:{
                	emphasis: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
                }
            })
        }
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
        // 为echarts对象加载数据
        myChart.setOption(option); 
    }
	
	/**
	 * 饼状图画图公用类，根据参数isShowValue判断是否显示原始数据
	 * @param ec
	 * @param drawDivId
	 * @param data
	 * @param title
	 * @param isShowValue false-不显示原始数据，只显示百分比；true(或null)-显示原始数据和百分比
	 */
	function drawPie1(ec, drawDivId, data, title, isShowValue) {
        var myChart = ec.init(document.getElementById(drawDivId));
        var option = {
        		color: colorDefault,
        		title : {
	    	        text: title,
	    	        //subtext: '纯属虚构',  //副标题
	    	        x:'center',
	    	        y: 'top'
	    	    },
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    series : [] 
        	};
        
        if(isShowValue == null || isShowValue == "default" || isShowValue == true || isShowValue == "true") {
			option.tooltip.formatter = "{a} <br/>{b} : {c} ({d}%)";	//格式化提示标签数据，显示原始数据和百分比
		} else if(isShowValue == false || isShowValue == "false") {
			option.tooltip.formatter = "{a} <br/>{b} : {d}%";	//格式化提示标签数据，不显示原始数据，只显示百分比
		} else {
			option.tooltip.formatter = "{a} <br/>{b} : {c} ({d}%)";
		}
        
        var jsonobj = data;
        //给图例及标题赋值
        //option.legend.data = jsonobj.legend;
    	//option.title.text = jsonobj.titletext;
        var series_arr = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
            //option.series.push(jsonobj.seriesList[i]);
        	option.series.push({
                name:series_arr[i].name,
                type:series_arr[i].type,
                radius : '55%',
                center: ['50%', '60%'],
                data:series_arr[i].data,
                itemStyle:{
                	emphasis: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
                }
            })
        }
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
        // 为echarts对象加载数据
        myChart.setOption(option); 
    }
	
	/**
	 * 饼状环形图画图公用类
	 * @param ec
	 * @param drawid
	 * @param data
	 */
	function drawHPie(ec, drawid, data) {
        var myChart = ec.init(document.getElementById(drawid));
        var option = {
        	    title : {
        	        text: '销售额（万元）',
        	        //subtext: '纯属虚构',
        	        x:'center',
        	        y: 'top'
        	    },
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b}: {c} ({d}%)"
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        x: 'left',
        	        y:"7%",
        	        data: []
        	    },
        	    series : [] 
        	};
        
        
       var jsonobj = data;
        //给图标标题赋值
       	option.title.text = jsonobj.titletext;
        option.legend.data = jsonobj.legend;
        var series_arr = jsonobj.seriesList;
        //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
        for (var i = 0; i < series_arr.length; i++) {
            //option.series.push(jsonobj.seriesList[i]);
        	option.series.push({
                name:jsonobj.seriesList[i].name,
                type:jsonobj.seriesList[i].type,
                radius: ['50%', '70%'],
                center: ['50%', '55%'],
                avoidLabelOverlap: false,
                data:jsonobj.seriesList[i].data,
                label: {
                    normal: {
                        show: true,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '15',
                            fontWeight: 'bold'
                        }
                    }
                },
        	 	labelLine: {
                 normal: {
                     show: false
                 }
        	 	}
               
            })
        }
        //过渡控制，隐藏loading（读取中）
        myChart.hideLoading();
        // 为echarts对象加载数据
        myChart.setOption(option); 
    }
		

    
//    'echarts/chart/bar': chart_path,  
//    
//    'echarts/chart/pie': chart_path,  
//
//    'echarts/chart/line': chart_path,  
//
//    'echarts/chart/k': chart_path,  
//
//    'echarts/chart/scatter': chart_path,  
//
//    'echarts/chart/radar': chart_path,  
//
//    'echarts/chart/chord': chart_path,  
//
//    'echarts/chart/force': chart_path,  
//
//    'echarts/chart/map': map_path
	//========================================统计图公用结束===============================



























