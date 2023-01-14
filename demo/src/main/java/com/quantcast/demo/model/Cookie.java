package com.quantcast.demo.model;

import java.util.Date;

public class Cookie {
	
	private String cookie;
	private Date timeStamp;
	
	public Cookie(String cookie, Date timeStamp) {
		super();
		this.cookie = cookie;
		this.timeStamp = timeStamp;
	}

	public String getCookie() {
		return cookie;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}
}
