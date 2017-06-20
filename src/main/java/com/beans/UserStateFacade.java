/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entity.News;
import com.entity.UserState;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ryan
 */

@Stateless
@Named
public class UserStateFacade extends AbstractFacade<UserState> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserStateFacade() {
        super(UserState.class);
    }
    public void deactive(){
        UserState attached = em.find(UserState.class, 2);
        em.createNamedQuery("UserInformation.findupdateimei", UserInformationFacade.class)
                .setParameter("a", 1)
                .setParameter("b", attached)
                .executeUpdate();
        
    }
    
      public void setEnable(int uiid,int stateid,String i) {

        if (stateid == 1) {
            UserState attached = em.find(UserState.class, 2);
            em.createNamedQuery("UserInformation.findupdateimei", UserInformationFacade.class)
                    .setParameter("a", uiid)
                    .setParameter("b", attached)
                    .executeUpdate();
            com.log.logParent.iLog("غیر فعال کردن کاربر", i);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "غیر فعال شد", ""));
        } else {
            UserState attached = em.find(UserState.class, 1);
            em.createNamedQuery("UserInformation.findupdateimei", UserInformationFacade.class)
                    .setParameter("a", uiid)
                    .setParameter("b", attached)
                    .executeUpdate();
            com.log.logParent.iLog("فعال کردن کاربر", i);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "فعال شد", ""));
        }

       
    }
      public void deleteNews(int id) {

        Query query = em.createNativeQuery("DELETE FROM `lms`.`news` WHERE `news`.`news_id` = "+id);
        query.executeUpdate();
    }
    
}
