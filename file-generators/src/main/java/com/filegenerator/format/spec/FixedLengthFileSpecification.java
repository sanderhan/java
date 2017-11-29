package com.filegenerator.format.spec;

import java.util.List;

import lombok.Data;

@Data
public class FixedLengthFileSpecification {
	
	
	List<FixedLengthFieldSpecification> fieldSpecs;
	
}
