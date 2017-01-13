package com.liang.stream;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TextStream {
	
	public void writeData(PrintWriter out) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		out.println(calendar.get(Calendar.YEAR) + "|" + (calendar.get(Calendar.MONTH)+1) + "|" + (calendar.get(Calendar.DAY_OF_MONTH)));
	}
	
	public void readData(Scanner in) {
		String line = in.nextLine();
		String[] tokes = line.split("\\|");
	}
}
