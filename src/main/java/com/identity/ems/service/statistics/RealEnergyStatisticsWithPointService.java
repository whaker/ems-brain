package com.identity.ems.service.statistics;

import com.identity.ems.domain.BemsEnergyAnalysisData;
import com.identity.ems.mapper.statistics.RealEnergyStatisticsWithPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class RealEnergyStatisticsWithPointService {
	private static final Logger logger = LoggerFactory.getLogger(RealEnergyStatisticsWithPointService.class);

	@Autowired
	RealEnergyStatisticsWithPointMapper realEnergyStatisticsWithPointMapper;

	public List<BemsEnergyAnalysisData> getAll(BigInteger buildingIdx, BigInteger energyIdx, String pointTypeCode) {
		return realEnergyStatisticsWithPointMapper.findAllByEnergyIdxAndPointTypeCode(buildingIdx, energyIdx, pointTypeCode);
	}
}