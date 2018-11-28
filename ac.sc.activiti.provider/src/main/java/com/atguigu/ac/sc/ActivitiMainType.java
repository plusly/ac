package com.atguigu.ac.sc;

import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ActivitiMainType {
	
	@Autowired
	private ProcessEngine processEngine;

	public static void main(String[] args) {
		SpringApplication.run(ActivitiMainType.class, args);
		
	}
}
