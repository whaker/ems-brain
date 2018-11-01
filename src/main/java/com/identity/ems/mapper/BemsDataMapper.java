package com.identity.ems.mapper;

import com.identity.ems.domain.BemsData;
import com.identity.ems.mapper.support.MasterMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@MasterMapper
public interface BemsDataMapper {
	public List<BemsData> findAllByBuildingIdx(@Param("buildingIdx") BigInteger buildingIdx);
}