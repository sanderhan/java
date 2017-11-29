package com.practice.spring.api.fileservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DataRow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5550535715837618277L;
	private List<String> headers;
	private List<Object> columns;
	
	public DataRow() {
		
	}
	
	public DataRow(List<String>headers, List<Object> columns ) {
		this.headers = headers ;
		this.columns = columns;
	}
	
	public void addColumn(Object column) {
		if(columns == null) {
			columns = new ArrayList<Object>();
		}
		
		columns.add(column);
	}
	
}
