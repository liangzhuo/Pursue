package com.zyff.spring.dependson;

import java.io.IOException;

public class DependentBean {
	private ResourceBean resourceBean;
	
	public void write(String ss) throws IOException{
		System.out.println("DependentBean:###########写资源");
		resourceBean.getFos().write(ss.getBytes());
	}
	
	public void init() throws IOException{
		System.out.println("DependentBean:###########初始化");
		resourceBean.getFos().write("DependentBean:###########初始化".getBytes());
	}
	
	public void destroy() throws IOException{
		System.out.println("DependentBean:###########销毁");
		resourceBean.getFos().write("DependentBean:###########销毁".getBytes());
	}

	public ResourceBean getResourceBean() {
		return resourceBean;
	}

	public void setResourceBean(ResourceBean resourceBean) {
		this.resourceBean = resourceBean;
	}
	
}
