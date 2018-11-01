package com.identity.ems.mapper.statistics;

import com.identity.ems.mapper.support.MasterMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

@MasterMapper
public interface EnergyAnalysisPeriodStatisticsMapper {
	public int createTable(@Param("buildingIdx") BigInteger buildingIdx);
	public int saveForHour(@Param("buildingIdx") BigInteger buildingIdx, @Param("date") String date, @Param("time") String time);
	public int saveForDay(@Param("buildingIdx") BigInteger buildingIdx, @Param("date") String date);
	public int saveForMonth(@Param("buildingIdx") BigInteger buildingIdx, @Param("date") String date);
	public int saveForYear(@Param("buildingIdx") BigInteger buildingIdx, @Param("date") String date);
}