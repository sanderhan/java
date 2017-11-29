package com.filegenerator.format;

import lombok.Data;

@Data
public class FixedLengthFieldSpecification 
	extends FieldSpecification {
	
	int offset;
	int length;
	
}
