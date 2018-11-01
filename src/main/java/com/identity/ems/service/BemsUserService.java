package com.identity.ems.service;

import com.identity.ems.domain.*;
import com.identity.ems.mapper.BemsDataMapper;
import com.identity.ems.mapper.BemsUserMapper;
import com.identity.ems.mapper.statistics.PointRealStatisticsMapper;
import com.identity.ems.service.statistics.PointRealStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BemsUserService {
	private static final Logger logger = LoggerFactory.getLogger(BemsUserService.class);

	@Autowired
	BemsUserMapper bemsUserMapper;

	public List<BemsUser> getByBuildingBlockIdx(BigInteger buildingBlockIdx) {
		return bemsUserMapper.findAllByBuildingBlockIdx(buildingBlockIdx);
	}

}