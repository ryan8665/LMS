/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dbHelper.Model;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "svStatus", urlPatterns = {"/svStatus"})
public class svStatus extends HttpServlet {
        ResultSet _rs;
        private String _message = "Error";
        private int _status = 2;
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
        response.setContentType("text/html;charset=UTF-8");
        Model om = new Model();
        om.insert("INSERT INTO `lms`.`login_log` (`lid`, `device`, `date`) VALUES (NULL, '2', CURRENT_TIMESTAMP);");
        _rs = om.result("SELECT *  FROM server_status inner join status on server_status.status_id = status.status_id WHERE `server_status_id` = (SELECT max(`server_status_id`) FROM `server_status`)");
        try {
            while (_rs.next()) {
                _status = _rs.getInt("status_id");
                _message = _rs.getString("description");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(svStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
         om.closeConnection();
        try (PrintWriter out = response.getWriter()) {

            out.println("[{'status':'"+_status+"','message':'"+_message+"'}]");
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
