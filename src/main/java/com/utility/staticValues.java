/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utility;

import com.dbHelper.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ryan
 */
@ManagedBean
@RequestScoped
public class staticValues {

    public static String user() {
        Model om = new Model();
        return om.select("SELECT count(`user_id`) FROM `user` where `user_type_id` = 1");
    }

    public static String teacher() {
        Model om = new Model();
        return om.select("SELECT count(`user_id`) FROM `user` where `user_type_id` = 2");
    }

    public static String question() {
        Model om = new Model();
        return om.select("SELECT count(`question_id`) FROM `question`");
    }

   

}
