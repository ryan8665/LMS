/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.CourseType;
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
public class CourseTypeFacade extends AbstractFacade<CourseType> {

    private boolean disable = true;
    private String value, description;
    private String svalue, sdescription;

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

    public CourseTypeFacade() {
        super(CourseType.class);
    }

    public List<CourseType> getCourseType() {
        return em.createNamedQuery("CourseType.findAll").getResultList();
    }
     public List<CourseType> getCourseTypeSort() {
        return em.createNamedQuery("CourseType.findAllSort").getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((CourseType) event.getObject()).getCourseTypeId();
        List<CourseType> lv = null;
        lv = em.createNamedQuery("CourseType.findByCourseTypeId").setParameter("courseTypeId", uid).getResultList();
        for (CourseType c : lv) {
            sdescription = c.getDescription();
            svalue = c.getValue();
        }
    }

    public void savegroup(String i) {
        disable = true;
        if (value.trim().length() <= 0 || description.trim().length() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "لطفا فیلدهای ضروری را پر کنید", "لطفا فیلدهای ضروری را پر کنید"));
            description = "";
            value = "";
        } else {
            Model om = new Model();
            boolean res = om.insert("INSERT INTO `lms`.`course_type` (`course_type_id`, `value`, `description`) VALUES"
                    + " (NULL, '" + value + "', '" + description + "');");
            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطای رخ داده است لطفا بعدا دوباره تلاش کنید", "خطای رخ داده است لطفا بعدا دوباره تلاش کنید"));
                description = "";
                value = "";
            } else {
                com.log.logParent.iLog("ایجاد گروه درسی‌ جدید", i);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
                description = "";
                value = "";
            }

        }

    }

}
