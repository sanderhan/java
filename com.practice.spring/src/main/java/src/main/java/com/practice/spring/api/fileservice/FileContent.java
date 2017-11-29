package com.practice.spring.api.fileservice;

import java.util.List;

import lombok.Data;

@Data
public class FileContent {

	List<String>lines;
	
	public FileContent(List<String> lines) {
		this.lines = lines;
	}
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		for(String line : lines) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}
	
}
