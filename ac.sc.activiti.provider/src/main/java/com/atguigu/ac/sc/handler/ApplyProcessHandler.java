package com.atguigu.ac.sc.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;

@RestController
public class ApplyProcessHandler {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	


	@RequestMapping("/complate/process/apply")
	public ResultEntity<String> complateProcessApply(@RequestBody Map<String, Object> variables) {

		ResultEntity<String> resultEntity = new ResultEntity<>();

		try {
			String processDefinitionKey = "emailProcess";

			String member = (String) variables.get("member");

			String taskId = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey)
					.taskAssignee(member).singleResult().getId();

			taskService.complete(taskId, variables);
			
			resultEntity.setResult(ResultEntity.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();

			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}

		return resultEntity;
	}

	@RequestMapping("/start/realname/auth/process/instance/{loginAcc}")
	public ResultEntity<String> startRealNameAuthProcessInstance(@PathVariable("loginAcc") String loginAcc) {

		ResultEntity<String> resultEntity = new ResultEntity<>();

		try {
			String processDefinitionKey = "emailProcess";

			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionKey(processDefinitionKey).latestVersion().singleResult();

			String id = processDefinition.getId();

			HashMap<String, Object> variables = new HashMap<>();
			variables.put("member", loginAcc);

			ProcessInstance processInstance = runtimeService.startProcessInstanceById(id, variables);

			String processInstanceId = processInstance.getId();

			resultEntity.setResult(ResultEntity.SUCCESS);
			resultEntity.setData(processInstanceId);

		} catch (Exception e) {

			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}

		return resultEntity;
	}
}
