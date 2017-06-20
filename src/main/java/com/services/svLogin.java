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
@WebServlet(name = "svLogin", urlPatterns = {"/svLogin"})
public class svLogin extends HttpServlet {

    ResultSet _rs;
    private String _username, _password, _userid, _imei;
    private int _loginStatus = 3;
    String _result;

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
        String temp = "";
        try {
            _username = request.getParameter("u");
            _password = request.getParameter("p");
            _imei = request.getParameter("im");
            _rs = om.result("SELECT i.mobile,fname,lname,i.state_id,u.`user_id`,i.user_information_id , i.email ,u.imei FROM `user_information` as i inner join user as u on i.`user_information_id` = u.`user_information_id` WHERE i.`mobile` = '" + _username + "' and i.`password` = '" + _password + "'");

            try {
                _rs.next();
                temp = _rs.getString(1);
            } catch (Exception ex) {
                _loginStatus = 2;
            }
            if (temp.length() == 0) {
                _loginStatus = 1;
            } else {
                try {
                    String personal_information_id, state, firstname, lastname, userid, email, imei = "";
                    _rs.beforeFirst();
                    _loginStatus = 0;
                    _result = "[{'login':'0',";
                    while (_rs.next()) {
                        firstname = _rs.getString(2);
                        lastname = _rs.getString(3);
                        state = _rs.getString(4);
                        _userid = userid = _rs.getString(5);
                        personal_information_id = _rs.getString(6);
                        email = _rs.getString(7);
                        imei = _rs.getString(8);
                        _result += "'firstname':'" + firstname + "','lastname':'" + lastname + "','p_id':'" + personal_information_id + "','state':'" + state + "','emaile':'" + email + "','userid':'" + userid + "'}";
                        if (!_rs.isLast()) {
                            _result += ",";
                        }
                    }
                    _result += "]";

                    if (_imei.equals(imei)) {

                    } else {
                        _loginStatus = 4;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(svLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            _loginStatus = 3;
        }
        om.closeConnection();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (_loginStatus == 0) {
                com.log.logParent.iLog("ورود به سمانه - اندروید", _userid);

                om.Update("UPDATE `lms`.`user` SET `last_login_time` = CURRENT_TIMESTAMP WHERE `user`.`user_id` = " + _userid + ";");
                out.println(_result);
            } else {
                out.println("[{'login':'" + _loginStatus + "'}]");
            }

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
