/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.user.service;

import com.shop.db.DBConnection;

import com.shop.init.Status;

import com.shop.inv.user.bean.UserBean;
import com.shop.inv.user.bean.UserManagementInputBean;
import com.shop.util.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserManagementService {

    public List<UserBean> loadData(UserManagementInputBean bean, String orderBy, int from, int rows) throws Exception {
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        List<UserBean> dataList = null;
        long totalCount = 0;
        try {

            con = DBConnection.getConnection();
            //con.setAutoCommit(true);
            String sqlCount = "select count(*) AS TOTAL FROM web_user where USERNAME LIKE ?";
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

            dataList = new ArrayList<UserBean>();

            while (res.next()) {
                UserBean dataBean = new UserBean();

                dataBean.setProfilename(res.getString("PROFILENAME"));
                dataBean.setName(res.getString("NAME"));
                dataBean.setUsername(res.getString("USERNAME"));
                dataBean.setProfileId(res.getString("PROFILE_ID"));

                dataBean.setEmail(res.getString("EMAIL"));
                dataBean.setMobile(res.getString("MOBILE"));
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

    public void findData(UserManagementInputBean bean) throws Exception {

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
            prepSt.setString(1, bean.getUsername());
            res = prepSt.executeQuery();
            while (res.next()) {
                bean.setUpusername(res.getString("USERNAME"));
                bean.setUpusernamecopy(res.getString("USERNAME"));
                bean.setUpname(res.getString("NAME"));
                bean.setUpuserPro(res.getString("PROFILE_ID"));

                bean.setUpstatus(res.getString("STATUS"));
                bean.setUpemail(res.getString("EMAIL"));
                bean.setUpmobile(res.getString("MOBILE"));

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

    public boolean updateData(UserManagementInputBean inputBean) throws Exception {

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
            prepSt.setString(1, inputBean.getUpname());
            System.out.println("update="+inputBean.getUpusername().toLowerCase());
            prepSt.setString(2, inputBean.getUpusername().toLowerCase());
            prepSt.setInt(3, Integer.parseInt(inputBean.getUpuserPro()));
            prepSt.setInt(4, Integer.parseInt(inputBean.getUpstatus()));

            prepSt.setString(5, inputBean.getUpemail());
            prepSt.setString(6, inputBean.getUpmobile());
            prepSt.setString(7, inputBean.getUpusernamecopy());

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

    public boolean deleteData(UserManagementInputBean bean) throws Exception {

        PreparedStatement prepSt = null;
        Connection con = null;
        String deleteUser = null;
        boolean ok = false;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            deleteUser = "DELETE FROM WEB_USER  where USERNAME=? ";
            prepSt = con.prepareStatement(deleteUser);
            prepSt.setString(1, bean.getUsername());
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

//    public void getProfileList(UserManagementInputBean inputBean) throws Exception {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet result = null;
//
//        try {
//            connection = DBConnection.getConnection();
//            connection.setAutoCommit(true);
//            String sql = " SELECT PROFILE_ID,DESCRIPTION FROM CLA_USER_PROFILE where STATUS=?";
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, Status.ACTIVE);
//            result = ps.executeQuery();
//
//            while (result.next()) {
//                inputBean.getUserProList().put(result.getString("PROFILE_ID"), result.getString("DESCRIPTION"));
//            }
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            if (result != null) {
//                result.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//
//    }

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

//    public boolean checkUserNameUpdate(String earlierUserName, String username) throws Exception {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet result = null;
//        boolean ok = false;
//
//        try {
//            connection = DBConnection.getConnection();
//            connection.setAutoCommit(true);
//            String sql = "SELECT * FROM CLA_USER where USERNAME!=? and USERNAME=?";
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, earlierUserName);
//            ps.setString(2, username);
//            result = ps.executeQuery();
//
//            if (result.next()) {
//                ok = true;
//            }
//
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            
//            if (result != null) {
//                result.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            
//            if (connection != null) {
//                connection.close();
//            }
//        }
//        return ok;
//    }

    public boolean addData(UserManagementInputBean inputBean) throws Exception {
        Connection con = null;
        String sql;
        PreparedStatement preStat = null;
        boolean ok = false;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            sql = "INSERT INTO web_user(USERNAME,NAME,STATUS,PROFILE_ID,PASSWORD,"
                    + "EMAIL,MOBILE) "
                    + " VALUES(?,?,?,?,?,  ?,?)";

            preStat = con.prepareStatement(sql);

            preStat.setString(1, inputBean.getUsername().toLowerCase());
            preStat.setString(2, inputBean.getName());
            preStat.setString(3, "1");
            preStat.setString(4, inputBean.getUserPro());
            preStat.setString(5, Util.generateHash(inputBean.getPassword()));
            
            preStat.setString(6, inputBean.getEmail());
            preStat.setString(7, inputBean.getMobile());

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
    
        public void getProfileList(UserManagementInputBean inputBean) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(true);
            String sql = " SELECT PROFILE_ID,DESCRIPTION FROM WEB_USER_PROFILE where STATUS=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, Status.ACTIVE);
            result = ps.executeQuery();

            while (result.next()) {
                inputBean.getUserProList().put(result.getString("PROFILE_ID"), result.getString("DESCRIPTION"));
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

}
