/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controler.login;
import com.dbHelper.Model;
import com.entity.Admin;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryan
 */
@ManagedBean
@SessionScoped
public class AdminFacade extends AbstractFacade<Admin> {

    private String phone, password, ftel, fpass;
    private boolean isLogin = false;
    private boolean isAdmin = false;
    private boolean isTeacher = false;
    private boolean isStudent = false;
    public String name, family, uid, email, state, tel, admin;
    private String n, f, i, e, s, t, a, ui;
    private String userid = null;
    private String isLoginUser, isAdminUser;
    private int loginAttempt = 0;

    public boolean isIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public boolean isIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getIsLoginUser() {
        return isLoginUser;
    }

    public void setIsLoginUser(String isLoginUser) {
        this.isLoginUser = isLoginUser;
    }

    public String getIsAdminUser() {
        return isAdminUser;
    }

    public void setIsAdminUser(String isAdminUser) {
        this.isAdminUser = isAdminUser;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    private Date l;

    public Date getL() {
        return l;
    }

    public void setL(Date l) {
        this.l = l;
    }

    public String getUi() {
        return ui;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getFpass() {
        return fpass;
    }

    public void setFpass(String fpass) {
        this.fpass = fpass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFtel() {
        return ftel;
    }

    public void setFtel(String ftel) {
        this.ftel = ftel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminFacade() {
        super(Admin.class);
    }

    public List<Admin> getAdmin() {
        return em.createNamedQuery("Admin.findAll").getResultList();
    }

    public boolean doLogin() throws IOException {
        phone = phone.trim();
        Model om = new Model();
        ResultSet rs = null;
        FacesContext context = FacesContext.getCurrentInstance();
        if (phone.trim().length() <= 0 || password.trim().length() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "لطفا فیلدهای ضروری را پر کنید", "لطفا فیلدهای ضروری را پر کنید"));
            return false;

        } else {
            try {
                rs = om.result("SELECT * FROM `admin` as a inner join `user_information` as u on a.user_information_id = u.user_information_id  inner join user as s on s.user_information_id = u.user_information_id where a.password = " + password);

                while (rs.next()) {
                    n = name = rs.getString("u.fname");
                    f = family = rs.getString("u.lname");
                    i = uid = rs.getString("u.user_information_id");
                    e = email = rs.getString("u.email");
                    s = state = rs.getString("u.state_id");
                    t = tel = rs.getString("u.mobile");
                    a = admin = rs.getString("a.admin_id");
                    ui = rs.getString("a.user_information_id");
                    l = rs.getDate("s.last_login_time");
                    userid = rs.getString("s.user_id");
                    if (rs.getString("flag").equals("1")) {
                        isAdmin = true;
                        isAdminUser = "1";

                    } else {
                        isAdmin = false;
                        isAdminUser = "0";
                    }

                }

                if (phone.equals(tel)) {
                    if (!state.equals("1")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "اکانت شما غیر فعال شده است لطفا با پشتیبانی‌ تماس حاصل نمائید", "اکانت شما غیر فال شده است لطفا با پشتیبانی‌ تماس حاصل نمائید"));
                        return false;
                    } else {
                        com.log.logParent.iLog("ورود به سامانه", userid);
                        om.insert("INSERT INTO `lms`.`login_log` (`lid`, `device`, `date`) VALUES (NULL, '1', CURRENT_TIMESTAMP);");
                        om.Update("UPDATE `lms`.`user` SET `last_login_time` = CURRENT_TIMESTAMP WHERE `user`.`user_id` = " + userid + ";");
                        isLogin = true;
                        isAdminUser = "1";

                        FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
                        //    com.utility.sendSms.sendWelcome(phone, name + " " + family);
                        return true;
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "نام کاربری  یا پسورد اشتباه می باشد", "نام کاربری  یا پسورد اشتباه می باشد"));
                    return false;

                }

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطای رخ داده است لطفا بعدن دوباره تلاش کنید", "خطای رخ داده است لطفا بعدن دوباره تلاش کنید"));
                isAdminUser = "0";
                return false;
            }
        }

    }

    public void doGet_password() throws IOException {
        Model om = new Model();
        String temptel = om.select("SELECT `mobile` FROM `user_information` WHERE `mobile` = " + ftel);
        if (temptel.equals(ftel)) {
            ResultSet rs = om.result("SELECT u.fname,u.lname,a.password FROM `admin` as a inner join `user_information` as u on a.user_information_id = u.user_information_id where `mobile` = " + ftel);
            try {
                while (rs.next()) {
                    name = rs.getString("u.fname");
                    family = rs.getString("u.lname");
                    fpass = rs.getString("a.password");
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "گذرواژه به شماره تلفن همراه ارسال شد", ""));
                com.utility.sendSms.sendPass(ftel, name, ftel, fpass);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطای رخ داده است لطفا بدن دوباره تلاش کنید", "خطای رخ داده است لطفا بدن دوباره تلاش کنید"));
            }
        } else {
            ftel = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "شماره تلفن همراه معتبر نمی‌باشد", "شماره تلفن همراه معتبر نمی‌باشد"));
        }
    }

    public List<Admin> getAdmins() {
        return em.createNamedQuery("Admin.findAll").getResultList();
    }

    public int getAccessLevel(String number, String password) {
        Model om = new Model();
        String res = om.select("SELECT `user_type_id` FROM `user_information` as i inner join `user` as u on i.`user_information_id` = u.`user_information_id` where `mobile` = " + phone);
        switch (res) {
            case "1":
                return 1;

            case "2":
                return 2;

            case "3":
                return 3;

            default:

                return 0;
        }

    }
    String aa = "SELECT `state_id` FROM `user_information` as i inner join `user` as u on i.`user_information_id` = u.`user_information_id` where `mobile` = 09125884909";

    public boolean doEnter() throws SQLException, IOException {
        if (loginAttempt < 5) {

            Model om = new Model();
            ResultSet rs;
            try {
                switch (getAccessLevel(phone, password)) {
                    case 1:
                        rs = om.result("SELECT * FROM `user_information` as i inner join `user` as u on i.`user_information_id` = u.`user_information_id` WHERE i.mobile = " + phone + " and i.password = " + password);
                        while (rs.next()) {
                            n = name = rs.getString("i.fname");
                            f = family = rs.getString("i.lname");
                            i = uid = rs.getString("i.user_information_id");
                            e = email = rs.getString("i.email");
                            s = state = rs.getString("i.state_id");
                            t = tel = rs.getString("i.mobile");
                            ui = rs.getString("u.user_information_id");
                            l = rs.getDate("u.last_login_time");
                            userid = rs.getString("u.user_id");
                        }
                        if (phone.equals(tel)) {
                            if (state.equals("2")) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "اکانت شما غیر فعال شده است لطفا با پشتیبانی‌ تماس حاصل نمائید", "اکانت شما غیر فال شده است لطفا با پشتیبانی‌ تماس حاصل نمائید"));
                                return false;
                            } else {
                                com.log.logParent.iLog("ورود به سامانه", userid);
                                om.insert("INSERT INTO `lms`.`login_log` (`lid`, `device`, `date`) VALUES (NULL, '1', CURRENT_TIMESTAMP);");
                                om.Update("UPDATE `lms`.`user` SET `last_login_time` = CURRENT_TIMESTAMP WHERE `user`.`user_id` = " + userid + ";");
                                isLogin = true;
                                isAdmin = false;
                                isTeacher = false;
                                isStudent = true;
                                isAdminUser = "0";

                                FacesContext.getCurrentInstance().getExternalContext().redirect("/");
                                    com.utility.sendSms.sendWelcome(phone, name + " " + family); 
                                return true;
                            }
                        } else {
                            loginAttempt++;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "نام کاربری  یا پسورد اشتباه می باشد", "نام کاربری  یا پسورد اشتباه می باشد"));
                            return false;
                        }
                    case 2:

                        rs = om.result("SELECT * FROM `user_information` as i inner join `user` as u on i.`user_information_id` = u.`user_information_id` WHERE i.mobile = " + phone + " and i.password = " + password);
                        while (rs.next()) {
                            n = name = rs.getString("i.fname");
                            f = family = rs.getString("i.lname");
                            i = uid = rs.getString("i.user_information_id");
                            e = email = rs.getString("i.email");
                            s = state = rs.getString("i.state_id");
                            t = tel = rs.getString("i.mobile");
                            ui = rs.getString("u.user_information_id");
                            l = rs.getDate("u.last_login_time");
                            userid = rs.getString("u.user_id");
                        }
                        if (phone.equals(tel)) {
                            if (!state.equals("1")) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "اکانت شما غیر فعال شده است لطفا با پشتیبانی‌ تماس حاصل نمائید", "اکانت شما غیر فال شده است لطفا با پشتیبانی‌ تماس حاصل نمائید"));
                                return false;
                            } else {
                                com.log.logParent.iLog("ورود به سامانه", userid);
                                om.insert("INSERT INTO `lms`.`login_log` (`lid`, `device`, `date`) VALUES (NULL, '1', CURRENT_TIMESTAMP);");
                                om.Update("UPDATE `lms`.`user` SET `last_login_time` = CURRENT_TIMESTAMP WHERE `user`.`user_id` = " + userid + ";");
                                isLogin = true;
                                isAdmin = false;
                                isTeacher = true;
                                isAdminUser = "0";

                                FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
                                    com.utility.sendSms.sendWelcome(phone, name + " " + family); 
                                return true;
                            }

                        } else {
                            loginAttempt++;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "نام کاربری  یا پسورد اشتباه می باشد", "نام کاربری  یا پسورد اشتباه می باشد"));
                            return false;
                        }

                    case 3:
                        rs = om.result("SELECT * FROM `admin` as a inner join `user_information` as u on a.user_information_id = u.user_information_id  inner join user as s on s.user_information_id = u.user_information_id where a.password = " + password);

                        while (rs.next()) {
                            n = name = rs.getString("u.fname");
                            f = family = rs.getString("u.lname");
                            i = uid = rs.getString("u.user_information_id");
                            e = email = rs.getString("u.email");
                            s = state = rs.getString("u.state_id");
                            t = tel = rs.getString("u.mobile");
                            a = admin = rs.getString("a.admin_id");
                            ui = rs.getString("a.user_information_id");
                            l = rs.getDate("s.last_login_time");
                            userid = rs.getString("s.user_id");
                            if (rs.getString("flag").equals("1")) {
                                isAdmin = true;
                                isAdminUser = "1";

                            } else {
                                isAdmin = false;
                                isAdminUser = "0";
                            }
                        }
                        if (phone.equals(tel)) {
                            if (!state.equals("1")) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "اکانت شما غیر فعال شده است لطفا با پشتیبانی‌ تماس حاصل نمائید", "اکانت شما غیر فال شده است لطفا با پشتیبانی‌ تماس حاصل نمائید"));
                                return false;
                            } else {
                                com.log.logParent.iLog("ورود به سامانه", userid);
                                om.insert("INSERT INTO `lms`.`login_log` (`lid`, `device`, `date`) VALUES (NULL, '1', CURRENT_TIMESTAMP);");
                                om.Update("UPDATE `lms`.`user` SET `last_login_time` = CURRENT_TIMESTAMP WHERE `user`.`user_id` = " + userid + ";");
                                isLogin = true;
                                isTeacher = false;
                                isAdminUser = "1";

                                FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
                                    com.utility.sendSms.sendWelcome(phone, name + " " + family);
                                return true;

                            }
                        } else {
                            loginAttempt++;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "نام کاربری  یا پسورد اشتباه می باشد", "نام کاربری  یا پسورد اشتباه می باشد"));
                            return false;
                        }

                    case 0:
                        loginAttempt++;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "نام کاربری  یا پسورد اشتباه می باشد", "نام کاربری  یا پسورد اشتباه می باشد"));
                        isAdmin = false;
                        isAdminUser = "0";
                        isTeacher = false;
                        return false;
                    default:
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "نام کاربری  یا پسورد اشتباه می باشد", "نام کاربری  یا پسورد اشتباه می باشد"));
                        return false;
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "نام کاربری  یا پسورد اشتباه می باشد", "نام کاربری  یا پسورد اشتباه می باشد"));
                return false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "اکانت شما به صورت موقت غیر فعال شده ، لطفا بعدا تلاش کنید", ""));
            return false;
        }

    }

}
