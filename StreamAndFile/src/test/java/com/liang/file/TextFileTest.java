package com.liang.file;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TextFileTest {
	public static void main(String[] args) {
		
	}
	
	private static void writeData(Employee[] employees, PrintWriter out) {
		
	}
	
	private static Employee[] readData(Scanner in) {
		return null;
	}
	
	class Employee {
		private String name;
		private double salary;
		private Date hireDay;
		
		public Employee() {
			
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public Date getHireDay() {
			return hireDay;
		}

		public void setHireDay(Date hireDay) {
			this.hireDay = hireDay;
		}
		
		public void raiseSalay(double byPercent) {
			double raise = salary * byPercent / 100;
			salary += raise;
		}

		@Override
		public String toString() {
			return "Employee [name=" + name + ", salary=" + salary
					+ ", hireDay=" + hireDay + "]";
		}
		
		public void writeData(PrintWriter out) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(hireDay);
			out.println(name + "|" + salary + "|" + calendar.get(Calendar.YEAR) + "|" + 
						(calendar.get(Calendar.MONTH)+1) + "|" +
						calendar.get(Calendar.DAY_OF_MONTH));
		}
		
		public void readData(Scanner in) {
			
		}
		
		
	}
}
