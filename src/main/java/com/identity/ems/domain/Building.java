package com.identity.ems.domain;

import java.math.BigInteger;

public class Building {
	private BigInteger buildingIdx;
	private String buildingName;
    private BigInteger parentBuildingIdx;

	public Building() {
	}

    public BigInteger getBuildingIdx() {
        return buildingIdx;
    }

    public void setBuildingIdx(BigInteger buildingIdx) {
        this.buildingIdx = buildingIdx;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public BigInteger getParentBuildingIdx() {
        return parentBuildingIdx;
    }

    public void setParentBuildingIdx(BigInteger parentBuildingIdx) {
        this.parentBuildingIdx = parentBuildingIdx;
    }
}
