package com.identity.ems.domain;

import java.math.BigInteger;

public class PointPeriodStatistic {
	private BigInteger pointIdx;
	private String date;
	private String time;
	private String periodType;
	private double pointSum;
	private double pointAverage;

	public PointPeriodStatistic() {
	}

    public BigInteger getPointIdx() {
        return pointIdx;
    }

    public void setPointIdx(BigInteger pointIdx) {
        this.pointIdx = pointIdx;
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

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public double getPointSum() {
        return pointSum;
    }

    public void setPointSum(double pointSum) {
        this.pointSum = pointSum;
    }

    public double getPointAverage() {
        return pointAverage;
    }

    public void setPointAverage(double pointAverage) {
        this.pointAverage = pointAverage;
    }
}
