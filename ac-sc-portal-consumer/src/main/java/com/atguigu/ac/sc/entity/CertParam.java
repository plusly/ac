package com.atguigu.ac.sc.entity;

import org.springframework.web.multipart.MultipartFile;

public class CertParam {

	private Integer certId;
	private MultipartFile certFile;

	public CertParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CertParam(Integer certId, MultipartFile certFile) {
		super();
		this.certId = certId;
		this.certFile = certFile;
	}

	public Integer getCertId() {
		return certId;
	}

	public void setCertId(Integer certId) {
		this.certId = certId;
	}

	public MultipartFile getCertFile() {
		return certFile;
	}

	public void setCertFile(MultipartFile certFile) {
		this.certFile = certFile;
	}

	@Override
	public String toString() {
		return "CertParam [certId=" + certId + ", multipartFile=" + certFile + "]";
	}

}
