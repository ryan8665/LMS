/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entity.SCT;
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
public class TpackageFacade extends AbstractFacade<SCT> {

    private boolean disable = true;
    private String tit, teacher, desc, price,statuse,cource;
    private Date Dcreate;

    public Date getDcreate() {
        return Dcreate;
    }

    public void setDcreate(Date Dcreate) {
        this.Dcreate = Dcreate;
    }
    

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatuse() {
        return statuse;
    }

    public void setStatuse(String statuse) {
        this.statuse = statuse;
    }

    public String getCource() {
        return cource;
    }

    public void setCource(String cource) {
        this.cource = cource;
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

    public TpackageFacade() {
        super(SCT.class);
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int nid = ((SCT) event.getObject()).getSCTId();
        List<SCT> lv = null;
        lv = em.createNamedQuery("SCT.findBySCTId").setParameter("sCTId", nid).getResultList();
        for (SCT s : lv) {
            tit = s.getTitle();
            price = s.getPrice()+"";
            teacher = s.getTeacherId().getUserInformationId().getFname()+" "+s.getTeacherId().getUserInformationId().getLname();
            cource = s.getSubCourseId().getName();
            desc = s.getDescription();
            Dcreate = s.getCreateDate();
            

        }

    }

    public List<SCT> Tpackage(int tid) {

        return em.createNamedQuery("SCT.findByTeacher").setParameter("id", tid).getResultList();
    }

}
