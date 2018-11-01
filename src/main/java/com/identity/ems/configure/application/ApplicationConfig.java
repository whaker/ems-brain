package com.identity.ems.configure.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
class ApplicationConfig {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Bean
	public int limitRangeMinValue() {
		return applicationProperties.getLimitRangeMinValue();
	}

	@Bean
	public int limitRangeMaxValue() {
		return applicationProperties.getLimitRangeMaxValue();
	}

	@Bean
	public int calendarAddMinute() {
		return applicationProperties.getCalendarAddMinute();
	}
}