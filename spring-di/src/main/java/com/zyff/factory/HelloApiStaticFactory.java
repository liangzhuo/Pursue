package com.zyff.factory;

import com.zyff.spring.HelloApi;
import com.zyff.spring.HelloImpl;

public class HelloApiStaticFactory {
	public static HelloApi newInstance(String message, int index){
		return new HelloImpl(message, index);
	}
}
