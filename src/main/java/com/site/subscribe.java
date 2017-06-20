/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.site;

import com.dbHelper.Model;
import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author amirk
 */
@ManagedBean
@RequestScoped
public class subscribe {

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void subscribe() {
        Model om = new Model();
        om.insert("INSERT INTO `web_subscribe` (`s_id`, `s_email`, `s_date`) VALUES (NULL, '" + email + "', CURRENT_TIMESTAMP);");
        email = "";
        om.closeConnection();
    }

}
