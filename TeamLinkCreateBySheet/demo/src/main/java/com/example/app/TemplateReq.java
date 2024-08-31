package com.example.app;

import java.util.List;

public class TemplateReq {

	public String subject;
	public DateTimeClassss startTime;
	public DateTimeClassss endTime;
	public String content;
	public List<Attendee> attendeeEmailAddressList;
	public String meetingType;
	public String createdBy = "160666";
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public DateTimeClassss getStartTime() {
		return startTime;
	}
	public void setStartTime(DateTimeClassss startTime) {
		this.startTime = startTime;
	}
	public DateTimeClassss getEndTime() {
		return endTime;
	}
	public void setEndTime(DateTimeClassss endTime) {
		this.endTime = endTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Attendee> getAttendeeEmailAddressList() {
		return attendeeEmailAddressList;
	}
	public void setAttendeeEmailAddressList(List<Attendee> attendeeEmailAddressList) {
		this.attendeeEmailAddressList = attendeeEmailAddressList;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	
	@Override
	public String toString() {
		return "{ meetingId : " +null + ", "+ "subject : " +subject+ ", "+ "startTime : "  +startTime+", "+ "endTime : "+endTime+ ", "+ "content : "+content +","+ "attendeeEmailAddressList : "+attendeeEmailAddressList+", "+"meetingType : "+ meetingType+", "+ "createdBy : "+createdBy+ " } ";		 
		
	} 
	
	
	
	
}
