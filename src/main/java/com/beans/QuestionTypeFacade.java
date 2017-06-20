/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.QuestionType;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amirk
 */
@Stateless
@ManagedBean
@RequestScoped
public class QuestionTypeFacade extends AbstractFacade<QuestionType> {

    String name, desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionTypeFacade() {
        super(QuestionType.class);
    }

    public List<QuestionType> getType() {
        return em.createNamedQuery("QuestionType.findAll").getResultList();

    }

    public void doSub(String userid) {
        Model om = new Model();
       boolean res =  om.insert("INSERT INTO `lms`.`question_type` (`q_id`, `q_name`, `q_description`) VALUES (NULL, '"+name+"', '"+desc+"');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطای رخ داده است لطفا بعدا دوباره تلاش کنید", "خطای رخ داده است لطفا بعدا دوباره تلاش کنید"));
        } else {
            com.log.logParent.iLog("ثبت نوع سوال "+name, userid);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
            name = "";
            desc = "";
        }
    }

}
