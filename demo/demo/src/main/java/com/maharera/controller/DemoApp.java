package com.maharera.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/v1")
public class DemoApp {

	private static final Logger logger = LoggerFactory.getLogger(DemoApp.class);
	private static final RestTemplate restTemplate = new RestTemplate();
	
	public static void execute(String txtFilePath, String url) {
//		
//		String txtFilePath = "path/File.txt";
//		
//		String url = "http://xyz";
		
		try{
			
			List<String> documentIds = Files.readAllLines(Paths.get(txtFilePath));
			
			for(String docId : documentIds) {
				callApi(url, docId);
				removeDocId(txtFilePath, docId);				
			}
			
		}catch(IOException e) {
			logger.error("error in reading or processing the file", e);
		}
			
	}

	private static void removeDocId(String txtFilePath, String docId) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(txtFilePath));
			
			List<String> updatedLines = lines.stream()
					.filter(line -> !line.trim().equals(docId))
					.collect(Collectors.toList());
			
			Files.write(Paths.get(txtFilePath), updatedLines);
			
		}catch(IOException e) {
			logger.error("error in removing document Id from the file "+ docId , e);
		}
	}

	private static void callApi(String url, String docId) {
		String fullUrl = url + docId ;
		
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			
			logger.info("Response for document ID {} : ", docId, response.getStatusCode());
			
			
		}catch(RestClientException e) {
			logger.error("Error calling api for document ID " + docId, e);
		}
		
	}
}
