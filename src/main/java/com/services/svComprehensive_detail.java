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
@WebServlet(name = "svComprehensive_detail", urlPatterns = {"/svComprehensive_detail"})
public class svComprehensive_detail extends HttpServlet {

    private boolean _error = true;
    private String _title, _content, _id, _date, _course, _hardness;
    private String _o;
    private String _c_id;

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
        _c_id = request.getParameter("i");
        Model om = new Model();
        ResultSet rs;

        rs = om.result("SELECT c_id , c_title,`c_description`,`c_execute_date`,`c_short_description`, s.`name`,h.`description` FROM "
                + "`comprehensive` as c inner join `admin` as a on c.`c_creator` = a.`admin_id`inner join "
                + "sub_course as s on s.`sub_course_id` = c.`c_course` inner join hardness as h on h.`hardness_id` = c.`c_hardness` where `c_id` = "+_c_id);
        try {
            _o = "[";
            while (rs.next()) {
                _title = rs.getString("c_title");
                _content = rs.getString("c_description");
                _id = rs.getString("c_id");
                _date = rs.getString("c_execute_date");
                _course = rs.getString("name");
                _hardness = rs.getString("h.description");
                _error = false;
                _o += "{'flag':'0','id':'" + _id + "','title':'" + _title + "','content':'" + _content + "','date':'" + _date + "','course':'" + _course + "','hardness':'" + _hardness + "'}";
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
