/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Answer;
import com.entity.SmsLog;
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
import org.primefaces.event.SelectEvent;

/**
 *
 * @author amirk
 */
@Stateless

@ManagedBean
@ViewScoped
public class SMS extends AbstractFacade<SMS> {

    private boolean disable = true;
    private String number, msg;
    private String stitel,sdesc,smessage,sdate,snumber;

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }
    

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getStitel() {
        return stitel;
    }

    public void setStitel(String stitel) {
        this.stitel = stitel;
    }

    public String getSdesc() {
        return sdesc;
    }

    public void setSdesc(String sdesc) {
        this.sdesc = sdesc;
    }

    public String getSmessage() {
        return smessage;
    }

    public void setSmessage(String smessage) {
        this.smessage = smessage;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
    

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SMS() {
        super(SMS.class);
    }

    public void sendSMS(String userid) {
        if (number.trim().length() < 11) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "شماره تلفن همراه معتبر نمی‌باشد", "ارسال نشد"));
            number = "";

        } else {
            try {
                Model om = new Model();
                String temp = number.substring(1, 4);
                Integer.parseInt(temp);
                sendSms._send(number, msg);

                om.insert("INSERT INTO `lms`.`sms_log` (`sid`, `title`, `message`, `date`) VALUES "
                        + "(NULL, '" + number + "', '" + msg + "', CURRENT_TIMESTAMP);");
                com.log.logParent.iLog("ارسال پیامک", userid);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));
                number = "";
                msg = "";
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال نشد", "ارسال نشد"));
                number = "";
                msg = "";
            }
        }

    }

    public List<SmsLog> getSmsLog() {
        return em.createNamedQuery("SmsLog.findAll").getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int nid = ((SmsLog) event.getObject()).getSid();
        List<SmsLog> lv = null;
        lv = em.createNamedQuery("SmsLog.findBySid").setParameter("sid", nid).getResultList();
        for (SmsLog s : lv) {
           stitel = s.getTitle();
           sdate = s.getDate()+"";
           smessage = s.getMessage();
           
        }
    }

}
