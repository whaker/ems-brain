
package com.identity.ems.processor.deeplearning;

import com.identity.ems.domain.Building;
import com.identity.ems.mapper.BuildingMapper;
import com.identity.ems.service.deeplearning.EnergyFeedForwardService;
import com.identity.ems.service.statistics.RealEnergyStatisticsWithPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
public class EnergyFeedForwardProcessor {
	private static final Logger logger = LoggerFactory.getLogger(EnergyFeedForwardProcessor.class);

	@Autowired
	BuildingMapper buildingMapper;

	@Autowired
    RealEnergyStatisticsWithPointService realEnergyStatisticsWithPointService;

	@Autowired
    EnergyFeedForwardService energyFeedForwardService;

	public void runByBuilding(Building building) throws IOException {
	    // 전기
        BigInteger powerEnergyIdx = BigInteger.valueOf(101001001);
        energyFeedForwardService.run(
                realEnergyStatisticsWithPointService.getAll(
                        building.getBuildingIdx(),
                        powerEnergyIdx,
                        "PT_OA_ALL"),
                "MultiNetworkFile_" + String.valueOf(powerEnergyIdx) +".zip"
        );

        // 가스
        BigInteger gasEnergyIdx = BigInteger.valueOf(102001001);
        energyFeedForwardService.run(
                realEnergyStatisticsWithPointService.getAll(
                        building.getBuildingIdx(),
                        gasEnergyIdx,
                        "PT_OA_ALL"),
                "MultiNetworkFile_" + String.valueOf(gasEnergyIdx) +".zip"
        );
    }

    public void run(String date, String time) {
        logger.debug("Energy feed forward started. date : {}, time : {}, periodType : {}", date, time);
        try {
            List<Building> buildings = buildingMapper.findAll();
            buildings.forEach(building -> {
                try {
                    runByBuilding(building);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("Energy feed forward run failed!. errorMessage : " + e.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Energy feed forward run failed!. errorMessage : " + e.getMessage());
        }
        logger.debug("Energy feed forward have been completed. date : {}, time : {}, periodType : {}", date, time);
    }
}
