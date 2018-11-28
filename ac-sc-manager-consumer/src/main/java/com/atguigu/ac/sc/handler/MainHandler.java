package com.atguigu.ac.sc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainHandler {

	@RequestMapping("/main")
	public String toMainPage() {
		
		return "main";
	}
}
