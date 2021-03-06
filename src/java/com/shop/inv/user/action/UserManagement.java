/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.user.action;

import com.shop.init.Module;
import com.shop.util.LogFileCreator;
import com.shop.login.bean.SessionUserBean;
import com.shop.inv.user.bean.UserBean;
import com.shop.inv.user.bean.UserManagementInputBean;
import com.shop.inv.user.service.UserManagementService;
import com.shop.util.AccessControlService;
import com.shop.util.Common;
import com.shop.init.InitConfigValue;
import com.shop.init.PageVarList;
import com.shop.init.TaskVarList;
import com.shop.login.bean.TaskBean;
import com.shop.util.PasswordValidator;
import com.shop.util.SystemMessage;
import com.shop.util.Util;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author tharaka
 */
public class UserManagement extends ActionSupport implements ModelDriven<UserManagementInputBean>, AccessControlService {

    private UserManagementService service = new UserManagementService();
    UserManagementInputBean inputBean = new UserManagementInputBean();
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
    public UserManagementInputBean getModel() {
        try {
            service.getProfileList(inputBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

    public String List() {
        try {
            List<UserBean> dataList = null;
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

        try {
            service.findData(inputBean);
        } catch (Exception e) {
            addActionError(SystemMessage.COMMON_ERROR_PROCESS);
            LogFileCreator.writeErrorToLog(e);
        }

        return "find";
    }

    public String Update() {

        try {
            if (doValidationUpdate(inputBean)) {

                if (service.updateData(inputBean)) {

                    addActionMessage(SystemMessage.USR_UPDATED);
                    LogFileCreator.writeInfoToLog(SystemMessage.USR_UPDATED);

                } else {
                    addActionError(SystemMessage.USR_UPDATED_ERROR);
                }

            }
        } catch (Exception ex) {
            addActionError(SystemMessage.USR_UPDATED_ERROR);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "update";
    }

    public String Delete() {
        try {
            if (getSub().getUsername().equals(inputBean.getUsername())) {
                inputBean.setMessage(SystemMessage.USR_DELETED_ERROR_SESSUSR);
                inputBean.setSuccess(false);
                return "delete";
            }
            if (service.deleteData(inputBean)) {
                LogFileCreator.writeInfoToLog(SystemMessage.USR_DELETED);
                inputBean.setMessage(SystemMessage.USR_DELETED);
                inputBean.setSuccess(true);
            } else {
                inputBean.setMessage(SystemMessage.USR_DELETED_ERROR);
                inputBean.setSuccess(false);
            }

        } catch (Exception ex) {
            inputBean.setMessage(SystemMessage.USR_DELETED_ERROR);
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
                    addActionMessage(SystemMessage.USR_ADD);
                    LogFileCreator.writeInfoToLog(SystemMessage.USR_ADD);

                } else {
                    addActionError(SystemMessage.USR_ADD_FAIL);
                }
            }

        } catch (Exception ex) {
            addActionError(SystemMessage.USR_ADD_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "add";
    }

    private boolean doValidation(UserManagementInputBean userBean) throws Exception {
        boolean ok = false;
        System.out.println(">1:"+userBean.getMobile());
        System.out.println(">2:"+userBean.getPassword());
        System.out.println(">2:"+userBean.getRepassword());
        System.out.println(">3:"+userBean.getUserPro());
        try {

            if (userBean.getName() == null || userBean.getName().isEmpty()) {
                addActionError(SystemMessage.USR_NAME_EMPTY);
                return ok;
            } else if (userBean.getUsername() == null || userBean.getUsername().isEmpty()) {
                addActionError(SystemMessage.USR_USERNAME_EMPTY);
                return ok;
            } else if (!Util.validateSTRING(userBean.getUsername())) {
                addActionError(SystemMessage.USR_USERNAME_INVALID);
                return ok;
            } else if (service.checkUserName(userBean.getUsername().toLowerCase())) {
                addActionError(SystemMessage.USR_USERNAME_ALREADY);
                return ok;
            } else if (userBean.getEmail() == null || userBean.getEmail().isEmpty()) {
                addActionError(SystemMessage.USR_EMAIL_EMPTY);
                return ok;
            } else if (!Util.validateEMAIL(userBean.getEmail())) {
                addActionError(SystemMessage.USR_EMAIL_INVALID);
                return ok;
            } else if (userBean.getMobile().isEmpty() || userBean.getMobile() == null ) {
                addActionError(SystemMessage.USR_PHONE_EMPTY);
                return ok;

            } else if(!Util.validatePHONENO(userBean.getMobile())){
                addActionError(SystemMessage.USR_PHONE_INVALID);
                return ok;
            }else if (userBean.getPassword() == null || userBean.getPassword().isEmpty()) {
                addActionError(SystemMessage.USR_PASSWORD_EMPTY);
                return ok;
            } else if (userBean.getRepassword() == null || userBean.getRepassword().isEmpty()) {
                addActionError(SystemMessage.USR_CONPASSWORD_EMPTY);
                return ok;
            } else if (!(userBean.getPassword().equals(userBean.getRepassword()))) {
                addActionError(SystemMessage.USR_PASSWORD_NOT_MATCH);
                return ok;
            } else if (userBean.getUserPro().equals("-1")) {
                addActionError(SystemMessage.USR_PROFILE_SELECT);
                return ok;
            }else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;

    }

    private boolean doValidationUpdate(UserManagementInputBean userBean) throws Exception {
        boolean ok = false;

        try {

            if (userBean.getUpname() == null || userBean.getUpname().isEmpty()) {
                addActionError(SystemMessage.USR_NAME_EMPTY);
                return ok;
            } else if (userBean.getUpusername() == null || userBean.getUpusername().isEmpty()) {
                addActionError(SystemMessage.USR_USERNAME_EMPTY);
                return ok;
            } else if (!Util.validateSTRING(userBean.getUpusername())) {
                addActionError(SystemMessage.USR_USERNAME_INVALID);
                return ok;
            }else if (userBean.getUpuserPro().equals("-1")) {
                addActionError(SystemMessage.USR_PROFILE_SELECT);
                return ok;
            } 
            else if (userBean.getUpemail() == null || userBean.getUpemail().isEmpty()) {
                addActionError(SystemMessage.USR_EMAIL_EMPTY);
                return ok;
            } else if (!Util.validateEMAIL(userBean.getUpemail())) {
                addActionError(SystemMessage.USR_EMAIL_INVALID);
                return ok;
            }  else if (!(userBean.getUpmobile().isEmpty() || userBean.getUpmobile() == null) && !Util.validatePHONENO(userBean.getUpmobile())) {
                addActionError(SystemMessage.USR_PHONE_INVALID);
                return ok;

            } else if (userBean.getUpstatus().equals("-1")) {
                addActionError(SystemMessage.USR_STATUS_SELECT);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;

    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.USER_MANAGEMENT, request);
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
        String page = PageVarList.USER_MANAGEMENT;
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
