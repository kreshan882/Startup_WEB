/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.report.bean;

/**
 *
 * @author Kreshan Rajendran
 */
public class MemberReportBean {
        
    private String memIdDes;
    
    private String memId;
    private String memName;
    private String perAddr;
    private String temAddr;
    private String offAddr;
    private String tpNum;
    private String mobileNum;
    private String offPhnNum;
    private String memCast;
    
    private String regDate;



    
    private long fullCount;

    public String getMemCast() {
        return memCast;
    }

    public void setMemCast(String memCast) {
        this.memCast = memCast;
    }

    
    
    public String getOffPhnNum() {
        return offPhnNum;
    }

    public void setOffPhnNum(String offPhnNum) {
        this.offPhnNum = offPhnNum;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    
    
    
    public String getMemIdDes() {
        return memIdDes;
    }

    public void setMemIdDes(String memIdDes) {
        this.memIdDes = memIdDes;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getPerAddr() {
        return perAddr;
    }

    public void setPerAddr(String perAddr) {
        this.perAddr = perAddr;
    }

    public String getTemAddr() {
        return temAddr;
    }

    public void setTemAddr(String temAddr) {
        this.temAddr = temAddr;
    }

    public String getOffAddr() {
        return offAddr;
    }

    public void setOffAddr(String offAddr) {
        this.offAddr = offAddr;
    }

    public String getTpNum() {
        return tpNum;
    }

    public void setTpNum(String tpNum) {
        this.tpNum = tpNum;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    
}
