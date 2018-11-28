package com.atguigu.ac.sc.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;
import com.atguigu.ac.sc.service.MemberService;

@RestController
public class ManagerMemberHandler {

	@Autowired
	private MemberService memberService; 
	
	@RequestMapping("/get/member/cert/detail/{memberId}")
	public Map<String, Object> getMemberCertDetail(@PathVariable("memberId") Integer memberId){
		
		Member member = memberService.getMemberByPrimaryKey(memberId);
		
		HashMap<String,Object> resultMap = new HashMap<>();
		
		resultMap.put("memberId", member.getMemberId());
		resultMap.put("nickName", member.getNickName());
		resultMap.put("realName", member.getRealName());
		resultMap.put("cardNum", member.getCardNum());
		resultMap.put("phoneNum", member.getPhoneNum());
		resultMap.put("emailAddr", member.getEmailAddr());
		
		List<Map<String,String>> certDetail = memberService.getMemberCertDetail(memberId);
		
		resultMap.put("detailMapList", certDetail);
		
		return resultMap;
	}
	

	@RequestMapping("/get/member/by/processinstanceid/{processInstanceId}")
	public ResultEntity<Member> getMemberByProcessInstanceId(@PathVariable("processInstanceId")String processInstanceId) {
		
		ResultEntity<Member> resultEntity = new ResultEntity<>();
		
		try {
			int parseInt = Integer.parseInt(processInstanceId);
			
			Member member = memberService.getMemberByProcessInstanceId(parseInt);
			
			resultEntity.setResult(ResultEntity.SUCCESS);
			resultEntity.setData(member);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}
		
		return resultEntity;
	}
}
