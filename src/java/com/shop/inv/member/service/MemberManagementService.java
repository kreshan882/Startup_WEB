/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.member.service;

import com.shop.db.DBConnection;
import com.shop.init.InitConfigValue;
import com.shop.init.Status;
import com.shop.inv.member.bean.MemberBean;
import com.shop.inv.member.bean.MemberManagementInputBean;
import com.shop.util.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jpos.iso.ISOUtil;

/**
 *
 * @author Kreshan Rajendran
 */
public class MemberManagementService {
    
    public List<MemberBean> loadData(MemberManagementInputBean bean, String orderBy, int from, int rows) throws Exception {
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        List<MemberBean> dataList = null;
        long totalCount = 0;
        try {

            con = DBConnection.getConnection();
            //con.setAutoCommit(true);
            //String sqlCount = "select count(*) AS TOTAL FROM web_user where USERNAME LIKE ?";
            String sqlCount = "select count(*) AS TOTAL FROM dma_member where MEM_ID LIKE ? or  MEM_NAME=? or MEM_NIC like ?";
            prepSt = con.prepareStatement(sqlCount);
            prepSt.setString(1, "%" + bean.getSearchname().toUpperCase() + "%");
            prepSt.setString(2, "%" + bean.getSearchname().toUpperCase() + "%");
            prepSt.setString(3, "%" + bean.getSearchname().toUpperCase() + "%");
            res = prepSt.executeQuery();
            if (res.next()) {
                totalCount = res.getLong("TOTAL");
            }
            if (res != null) {
                res.close();
            }
            if (prepSt != null) {
                prepSt.close();
            }
            getUsersListQuery ="SELECT M.MEM_ID,M.MEM_NAME,M.MEM_NIC, M.MEM_DOB, M.MEM_PHONE, "
                    + "M.MEM_BORN_PLACE, M.MEM_CAST, MC.CAST_NAME, M.CREATE_DATE,M.STATUS "
                    + "FROM dma_member M, dma_cast MC where M.MEM_CAST=MC.CAST_ID "
                    + "and (M.MEM_ID like ? or M.MEM_NAME like ? or M.MEM_NIC like ? )"+ orderBy + " LIMIT " + from + "," + rows;

//            getUsersListQuery = "SELECT up.DESCRIPTION AS PROFILENAME,u.NAME,u.USERNAME,u.PROFILE_ID, u.EMAIL,"
//                    + "u.MOBILE,u.STATUS,u.CREATE_DATE  "
//                    + "FROM WEB_USER u,WEB_USER_PROFILE up  "
//                    + "where u.PROFILE_ID=up.PROFILE_ID AND UPPER(u.USERNAME) LIKE ? " + orderBy + " LIMIT " + from + "," + rows;

            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setString(1, "%" + bean.getSearchname().toUpperCase() + "%");
            prepSt.setString(2, "%" + bean.getSearchname().toUpperCase() + "%");
            prepSt.setString(3, "%" + bean.getSearchname().toUpperCase() + "%");
            res = prepSt.executeQuery();

            dataList = new ArrayList<MemberBean>();

            while (res.next()) {
                MemberBean dataBean = new MemberBean();

                dataBean.setMemId(res.getString("MEM_ID"));
                dataBean.setMemIdDes("M"+ISOUtil.zeropad(res.getString("MEM_ID"),5));
                dataBean.setMemName(res.getString("MEM_NAME"));
                dataBean.setMemNic(res.getString("MEM_NIC"));
                
                dataBean.setMemDob(res.getString("MEM_DOB"));
                dataBean.setPhoneNo(res.getString("MEM_PHONE"));
                dataBean.setMemBornPlace(res.getString("MEM_BORN_PLACE"));
                dataBean.setMemCast(res.getString("CAST_NAME"));

                dataBean.setStatus(res.getString("STATUS"));
                dataBean.setRegDate(res.getString("CREATE_DATE"));
                
                dataBean.setFullCount(totalCount);
                dataList.add(dataBean);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (prepSt != null) {
                prepSt.close();
            }
            
            if (con != null) {
                con.close();
            }

        }
        return dataList;
    }

    public void findData(MemberManagementInputBean inputBean) throws Exception {

        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        System.out.println("getMemId:"+inputBean.getMemId());
        try {

            con = DBConnection.getConnection();
            //con.setAutoCommit(true);
            getUsersListQuery = "SELECT MEM_ID, "
                    + "MEM_NAME, MEM_NIC, MEM_DOB,MEM_PHONE,MEM_MOBILE,  EMAIL, "
                    + "QUALIFICATION, PERM_ADD, TEMP_ADD, MEM_BORN_PLACE, MEM_CAST, MEM_SUB_CAST, "
                    + "MEM_TYPE_ISLIFE,MEM_EXP_DATE, NUM_OF_BRO, NUM_OF_SIS, "
                    + "JOB_TITLE, JOB_ADD, JOB_PHONE, "
                    + "FAT_NAME, FAT_ADD, FAT_CAST, MOT_NAME, MOT_ADD, MOT_CAST, "
                    + "GRAN_FAT_NAME, GRAN_FAT_ADD, GRAN_FAT_CAST, GRAN_MOT_NAME, GRAN_MOT_ADD,GRAN_MOT_CAST, "
                    + "IS_MARRIED, WI_NAME, NUM_OF_SUN, NUM_OF_DOT, STATUS,"
                    
                    + "WI_DOB,WI_ADD,WI_EMAIL,WI_MOBILE,WI_BORN_PLACE,"
                    + "WI_CAST,WI_FAT_NAME,WI_FAT_BORN_PLACE,WI_FAT_CAST,WI_MOT_NAME,"
                    + "WI_MOT_BORN_PLACE,WI_MOT_CAST,WI_GRAN_FAT_NAME,WI_GRAN_FAT_BORN_PLACE,WI_GRAN_FAT_CAST,"
                    + "WI_GRAN_MOT_NAME,WI_GRAN_MOT_BORN_PLACE,WI_GRAN_MOT_CAST,IMG_MEMBER,IMG_FAMILY  "
                    + "FROM dma_member where MEM_ID=?";

            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setString(1, inputBean.getMemId());
            res = prepSt.executeQuery();
            while (res.next()) {
//                bean.setMemId(res.getString("MEM_ID"));
//                bean.setMemName(res.getString("MEM_NAME"));
//                bean.setEmail(res.getString("EMAIL"));
//                
//                bean.setPhoneNo(res.getString("MEM_PHONE"));
//                bean.setMemCast(res.getString("MEM_CAST"));
//                bean.setStatus(res.getString("STATUS"));

                inputBean.setMemId(res.getString("MEM_ID"));
                inputBean.setMemIdDes("M"+ISOUtil.zeropad(res.getString("MEM_ID"),5));
                inputBean.setMemName(res.getString("MEM_NAME"));
                inputBean.setMemNic(res.getString("MEM_NIC"));
                inputBean.setMemDob(res.getString("MEM_DOB"));
                inputBean.setPhoneNo(res.getString("MEM_PHONE"));
                inputBean.setMobileNo(res.getString("MEM_MOBILE"));
                inputBean.setEmail(res.getString("EMAIL"));

                inputBean.setQualification(res.getString("QUALIFICATION"));
                inputBean.setPerAddress(res.getString("PERM_ADD"));
                inputBean.setTemAddress(res.getString("TEMP_ADD"));
                inputBean.setMemBornPlace(res.getString("MEM_BORN_PLACE"));
                inputBean.setMemCast(res.getString("MEM_CAST"));
                inputBean.setMemSubCast(res.getString("MEM_SUB_CAST"));

                inputBean.setMemIslife(res.getString("MEM_TYPE_ISLIFE"));
                inputBean.setMemExpdate(res.getString("MEM_EXP_DATE"));
                inputBean.setNoOfBrother(res.getString("NUM_OF_BRO"));
                inputBean.setNoOfSister(res.getString("NUM_OF_SIS"));

                inputBean.setJobTitle(res.getString("JOB_TITLE"));
                inputBean.setJobAddress(res.getString("JOB_ADD"));
                inputBean.setJobPhone(res.getString("JOB_PHONE"));

                inputBean.setFatName(res.getString("FAT_NAME"));
                inputBean.setFatBirthPlace(res.getString("FAT_ADD"));
                inputBean.setFatCast(res.getString("FAT_CAST"));
                inputBean.setMothName(res.getString("MOT_NAME"));
                inputBean.setMothBirthPlace(res.getString("MOT_ADD"));
                inputBean.setMothCast(res.getString("MOT_CAST"));

                inputBean.setGrandFatName(res.getString("GRAN_FAT_NAME"));
                inputBean.setGrandFatBirthPlace(res.getString("GRAN_FAT_ADD"));
                inputBean.setGrandFatCast(res.getString("GRAN_FAT_CAST"));
                inputBean.setGrandMothName(res.getString("GRAN_MOT_NAME"));
                inputBean.setGrandMothBirthPlace(res.getString("GRAN_MOT_ADD"));
                inputBean.setGrandMothCast(res.getString("GRAN_MOT_CAST"));

                inputBean.setIsMerrid(res.getString("IS_MARRIED"));
                inputBean.setWifeName(res.getString("WI_NAME"));
                inputBean.setNoOfSuns(res.getString("NUM_OF_SUN"));
                inputBean.setNoOfDoters(res.getString("NUM_OF_DOT"));
                inputBean.setStatus(res.getString("STATUS"));

                //wife details
                inputBean.setWifeDob(res.getString("WI_DOB"));
                inputBean.setWifeAdd(res.getString("WI_ADD"));
                inputBean.setWifeEmail(res.getString("WI_EMAIL"));
                inputBean.setWifeMobile(res.getString("WI_MOBILE"));
                inputBean.setWifeBirPlace(res.getString("WI_BORN_PLACE"));

                inputBean.setWifeCast(res.getString("WI_CAST"));
                inputBean.setWifeFatName(res.getString("WI_FAT_NAME"));
                inputBean.setWifeFatBirthPlace(res.getString("WI_FAT_BORN_PLACE"));
                inputBean.setWifeFatCast(res.getString("WI_FAT_CAST"));
                inputBean.setWifeMothName(res.getString("WI_MOT_NAME"));

                inputBean.setWifeMothBirthPlace(res.getString("WI_MOT_BORN_PLACE"));
                inputBean.setWifeMothCast(res.getString("WI_MOT_CAST"));
                inputBean.setWifeGrandFatName(res.getString("WI_GRAN_FAT_NAME"));
                inputBean.setWifeGrandFatBirthPlace(res.getString("WI_GRAN_FAT_BORN_PLACE"));
                inputBean.setWifeGrandFatCast(res.getString("WI_GRAN_FAT_CAST"));

                inputBean.setWifeGrandMothName(res.getString("WI_GRAN_MOT_NAME"));
                inputBean.setWifeGrandMothBirthPlace(res.getString("WI_GRAN_MOT_BORN_PLACE"));
                inputBean.setWifeGrandMothCast(res.getString("WI_GRAN_MOT_CAST"));
                if(res.getString("IMG_MEMBER")!=null){
                    inputBean.setMemImgFileFileName(res.getString("IMG_MEMBER"));
                }
                if(res.getString("IMG_FAMILY")!=null){
                    inputBean.setFamImgFileFileName(res.getString("IMG_FAMILY"));
                }
                

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (prepSt != null) {
                prepSt.close();
            }
            if (con != null) {
                con.close();
            }

        }

    }

    public boolean updateData(MemberManagementInputBean inputBean,boolean mem_img,boolean fam_img) throws Exception {

        boolean ok = false;
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String sql = null;
        try {
            System.out.println("getMemId:"+inputBean.getMemId());
            con = DBConnection.getConnection();
            //con.setAutoCommit(true);

            sql = "UPDATE dma_member SET "
                    + "MEM_NAME=?, MEM_NIC=?, MEM_DOB=?,MEM_PHONE=?,MEM_MOBILE=?,  EMAIL=?, "
                    + "QUALIFICATION=?, PERM_ADD=?, TEMP_ADD=?, MEM_BORN_PLACE=?, MEM_CAST=?, MEM_SUB_CAST=?, "
                    + "MEM_TYPE_ISLIFE=?,MEM_EXP_DATE=?, NUM_OF_BRO=?, NUM_OF_SIS=?, "
                    + "JOB_TITLE=?, JOB_ADD=?, JOB_PHONE=?, "
                    + "FAT_NAME=?, FAT_ADD=?, FAT_CAST=?, MOT_NAME=?, MOT_ADD=?, MOT_CAST=?, "
                    + "GRAN_FAT_NAME=?, GRAN_FAT_ADD=?, GRAN_FAT_CAST=?, GRAN_MOT_NAME=?, GRAN_MOT_ADD=?,GRAN_MOT_CAST=?, "
                    + "IS_MARRIED=?, WI_NAME=?, NUM_OF_SUN=?, NUM_OF_DOT=?, STATUS=?,"
                    
                    + "WI_DOB=?,WI_ADD=?,WI_EMAIL=?,WI_MOBILE=?,WI_BORN_PLACE=?,"
                    + "WI_CAST=?,WI_FAT_NAME=?,WI_FAT_BORN_PLACE=?,WI_FAT_CAST=?,WI_MOT_NAME=?,"
                    + "WI_MOT_BORN_PLACE=?,WI_MOT_CAST=?,WI_GRAN_FAT_NAME=?,WI_GRAN_FAT_BORN_PLACE=?,WI_GRAN_FAT_CAST=?,"
                    + "WI_GRAN_MOT_NAME=?,WI_GRAN_MOT_BORN_PLACE=?,WI_GRAN_MOT_CAST=?,IMG_MEMBER=?,IMG_FAMILY=?  "
                    + "Where MEM_ID=?";
            prepSt = con.prepareStatement(sql);
            prepSt.setString( 1 ,inputBean.getMemName());
            prepSt.setString( 2 ,inputBean.getMemNic());
            prepSt.setString( 3 ,inputBean.getMemDob());
            prepSt.setString( 4 ,inputBean.getPhoneNo());
            prepSt.setString( 5 ,inputBean.getMobileNo());
            prepSt.setString( 6 ,inputBean.getEmail());

            prepSt.setString( 7 ,inputBean.getQualification());
            prepSt.setString( 8 ,inputBean.getPerAddress());
            prepSt.setString( 9 ,inputBean.getTemAddress());
            prepSt.setString( 10 ,inputBean.getMemBornPlace());
            prepSt.setString( 11 ,inputBean.getMemCast());
            prepSt.setString( 12 ,inputBean.getMemSubCast());

            prepSt.setString( 13 ,inputBean.getMemIslife());
            prepSt.setString( 14 ,inputBean.getMemExpdate());
            prepSt.setString( 15 ,inputBean.getNoOfBrother());
            prepSt.setString( 16 ,inputBean.getNoOfSister());

            prepSt.setString( 17 ,inputBean.getJobTitle());
            prepSt.setString( 18 ,inputBean.getJobAddress());
            prepSt.setString( 19 ,inputBean.getJobPhone());

            prepSt.setString( 20 ,inputBean.getFatName());
            prepSt.setString( 21 ,inputBean.getFatBirthPlace());
            prepSt.setString( 22 ,inputBean.getFatCast());
            prepSt.setString( 23 ,inputBean.getMothName());
            prepSt.setString( 24 ,inputBean.getMothBirthPlace());
            prepSt.setString( 25 ,inputBean.getMothCast());

            prepSt.setString( 26 ,inputBean.getGrandFatName());
            prepSt.setString( 27 ,inputBean.getGrandFatBirthPlace());
            prepSt.setString( 28 ,inputBean.getGrandFatCast());
            prepSt.setString( 29 ,inputBean.getGrandMothName());
            prepSt.setString( 30 ,inputBean.getGrandMothBirthPlace());
            prepSt.setString( 31 ,inputBean.getGrandMothCast());

            prepSt.setString( 32 ,inputBean.getIsMerrid());
            prepSt.setString( 33 ,inputBean.getWifeName());
            prepSt.setString( 34 ,inputBean.getNoOfSuns());
            prepSt.setString( 35 ,inputBean.getNoOfDoters());
            prepSt.setString( 36 ,inputBean.getStatus());

                            //wife details
            prepSt.setString( 37 ,inputBean.getWifeDob());
            prepSt.setString( 38 ,inputBean.getWifeAdd());
            prepSt.setString( 39 ,inputBean.getWifeEmail());
            prepSt.setString( 40 ,inputBean.getWifeMobile());
            prepSt.setString( 41 ,inputBean.getWifeBirPlace());

            prepSt.setString( 42 ,inputBean.getWifeCast());
            prepSt.setString( 43 ,inputBean.getWifeFatName());
            prepSt.setString( 44 ,inputBean.getWifeFatBirthPlace());
            prepSt.setString( 45 ,inputBean.getWifeFatCast());
            prepSt.setString( 46 ,inputBean.getWifeMothName());

            prepSt.setString( 47 ,inputBean.getWifeMothBirthPlace());
            prepSt.setString( 48 ,inputBean.getWifeMothCast());
            prepSt.setString( 49 ,inputBean.getWifeGrandFatName());
            prepSt.setString( 50 ,inputBean.getWifeGrandFatBirthPlace());
            prepSt.setString( 51 ,inputBean.getWifeGrandFatCast());

            prepSt.setString( 52 ,inputBean.getWifeGrandMothName());
            prepSt.setString( 53 ,inputBean.getWifeGrandMothBirthPlace());
            prepSt.setString( 54 ,inputBean.getWifeGrandMothCast());

            if(mem_img){
                prepSt.setString(55, "PRO_"+inputBean.getMemIdDes()+".png"); //PRO_M00011.jsp
            }else{
                prepSt.setString(55, "PRO_"+inputBean.getMemIdDes()+".png");
            }
            if(fam_img){
                prepSt.setString(56, "FAM_"+inputBean.getMemIdDes()+".png"); //FAM_M00011.jsp
            }else{
                prepSt.setString(56, "FAM_"+inputBean.getMemIdDes()+".png");
            }
            
//            if(inputBean.getMemImgFileFileName()!=null){
//                        prepSt.setString( 55 ,inputBean.getString("IMG_MEMBER"));
//            }
//                
//            if(inputBean.getString("IMG_FAMILY")!=null){
//                    prepSt.setString( 56 ,inputBean.getString("IMG_FAMILY"));
//            }
            prepSt.setString(57, inputBean.getMemIdUp());


            int n = prepSt.executeUpdate();
            
            System.out.println("commit:"+n);
           // con.commit();
            if (n > 0) {
                ok = true;
            }

        } catch (Exception e) {
//            con.rollback();
            ok = false;
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (prepSt != null) {
                prepSt.close();
            }
            
            if (con != null) {
                con.close();
            }
        }
        return ok;
    }

    public boolean deleteData(MemberManagementInputBean bean) throws Exception {
        System.out.println("del mem id:"+bean.getMemId());
        PreparedStatement prepSt = null;
        Connection con = null;
        String deleteUser = null;
        boolean ok = false;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            deleteUser = "DELETE FROM dma_member  where MEM_ID=? ";
            prepSt = con.prepareStatement(deleteUser);
            prepSt.setString(1, bean.getMemId());
            int n = prepSt.executeUpdate();
            if (n > 0) {
                ok = true;
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            ok = false;
            throw e;
        } finally {
            if (prepSt != null) {
                prepSt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ok;
    }



    public boolean checkUserName(String username) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        boolean ok = false;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(true);
            String sql = "SELECT * FROM web_user where USERNAME=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            result = ps.executeQuery();

            if (result.next()) {
                ok = true;
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (result != null) {
                result.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return ok;
    }


    public boolean addData(MemberManagementInputBean inputBean,boolean mem_img,boolean fam_img) throws Exception {
        Connection con = null;
        String sql;
        PreparedStatement preStat = null;
        boolean ok = false;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            sql= "INSERT INTO dma_member ("
                    + "MEM_NAME, MEM_NIC, MEM_DOB,MEM_PHONE,MEM_MOBILE,  EMAIL, "
                    + "QUALIFICATION, PERM_ADD, TEMP_ADD, MEM_BORN_PLACE, MEM_CAST, MEM_SUB_CAST, "
                    + "MEM_TYPE_ISLIFE,MEM_EXP_DATE, NUM_OF_BRO, NUM_OF_SIS, "
                    + "JOB_TITLE, JOB_ADD, JOB_PHONE, "
                    + "FAT_NAME, FAT_ADD, FAT_CAST, MOT_NAME, MOT_ADD, MOT_CAST, "
                    + "GRAN_FAT_NAME, GRAN_FAT_ADD, GRAN_FAT_CAST, GRAN_MOT_NAME, GRAN_MOT_ADD,GRAN_MOT_CAST, "
                    + "IS_MARRIED, WI_NAME, NUM_OF_SUN, NUM_OF_DOT, STATUS,"
                    
                    + "WI_DOB,WI_ADD,WI_EMAIL,WI_MOBILE,WI_BORN_PLACE,"
                    + "WI_CAST,WI_FAT_NAME,WI_FAT_BORN_PLACE,WI_FAT_CAST,WI_MOT_NAME,"
                    + "WI_MOT_BORN_PLACE,WI_MOT_CAST,WI_GRAN_FAT_NAME,WI_GRAN_FAT_BORN_PLACE,WI_GRAN_FAT_CAST,"
                    + "WI_GRAN_MOT_NAME,WI_GRAN_MOT_BORN_PLACE,WI_GRAN_MOT_CAST,IMG_MEMBER,IMG_FAMILY) "
                    + "VALUES ("
                    + "?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?,"
                    + "?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?, ?, "
                    
                    + "?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?);";

            preStat = con.prepareStatement(sql);

            preStat.setString(1, inputBean.getMemName());
            preStat.setString(2, inputBean.getMemNic());
            preStat.setDate(3, Util.convertStringToDBDate(inputBean.getMemDob()));
            preStat.setString(4, inputBean.getPhoneNo());
            preStat.setString(5, inputBean.getMobileNo());
            preStat.setString(6, inputBean.getEmail());
            
            preStat.setString(7, inputBean.getQualification());
            preStat.setString(8, inputBean.getPerAddress());
            preStat.setString(9, inputBean.getTemAddress());
            preStat.setString(10, inputBean.getMemBornPlace());
            preStat.setString(11, inputBean.getMemCast());
            preStat.setString(12, inputBean.getMemSubCast());
            
            preStat.setString(13, inputBean.getMemIslife());
            preStat.setDate(14, Util.convertStringToDBDate(inputBean.getMemExpdate()));
            preStat.setInt(15, Integer.parseInt(inputBean.getNoOfBrother()));
            preStat.setInt(16, Integer.parseInt(inputBean.getNoOfSister()));
            
            preStat.setString(17, inputBean.getJobTitle());
            preStat.setString(18, inputBean.getJobAddress());
            preStat.setString(19, inputBean.getJobPhone());
            
            preStat.setString(20, inputBean.getFatName());
            preStat.setString(21, inputBean.getFatBirthPlace());
            preStat.setString(22, inputBean.getFatCast());
            preStat.setString(23, inputBean.getMothName());
            preStat.setString(24, inputBean.getMothBirthPlace());
            preStat.setString(25, inputBean.getMothCast());
            
            preStat.setString(26, inputBean.getGrandFatName());
            preStat.setString(27, inputBean.getGrandFatBirthPlace());
            preStat.setString(28, inputBean.getGrandFatCast());
            preStat.setString(29, inputBean.getGrandMothName());
            preStat.setString(30, inputBean.getGrandMothBirthPlace());
            preStat.setString(31, inputBean.getGrandMothCast());
            
            preStat.setString(32, inputBean.getIsMerrid());
            preStat.setString(33, inputBean.getWifeName());
            preStat.setInt(34, Integer.parseInt(inputBean.getNoOfSuns()));
            preStat.setInt(35, Integer.parseInt(inputBean.getNoOfDoters()));
            preStat.setInt(36, Status.ACTIVE);
            
            //wife details
            preStat.setDate(37, Util.convertStringToDBDate(inputBean.getWifeDob()));
            preStat.setString(38, inputBean.getWifeAdd());
            preStat.setString(39, inputBean.getWifeEmail());
            preStat.setString(40, inputBean.getWifeMobile());
            preStat.setString(41, inputBean.getWifeBirPlace());
            
            preStat.setString(42, inputBean.getWifeCast());
            preStat.setString(43, inputBean.getWifeFatName());
            preStat.setString(44, inputBean.getWifeFatBirthPlace());
            preStat.setString(45, inputBean.getWifeFatCast());
            preStat.setString(46, inputBean.getWifeMothName());
            
            preStat.setString(47, inputBean.getWifeMothBirthPlace());
            preStat.setString(48, inputBean.getWifeMothCast());
            preStat.setString(49, inputBean.getWifeGrandFatName());
            preStat.setString(50, inputBean.getWifeGrandFatBirthPlace());
            preStat.setString(51, inputBean.getWifeGrandFatCast());
            
            preStat.setString(52, inputBean.getWifeGrandMothName());
            preStat.setString(53, inputBean.getWifeGrandMothBirthPlace());
            preStat.setString(54, inputBean.getWifeGrandMothCast());
            if(mem_img){
                preStat.setString(55, "PRO_"+inputBean.getMemIdDes()+".png"); //PRO_M00011.jsp
            }else{
                preStat.setString(55, null);
            }
            if(fam_img){
                preStat.setString(56, "FAM_"+inputBean.getMemIdDes()+".png"); //FAM_M00011.jsp
            }else{
                preStat.setString(56, null);
            }
            
            
            
            

            int n = preStat.executeUpdate();
            
            if (n >= 0) {
                ok = true;
            }
            con.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStat != null) {
                preStat.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ok;
    }
    
       
    public void getCastList(MemberManagementInputBean inputBean) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(true);
            String sql = " SELECT CAST_ID,CAST_NAME FROM DMA_CAST";
            ps = connection.prepareStatement(sql);
            result = ps.executeQuery();

            while (result.next()) {
                inputBean.getMemCastList().put(result.getString("CAST_ID"), result.getString("CAST_NAME"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (result != null) {
                result.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
    public String getlastMemId() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        int lastMemId=0;
        String lastMemIdStr="M";
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(true);
            String sql = " SELECT max(MEM_ID) AS LST_MEM_ID  FROM dma_member";
            ps = connection.prepareStatement(sql);
            result = ps.executeQuery();

            if (result.next()) {
                lastMemId = result.getInt("LST_MEM_ID");
                lastMemId=lastMemId+1;
                lastMemIdStr=lastMemIdStr.concat(ISOUtil.zeropad(""+lastMemId, 5));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (result != null) {
                result.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    return lastMemIdStr;
    }
    
    public List<MemberBean> downloadData(MemberManagementInputBean bean) throws SQLException, Exception {
        //System.out.println("here 4");
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getHistoryRecordsQuery = null;
        List<MemberBean> dataList = null;
        long totalCount = 0;


        try {

//            con = DBConnection.getConnection();
//            //con.setAutoCommit(true);
//
//            getHistoryRecordsQuery = "SELECT  * FROM (select t.TXN_ID AS ID,tt.DESCRIPTION as TXN_TYPE, T.CUSTOMER_ID,\n"
//                    + "T.RECEPIENT_MOBILE AS RECEPIENT_MOBILE,\n"
//                    + "t.CHANNEL_TYPE,T.AMOUNT AS AMOUNT,T.ORD_ID AS ORD_ID,T.CUSTOMER_ACCOUNT_NUMBER AS CUSTOMER_ACCOUNT_NUMBER,\n"
//                    + "T.CUSTOMER_MOBILE AS CUSTOMER_MOBILE,T.TRACE_NO AS REF_NO,T.BATCH_NO,T.SERVICE_CHARGE AS SERVICE_CHARGE  ,\n"
//                    + "T.TXN_DATE_TIME AS TXN_DATE,T.TIMESTAMP,T.RESPONSE_CODE,S.DESCRIPTION AS STATUS\n"
//                    + "from CLA_TRANSACTION t,CLA_MT_TXN_TYPE tt,\n"
//                    + "CLA_MT_STATUS S \n"
//                    + "where " + where + " and tt.CODE = t.TXN_TYPE AND T.STATUS=S.CODE)t\n"
//                    + "LEFT OUTER JOIN (select DESCRIPTION AS CH_TYPE ,CODE FROM CLA_MT_LISTENER_TYPE)  t2\n"
//                    + "ON t.CHANNEL_TYPE = t2.CODE \n"
//                    + "LEFT  OUTER JOIN (select name AS CUSTOMER_NAME,CUSTOMER_ID FROM CLA_CUSTOMER)  t3\n"
//                    + "ON t.CUSTOMER_ID = t3.CUSTOMER_ID";
//
//            prepSt = con.prepareStatement(getHistoryRecordsQuery);
//
//            res = prepSt.executeQuery();

            dataList = new ArrayList<MemberBean>();

            MemberBean dataBean = null;

            dataBean = new MemberBean();
                dataBean.setCUS_NAME("kkkk");
                //dataBean.setFullCount(2);
                dataList.add(dataBean);
//            while (res.next()) {
//
//                dataBean = new MemberManagementInputBean();
//                dataBean.setTXN_TYPE(res.getString("TXN_TYPE"));
//                dataBean.setCUS_NAME(res.getString("CUSTOMER_NAME"));
//                dataBean.setREC_MOBILE(res.getString("RECEPIENT_MOBILE"));
//                dataBean.setAMOUNT("Rs " + Util.round(res.getString("AMOUNT")));
//                dataBean.setORDER_ID(res.getString("ORD_ID"));
//                dataBean.setCUS_ACOUNT(res.getString("CUSTOMER_ACCOUNT_NUMBER"));
//                dataBean.setChannel(res.getString("CH_TYPE"));
//                dataBean.setCUS_MOBILE(res.getString("CUSTOMER_MOBILE"));
//                dataBean.setSERVICE_FEE(Util.round(res.getString("SERVICE_CHARGE")));
//                dataBean.setDATETIME(Util.formatTimestamp(res.getTimestamp("TIMESTAMP")).toString());
//                dataBean.setFullCount(totalCount);
//                dataList.add(dataBean);
//
//            }

        } catch (Exception e) {
            throw e;

        } finally {
            if (res != null) {
                res.close();
            }
            if (prepSt != null) {
                prepSt.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return dataList;

    }
    
    
        public void loadReportData(MemberManagementInputBean inputBean) throws Exception {

        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        System.out.println("getMemId:"+inputBean.getMemId());
        try {

            con = DBConnection.getConnection();
            getUsersListQuery = "SELECT M.MEM_ID,M.MEM_TYPE_ISLIFE,M.MEM_NAME,M.MEM_NIC, M.MEM_PHONE, "
                    + "M.MEM_MOBILE, M.EMAIL,MC.CAST_NAME AS MEM_CAST,M.MEM_SUB_CAST,M.MEM_BORN_PLACE,"
                    + "M.MEM_DOB ,M.IS_MARRIED ,M.FAT_NAME ,M.FAT_ADD ,M.FAT_CAST ,"
                    + "M.MOT_NAME,M.MOT_ADD,M.MOT_CAST,M.GRAN_FAT_NAME,M.GRAN_FAT_ADD,"
                    + "M.GRAN_FAT_CAST,M.GRAN_MOT_NAME,M.GRAN_MOT_ADD,M.GRAN_MOT_CAST ,M.IMG_MEMBER,"
                    + "M.PERM_ADD,"
                    + "M.WI_NAME,M.NUM_OF_SUN,M.NUM_OF_DOT,M.WI_DOB,M.WI_BORN_PLACE,M.WI_CAST,"
                    + "M.MEM_EXP_DATE, "
                    + "M.NUM_OF_BRO, M.NUM_OF_SIS,"
                    + "M.JOB_TITLE, M.JOB_ADD, M.JOB_PHONE,"
                    + "M.WI_FAT_NAME,M.WI_FAT_BORN_PLACE,M.WI_FAT_CAST,M.WI_MOT_NAME,M.WI_MOT_BORN_PLACE,M.WI_MOT_CAST,"
                    + "M.WI_GRAN_FAT_NAME,M.WI_GRAN_FAT_BORN_PLACE,M.WI_GRAN_FAT_CAST,M.WI_GRAN_MOT_NAME,M.WI_GRAN_MOT_BORN_PLACE,M.WI_GRAN_MOT_CAST "
                    + "FROM dma_member M, dma_cast MC  "
                    + "where M.MEM_CAST=MC.CAST_ID  and M.MEM_ID = ? ";

            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setString(1, inputBean.getMemId());
            res = prepSt.executeQuery();
            
            while (res.next()) {
                //inputBean.getParameterMap().put("Txn_From", res.getString("MEM_ID"));
                
                inputBean.getParameterMap().put("CERT_DATE", Util.getDateNow());
                inputBean.getParameterMap().put("MEM_ID", "M"+ISOUtil.zeropad(res.getString("MEM_ID"), 5));
                inputBean.getParameterMap().put("MEM_TYPE", res.getString("MEM_TYPE_ISLIFE"));
                inputBean.getParameterMap().put("MEM_NAME", res.getString("MEM_NAME"));
                inputBean.getParameterMap().put("MEM_NIC", res.getString("MEM_NIC"));
                inputBean.getParameterMap().put("MEM_TP", res.getString("MEM_PHONE"));
                
                inputBean.getParameterMap().put("MEM_MOB", res.getString("MEM_MOBILE"));
                inputBean.getParameterMap().put("MEM_EMAIL", res.getString("EMAIL"));
                inputBean.getParameterMap().put("MEM_CAST", res.getString("MEM_CAST"));
                inputBean.getParameterMap().put("MEM_SUB_CAST", res.getString("MEM_SUB_CAST"));
                inputBean.getParameterMap().put("MEM_NAT_PLACE", res.getString("MEM_BORN_PLACE"));
                
                inputBean.getParameterMap().put("MEM_DOB", res.getString("MEM_DOB"));
                inputBean.getParameterMap().put("MEM_MERR_STAT", res.getString("IS_MARRIED"));
                inputBean.getParameterMap().put("PERM_ADD", res.getString("PERM_ADD"));
                
                inputBean.getParameterMap().put("FAT_NAME", res.getString("FAT_NAME"));
                inputBean.getParameterMap().put("FAT_PLACE", res.getString("FAT_ADD"));
                inputBean.getParameterMap().put("FAT_CAST", res.getString("FAT_CAST"));
                
                inputBean.getParameterMap().put("MOT_NAME", res.getString("MOT_NAME"));
                inputBean.getParameterMap().put("MOT_PLACE", res.getString("MOT_ADD"));
                inputBean.getParameterMap().put("MOT_CAST", res.getString("MOT_CAST"));
                inputBean.getParameterMap().put("GRA_FAT_NAME", res.getString("GRAN_FAT_NAME"));
                inputBean.getParameterMap().put("GRA_FAT_PLACE", res.getString("GRAN_FAT_ADD"));
                
                inputBean.getParameterMap().put("GRA_FAT_CAST", res.getString("GRAN_FAT_CAST"));
                inputBean.getParameterMap().put("GRA_MOT_NAME", res.getString("GRAN_MOT_NAME"));
                inputBean.getParameterMap().put("GRA_MOT_PLACE", res.getString("GRAN_MOT_ADD"));
                inputBean.getParameterMap().put("GRA_MOT_CAST", res.getString("GRAN_MOT_CAST"));
                
                
                
                String imagePath=InitConfigValue.IMAGE_UPLOAD_PATH+res.getString("IMG_MEMBER");
                if(res.getString("IMG_MEMBER")!=null){
                inputBean.getParameterMap().put("MEM_IMAGE", imagePath);
                }

                inputBean.getParameterMap().put("EXP_DATE", res.getString("MEM_EXP_DATE"));
                
                inputBean.getParameterMap().put("JOB_TITLE", res.getString("JOB_TITLE"));
                inputBean.getParameterMap().put("JOB_ADD", res.getString("JOB_ADD"));
                inputBean.getParameterMap().put("JOB_PHONE", res.getString("JOB_PHONE"));
                
                inputBean.getParameterMap().put("NUM_OF_BRO", res.getString("NUM_OF_BRO"));
                inputBean.getParameterMap().put("NUM_OF_SIS", res.getString("NUM_OF_SIS"));
                
                
                inputBean.getParameterMap().put("WI_NAME", res.getString("WI_NAME"));
                inputBean.getParameterMap().put("NUM_OF_SUN", res.getString("NUM_OF_SUN"));
                inputBean.getParameterMap().put("NUM_OF_DOT", res.getString("NUM_OF_DOT"));
                inputBean.getParameterMap().put("WI_DOB", res.getString("WI_DOB"));
                inputBean.getParameterMap().put("WI_BORN_PLACE", res.getString("WI_BORN_PLACE"));  
                inputBean.getParameterMap().put("WI_CAST", res.getString("WI_CAST"));
                
                inputBean.getParameterMap().put("WI_FAT_NAME", res.getString("WI_FAT_NAME"));
                inputBean.getParameterMap().put("WI_FAT_BORN_PLACE", res.getString("WI_FAT_BORN_PLACE"));
                inputBean.getParameterMap().put("WI_FAT_CAST", res.getString("WI_FAT_CAST"));
                inputBean.getParameterMap().put("WI_MOT_NAME", res.getString("WI_MOT_NAME"));
                inputBean.getParameterMap().put("WI_MOT_BORN_PLACE", res.getString("WI_MOT_BORN_PLACE"));
                inputBean.getParameterMap().put("WI_MOT_CAST", res.getString("WI_MOT_CAST"));
                
                inputBean.getParameterMap().put("WI_GRAN_FAT_NAME", res.getString("WI_GRAN_FAT_NAME"));
                inputBean.getParameterMap().put("WI_GRAN_FAT_BORN_PLACE", res.getString("WI_GRAN_FAT_BORN_PLACE"));
                inputBean.getParameterMap().put("WI_GRAN_FAT_CAST", res.getString("WI_GRAN_FAT_CAST"));
                inputBean.getParameterMap().put("WI_GRAN_MOT_NAME", res.getString("WI_GRAN_MOT_NAME"));
                inputBean.getParameterMap().put("WI_GRAN_MOT_BORN_PLACE", res.getString("WI_GRAN_MOT_BORN_PLACE"));
                inputBean.getParameterMap().put("WI_GRAN_MOT_CAST", res.getString("WI_GRAN_MOT_CAST"));
                
                
                
                
                
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (prepSt != null) {
                prepSt.close();
            }
            if (con != null) {
                con.close();
            }

        }

    }
}
