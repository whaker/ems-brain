package com.identity.ems.mapper.statistics;

import com.identity.ems.domain.BemsEnergyAnalysisData;
import com.identity.ems.mapper.support.MasterMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@MasterMapper
public interface RealEnergyStatisticsWithPointMapper {
	public List<BemsEnergyAnalysisData> findAllByEnergyIdxAndPointTypeCode(@Param("buildingIdx") BigInteger buildingIdx, @Param("energyIdx") BigInteger energyIdx, @Param("pointTypeCode") String pointTypeCode);
}