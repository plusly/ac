package com.atguigu.ac.sc.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;
import com.atguigu.ac.sc.entity.Ticket;
import com.atguigu.ac.sc.service.TicketService;

@RestController
public class TicketHandler {

	@Autowired
	private TicketService ticketService;
	
	@RequestMapping("/get/authcode/by/memberid/{memberId}")
	public ResultEntity<String> getAuthCodeByMemberId(@PathVariable("memberId")Integer memberId){
		
		ResultEntity<String> resultEntity = new ResultEntity<>();
		
		try {
			String authCode = ticketService.getAuthCodeByMemberId(memberId);
			
			resultEntity.setResult(ResultEntity.SUCCESS);
			resultEntity.setData(authCode);
		} catch (Exception e) {
			
			e.printStackTrace();
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setData(ResultEntity.NO_DATA);
			resultEntity.setMessage(e.getMessage());
		}
		
		return resultEntity;
	}
	
	@RequestMapping("/update/ticket/auth/code/{memberId}/{code}")
	public ResultEntity<String> updateTicketAuthCode(@PathVariable("memberId")Integer memberId, @PathVariable("code")String code) {
		
		ticketService.updateTicketAuthCode(memberId, code);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
	
	@RequestMapping("/update/ticket/pstep/{memberId}/{pstep}")
	public ResultEntity<String> updateTicketPstep(@PathVariable("memberId") Integer memberId, 
			@PathVariable("pstep") String pstep){
		
		ticketService.updateTicketPstep(memberId, pstep);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, null, null);
	}
	
	@RequestMapping("/save/ticket")
	public ResultEntity<String> saveTicket(@RequestBody Ticket ticket){
		
		ticketService.save(ticket);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, null, null);
	}
	
	@RequestMapping("/get/ticket/by/{memberId}")
	public ResultEntity<Ticket> getTicketBymemberId(@PathVariable("memberId") Integer memberId){
		
		ResultEntity<Ticket> resultEntity = new ResultEntity<>();
		
		try {
			Ticket ticket = ticketService.getBymemberId(memberId);
			
			resultEntity.setData(ticket);
			resultEntity.setResult(ResultEntity.SUCCESS);
		} catch (Exception e) {
			
			resultEntity.setMessage(e.getMessage());
			resultEntity.setResult(ResultEntity.FAILED);
		}
		
		return resultEntity;
	}
}
