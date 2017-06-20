/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.site;

import java.io.IOException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author amirk
 */
@ManagedBean
@SessionScoped
public class getOrder {

    private int toll, total;
    private String val;
    private String wval;
    protected int num;

    public int getToll() {
        return toll;
    }

    public int getTotal() {
        return total;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public void setWval(String wval) {
        this.wval = wval;
    }

    public String getVal() {
        return val;
    }

    protected boolean chOrder() {

        try {
            num = Integer.parseInt(val);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void order() throws IOException {
        if (chOrder()) {
            toll = (num * 9) / 100;
            total = num + toll;
            if (num <= 500) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/web/invoice.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/web/pre_invoice.xhtml");
            }

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Error/500.xhtml");
        }

    }

    public void cls() throws IOException {
        toll = 0;
        total = 0;
        num = 0;
        val = "0";
        FacesContext.getCurrentInstance().getExternalContext().redirect("/web/invoice.xhtml");
    }

    public void pay(int u) throws IOException {
       
        FacesContext.getCurrentInstance().getExternalContext().redirect("/web/index.jsp?u=" + u + "&p=" + total);
    
    }

}
