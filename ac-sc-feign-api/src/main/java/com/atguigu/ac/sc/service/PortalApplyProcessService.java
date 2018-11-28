package com.atguigu.ac.sc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.ac.sc.entity.Cert;
import com.atguigu.ac.sc.entity.ResultEntity;
import com.atguigu.ac.sc.entity.Ticket;
import com.atguigu.ac.sc.entity.TrueMemberCert;

@FeignClient("ac-sc-portal-provider")
public interface PortalApplyProcessService {

	@RequestMapping("/get/ticket/by/{memberId}")
	public ResultEntity<Ticket> getTicketBymemberId(@PathVariable("memberId") Integer memberId);

	@RequestMapping("/save/ticket")
	public ResultEntity<String> saveTicket(@RequestBody Ticket ticket);

	@RequestMapping("/update/member/authstatus/{memberId}/{authStatus}")
	public ResultEntity<String> updateMemberAuthStatus(@PathVariable("memberId") Integer memberId, @PathVariable("authStatus") String authStatus);

	@RequestMapping("/update/ticket/pstep/{memberId}/{pstep}")
	public ResultEntity<String> updateTicketPstep(@PathVariable("memberId") Integer memberId, @PathVariable("pstep") String pstep);

	@RequestMapping("/update/member/accttype/{memberId}/{accttype}")
	public ResultEntity<String> updateMemberAccttype(@PathVariable("memberId")Integer memberId, @PathVariable("accttype")String accttype);

	@RequestMapping("/update/member/{memberId}/{realName}/{cardNum}/{phoneNum}")
	public void updateMember(@PathVariable("memberId")Integer memberId, 
			@PathVariable("realName")String realName, 
			@PathVariable("cardNum")String cardNum, 
			@PathVariable("phoneNum")String phoneNum);

	@RequestMapping("/get/listcertid/by/acctype/{accType}")
	public List<Cert> getListCertIdByAccType(@PathVariable("accType")Byte accType);

	@RequestMapping("/save/truemembercert/list")
	public ResultEntity<String> saveTrueMemberCertList(@RequestBody ArrayList<TrueMemberCert> memberCertList);

	@RequestMapping("/update/ticket/auth/code/{memberId}/{code}")
	public ResultEntity<String> updateTicketAuthCode(@PathVariable("memberId")Integer memberId, @PathVariable("code")String code);

	@RequestMapping("/update/member/email/address")
	public ResultEntity<String> updateMemberEmailAddress(@RequestBody HashMap<String, Object> hashMap);

	@RequestMapping("/get/authcode/by/memberid/{memberId}")
	public ResultEntity<String> getAuthCodeByMemberId(@PathVariable("memberId")Integer memberId);
	
}
