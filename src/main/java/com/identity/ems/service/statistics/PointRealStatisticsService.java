package com.identity.ems.service.statistics;

import com.identity.ems.domain.PointRealStatistic;
import com.identity.ems.mapper.statistics.PointRealStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class PointRealStatisticsService {
	private static final Logger logger = LoggerFactory.getLogger(PointRealStatisticsService.class);

	@Autowired
	PointRealStatisticsMapper pointRealStatisticsMapper;

	public void createTable(BigInteger buildingIdx) {
		pointRealStatisticsMapper.createTable(buildingIdx);
	}

	public List<PointRealStatistic> getAll(BigInteger buildingIdx, String date, String time) {
		return pointRealStatisticsMapper.findAllByDateTime(buildingIdx, date, time);
	}

	public List<PointRealStatistic> getAllByLastDateTime(BigInteger buildingIdx, String date, String time) {
		return pointRealStatisticsMapper.findAllByLastDateTime(buildingIdx, date, time);
	}

	public void save(BigInteger buildingIdx, PointRealStatistic statistic) {
		pointRealStatisticsMapper.save(buildingIdx, statistic);
	}
}