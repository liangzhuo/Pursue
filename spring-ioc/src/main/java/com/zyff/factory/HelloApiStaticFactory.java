package com.zyff.factory;

import com.zyff.spring.HelloApi;
import com.zyff.spring.HelloImpl2;

public class HelloApiStaticFactory {
	public static HelloApi newInstance(String message){
		return new HelloImpl2(message);
	}
}
