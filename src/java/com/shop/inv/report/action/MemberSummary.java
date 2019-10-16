/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.report.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.init.PageVarList;
import com.shop.init.TaskVarList;
import com.shop.inv.report.bean.MemberSummaryBean;
import com.shop.inv.report.bean.MemberSummaryInputBean;
import com.shop.inv.report.service.MemberSummaryService;
import com.shop.login.bean.SessionUserBean;
import com.shop.login.bean.TaskBean;
import com.shop.util.AccessControlService;
import com.shop.util.Common;
import com.shop.util.LogFileCreator;
import com.shop.util.SystemMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import java.util.List;

/**
 *
 * @author Kreshan Rajendran
 */
public class MemberSummary extends ActionSupport implements ModelDriven<MemberSummaryInputBean>, AccessControlService {

    private MemberSummaryService service = new MemberSummaryService();
    MemberSummaryInputBean inputBean = new MemberSummaryInputBean();


    public SessionUserBean getSub(){
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }
    
    public HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }
    public String execute() {
        System.out.println("calling....1");
        return SUCCESS;
    }

    @Override
    public MemberSummaryInputBean getModel() {
        try {
            System.out.println("calling....2");
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

    public String List() {
        try {
            List<MemberSummaryBean> dataList = null;
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
            }

            dataList = service.loadData(inputBean, orderBy, from, rows);

            if (!dataList.isEmpty()) {
                records = dataList.get(0).getFullCount();
                inputBean.setRecords(records);
                inputBean.setGridModel(dataList);
                int total = (int) Math.ceil((double) records / (double) rows);
                inputBean.setTotal(total);
            } else {
                inputBean.setRecords(0L);
                inputBean.setTotal(0);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
            addActionError(SystemMessage.COMMON_ERROR_PROCESS);
        }
        return "list";
    }

   

   
    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.MEMBER_SUMMARY, request);
        inputBean.setVadd(true);
        inputBean.setVupdate(true);
        inputBean.setVdelete(true);
        inputBean.setVdownload(true);
        inputBean.setVresetpass(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setVadd(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setVupdate(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setVdelete(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    inputBean.setVdownload(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.PWRESET)) {
                    inputBean.setVresetpass(false);
                }
            }
        }

        return true;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.MEMBER_SUMMARY;
        String task = null;
        if ("View".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("List".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Add".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("Find".equals(method)) {
            task = TaskVarList.UPDATE;
        } else if ("Update".equals(method)) {
            task = TaskVarList.UPDATE;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("Download".equals(method)) {
            System.out.println("downnnnnn000");
            task = TaskVarList.UPDATE;
            return true;
            //task = TaskVarList.DOWNLOAD;
        } else if ("ResetPw".equals(method)) {
            task = TaskVarList.PWRESET;
        }

        if ("execute".equals(method)) {
            status = true;
        } else {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(task, page, session);
        }
        return status;
    }

}



