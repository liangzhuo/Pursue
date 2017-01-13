package com.zyff.spring;


import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest extends TestCase{

    
    @Test
    public void testHello(){
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	//不指定id，全限定类名
    	HelloApi hello = ac.getBean(HelloApi.class);
    	hello.sayHello();
    }
    
    @Test
    public void testHello1(){
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	//指定id
    	HelloApi hello = ac.getBean("hello",HelloApi.class);
    	hello.sayHello();
    }
    
    @Test
    public void testHello2(){
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	//根据id获取
    	HelloApi hello1 = ac.getBean("hello1",HelloApi.class);
    	hello1.sayHello();
    	//根据别名获取
    	HelloApi alias1 = ac.getBean("alias1",HelloApi.class);
    	alias1.sayHello();
    	//根据id获取
    	HelloApi hello = ac.getBean("hello",HelloApi.class);
    	hello.sayHello();
    	
    	String[] helloAlias = ac.getAliases("hello");
    	//因此别名不能和id一样，如果一样则由IoC容器负责消除冲突
    	Assert.assertEquals(0, helloAlias.length);
    }
    
    @Test
    public void testHello3(){
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	//根据id获取
    	HelloApi bean1 = ac.getBean("bean1",HelloApi.class);
    	bean1.sayHello();
    	//根据别名获取
    	HelloApi alias1 = ac.getBean("alias11",HelloApi.class);
    	alias1.sayHello();
    	
    	String[] bean1Alias = ac.getAliases("bean1");
    	System.out.println("=====applicationContext.xml bean1别名");
    	for(String alias : bean1Alias){
    		System.out.println(alias);
    	}
    	Assert.assertEquals(3, bean1Alias.length);
    	
    	
    	//根据id获取
    	HelloApi bean2 = ac.getBean("bean2",HelloApi.class);
    	bean2.sayHello();
    	//根据别名获取
    	HelloApi alias21 = ac.getBean("alias21",HelloApi.class);
    	alias21.sayHello();
    	
    	String[] bean2Alias = ac.getAliases("bean2");
    	System.out.println("=====applicationContext.xml bean2别名");
    	for(String alias : bean2Alias){
    		System.out.println(alias);
    	}
    	Assert.assertEquals(3, bean1Alias.length);
    }
    
    @Test
    public void testHello4(){
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	//根据id获取
    	HelloApi bean1 = ac.getBean("bean1",HelloApi.class);
    	bean1.sayHello();
    	//根据别名获取
    	HelloApi alias1 = ac.getBean("alias1",HelloApi.class);
    	alias1.sayHello();
    	
    	String[] bean1Alias = ac.getAliases("bean1");
    	for(String alias : bean1Alias){
    		System.out.println(alias);
    	}
    	Assert.assertEquals(2, bean1Alias.length);
    	
    }
}
