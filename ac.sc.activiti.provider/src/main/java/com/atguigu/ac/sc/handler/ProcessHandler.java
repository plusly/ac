package com.atguigu.ac.sc.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;
import com.atguigu.ac.sc.service.ManagerProviderSerivce;

@RestController
public class ProcessHandler {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ManagerProviderSerivce managerProviderSerivce;
	
	@RequestMapping("/get/approval/tasklist/remote")
	public ResultEntity<List<Map<String, Object>>> getApprovalTaskListRemote() {
		
		String processDefinitionKey = "emailProcess";
		String candidateGroup = "processAdmin";
		
		int version = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey)
				.latestVersion().singleResult().getVersion();
		
		List<Task> taskList = taskService.createTaskQuery()
								.processDefinitionKey(processDefinitionKey)
								.taskCandidateGroup(candidateGroup)
								.list();
		
		ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
		
		for (Task task : taskList) {
			
			String taskId = task.getId();
			String taskName = task.getName();
			String processInstanceId = task.getProcessInstanceId();
			
			ResultEntity<Member> result = managerProviderSerivce
					.getMemberByProcessInstanceId(processInstanceId);
			
			HashMap<String,Object> hashMap = new HashMap<>();
			
			hashMap.put("taskId", taskId);
			hashMap.put("taskName", taskName);
			hashMap.put("version", version);
			hashMap.put("memberId", result.getData().getMemberId());
			hashMap.put("nickName", result.getData().getNickName());
			
			arrayList.add(hashMap);
		}
		
		return new ResultEntity<List<Map<String,Object>>>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, arrayList);
	}
	
	@RequestMapping("/get/process/definition/picture/{pdKey}/{pdVersion}")
	public byte[] seePicture(@PathVariable("pdKey") String pdKey, 
			@PathVariable("pdVersion") Integer pdVersion) throws IOException {
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(pdKey).processDefinitionVersion(pdVersion).singleResult();
		
		String deploymentId = processDefinition.getDeploymentId();
		
		String resourceName = processDefinition.getDiagramResourceName();
		
		InputStream resourceAsStream = repositoryService.getResourceAsStream(deploymentId, resourceName);
		
		byte[] buffer = new byte[1024];
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		int len = 0;
		
		while((len = resourceAsStream.read(buffer)) > 0) {
			
			byteArrayOutputStream.write(buffer, 0, len);
		}
		
		byte[] byteArray = byteArrayOutputStream.toByteArray();
		
		return byteArray;
	}
	
	@RequestMapping("/process/remote/deploy")
	public String processRemoteDeploy(@RequestParam("pdfile") MultipartFile multipartFile) throws IOException {
		
		InputStream inputStream = multipartFile.getInputStream();
		
		String originalFilename = multipartFile.getOriginalFilename();
		
		Deployment deploy = repositoryService.createDeployment()
					.addInputStream(originalFilename, inputStream).deploy();
		
		return "SUCCESS";
	}

	@RequestMapping("/process/get/allProcess")
	public ResultEntity<List<Map<String, Object>>> getAllProcess(){
		
		ResultEntity<List<Map<String, Object>>> resultEntity = new ResultEntity<>();
		
		try {
			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
			
			ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
			
			for (ProcessDefinition processDefinition : list) {
				
				HashMap<String, Object> hashMap = new HashMap<>();
				
				String id = processDefinition.getId();
				int version = processDefinition.getVersion();
				String key = processDefinition.getKey();
				String name = processDefinition.getName();
				
				hashMap.put("id", id);
				hashMap.put("version", version);
				hashMap.put("key", key);
				hashMap.put("name", name);
				
				arrayList.add(hashMap);
			}
			System.out.println(arrayList);
			resultEntity.setData(arrayList);
			resultEntity.setResult(ResultEntity.SUCCESS);
			
		} catch (Exception e) {
			
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
			
			e.printStackTrace();
		}
		
		return resultEntity;
	}
	
}
