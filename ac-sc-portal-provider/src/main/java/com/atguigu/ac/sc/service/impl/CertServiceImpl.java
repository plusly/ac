package com.atguigu.ac.sc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ac.sc.entity.Cert;
import com.atguigu.ac.sc.mapper.CertMapper;
import com.atguigu.ac.sc.service.CertService;

@Service
@Transactional
public class CertServiceImpl implements CertService{
	
	@Autowired
	private CertMapper certMapper;

	@Override
	public List<Cert> getCertList(List<Integer> list) {
		
		return certMapper.selectCertList(list);
	}

	
}
