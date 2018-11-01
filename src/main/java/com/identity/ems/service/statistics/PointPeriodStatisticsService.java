package com.identity.ems.service.statistics;

import com.identity.ems.mapper.statistics.PointPeriodStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class PointPeriodStatisticsService {
	private static final Logger logger = LoggerFactory.getLogger(PointPeriodStatisticsService.class);

	@Autowired
	PointPeriodStatisticsMapper pointPeriodStatisticsMapper;

	public void createTable(BigInteger buildingIdx) {
		pointPeriodStatisticsMapper.createTable(buildingIdx);
	}
	public void saveForHour(BigInteger buildingIdx, String date, String time) {
		pointPeriodStatisticsMapper.saveForHour(buildingIdx, date, time);
	}

	public void saveForDay(BigInteger buildingIdx, String date) {
		pointPeriodStatisticsMapper.saveForDay(buildingIdx, date);
	}

	public void saveForMonth(BigInteger buildingIdx, String date) {
		pointPeriodStatisticsMapper.saveForMonth(buildingIdx, date);
	}

	public void saveForYear(BigInteger buildingIdx, String date) {
		pointPeriodStatisticsMapper.saveForYear(buildingIdx, date);
	}
}