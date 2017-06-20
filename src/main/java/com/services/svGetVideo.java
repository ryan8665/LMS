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
 * @author ryan
 */
@WebServlet(name = "svGetVideo", urlPatterns = {"/svGetVideo"})
public class svGetVideo extends HttpServlet {

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
        String _user, _id, _name, _date, _desc, _link, _view, _course;
        String o = "";
        boolean error = true;
        _user = request.getParameter("u");
        Model om = new Model();
          o = "[";
        ResultSet _rs = om.result("SELECT c.`course_id` as course FROM `user_s_c_t` as u "
                + "inner join s_c_T as s on u.`user_s_c_t_id` = s.s_c_t_id "
                + "inner join sub_course as su on s.`sub_course_id` = su.`sub_course_id` "
                + "inner join course as c on su.`course_id` = c.`course_id` "
                + "inner join video as v on c.`course_id` = v.`v_course` "
                + "WHERE `user_sub_course_user_id` = "+_user+" group by c.`course_id`");
        try {
          
            while (_rs.next()) {
                ResultSet _rs2 = om.result("SELECT * FROM `video` WHERE `v_course` = " + _rs.getString("course"));
                while (_rs2.next()) {
                    error = false;
                    _id = _rs2.getString("v_id");
                    _name = _rs2.getString("v_title");
                    _date = _rs2.getString("v_date");
                    _desc = _rs2.getString("v_desc");
                    _link = _rs2.getString("v_link");
                    _view = _rs2.getString("v_view");
                    _course = _rs2.getString("v_course");
                    o += "{'flag':'0','id':'" + _id + "','name':'" + _name + "','date':'" + _date + "','des':'"
                            + _desc + "','link':'" + _link + "','view':'" + _view + "','course':'" + _course + "'}";
                    if (!_rs2.isLast()) {
                        o += ",";
                    }
                   
                }
                   
            }
            o += "]";
            om.closeConnection();

        } catch (SQLException ex) {
            om.closeConnection();
            error = true;
            o = ex.toString();
            Logger.getLogger(svStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (error) {
                out.println("[{'flag':'1'}]" + o);
            } else {
                out.println(o);
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
