/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Chapter;
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
public class ChapterFacade extends AbstractFacade<Chapter> {

    private boolean disable = true;
    private String value, description, groupid;
    private String svalue, sdescription, sgroupid;

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

    public ChapterFacade() {
        super(Chapter.class);
    }

    public List<Chapter> getChapter() {
        return em.createNamedQuery("Chapter.findAll").getResultList();
    }

    public List<Chapter> getChapterSort() {
        return em.createNamedQuery("Chapter.findAllSort").getResultList();
    }

    public List<Chapter> getChapterSortFindbySubCource(int sid) {
        return em.createNamedQuery("Chapter.findAllfindbycource").setParameter("s", sid).getResultList();
    }
     public List<Chapter> haha(String sid){
           return em.createNamedQuery("Chapter.findAllfindbycource").setParameter("s", Integer.parseInt(sid)).getResultList();
     }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((Chapter) event.getObject()).getChapterId();
        List<Chapter> lv = null;
        lv = em.createNamedQuery("Chapter.findByChapterId").setParameter("chapterId", uid).getResultList();
        for (Chapter c : lv) {
            sdescription = c.getDescription();
            svalue = c.getTitle();
            sgroupid = c.getSubCourseId().getName();
        }
    }

    public void savechapter(String i) {

        if (value.trim().length() <= 0 || description.trim().length() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "لطفا فیلدهای ضروری را پر کنید", "لطفا فیلدهای ضروری را پر کنید"));
            description = "";
            value = "";
        } else {
            Model om = new Model();
            boolean res = om.insert("INSERT INTO `lms`.`chapter` (`chapter_id`, `title`, `description`, `sub_course_id`) VALUES "
                    + "(NULL, '" + value + "', '" + description + "', '" + groupid.trim() + "');");
            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطای رخ داده است لطفا بعدا دوباره تلاش کنید", "خطای رخ داده است لطفا بعدا دوباره تلاش کنید"));
                description = "";
                value = "";
            } else {
                com.log.logParent.iLog("‌ایجاد فصل جدید", i);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
                description = "";
                value = "";
            }

        }
    }
}
