package com.identity.ems.service.statistics;

import com.identity.ems.mapper.statistics.EnergyAnalysisPeriodStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class EnergyAnalysisPeriodStatisticsService {
	private static final Logger logger = LoggerFactory.getLogger(EnergyAnalysisPeriodStatisticsService.class);

	@Autowired
	EnergyAnalysisPeriodStatisticsMapper energyAnalysisPeriodStatisticsMapper;

	public void createTable(BigInteger buildingIdx) {
		energyAnalysisPeriodStatisticsMapper.createTable(buildingIdx);
	}
	public void saveForHour(BigInteger buildingIdx, String date, String time) {
		energyAnalysisPeriodStatisticsMapper.saveForHour(buildingIdx, date, time);
	}

	public void saveForDay(BigInteger buildingIdx, String date) {
		energyAnalysisPeriodStatisticsMapper.saveForDay(buildingIdx, date);
	}

	public void saveForMonth(BigInteger buildingIdx, String date) {
		energyAnalysisPeriodStatisticsMapper.saveForMonth(buildingIdx, date);
	}

	public void saveForYear(BigInteger buildingIdx, String date) {
		energyAnalysisPeriodStatisticsMapper.saveForYear(buildingIdx, date);
	}
}