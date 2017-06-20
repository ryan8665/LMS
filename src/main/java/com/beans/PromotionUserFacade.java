/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entity.PromotionUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
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
public class PromotionUserFacade extends AbstractFacade<PromotionUser> {
 boolean disable = true;

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

    public PromotionUserFacade() {
        super(PromotionUser.class);
    }
     public List<PromotionUser> getPromotionUser() {
        return em.createNamedQuery("PromotionUser.findAll").getResultList();
    }
      public void onRowSelect(SelectEvent event) {
        int nid = ((PromotionUser) event.getObject()).getPromotionUserId();
        System.out.println(nid);
        List<PromotionUser> lv = null;
        lv = em.createNamedQuery("Invoice.findByInvoiceId").setParameter("invoiceId", nid).getResultList();
        disable = false;
        for (PromotionUser p : lv) {
            

        }

    }
    
}
