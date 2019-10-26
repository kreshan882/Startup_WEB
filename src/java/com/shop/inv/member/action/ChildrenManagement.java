/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.member.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.init.PageVarList;
import com.shop.init.TaskVarList;
import com.shop.inv.member.bean.ChildrenBean;
import com.shop.inv.member.bean.ChildrenInputBean;
import com.shop.inv.member.service.ChildrenService;
import com.shop.login.bean.SessionUserBean;
import com.shop.login.bean.TaskBean;
import com.shop.util.AccessControlService;
import com.shop.util.Common;
import com.shop.util.LogFileCreator;
import com.shop.util.PasswordValidator;
import com.shop.util.SystemMessage;
import com.shop.util.Util;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Kreshan Rajendran
 */
public class ChildrenManagement extends ActionSupport implements ModelDriven<ChildrenInputBean>, AccessControlService {

    private ChildrenService service = new ChildrenService();
    ChildrenInputBean inputBean = new ChildrenInputBean();
    PasswordValidator pwdvalidator = new PasswordValidator();

    public SessionUserBean getSub(){
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }
    
    public HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }
    public String execute() {
        return SUCCESS;
    }
    

    
    
    @Override
    public ChildrenInputBean getModel() {
        try {
            service.getmemberloadList(inputBean);
            inputBean.getChildGenderList().putAll(inputBean.getChildGenderList());
            inputBean.getChildMerStatusList().putAll(inputBean.getChildMerStatusList());
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

    public String List() {
        try {
            List<ChildrenBean> dataList = null;
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

    public String Find() {
        System.out.println("getChildId::"+inputBean.getChildId());
        try {
            service.findData(inputBean);
        } catch (Exception e) {
            addActionError(SystemMessage.COMMON_ERROR_PROCESS);
            LogFileCreator.writeErrorToLog(e);
        }

        return "find";
    }

    public String Update() {
        System.out.println("getChildId::"+inputBean.getChildId());
        try {
            if (doValidationUpdate(inputBean)) {

                if (service.updateData(inputBean)) {

                    addActionMessage(SystemMessage.CHILD_UPDATED);
                    LogFileCreator.writeInfoToLog(SystemMessage.CHILD_UPDATED);

                } else {
                    addActionError(SystemMessage.CHILD_UPDATED_ERROR);
                }

            }
        } catch (Exception ex) {
            addActionError(SystemMessage.CHILD_UPDATED_ERROR);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "update";
    }

    public String Delete() {
        try {

            if (service.deleteData(inputBean)) {
                LogFileCreator.writeInfoToLog(SystemMessage.CHILD_DELETED);
                inputBean.setMessage(SystemMessage.CHILD_DELETED);
                inputBean.setSuccess(true);
            } else {
                inputBean.setMessage(SystemMessage.CHILD_DELETED_ERROR);
                inputBean.setSuccess(false);
            }

        } catch (Exception ex) {
            inputBean.setMessage(SystemMessage.CHILD_DELETED_ERROR);
            inputBean.setSuccess(false);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "delete";
    }

    public String Add() {
        try {
            if (doValidation(inputBean)) {

                if (service.addData(inputBean)) {
                    addActionMessage(SystemMessage.CHILD_ADD);
                    LogFileCreator.writeInfoToLog(SystemMessage.CHILD_ADD);

                } else {
                    addActionError(SystemMessage.CHILD_ADD_FAIL);
                }
            }

        } catch (Exception ex) {
            addActionError(SystemMessage.CHILD_ADD_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "add";
    }

    private boolean doValidation(ChildrenInputBean userBean) throws Exception {
        boolean ok = false;
        System.out.println(">1:"+userBean.getChildName());

        try {

            if (userBean.getChildName() == null || userBean.getChildName().isEmpty()) {
                addActionError(SystemMessage.CHILD_NAME_EMPTY);
                return ok;
            } else if (!Util.validateNAME(userBean.getChildName())) {
                addActionError(SystemMessage.CHILD_NAME_INVALID);
                return ok;
            } 
            else if (userBean.getChildGender().equals("-1")) {
                addActionError(SystemMessage.CHILD_GENDER_SELECT);
                return ok;
            }else if (userBean.getChildMerStatus().equals("-1")) {
                addActionError(SystemMessage.CHILD_MERRIED_SELECT);
                return ok;
            }
            else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;

    }

        private boolean doValidationUpdate(ChildrenInputBean userBean) throws Exception {
        boolean ok = false;
        System.out.println(">1:"+userBean.getUpchildName());

        try {

            if (userBean.getUpchildName() == null || userBean.getUpchildName().isEmpty()) {
                addActionError(SystemMessage.CHILD_NAME_EMPTY);
                return ok;
            } else if (!Util.validateNAME(userBean.getUpchildName())) {
                addActionError(SystemMessage.CHILD_NAME_INVALID);
                return ok;
            } 
            else if (userBean.getUpchildGender().equals("-1")) {
                addActionError(SystemMessage.CHILD_GENDER_SELECT);
                return ok;
            }else if (userBean.getUpchildMerStatus().equals("-1")) {
                addActionError(SystemMessage.CHILD_MERRIED_SELECT);
                return ok;
            }
            else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;

    }
   

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.CHILDREN_MANAGEMENT, request);
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
        String page = PageVarList.CHILDREN_MANAGEMENT;
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
            task = TaskVarList.DOWNLOAD;
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

