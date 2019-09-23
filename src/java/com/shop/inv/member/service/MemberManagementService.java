/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.member.service;

import com.shop.db.DBConnection;
import com.shop.init.Status;
import com.shop.inv.member.bean.MemberBean;
import com.shop.inv.member.bean.MemberManagementInputBean;
import com.shop.util.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            String sqlCount = "select count(*) AS TOTAL FROM dma_member where MEM_ID LIKE ? or  MEM_NAME=?";
            prepSt = con.prepareStatement(sqlCount);
            prepSt.setString(1, "%" + bean.getSearchname() + "%");
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


            getUsersListQuery = "SELECT up.DESCRIPTION AS PROFILENAME,u.NAME,u.USERNAME,u.PROFILE_ID, u.EMAIL,"
                    + "u.MOBILE,u.STATUS,u.CREATE_DATE  "
                    + "FROM WEB_USER u,WEB_USER_PROFILE up  "
                    + "where u.PROFILE_ID=up.PROFILE_ID AND UPPER(u.USERNAME) LIKE ? " + orderBy + " LIMIT " + from + "," + rows;

            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setString(1, "%" + bean.getSearchname().toUpperCase() + "%");
            res = prepSt.executeQuery();

            dataList = new ArrayList<MemberBean>();

            while (res.next()) {
                MemberBean dataBean = new MemberBean();

//                dataBean.setProfilename(res.getString("PROFILENAME"));
//                dataBean.setName(res.getString("NAME"));
//                dataBean.setUsername(res.getString("USERNAME"));
//                dataBean.setProfileId(res.getString("PROFILE_ID"));
//
//                dataBean.setEmail(res.getString("EMAIL"));
//                dataBean.setMobile(res.getString("MOBILE"));
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

    public void findData(MemberManagementInputBean bean) throws Exception {

        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        try {

            con = DBConnection.getConnection();
            //con.setAutoCommit(true);
            getUsersListQuery = "SELECT USERNAME,NAME,PROFILE_ID,STATUS,EMAIL,MOBILE "
                    + " from WEB_USER Where USERNAME=?";

            prepSt = con.prepareStatement(getUsersListQuery);
//            prepSt.setString(1, bean.getUsername());
            res = prepSt.executeQuery();
            while (res.next()) {
//                bean.setUpusername(res.getString("USERNAME"));
//                bean.setUpusernamecopy(res.getString("USERNAME"));
//                bean.setUpname(res.getString("NAME"));
//                bean.setUpuserPro(res.getString("PROFILE_ID"));
//
//                bean.setUpstatus(res.getString("STATUS"));
//                bean.setUpemail(res.getString("EMAIL"));
//                bean.setUpmobile(res.getString("MOBILE"));

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

    public boolean updateData(MemberManagementInputBean inputBean) throws Exception {

        boolean ok = false;
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String sql = null;
        try {

            con = DBConnection.getConnection();
            //con.setAutoCommit(true);

            sql = "UPDATE WEB_USER SET NAME=?,USERNAME=?,PROFILE_ID=?,STATUS=?,"
                    + "EMAIL=?,MOBILE=? Where USERNAME=?";
            prepSt = con.prepareStatement(sql);
//            prepSt.setString(1, inputBean.getUpname());
//            System.out.println("update="+inputBean.getUpusername().toLowerCase());
//            prepSt.setString(2, inputBean.getUpusername().toLowerCase());
//            prepSt.setInt(3, Integer.parseInt(inputBean.getUpuserPro()));
//            prepSt.setInt(4, Integer.parseInt(inputBean.getUpstatus()));
//
//            prepSt.setString(5, inputBean.getUpemail());
//            prepSt.setString(6, inputBean.getUpmobile());
//            prepSt.setString(7, inputBean.getUpusernamecopy());

            int n = prepSt.executeUpdate();
            if (n > 0) {
                ok = true;
            }

        } catch (Exception e) {
//            con.rollback();
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

        PreparedStatement prepSt = null;
        Connection con = null;
        String deleteUser = null;
        boolean ok = false;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            deleteUser = "DELETE FROM WEB_USER  where USERNAME=? ";
            prepSt = con.prepareStatement(deleteUser);
//            prepSt.setString(1, bean.getUsername());
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


    public boolean addData(MemberManagementInputBean inputBean) throws Exception {
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
                    + "IS_MARRIED, WIFR_NAME, NUM_OF_SUN, NUM_OF_DOT, STATUS) "
                    + "VALUES ("
                    + "?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?,"
                    + "?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?,"
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
}
