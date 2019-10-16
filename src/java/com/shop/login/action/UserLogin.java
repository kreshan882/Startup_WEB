/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.login.action;

import com.shop.init.Status;
import com.shop.util.LogFileCreator;
import com.shop.login.bean.HomeValues;
import com.shop.login.bean.ModuleBean;
import com.shop.login.bean.PageBean;
import com.shop.login.bean.SessionUserBean;
import com.shop.login.bean.TaskBean;
import com.shop.login.bean.UserLoginBean;
import com.shop.login.service.LoginService;
import com.shop.init.InitConfigValue;
import com.shop.util.SessionVarlist;
import com.shop.util.SystemMessage;
import com.shop.util.Util;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author kreshan
 */
public class UserLogin extends ActionSupport implements Action, ModelDriven<UserLoginBean> {

    UserLoginBean inputBean = new UserLoginBean();
    LoginService service = new LoginService();
    HomeValues homeValues = new HomeValues();
    SessionUserBean sub = new SessionUserBean();

    HttpServletRequest request = ServletActionContext.getRequest();
    List<String> profilePageidList = new ArrayList<String>();
//public static int k=0;
    
    public String execute() {
        return SUCCESS;
    }
 
    public String loginCheck() {

        try {
        	System.out.println("pass:"+Util.generateHash(inputBean.getPassword()));
           
                if (service.getDbUserDetails(inputBean)) {
                System.out.println(">>>"+inputBean.getStatus()+":"+inputBean.getDbPassword());
                if (inputBean.getStatus() == Status.ACTIVE) {
                	System.out.println("pass:"+Util.generateHash(inputBean.getPassword()));
                        if (Util.generateHash(inputBean.getPassword()).equals(inputBean.getDbPassword())) {

                        	System.out.println("login sucessssss");
                                sub.setUsername(inputBean.getUserName());
                                sub.setUserProfileId(inputBean.getProfileId());
                                sub.setLogFilePath(InitConfigValue.LOGPATH);
                                sub.setName(inputBean.getName());
                                sub.setStatus(inputBean.getStatus());
                                System.out.println("log0");
                                HttpSession sessionPrevious = ServletActionContext.getRequest().getSession(false);
                                if (sessionPrevious != null) {
                                    sessionPrevious.invalidate();
                                }
                                System.out.println("log1");
                                HttpSession session = ServletActionContext.getRequest().getSession(true);
                                sub.setCurrentSessionId(session.getId());
                                session.setAttribute("SessionObject", sub);
                                System.out.println("log2");
                                //set user and sessionid to hashmap              
                                ServletContext sc = ServletActionContext.getServletContext();
                                HashMap<String, String> userMap = (HashMap<String, String>) sc.getAttribute(SessionVarlist.USERMAP);
                                if (userMap == null) {
                                    userMap = new HashMap<String, String>();
                                }
                                userMap.put(sub.getUsername(), session.getId());
                                sc.setAttribute(SessionVarlist.USERMAP, userMap);

                                System.out.println("log3");
                                LogFileCreator.writeInfoToLog(SystemMessage.LOGIN_MSG + " User:" + inputBean.getUserName());

                                profilePageidList = service.getUserprofilePageidList(inputBean.getProfileId());
                                session.setAttribute("profilePageidList", profilePageidList);

                                HashMap<String, List<TaskBean>> pageTaskList = service.getAllPageTask(inputBean.getProfileId());
                                session.setAttribute("pageTaskList", pageTaskList);
                                System.out.println("log4");
                                Map<ModuleBean, List<PageBean>> modulePageList = service.getModulePageByUser(inputBean.getProfileId());
                                session.setAttribute("modulePageList", modulePageList);
                                System.out.println("log5");
                                return "success";

                        } else {
                            addActionError(SystemMessage.LOGIN_INVALID_PW); //merchant paeeword wrong
                            return "login";
                        }
                     
                } else {
                    addActionError("User inactive");
                    return "login";
                }

            } else {
                addActionError(SystemMessage.LOGIN_INVALID);//unvalid user only
                return "login";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            addActionError("Error contact administrator");
            LogFileCreator.writeErrorToLog(ex);
        } finally {
            inputBean.setUserName("");
            inputBean.setPassword("");
        }
        return "login";

    }

    public String homeFunction() throws Exception {
        return SUCCESS;

    }

    @Override
    public UserLoginBean getModel() {
        return inputBean;
    }

    public String Logout() {
        try {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            System.out.println("user login.........................");
            if (session != null) {

                if (inputBean.getMessage() != null && !inputBean.getMessage().isEmpty()) {
                    String message = inputBean.getMessage();
                    if (message.equals("error1")) {
                        addActionError("Access denied. Please login again.");
                    } else if (message.equals("error2")) {
                        addActionError("You have been logged another mechine.");
                    } else if (message.equals("error3")) {
                        addActionMessage("Your password changed successfully. Please login with the new password.");
                    } else if (message.equals("error4")) {
                        addActionError("Session timeout.");
                    }else if (message.equals("error5")) {
                        addActionError("Operation fail.");
                    }
                }

                SessionUserBean su = (SessionUserBean) session.getAttribute("SessionObject");
                if (su != null) {
                    System.out.println(SystemMessage.LOGOUT_MSG);
                } else {
                    addActionError("Session timeout.");
                }

                session.removeAttribute("SessionObject");
                session.removeAttribute("pageTaskList");
                session.removeAttribute("SessionHomeValues");
                session.removeAttribute("profilePageidList");
                session.removeAttribute("modulePageList");
                session.invalidate();
                session = null;
            } else {
                addActionError("Session timeout");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return LOGIN;
    }
    

}
