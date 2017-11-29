package com.filegenerator.format;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.filegenerator.format.spec.CSVFileSpecification;

import org.junit.Assert;

class CSVFileFormatTest  {

	private List<List<Object>> dataSetWithoutHeaders =  new ArrayList<List<Object>>();
	private List<Map<String,Object>> dataSetWithHeaders =  new ArrayList<Map<String,Object>>();
	private List<String> headers =  Arrays.asList("id","name","date","value1","value2");
	
	@BeforeEach
	void setUp() throws Exception {
		List<Object> row1 = Arrays.asList(1, "Row 1", new Date(), 12.31, "301");
		List<Object> row2 = Arrays.asList(2, "Row 2", new Date(), 0, "0");
		dataSetWithoutHeaders.add(row1);
		dataSetWithoutHeaders.add(row2);
		
		Map<String,Object> mRow1 = new HashMap() { 
			{
				put("id", 1);
				put("name" , "Row 1");
				put("date", new Date());;
				put("value1",15.2);
			}
		};
		Map<String,Object> mRow2 = new HashMap() { 
			{
				put("id", 2);
				put("name" , "Row 2");
				put("date", "2017-01-01");
				put("value1",2);
				put("value2",-1);
			}
		};
		
		dataSetWithHeaders.add(mRow1);
		dataSetWithHeaders.add(mRow2);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFormatListOfListOfObjectFileSpecification() {
		CSVFileSpecification spec = new CSVFileSpecification();
		CSVFileFormat format = new CSVFileFormat(spec);
		String actual = format.format(dataSetWithoutHeaders);
		
		String expected = "1,\"Row 1\",Tue Nov 28 22:41:45 PST 2017,12.31,301\\n2,\"Row 2\",Tue Nov 28 22:41:45 PST 2017,0,0\\n";
		Assert.assertEquals(expected, actual);
	}

	@Test
	void testFormatListOfMapOfStringObjectListOfStringFileSpecification() {
		CSVFileSpecification spec = new CSVFileSpecification();
		CSVFileFormat format = new CSVFileFormat(spec);
		String actual = format.format(dataSetWithHeaders, headers) ;
		
		String expected ="id,name,date,value1,value2\\n1,\"Row 1\",Tue Nov 28 22:52:57 PST 2017,15.2,\"\"\\n2,\"Row 2\",\"2017-01-01\",2,-1\\n";
		Assert.assertEquals(expected, actual);
	}

}
