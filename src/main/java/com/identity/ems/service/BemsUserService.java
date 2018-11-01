package com.identity.ems.service;

import com.identity.ems.domain.*;
import com.identity.ems.mapper.BemsUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BemsUserService {
	private static final Logger logger = LoggerFactory.getLogger(BemsUserService.class);

	@Autowired
	BemsUserMapper bemsUserMapper;

	public List<BemsUser> getByBuildingBlockIdx(BigInteger buildingBlockIdx) {
		return bemsUserMapper.findAllByBuildingBlockIdx(buildingBlockIdx);
	}

}