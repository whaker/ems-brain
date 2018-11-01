package com.identity.ems.service.statistics;

import com.identity.ems.domain.AnalysisStatistic;
import com.identity.ems.mapper.statistics.PerformanceAnalysisRealStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class PerformanceAnalysisRealStatisticsService {
	private static final Logger logger = LoggerFactory.getLogger(PerformanceAnalysisRealStatisticsService.class);

	@Autowired
	PerformanceAnalysisRealStatisticsMapper analysisRealStatisticsMapper;

	public void createTable(BigInteger buildingIdx) {
		analysisRealStatisticsMapper.createTable(buildingIdx);
	}

	public void save(BigInteger buildingIdx, AnalysisStatistic statistic) {
		analysisRealStatisticsMapper.save(buildingIdx, statistic);
	}
}