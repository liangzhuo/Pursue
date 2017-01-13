package com.liang.freemarker.service;

import com.liang.freemarker.ftl.model.Footer;

public class FooterService {
	private static Footer f = new Footer();
	
	static{
		f.setDes("北京真的好吗");
	}
	
	public static void update(String des){
		f.setDes(des);
	}
	
	public static Footer getFooter(){
		return f;
	}
}
