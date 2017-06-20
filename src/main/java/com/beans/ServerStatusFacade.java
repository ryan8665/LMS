/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.ServerStatus;
import com.entity.Status;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryan
 */
@Stateless

@ManagedBean
@ViewScoped
public class ServerStatusFacade extends AbstractFacade<ServerStatus> {

    private String sstatus, disc ,adminid;

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getSstatus() {
        return sstatus;
    }

    public void setSstatus(String sstatus) {
        this.sstatus = sstatus;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServerStatusFacade() {
        super(ServerStatus.class);
    }

    public List<ServerStatus> getServerStatus() {
        return em.createNamedQuery("ServerStatus.findAll").getResultList();
    }

    public List<Status> getStatus() {
        return em.createNamedQuery("Status.findAll").getResultList();
    }

    public void newStatus(String i) {

        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`server_status` (`server_status_id`, `description`, `admin_id`, `status_id`, `date`) VALUES "
                + "(NULL, '"+disc+"', '"+adminid+"', '" + sstatus.charAt(0) + "', CURRENT_TIMESTAMP);");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INSERT INTO `lms`.`server_status` (`server_status_id`, `description`, `admin_id`, `status_id`, `date`) VALUES "
                + "(NULL, '"+disc+"', '"+adminid+"', '" + sstatus.charAt(0) + "', CURRENT_TIMESTAMP);", sstatus));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", sstatus));
             com.log.logParent.iLog("‌تغییر وضعیت سرور", i);
            sstatus = "";
            disc = "";
        }
    }

}
