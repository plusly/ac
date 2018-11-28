package com.atguigu.ac.sc.handler;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;
import com.atguigu.ac.sc.service.MemberService;
import com.atguigu.ac.sc.utils.StringUtils;

@RestController
public class MemberHandler {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/update/member/email/address")
	public ResultEntity<String> updateMemberEmailAddress(@RequestBody HashMap<String, Object> hashMap){
		
		Integer memberId = (Integer) hashMap.get("memberId");
		String emailAddress = (String) hashMap.get("emailAddress");
		
		memberService.updateMemberEmailAddress(memberId, emailAddress);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
	@RequestMapping("/update/member/{memberId}/{realName}/{cardNum}/{phoneNum}")
	public void updateMember(@PathVariable("memberId")Integer memberId, 
			@PathVariable("realName")String realName, 
			@PathVariable("cardNum")String cardNum, 
			@PathVariable("phoneNum")String phoneNum){
		
		memberService.updateMember(memberId, realName, cardNum, phoneNum);
	}
	
	@RequestMapping("/update/member/accttype/{memberId}/{accttype}")
	public ResultEntity<String> updateMemberAccttype(@PathVariable("memberId")Integer memberId, 
			@PathVariable("accttype")String accttype){
		
		memberService.updateMemberAccttype(memberId, accttype);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
	
	@RequestMapping("/update/member/authstatus/{memberId}/{authStatus}")
	public ResultEntity<String> updateMemberAuthStatus(@PathVariable("memberId") Integer memberId, @PathVariable("authStatus") String authStatus) {
		
		memberService.updateMemberAuthStatus(memberId, authStatus);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
	@RequestMapping("/provider/member/do/login")
	public ResultEntity<Member> doLogin(@RequestParam("loginAcc") String loginAcc, 
			@RequestParam("loginPwd") String loginPwd){
		
		ResultEntity<Member> resultEntity = new ResultEntity<>();
		
		Member member = memberService.getByLoginAccAndLoginPwd(loginAcc, loginPwd);
		
		if(member != null) {
			
			resultEntity.setData(member);
			resultEntity.setResult(ResultEntity.SUCCESS);
		}else {
			
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage("用户名或密码不对！");
		}
		
		return resultEntity;
	}
	

	@RequestMapping(value="/provider/member/do/register", method=RequestMethod.POST)
	public ResultEntity<String> doRegister(@RequestBody Member member) {
		
		System.out.println("******" + member);
		
		ResultEntity<String> resultEntity = new ResultEntity<>();
		
		String loginAcc = member.getLoginAcc();
		
		Member member2 = memberService.getByLoginAcc(loginAcc);
		
		if(member2 == null) {
			
			String loginPwd = member.getLoginPwd();
			
			String md5 = StringUtils.md5(loginPwd);
			
			member.setLoginPwd(md5);
			
			memberService.add(member);
			
			resultEntity.setResult(ResultEntity.SUCCESS);
			resultEntity.setData(ResultEntity.NO_DATA);
		}else {
			
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setData(ResultEntity.NO_DATA);
			resultEntity.setMessage("该用户名已被注册，请重新注册！");
		}
		
		return resultEntity;
	}
}
