package com.zzrenfeng.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * @功能描述：通过实现接口ApplicationContextAware，来获取Spring容器初始化时注入的ApplicationContext对象，从而获取实例化的beans
 * 			本类采用第五种方案实现！！！		
 * 	
 * 			**** 总结：java类获取Spring容器实例化bean的5中方法 ****
 * 			第一种：在初始化时保存ApplicationContext对象
 * 				eg:		ApplicationContext ac = new FileSystemXmlApplicationContext("applicationContext.xml");
 * 						ac.getBean("beanId");
 * 				说明：这种方式适用于采用Spring框架的独立应用程序，需要程序通过配置文件手工初始化Spring。
 * 			第二种：通过Spring提供的工具类获取ApplicationContext对象
 * 				eg:		import org.springframework.web.context.support.WebApplicationContextUtils;
 *						ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletContext sc);
 *						ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(ServletContext sc);
 *						ac1.getBean("beanId");
 *						ac2.getBean("beanId");
 *				说明：
 *					1、这两种方式适合于采用Spring框架的B/S系统，通过ServletContext对象获取ApplicationContext对象，然后在通过它获取需要的类实例；
 *					2、第一种方式在获取失败时抛出异常，第二种方式返回null。
 *			第三种：继承自抽象类ApplicationObjectSupport
 *				说明：通过抽象类ApplicationObjectSupport提供的getApplicationContext()方法可以方便的获取到ApplicationContext实例，
 *					进而获取Spring容器中的bean。Spring初始化时，会通过该抽象类的setApplicationContext(ApplicationContext context)方法将ApplicationContext 对象注入。
 *			第四种：继承自抽象类WebApplicationObjectSupport
 *				说明：和上面方法类似，通过调用getWebApplicationContext()获取WebApplicationContext实例；
 *			第五种：实现接口ApplicationContextAware
 *				说明：实现该接口的setApplicationContext(ApplicationContext context)方法，并保存ApplicationContext对象。Spring初始化时，会通过该方法将ApplicationContext对象注入。
 *				虽然Spring提供了后三种方法可以实现在普通的类中继承或实现相应的类或接口来获取Spring的ApplicationContext对象，
 *				但是在使用时一定要注意继承或实现这些抽象类或接口的普通java类一定要在Spring的配置文件（即application-context.xml文件）中进行配置，否则获取的ApplicationContext对象将为null。
 *			
 *			特别注意：在服务器启动Spring容器初始化时，不能通过以下方法获取Spring容器:
 *				import org.springframework.web.context.ContextLoader; 
 *				import org.springframework.web.context.WebApplicationContext;
 *				WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext(); 
 *				wac.getBean(beanID);
 * 	
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月15日 上午11:18:24
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class SpringConfigTool implements ApplicationContextAware{	// extends ApplicationObjectSupport{
	
	private static ApplicationContext applicationContext = null;
	private static SpringConfigTool springConfigTool = null;
	
	public synchronized static SpringConfigTool init() {
		if(null == springConfigTool) {
			springConfigTool = new SpringConfigTool();
		}
		return springConfigTool;
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		applicationContext = appContext;
	}
	
	public synchronized static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
}
