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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ryan
 */
public class svRegister extends HttpServlet {

    private int _codeStatus = 1;
    String imei, c, s, i;

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
        
        Model om = new Model();
        i = request.getParameter("i");
        s = request.getParameter("s");
        c = request.getParameter("c");
        imei = request.getParameter("imei");
        boolean res = true;
        res = om.insert("INSERT INTO `lms`.`user` (`user_id`, `title`, `imei`, `geo_city_id`, `school_id`, `major_id`, `password`, `registration_date`, `last_login_time`, `user_type_id`, `user_information_id`, `grade_id`) VALUES "
                + "(NULL, NULL, '" + imei + "', '" + c + "', '" + s + "', '1', '1234', CURRENT_DATE(), NULL, '1', '" + i + "', '1'); ");
        if (res) {
            _codeStatus = 1;
        } else {
            om.Update("UPDATE `lms`.`user_information` SET `state_id` = '1' WHERE `user_information`.`user_information_id` = " + i + ";");
            _codeStatus = 0;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("[{'flag':'" + _codeStatus + "'}]");
            out.println("INSERT INTO `lms`.`user` (`user_id`, `title`, `imei`, `geo_city_id`, `school_id`, `major_id`, `password`, `registration_date`, `last_login_time`, `user_type_id`, `user_information_id`, `grade_id`) VALUES "
                + "(NULL, NULL, '" + imei + "', '" + c + "', '" + s + "', '1', '1234', CURRENT_DATE(), NULL, '1', '" + i + "', '1'); ");
            

        }finally{
             om.closeConnection();
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
