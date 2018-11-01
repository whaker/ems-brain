package com.identity.ems.service;

import com.identity.ems.domain.PerformanceInformation;
import com.identity.ems.mapper.BemsPerformanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BemsPerformanceService {
	private static final Logger logger = LoggerFactory.getLogger(BemsPerformanceService.class);

	@Autowired
	BemsPerformanceMapper bemsPerformanceMapper;

	public List<PerformanceInformation> getPerformanceInformations(BigInteger buildingIdx) {
		return bemsPerformanceMapper.findAllForPerformance(buildingIdx);
	}
}