package com.identity.ems.configure.application;

import com.identity.ems.AbstractTestableContext;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

public class ApplicationConfigTest extends AbstractTestableContext {

	@Resource
	int agentMasterIdx;
	
	@Resource
	int buildingMasterIdx;
	
	@Test
	public void testAgentMasterIdx() {
		assertTrue(agentMasterIdx == 1);
	}

	@Test
	public void testBuildingMasterIdx() {
		assertTrue(buildingMasterIdx == 1);
	}

}
