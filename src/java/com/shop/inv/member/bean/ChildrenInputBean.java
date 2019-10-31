/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.member.bean;


import com.shop.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Kreshan Rajendran
 */
public class ChildrenInputBean {
        //Search Data
    private String searchname ="";
    private boolean search;
    
    //Add data

    private String memberload;
    private TreeMap<Integer,String> memberloadList = new TreeMap<Integer,String>();
    private String childName;
    private String childDob;
    private String childGender; //M/F
    private Map<String,String> childGenderList = Util.getChildGenderdList();
    private String childMerStatus;
    private Map<String,String> childMerStatusList = Util.getChildMerriedStatusList();
    
    private String childEdu;
    private String childAddr;
    private String childPhone;
    private String childMobile;
    private String childEmail;
    
    
    
    //Delete Data
    private String message;
    private boolean success;

//   Update Data
    private String childId;
    
    private String upmemberload;
    private String upchildName;
    private String upchildDob;
    private String upchildGender; //M/F
    private String upchildMerStatus;
    
    private String upchildEdu;
    private String upchildAddr;
    private String upchildPhone;
    private String upchildMobile;
    private String upchildEmail;
    

     
    /*-------for access control-----------*/
    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vdownload;
    private boolean vresetpass;
    //    private boolean vactdct;
    /*-------for access control-----------*/ 
    
    //Table data
    private List<ChildrenBean> gridModel=new ArrayList<ChildrenBean>();
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getUpmemberload() {
        return upmemberload;
    }

    public void setUpmemberload(String upmemberload) {
        this.upmemberload = upmemberload;
    }

    public String getUpchildName() {
        return upchildName;
    }

    public void setUpchildName(String upchildName) {
        this.upchildName = upchildName;
    }

    public String getUpchildDob() {
        return upchildDob;
    }

    public void setUpchildDob(String upchildDob) {
        this.upchildDob = upchildDob;
    }

    public String getUpchildGender() {
        return upchildGender;
    }

    public void setUpchildGender(String upchildGender) {
        this.upchildGender = upchildGender;
    }

    public String getUpchildMerStatus() {
        return upchildMerStatus;
    }

    public void setUpchildMerStatus(String upchildMerStatus) {
        this.upchildMerStatus = upchildMerStatus;
    }

    public String getUpchildEdu() {
        return upchildEdu;
    }

    public void setUpchildEdu(String upchildEdu) {
        this.upchildEdu = upchildEdu;
    }

    public String getUpchildAddr() {
        return upchildAddr;
    }

    public void setUpchildAddr(String upchildAddr) {
        this.upchildAddr = upchildAddr;
    }

    public String getUpchildPhone() {
        return upchildPhone;
    }

    public void setUpchildPhone(String upchildPhone) {
        this.upchildPhone = upchildPhone;
    }

    public String getUpchildMobile() {
        return upchildMobile;
    }

    public void setUpchildMobile(String upchildMobile) {
        this.upchildMobile = upchildMobile;
    }

    public String getUpchildEmail() {
        return upchildEmail;
    }

    public void setUpchildEmail(String upchildEmail) {
        this.upchildEmail = upchildEmail;
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

    public String getChildDob() {
        return childDob;
    }

    public void setChildDob(String childDob) {
        this.childDob = childDob;
    }

    public String getChildGender() {
        return childGender;
    }

    public void setChildGender(String childGender) {
        this.childGender = childGender;
    }

    public Map<String, String> getChildGenderList() {
        return childGenderList;
    }

    public void setChildGenderList(Map<String, String> childGenderList) {
        this.childGenderList = childGenderList;
    }

    public String getChildMerStatus() {
        return childMerStatus;
    }

    public void setChildMerStatus(String childMerStatus) {
        this.childMerStatus = childMerStatus;
    }

    public Map<String, String> getChildMerStatusList() {
        return childMerStatusList;
    }

    public void setChildMerStatusList(Map<String, String> childMerStatusList) {
        this.childMerStatusList = childMerStatusList;
    }

    
    
    
    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    public String getMemberload() {
        return memberload;
    }

    public void setMemberload(String memberload) {
        this.memberload = memberload;
    }

    public TreeMap<Integer, String> getMemberloadList() {
        return memberloadList;
    }

    public void setMemberloadList(TreeMap<Integer, String> memberloadList) {
        this.memberloadList = memberloadList;
    }



  

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isVadd() {
        return vadd;
    }

    public void setVadd(boolean vadd) {
        this.vadd = vadd;
    }

    public boolean isVupdate() {
        return vupdate;
    }

    public void setVupdate(boolean vupdate) {
        this.vupdate = vupdate;
    }

    public boolean isVdelete() {
        return vdelete;
    }

    public void setVdelete(boolean vdelete) {
        this.vdelete = vdelete;
    }

    public boolean isVdownload() {
        return vdownload;
    }

    public void setVdownload(boolean vdownload) {
        this.vdownload = vdownload;
    }

    public boolean isVresetpass() {
        return vresetpass;
    }

    public void setVresetpass(boolean vresetpass) {
        this.vresetpass = vresetpass;
    }

    public List<ChildrenBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<ChildrenBean> gridModel) {
        this.gridModel = gridModel;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    
    
    
}
