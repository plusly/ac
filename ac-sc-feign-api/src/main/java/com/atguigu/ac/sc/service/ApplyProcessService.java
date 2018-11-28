package com.atguigu.ac.sc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.ac.sc.entity.ResultEntity;

@FeignClient("ac-sc-activiti-provider")
public interface ApplyProcessService {
	
	@RequestMapping("/start/realname/auth/process/instance/{loginAcc}")
	public ResultEntity<String> startRealNameAuthProcessInstance(@PathVariable("loginAcc") String loginAcc);

	@RequestMapping("/complate/process/apply")
	public ResultEntity<String> complateProcessApply(@RequestBody Map<String, Object> variables);

}
