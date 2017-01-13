package com.zyff.spring.dependson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResourceBean {
	private FileOutputStream fos;
	private File file;
	
	public void init(){
		System.out.println("ResourceBean:###########初始化");
		//加载资源，此处只是演示
		System.out.println("ResourceBean:###########加载资源，执行一些预操作");
		try {
			this.fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy(){
		System.out.println("ResourceBean:###########销毁");
		//加载资源，此处只是演示
		System.out.println("ResourceBean:###########释放资源，执行一些清理操作");
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public FileOutputStream getFos() {
		return fos;
	}

	public void setFos(FileOutputStream fos) {
		this.fos = fos;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
