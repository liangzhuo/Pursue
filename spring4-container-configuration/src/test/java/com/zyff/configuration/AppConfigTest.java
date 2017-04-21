package com.zyff.configuration;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zyff.component.Component1;
import com.zyff.service.MyService;
import com.zyff.service.MyService1;
import com.zyff.service.MyService2;
import com.zyff.service.TransferService;

public class AppConfigTest extends TestCase {
	
	@Test
	public void testConfiguration() {
		ApplicationContext cx = new AnnotationConfigApplicationContext(AppConfig.class);
		MyService myService = cx.getBean(MyService.class);
		myService.say();
	}
	
	@Test
	public void testConfigurationAndComponent() {
		ApplicationContext cx = new AnnotationConfigApplicationContext(AppConfig.class, Component1.class);
		MyService myService = cx.getBean(MyService.class);
		myService.say();
		
		Component1 component1 = cx.getBean(Component1.class);
		component1.say();
	}
	
	@Test
	public void testRegister() {
		AnnotationConfigApplicationContext cx = new AnnotationConfigApplicationContext();
		cx.register(AppConfig.class, OtherConfig.class);
		cx.register(AdditionalConfig.class);
		cx.refresh();
		MyService1 myService1 = cx.getBean(MyService1.class);
		myService1.say();
	}
	
	@Test
	public void testComponentScan() {
		ApplicationContext cx = new AnnotationConfigApplicationContext(AppConfig.class);
		MyService2 myService2 = cx.getBean(MyService2.class);
		myService2.say();
	}
	
	@Test
	public void testComponentScan1() {
		AnnotationConfigApplicationContext cx = new AnnotationConfigApplicationContext();
		cx.scan("com.zyff");
		cx.refresh();
		MyService2 myService2 = cx.getBean(MyService2.class);
		myService2.say();
	}
	
	@Test
	public void testBeanDependencies() {
		ApplicationContext cx = new AnnotationConfigApplicationContext(AppConfig.class);
		TransferService transferService = cx.getBean(TransferService.class);
		transferService.say();
	}
	
	@Test
	public void testInitAndDestory() {
		AnnotationConfigApplicationContext cx = new AnnotationConfigApplicationContext(AppConfig.class);
		cx.close();
	}
}
