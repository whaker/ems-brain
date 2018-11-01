package com.identity.ems.mapper;

import com.identity.ems.domain.BemsUser;
import com.identity.ems.domain.Building;
import com.identity.ems.mapper.support.MasterMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@MasterMapper
public interface BemsUserMapper {
	public List<BemsUser> findAllByBuildingBlockIdx(@Param("buildingBlockIdx") BigInteger buildingBlockIdx);
}