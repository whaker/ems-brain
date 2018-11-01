package com.identity.ems.domain;

import java.math.BigInteger;

public class BemsMeasuredHistory {
	BigInteger pointIdx;
	String date;
	String time;
	double pointValue;
	double pointChangeValue;

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

	public double getPointValue() {
		return pointValue;
	}
	
	public void setPointValue(double pointValue) {
		this.pointValue = pointValue;
	}
	
	public double getPointChangeValue() {
		return pointChangeValue;
	}
	
	public void setPointChangeValue(double pointChangeValue) {
		this.pointChangeValue = pointChangeValue;
	}
}
