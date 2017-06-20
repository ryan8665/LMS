/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.site;

import com.dbHelper.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author amirk
 */
@ManagedBean
@RequestScoped
public class main {

    private String name, domain, about, company, shortDesc, tag;
    private String wname, wdomain, wabout, wcompany, wshortDesc, wtag;

    public String getWname() {
        return wname;
    }

    public String getWdomain() {
        return wdomain;
    }

    public String getWabout() {
        return wabout;
    }

    public String getWcompany() {
        return wcompany;
    }

    public String getWshortDesc() {
        return wshortDesc;
    }

    public String getWtag() {
        return wtag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void updateRecord() {
        Model om = new Model();
        boolean res = om.insert("UPDATE `lms`.`web_website` SET `w_name` = '" + name + "', `w_domain` = '" + domain + "', `w_about` = '" + about + "', `w_short` = '" + shortDesc + "', `w_description` = '" + about + "', `w_corp` = '" + company + "', `w_tag` = '" + tag + "' WHERE `web_website`.`w_id` = 1; ");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت نشد", "ثبت نشد"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
        om.closeConnection();
    }

    public void content() throws SQLException {
        Model om = new Model();
        ResultSet rs = om.result("SELECT * FROM `web_website` limit 1");
        while (rs.next()) {
            name = wname = rs.getString("w_name");
            domain = wdomain = rs.getNString("w_domain");
            about = wabout = rs.getString("w_about");
            company = wcompany = rs.getString("w_corp");
            shortDesc = wshortDesc = rs.getString("w_short");
            tag = wtag = rs.getString("w_tag");

        }
        om.closeConnection();


    }

}
