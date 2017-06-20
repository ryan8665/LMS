/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Admin;
import com.entity.TeacherGroup;
import com.utility.sendSms;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class TeacherGroupFacade extends AbstractFacade<TeacherGroup> {

    private int teacherGroupid;
    private String sms;
    private boolean disable = true;
    private String gname, gdesc;
    private String dname, ddet;
    private String msg, title;

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
    
    

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
    
    

    public int getTeacherGroupid() {
        return teacherGroupid;
    }

    public void setTeacherGroupid(int teacherGroupid) {
        this.teacherGroupid = teacherGroupid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDdet() {
        return ddet;
    }

    public void setDdet(String ddet) {
        this.ddet = ddet;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
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

    public TeacherGroupFacade() {
        super(TeacherGroup.class);
    }

    public List<TeacherGroup> getTeacherGroup() {
        return em.createNamedQuery("TeacherGroup.findAll").getResultList();
    }

    public List<TeacherGroup> TeacherGroupByTeacher(int id) {
        return em.createNamedQuery("TeacherGroup.findByTeacher").setParameter("id", id).getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int tid = ((TeacherGroup) event.getObject()).getTId();
        teacherGroupid = tid;
        List<TeacherGroup> lv = null;
        lv = em.createNamedQuery("TeacherGroup.findByTId").setParameter("tId", tid).getResultList();
        for (TeacherGroup t : lv) {
            dname = t.getTName();
            ddet = t.getTDescription();

        }
    }

    public void subGroup(String teacher) {
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`teacher_group` (`t_id`, `t_name`, `t_description`, `t_teacher`, `t_group_user_teacher`) VALUES "
                + "(NULL, '" + gname + "', '" + gdesc + "', '" + teacher + "', NULL);");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", "ثبت نشد"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }

    }
    
    public void sendSMS(String id) throws SQLException{
        disable = true;
        Model om = new Model();
        ResultSet rs = om.result("SELECT i.`mobile` FROM `user_group` as g "
                + "inner join user as u on g.`u_user` = u.`user_id` "
                + "inner join `user_information` as i on u.`user_information_id` = i.`user_information_id` "
                + "where `u_teacher_group` = "+teacherGroupid);
        while (rs.next()) {            
             sendSms._send(rs.getString("mobile"), sms);
        }
         com.log.logParent.iLog("ارسال پیامک گروهی", id);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));
    }
    
    public void sendMSG(String id) throws SQLException {
        disable = true;
        String a = "";
        Model om = new Model();
         ResultSet rs = om.result("SELECT u.`user_id` FROM `user_group` as g "
                 + "inner join user as u on g.`u_user` = u.`user_id` "
                 + "inner join `user_information` as i on u.`user_information_id` = i.`user_information_id` "
                 + "where `u_teacher_group` = "+teacherGroupid);
         boolean er = true;
        while (rs.next()) {            
            er = om.insert("INSERT INTO `lms`.`message` (`message_id`, `title`, `message`, `sender_id`, `reciver_id`, `is_read`, `date`) "
                + "VALUES (NULL, '" + title + "', '" + msg + "', '" + id + "', '" + rs.getString("user_id") + "', '1', CURRENT_TIMESTAMP);");
        }
        

        if (er) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, a, a));
        } else {
            com.log.logParent.iLog("ارسال پیام گروهی", id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));
            msg = "";
            title = "";

        }
    }

}
