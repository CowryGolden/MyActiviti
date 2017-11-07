package com.zzrenfeng.base.listener;

import java.lang.management.ManagementFactory;
import java.util.Iterator;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.IpUtil;
import com.zzrenfeng.base.utils.SystemBuffer;


/**
 * @功能描述：将用户自定义的MyServletContextListener监听器在Servlet容器进行注册， 
 * 			Servlet容器会在启动或终止 Web应用时，会调用该监听器的相关方法。
 * 			在  web.xml文件中，  <listener>  元素用于向容器注册监听器：
 *			<listener>
 *				<display-name>WebContainerListener</display-name>
 *				<listener-class>com.zzrenfeng.base.listener.WebContainerListener</listener-class>
 *			</listener> 
 *			在Container加载Web应用程序时（例如启动 Container之后），会呼叫contextInitialized()，
 *			而当容器移除Web应用程序时，会呼叫contextDestroyed()方法。 通过 Tomcat控制台的打印结果的
 *			先后顺序，会发现当 Web应用启动时，Servlet容器先调用contextInitialized()方法，再调用
 *			lifeInit的init()方法；当Web应用终止时，Servlet容器先调用lifeInit的destroy()方法，
 *			再调用contextDestroyed()方法。由此可见，在Web应用的生命周期中，ServletContext
 *			对象最早被创建，最晚被销毁。
 *			
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月13日 下午3:11:37
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@WebListener
public class WebContainerListener implements ServletContextListener {
	
	protected static Logger LOGGER = LoggerFactory.getLogger(WebContainerListener.class);

    /**
     * Default constructor. 
     */
    public WebContainerListener() {
        super();
    }

	/**
     * 在Container加载Web应用程序时（例如启动 Container之后），会呼叫contextInitialized()，
     * 再调用lifeInit的init()方法；
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	LOGGER.info("==============WEB容器启动，加载资源开始==============");
        //在此加入容器加载时需要初始化的资源
    	SystemBuffer.init();  //加载系统静态资源
    	doResetJobState();    //重设定时任务状态，以便当WebContainer重启时重新执行定时任务
    }

	/**
     * @see Web应用被卸载时调用如下销毁方法
     * Servlet容器先调用lifeInit的destroy()方法，再调用contextDestroyed()方法。
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	LOGGER.info("==============WEB容器停止，销毁资源开始==============");
    }
    
    /**
     * @功能描述：重设定时任务状态
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月13日 下午3:24:11
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     *
     */
    private void doResetJobState() {    	
		try {
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"), 
										Query.match(Query.attr("protocol"),
										Query.value("HTTP/1.1")));
			String hostname = IpUtil.getLocalIP();
			for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();) {
				ObjectName obj = i.next();
//				String scheme = mbs.getAttribute(obj, "scheme").toString();
				String port = obj.getKeyProperty("port");
				Constants.SERVER_ADDRESS = hostname + ":" + port;
				LOGGER.info("==============SERVER_ADDRESS=============="+hostname + ":" + port);
				//取第一个就行了
				break;
			}
			//在此处加入重置定时任务状态的方法
			LOGGER.info("==============重新设定定时任务状态==============");
			
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
			LOGGER.error("获取服务器信息错误!",e);
		}
		
		
    }
	
}
