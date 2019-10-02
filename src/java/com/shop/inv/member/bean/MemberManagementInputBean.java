/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.member.bean;

import com.shop.util.Util;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kreshan Rajendran
 */
public class MemberManagementInputBean {
    
    //Search Data
    private String searchname ="";
    private boolean search;
    
    //Add data
    private String memId; //1,2,3
    private String memName;
    private String memNic;
    private String memDob;
    

    private String phoneNo;
    private String mobileNo;
    private String email;
    
    private String qualification; 
    private String perAddress;
    private String temAddress;

    private String memBornPlace;  //districk type
    private String memCast;
    private HashMap<String,String> memCastList = new HashMap<String,String>();
    private String memSubCast;
    
    
    private String memIslife;   //1=life, 0= anual
    private Map<String,String> memIslifeList = Util.getMemIslifeList();
    private String memExpdate;
    
    private String noOfBrother;
    private String noOfSister;
    
    private String jobTitle;  // occupation
    private String jobAddress;
    private String jobPhone;
   
    //parent details
    private String fatName;
    private String fatBirthPlace;
    private String fatCast;
    
    private String mothName;
    private String mothBirthPlace;
    private String mothCast;
    
    private String grandFatName;
    private String grandFatBirthPlace;
    private String grandFatCast;
    
    private String grandMothName;
    private String grandMothBirthPlace;
    private String grandMothCast;
    
    
    
    
    private String isMerrid;  //1=merrid, 0=single
    private Map<String,String> isMerridList = Util.getMerriedList();
    private String wifeName;
    private String noOfSuns;
    private String noOfDoters;
    
    private Map<String,String> numberList = Util.getNumberList();
    

    // sun doter details ???
    // wife details+wife parent
    
    
    
    //Delete Data
    private String message;
    private boolean success;

    
    
   //Update Data
    private String status;     
    private Map<Integer,String>  statusList=Util.getBasicStatus();
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

    
    //report download
    Map parameterMap = new HashMap();
    List<MemberBean> reportdatalist = null;
    private String fileName;   // pdf download file
    
    
    private File   upfile;  // image file upload
    private String upfileContentType;
    private String upfileFileName;

    public File getUpfile() {
        return upfile;
    }

    public void setUpfile(File upfile) {
        this.upfile = upfile;
    }

    public String getUpfileContentType() {
        return upfileContentType;
    }

    public void setUpfileContentType(String upfileContentType) {
        this.upfileContentType = upfileContentType;
    }

    public String getUpfileFileName() {
        return upfileFileName;
    }

    public void setUpfileFileName(String upfileFileName) {
        this.upfileFileName = upfileFileName;
    }

    
    
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    
    public Map getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map parameterMap) {
        this.parameterMap = parameterMap;
    }

    public List<MemberBean> getReportdatalist() {
        return reportdatalist;
    }

    public void setReportdatalist(List<MemberBean> reportdatalist) {
        this.reportdatalist = reportdatalist;
    }
    
    
    
    
    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<Integer, String> getStatusList() {
        return statusList;
    }

    public void setStatusList(Map<Integer, String> statusList) {
        this.statusList = statusList;
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

    public String getMemNic() {
        return memNic;
    }

    public void setMemNic(String memNic) {
        this.memNic = memNic;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPerAddress() {
        return perAddress;
    }

    public void setPerAddress(String perAddress) {
        this.perAddress = perAddress;
    }

    public String getTemAddress() {
        return temAddress;
    }

    public void setTemAddress(String temAddress) {
        this.temAddress = temAddress;
    }

    public String getMemBornPlace() {
        return memBornPlace;
    }

    public void setMemBornPlace(String memBornPlace) {
        this.memBornPlace = memBornPlace;
    }

    public String getMemSubCast() {
        return memSubCast;
    }

    public void setMemSubCast(String memSubCast) {
        this.memSubCast = memSubCast;
    }

    public String getFatName() {
        return fatName;
    }

    public void setFatName(String fatName) {
        this.fatName = fatName;
    }

    public String getFatBirthPlace() {
        return fatBirthPlace;
    }

    public void setFatBirthPlace(String fatBirthPlace) {
        this.fatBirthPlace = fatBirthPlace;
    }

    public String getFatCast() {
        return fatCast;
    }

    public void setFatCast(String fatCast) {
        this.fatCast = fatCast;
    }

    public String getMothName() {
        return mothName;
    }

    public void setMothName(String mothName) {
        this.mothName = mothName;
    }

    public String getMothBirthPlace() {
        return mothBirthPlace;
    }

    public void setMothBirthPlace(String mothBirthPlace) {
        this.mothBirthPlace = mothBirthPlace;
    }

    public String getMothCast() {
        return mothCast;
    }

    public void setMothCast(String mothCast) {
        this.mothCast = mothCast;
    }

    public String getGrandFatName() {
        return grandFatName;
    }

    public void setGrandFatName(String grandFatName) {
        this.grandFatName = grandFatName;
    }

    public String getGrandFatBirthPlace() {
        return grandFatBirthPlace;
    }

    public void setGrandFatBirthPlace(String grandFatBirthPlace) {
        this.grandFatBirthPlace = grandFatBirthPlace;
    }

    public String getGrandFatCast() {
        return grandFatCast;
    }

    public void setGrandFatCast(String grandFatCast) {
        this.grandFatCast = grandFatCast;
    }

    public String getGrandMothName() {
        return grandMothName;
    }

    public void setGrandMothName(String grandMothName) {
        this.grandMothName = grandMothName;
    }

    public String getGrandMothBirthPlace() {
        return grandMothBirthPlace;
    }

    public void setGrandMothBirthPlace(String grandMothBirthPlace) {
        this.grandMothBirthPlace = grandMothBirthPlace;
    }

    public String getGrandMothCast() {
        return grandMothCast;
    }

    public void setGrandMothCast(String grandMothCast) {
        this.grandMothCast = grandMothCast;
    }

    public String getNoOfBrother() {
        return noOfBrother;
    }

    public void setNoOfBrother(String noOfBrother) {
        this.noOfBrother = noOfBrother;
    }

    public String getNoOfSister() {
        return noOfSister;
    }

    public void setNoOfSister(String noOfSister) {
        this.noOfSister = noOfSister;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public String getJobPhone() {
        return jobPhone;
    }

    public void setJobPhone(String jobPhone) {
        this.jobPhone = jobPhone;
    }

    public String getIsMerrid() {
        return isMerrid;
    }

    public void setIsMerrid(String isMerrid) {
        this.isMerrid = isMerrid;
    }

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    public String getNoOfSuns() {
        return noOfSuns;
    }

    public void setNoOfSuns(String noOfSuns) {
        this.noOfSuns = noOfSuns;
    }

    public String getNoOfDoters() {
        return noOfDoters;
    }

    public void setNoOfDoters(String noOfDoters) {
        this.noOfDoters = noOfDoters;
    }

    public Map<String, String> getIsMerridList() {
        return isMerridList;
    }

    public void setIsMerridList(Map<String, String> isMerridList) {
        this.isMerridList = isMerridList;
    }

    public Map<String, String> getNumberList() {
        return numberList;
    }

    public void setNumberList(Map<String, String> numberList) {
        this.numberList = numberList;
    }

    
    
}
