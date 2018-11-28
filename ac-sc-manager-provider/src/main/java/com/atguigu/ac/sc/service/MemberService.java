package com.atguigu.ac.sc.service;

import java.util.List;
import java.util.Map;

import com.atguigu.ac.sc.entity.Member;

public interface MemberService {

	Member getMemberByProcessInstanceId(Integer processInstanceId);
	
	Member getMemberByPrimaryKey(Integer memberId);
	
	List<Map<String, String>> getMemberCertDetail(Integer memberId);

}
