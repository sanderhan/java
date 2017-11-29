package com.practice.spring.api.fileservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fileservice")
public class FileServiceController {

	@Autowired
	private IFileService fileService;
	
	@RequestMapping(value="/generate",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public File generate (@RequestParam String type, @RequestBody List<DataSet> dataSets) {
		return fileService.generateFile(type, dataSets);
	}
}
