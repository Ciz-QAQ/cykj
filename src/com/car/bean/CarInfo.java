package com.car.bean;

import java.math.BigDecimal;
import java.sql.Date;

public class CarInfo {

    private BigDecimal state,cid;
    private String cNumber,cColor,cDateString,cPlace;
    private Date cDate;

    public CarInfo() {
    }

    public CarInfo(BigDecimal state, BigDecimal cid, String cNumber, String cColor, String cDateString, String cPlace, Date cDate) {
        this.state = state;
        this.cid = cid;
        this.cNumber = cNumber;
        this.cColor = cColor;
        this.cDateString = cDateString;
        this.cPlace = cPlace;
        this.cDate = cDate;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "state=" + state +
                ", cid=" + cid +
                ", cNumber='" + cNumber + '\'' +
                ", cColor='" + cColor + '\'' +
                ", cDateString='" + cDateString + '\'' +
                ", cPlace='" + cPlace + '\'' +
                ", cDate=" + cDate +
                '}';
    }

    public BigDecimal getState() {
        return state;
    }

    public void setState(BigDecimal state) {
        this.state = state;
    }

    public BigDecimal getCid() {
        return cid;
    }

    public void setCid(BigDecimal cid) {
        this.cid = cid;
    }

    public String getcNumber() {
        return cNumber;
    }

    public void setcNumber(String cNumber) {
        this.cNumber = cNumber;
    }

    public String getcColor() {
        return cColor;
    }

    public void setcColor(String cColor) {
        this.cColor = cColor;
    }

    public String getcDateString() {
        return cDateString;
    }

    public void setcDateString(String cDateString) {
        this.cDateString = cDateString;
    }

    public String getcPlace() {
        return cPlace;
    }

    public void setcPlace(String cPlace) {
        this.cPlace = cPlace;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }
}
