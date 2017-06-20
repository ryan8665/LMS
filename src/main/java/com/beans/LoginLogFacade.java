/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.LoginLog;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amirk
 */
@Stateless

@ManagedBean
@ViewScoped
public class LoginLogFacade extends AbstractFacade<LoginLog> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginLogFacade() {
        super(LoginLog.class);
    }

    public List<LoginLog> getLoginLog() {
        return em.createNamedQuery("LoginLog.findAll").getResultList();
    }
    Model om = new Model();

    public String countAndroid() {
        return om.select("SELECT count(`lid`) FROM `login_log` WHERE `device` = 2 And DATE(`date`) = CURDATE();");
    }

    public String countIos() {
        return om.select("SELECT count(`lid`) FROM `login_log` WHERE `device` = 3 And DATE(`date`) = CURDATE();");
    }

    public String countWeb() {
        return om.select("SELECT count(`lid`) FROM `login_log` WHERE `device` = 1 And DATE(`date`) = CURDATE();");
    }

    public String countAll() {
        return om.select("SELECT count(`lid`) FROM `login_log` WHERE DATE(`date`) = CURDATE();");
    }

    public String countAllMedia() {
        return om.select("SELECT count(`mid`) FROM `media_center` WHERE DATE(`date`) = CURDATE();");
    }

    public String countAllSMS() {
        return om.select("SELECT count(`sid`) FROM `sms_log` WHERE DATE(`date`) = CURDATE();");
    }

    public String countRegister() {
        return om.select("SELECT count(`user_id`) FROM `user` WHERE DATE(`registration_date`) = CURDATE();");
    }

}
