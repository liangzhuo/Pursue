package com.zyff.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zyff.factory.Bar;
import com.zyff.factory.Factory;
import com.zyff.factory.Foo;
import com.zyff.repository.AccountRepository;
import com.zyff.service.MyService;
import com.zyff.service.TransferService;

@Configuration
@ComponentScan(basePackages = "com.zyff")
public class AppConfig {
	
	/**
	 * 与<bean id="myService" class="com.zyff.service.MyService"/>一致
	 * @return
	 */
	@Bean
	public MyService myService(){
		return new MyService();
	}
	
	@Bean
	public TransferService transferService(AccountRepository accountRepository){
		return new TransferService(accountRepository);
	}
	
	@Bean(initMethod = "init")
	public Foo foo() {
		return new Foo();
	}
	
	@Bean(destroyMethod = "destory")
	public Bar bar() {
		return new Bar();
	}
	
	@Bean
	public Factory factory() {
		Factory factory = new Factory();
		factory.init();
		return factory;
	}
}
