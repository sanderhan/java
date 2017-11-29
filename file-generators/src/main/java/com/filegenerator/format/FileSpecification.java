package com.filegenerator.format;

import lombok.Data;

@Data
public class FileSpecification {
	public static enum Lookup {
		SEQUENCE,NAME
	}
	
	Lookup lookup = Lookup.SEQUENCE;
	
}
