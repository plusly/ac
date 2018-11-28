package com.atguigu.ac.sc.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;
import com.atguigu.ac.sc.service.MemberRemoteService;

@Controller
public class MemberConsumerHandler {

	@Autowired
	private MemberRemoteService memberRemoteService;
	
	@RequestMapping("/member/do/login")
	public String doLogin(String loginAcc, String loginPwd, HttpSession session) {
		
		ResultEntity<Member> resultEntity = memberRemoteService.doLogin(loginAcc, loginPwd);
		
		if(ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
			
			Member member = resultEntity.getData();
			
			session.setAttribute("member", member);
			
			return "member/member";
		}else {
			
			return "member/login";
		}
		
	}
	
	@RequestMapping("/member/do/register")
	public String doRegister(Member member) {
		
		ResultEntity<String> result = memberRemoteService.doRegister(member);
		
		if("SUCCESS".equals(result.getResult())) {
			
			return "member/login";
		}else {
			
			return "member/reg";
		}
		
	}
	
	@RequestMapping("/to/registerPage")
	public String toRegisterPage() {
		
		return "member/reg";
	}
	
	@RequestMapping("/to/loginPage")
	public String toLoginPage() {
		
		return "member/login";
	}
}
