/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entity.Alarm;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
public class AlarmFacade extends AbstractFacade<Alarm> {
    private String description,title,date,sende,mobile;
    private Date doDate;
    private boolean disable = true;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDoDate() {
        return doDate;
    }

    public void setDoDate(Date doDate) {
        this.doDate = doDate;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSende() {
        return sende;
    }

    public void setSende(String sende) {
        this.sende = sende;
    }
    

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlarmFacade() {
        super(Alarm.class);
    }

    public List<Alarm> getAlarm() {
        return em.createNamedQuery("Alarm.findAll").getResultList();
    }
    
    public List<Alarm> alarmByUser(int user){
        return em.createNamedQuery("Alarm.findByUser").setParameter("id", user).getResultList();
    }
    
    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((Alarm) event.getObject()).getAId();
        List<Alarm> lv = null;
        lv = em.createNamedQuery("Alarm.findByAId").setParameter("aId", uid).getResultList();
        for (Alarm a : lv) {
            description =  a.getADescription();
            doDate = a.getADate();
            title = a.getATitle();
            sende = a.getASender().getUserInformationId().getFname()+" "+a.getASender().getUserInformationId().getLname();
            mobile = a.getASender().getUserInformationId().getMobile();
            

        }
    }
    
    
}
