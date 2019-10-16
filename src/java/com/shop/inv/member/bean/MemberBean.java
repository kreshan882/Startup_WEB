/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.member.bean;

/**
 *
 * @author Kreshan Rajendran
 */
public class MemberBean {
        
    private String memId;
    
    private String memIdDes;
    private String memName;
    private String memNic;
    private String memDob;
    private String phoneNo;
    private String memBornPlace;
    private String memCast;


    //report
    private String CUS_NAME;
    private String status;
    private String regDate;
    
    private long fullCount;


    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemIdDes() {
        return memIdDes;
    }

    public void setMemIdDes(String memIdDes) {
        this.memIdDes = memIdDes;
    }

    
    


    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemNic() {
        return memNic;
    }

    public void setMemNic(String memNic) {
        this.memNic = memNic;
    }

    public String getMemDob() {
        return memDob;
    }

    public void setMemDob(String memDob) {
        this.memDob = memDob;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMemBornPlace() {
        return memBornPlace;
    }

    public void setMemBornPlace(String memBornPlace) {
        this.memBornPlace = memBornPlace;
    }

    public String getMemCast() {
        return memCast;
    }

    public void setMemCast(String memCast) {
        this.memCast = memCast;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    public String getCUS_NAME() {
        return CUS_NAME;
    }

    public void setCUS_NAME(String CUS_NAME) {
        this.CUS_NAME = CUS_NAME;
    }
    
    
}
