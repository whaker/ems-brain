package com.identity.ems.service.statistics;

import com.identity.ems.mapper.statistics.EnergyPeriodStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class EnergyPeriodStatisticsService {
	private static final Logger logger = LoggerFactory.getLogger(EnergyPeriodStatisticsService.class);

	@Autowired
	EnergyPeriodStatisticsMapper energyPeriodStatisticsMapper;

	public void createTable(BigInteger buildingIdx) {
		energyPeriodStatisticsMapper.createTable(buildingIdx);
	}
	public void saveForHour(BigInteger buildingIdx, String date, String time) {
		energyPeriodStatisticsMapper.saveForHour(buildingIdx, date, time);
	}

	public void saveForDay(BigInteger buildingIdx, String date) {
		energyPeriodStatisticsMapper.saveForDay(buildingIdx, date);
	}

	public void saveForMonth(BigInteger buildingIdx, String date) {
		energyPeriodStatisticsMapper.saveForMonth(buildingIdx, date);
	}

	public void saveForYear(BigInteger buildingIdx, String date) {
		energyPeriodStatisticsMapper.saveForYear(buildingIdx, date);
	}
}