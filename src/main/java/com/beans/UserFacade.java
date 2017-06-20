/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Invoice;
import com.entity.Log;
import com.entity.SCT;
import com.entity.User;
import com.entity.UserState;
import com.utility.sendSms;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ryan
 */
@Stateless
@ManagedBean
@ViewScoped

public class UserFacade extends AbstractFacade<User> {

    private int uid, adminid, uiid, stateid;
    String msg, title;
    private String sms;

    String name, family, mobile, imei, sex, school, schoold, city, lastlogin, email, state, password, ui ;
    private String sname, sfamily, semail, stel, spass ,smsteacher;
    boolean sdisable = true;
    boolean sdisable2 = true;
    boolean sdisable3 = true;
    private String userid;

    public String getSmsteacher() {
        return smsteacher;
    }

    public void setSmsteacher(String smsteacher) {
        this.smsteacher = smsteacher;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public int getStateid() {
        return stateid;
    }

    public void setStateid(int stateid) {
        this.stateid = stateid;
    }

    public int getUiid() {
        return uiid;
    }

    public void setUiid(int uiid) {
        this.uiid = uiid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSfamily() {
        return sfamily;
    }

    public void setSfamily(String sfamily) {
        this.sfamily = sfamily;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getStel() {
        return stel;
    }

    public void setStel(String stel) {
        this.stel = stel;
    }

    public String getSpass() {
        return spass;
    }

    public void setSpass(String spass) {
        this.spass = spass;
    }

    public boolean isSdisable3() {
        return sdisable3;
    }

    public void setSdisable3(boolean sdisable3) {
        this.sdisable3 = sdisable3;
    }

    public boolean isSdisable2() {
        return sdisable2;
    }

    public void setSdisable2(boolean sdisable2) {
        this.sdisable2 = sdisable2;
    }

    public String getUi() {
        return ui;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSdisable() {
        return sdisable;
    }

    public void setSdisable(boolean sdisable) {
        this.sdisable = sdisable;
    }

    public String getSchoold() {
        return schoold;
    }

    public void setSchoold(String schoold) {
        this.schoold = schoold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public UserFacade() {
        super(User.class);
    }

    public List<User> getUser() {
        return em.createNamedQuery("User.findAllStudent").setParameter("i", Integer.parseInt("1")).getResultList();
    }

    public List<User> getTeacher() {
        return em.createNamedQuery("User.findAllStudent").setParameter("i", Integer.parseInt("2")).getResultList();
    }

    public List<Log> getLog() {
        return em.createNamedQuery("Log.findstudent").setParameter("userId", uid).getResultList();

    }
    
    
    
    

    public List<SCT> getCourseByTeacher() {
        return em.createNamedQuery("SCT.findByTeacherID").setParameter("id", uid).getResultList();

    }
    
     public List<SCT> CourseByTeacherID(String id) {
        return em.createNamedQuery("SCT.findByTeacherID").setParameter("id", id).getResultList();

    }

    public void onRowSelect(SelectEvent event) {
        sdisable = false;
        uid = ((User) event.getObject()).getUserId();
        List<User> lv = null;
        lv = em.createNamedQuery("User.findByUserId").setParameter("userId", uid).getResultList();
        for (User u : lv) {
            name = u.getUserInformationId().getFname();
            family = u.getUserInformationId().getLname();
            mobile = u.getUserInformationId().getMobile();
            imei = u.getImei();
            sex = u.getUserInformationId().getUserGenderId().getValue();
            school = u.getSchoolId().getTitle();
            schoold = u.getSchoolId().getDesciption();
            city = u.getGeoCityId().getValue();
            //   lastlogin = u.getLastLoginTime().toString();
            email = u.getUserInformationId().getEmail();
            state = u.getUserInformationId().getStateId().getValue();
            password = u.getUserInformationId().getPassword();
            uiid = u.getUserInformationId().getUserInformationId();
            stateid = u.getUserInformationId().getStateId().getUserStateId();

        }
    }

    public void sendMsg() {

        Model om = new Model();
        boolean er = om.insert("INSERT INTO `lms`.`message` (`message_id`, `title`, `message`, `sender_id`, `reciver_id`, `is_read`, `date`) "
                + "VALUES (NULL, '" + title + "', '" + msg + "', '" + userid + "', '" + uid + "', '1', CURRENT_TIMESTAMP);");

        if (er) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ارسال نشد", "ارسال نشد"));
        } else {
            com.log.logParent.iLog("ارسال پیام", userid);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));
            msg = "";
            title = "";

        }

    }

    public List<Invoice> getInvoice() {
        return em.createNamedQuery("Invoice.finduser").setParameter("i", uid).getResultList();
    }

    public void setEnable() {
        
        sdisable2 = true;
        sdisable = true;
        

        /**
         * Model om = new Model(); String tempuid = om.select("SELECT
         * `user_information_id` FROM `user` WHERE `user_id` = " + uid); String
         * tempui = om.select("SELECT `state_id` FROM `user_information` WHERE
         * `user_information_id` = " + tempuid); if (tempui.endsWith("1")) {
         * om.Update("UPDATE `lms`.`user_information` SET `state_id` = '2' " +
         * "WHERE `user_information`.`user_information_id` = " + tempuid); state
         * = "غیر فعال شد"; String a = "UPDATE `lms`.`user_information` SET
         * `state_id` = '2' " + "WHERE `user_information`.`user_information_id`
         * = " + tempuid; FacesContext.getCurrentInstance().addMessage(null, new
         * FacesMessage(FacesMessage.SEVERITY_INFO, "غیر فعال شد", a)); } else {
         * om.Update("UPDATE `lms`.`user_information` SET `state_id` = '1' " +
         * "WHERE `user_information`.`user_information_id` = " + tempuid); state
         * = " فعال "; String a = "UPDATE `lms`.`user_information` SET
         * `state_id` = '1' " + "WHERE `user_information`.`user_information_id`
         * = " + tempuid; FacesContext.getCurrentInstance().addMessage(null, new
         * FacesMessage(FacesMessage.SEVERITY_INFO, " فعال شد", a)); }
         *
         */
    }

    public void onRowSelectTeacher(SelectEvent event) {
        sdisable2 = false;
        uid = ((User) event.getObject()).getUserId();
        System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo    " + uid);
        List<User> lv = null;
        lv = em.createNamedQuery("User.findByUserId").setParameter("userId", uid).getResultList();
        for (User u : lv) {
            name = u.getUserInformationId().getFname();
            family = u.getUserInformationId().getLname();
            mobile = u.getUserInformationId().getMobile();
            imei = u.getImei();
            sex = u.getUserInformationId().getUserGenderId().getValue();
            school = u.getSchoolId().getTitle();
            schoold = u.getSchoolId().getDesciption();
            city = u.getGeoCityId().getValue();
            //   lastlogin = u.getLastLoginTime().toString();
            email = u.getUserInformationId().getEmail();
            state = u.getUserInformationId().getStateId().getValue();
            password = u.getUserInformationId().getPassword();
            uiid = u.getUserInformationId().getUserInformationId();
            stateid = u.getUserInformationId().getStateId().getUserStateId();

        }
    }

    public void send_sms() {
        sdisable = true;

        try {
            Model om = new Model();
            String temp = mobile.substring(1, 4);
            Integer.parseInt(temp);
            sendSms._send(mobile, sms);

            om.insert("INSERT INTO `lms`.`sms_log` (`sid`, `title`, `message`, `date`) VALUES "
                    + "(NULL, '" + mobile + "', '" + sms + "', CURRENT_TIMESTAMP);");
            com.log.logParent.iLog("ارسال پیامک", userid);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));

            sms = "";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال نشد", "ارسال نشد"));

            sms = "";
        }

    }
    
    public void sendSMS(String i) {
        if (mobile.trim().length() < 11) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "شماره تلفن همراه معتبر نمی‌باشد", "ارسال نشد"));
            mobile = "";
            msg = "";

        } else {
            try {
                Model om = new Model();
                String temp = mobile.substring(1, 4);
                Integer.parseInt(temp);
                sendSms._send(mobile, msg);

                om.insert("INSERT INTO `lms`.`sms_log` (`sid`, `title`, `message`, `date`) VALUES "
                        + "(NULL, '" + mobile + "', '" + msg + "', CURRENT_TIMESTAMP);");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));
                com.log.logParent.iLog("ارسال پیامک", i);

                msg = "";
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ارسال نشد", "ارسال نشد"));

                msg = "";
            }
        }

    }

}
