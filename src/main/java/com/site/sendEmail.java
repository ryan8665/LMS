/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.site;

import com.utility.Utilities;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.utility.sendEmail.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author amirk
 */
@ManagedBean
@RequestScoped
public class sendEmail {

    private String from, site, name, body;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void send() {
        String temp = name + "\n" + from + "\n" + site + "\n" + body + "\n";
        boolean res = com.utility.sendEmail.sendEmail(name, temp, "amirkhany@gmail.com");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ارسال نشد", "ارسال نشد"));
        }
        name = from = site = body = "";
    }

}
