/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controler;

import com.beans.AdminFacade;
import com.beans.AdminsFacade;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class login {

    private boolean isLogin = false;
    private boolean isAdmin = false;
    private boolean isTeacher = false;
    private boolean isStudent = false;
    
    public String n,f,name,family;

    public boolean isIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
    
    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }
    

    public boolean isIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isIsLogin() {
        return isLogin;

    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String invalidatSession() throws IOException {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();

        return "/login.xhtml";

    }

    public void doit() {

    }

}
