package com.liang.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class OldIOAndNewIO {
	/**
	 * 原IO读写方式（性能一般）
	 * @param sourcePath
	 * @param destPath
	 */
	public void oldCopy(String sourcePath, String destPath) {
		try {
			File sourceFile = new File(sourcePath);
			File destFile = new File(destPath);
			
			FileInputStream fis = new FileInputStream(sourceFile);
			FileOutputStream fos = new FileOutputStream(destFile);
			
			byte[] buff = new byte[1024];
			int len = 0;
			while((len = fis.read(buff)) != -1) {
				fos.write(buff, 0, len);
			}
			fos.flush();
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * NewIO读取文件，性能有一定提升（推荐使用）
	 * @param sourcePath
	 * @param destPath
	 */
	public void newCopy(String sourcePath, String destPath) {
		try {
			File source = new File(sourcePath);
			File dest = new File(destPath);
			FileChannel in = new FileInputStream(new File(sourcePath)).getChannel();
			FileChannel out = new FileOutputStream(new File(destPath)).getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
			
			while (in.read(buffer) != -1) {
				buffer.flip(); // 准备写
				out.write(buffer);
				buffer.clear(); // 清掉缓存中的数据
			}
			
			out.close();
			in.close();
			source.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 网上说该方法复制大文件很快，用快2G的文件测试，没有传统的IO快
	 * 文件内存映射方案
	 * @param sourcePath
	 * @param destPath
	 */
	@SuppressWarnings("resource")
	public void mapMemeryBufferCopy(String sourcePath, String destPath) {
		File source = new File(sourcePath);
		File dest = new File(destPath);
		FileChannel in = null;
		FileChannel out = null;
		try {
			in = new FileInputStream(source).getChannel();
			out = new FileOutputStream(dest).getChannel();
			
			long size = in.size();
			
			MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
			out.write(buf);
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		OldIOAndNewIO oldAndNew = new OldIOAndNewIO();
		//String sourcePath = "E://A.mp4";
		String sourcePath = "D://test//A.mp4";
		//String destPath = "D://test//A.mp4";
		String destPath = "E://A.mp4";
		long startTime = System.currentTimeMillis();
		//oldAndNew.oldCopy(sourcePath, destPath);
		//oldAndNew.newCopy(sourcePath, destPath);
		oldAndNew.mapMemeryBufferCopy(sourcePath, destPath);
		long endTime = System.currentTimeMillis();
		System.out.println("花费时间：" + (endTime - startTime));
	}
}
