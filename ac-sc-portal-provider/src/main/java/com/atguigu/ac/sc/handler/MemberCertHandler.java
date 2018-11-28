package com.atguigu.ac.sc.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.ac.sc.entity.Cert;
import com.atguigu.ac.sc.service.CertService;
import com.atguigu.ac.sc.service.MemberCertService;

@RestController
public class MemberCertHandler {
	
	@Autowired
	private MemberCertService memberCertService;
	
	@Autowired
	private CertService certService;
	
	@RequestMapping("/get/listcertid/by/acctype/{accType}")
	public List<Cert> getListCertIdByAccType(@PathVariable("accType")Byte accType){
		
		List<Integer> list = memberCertService.getListCertIdByAccType(accType);
		
		List<Cert> certs = certService.getCertList(list);
		
		return certs;
	}
}
