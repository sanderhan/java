package com.practice.spring.FileService.common;

import lombok.Data;

import java.util.Iterator;
import java.util.List;
@Data

public class FileContent {

	String fileName;
	List <FileLine> lines;
	
	public String toSting() {
		StringBuilder sb = new StringBuilder();
		Iterator<FileLine> it = lines.iterator();
		while(it.hasNext()) {
			sb.append(it.next().toString())
			  .append("\n");
		}
		
		return sb.toString();
	}
	
}
