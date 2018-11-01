package com.identity.ems.mapper;

import com.identity.ems.AbstractTestableContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

public class BemsPatternDataMapperTest extends AbstractTestableContext {
	private static final Logger logger = LoggerFactory.getLogger(BuildingMapperTest.class);

	@Autowired
	BemsDataMapper patternDataMapper;

	@Test
	public void findAllByBuildingIdx() {
		patternDataMapper.findAllByBuildingIdx(BigInteger.valueOf(1)).forEach(pattern -> {
			logger.debug("{}, {}",
					pattern.getBuildingIdx(),
					pattern.getPointIdx());
		});
	}
}