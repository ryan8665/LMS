/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.ComprehensiveUser;

import com.entity.Quiz;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ryan
 */
@Stateless
@ManagedBean
@ViewScoped
public class QuizFacade extends AbstractFacade<Quiz> {
    private String name,desc,subchapter,hardness,number;

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

    public String getSubchapter() {
        return subchapter;
    }

    public void setSubchapter(String subchapter) {
        this.subchapter = subchapter;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuizFacade() {
        super(Quiz.class);
    }
    
     public void doSubmit(String uid,int a,int b) {
         Model om = new Model();
       
        boolean res = om.insert("INSERT INTO `lms`.`quiz` (`q_id`, `q_name`, `q_des`, `q_sct`, `q_hardness`, `q_teacher`, `q_student`, `q_date_in`, `q_date_fill`, `q_mark`, `q_flag`, `q_subChapter`, `q_number`) VALUES "
                + "(NULL, '"+name+"', '"+desc+"', NULL, '"+hardness+"', '"+uid+"', '"+a+"', CURRENT_TIME(), NOW(), NULL, '0', '"+subchapter+"', '"+number+"');");
        
        String n = "INSERT INTO `lms`.`quiz` (`q_id`, `q_name`, `q_des`, `q_sct`, `q_hardness`, `q_teacher`, `q_student`, `q_date_in`, `q_date_fill`, `q_mark`, `q_flag`, `q_subChapter`, `q_number`) VALUES "
                + "(NULL, '"+name+"', '"+desc+"', NULL, '"+hardness+"', '"+uid+"', '"+a+"', CURRENT_TIME(), NOW(), NULL, '0', '"+subchapter+"', '"+number+"');";
        if (res) {
           
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, n, n));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
    }
     
     public List<Quiz> ComprehensiveByUser(String teacher) {
        return em.createNamedQuery("Quiz.findAll").getResultList();
    }

    public List<Quiz> getss() {
        return em.createNamedQuery("Quiz.findAll").getResultList();
   }
    
     public List<ComprehensiveUser> ComprehensiveByExam(int id) {
        return em.createNamedQuery("ComprehensiveUser.findByExam").setParameter("id", id).getResultList();
    }
     
    
}
