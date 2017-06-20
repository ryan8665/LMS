/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.ComprehensiveUser;
import com.entity.Log;
import com.entity.Quiz;
import com.entity.UserSCT;
import com.utility.sendSms;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author amirk
 */
@Stateless
@ManagedBean
@ViewScoped
public class TuserFacade extends AbstractFacade<UserSCT> {

    private int usertogroup;

    private boolean disable = true;
    private String name, family, mobile, desc, pack, email, sex, status, course, usergroupid;
    private String sms, title, msg;
    private int userid;
    private int uiid, stateid, pid, user,cid;
    Date packDat;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUsergroupid() {
        return usergroupid;
    }

    public void setUsergroupid(String usergroupid) {
        this.usergroupid = usergroupid;
    }

    public int getUsertogroup() {
        return usertogroup;
    }

    public void setUsertogroup(int usertogroup) {
        this.usertogroup = usertogroup;
    }

    public int getUiid() {
        return uiid;
    }

    public void setUiid(int uiid) {
        this.uiid = uiid;
    }

    public int getStateid() {
        return stateid;
    }

    public void setStateid(int stateid) {
        this.stateid = stateid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getPackDat() {
        return packDat;
    }

    public void setPackDat(Date packDat) {
        this.packDat = packDat;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TuserFacade() {
        super(UserSCT.class);
    }

    public List<UserSCT> TuserbyTeacher(int u) {

        return em.createNamedQuery("UserSCT.findByTeacher").setParameter("i", u).getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((UserSCT) event.getObject()).getUserSCTId();

        List<UserSCT> lv = null;
        lv = em.createNamedQuery("UserSCT.findByUserSCTId").setParameter("userSCTId", uid).getResultList();
        for (UserSCT u : lv) {
            cid = u.getUserSCTSCTId().getSubCourseId().getSubCourseId();
            course = u.getUserSCTSCTId().getSubCourseId().getName();
            name = u.getUserSubCourseUserId().getUserInformationId().getFname();
            family = u.getUserSubCourseUserId().getUserInformationId().getLname();
            mobile = u.getUserSubCourseUserId().getUserInformationId().getMobile();
            email = u.getUserSubCourseUserId().getUserInformationId().getEmail();
            sex = u.getUserSubCourseUserId().getUserInformationId().getUserGenderId().getValue();
            status = u.getUserSubCourseUserId().getUserInformationId().getStateId().getValue();
            pack = u.getUserSCTSCTId().getTitle();
            desc = u.getUserSCTSCTId().getDescription();
            packDat = u.getRegistrationDate();
            usertogroup = userid = u.getUserSubCourseUserId().getUserId();
            uiid = u.getUserSubCourseUserId().getUserInformationId().getUserInformationId();
            stateid = u.getUserSubCourseUserId().getUserInformationId().getStateId().getUserStateId();
            pid = u.getUserSCTSCTId().getSubCourseId().getSubCourseId();
            user = u.getUserSubCourseUserId().getUserId();
        }
    }

    public List<Log> tUserUserLog() {
        return em.createNamedQuery("Log.findstudent").setParameter("userId", userid).getResultList();

    }

    public void send_sms(String id) {
        disable = true;

        try {
            Model om = new Model();
            String temp = mobile.substring(1, 4);
            Integer.parseInt(temp);
            sendSms._send(mobile, sms);

            om.insert("INSERT INTO `lms`.`sms_log` (`sid`, `title`, `message`, `date`) VALUES "
                    + "(NULL, '" + mobile + "', '" + sms + "', CURRENT_TIMESTAMP);");
            com.log.logParent.iLog("ارسال پیامک", id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));

            sms = "";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال نشد", "ارسال نشد"));

            sms = "";
        }

    }

    public void sendMsg(String id) {
        disable = true;
        Model om = new Model();
        boolean er = om.insert("INSERT INTO `lms`.`message` (`message_id`, `title`, `message`, `sender_id`, `reciver_id`, `is_read`, `date`) "
                + "VALUES (NULL, '" + title + "', '" + msg + "', '" + id + "', '" + userid + "', '1', CURRENT_TIMESTAMP);");

        if (er) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ارسال نشد", "ارسال نشد"));
        } else {
            com.log.logParent.iLog("ارسال پیام", id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));
            msg = "";
            title = "";

        }

    }

    public List<ComprehensiveUser> ComprehensiveByUser() {
        return em.createNamedQuery("ComprehensiveUser.findByUser").setParameter("id", userid).getResultList();
    }

    public void setEnable() {
        disable = true;
    }

    public void addGrouptoUser() {
        Model om = new Model();
        //System.out.println("DELETE FROM `lms`.`user_group` WHERE u_user = " + usertogroup + " and u_teacher_group = " + usergroupid.trim());
        try {
            om.Delete("DELETE FROM `lms`.`user_group` WHERE u_user = " + usertogroup + " and u_teacher_group = " + usergroupid.trim());

        } catch (Exception e) {
        } finally {
            boolean res = om.insert("INSERT INTO `lms`.`user_group` (`u_id`, `u_user`, `u_teacher_group`, `u_date`) VALUES "
                    + "(NULL, '" + usertogroup + "', '" + usergroupid.trim() + "', CURRENT_TIMESTAMP);");
            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ثبت نشد", "ثبت نشد"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
            }
        }

    }
    
     public List<Quiz> QuizbyTeacher(String a) {
         return em.createNamedQuery("Quiz.findByQTeacherUser").setParameter("Id", userid)
                 .setParameter("u", Integer.parseInt(a))
                 .getResultList();
    }
     
     public List<Quiz> QuizbyALLLLL() {
         return em.createNamedQuery("Quiz.findByQTeacherUser")
                 .getResultList();
    }
     
     public List<ComprehensiveUser> ComprehensiveByUserandTeacher(String a) {
        return em.createNamedQuery("ComprehensiveUser.findByUserteacher").setParameter("id", userid)
                .setParameter("t", cid)
                .getResultList();
    }
}
