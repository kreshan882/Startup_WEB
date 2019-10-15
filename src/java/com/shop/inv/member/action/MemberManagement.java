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
//import com.shop.inv.user.bean.UserBean;
//import com.shop.inv.user.bean.UserManagementInputBean;
//import com.shop.inv.user.service.UserManagementService;
import com.shop.login.bean.SessionUserBean;
import com.shop.login.bean.TaskBean;
import com.shop.util.AccessControlService;
import com.shop.util.Common;
import com.shop.util.LogFileCreator;
import com.shop.util.PasswordValidator;
import com.shop.util.SystemMessage;
import com.shop.util.Util;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.jpos.iso.ISOUtil;

/**
 *
 * @author Kreshan Rajendran
 */
public class MemberManagement extends ActionSupport implements ModelDriven<MemberManagementInputBean>, AccessControlService {

    private MemberManagementService service = new MemberManagementService();
    MemberManagementInputBean inputBean = new MemberManagementInputBean();
    PasswordValidator pwdvalidator = new PasswordValidator();

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
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

//    public String List() {
//        try {
//            List<MemberBean> dataList = null;
//            int rows = inputBean.getRows();
//            int page = inputBean.getPage();
//            int to = (rows * page);
//            int from = to - rows;
//            long records = 0;
//            String orderBy = "";
//
//            if (!inputBean.getSidx().isEmpty()) {
//                orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
//            }
//
//            dataList = service.loadData(inputBean, orderBy, from, rows);
//
//            if (!dataList.isEmpty()) {
//                records = dataList.get(0).getFullCount();
//                inputBean.setRecords(records);
//                inputBean.setGridModel(dataList);
//                int total = (int) Math.ceil((double) records / (double) rows);
//                inputBean.setTotal(total);
//            } else {
//                inputBean.setRecords(0L);
//                inputBean.setTotal(0);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            LogFileCreator.writeErrorToLog(ex);
//            addActionError(SystemMessage.COMMON_ERROR_PROCESS);
//        }
//        return "list";
//    }
//
//    public String Find() {
//
//        try {
//            service.findData(inputBean);
//        } catch (Exception e) {
//            addActionError(SystemMessage.COMMON_ERROR_PROCESS);
//            LogFileCreator.writeErrorToLog(e);
//        }
//
//        return "find";
//    }
//
//    public String Update() {
//
//        try {
//            if (doValidationUpdate(inputBean)) {
//
//                if (service.updateData(inputBean)) {
//
//                    addActionMessage(SystemMessage.USR_UPDATED);
//                    LogFileCreator.writeInfoToLog(SystemMessage.USR_UPDATED);
//
//                } else {
//                    addActionError(SystemMessage.USR_UPDATED_ERROR);
//                }
//
//            }
//        } catch (Exception ex) {
//            addActionError(SystemMessage.USR_UPDATED_ERROR);
//            ex.printStackTrace();
//            LogFileCreator.writeErrorToLog(ex);
//        }
//        return "update";
//    }
//
//    public String Delete() {
//        try {
//            if (getSub().getUsername().equals(inputBean.getUsername())) {
//                inputBean.setMessage(SystemMessage.USR_DELETED_ERROR_SESSUSR);
//                inputBean.setSuccess(false);
//                return "delete";
//            }
//            if (service.deleteData(inputBean)) {
//                LogFileCreator.writeInfoToLog(SystemMessage.USR_DELETED);
//                inputBean.setMessage(SystemMessage.USR_DELETED);
//                inputBean.setSuccess(true);
//            } else {
//                inputBean.setMessage(SystemMessage.USR_DELETED_ERROR);
//                inputBean.setSuccess(false);
//            }
//
//        } catch (Exception ex) {
//            inputBean.setMessage(SystemMessage.USR_DELETED_ERROR);
//            inputBean.setSuccess(false);
//            ex.printStackTrace();
//            LogFileCreator.writeErrorToLog(ex);
//        }
//
//        return "delete";
//    }

    public String Add() {
        try {
            if (doValidation(inputBean)) {
               boolean rek=MemberManagement.imageUpload(inputBean);
                System.out.println("image loaded:"+rek);
                
                if (service.addData(inputBean)) {
                    addActionMessage(SystemMessage.MEMB_ADD);
                    LogFileCreator.writeInfoToLog(SystemMessage.MEMB_ADD);

                } else {
                    addActionError(SystemMessage.MEMB_ADD_FAIL);
                }
            }

        } catch (Exception ex) {
            addActionError(SystemMessage.MEMB_ADD_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "add";
    }
    public static boolean imageUpload(MemberManagementInputBean inputBean) {
        System.out.println("ddddddddddddddddddddddddddd44"+inputBean.getMemId());

        
        try {
        int width = 600;    //width of the image 
        int height = 600;   //height of the image 
            
        File input_file = inputBean.getMemImgFile();
        BufferedImage image = ImageIO.read(input_file);


        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        
        
        
        File output = new File("C:\\Program Files\\glassfish-4.1.1\\glassfish\\domains\\domain1\\docroot\\imagesK\\members\\PRO"+ISOUtil.zeropad(inputBean.getMemId(), 5)+".png");
        ImageIO.write(resized, "png", output);
//        if (doValidationFile(bean.getUpfileFileName())) {}


        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public String UploadFile() {
        System.out.println("ddddddddddddddddddddddddddd"+inputBean.getMemId());

        
        try {
        int width = 600;    //width of the image 
        int height = 600;   //height of the image 
//        BufferedImage image = null; 
//        System.out.println("upload file name ...........0:"+inputBean.getUpfileFileName());
//        File input_file = inputBean.getUpfile();
//        System.out.println("fole"+input_file);
//        
//        
//        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
//            image = ImageIO.read(input_file);  
//            System.out.println("Reading complete."); 
//
//            
//            
//            File output_file = new File("C:\\Program Files\\glassfish-4.1.1\\glassfish\\domains\\domain1\\docroot\\imagesK\\members\\MEM_"+inputBean.getMemId()+".png"); 
//            ImageIO.write(image, "jpg", output_file); 
//            System.out.println("Writing complete.");
            
        File input_file = inputBean.getUpfile();
        BufferedImage image = ImageIO.read(input_file);


        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        
        
        
        File output = new File("C:\\Program Files\\glassfish-4.1.1\\glassfish\\domains\\domain1\\docroot\\imagesK\\members\\MEM_"+inputBean.getMemId()+".png");
        ImageIO.write(resized, "png", output);
//        if (doValidationFile(bean.getUpfileFileName())) {}


        } catch (Exception e) {
            addActionError("builk senf fail...");
            e.printStackTrace();
        }
        return "add";
    }
    
        private boolean doValidationFile(String filenamei) throws Exception {

        boolean ok = false;
        String filename = "" + filenamei;
        String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        String filetypeCheck = "jsp";

        try {

            if (filenamei != null && !extension.equals(filetypeCheck)) {
                addActionError("wrong file trype..");
                return ok;
            } else {
                ok = true;
            }
        } catch (Exception e) {
            throw e;
        }

        return ok;
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
            
            else if (userBean.getIsMerrid().equals("-1")) { //1=merried, 0=single
                addActionError(SystemMessage.MEMB_MERIED_STATUS_SELECT);
                return ok;
            }
            else if (userBean.getIsMerrid().equals("1") && !Util.validateDESCRIPTION(userBean.getWifeAdd()) ) { 
                addActionError("wife address invalid");
                return ok;
            }else if (userBean.getIsMerrid().equals("1") &&(userBean.getMemDob() == null || userBean.getMemDob().isEmpty())) {
                addActionError("wife date of birth empty");
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
        String page = PageVarList.ADD_MEMBER_MANAGEMENT;
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
        } else if ("UploadFile".equals(method)) {
            status = true;
        }else {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(task, page, session);
        }
        return status;
    }

}
