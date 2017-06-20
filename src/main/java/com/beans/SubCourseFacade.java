/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.SubCourse;
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
public class SubCourseFacade extends AbstractFacade<SubCourse> {

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

    public SubCourseFacade() {
        super(SubCourse.class);
    }

    public List<SubCourse> getSubCourse() {
        return em.createNamedQuery("SubCourse.findAll").getResultList();
    }
     public List<SubCourse> getSubCourseSort() {
        return em.createNamedQuery("SubCourse.findAllSort").getResultList();
    }
     public List<SubCourse> SubCourseSortByCourseId(String a) {
        return em.createNamedQuery("SubCourse.findByAllSortSubCourseId").setParameter("subCourseId", Integer.parseInt(a)).getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((SubCourse) event.getObject()).getSubCourseId();
        List<SubCourse> lv = null;
        lv = em.createNamedQuery("SubCourse.findBySubCourseId").setParameter("subCourseId", uid).getResultList();
        for (SubCourse c : lv) {
            sdescription = c.getDescription();
            svalue = c.getName();
            sgroupid = c.getCourseId().getName();
        }
    }

    public void savesubcourse(String i) {
        disable = true;
        if (value.trim().length() <= 0 || description.trim().length() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "لطفا فیلدهای ضروری را پر کنید", "لطفا فیلدهای ضروری را پر کنید"));
            description = "";
            value = "";
        } else {
            Model om = new Model();
            boolean res = om.insert("INSERT INTO `lms`.`sub_course` (`sub_course_id`, `description`, `course_id`, `global_status_id`, `inclusive`, `name`) VALUES "
                    + "(NULL, '" + description + "', '" + groupid.trim() + "', '1', '0', '" + value + "');");
            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطای رخ داده است لطفا بعدا دوباره تلاش کنید", "خطای رخ داده است لطفا بعدا دوباره تلاش کنید"));
                description = "";
                value = "";
            } else {
                com.log.logParent.iLog("‌ایجاد زیر درس جدید", i);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
                description = "";
                value = "";
            }

        }

    }
}
