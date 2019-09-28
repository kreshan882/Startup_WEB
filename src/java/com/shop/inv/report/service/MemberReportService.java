/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.report.service;

import com.shop.db.DBConnection;
import com.shop.init.Status;
import com.shop.inv.report.bean.MemberReportBean;
import com.shop.inv.report.bean.MemberReportInputBean;
import com.shop.util.Util;
import java.sql.Connection;
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
public class MemberReportService {
    
    public List<MemberReportBean> loadData(MemberReportInputBean bean, String orderBy, int from, int rows) throws Exception {
        System.out.println("bean.getMemCastID()"+bean.getMemCastID());
        PreparedStatement prepSt = null;
         ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        List<MemberReportBean> dataList = null;
        long totalCount = 0;
        try {

            con = DBConnection.getConnection();
            //con.setAutoCommit(true);
            //String sqlCount = "select count(*) AS TOTAL FROM web_user where USERNAME LIKE ?";
            String sqlCount = "select count(*) AS TOTAL FROM dma_member where MEM_ID LIKE ? and MEM_CAST like ?";
            prepSt = con.prepareStatement(sqlCount);
            prepSt.setString(1, "%" + bean.getMemId().toUpperCase() + "%");
            prepSt.setString(2, "%" + bean.getMemCastID() + "%");
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
            getUsersListQuery ="SELECT M.MEM_ID,M.MEM_NAME,M.PERM_ADD,M.TEMP_ADD,M.JOB_ADD,"
                    + "M.MEM_PHONE,M.MEM_MOBILE,M.JOB_PHONE,M.CREATE_DATE,MC.CAST_NAME  "
                    + "FROM dma_member M,dma_cast MC "
                    + " where M.MEM_CAST=MC.CAST_ID and M.MEM_ID like ? and M.MEM_CAST like ?";


            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setString(1, "%" + bean.getMemId().toUpperCase() + "%");
            prepSt.setString(2, "%" + bean.getMemCastID() + "%");
            res = prepSt.executeQuery();

            dataList = new ArrayList<MemberReportBean>();

            while (res.next()) {
                MemberReportBean dataBean = new MemberReportBean();

                dataBean.setMemId(res.getString("MEM_ID"));
                dataBean.setMemIdDes("M"+ISOUtil.zeropad(res.getString("MEM_ID"),4));
                dataBean.setMemName(res.getString("MEM_NAME"));
                dataBean.setPerAddr(res.getString("PERM_ADD"));
                dataBean.setTemAddr(res.getString("TEMP_ADD"));
                dataBean.setOffAddr(res.getString("JOB_ADD"));
                
                dataBean.setTpNum(res.getString("MEM_PHONE"));
                dataBean.setMobileNum(res.getString("MEM_MOBILE"));
                dataBean.setOffPhnNum(res.getString("JOB_PHONE"));
                dataBean.setRegDate(Util.changeDateFormatyyyyMMdd(res.getString("CREATE_DATE")));
                dataBean.setMemCast(res.getString("CAST_NAME"));
                
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


    

    
    
    public void loadReportData(MemberReportInputBean inputBean) throws Exception {

        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        List<MemberReportBean> dataList = null;
        System.out.println("getMemId:"+inputBean.getMemId());
        try {

            con = DBConnection.getConnection();
            getUsersListQuery = "SELECT M.MEM_ID,M.MEM_NAME,M.PERM_ADD,M.TEMP_ADD,M.JOB_ADD,"
                    + "M.MEM_PHONE,M.MEM_MOBILE,M.JOB_PHONE,M.CREATE_DATE,MC.CAST_NAME  "
                    + "FROM dma_member M,dma_cast MC "
                    + " where M.MEM_CAST=MC.CAST_ID and M.MEM_ID like ? and M.MEM_CAST like ?";

            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setString(1, "%" + inputBean.getMemId().toUpperCase() + "%");
            prepSt.setString(2, "%" + inputBean.getMemCastID() + "%");
            res = prepSt.executeQuery();
             
            
            inputBean.getParameterMap().put("CERT_DATE", Util.getDateNow());
            
            dataList = new ArrayList<MemberReportBean>();
            MemberReportBean dataBean = null;
                
            while (res.next()) {
                dataBean=new MemberReportBean();
                dataBean.setMemName(res.getString("MEM_NAME"));
                dataBean.setPerAddr(res.getString("PERM_ADD"));
                dataBean.setTemAddr(res.getString("TEMP_ADD"));
                dataBean.setOffAddr(res.getString("JOB_ADD"));
                
                dataBean.setTpNum(res.getString("MEM_PHONE"));
                dataBean.setMobileNum(res.getString("MEM_MOBILE"));
                dataBean.setOffPhnNum(res.getString("JOB_PHONE"));
               
                dataList.add(dataBean);
            }
            inputBean.setReportdatalist(dataList);
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
