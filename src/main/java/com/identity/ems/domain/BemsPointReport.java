package com.identity.ems.domain;

import java.math.BigInteger;

/**
 * Created by iroot on 2017. 4. 16.
 */
public class BemsPointReport {
    BigInteger pointIdx;
    String buildingName;
    String blockName;
    String floorName;
    String zoneName;
    BigInteger facilityIdx;
    String facilityName;
    String pointGroupCode;
    String pointName;
    double voltage;
    double voltageR;
    double voltageS;
    double voltageT;
    double ampere;
    double ampereR;
    double ampereS;
    double ampereT;
    double kw;
    double kwh;
    double energyPrice;
    BigInteger parentFacilityIdx;

    public BemsPointReport() {
    }

    public BigInteger getPointIdx() {
        return pointIdx;
    }

    public void setPointIdx(BigInteger pointIdx) {
        this.pointIdx = pointIdx;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public BigInteger getFacilityIdx() {
        return facilityIdx;
    }

    public void setFacilityIdx(BigInteger facilityIdx) {
        this.facilityIdx = facilityIdx;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getPointGroupCode() {
        return pointGroupCode;
    }

    public void setPointGroupCode(String pointGroupCode) {
        this.pointGroupCode = pointGroupCode;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getVoltageR() {
        return voltageR;
    }

    public void setVoltageR(double voltageR) {
        this.voltageR = voltageR;
    }

    public double getVoltageS() {
        return voltageS;
    }

    public void setVoltageS(double voltageS) {
        this.voltageS = voltageS;
    }

    public double getVoltageT() {
        return voltageT;
    }

    public void setVoltageT(double voltageT) {
        this.voltageT = voltageT;
    }

    public double getAmpere() {
        return ampere;
    }

    public void setAmpere(double ampere) {
        this.ampere = ampere;
    }

    public double getAmpereR() {
        return ampereR;
    }

    public void setAmpereR(double ampereR) {
        this.ampereR = ampereR;
    }

    public double getAmpereS() {
        return ampereS;
    }

    public void setAmpereS(double ampereS) {
        this.ampereS = ampereS;
    }

    public double getAmpereT() {
        return ampereT;
    }

    public void setAmpereT(double ampereT) {
        this.ampereT = ampereT;
    }

    public double getKw() {
        return kw;
    }

    public void setKw(double kw) {
        this.kw = kw;
    }

    public double getKwh() {
        return kwh;
    }

    public void setKwh(double kwh) {
        this.kwh = kwh;
    }

    public double getEnergyPrice() {
        return energyPrice;
    }

    public void setEnergyPrice(double energyPrice) {
        this.energyPrice = energyPrice;
    }

    public BigInteger getParentFacilityIdx() {
        return parentFacilityIdx;
    }

    public void setParentFacilityIdx(BigInteger parentFacilityIdx) {
        this.parentFacilityIdx = parentFacilityIdx;
    }
}
