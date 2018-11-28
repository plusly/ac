package com.atguigu.ac.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;

@FeignClient("ac-sc-manager-provider")
public interface ManagerProviderSerivce {

	@RequestMapping("/get/member/by/processinstanceid/{processInstanceId}")
	public ResultEntity<Member> getMemberByProcessInstanceId(@PathVariable("processInstanceId")String processInstanceId);
	
	@RequestMapping("/get/member/cert/detail/{memberId}")
	public Map<String, Object> getMemberCertDetail(@PathVariable("memberId") Integer memberId);
}
