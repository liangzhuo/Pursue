package com.liang.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.liang.io.OldIOAndNewIO;

public class CopyRunnable implements Runnable{

	@Override
	public void run() {
		OldIOAndNewIO oldAndNew = new OldIOAndNewIO();
		String sourcePath = "E://A.mp4";
		String destPath = "D://test//A.mp4";
		long startTime = System.currentTimeMillis();
		//oldAndNew.oldCopy(sourcePath, destPath);
		oldAndNew.newCopy(sourcePath, destPath);
		//oldAndNew.mapMemeryBufferCopy(sourcePath, destPath);
		long endTime = System.currentTimeMillis();
		System.out.println("花费时间：" + (endTime - startTime));
	}
	
	public static void main(String[] args) {
		Runnable copyRunnable = new CopyRunnable();
		Thread thread = new Thread(copyRunnable);
		thread.start();
	}

}
