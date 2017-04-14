package com.liang.decorator.way2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputTest {
	public static void main(String[] args) {
		int c;
		try {
			String filePath = Thread.currentThread().getContextClassLoader().getResource("com/liang/decorator/way2/test.txt").getPath();
			// 获取当前目录路径
//			File directory = new File("");
//			String filePath = directory.getAbsolutePath();
//			System.out.println(filePath);
			InputStream in = new LowerCaseInputStream(
					new BufferedInputStream(
							new FileInputStream(filePath)));
			
			while ((c = in.read()) > 0) {
				System.out.print((char)c);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
