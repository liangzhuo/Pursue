package com.zyff.spring;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest extends TestCase{

	public HelloWorldTest( String testName ){
        super( testName );
    }

    public static Test suite(){
        return new TestSuite( HelloWorldTest.class );
    }

    public void testApp(){
        assertTrue( true );
    }
    
    @org.junit.Test
    public void testHello(){
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	HelloApi hello = ac.getBean("hello",HelloApi.class);
    	hello.sayHello();
    }
}
