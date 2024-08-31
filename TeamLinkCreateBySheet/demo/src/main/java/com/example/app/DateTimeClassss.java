package com.example.app;

public class DateTimeClassss {
	
	public String oDataType = "";
	public String dateTime;
	public String timeZone = "India Standard Time";
	
	public DateTimeClassss(String dateTime) {
		super();
		this.dateTime = dateTime;
	}
	
	
	public DateTimeClassss() {
		
	}


	@Override
	public String toString() {
		return "{ "+"oDataType : " + oDataType + ", "+"dateTime : " + dateTime + ", "+"timeZone : " + timeZone + "}";
	}
	
	
	
}
