package com.identity.ems.tasks;

import com.identity.ems.processor.BemsBrainProcessor;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class BemsBrainTask {

	private static final Logger logger = LoggerFactory.getLogger(BemsBrainTask.class);

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");

	@Autowired
	int calendarAddMinute;

	@Autowired
	BemsBrainProcessor bemsBrainProcessor;

	@Scheduled(cron = "${ems.application.cron.expression.real-analysis}")
	public void runForReal() {
		Calendar cal = Calendar.getInstance();
		if (calendarAddMinute != 0) {
			cal.add(Calendar.MINUTE, calendarAddMinute);
		}
		int minute = cal.get(Calendar.MINUTE);
		cal.set(Calendar.MINUTE, minute - (minute % 15));

		logger.info("Run real statistics. date : {}, time : {}", dateFormat.format(cal.getTime()), timeFormat.format(cal.getTime()));
		bemsBrainProcessor.run(dateFormat.format(cal.getTime()), timeFormat.format(cal.getTime()), "R");
	}
}