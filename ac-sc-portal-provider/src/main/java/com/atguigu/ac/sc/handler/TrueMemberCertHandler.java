package com.atguigu.ac.sc.handler;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.ac.sc.entity.ResultEntity;
import com.atguigu.ac.sc.entity.TrueMemberCert;
import com.atguigu.ac.sc.service.TrueMemberCertService;

@RestController
public class TrueMemberCertHandler {

	@Autowired
	private TrueMemberCertService trueMemberCertService;
	
	@RequestMapping("/save/truemembercert/list")
	public ResultEntity<String> saveTrueMemberCertList(@RequestBody ArrayList<TrueMemberCert> memberCertList){
		
		trueMemberCertService.saveTrueMemberCertList(memberCertList);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, null, null);
	}
	
}
