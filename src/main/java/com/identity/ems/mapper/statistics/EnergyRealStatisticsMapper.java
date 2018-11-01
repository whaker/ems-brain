package com.identity.ems.mapper.statistics;

import com.identity.ems.mapper.support.MasterMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

@MasterMapper
public interface EnergyRealStatisticsMapper {
	public int createTable(@Param("buildingIdx") BigInteger buildingIdx);
	public int save(@Param("buildingIdx") BigInteger buildingIdx, @Param("date") String date, @Param("time") String time);
	public int saveForParent(@Param("buildingIdx") BigInteger buildingIdx, @Param("date") String date, @Param("time") String time);
}