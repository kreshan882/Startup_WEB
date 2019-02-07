/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.global;

import com.shop.db.DBConnection;
import com.shop.init.InitConfigValue;
import com.shop.init.InitConfigValueReader;
import com.shop.login.bean.SessionUserBean;
import com.shop.util.LogFileCreator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author kreshan
 */
public class ContextListener implements ServletContextListener {

    SessionUserBean sub = new SessionUserBean();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
//            InitConfigValue.WS_URL = "http://127.0.0.1:8080/webservice";
//            InitConfigValue.LOGPATH = "/opt/mcs_indpro/logs/";
//            InitConfigValue.QR_IMG_PATH="";
//            
            if (System.getProperty("os.name").startsWith("Windows")) {
                InitConfigValue.SCONFIGPATH = "C:/mcs_indpro/conf/";
            }else if (System.getProperty("os.name").startsWith("Linux")) {
                InitConfigValue.SCONFIGPATH = "/opt/mcs_indpro/conf/";
            }
                
		
            InitConfigValueReader.readConfigValues();
            System.out.println("Create Db Pool:"+InitConfigValue.DBPASSWORD);
            DBConnection.createDbPool();

            System.out.println("Create Db Pool sucess..");
        } catch (Exception e) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, e);
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
