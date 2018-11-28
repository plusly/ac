package com.atguigu.ac.sc.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;

@FeignClient("ac-sc-portal-provider")
public interface MemberRemoteService {
	
	@RequestMapping(value="/provider/member/do/register", method=RequestMethod.POST)
	public ResultEntity<String> doRegister(@RequestBody Member member);
	
	@RequestMapping("/provider/member/do/login")
	public ResultEntity<Member> doLogin(@RequestParam("loginAcc") String loginAcc, @RequestParam("loginPwd") String loginPwd);
	
}
