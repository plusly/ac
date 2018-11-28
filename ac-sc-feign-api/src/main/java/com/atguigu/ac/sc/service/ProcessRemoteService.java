package com.atguigu.ac.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.ac.sc.entity.ResultEntity;

@FeignClient("ac-sc-activiti-provider")
public interface ProcessRemoteService {

	@RequestMapping("/process/get/allProcess")
	public ResultEntity<List<Map<String, Object>>> getAllProcess();
	

	@RequestMapping("/get/approval/tasklist/remote")
	public ResultEntity<List<Map<String, Object>>> getApprovalTaskListRemote();
	
}
