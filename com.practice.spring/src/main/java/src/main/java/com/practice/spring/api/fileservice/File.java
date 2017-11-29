package com.practice.spring.api.fileservice;

import lombok.Data;

@Data
public class File {
	String fileName;
	FileContent fileContent;
	
	public File() {
		
	}
	public File(String fileName, FileContent fileContent) {
		this.fileName = fileName;
		this.fileContent = fileContent;
	}
}
