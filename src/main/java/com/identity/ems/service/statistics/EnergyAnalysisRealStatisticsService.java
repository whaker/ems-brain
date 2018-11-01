package com.identity.ems.service.statistics;

import com.identity.ems.mapper.statistics.EnergyAnalysisRealStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class EnergyAnalysisRealStatisticsService {
	private static final Logger logger = LoggerFactory.getLogger(EnergyAnalysisRealStatisticsService.class);

	@Autowired
	EnergyAnalysisRealStatisticsMapper energyAnalysisRealStatisticsMapper;

	public void createTable(BigInteger buildingIdx) {
		energyAnalysisRealStatisticsMapper.createTable(buildingIdx);
	}

	public void save(BigInteger buildingIdx, String date, String time) {
		energyAnalysisRealStatisticsMapper.save(buildingIdx, date, time);
	}

	public void savePrice(BigInteger buildingIdx, String date, String time) {
		energyAnalysisRealStatisticsMapper.savePrice(buildingIdx, date, time);
	}

	public void savePerArea(BigInteger buildingIdx, String date, String time) {
		energyAnalysisRealStatisticsMapper.savePerArea(buildingIdx, date, time);
	}
}