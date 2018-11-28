package com.atguigu.activiti;

import static org.junit.Assert.*;

import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {
	
	@Autowired
	RepositoryService repositoryService;

	@Test
	public void test() throws Exception {
		
		repositoryService.createDeployment().addClasspathResource("emailprocess.bpmn").deploy();
	}
}
