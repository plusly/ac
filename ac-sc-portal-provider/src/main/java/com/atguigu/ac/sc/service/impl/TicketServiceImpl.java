package com.atguigu.ac.sc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ac.sc.entity.Ticket;
import com.atguigu.ac.sc.mapper.TicketMapper;
import com.atguigu.ac.sc.service.TicketService;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketMapper ticketMapper;

	@Override
	public int removeByPrimaryKey(Integer id) {
		
		return ticketMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int save(Ticket record) {
		
		return ticketMapper.insert(record);
	}

	@Transactional(readOnly=true)
	public Ticket getByPrimaryKey(Integer id) {
		
		return ticketMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=true)
	public List<Ticket> getAll() {
		
		return ticketMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Ticket record) {
		
		return ticketMapper.updateByPrimaryKey(record);
	}

	@Override
	public Ticket getBymemberId(Integer memberId) {
		
		return ticketMapper.selectBymemberId(memberId);
	}

	@Override
	public void updateTicketPstep(Integer memberId, String pstep) {
		
		ticketMapper.updateTicketPstep(memberId, pstep);
		
	}

	@Override
	public void updateTicketAuthCode(Integer memberId, String code) {
		
		ticketMapper.updateTicketAuthCode(memberId, code);
	}

	@Override
	public String getAuthCodeByMemberId(Integer memberId) {
		
		return ticketMapper.selectAuthCodeByMemberId(memberId);
	}

}
