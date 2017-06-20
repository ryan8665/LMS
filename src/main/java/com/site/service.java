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
public class service {

    private String service1, service2, service3, desc1, desc2, desc3;

    public String getService1() {
        return service1;
    }

    public void setService1(String service1) {
        this.service1 = service1;
    }

    public String getService2() {
        return service2;
    }

    public void setService2(String service2) {
        this.service2 = service2;
    }

    public String getService3() {
        return service3;
    }

    public void setService3(String service3) {
        this.service3 = service3;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3;
    }

    public void updateRecord() {
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`web_service` (`s_id`, `s_service_one`, `s_service_one_desc`, `s_service_two`, `s_service_two_desc`, `s_service_three`, `s_service_three_desc`, `s_web`) VALUES "
                + "(NULL, '" + service1 + "', '" + desc1 + "', '" + service2 + "', '" + desc2 + "', '" + service3 + "', '" + desc3 + "', '1');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت نشد", "ثبت نشد"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
        om.closeConnection();
    }

    public void content() throws SQLException {
        Model om = new Model();
        ResultSet rs = om.result("SELECT * FROM `web_service` order by `s_id` DESC limit 1");
        while (rs.next()) {
            service1 = rs.getString(2);
            desc1 = rs.getString(3);
            service2 = rs.getString(4);
            desc2 = rs.getString(5);
            service3 = rs.getString(6);
            desc3 = rs.getString(7);

        }
        om.closeConnection();
    }
    

}
