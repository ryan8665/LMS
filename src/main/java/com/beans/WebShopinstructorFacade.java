/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entity.WebShopinstructor;
import com.dbHelper.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amirk
 */

@ManagedBean
@RequestScoped
public class WebShopinstructorFacade extends AbstractFacade<WebShopinstructor> {
     private String title, description;
    private String wtitle, wdescription;

    public String getWtitle() {
        return wtitle;
    }

    public String getWdescription() {
        return wdescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    
     public void setDescription(String description) {
        this.description = description;
    }


    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WebShopinstructorFacade() {
        super(WebShopinstructor.class);
    }
    
    public void updateRecord() {
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`web_shopinstructor` (`s_id`, `s_title`, `s_des`, `web_id`) VALUES (NULL, '"+title+"', '"+description+"', '1');");
        System.out.println("INSERT INTO `lms`.`web_shopinstructor` (`s_id`, `s_title`, `s_des`, `web_id`) VALUES (NULL, '"+title+"', '"+description+"', '1');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت نشد", "ثبت نشد"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
         om.closeConnection();
    }

    public void content() throws SQLException {
        Model om = new Model();
        ResultSet rs = om.result("SELECT * FROM `web_shopinstructor`  order by `s_id` DESC limit 1");
        while (rs.next()) {
            title = wtitle = rs.getString("s_title");
            description = wdescription = rs.getString("s_des");

        }
         om.closeConnection();
    }
    
}
