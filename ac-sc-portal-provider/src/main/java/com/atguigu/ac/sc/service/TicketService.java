package com.atguigu.ac.sc.service;

import java.util.List;

import com.atguigu.ac.sc.entity.Ticket;

public interface TicketService {

	int removeByPrimaryKey(Integer id);

    int save(Ticket record);

    Ticket getByPrimaryKey(Integer id);

    List<Ticket> getAll();

    int updateByPrimaryKey(Ticket record);

	Ticket getBymemberId(Integer memberId);

	void updateTicketPstep(Integer memberId, String pstep);

	void updateTicketAuthCode(Integer memberId, String code);

	String getAuthCodeByMemberId(Integer memberId);
}
