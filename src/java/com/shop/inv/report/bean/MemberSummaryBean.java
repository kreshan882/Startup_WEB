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
public class MemberSummaryBean {
    private String castName;
    private String castCount;
    
    private long fullCount;

    public String getCastName() {
        return castName;
    }

    public void setCastName(String castName) {
        this.castName = castName;
    }

    public String getCastCount() {
        return castCount;
    }

    public void setCastCount(String castCount) {
        this.castCount = castCount;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }
    
    
    
}
