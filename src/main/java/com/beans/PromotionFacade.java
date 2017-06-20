/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Promotion;
import java.math.BigInteger;
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
 * @author Ryan
 */
@Stateless

@ManagedBean
@ViewScoped
public class PromotionFacade extends AbstractFacade<Promotion> {

    private String quantity, ptitel, pdescription, pval, adminid;
    private String name, family, ex, cr, titel, disc, value, code;
    private boolean disable = true;

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPtitel() {
        return ptitel;
    }

    public void setPtitel(String ptitel) {
        this.ptitel = ptitel;
    }

    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public String getPval() {
        return pval;
    }

    public void setPval(String pval) {
        this.pval = pval;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public PromotionFacade() {
        super(Promotion.class);
    }

    public List<Promotion> getPromotion() {
        return em.createNamedQuery("Promotion.findAll").getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((Promotion) event.getObject()).getPromotionId();
        List<Promotion> lv = null;
        lv = em.createNamedQuery("Promotion.findByPromotionId").setParameter("promotionId", uid).getResultList();
        for (Promotion p : lv) {
            name = p.getRegistrarId().getUserInformationId().getFname();
            family = p.getRegistrarId().getUserInformationId().getLname();
            ex = p.getExpirationDate() + "";
            cr = p.getRegistrationDate() + "";
            titel = p.getTitle();
            disc = p.getDescription();
            value = p.getValue() + "";
            code = p.getCode();

        }
    }

    public void doredeem(String i) {
     
        boolean res = true;
        quantity = "1";
        try {
            int v = Integer.parseInt(pval);
            int q = Integer.parseInt(quantity);

            Model om = new Model();
            long c = (long) (Math.random() * 99999999999999L) + 10000000000000L;
                    res = om.insert("INSERT INTO `lms`.`promotion` (`promotion_id`, `title`, `description`, `registration_date`, `expiration_date`, `registrar_id`, `value`, `code`) VALUES "
                            + "(NULL, '" + ptitel + "', '" + pdescription + "', CURRENT_DATE(), NULL, '" + adminid + "', '" + v + "', '" + c + "');");

            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", ""));
                ptitel = "";
                pdescription = "";
                pval = "";
                
            } else {
                disable = true;
                ptitel = "";
                pdescription = "";
                pval = "";
                com.log.logParent.iLog("‌ایجاد بن تخفیف جدید", i);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", ""));
            }
        } catch (Exception e) {
            ptitel = "";
            pdescription = "";
            pval = "";
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "لطفا فیلدهای ضروری را پر کنید", "خطا"));
        }
    }

}
