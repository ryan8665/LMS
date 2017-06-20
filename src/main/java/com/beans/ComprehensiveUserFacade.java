/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entity.ComprehensiveUser;
import com.entity.Quiz;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amirk
 */
@Stateless
@ManagedBean
@ViewScoped
public class ComprehensiveUserFacade extends AbstractFacade<ComprehensiveUser> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComprehensiveUserFacade() {
        super(ComprehensiveUser.class);
    }
    public List<ComprehensiveUser> getComprehensive() {
        return em.createNamedQuery("Comprehensive.findAll").getResultList();
    }
    public List<ComprehensiveUser> ComprehensiveByExam(int id) {
        return em.createNamedQuery("ComprehensiveUser.findByExam").setParameter("id", id).getResultList();
    }
    
      public List<Quiz> QuizByUser() {
        return em.createNamedQuery("Quiz.findAll").getResultList();
    }

    public List<Quiz> getQuiz() {
         return em.createNamedQuery("Quiz.findByQTeacher").setParameter("Id", 31).getResultList();
    }
    
    public List<Quiz> QuizbyTeacher(String a) {
         return em.createNamedQuery("Quiz.findByQTeacherUser").setParameter("Id", 30)
                 .setParameter("u", Integer.parseInt(a))
                 .getResultList();
    }
      
      
    
}
