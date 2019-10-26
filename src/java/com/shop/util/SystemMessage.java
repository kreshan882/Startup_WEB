/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.util;

/**
 *
 * @author tharaka
 */
public class SystemMessage {

/////////////////////////////////////////////////////////////////////////
    //Common messages
    ////////////////////////////////////////////////////////////////////////
    public static final String COMMON_ERROR_PROCESS = "Error occurred while processing";

    /////////////////////////////////////////////////////////////////////////
    //Login managment
    ////////////////////////////////////////////////////////////////////////
    public static final String LOGIN_MSG = "User login successful ";
    public static final String LOGOUT_MSG = "User logout successful ";
    public static final String LOGIN_INVALID = "Invalid user login";
    public static final String LOGIN_INVALID_PW = "Invalid user password";
    public static final String LOGIN_INVALID_BANKUSER = "Invalid bank user login";


    /////////////////////////////////////////////////////////////////////////////////
    //USER MANAGEMENT
    /////////////////////////////////////////////////////////////////////////////////
    public static final String USR_NAME_EMPTY = "Empty name";
    public static final String USR_NAME_INVALID = "Invalid name";
    public static final String USR_USERNAME_EMPTY = "Empty user name";
    public static final String USR_USERNAME_INVALID = "Invalid user name";
    public static final String USR_USERNAME_ALREADY = "User already exits ";
    public static final String USR_PASSWORD_EMPTY = "Empty user password";
    public static final String USR_PASSWORD_POLICY_VIALATION = "Password policy violation";
    public static final String USR_CONPASSWORD_EMPTY = "Empty confirm user password";
    public static final String USR_PASSWORD_NOT_MATCH = "Password not matched";

    public static final String USR_PROFILE_SELECT = "Select user profile ";
    public static final String USR_USERTYPE_SELECT = "Select user type ";

    public static final String USR_EMAIL_EMPTY = "Empty e-mail";
    public static final String USR_EMAIL_INVALID = "Invalid email";
    public static final String USR_PHONE_EMPTY = "Empty phone number ";
    public static final String USR_PHONE_INVALID = "Invalid phone number ";

    public static final String USR_STATUS_SELECT = "Select user status ";

    public static final String USR_ADD = "User registration successful";
    public static final String USR_ADD_FAIL = "User registration fail";

    public static final String USR_UPDATED = "User updated successfully";
    public static final String USR_UPDATED_ERROR = "User update failed";

    public static final String USR_DELETED = "User deleted successfully";
    public static final String USR_DELETED_ERROR = "User delete failed";
    public static final String USR_DELETED_ERROR_SESSUSR = "Current User cannot delete";

    public static final String USR_PW_CHG = "User password changed successfully";
    public static final String USR_PW_UPDATE = "Password update successful";
    public static final String USR_PW_NOT_MAT = "User password not matched ";
    public static final String USR_PW_WORNG = "Old password incorrect";
    public static final String USR_PW_WORNG_OLD = "Old password Empty";
    public static final String USR_PW_POL_NOT_MAT = "User password does not comply with the password policy ";
    public static final String USR_PW_CNT_EQUAL_NWEPW = "Cannot use the same password ";

    public static final String USRPROFILE_NAME_EMPTY = "Empty user profile name";
    public static final String USRPROFILE_NAME_INVALID = "Invalid user profile name";
    public static final String USRPROFILE_NAME_ALREADY = "Profile name exists";

    public static final String USRPROFILE_MODULE_SELECT = "Select  module";
    public static final String USRPROFILE_PAGE_SELECT = "Select  page";
    public static final String USRPROFILE_TASK_SELECT = "Select profile task";
    public static final String USRPROFILE_STATUS_SELECT = "Select status";

    public static final String USRPROFILE_ADD = "User profile registration successful";
    public static final String USRPROFILE_ADD_FAIL = "User profile registration fail";

    public static final String USRPROFILE_UPDATED = "User profile update successful";
    public static final String USRPROFILE_UPDATED_ERROR = "User profile update failed";

    public static final String USRPROFILE_DELETED = "User profile delete successful";
    public static final String USRPROFILE_DELETED_ERROR = "User profile delete failed";
    public static final String USRPROFILE_DELETED_ERROR_ADMIN = "Admin user profile cannot be deleted";
    /////////////////////////////////////////////////////////////////////////
    //Member managment
    ////////////////////////////////////////////////////////////////////////
    
    public static final String MEMB_NAME_EMPTY = "Empty member name";
    public static final String MEMB_NAME_INVALID = "Invalid member name";
    public static final String MEMB_NIC_EMPTY = "Empty member NIC";
    public static final String MEMB_NIC_INVALID = "Invalid member NIC";   
    public static final String MEMB_DOB_EMPTY = "Empty member DOB";

    public static final String MEMB_PHONE_INVALID = "Invalid member Phone";
    public static final String MEMB_MOBILE_INVALID = "Invalid member Mobile";
    public static final String MEMB_EMAIL_INVALID = "Invalid member Email";
    
    public static final String MEMB_QUALI_INVALID = "Invalid Qulification";
    public static final String MEMB_PAADD_INVALID = "Invalid Permenent Addredd";
    public static final String MEMB_TMADD_INVALID = "Invalid Tempoary Address";
    
    public static final String MEMB_BORNPLAVE_INVALID = "Invalid Qulification";
    public static final String MEMB_CAST_SELECT = "Select member cast ";
    public static final String MEMB_SUBCAST_INVALID = "Invalid Qulification";
  
    public static final String MEMB_TYPE_SELECT = "Select membership type";
    
    public static final String MEMB_JOBTITLE_INVALID = "Invalid Occupation";
    public static final String MEMB_JOBADDR_INVALID = "Invalid Office Address";
    public static final String MEMB_JOBPHONE_INVALID = "Invalid Office Phone";
    
    
    public static final String MEMB_FAT_NAME_INVALID = "Invalid Father Name";
    public static final String MEMB_FAT_BIRPLAC_INVALID ="Invalid Father Birth place"; 
    public static final String MEMB_FAT_CAST_INVALID = "Invalid Father cast ";
    
    public static final String MEMB_MOT_NAME_INVALID = "Invalid Mother Name";
    public static final String MEMB_MOT_BIRPLAC_INVALID ="Invalid Mother Birth place"; 
    public static final String MEMB_MOT_CAST_INVALID = "Invalid Mother cast ";
    
    public static final String MEMB_GRANDFAT_NAME_INVALID = "Invalid Grand Father Name";
    public static final String MEMB_GRANDFAT_BIRPLAC_INVALID ="Invalid Grand Father Birth place"; 
    public static final String MEMB_GRANDFAT_CAST_INVALID = "Invalid Grand Father cast ";
    
    public static final String MEMB_GRANDMOT_NAME_INVALID = "Invalid Grand Mother Name";
    public static final String MEMB_GRANDMOT_BIRPLAC_INVALID ="Invalid Grand Mother Birth place"; 
    public static final String MEMB_GRANDMOT_CAST_Invalid = "Invalid Grand Mother cast ";
    
    public static final String MEMB_MERIED_STATUS_SELECT = "Select Merried Status ";
    
    
    public static final String MEMB_IMAGE_PROFILE_INVALID = "Invalid Profile Image(JPG & PNG)";
    public static final String MEMB_IMAGE_FAMILY_INVALID = "Invalid Family Image(JPG & PNG)";
    
    
    public static final String MEMB_ADD = "Member registration successful";
    public static final String MEMB_ADD_FAIL = "Member registration fail";

    public static final String MEMB_DELETED = "Member deleted successfully";
    public static final String MEMB_DELETED_ERROR = "Member delete failed";
    
//
//    /////////////////////////////////////////////////////////////////////////////////
//    //TRANSACTION MANAGEMENT
//    /////////////////////////////////////////////////////////////////////////////////
//    public static final String TRANSACTION_VIEWTRANS_CANCLED = "Transaction Canceled";
//    public static final String TRANSACTION_VIEWTRANS_DOWNLOAD_PDF = "Transaction File Downloaded";
//    public static final String TRANSACTION_CHANNELTYPE_EMPTY = "Empty Channel Type";
//    public static final String TRANSACTION_AUDIT_DOWNLOAD_PDF = "Audit File Downloaded";
    
    public static final String CHILD_NAME_EMPTY = "Empty name";
    public static final String CHILD_NAME_INVALID = "Invalid name";
    
    public static final String CHILD_GENDER_SELECT = "Select Gender";
    public static final String CHILD_MERRIED_SELECT = "Select Merried Status";
    
    
    
    public static final String CHILD_ADD = "Child registration successful";
    public static final String CHILD_ADD_FAIL = "Child registration fail";
    
    public static final String CHILD_UPDATED = "Child updated successfully";
    public static final String CHILD_UPDATED_ERROR = "Child update failed";

    public static final String CHILD_DELETED = "Child deleted successfully";
    public static final String CHILD_DELETED_ERROR = "Child delete failed";
}
