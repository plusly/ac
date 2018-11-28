package com.atguigu.ac.sc.service;

import java.util.List;

import com.atguigu.ac.sc.entity.Member;

public interface MemberService {
	
    int removeByPrimaryKey(Integer memberId);

    int add(Member record);

    Member getByPrimaryKey(Integer memberId);

    List<Member> getAll();

    int updateByPrimaryKey(Member record);

	Member getByLoginAcc(String loginAcc);

	Member getByLoginAccAndLoginPwd(String loginAcc, String loginPwd);

	void updateMemberAuthStatus(Integer memberId, String authStatus);

	void updateMemberAccttype(Integer memberId, String accttype);

	void updateMember(Integer memberId, String realName, String cardNum, String phoneNum);

	void updateMemberEmailAddress(Integer memberId, String emailAddress);

}
