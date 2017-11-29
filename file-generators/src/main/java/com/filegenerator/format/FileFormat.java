package com.filegenerator.format;

import java.util.List;
import java.util.Map;

public abstract class FileFormat {
	
	public static enum Type {
		BINARY,PLAIN_TEXT,FIXED_LENGTH,CSV,ACH,EDI820,CCD,PPD,CTX ,JSON,XML
	}

	private FileSpecification fileSpec;
	
	public FileFormat() {
		
	}
	
	public FileFormat(FileSpecification spec) {
		this.fileSpec = spec;
	}

	public String format(List<List<Object>> dataset) {
		return format(dataset, fileSpec);
	};
	public String format(List<Map<String,Object>> dataset,List<String> headers) {
		return format(dataset, headers, fileSpec);
	};

	public abstract String format(List<List<Object>> dataset, FileSpecification spec);
	public abstract String format(List<Map<String,Object>> dataset,List<String> headers,FileSpecification spec);
	
	
}
