/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.report.service;

import com.shop.db.DBConnection;
import com.shop.inv.report.bean.MemberSummaryBean;
import com.shop.inv.report.bean.MemberSummaryInputBean;
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
public class MemberSummaryService {
     public List<MemberSummaryBean> loadData(MemberSummaryInputBean bean, String orderBy, int from, int rows) throws Exception {
        PreparedStatement prepSt = null;
         ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        List<MemberSummaryBean> dataList = null;
        long totalCount = 0;
        try {

            con = DBConnection.getConnection();
            //con.setAutoCommit(true);
            //String sqlCount = "select count(*) AS TOTAL FROM web_user where USERNAME LIKE ?";
            String sqlCount = "SELECT count(*) AS TOTAL FROM dma_cast C ";
            prepSt = con.prepareStatement(sqlCount);
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
            getUsersListQuery ="SELECT C.CAST_NAME,count(MEM_CAST) AS MEM_COUNT "
                    + "FROM dma_member M,dma_cast C "
                    + "where M.MEM_CAST=C.CAST_ID  group By MEM_CAST";


            prepSt = con.prepareStatement(getUsersListQuery);
            res = prepSt.executeQuery();

            dataList = new ArrayList<MemberSummaryBean>();

            while (res.next()) {
                MemberSummaryBean dataBean = new MemberSummaryBean();

                dataBean.setCastName(res.getString("CAST_NAME"));
                dataBean.setCastCount(res.getString("MEM_COUNT"));
                
                
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

}
