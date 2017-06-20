/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.site;

import com.dbHelper.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author amirk
 */
@ManagedBean
@RequestScoped
public class fiinans {

    public String sumFiinans(int id) {
        Model om = new Model();
        String temp = om.select("SELECT sum(`amount`) FROM `invoice` where `user_id` = " + id);
        try {
            if (Integer.parseInt(temp) == 0 || temp == null) {
                temp = "0";
            }
        } catch (Exception e) {
            temp = "0";
        }
         om.closeConnection();
        return temp;
    }

}
