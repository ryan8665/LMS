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
public class slider {

    private final String replace1 = "\n", replace2 = "\\<";

    private String titel, subtitle, description;
    private String wtitel, wsubtitle, wdescription;

    public String getReplace1() {
        return replace1;
    }

    public String getReplace2() {
        return replace2;
    }

    public String getWtitel() {
        return wtitel;
    }

    public String getWsubtitle() {
        return wsubtitle;
    }

    public String getWdescription() {
        return wdescription;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateRecord() {
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`web_slider` (`s_id`, `s_title`, `s_sub_title`, `s_description`, `s_web`) VALUES "
                + "(NULL, '" + titel + "', '" + subtitle + "', '" + description + "', '1');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت نشد", "ثبت نشد"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
        om.closeConnection();
    }

    public void content() throws SQLException {
        Model om = new Model();
        ResultSet rs = om.result("SELECT * FROM `web_slider` order by s_id DESC limit 1");
        while (rs.next()) {
            titel = wtitel = rs.getString("s_title");
            subtitle = wsubtitle = rs.getString("s_sub_title");
            description = wdescription = rs.getString("s_description");
            //wdescription = wdescription.replace("\n", "<br/>");

        }
        om.closeConnection();
    }

}
