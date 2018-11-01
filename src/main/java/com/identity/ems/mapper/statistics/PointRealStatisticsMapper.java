package com.identity.ems.mapper.statistics;

import com.identity.ems.domain.PointRealStatistic;
import com.identity.ems.mapper.support.MasterMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@MasterMapper
public interface PointRealStatisticsMapper {
	public int createTable(@Param("buildingIdx") BigInteger buildingIdx);
	public List<PointRealStatistic> findAllByLastDateTime(@Param("buildingIdx") BigInteger buildingIdx, @Param("date") String date, @Param("time") String time);
	public int save(@Param("buildingIdx") BigInteger buildingIdx, @Param("statistic") PointRealStatistic statistic);
    public List<PointRealStatistic> findAllByDateTime(@Param("buildingIdx") BigInteger buildingIdx, @Param("date") String date, @Param("time") String time);
}