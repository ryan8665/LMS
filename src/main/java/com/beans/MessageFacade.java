/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Message;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class MessageFacade extends AbstractFacade<Message> {

    private String id, emsg, etitile, esender, reply, err, unread = "0", userid, esenderid;
    private boolean disable = true;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUnread() {
        return unread;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
    private boolean disable2 = true;

    public boolean isDisable2() {
        return disable2;
    }

    public void setDisable2(boolean disable2) {
        this.disable2 = disable2;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }

    public String getEtitile() {
        return etitile;
    }

    public void setEtitile(String etitile) {
        this.etitile = etitile;
    }

    public String getEsender() {
        return esender;
    }

    public void setEsender(String esender) {
        this.esender = esender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }

    public List<Message> getMessage() {

        return em.createNamedQuery("Message.fid").setParameter("id", Integer.parseInt(id)).getResultList();

    }

    public List<Message> sent() {

        return em.createNamedQuery("Message.fids").setParameter("id", Integer.parseInt(id)).getResultList();

    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        Model om = new Model();

        int nid = ((Message) event.getObject()).getMessageId();
        List<Message> lv = null;
        lv = em.createNamedQuery("Message.findByMessageId").setParameter("messageId", nid).getResultList();
        om.Update("UPDATE `lms`.`message` SET `is_read` = '0' WHERE `message`.`message_id` = " + nid + "; ");
        for (Message m : lv) {
            emsg = m.getMessage();
            etitile = m.getTitle();
            err = m.getReciverId().getUserInformationId().getFname() + " " + m.getReciverId().getUserInformationId().getLname();
            esender = m.getSenderId().getUserInformationId().getFname() + " " + m.getSenderId().getUserInformationId().getLname();
            esenderid = m.getSenderId().getUserId() + "";

        }
    }

    public void onRowSelect2(SelectEvent event) {
        disable2 = false;
        int nid = ((Message) event.getObject()).getMessageId();
        List<Message> lv = null;
        lv = em.createNamedQuery("Message.findByMessageId").setParameter("messageId", nid).getResultList();
        for (Message m : lv) {
            emsg = m.getMessage();
            etitile = m.getTitle();
            err = m.getReciverId().getUserInformationId().getFname() + " " + m.getReciverId().getUserInformationId().getLname();
            esender = m.getSenderId().getUserInformationId().getFname() + " " + m.getSenderId().getUserInformationId().getLname();
            esenderid = m.getSenderId().getUserId() + "";

        }
    }

    public void insertMessageReply() {
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`message` (`message_id`, `title`, `message`, `sender_id`, `reciver_id`, `is_read`, `date`) VALUES "
                + "(NULL, 're " + etitile + "', '" + reply + "', '" + userid + "', '" + esenderid + "', '1', CURRENT_TIMESTAMP);");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", ""));
            emsg = "";

        } else {

            com.log.logParent.iLog("ارسال پیام", userid);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", ""));
            emsg = "";
        }
    }

    public void unreadmessages(String a) {
        Model om = new Model();
        unread = om.select("SELECT count(`message_id`) FROM `message` WHERE `is_read`= '1' AND `reciver_id` = '" + a + "'");

    }

}
