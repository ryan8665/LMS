/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entity.Invoice;
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
public class InvoiceFacade extends AbstractFacade<Invoice> {

    boolean disable = true;
    private String name, family, school, city, value, discription, amount, datetime, tel, email;

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvoiceFacade() {
        super(Invoice.class);
    }

    public List<Invoice> getInvoice() {
        return em.createNamedQuery("Invoice.findAll").getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        int nid = ((Invoice) event.getObject()).getInvoiceId();
        System.out.println(nid);
        List<Invoice> lv = null;
        lv = em.createNamedQuery("Invoice.findByInvoiceId").setParameter("invoiceId", nid).getResultList();
        disable = false;
        for (Invoice i : lv) {
            name = i.getUserId().getUserInformationId().getFname();
            family = i.getUserId().getUserInformationId().getLname();
            school = i.getUserId().getSchoolId().getTitle();
            city = i.getUserId().getGeoCityId().getValue();
            value = i.getAmount() + "";
            discription = i.getDescription();
            datetime = i.getDtime() + "";
            tel = i.getUserId().getUserInformationId().getMobile();
            email = i.getUserId().getUserInformationId().getEmail();

        }

    }

}
