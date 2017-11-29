package com.filegenerator.format.spec;

import lombok.Data;

@Data
public class CSVFileSpecification 
	extends FileSpecification {
	
	private CharSequence delimiter = ",";
	private CharSequence rowTerminator ="\n";
	
	private boolean quotedOnAllFields = false;
	private boolean quoteOnTextFields = true;
	
	private boolean headerRequired = true;
	
}
