/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.member.service;

import com.shop.db.DBConnection;
import com.shop.init.Status;
import com.shop.inv.member.bean.ChildrenBean;
import com.shop.inv.member.bean.ChildrenInputBean;
//import com.shop.inv.user.bean.UserBean;
//import com.shop.inv.user.bean.UserManagementInputBean;
import com.shop.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.jpos.iso.ISOUtil;

/**
 *
 * @author Kreshan Rajendran
 */
public class ChildrenService {

    public List<ChildrenBean> loadData(ChildrenInputBean bean, String orderBy, int from, int rows) throws Exception {
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        List<ChildrenBean> dataList = null;
        long totalCount = 0;
        try {
            System.out.println("getMemberload():"+bean.getMemberload());
            con = DBConnection.getConnection();
            //con.setAutoCommit(true);
            String sqlCount = "select count(*) AS TOTAL FROM dma_member_children where MEM_ID LIKE ?";
            prepSt = con.prepareStatement(sqlCount);
            prepSt.setString(1, "%" + bean.getMemberload() + "%");
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


            getUsersListQuery = "SELECT ID,MEM_ID,CHILD_NAME,"
                    + "CHILD_DOB,GENDER,MARRIED_STATUS,"
                    + "EDUCATION,ADDRESS,PHONE,MOBILE,EMAIL "
                    + "FROM dma_member_children where MEM_ID LIKE ? " + orderBy + " LIMIT " + from + "," + rows;

            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setString(1, "%" + bean.getMemberload() + "%");
            res = prepSt.executeQuery();

            dataList = new ArrayList<ChildrenBean>();

            while (res.next()) {
                ChildrenBean dataBean = new ChildrenBean();

                dataBean.setChile_id(res.getString("ID"));
                dataBean.setMem_id(res.getString("MEM_ID"));
                dataBean.setMem_id_des("M"+ISOUtil.zeropad(res.getString("MEM_ID"),5));
                dataBean.setChild_name(res.getString("CHILD_NAME"));

                dataBean.setChild_dob(res.getString("CHILD_DOB"));
                dataBean.setChild_gender(res.getString("GENDER"));
                dataBean.setChild_merrid_status(res.getString("MARRIED_STATUS"));
                
                dataBean.setChildEdu(res.getString("EDUCATION"));
                dataBean.setChildAddr(res.getString("ADDRESS"));
                dataBean.setChildPhone(res.getString("PHONE"));
                dataBean.setChildMobile(res.getString("MOBILE"));
                dataBean.setChildEmail(res.getString("EMAIL"));

                
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

    public void findData(ChildrenInputBean inputBean) throws Exception {

        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        try {

            con = DBConnection.getConnection();
            //con.setAutoCommit(true);
            getUsersListQuery = "SELECT MEM_ID,CHILD_NAME,CHILD_DOB,GENDER,MARRIED_STATUS,"
                    + "EDUCATION,ADDRESS,PHONE,MOBILE,EMAIL "
                    + " from dma_member_children Where ID=?";

            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setString(1, inputBean.getChildId());
            res = prepSt.executeQuery();
            while (res.next()) {
                
                    inputBean.setMemberload(res.getString("MEM_ID"));
                    inputBean.setChildName(res.getString("CHILD_NAME"));
                    inputBean.setChildDob(res.getString("CHILD_DOB"));
                    inputBean.setChildGender(res.getString("GENDER"));
                    inputBean.setChildMerStatus(res.getString("MARRIED_STATUS"));
            
                    inputBean.setChildEdu(res.getString("EDUCATION"));
                    inputBean.setChildAddr(res.getString("ADDRESS"));
                    inputBean.setChildPhone(res.getString("PHONE"));
                    inputBean.setChildMobile(res.getString("MOBILE"));
                    inputBean.setChildEmail(res.getString("EMAIL"));

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

    public boolean updateData(ChildrenInputBean inputBean) throws Exception {

        boolean ok = false;
        PreparedStatement preStat = null;
        ResultSet res = null;
        Connection con = null;
        String sql = null;
        try {

            con = DBConnection.getConnection();
            //con.setAutoCommit(true);

            sql = "UPDATE dma_member_children SET CHILD_NAME=?,CHILD_DOB=?,GENDER=?,MARRIED_STATUS=?,"
                    + "EDUCATION=?,ADDRESS=?,PHONE=?,MOBILE=?,EMAIL=?  Where ID=?";
            preStat = con.prepareStatement(sql);
            //preStat.setString(1, inputBean.getMemberload());
            preStat.setString(1, inputBean.getUpchildName());
            preStat.setString(2, inputBean.getUpchildDob());
            preStat.setString(3, inputBean.getUpchildGender());
            preStat.setString(4, inputBean.getUpchildMerStatus());
            
            preStat.setString(5, inputBean.getUpchildEdu());
            preStat.setString(6, inputBean.getUpchildAddr());
            preStat.setString(7, inputBean.getUpchildPhone());
            preStat.setString(8, inputBean.getUpchildMobile());
            preStat.setString(9, inputBean.getUpchildEmail());
            
            preStat.setString(10, inputBean.getChildId());

            int n = preStat.executeUpdate();
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
            if (preStat != null) {
                preStat.close();
            }
            
            if (con != null) {
                con.close();
            }
        }
        return ok;
    }

    public boolean deleteData(ChildrenInputBean bean) throws Exception {

        PreparedStatement prepSt = null;
        Connection con = null;
        String deleteUser = null;
        boolean ok = false;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            deleteUser = "DELETE FROM dma_member_children  where ID=? ";
            prepSt = con.prepareStatement(deleteUser);
            prepSt.setString(1, bean.getChildId());
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


    public boolean addData(ChildrenInputBean inputBean) throws Exception {
        Connection con = null;
        String sql;
        PreparedStatement preStat = null;
        boolean ok = false;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            sql = "INSERT INTO dma_member_children(MEM_ID,CHILD_NAME,CHILD_DOB,GENDER,MARRIED_STATUS,"
                    + "EDUCATION,ADDRESS,PHONE,MOBILE,EMAIL) "
                    + " VALUES(?,?,?,?,?,   ?,?,?,?,?)";

            preStat = con.prepareStatement(sql);

            preStat.setString(1, inputBean.getMemberload());
            preStat.setString(2, inputBean.getChildName());
            preStat.setString(3, inputBean.getChildDob());
            preStat.setString(4, inputBean.getChildGender());
            preStat.setString(5, inputBean.getChildMerStatus());
            
            preStat.setString(6, inputBean.getChildEdu());
            preStat.setString(7, inputBean.getChildAddr());
            preStat.setString(8, inputBean.getChildPhone());
            preStat.setString(9, inputBean.getChildMobile());
            preStat.setString(10, inputBean.getChildEmail());

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
    
        public void getmemberloadList(ChildrenInputBean inputBean) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(true);
            String sql = " SELECT MEM_ID,MEM_NAME FROM dma_member where STATUS=? order by MEM_ID";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, Status.ACTIVE);
            result = ps.executeQuery();

            while (result.next()) {
                String memStr="[M"+ISOUtil.zeropad(result.getString("MEM_ID"), 5)+"] "+result.getString("MEM_NAME");
                inputBean.getMemberloadList().put(result.getString("MEM_ID"), memStr);
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
