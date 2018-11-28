package com.atguigu.ac.sc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.mapper.MemberMapper;
import com.atguigu.ac.sc.service.MemberService;
import com.atguigu.ac.sc.utils.StringUtils;

@Service
@Transactional 
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public int removeByPrimaryKey(Integer memberId) {
		
		return memberMapper.deleteByPrimaryKey(memberId);
	}

	@Override
	public int add(Member record) {
		
		return memberMapper.insert(record);
	}

	@Override
	public Member getByPrimaryKey(Integer memberId) {
		
		return memberMapper.selectByPrimaryKey(memberId);
	}
	
	@Override
	public Member getByLoginAcc(String loginAcc) {
		
		return memberMapper.selectByLoginAcc(loginAcc);
	}

	@Override
	public List<Member> getAll() {
		
		return memberMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Member record) {
		
		return memberMapper.updateByPrimaryKey(record);
	}

	@Override
	public Member getByLoginAccAndLoginPwd(String loginAcc, String loginPwd) {
		
		String md5 = StringUtils.md5(loginPwd);
		
		return memberMapper.selectByLoginAccAndLoginPwd(loginAcc, md5);
		
	}

	@Override
	public void updateMemberAuthStatus(Integer memberId, String authStatus) {
		
		memberMapper.updateMemberAuthStatus(memberId, authStatus);
		
	}

	@Override
	public void updateMemberAccttype(Integer memberId, String accttype) {
		
		memberMapper.updateMemberAccttype(memberId, accttype);
	}

	@Override
	public void updateMember(Integer memberId, String realName, String cardNum, String phoneNum) {
		
		memberMapper.updateMember(memberId, realName, cardNum, phoneNum);
	}

	@Override
	public void updateMemberEmailAddress(Integer memberId, String emailAddress) {
		
		memberMapper.updateMemberEmailAddress(memberId, emailAddress);
	}

}
