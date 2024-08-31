package com.example.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
//import javax.servlet.http.HttpServletRequest;

public class DemoApp {
	

    public static void execute() {
        String excelFilePath = "C://Users//vmrerauser26//Desktop//LiveCauselistHearingLink.xlsx";
//        String apiUrl = "https://maharerait.maharashtra.gov.in/api/maha-rera-integration-service/integration/createEvent";
        String apiUrl = "http://devmaharera.in/api/maha-rera-integration-service/integration/createEvent";
        String authToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIzNHFoLWJQZ1Nyck5WdG92Z1FROUhuX3JfZHhGeV9mUDVJVjkzT1VXMVVjIn0.eyJleHAiOjE3MjUwOTQ4MzgsImlhdCI6MTcyNTA4ODIzOCwianRpIjoiMmRhZWQ5MGUtZThlMi00NWYxLWE3YjktMDAyYTVkMWU3ZDE3IiwiaXNzIjoiaHR0cDovL2JhY2tlbmQtc3RhbmRhbG9uZS1rZXljbG9hay1zdmMtdWF0OjgwODkvYXV0aC9yZWFsbXMvZGVtbyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI1MGFhYTIxZC0wMTFiLTQ0N2EtOGEzNy0wNDliYmZiMWVmNmMiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vY2xpZW50MSIsInNlc3Npb25fc3RhdGUiOiJiN2Y2MWIyMi1lYWRiLTQwM2ItOTg0Ny1jZmVkYTYzZDY5MmMiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiYXBwX2FnZW50IiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJkZW1vY2xpZW50MSI6eyJyb2xlcyI6WyJBR0VOVCJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiUEFWQU4gQkFKQUoiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJwYXZhbmIiLCJnaXZlbl9uYW1lIjoiUEFWQU4iLCJmYW1pbHlfbmFtZSI6IkJBSkFKIn0.M5GVPCRjNlbJtSo_LgBC_xSb6vSDwtMgKpMaQy7cDxN0w_6-1fsPB55CKyADxo7669-vlkzRkAAIaCtF7wftkEvsc_w2BWpi0oL4IewiHozAUd-y3wmWSRZcaYZYWOsIXNmVR_P5M0C5kVNJHCNvuzE9nxkKUnCToTjddV_m5ULC89HVWnLeDeapltz3DbotBG-2pXvfj963nP-WlyFzsLibhIV-gYKVMjhfmo1tweSBzcZSnlj6B5FLg2579XnfJfgLDC7LSRXJ02EtsOKxxJnsMsVM6lo18GBzTz2BM0heAqUpaWz2JklYOV2ZLDfs10ryNg28jsOdQDAjlpFn5Q";
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
 
            Sheet sheet = workbook.getSheetAt(0);
            RestTemplate restTemplate = new RestTemplate();         
            int i = 0;
            for (Row row : sheet) {
            	int count = 0 ;
//                Map<String, Object> requestBody = new HashMap<>();
                TemplateReq requestBody = new TemplateReq();
               
//                Map<String, Object> endTimeMap = new HashMap<>();
              
                i++;
                
                LocalDate localDate=null;
                for (Cell cell : row) {
                	                	
                	count++;
                    String columnName = sheet.getRow(1).getCell(cell.getColumnIndex()).getStringCellValue();
                    
                    if( cell.getCellType() == CellType.NUMERIC && (count-1)==0 ) {
                    	Date date = cell.getDateCellValue();
                        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

//                        System.out.println("Date : "+localDate);
                    }
                    if( cell.getCellType() == CellType.NUMERIC && ((count-1)==1 || (count-1)==2)) {
                    	Date time = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//                        System.out.println(sdf.format(time));
                        
                        if(count==2) {
                        	 DateTimeClassss startTime = new DateTimeClassss(""+localDate+"T"+sdf.format(time)+"");
//                        	 requestBody.put("startTime", startTime);
                        	 requestBody.setStartTime(startTime);
                        }
                        
                        if(count==3) {
                       	 DateTimeClassss endTime = new DateTimeClassss(""+localDate+"T"+sdf.format(time)+"");
//                       	 requestBody.put("endTime", startTime);
                       	 requestBody.setEndTime(endTime);
                       }
                    	
                    }
                    
                    
                    if(count == 4) continue;
//                    	requestBody.put(columnName, getCellValue(cell));
                    	
                    
//                    System.out.println(getCellValue(cell) + " is of type : " + cell.getCellType());
                    if(count==6) {
                    	List<Attendee> list = new ArrayList<>();
                    	String email = (String) getCellValue(cell);
                    	Attendee attend = new Attendee(email);
                    	                    	
                    	list.add(attend);
//                    	requestBody.put( "attendeeEmailAddressList", list);
                    	requestBody.setAttendeeEmailAddressList(list);
                    }
//                    requestBody.put( "meetingType", "HEARING");
//                    requestBody.put( "content", "");
//                    requestBody.put("subject", "Hearing");
                    
                    requestBody.setContent("");
                    requestBody.setSubject("Hearing");
                    requestBody.setMeetingType("HEARING");
                    if(count == 6) break;
                }
                
                System.out.println("requestBody : "+requestBody);
                
             
                if(i==1) {
            		
            		continue;
            	}
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/json");
                headers.set("Authorization", "Bearer " + authToken); 
                HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);
 
                ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
//                ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
                System.out.println("Response: " + response.getBody());
                
//    			headers.set(HttpHeaders.AUTHORIZATION, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
//    			HttpEntity<?> requestEntity = new HttpEntity<>(userProfileMasterRequest, headers);
//    			ServiceResponse response = restTemplate.postForObject(getUserProfileMasterDetails, requestEntity,
//    					ServiceResponse.class);
//    			if (response != null && response.getResponseObject() != null) {
//    				userProfileMaster = mapper.convertValue(response.getResponseObject(),
//    						new TypeReference<UserProfileMaster>() {
//    						});
//    			}
                
                if(response.getStatusCode()==HttpStatusCode.valueOf(200)) {
                	System.out.println("Succesfull execution");
                }
            }
            
            
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }

}
