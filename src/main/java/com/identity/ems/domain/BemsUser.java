package com.identity.ems.domain;

import java.math.BigInteger;

public class BemsUser {
	private String userName;
	private String activated;
    private String language;
	private BigInteger  buildingIdx;
	private BigInteger  buildingBlockIdx;

	public BemsUser() {
	}

	public BemsUser(String userName, String activated, String language, BigInteger buildingIdx, BigInteger buildingBlockIdx) {
		this.userName = userName;
		this.activated = activated;
		this.language = language;
		this.buildingIdx = buildingIdx;
		this.buildingBlockIdx = buildingBlockIdx;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActivated() {
		return activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public BigInteger getBuildingIdx() {
		return buildingIdx;
	}

	public void setBuildingIdx(BigInteger buildingIdx) {
		this.buildingIdx = buildingIdx;
	}

	public BigInteger getBuildingBlockIdx() {
		return buildingBlockIdx;
	}

	public void setBuildingBlockIdx(BigInteger buildingBlockIdx) {
		this.buildingBlockIdx = buildingBlockIdx;
	}

	@Override
	public String toString() {
		return "BemsUser{" +
				"userName='" + userName + '\'' +
				", activated='" + activated + '\'' +
				", language='" + language + '\'' +
				", buildingIdx=" + buildingIdx +
				", buildingBlockIdx=" + buildingBlockIdx +
				'}';
	}
}
