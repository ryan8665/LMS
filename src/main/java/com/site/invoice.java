/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.site;

import com.beans.AbstractFacade;
import com.dbHelper.Model;
import com.entity.Invoice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
public class invoice extends AbstractFacade<Invoice> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public invoice() {
        super(Invoice.class);
    }

    public List<Invoice> InvoicebyUser(int user) {
        return em.createNamedQuery("Invoice.finduser").setParameter("i", user).getResultList();
    }

    private String error, title, description;
    private String werror, wtitle, wdescription;

    public String getWerror() {
        return werror;
    }

    public String getWtitle() {
        return wtitle;
    }

    public String getWdescription() {
        return wdescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public void updateRecord() {
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`web_invoice` (`i_id`, `i_title`, `i_description`, `i_error`, `i_web_id`) VALUES "
                + "(NULL, '" + title + "', '" + description + "', '" + error + "', '1');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت نشد", "ثبت نشد"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
         om.closeConnection();
    }

    public void content() throws SQLException {
        Model om = new Model();
        ResultSet rs = om.result("SELECT * FROM `web_invoice`  order by `i_id` DESC limit 1");
        while (rs.next()) {
            title = wtitle = rs.getString(2);
            description = wdescription = rs.getString(3);
            error = werror = rs.getString(4);

        }
         om.closeConnection();
    }

}
