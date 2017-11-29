package com.practice.spring.api.fileservice;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DataSet implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -346229729544647395L;
	private List<String> headers;
	private List<DataRow>rows;
	
	public DataSet() {
		
	}
	public DataSet(List<DataRow> rows) {
		this.rows = rows;
	}
	
	public DataSet(List<String>headers,List<DataRow> rows) {
		this.headers = headers;
		this.rows = rows;
	}
}
