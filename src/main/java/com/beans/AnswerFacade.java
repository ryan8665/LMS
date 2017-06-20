/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Answer;
import com.entity.Question;
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
@Named
public class AnswerFacade extends AbstractFacade<Answer> {

    private boolean disable = true;
    private String at;
    private String answerTest;
    int aid;

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAnswerTest() {
        return answerTest;
    }

    public void setAnswerTest(String answerTest) {
        this.answerTest = answerTest;
    }

    public boolean isDisable() {
        return disable;
    }
    private int qid;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
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

    public AnswerFacade() {
        super(Answer.class);
    }

    public List<Answer> getAnswer() {

        return em.createNamedQuery("Answer.findAll").getResultList();
    }

    public List<Answer> AnswerByQuestion(int uid) {
        return em.createNamedQuery("Answer.findByquestion").setParameter("id", uid).getResultList();

    }

    public void subAnswer(String a,String i) {
        disable = true;
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`answer` (`answer_id`, `value`, `question_id`) VALUES (NULL, '" + answerTest + "', '" + a + "');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد" , "ثبت نشد"));
            answerTest = "";

        } else {
              com.log.logParent.iLog("‌ثبت گزینه سوال", i);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد" , "ثبت شد"));
            answerTest = "";
        }

    }

    public void changeRightAnswer(String q) {
        
        Answer ans = em.find(Answer.class, aid);
         em.createNamedQuery("Question.findupdate", Question.class)
                .setParameter("a", Integer.parseInt(q))
                .setParameter("b", ans)
                .executeUpdate();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت شد" , "ثبت شد"));
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((Answer) event.getObject()).getAnswerId();
        aid = uid;
        List<Answer> lv = null;
        lv = em.createNamedQuery("Answer.findByAnswerId").setParameter("answerId", uid).getResultList();
        for (Answer a : lv) {
            qid = a.getQuestionId().getQuestionId();

        }
    }

}
