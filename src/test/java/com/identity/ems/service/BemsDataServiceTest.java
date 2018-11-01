package com.identity.ems.service;

import com.identity.ems.AbstractTestableContext;
import com.identity.ems.domain.BemsData;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

public class BemsDataServiceTest extends AbstractTestableContext {
	private static final Logger logger = LoggerFactory.getLogger(BemsDataServiceTest.class);

	@Autowired
	BemsMeasuredHistoryService historyService;

	@Autowired
	BemsDataService bemsDataService;

	@Test
	public void getBemsDatasByPoint() {
		List<BemsData> bemsDatas = bemsDataService.getBemsDataFromMeasuredData(BigInteger.valueOf(1));
		bemsDataService.getFacilityBemsDataGroupByPoint(bemsDatas).forEach((key, value) -> {
			System.out.println("key : " + key);
		});
		logger.debug("end");
	}
}
