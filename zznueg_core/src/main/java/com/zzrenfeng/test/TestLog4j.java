package com.zzrenfeng.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog4j {
	
	protected static Logger logger = Logger.getLogger(TestLog4j.class);
/*
	****spring方式加载，配置与web.xml中：****
	Spring加载log4j.properties，它提供了一个Log4jConfigListener，本身就能通过web.xml配置
	从指定位置加载log4j配置文件和log4j的输出路径，要注意的是Log4jConfigListener必须要在Spring的Listener之前。
	web.xml文件配置如下：
	<!-- 设置由Sprng载入的Log4j配置文件位置 -->
	<context-param>
		<param-name>log4jConfigLocation</param-name>
		<param-value>WEB-INF/classes/log4j.properties</param-value>
	</context-param>
	<!-- Spring刷新Log4j配置文件变动的间隔,单位为毫秒 -->
	<context-param>
		<param-name>log4jRefreshInterval</param-name>
		<param-value>10000</param-value>
	</context-param>
	<listener>
		<listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
	</listener>
*/	
	public static void main(String[] args) {
		//通过PropertyConfigurator加载log4j.properties文件；如果不添加这句话，则由spring默认加载，放在源文件目录即可
//		PropertyConfigurator.configure("resources/log4j.properties");
		logger.debug("Debug-测试log4j是否成功");
		logger.info("Info-测试log4j是否成功");
		logger.warn("Warn-测试log4j是否成功");
		logger.error("Error-测试log4j是否成功");
	}

}
