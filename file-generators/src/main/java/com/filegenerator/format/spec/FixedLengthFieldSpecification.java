package com.filegenerator.format.spec;

import lombok.Data;

@Data
public class FixedLengthFieldSpecification 
	extends FieldSpecification {
	
	int offset;
	int length;
	
}
