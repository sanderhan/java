package com.practice.spring.api.fileservice;

import java.util.List;

public interface IFileService {
	public File generateFile(String type, List<DataSet> dataSets);
}
