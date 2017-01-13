package com.liang.springmvc.web.controller.support.fomatter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.liang.springmvc.model.PhoneNumberModel;

public class PhoneNumberFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<PhoneNumber>{

	private final Set<Class<?>> fieldTypes;
	private final PhoneNumFormatter formatter;
	
	public PhoneNumberFormatAnnotationFormatterFactory() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(PhoneNumberModel.class);
		this.fieldTypes = set;
		this.formatter = new PhoneNumFormatter();
	}
	
	public Set<Class<?>> getFieldTypes() {
		return fieldTypes;
	}

	public Printer<?> getPrinter(PhoneNumber annotation, Class<?> fieldType) {
		return formatter;
	}

	public Parser<?> getParser(PhoneNumber annotation, Class<?> fieldType) {
		return formatter;
	}

}
