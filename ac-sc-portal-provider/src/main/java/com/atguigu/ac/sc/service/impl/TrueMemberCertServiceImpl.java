package com.atguigu.ac.sc.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ac.sc.entity.TrueMemberCert;
import com.atguigu.ac.sc.mapper.TrueMemberCertMapper;
import com.atguigu.ac.sc.service.TrueMemberCertService;

@Service
@Transactional
public class TrueMemberCertServiceImpl implements TrueMemberCertService {
	
	@Autowired
	TrueMemberCertMapper trueMemberCertMapper;

	@Override
	public void saveTrueMemberCertList(ArrayList<TrueMemberCert> memberCertList) {
		
		trueMemberCertMapper.insertTrueMemberCertList(memberCertList);
	}

	
}
