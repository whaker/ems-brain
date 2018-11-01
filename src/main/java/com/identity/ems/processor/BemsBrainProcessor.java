
package com.identity.ems.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BemsBrainProcessor {
	private static final Logger logger = LoggerFactory.getLogger(BemsBrainProcessor.class);

	public void runForReal(String date, String time) {
	}

	

	public void run(String date, String time, String periodType) {
	    if (periodType.equals("R")) {
	        runForReal(date, time);
		} else if (periodType.equals("H")) {
		} else if (periodType.equals("D"))  {
	    } else if (periodType.equals("M")) {
		} else if (periodType.equals("Y"))  {
		} else {
	        logger.error("Invalid Period Type : {}", periodType);
		}
	}
}
