package com.example.app;

public class Attendee {
	public String address;
	public String name = "Aniket Kannallu";
	
	public Attendee() {
		
	}

	public Attendee(String address) {
		super();
		this.address = address;
	}

	@Override
	public String toString() {
		return " { "+"address:" + address + ", "+"name:" + name + "} ";
	}
	
	
	  

	
}
