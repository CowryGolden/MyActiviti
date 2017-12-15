package com.zzrenfeng.activiti.test;

import static org.junit.Assert.*;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * @功能描述：测试流程运行过程
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月14日 下午3:41:47
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)	//使用JUnit4进行测试，用于指定junit运行环境，是junit提供给其他框架测试环境接口扩展，为了便于使用spring的依赖注入
@ContextConfiguration(locations = {"classpath:spring-activiti-config.xml", "classpath:spring-db-config.xml"})	//加载配置文件
public class TestActiviti21 extends AbstractJUnit4SpringContextTests { //extends AbstractTransactionalJUnit4SpringContextTests {
	
	/*
	 * 以下服务对象实例获取方法有如下两种：
	 * 1、手动创建服务对象，通过getProcessEngine()方法获取流程引擎实力
	 * 2、使用注解进行自动注入，引入依赖jar，进行环境配置
	 */
	@Autowired	
	private RepositoryService repositoryService;//工作流仓储服务
	@Autowired
	private RuntimeService runtimeService;		//工作流运行时服务
	@Autowired
	private TaskService taskService;			//工作流任务服务
	@Autowired
	private HistoryService historyService;		//工作流历史数据服务
	
	/**
	 * @功能描述：引用TestActiviti1中的获取ProcessEngine的方法，从而获取流程引擎相关服务
	 * 			说明：通过@Autowired（Spring自动注入）方式没有测试成功（因为spring-activiti-config.xml配置了Spring容器初始化的相关bean），待后续优化！！！！
	 * 				这里采用手动创建各个服务对象的方式进行初始化
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月14日 下午3:09:32
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20171215
	 * @修改描述：已经找到解决方案：引入spring-test.jar，指定@RunWith使用JUnit4运行环境，使用@ContextConfiguration加载配置文件
	 * 			继承AbstractJUnit4SpringContextTests或AbstractTransactionalJUnit4SpringContextTests使用@Autowired/@Resource进行自动注入
	 * 
	 * @return
	 */
	public ProcessEngine getProcessEngine() {
		 // 1.创建Activiti配置对象的实例
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        // 2.设置数据库连接信息

        // 设置数据库地址
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti6?createDatabaseIfNotExist&amp;useUnicode=true&amp;characterEncoding=utf8");
        // 数据库驱动
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        // 用户名
        configuration.setJdbcUsername("root");
        // 密码
        configuration.setJdbcPassword("r00t");

        // 设置数据库建表策略
        /**
         * DB_SCHEMA_UPDATE_TRUE：如果不存在表就创建表，存在就直接使用
         * DB_SCHEMA_UPDATE_FALSE：如果不存在表就抛出异常
         * DB_SCHEMA_UPDATE_CREATE_DROP：每次都先删除表，再创建新的表
         */
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 3.使用配置对象创建流程引擎实例（检查数据库连接等环境信息是否正确）
        ProcessEngine processEngine = configuration.buildProcessEngine();

        System.out.println("+++++++++++++++++++++++++++++++++---->>>>" + processEngine);
        return processEngine;
	}
	
	

	@Test
	public void monthlyTest() {
		/*
		 * 初始化流程引擎服务对象（手动获取服务对象）(20171214)
		 * 由于spring-activiti-config.xml配置了相关服务对象bean的初始化，这里没有通过@Autowired注入的方式获取成功，待后续优化
		 * 
		 * **** 已经找到获取由Spring容器实例化的服务对象，使用注解自动注入(20171215) ****
		 * 依赖配置。引入spring-test-4.2.0.RELEASE.jar/junit-4.12.jar，指定@RunWith使用JUnit4运行环境，使用@ContextConfiguration加载配置文件；（注意spring的版本与junit的版本兼容）
		 * 使用注解自动注入。继承AbstractJUnit4SpringContextTests或AbstractTransactionalJUnit4SpringContextTests使用@Autowired/@Resource进行自动注入；
		 */
//		repositoryService = getProcessEngine().getRepositoryService();
//		runtimeService = getProcessEngine().getRuntimeService();
//		taskService = getProcessEngine().getTaskService();
//		historyService = getProcessEngine().getHistoryService();	
			
		
		//部署流程定义
		repositoryService.createDeployment().addClasspathResource("bpmn/test.bpmn20.xml").deploy();
		//启动流程实例
		String processId = runtimeService.startProcessInstanceByKey("financialReport").getId();
		//获得第一个任务
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("sales").list();
		
		for (Task task : tasks) {
			System.out.println("########@@@@@@@@========>>>>Following task is available for sales group: " + task.getName());
			//认领任务这里由foozie认领，因为fozzie是sales组的成员
			taskService.claim(task.getId(), "fozzie");
		}
		//查看fozzie现在是否能够获取到改任务
		tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
		
		for (Task task : tasks) {
			System.out.println("Task for fozzie: " + task.getName());
			//执行（完成）任务
			taskService.complete(task.getId());
		}
		//现在fozzie的可执行任务数就为0了
		System.out.println("########@@@@@@@@========>>>>Number of tasks for fozzid: " + taskService.createTaskQuery().taskAssignee("fozzie").count());
		
		//获取第二个任务
		tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		
		for (Task task : tasks) {
			//认领任务这里由kermit认领，因为kermit是management组的成员
			taskService.claim(task.getId(), "kermit");
		}
		//完成第二个任务结束流程
		for (Task task : tasks) {
			taskService.complete(task.getId());
		}
		
		//核实流程是否结束，输出流程结束时间
		//HistoricActivityInstance historicProcessInstance = historyService.createHistoricActivityInstanceQuery().processInstanceId(processId).singleResult();
		HistoricActivityInstance historicProcessInstance = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processId).activityId("theEnd").singleResult();	//这里仅获取结束节点；以上方法获取的多个流程节点而无法定位到singleResult，因此会报错
		
		System.out.println("########@@@@@@@@========>>>>Process instance end time: " + historicProcessInstance.getEndTime());
	}

}
