/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Log;
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
public class LogFacade extends AbstractFacade<Log> {

    private String pass, epass, reepass;
    private int adminid;
  
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEpass() {
        return epass;
    }

    public void setEpass(String epass) {
        this.epass = epass;
    }

    public String getReepass() {
        return reepass;
    }

    public void setReepass(String reepass) {
        this.reepass = reepass;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogFacade() {
        super(Log.class);
    }

    public List<Log> getLog() {
        return em.createNamedQuery("Log.findAll").getResultList();
    }

    public List<Log> getLogMe() {
        return em.createNamedQuery("Log.finduserinfiid").setParameter("id", adminid).getResultList();
    }

    public void changePass() {
        Model om = new Model();
        String temp = om.select("SELECT `password` FROM `admin` WHERE `user_information_id` = "+adminid);
        if (temp.equals(pass)) {
            boolean res = om.Update("UPDATE `lms`.`admin` SET `password` = '"+reepass+"' WHERE `user_information_id` = "+adminid);
            if (res) {
                pass = ""; epass = ""; reepass = "";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "لطفا فیلدهای ضروری را پر کنید", "خطا"));
            } else {
                pass = ""; epass = ""; reepass = "";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "با موفقیت ویرایش شد", "ویرایش شد"));
            }
        } else {
            pass = ""; epass = ""; reepass = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "گذرواژه اشتباه می‌باشد", "خطا"));
        }
    }

}
