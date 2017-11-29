package com.practice.spring.FileService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spring.FileService.common.*;
@RestController

public class FileServiceController {

	@RequestMapping("/fileservice/generatefile")
	public FileContent generateFile(@RequestParam(value="config")FileConfiguration filfConfig) {
	   return new FileContent();	
	}
}
