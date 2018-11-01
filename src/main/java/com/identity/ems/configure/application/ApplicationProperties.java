package com.identity.ems.configure.application;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ApplicationProperties.PREFIX)
public class ApplicationProperties {
	
	public static final String PREFIX = "ems.application"; 
	
	int limitRangeMinValue;
	int limitRangeMaxValue;
	int calendarAddMinute;
	
	public int getLimitRangeMinValue() {
		return limitRangeMinValue;
	}

	public void setLimitRangeMinValue(int limitRangeMinValue) {
		this.limitRangeMinValue = limitRangeMinValue;
	}

	public int getLimitRangeMaxValue() {
		return limitRangeMaxValue;
	}

	public void setLimitRangeMaxValue(int limitRangeMaxValue) {
		this.limitRangeMaxValue = limitRangeMaxValue;
	}

	public int getCalendarAddMinute() {
		return calendarAddMinute;
	}

	public void setCalendarAddMinute(int calendarAddMinute) {
		this.calendarAddMinute = calendarAddMinute;
	}
}
