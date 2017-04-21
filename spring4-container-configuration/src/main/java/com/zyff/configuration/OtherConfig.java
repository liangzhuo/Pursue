package com.zyff.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zyff.service.MyService1;

@Configuration
public class OtherConfig {

	@Bean
	public MyService1 myService1() {
		return new MyService1();
	}
}
