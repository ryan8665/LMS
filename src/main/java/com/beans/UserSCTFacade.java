/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entity.User;
import com.entity.UserSCT;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ryan
 */
@Stateless
@ManagedBean
@ViewScoped

public class UserSCTFacade extends AbstractFacade<UserSCT> {

    private String desc;
    private boolean disable = true;
    private int userID, uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserSCTFacade() {
        super(UserSCT.class);
    }

    public List<UserSCT> getUserSCT(int u) {
        System.out.println("555555555555555555555555555555555555555555555555555555555555" + "              " + u);
        return em.createNamedQuery("UserSCT.findByid").setParameter("id", u).getResultList();
    }

    public List<UserSCT> UserSCTAll(int u) {
        System.out.println("555555555555555555555555555555555555555555555555555555555555" + "              " + u);
        return em.createNamedQuery("UserSCT.findByid").setParameter("id", u).getResultList();
    }
    
     public List<UserSCT> TuserbyTeacher(int u) {
  
        return em.createNamedQuery("UserSCT.findByTeacher").setParameter("i", u).getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        uid = ((UserSCT) event.getObject()).getUserSCTId();
        userID = uid;
        List<UserSCT> lv = null;
        lv = em.createNamedQuery("UserSCT.findByUserSCTId").setParameter("userSCTId", uid).getResultList();
        for (UserSCT u : lv) {
            desc = u.getDescription();

        }
    }

    public List<UserSCT> userPackage(int id) {
        return em.createNamedQuery("UserSCT.findByUser").setParameter("id", id).getResultList();
    }

}
