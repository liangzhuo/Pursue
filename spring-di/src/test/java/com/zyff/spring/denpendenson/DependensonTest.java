package com.zyff.spring.denpendenson;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zyff.spring.dependson.DependentBean;

public class DependensonTest {
	@Test
	public void denpendensonTest() throws IOException{
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("dependens-on.xml");
		//需注册销毁回调，否则自己定义的销毁方法不执行
		ac.registerShutdownHook();
		DependentBean dependentBean = ac.getBean("dependentBean",DependentBean.class);
		dependentBean.write("aaaa");
	}
}
