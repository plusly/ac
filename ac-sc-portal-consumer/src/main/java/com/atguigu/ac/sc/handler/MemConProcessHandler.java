package com.atguigu.ac.sc.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.ac.sc.entity.Cert;
import com.atguigu.ac.sc.entity.CertParam;
import com.atguigu.ac.sc.entity.CertParamWrapper;
import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;
import com.atguigu.ac.sc.entity.Ticket;
import com.atguigu.ac.sc.entity.TrueMemberCert;
import com.atguigu.ac.sc.service.ApplyProcessService;
import com.atguigu.ac.sc.service.PortalApplyProcessService;
import com.atguigu.ac.sc.utils.StringUtils;

@Controller
public class MemConProcessHandler {

	@Autowired
	private ApplyProcessService applyProcessService;

	@Autowired
	private PortalApplyProcessService portalApplyProcessService;

	@Autowired
	private StorageClient storageClient;

	@RequestMapping("/consumer/process/apply/checke/code")
	public String consumerProcessApplyCheckCode(@RequestParam String checkCode, HttpSession session, Model model) {

		Member member = (Member) session.getAttribute("member");

		if (member == null) {

			return "member/login";
		}
		
		if(checkCode == null || "".equals(checkCode)) {
			
			model.addAttribute("MESSAGE", "您的验证码为空");
			return "member/codecheck";
		}
		
		Integer memberId = member.getMemberId();
		
		ResultEntity<String> resultEntity = portalApplyProcessService.getAuthCodeByMemberId(memberId);
		
		String authCode = null;		
		if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
			
			authCode = resultEntity.getData();
		}
		
		if(authCode != null && authCode.equals(checkCode)) {
			
			HashMap<String, Object> variables = new HashMap<>();
			variables.put("member", member.getLoginAcc());
			variables.put("flag", true);
			
			applyProcessService.complateProcessApply(variables);
			
			portalApplyProcessService.updateMemberAuthStatus(memberId, "2");
			
			member.setAuthStatus(new Byte("2"));
			
		}else {
			
			model.addAttribute("MESSAGE", "您输入的验证码不对！");
			return "member/codecheck";
		}
		
		return "member/member";
	}

	@RequestMapping("/consumer/process/apply/email/address")
	public String consumerProcessApplyEmailAddress(@RequestParam String emailAddress, HttpSession session,
			Model model) {

		Member member = (Member) session.getAttribute("member");

		if (member == null) {

			return "member/login";
		}

		HashMap<String, Object> variables = new HashMap<>();

		String code = StringUtils.getCode();

		variables.put("member", member.getLoginAcc());
		variables.put("memberEmail", emailAddress);
		variables.put("flag", true);
		variables.put("code", code);

		ResultEntity<String> resultEntity = applyProcessService.complateProcessApply(variables);

		if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {

			Integer memberId = member.getMemberId();

			portalApplyProcessService.updateTicketAuthCode(memberId, code);

			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("memberId", memberId);
			hashMap.put("emailAddress", emailAddress);

			portalApplyProcessService.updateMemberEmailAddress(hashMap);

			member.setEmailAddr(emailAddress);

			String pstep = "codecheck";
			portalApplyProcessService.updateTicketPstep(memberId, pstep);

		} else {

			return "error11";
		}

		return "process/codecheck";
	}

	@RequestMapping("/consumer/process/apply/uploadfile")
	public String consumerProcessApplyUploadfile(CertParamWrapper wrapper, HttpSession session)
			throws IOException, MyException {

		// 完成流程实例
		Member member = (Member) session.getAttribute("member");

		if (member == null) {

			return "member/login";
		}

		String loginAcc = member.getLoginAcc();
		Integer memberId = member.getMemberId();

		List<CertParam> certParamList = wrapper.getCertParamList();

		ArrayList<TrueMemberCert> memberCertList = new ArrayList<>();

		for (CertParam certParam : certParamList) {

			MultipartFile certFile = certParam.getCertFile();

			String originalFilename = certFile.getOriginalFilename();

			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

			byte[] bytes = certFile.getBytes();

			String[] resultArray = storageClient.upload_file(bytes, extName, null);

			String groupName = resultArray[0];
			String fileRemoteName = resultArray[1];

			String iconpath = groupName + "/" + fileRemoteName;

			System.err.println(iconpath);

			TrueMemberCert trueMemberCert = new TrueMemberCert(null, memberId, certParam.getCertId(), iconpath);

			memberCertList.add(trueMemberCert);
		}

		portalApplyProcessService.saveTrueMemberCertList(memberCertList);

		String pstep = "emailaddress";
		portalApplyProcessService.updateTicketPstep(memberId, pstep);

		HashMap<String, Object> variables = new HashMap<>();

		variables.put("member", loginAcc);
		variables.put("flag", true);

		applyProcessService.complateProcessApply(variables);

		return "process/emailaddress";
	}

	@RequestMapping("/consumer/process/apply/basicinfo")
	public String consumerProcessApplyBasicinfo(String realName, String cardNum, String phoneNum, HttpSession session,
			Model model) {

		// 完成流程实例
		Member member = (Member) session.getAttribute("member");

		if (member == null) {

			return "member/login";
		}

		String loginAcc = member.getLoginAcc();

		HashMap<String, Object> variables = new HashMap<>();

		variables.put("member", loginAcc);
		variables.put("flag", true);

		ResultEntity<String> resultEntity = applyProcessService.complateProcessApply(variables);

		if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {

			Integer memberId = member.getMemberId();
			String pstep = "uploadfile";

			portalApplyProcessService.updateTicketPstep(memberId, pstep);

			portalApplyProcessService.updateMember(memberId, realName, cardNum, phoneNum);

			Byte accType = member.getAccType();

			List<Cert> certList = portalApplyProcessService.getListCertIdByAccType(accType);

			model.addAttribute("certList", certList);

		} else {

			model.addAttribute("MESSAGE", resultEntity.getMessage());
			return "error11";
		}

		return "process/uploadfile";
	}

	@RequestMapping("/consumer/process/apply/accttype/{accttype}")
	public String consumerProcessApplyAccttpye(@PathVariable("accttype") String accttype, Model model,
			HttpSession session) {

		// 完成流程实例
		Member member = (Member) session.getAttribute("member");

		if (member == null) {

			return "member/login";
		}

		String loginAcc = member.getLoginAcc();

		HashMap<String, Object> variables = new HashMap<>();

		variables.put("member", loginAcc);

		// 完成流程实例
		ResultEntity<String> resultEntity = applyProcessService.complateProcessApply(variables);

		if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {

			// 修改t_ticket表的pstep
			Integer memberId = member.getMemberId();
			String pstep = "basicinfo";

			portalApplyProcessService.updateTicketPstep(memberId, pstep);

			// 修改t_member表的accttype
			portalApplyProcessService.updateMemberAccttype(memberId, accttype);

			member.setAccType(new Byte(accttype));
		} else {

			model.addAttribute("MESSAGE", resultEntity.getMessage());
			return "error11";
		}

		return "process/basicinfo";
	}

	@RequestMapping("/start/consumer/process/apply")
	public String consumerProcessApply(HttpSession session, Model model) {

		String viewName = null;

		// 启动流程实例
		Member member = (Member) session.getAttribute("member");

		if (member == null) {

			return "member/login";
		}

		Integer memberId = member.getMemberId();

		// 将当前流程实例相关信息保存到t_ticket表
		Ticket ticket = portalApplyProcessService.getTicketBymemberId(memberId).getData();

		if (ticket == null) {

			ResultEntity<String> resultEntity = applyProcessService
					.startRealNameAuthProcessInstance(member.getLoginAcc());

			if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {

				String processInstanceId = resultEntity.getData();

				portalApplyProcessService
						.saveTicket(new Ticket(null, memberId, processInstanceId, "0", null, "accttype"));

				// 修改t_member表中的auth_status
				String authStatus = "1";
				portalApplyProcessService.updateMemberAuthStatus(memberId, authStatus);

				member.setAuthStatus(new Byte(authStatus));
			} else {

				model.addAttribute("MESSAGE", resultEntity.getMessage());
				return "error11";
			}

			viewName = "process/accttype";
		} else {

			Byte accType = member.getAccType();

			List<Cert> certList = portalApplyProcessService.getListCertIdByAccType(accType);

			model.addAttribute("certList", certList);

			viewName = "process/" + ticket.getPstep();
		}

		return viewName;
	}
}
