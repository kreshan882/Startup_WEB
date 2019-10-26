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
public class ChildrenBean {
     private String chile_id;
    
    private String mem_id;
    private String mem_id_des;
    private String child_name;
    private String child_dob;
    private String child_gender;  //M/F
    private String child_merrid_status;  //M/S
    
    private String childEdu;
    private String childAddr;
    private String childPhone;
    private String childMobile;
    private String childEmail;

    private long fullCount;

    public String getMem_id_des() {
        return mem_id_des;
    }

    public void setMem_id_des(String mem_id_des) {
        this.mem_id_des = mem_id_des;
    }

    public String getChildEdu() {
        return childEdu;
    }

    public void setChildEdu(String childEdu) {
        this.childEdu = childEdu;
    }

    public String getChildAddr() {
        return childAddr;
    }

    public void setChildAddr(String childAddr) {
        this.childAddr = childAddr;
    }

    public String getChildPhone() {
        return childPhone;
    }

    public void setChildPhone(String childPhone) {
        this.childPhone = childPhone;
    }

    public String getChildMobile() {
        return childMobile;
    }

    public void setChildMobile(String childMobile) {
        this.childMobile = childMobile;
    }

    public String getChildEmail() {
        return childEmail;
    }

    public void setChildEmail(String childEmail) {
        this.childEmail = childEmail;
    }

    
    public String getChile_id() {
        return chile_id;
    }

    public void setChile_id(String chile_id) {
        this.chile_id = chile_id;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getChild_name() {
        return child_name;
    }

    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }

    public String getChild_dob() {
        return child_dob;
    }

    public void setChild_dob(String child_dob) {
        this.child_dob = child_dob;
    }

    public String getChild_gender() {
        return child_gender;
    }

    public void setChild_gender(String child_gender) {
        this.child_gender = child_gender;
    }

    public String getChild_merrid_status() {
        return child_merrid_status;
    }

    public void setChild_merrid_status(String child_merrid_status) {
        this.child_merrid_status = child_merrid_status;
    }



    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }
    
    
}
