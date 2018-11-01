package com.identity.ems.mapper.statistics;

import com.identity.ems.domain.AnalysisStatistic;
import com.identity.ems.mapper.support.MasterMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

@MasterMapper
public interface PerformanceAnalysisRealStatisticsMapper {
	public int createTable(@Param("buildingIdx") BigInteger buildingIdx);
	public int save(@Param("buildingIdx") BigInteger buildingIdx, @Param("statistic") AnalysisStatistic statistic);
}