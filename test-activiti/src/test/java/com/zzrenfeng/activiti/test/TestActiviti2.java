package com.zzrenfeng.activiti.test;

import static org.junit.Assert.*;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzrenfeng.activiti.base.BaseJUnit4Test;

public class TestActiviti2 extends BaseJUnit4Test {
	
	@Autowired	
	private RepositoryService repositoryService;//工作流仓储服务
	@Autowired
	private RuntimeService runtimeService;		//工作流运行时服务
	@Autowired
	private TaskService taskService;			//工作流任务服务
	@Autowired
	private HistoryService historyService;		//工作流历史数据服务

	@Test
	public void monthlyTest() {
		// 部署流程定义
		repositoryService.createDeployment().addClasspathResource("bpmn/test.bpmn20.xml").deploy();
		// 启动流程实例
		String processId = runtimeService.startProcessInstanceByKey("financialReport").getId();
		// 获得第一个任务
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("sales").list();

		for (Task task : tasks) {
			System.out.println("########@@@@@@@@========>>>>Following task is available for sales group: " + task.getName());
			// 认领任务这里由foozie认领，因为fozzie是sales组的成员
			taskService.claim(task.getId(), "fozzie");
		}
		// 查看fozzie现在是否能够获取到改任务
		tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();

		for (Task task : tasks) {
			System.out.println("Task for fozzie: " + task.getName());
			// 执行（完成）任务
			taskService.complete(task.getId());
		}
		// 现在fozzie的可执行任务数就为0了
		System.out.println("########@@@@@@@@========>>>>Number of tasks for fozzid: "
				+ taskService.createTaskQuery().taskAssignee("fozzie").count());

		// 获取第二个任务
		tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();

		for (Task task : tasks) {
			// 认领任务这里由kermit认领，因为kermit是management组的成员
			taskService.claim(task.getId(), "kermit");
		}
		// 完成第二个任务结束流程
		for (Task task : tasks) {
			taskService.complete(task.getId());
		}

		// 核实流程是否结束，输出流程结束时间
		HistoricActivityInstance historicProcessInstance = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processId).activityId("theEnd").singleResult();

		System.out.println("########@@@@@@@@========>>>>Process instance end time: " + historicProcessInstance.getEndTime());
	}

}
