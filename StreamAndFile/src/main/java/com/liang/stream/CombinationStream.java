package com.liang.stream;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CombinationStream {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("D:\\test\\employee.dat");
			DataInputStream dis = new DataInputStream(fis);
			double s = dis.readDouble();
			System.out.println(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
