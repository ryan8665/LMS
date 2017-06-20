/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dbHelper.Model;
import com.utility.sendSms;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ryan
 */
@WebServlet(name = "svRegister", urlPatterns = {"/svRegister"})
public class svPreRegister extends HttpServlet {

    private int _codeStatus = 1;
    String _i = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String temp;
        String name, family, sex, id, mobile, email, imei = "0000";
        Model om = new Model();
        name = request.getParameter("n");
        byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
        name = new String(bytes, StandardCharsets.UTF_8);
        family = request.getParameter("f");
        byte[] bytes2 = family.getBytes(StandardCharsets.ISO_8859_1);
        family = new String(bytes2, StandardCharsets.UTF_8);
        sex = request.getParameter("s");
        id = request.getParameter("i");
        mobile = request.getParameter("m");
        email = request.getParameter("e");
        imei = request.getParameter("im");

        temp = om.select("SELECT `mobile` FROM `user_information` WHERE `mobile` = " + mobile);
        if (!temp.equals(mobile)) {
            boolean res = true;
            int c = (int) (Math.random() * 9999999) + 1000000;
            res = om.insert("INSERT INTO `lms`.`user_information` (`user_information_id`, `fname`, `lname`, `user_gender_id`, `national_id`, `password`, `mobile`, `email`, `state_id`) VALUES "
                    + "(NULL, '" + name + "', '" + family + "', '" + sex + "', '" + id + "', '" + c + "', '" + mobile + "', '" + email + "', '3');");
            if (res) {
                _codeStatus = 1;
            } else {
                _i = om.select("SELECT `user_information_id` FROM `user_information` WHERE `mobile` = " + mobile);
                _codeStatus = 0;

                if (_i.length() > 0) {
                    boolean res2 = om.insert("INSERT INTO `lms`.`user` (`user_id`, `title`, `imei`, `geo_city_id`, `school_id`, `major_id`, `password`, `registration_date`, `last_login_time`, `user_type_id`, `user_information_id`, `grade_id`) VALUES "
                            + "(NULL, NULL, '" + imei + "', '0', '1', '1', '" + c + "', CURRENT_TIMESTAMP, NULL, '1', '" + _i + "', NULL);");
                    if (res2) {
                        _codeStatus = 1;
                    }
                    sendSms._send(mobile, c + " گذر واژه: ");
                }
            }
        } else {
            _codeStatus = 2;
        }
        om.closeConnection();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("[{'flag':'" + _codeStatus + "'}]");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
