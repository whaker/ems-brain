package com.identity.ems.configure.application;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ApplicationProperties.PREFIX)
public class ApplicationProperties {
	
	public static final String PREFIX = "ems.application"; 
	
	int calendarAddMinute;
	
	public int getCalendarAddMinute() {
		return calendarAddMinute;
	}

	public void setCalendarAddMinute(int calendarAddMinute) {
		this.calendarAddMinute = calendarAddMinute;
	}
}
