package com.filegenerator.format.spec;

import lombok.Data;

@Data
public class FieldSpecification {

	public static enum DataType {
		NUMERIC, DATE, TEXT
	}
	
	int sequence;
	String name;
	String type;
	String format;
	String descriptor;
	
}
