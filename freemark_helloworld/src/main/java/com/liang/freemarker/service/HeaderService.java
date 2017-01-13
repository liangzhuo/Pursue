package com.liang.freemarker.service;

import com.liang.freemarker.ftl.model.Header;

public class HeaderService {
	private static Header header = new Header();
	
	static{
		header.setAddress("ÉîÛÚ");
		header.setCompanyName("ANVN");
	}
	
	public static void update(String address, String companyName){
		header.setAddress(address);
		header.setCompanyName(companyName);
	}
	
	public static Header getHeader(){
		return header;
	}
}
