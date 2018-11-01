package com.identity.ems.domain;

import java.math.BigInteger;

public class AnalysisStatistic {
	private BigInteger facilityIdx;
    private BigInteger performanceIdx;
	private String date;
	private String time;
	private double analysisValue = 0.0f;

	public AnalysisStatistic() {
	}

	public BigInteger getFacilityIdx() {
		return facilityIdx;
	}

	public void setFacilityIdx(BigInteger facilityIdx) {
		this.facilityIdx = facilityIdx;
	}

	public BigInteger getPerformanceIdx() {
		return performanceIdx;
	}

	public void setPerformanceIdx(BigInteger performanceIdx) {
		this.performanceIdx = performanceIdx;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getAnalysisValue() {
		return analysisValue;
	}

	public void setAnalysisValue(double analysisValue) {
		this.analysisValue = analysisValue;
	}

	@Override
	public String toString() {
		return "AnalysisStatistic{" +
				"facilityIdx=" + facilityIdx +
				", performanceIdx=" + performanceIdx +
				", date='" + date + '\'' +
				", time='" + time + '\'' +
				", analysisValue=" + analysisValue +
				'}';
	}
}
