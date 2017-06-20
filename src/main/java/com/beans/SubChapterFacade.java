/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.SubChapter;
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
public class SubChapterFacade extends AbstractFacade<SubChapter> {

    private boolean disable = true;
    private String value, description, groupid;
    private String svalue, sdescription, sgroupid;
    private String sequential_number;
    private String number_q;

    public String getNumber_q() {
        return number_q;
    }

    public void setNumber_q(String number_q) {
        this.number_q = number_q;
    }
    

    public String getSequential_number() {
        return sequential_number;
    }

    public void setSequential_number(String sequential_number) {
        this.sequential_number = sequential_number;
    }
    

    public String getSvalue() {
        return svalue;
    }

    public void setSvalue(String svalue) {
        this.svalue = svalue;
    }

    public String getSdescription() {
        return sdescription;
    }

    public void setSdescription(String sdescription) {
        this.sdescription = sdescription;
    }

    public String getSgroupid() {
        return sgroupid;
    }

    public void setSgroupid(String sgroupid) {
        this.sgroupid = sgroupid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public SubChapterFacade() {
        super(SubChapter.class);
    }

    public List<SubChapter> getSubChapter() {
        return em.createNamedQuery("SubChapter.findAll").getResultList();
    }

    public List<SubChapter> getSubChapterSort() {
        return em.createNamedQuery("SubChapter.findAllSort").getResultList();
    }
     public List<SubChapter> getChapterSortFindBySubchapter(int sid) {
        return em.createNamedQuery("SubChapter.findAllfindbychapter").setParameter("s", sid).getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((SubChapter) event.getObject()).getSubChapterId();
        List<SubChapter> lv = null;
        lv = em.createNamedQuery("SubChapter.findBySubChapterId").setParameter("subChapterId", uid).getResultList();
        for (SubChapter s : lv) {
            sdescription = s.getDescription();
            svalue = s.getTitle();
            sgroupid = s.getChapterId().getTitle();
        }
    }

    public void savesubchapter(String i) {
        disable = false;
        if (value.trim().length() <= 0 || description.trim().length() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "لطفا فیلدهای ضروری را پر کنید", "لطفا فیلدهای ضروری را پر کنید"));
            description = "";
            value = "";
        } else {
            Model om = new Model();
            boolean res = om.insert("INSERT INTO `lms`.`sub_chapter` (`sub_chapter_id`, `title`, `description`, `chapter_id`, `sequential_number`, `number`) VALUES "
                    + "(NULL, '" + value + "', '" + description + "', '" + groupid.trim() + "', "+sequential_number+", "+number_q+");");
            if (res) {
              //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INSERT INTO `lms`.`sub_chapter` (`sub_chapter_id`, `title`, `description`, `chapter_id`, `sequential_number`) VALUES "
              //          + "(NULL, '" + value + "', '" + disable + "', '" + groupid.trim() + "', NULL);", "ثبت شد"));
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطای رخ داده است لطفا بعدا دوباره تلاش کنید", "خطای رخ داده است لطفا بعدا دوباره تلاش کنید"));
                description = "";
                value = "";
                sequential_number = "";
            } else {
                com.log.logParent.iLog("‌ایجاد زیر فصل جدید", i);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
                description = "";
                value = "";
                 sequential_number = "";
            }

        }
    }
}
