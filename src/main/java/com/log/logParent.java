/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.log;

import com.dbHelper.Model;

/**
 *
 * @author amirk
 */
public class logParent {

    public static void iLog(String log, String actString) {
        Model om = new Model();
        om.insert("INSERT INTO `lms`.`log` (`log_id`, `imei`, `user_id`, `date`) VALUES (NULL, '" + log + "', '" + actString + "', CURRENT_TIMESTAMP);");

    }

}
