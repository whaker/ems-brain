package com.identity.ems.domain;

import java.math.BigInteger;

/**
 * Created by iroot on 2017. 4. 16.
 */
public class BemsZoneReport {
    BigInteger zoneIdx;
    String buildingName;
    String zoneName;
    double kw;
    double kwh;

    public BigInteger getZoneIdx() {
        return zoneIdx;
    }

    public void setZoneIdx(BigInteger zoneIdx) {
        this.zoneIdx = zoneIdx;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
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
}
