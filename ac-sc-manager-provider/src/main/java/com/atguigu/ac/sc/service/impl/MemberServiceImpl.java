package com.atguigu.ac.sc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.mapper.MemberMapper;
import com.atguigu.ac.sc.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Transactional(readOnly=true)
	public Member getMemberByProcessInstanceId(Integer processInstanceId) {
		
		return memberMapper.selectMemberByProcessInstanceId(processInstanceId);
	}

	@Transactional(readOnly=true)
	public Member getMemberByPrimaryKey(Integer memberId) {
		
		return memberMapper.selectByPrimaryKey(memberId);
	}

	@Override
	public List<Map<String, String>> getMemberCertDetail(Integer memberId) {
		
		return memberMapper.selectMemberCertDetail(memberId);
	}
	
}
