package com.car.bean;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class Admin {

    private BigDecimal adminID, adminPsd, pID, state, job, charge;
    private String cName, cAccount, cPsd, cGoodAt, cIntroduce, cBackGround, cEducation;

    public Admin() {
    }

    public Admin(BigDecimal adminID, BigDecimal adminPsd, BigDecimal pID, BigDecimal state, BigDecimal job, BigDecimal charge, String cName, String cAccount, String cPsd, String cGoodAt, String cIntroduce, String cBackGround, String cEducation) {
        this.adminID = adminID;
        this.adminPsd = adminPsd;
        this.pID = pID;
        this.state = state;
        this.job = job;
        this.charge = charge;
        this.cName = cName;
        this.cAccount = cAccount;
        this.cPsd = cPsd;
        this.cGoodAt = cGoodAt;
        this.cIntroduce = cIntroduce;
        this.cBackGround = cBackGround;
        this.cEducation = cEducation;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminID=" + adminID +
                ", adminPsd=" + adminPsd +
                ", pID=" + pID +
                ", state=" + state +
                ", job=" + job +
                ", charge=" + charge +
                ", cName='" + cName + '\'' +
                ", cAccount='" + cAccount + '\'' +
                ", cPsd='" + cPsd + '\'' +
                ", cGoodAt='" + cGoodAt + '\'' +
                ", cIntroduce='" + cIntroduce + '\'' +
                ", cBackGround='" + cBackGround + '\'' +
                ", cEducation='" + cEducation + '\'' +
                '}';
    }

    public BigDecimal getAdminID() {
        return adminID;
    }

    public void setAdminID(BigDecimal adminID) {
        this.adminID = adminID;
    }

    public BigDecimal getAdminPsd() {
        return adminPsd;
    }

    public void setAdminPsd(BigDecimal adminPsd) {
        this.adminPsd = adminPsd;
    }

    public BigDecimal getpID() {
        return pID;
    }

    public void setpID(BigDecimal pID) {
        this.pID = pID;
    }

    public BigDecimal getState() {
        return state;
    }

    public void setState(BigDecimal state) {
        this.state = state;
    }

    public BigDecimal getJob() {
        return job;
    }

    public void setJob(BigDecimal job) {
        this.job = job;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAccount() {
        return cAccount;
    }

    public void setcAccount(String cAccount) {
        this.cAccount = cAccount;
    }

    public String getcPsd() {
        return cPsd;
    }

    public void setcPsd(String cPsd) {
        this.cPsd = cPsd;
    }

    public String getcGoodAt() {
        return cGoodAt;
    }

    public void setcGoodAt(String cGoodAt) {
        this.cGoodAt = cGoodAt;
    }

    public String getcIntroduce() {
        return cIntroduce;
    }

    public void setcIntroduce(String cIntroduce) {
        this.cIntroduce = cIntroduce;
    }

    public String getcBackGround() {
        return cBackGround;
    }

    public void setcBackGround(String cBackGround) {
        this.cBackGround = cBackGround;
    }

    public String getcEducation() {
        return cEducation;
    }

    public void setcEducation(String cEducation) {
        this.cEducation = cEducation;
    }

    public void ss(){
        System.out.println("qwer");
    }
}