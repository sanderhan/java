package com.filegenerator.format;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.filegenerator.format.spec.CSVFileSpecification;
import com.filegenerator.format.spec.FileSpecification;

public class CSVFileFormat 
	extends FileFormat {

	
	public CSVFileFormat() {
		
	}
	
	public CSVFileFormat(CSVFileSpecification spec) {
		super(spec);
	}
	
	private boolean isTextField(Object value) {
		if(value instanceof String) {
			boolean isNumeric = StringUtils.isNumeric(value.toString());
			
			return isNumeric?false:true;
		}
		return false;
	}
	
	private String formatRow(List<Object> row, CSVFileSpecification spec) {
		StringJoiner sj = new StringJoiner(spec.getDelimiter(),"", spec.getRowTerminator());
		for(Object col : row) {
			String value = col.toString();
			boolean needQuoted
				= spec.isQuotedOnAllFields() 
					|| (isTextField(col) && spec.isQuoteOnTextFields()) 
					? true:false;
			String line = needQuoted ? String.format("\"%s\"", value) : value;
			sj.add(line);
		}
		return sj.toString();
		
	}
	@Override
	public String format(List<List<Object>> dataset, FileSpecification spec) {
		CSVFileSpecification csvSpec = (CSVFileSpecification)spec;
		
		StringBuilder sb = new StringBuilder();
		for(List<Object> row : dataset) {
			String line = formatRow(row, csvSpec);
			sb.append(line);
		}
		return sb.toString();
	}

	private String getHeaderLine(List<String> headers, CSVFileSpecification spec) {
		StringJoiner sj = new StringJoiner(spec.getDelimiter(),"", spec.getRowTerminator());
		for(String header : headers) {
			String line = spec.isQuotedOnAllFields() ? String.format("\"%s\"", header) : header;
			sj.add(line);
		}
		return sj.toString();
	}
	
	@Override
	public String format(List<Map<String, Object>> dataset, List<String> headers, FileSpecification spec) {
		CSVFileSpecification csvSpec = (CSVFileSpecification)spec;
		
		StringBuilder sb = new StringBuilder();
		if(csvSpec.isHeaderRequired()) {
			sb.append(getHeaderLine(headers,csvSpec));
		}
		
		for(Map<String, Object> row : dataset) {
			List<Object> cols = headers.stream()
									.map(header->row.getOrDefault(header,""))
									.collect(Collectors.toList());
			
			String line = formatRow(cols, csvSpec);
			sb.append(line);
		}
		return sb.toString();
	}

}
