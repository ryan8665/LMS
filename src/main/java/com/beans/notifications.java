/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Message;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amirk
 */
@ManagedBean
@SessionScoped
public class notifications {
    private String unread = "0";

    public String getUnread() {
        return unread;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }
 
    
    public void unreadmessages(String a) {
        Model om = new Model();
        unread = om.select("SELECT count(`message_id`) FROM `message` WHERE `is_read`= '1' AND `reciver_id` = '" + a + "'");

    }
    
}
