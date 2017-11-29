package com.practice.spring.FileService.common;

import lombok.Data;
import java.util.List;

@Data

public class FileLine {

	String row;
	
	public String toString(){
		return row;
	}
	
}
