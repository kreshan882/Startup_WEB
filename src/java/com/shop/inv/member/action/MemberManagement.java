/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inv.member.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.init.InitConfigValue;
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
            inputBean.setMemIdDes(service.getlastMemId());
            inputBean.getMemIslifeList().putAll(inputBean.getMemIslifeList());
            inputBean.getIsMerridList().putAll(inputBean.getIsMerridList());
            inputBean.getNumberList().putAll(inputBean.getNumberList());        
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }



    public String Add() {
        try {
                System.out.println("getMemIdDes:"+inputBean.getMemIdDes());
                System.out.println("getMemImgFileFileName:"+inputBean.getMemImgFileFileName());
                System.out.println("getFamImgFileFileName:"+inputBean.getFamImgFileFileName());
                
            if (doValidation(inputBean)) {
                System.out.println("validation sucess");
               boolean me_img=MemberManagement.imageUpload(inputBean.getMemImgFile() ,"PRO_"+inputBean.getMemIdDes()+".png");
               boolean fam_img=MemberManagement.imageUpload(inputBean.getFamImgFile(),"FAM_"+inputBean.getMemIdDes()+".png");
                System.out.println("me_img:"+me_img);
                System.out.println("fam_img:"+fam_img);
                
                if (service.addData(inputBean,me_img,fam_img)) {
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
    public static boolean imageUpload(File Img_file,String outFile) {
        
        try {
        int width = 600, height = 600;   
            
        File memImg_file = Img_file;
        BufferedImage mem_image = ImageIO.read(memImg_file);
        Image tmp = mem_image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        File output = new File(InitConfigValue.IMAGE_UPLOAD_PATH+outFile);
        ImageIO.write(resized, "png", output);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
            } 
//            else if (!Util.validateNIC(userBean.getMemNic())) {
//                addActionError(SystemMessage.MEMB_NIC_INVALID);
//                return ok;
//            }
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
            else if (!userBean.getEmail().isEmpty() && !Util.validateEMAIL(userBean.getEmail())) {
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
            } else if (!Util.validateNAME(userBean.getFatCast())) {
                addActionError(SystemMessage.MEMB_FAT_CAST_INVALID);
                return ok;
            }
            
            else if (!Util.validateNAME(userBean.getMothName())) {
                addActionError(SystemMessage.MEMB_MOT_NAME_INVALID);
                return ok;
            } else if (!Util.validateNAME(userBean.getMothBirthPlace())) {
                addActionError(SystemMessage.MEMB_MOT_BIRPLAC_INVALID);
                return ok;
            } else if (!Util.validateNAME(userBean.getMothCast())) {
                addActionError(SystemMessage.MEMB_MOT_CAST_INVALID);
                return ok;
            }
            
            else if (!Util.validateNAME(userBean.getGrandFatName())) {
                addActionError(SystemMessage.MEMB_GRANDFAT_NAME_INVALID);
                return ok;
            } else if (!Util.validateNAME(userBean.getGrandFatBirthPlace())) {
                addActionError(SystemMessage.MEMB_GRANDFAT_BIRPLAC_INVALID);
                return ok;
            } else if (!userBean.getGrandFatCast().isEmpty() && !Util.validateNAME(userBean.getGrandFatCast())) {
                addActionError(SystemMessage.MEMB_GRANDFAT_CAST_INVALID);
                return ok;
            }
            
            else if (!Util.validateNAME(userBean.getGrandMothName())) {
                addActionError(SystemMessage.MEMB_GRANDMOT_NAME_INVALID);
                return ok;
            } else if (!Util.validateNAME(userBean.getGrandMothBirthPlace())) {
                addActionError(SystemMessage.MEMB_GRANDMOT_BIRPLAC_INVALID);
                return ok;
            } else if (!userBean.getGrandMothCast().isEmpty() && !Util.validateNAME(userBean.getGrandMothCast())) {
                addActionError(SystemMessage.MEMB_GRANDMOT_CAST_Invalid);
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
            
            else if (!Util.validateImageFileName(inputBean.getMemImgFileFileName())) {
                addActionError(SystemMessage.MEMB_IMAGE_PROFILE_INVALID);
                return ok;
            }
            else if (!Util.validateImageFileName(inputBean.getFamImgFileFileName())) {
                addActionError(SystemMessage.MEMB_IMAGE_FAMILY_INVALID);
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
