package com.atguigu.ac.sc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ac.sc.mapper.MemberCertMapper;
import com.atguigu.ac.sc.service.MemberCertService;

@Service
@Transactional
public class MemberCertServiceImpl implements MemberCertService {
	
	@Autowired
	private MemberCertMapper memberCertMapper;

	@Override
	public List<Integer> getListCertIdByAccType(Byte accType) {
		
		return memberCertMapper.selectListCertIdByAccType(accType);
	}

}
