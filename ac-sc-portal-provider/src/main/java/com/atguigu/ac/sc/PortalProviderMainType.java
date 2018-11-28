package com.atguigu.ac.sc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.atguigu.ac.sc.mapper")
public class PortalProviderMainType {

	public static void main(String[] args) {
		SpringApplication.run(PortalProviderMainType.class, args);
	}
}
