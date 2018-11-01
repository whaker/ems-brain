package com.identity.ems.mapper;

import com.identity.ems.AbstractTestableContext;
import com.identity.ems.domain.Building;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class BuildingMapperTest extends AbstractTestableContext {
	
	private static final Logger logger = LoggerFactory.getLogger(BuildingMapperTest.class);
	
	@Resource
	int buildingMasterIdx;
	
	@Autowired
	BuildingMapper buildingMapper;

	@Test
	public void findAll() {
		List<Building> buildings = buildingMapper.findAll();

		buildings.forEach(building -> {
			logger.info("building info : {}", building.getBuildingName());
		});
	}
}
