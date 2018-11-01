package com.identity.ems.mapper;

import com.identity.ems.domain.PerformanceInformation;
import com.identity.ems.mapper.support.MasterMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@MasterMapper
public interface BemsPerformanceMapper {
	public List<PerformanceInformation> findAllForPerformance(@Param("buildingIdx") BigInteger buildingIdx);
}