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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author amirk
 */
@WebServlet(name = "svProfile", urlPatterns = {"/svProfile"})
public class svProfile extends HttpServlet {
    private String _numberofPack;
    private String _id;
    private String _o;
    private String _title, _name, _family, _uid, _uiid, _meli, _city, _school, _register_date, _last_login, _gender, _email, _mobile;
    private boolean _error = true;

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
        _id = request.getParameter("i");
        Model om = new Model();
        ResultSet rs;
        _numberofPack = om.select("SELECT count(`user_s_c_t_id`) FROM `user_s_c_t` WHERE `user_sub_course_user_id` = "+_id);
        try {
            Integer.parseInt(_numberofPack);
        } catch (Exception e) {
            _numberofPack = "0";
        }
        rs = om.result("SELECT u.title,i.fname,i.lname,u.user_id,i.user_information_id,i.national_id,u.registration_date,u.last_login_time,g.value,i.email,i.mobile FROM `user` as u inner join user_information as i on u.`user_information_id` = i.`user_information_id` inner join user_gender as g on u.grade_id = g.`user_gender_id` where u.user_id = "+_id);
        try {
            _o = "[";
            while (rs.next()) {
                _title = rs.getString("u.title");
                _name = rs.getString("i.fname");
                _family = rs.getString("i.lname");
                _uid = rs.getString("u.user_id");
                _uiid = rs.getString("i.user_information_id");
                _meli = rs.getString("i.national_id");
                //  _city = rs.getString("amount");
                //  _school = rs.getString("description");
                try {
                    _register_date = rs.getString("u.registration_date");
                } catch (Exception e) {
                    _register_date = "0";
                }

                _last_login = rs.getString("u.last_login_time");
                _gender = rs.getString("g.value");
                _email = rs.getString("i.email");
                _mobile = rs.getString("i.mobile");
                _error = false;

                _o += "{'flag':'0','_name':'" + _name
                         + "','_title':'" + _title
                        + "','_family':'" + _family
                        + "','_uid':'" + _uid
                        + "','_uiid':'" + _uiid
                        + "','_meli':'" + _meli
                        + "','_city':'" + _city
                        + "','_school':'" + _school
                        + "','_register_date':'" + _register_date
                        + "','_last_login':'" + _last_login
                        + "','_gender':'" + _gender
                        + "','_email':'" + _email
                        + "','_countpack':'" + _numberofPack
                        + "','_mobile':'" + _mobile + "'}";

                if (!rs.isLast()) {
                    _o += ",";
                }
            }
            _o += "]";

        } catch (Exception e) {
            _error = true;
        }
 om.closeConnection();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (_error) {
                out.println("[{'flag':'1'}]");
            } else {
                out.println(_o);
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
