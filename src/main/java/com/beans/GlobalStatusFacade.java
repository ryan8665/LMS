/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.GlobalStatus;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryan
 */
@Stateless
@Named
public class GlobalStatusFacade extends AbstractFacade<GlobalStatus> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GlobalStatusFacade() {
        super(GlobalStatus.class);
    }

    public void setEnable(int id,String i) {
        Model om = new Model();
        String res = om.select("SELECT `global_status_id` FROM `s_c_t` WHERE `s_c_t_id` = " + id);
        if (res.equals("1")) {
            GlobalStatus attached = em.find(GlobalStatus.class, 2);
            em.createNamedQuery("SCT.findUpdateStatuse", SCTFacade.class)
                    .setParameter("a", id)
                    .setParameter("b", attached)
                    .executeUpdate();
            com.log.logParent.iLog("‌غیر فعال کردن پکیج", i);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "غیر فعال شد", ""));
        } else {
            GlobalStatus attached = em.find(GlobalStatus.class, 1);
            em.createNamedQuery("SCT.findUpdateStatuse", SCTFacade.class)
                    .setParameter("a", id)
                    .setParameter("b", attached)
                    .executeUpdate();
             com.log.logParent.iLog("‌فعال کردن پکیج", i);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " فعال شد", ""));
        }
    }

    public void setEnable_u_P(int id) {
        Model om = new Model();
        String res = om.select("SELECT `global_status_id` FROM `user_s_c_t` WHERE `user_s_c_t_id` = " + id);
        if (res.equals("1")) {
            GlobalStatus attached = em.find(GlobalStatus.class, 2);
            em.createNamedQuery("UserSCT.findUpdateStatuse", UserSCTFacade.class)
                    .setParameter("a", id)
                    .setParameter("b", attached)
                    .executeUpdate();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "غیر فعال شد", ""));
        } else {
            GlobalStatus attached = em.find(GlobalStatus.class, 1);
            em.createNamedQuery("UserSCT.findUpdateStatuse", UserSCTFacade.class)
                    .setParameter("a", id)
                    .setParameter("b", attached)
                    .executeUpdate();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " فعال شد", ""));
        }
    }

}
