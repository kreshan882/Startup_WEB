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
import com.shop.inv.member.bean.MemberBean;
import com.shop.inv.member.bean.MemberManagementInputBean;
import com.shop.inv.member.service.MemberManagementService;
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
import org.jpos.iso.ISOUtil;

/**
 *
 * @author Kreshan Rajendran
 */
public class EditAndViewMemberManagement extends ActionSupport implements ModelDriven<MemberManagementInputBean>, AccessControlService {

    private MemberManagementService service = new MemberManagementService();
    MemberManagementInputBean inputBean = new MemberManagementInputBean();


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
    public MemberManagementInputBean getModel() {
        try {
            System.out.println("calling....2");
            service.getCastList(inputBean);
            inputBean.setMemId(service.getlastMemId());
            inputBean.getMemIslifeList().putAll(inputBean.getMemIslifeList());
            inputBean.getIsMerridList().putAll(inputBean.getIsMerridList());
            inputBean.getNumberList().putAll(inputBean.getNumberList());    
            inputBean.getStatusList().putAll(inputBean.getStatusList());
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

    public String List() {
        try {
            List<MemberBean> dataList = null;
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

            if (service.deleteData(inputBean)) {
                LogFileCreator.writeInfoToLog(SystemMessage.MEMB_DELETED);
                inputBean.setMessage(SystemMessage.MEMB_DELETED);
                inputBean.setSuccess(true);
            } else {
                inputBean.setMessage(SystemMessage.MEMB_DELETED_ERROR);
                inputBean.setSuccess(false);
            }

        } catch (Exception ex) {
            inputBean.setMessage(SystemMessage.MEMB_DELETED_ERROR);
            inputBean.setSuccess(false);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "delete";
    }

    public String Download() throws Exception {
        System.out.println("here 1"+inputBean.getMemId());
        inputBean.setMemId("4");
        try{ 
                service.loadReportData(inputBean);

                
//                inputBean.getParameterMap().put("Txn_From", "3");
//                inputBean.getParameterMap().put("Txn_To", "4");
//                inputBean.getParameterMap().put("Channel_type", "ck");
//                inputBean.setReportdatalist(service.downloadData(inputBean)); // sun list
                
                String Cert_Name="cert_M"+ISOUtil.zeropad(inputBean.getMemId(), 4)+".pdf";
                inputBean.setFileName(Cert_Name);
        }catch(Exception e){
            addActionError(SystemMessage.COMMON_ERROR_PROCESS);
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "txnreport";
        

    }

    private boolean doValidation(MemberManagementInputBean userBean) throws Exception {
        boolean ok = false;
        System.out.println(">1:"+userBean.getMemId());
        System.out.println(">2:"+userBean.getMemName());
        System.out.println(">2:"+userBean.getMemDob());
        System.out.println(">3:"+userBean.getEmail());
        try {

            if (userBean.getMemName() == null || userBean.getMemName().isEmpty()) {
                addActionError(SystemMessage.MEMB_NAME_EMPTY);
                return ok;
            } else if (!Util.validateSTRING(userBean.getMemName())) {
                addActionError(SystemMessage.MEMB_NAME_INVALID);
                return ok;
            } 
            else if (userBean.getMemNic() == null || userBean.getMemNic().isEmpty()) {
                addActionError(SystemMessage.MEMB_NIC_EMPTY);
                return ok;
            } else if (!Util.validateNIC(userBean.getMemNic())) {
                addActionError(SystemMessage.MEMB_NIC_INVALID);
                return ok;
            }
            else if (userBean.getMemDob() == null || userBean.getMemDob().isEmpty()) {
                addActionError(SystemMessage.MEMB_DOB_EMPTY);
                return ok;
            }
            else if (!Util.validatePHONENO(userBean.getPhoneNo())) {
                addActionError(SystemMessage.MEMB_PHONE_INVALID);
                return ok;
            }
            else if (!Util.validatePHONENO(userBean.getMobileNo())) {
                addActionError(SystemMessage.MEMB_MOBILE_INVALID);
                return ok;
            }
            else if (!Util.validateEMAIL(userBean.getEmail())) {
                addActionError(SystemMessage.MEMB_EMAIL_INVALID);
                return ok;
            }
            
            
            else if (!Util.validateDESCRIPTION(userBean.getQualification())) {
                addActionError(SystemMessage.MEMB_QUALI_INVALID);
                return ok;
            }
            else if (!Util.validateDESCRIPTION(userBean.getPerAddress())) {
                addActionError(SystemMessage.MEMB_PAADD_INVALID);
                return ok;
            }
            else if (!Util.validateDESCRIPTION(userBean.getTemAddress())) {
                addActionError(SystemMessage.MEMB_TMADD_INVALID);
                return ok;
            }
            
            
            else if (!Util.validateDESCRIPTION(userBean.getMemBornPlace())) {
                addActionError(SystemMessage.MEMB_BORNPLAVE_INVALID);
                return ok;
            }else if (userBean.getMemCast().equals("-1")) {
                addActionError(SystemMessage.MEMB_CAST_SELECT);
                return ok;
            } else if (!Util.validateDESCRIPTION(userBean.getMemSubCast())) {
                addActionError(SystemMessage.MEMB_SUBCAST_INVALID);
                return ok;
            }
            
            else if (userBean.getMemIslife().equals("-1")) {
                addActionError(SystemMessage.MEMB_TYPE_SELECT);
                return ok;
            }
            
            else if (!Util.validateDESCRIPTION(userBean.getJobTitle())) {
                addActionError(SystemMessage.MEMB_JOBTITLE_INVALID);
                return ok;
            } else if (!Util.validateDESCRIPTION(userBean.getJobAddress())) {
                addActionError(SystemMessage.MEMB_JOBADDR_INVALID);
                return ok;
            } else if (!Util.validatePHONENO(userBean.getJobPhone())) {
                addActionError(SystemMessage.MEMB_JOBPHONE_INVALID);
                return ok;
            }
            
            
            else if (!Util.validateDESCRIPTION(userBean.getFatName())) {
                addActionError(SystemMessage.MEMB_FAT_NAME_INVALID);
                return ok;
            } else if (!Util.validateDESCRIPTION(userBean.getFatBirthPlace())) {
                addActionError(SystemMessage.MEMB_FAT_BIRPLAC_INVALID);
                return ok;
            } else if (userBean.getFatCast().equals("-1")) {
                addActionError(SystemMessage.MEMB_FAT_CAST_SELECT);
                return ok;
            }
            
            else if (!Util.validateDESCRIPTION(userBean.getMothName())) {
                addActionError(SystemMessage.MEMB_MOT_NAME_INVALID);
                return ok;
            } else if (!Util.validateDESCRIPTION(userBean.getMothBirthPlace())) {
                addActionError(SystemMessage.MEMB_MOT_BIRPLAC_INVALID);
                return ok;
            } else if (userBean.getMothCast().equals("-1")) {
                addActionError(SystemMessage.MEMB_MOT_CAST_SELECT);
                return ok;
            }
            
            else if (!Util.validateDESCRIPTION(userBean.getGrandFatName())) {
                addActionError(SystemMessage.MEMB_GRANDFAT_NAME_INVALID);
                return ok;
            } else if (!Util.validateDESCRIPTION(userBean.getGrandFatBirthPlace())) {
                addActionError(SystemMessage.MEMB_GRANDFAT_BIRPLAC_INVALID);
                return ok;
            } else if (userBean.getGrandFatCast().equals("-1")) {
                addActionError(SystemMessage.MEMB_GRANDFAT_CAST_SELECT);
                return ok;
            }
            
            else if (!Util.validateDESCRIPTION(userBean.getGrandMothName())) {
                addActionError(SystemMessage.MEMB_GRANDMOT_NAME_INVALID);
                return ok;
            } else if (!Util.validateDESCRIPTION(userBean.getGrandMothBirthPlace())) {
                addActionError(SystemMessage.MEMB_GRANDMOT_BIRPLAC_INVALID);
                return ok;
            } else if (userBean.getGrandMothCast().equals("-1")) {
                addActionError(SystemMessage.MEMB_GRANDMOT_CAST_SELECT);
                return ok;
            }
            
            else if (userBean.getIsMerrid().equals("-1")) {
                addActionError(SystemMessage.MEMB_MERIED_STATUS_SELECT);
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

    private boolean doValidationUpdate(MemberManagementInputBean userBean) throws Exception {
        boolean ok = false;

        try {

//            if (userBean.getUpname() == null || userBean.getUpname().isEmpty()) {
//                addActionError(SystemMessage.USR_NAME_EMPTY);
//                return ok;
//            } else if (userBean.getUpusername() == null || userBean.getUpusername().isEmpty()) {
//                addActionError(SystemMessage.USR_USERNAME_EMPTY);
//                return ok;
//            } else if (!Util.validateSTRING(userBean.getUpusername())) {
//                addActionError(SystemMessage.USR_USERNAME_INVALID);
//                return ok;
//            }else if (userBean.getUpuserPro().equals("-1")) {
//                addActionError(SystemMessage.USR_PROFILE_SELECT);
//                return ok;
//            } 
//            else if (userBean.getUpemail() == null || userBean.getUpemail().isEmpty()) {
//                addActionError(SystemMessage.USR_EMAIL_EMPTY);
//                return ok;
//            } else if (!Util.validateEMAIL(userBean.getUpemail())) {
//                addActionError(SystemMessage.USR_EMAIL_INVALID);
//                return ok;
//            }  else if (!(userBean.getUpmobile().isEmpty() || userBean.getUpmobile() == null) && !Util.validatePHONENO(userBean.getUpmobile())) {
//                addActionError(SystemMessage.USR_PHONE_INVALID);
//                return ok;
//
//            } else if (userBean.getUpstatus().equals("-1")) {
//                addActionError(SystemMessage.USR_STATUS_SELECT);
//                return ok;
//            } else {
//                ok = true;
//            }

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
        String page = PageVarList.EDITVIEW_MEMBER_MANAGEMENT;
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

