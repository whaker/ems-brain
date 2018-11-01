
package com.identity.ems.processor.statistics;

import com.identity.ems.domain.Building;
import com.identity.ems.mapper.BuildingMapper;
import com.identity.ems.service.statistics.EnergyRealStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class EnergyFeedForwardProcessor {
	private static final Logger logger = LoggerFactory.getLogger(EnergyFeedForwardProcessor.class);

	@Autowired
	BuildingMapper buildingMapper;

	@Autowired
	EnergyRealStatisticsService energyRealStatisticsService;

	public void createStatistics(BigInteger buildingIdx, String date, String time) {
		energyRealStatisticsService.save(buildingIdx, date, time);
		energyRealStatisticsService.saveForParent(buildingIdx, date, time);
	}

	public void runByBuilding(String date, String time, Building building) {
	    try {
			energyRealStatisticsService.createTable(building.getBuildingIdx());
			createStatistics(building.getBuildingIdx(), date, time);
		} catch (Exception e) {
	        e.printStackTrace();
			logger.error("Real energy statistics run failed by building. errorMessage : " + e.getMessage());
		}
	}

	public void run (String date, String time) {
		logger.debug("Real energy statistics started. date : {}, time : {}", date, time);
		try {
			List<Building> buildings = buildingMapper.findAll();
			buildings.forEach(building -> {
				runByBuilding(date, time, building);
			});
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Real energy statistics run fail!. errorMessage : " + e.getMessage());
		}
		logger.debug("Real energy statistics  have been completed. date : {}, time : {}", date, time);
	}
}
