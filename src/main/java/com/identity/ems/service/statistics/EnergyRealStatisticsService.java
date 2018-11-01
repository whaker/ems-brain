package com.identity.ems.service.statistics;

import com.identity.ems.mapper.statistics.EnergyRealStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class EnergyRealStatisticsService {
	private static final Logger logger = LoggerFactory.getLogger(EnergyRealStatisticsService.class);

	@Autowired
	EnergyRealStatisticsMapper energyRealStatisticsMapper;

	public void createTable(BigInteger buildingIdx) {
		energyRealStatisticsMapper.createTable(buildingIdx);
	}

	public void save(BigInteger buildingIdx, String date, String time) {
		energyRealStatisticsMapper.save(buildingIdx, date, time);
	}

	public void saveForParent(BigInteger buildingIdx, String date, String time) {
		energyRealStatisticsMapper.saveForParent(buildingIdx, date, time);
	}
}