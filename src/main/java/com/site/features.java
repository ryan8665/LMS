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
public class features {

    private String f1, f2, f3, f4, f5, f6, d1, d2, d3, d4, d5, d6;
    private String wf1, wf2, wf3, wf4, wf5, wf6, wd1, wd2, wd3, wd4, wd5, wd6;

    public String getWf1() {
        return wf1;
    }

    public String getWf2() {
        return wf2;
    }

    public String getWf3() {
        return wf3;
    }

    public String getWf4() {
        return wf4;
    }

    public String getWf5() {
        return wf5;
    }

    public String getWf6() {
        return wf6;
    }

    public String getWd1() {
        return wd1;
    }

    public String getWd2() {
        return wd2;
    }

    public String getWd3() {
        return wd3;
    }

    public String getWd4() {
        return wd4;
    }

    public String getWd5() {
        return wd5;
    }

    public String getWd6() {
        return wd6;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }

    public String getF4() {
        return f4;
    }

    public void setF4(String f4) {
        this.f4 = f4;
    }

    public String getF5() {
        return f5;
    }

    public void setF5(String f5) {
        this.f5 = f5;
    }

    public String getF6() {
        return f6;
    }

    public void setF6(String f6) {
        this.f6 = f6;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getD4() {
        return d4;
    }

    public void setD4(String d4) {
        this.d4 = d4;
    }

    public String getD5() {
        return d5;
    }

    public void setD5(String d5) {
        this.d5 = d5;
    }

    public String getD6() {
        return d6;
    }

    public void setD6(String d6) {
        this.d6 = d6;
    }

    public void updateRecord() {
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`web_features` (`f_id`, `f_title_one`, `f_desc_one`, `f_title_two`, `f_desc_two`, `f_title_three`, `f_desc_three`, `f_title_four`, `f_desc_four`, `f_title_five`, `f_desc_five`, `f_title_six`, `f_desc_six`, `f_web`) VALUES "
                + "(NULL, '" + f1 + "', '" + d1 + "', '" + f2 + "', '" + d2 + "', '" + f3 + "', '" + d3 + "', '" + f4 + "', '" + d4 + "', '" + f5 + "', '" + d5 + "', '" + f6 + "', '" + d6 + "', '1');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت نشد", "ثبت نشد"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
        om.closeConnection();
    }

    public void content() throws SQLException {
        Model om = new Model();
        ResultSet rs = om.result("SELECT * FROM `web_features` order by `f_id` DESC limit 1");
        while (rs.next()) {
            f1 = wf1 = rs.getString(2);
            d1 = wd1 = rs.getString(3);
            f2 = wf2 = rs.getString(4);
            d2 = wd2 = rs.getString(5);
            f3 = wf3 = rs.getString(6);
            d3 = wd3 = rs.getString(7);
            f4 = wf4 = rs.getString(8);
            d4 = wd4 = rs.getString(9);
            f5 = wf5 = rs.getString(10);
            d5 = wd5 = rs.getString(11);
            f6 = wf6 = rs.getString(12);
            d6 = wd6 = rs.getString(13);

        }
        om.closeConnection();
    }

}
