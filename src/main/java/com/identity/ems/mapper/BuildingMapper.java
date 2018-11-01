package com.identity.ems.mapper;

import com.identity.ems.domain.Building;
import com.identity.ems.mapper.support.MasterMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MasterMapper
public interface BuildingMapper {
	public List<Building> findAll();
	public List<Building> findAllByCategoryType(@Param("categoryType") String categoryType);
}