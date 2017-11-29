package com.practice.spring.api.fileservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class FileService implements IFileService {

	public String csvFormatter(List<Object> columns, String delimeter, String rowTerminater ) {
		StringBuilder sb = new StringBuilder();
		
		columns.stream().forEach(col -> sb.append(col).append(delimeter));
		sb.append(rowTerminater);
		return sb.toString();
	}
	
	@Override
	public File generateFile(String type, List<DataSet> dataSets) {
		String fileName = String.format("FileType_%s", type);
		List<String> lines = new ArrayList<String>();
		
		
		for(DataSet dataSet :dataSets) {
			List<DataRow>rows = dataSet.getRows();
			
			List<String> dataSetLines = rows.stream()
											.map(row -> csvFormatter(row.getColumns(),",","\n"))
											.collect(Collectors.toList());
			lines.addAll(dataSetLines);
			
		}
		FileContent fileContent = new FileContent(lines);
		File file = new File(fileName, fileContent);
		return file;
	}

}
