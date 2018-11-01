package com.identity.ems.service.statistics;

import com.identity.ems.mapper.statistics.PerformanceAnalysisPeriodStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class PerformanceAnalysisPeriodStatisticsService {
	private static final Logger logger = LoggerFactory.getLogger(PerformanceAnalysisPeriodStatisticsService.class);

	@Autowired
	PerformanceAnalysisPeriodStatisticsMapper analysisPeriodStatisticsMapper;

	public void createTable(BigInteger buildingIdx) {
		analysisPeriodStatisticsMapper.createTable(buildingIdx);
	}

	public void saveForHour(BigInteger buildingIdx, String date, String time) {
		analysisPeriodStatisticsMapper.saveForHour(buildingIdx, date, time);
	}

	public void saveForDay(BigInteger buildingIdx, String date) {
		analysisPeriodStatisticsMapper.saveForDay(buildingIdx, date);
	}

	public void saveForMonth(BigInteger buildingIdx, String date) {
		analysisPeriodStatisticsMapper.saveForMonth(buildingIdx, date);
	}

	public void saveForYear(BigInteger buildingIdx, String date) {
		analysisPeriodStatisticsMapper.saveForYear(buildingIdx, date);
	}
}