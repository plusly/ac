package com.atguigu.ac.sc.mapper;

import com.atguigu.ac.sc.entity.Ticket;
import java.util.List;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    Ticket selectByPrimaryKey(Integer id);

    List<Ticket> selectAll();

    int updateByPrimaryKey(Ticket record);

	Ticket selectBymemberId(Integer memberId);

	void updateTicketPstep(Integer memberId, String pstep);

	void updateTicketAuthCode(Integer memberId, String code);

	String selectAuthCodeByMemberId(Integer memberId);
}