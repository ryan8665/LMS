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
public class contact {

    private String address, tel, plus, facebook, linkdin, twitter, telegram, insta;
    private String waddress, wtel, wplus, wfacebook, wlinkdin, wtwitter, wtelegram, winsta;

    public String getWinsta() {
        return winsta;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getWtelegram() {
        return wtelegram;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getWaddress() {
        return waddress;
    }

    public String getWtel() {
        return wtel;
    }

    public String getWplus() {
        return wplus;
    }

    public String getWfacebook() {
        return wfacebook;
    }

    public String getWlinkdin() {
        return wlinkdin;
    }

    public String getWtwitter() {
        return wtwitter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkdin() {
        return linkdin;
    }

    public void setLinkdin(String linkdin) {
        this.linkdin = linkdin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void updateRecord() {
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`web_link` (`l_id`, `l_address`, `l_phone`, `l_plus`, `l_facebook`, `l_linkdin`, `l_twitter`, `l_web` ,`telegram` , `insta`) VALUES "
                + "(NULL, '" + address + "', '" + tel + "', '" + plus + "', '" + facebook + "', '" + linkdin + "', '" + twitter + "', '1' ,'" + telegram + "','" + insta + "');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت نشد", "ثبت نشد"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
        om.closeConnection();
    }

    public void content() throws SQLException {
        Model om = new Model();
        ResultSet rs = om.result("SELECT * FROM `web_link` order by `l_id` DESC limit 1");
        while (rs.next()) {
            address = waddress = rs.getString(2);
            tel = wtel = rs.getString(3);
            plus = wplus = rs.getString(4);
            facebook = wfacebook = rs.getString(5);
            linkdin = wlinkdin = rs.getString(6);
            twitter = wtwitter = rs.getString(7);
            telegram = wtelegram = rs.getString(8);
            insta = winsta = rs.getString(9);

        }
        om.closeConnection();
    }

}
