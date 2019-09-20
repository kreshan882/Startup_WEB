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

/**
 *
 * @author Kreshan Rajendran
 */
public class MemberManagementInputBean {
    
//        //Search Data
//    private String searchname ="";
//    private boolean search;
    
    //Add data
    private String memId;
    private String memName;
    private String memDob;
    private String memCast;
    private HashMap<String,String> memCastList = new HashMap<String,String>();
    private String memIslife;   //1=life, 0= anual
    private Map<String,String> memIslifeList = Util.getMemIslifeList();

    private String memExpdate;
    
    
    //Delete Data
    private String message;
    private boolean success;

//   //Update Data
//    private String upusername;
//    private String upusernamecopy;
//
//
//    private String upname;
//    private String upuserPro;
//    private String upstatus;     
//    private String upemail;
//    private String upmobile;
//    private Map<Integer,String>  upstatusList=Util.getBasicStatus();
     
    /*-------for access control-----------*/
    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vdownload;
    private boolean vresetpass;
    //    private boolean vactdct;
    /*-------for access control-----------*/ 
    
    //Table data
    private List<MemberBean> gridModel=new ArrayList<MemberBean>();
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;

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

    public List<MemberBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<MemberBean> gridModel) {
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

    public String getMemDob() {
        return memDob;
    }

    public void setMemDob(String memDob) {
        this.memDob = memDob;
    }

    public String getMemCast() {
        return memCast;
    }

    public void setMemCast(String memCast) {
        this.memCast = memCast;
    }

    public HashMap<String, String> getMemCastList() {
        return memCastList;
    }

    public void setMemCastList(HashMap<String, String> memCastList) {
        this.memCastList = memCastList;
    }

    public String getMemIslife() {
        return memIslife;
    }

    public void setMemIslife(String memIslife) {
        this.memIslife = memIslife;
    }

    public String getMemExpdate() {
        return memExpdate;
    }

    public void setMemExpdate(String memExpdate) {
        this.memExpdate = memExpdate;
    }

    public Map<String, String> getMemIslifeList() {
        return memIslifeList;
    }

    public void setMemIslifeList(Map<String, String> memIslifeList) {
        this.memIslifeList = memIslifeList;
    }

    
    
}
