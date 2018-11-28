package com.atguigu.ac.sc.mapper;

import com.atguigu.ac.sc.entity.Member;
import java.util.List;
import java.util.Map;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(Member record);

    Member selectByPrimaryKey(Integer memberId);

    List<Member> selectAll();

    int updateByPrimaryKey(Member record);

	Member selectByLoginAcc(String loginAcc);

	Member selectByLoginAccAndLoginPwd(String loginAcc, String loginPwd);

	void updateMemberAuthStatus(Integer memberId, String authStatus);

	void updateMemberAccttype(Integer memberId, String accttype);

	void updateMember(Integer memberId, String realName, String cardNum, String phoneNum);

	void updateMemberEmailAddress(Integer memberId, String emailAddress);

	Member selectMemberByProcessInstanceId(Integer processInstanceId);
	
	List<Map<String, String>> selectMemberCertDetail(Integer memberId);
}