package com.liang.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Stream {
	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream(new File("D:\\test\\test.txt"));
		// 获取当前读入的字节数量
		int byteAvailable = in.available();
		if (byteAvailable > 0) {
			byte[] data = new byte[byteAvailable];
			in.read(data);
			System.out.println(data);
		}
		in.close();
	}
}
