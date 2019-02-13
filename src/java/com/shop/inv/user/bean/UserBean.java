/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.user.bean;

/**
 *
 * @author epic_kapila
 */
public class UserBean {
    
    private String profileId;
    
    private String name;
    private String username;
    private String email;
    private String mobile;
    private String profile;
    private String profilename;

    private String status;
    private String regDate;
    
    private long fullCount;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }




    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    
}
