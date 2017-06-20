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
@WebServlet(name = "svUserPackage_Detail", urlPatterns = {"/svUserPackage_Detail"})
public class svUserPackage_Detail extends HttpServlet {

    private String _id;
    private String _o;
    private String _date, _name, _course, _teacher, _pid, _short, _status, _course_description, _price,_subCourseID;
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
        rs = om.result("SELECT c.sub_course_id as subCourseID , i.fname,i.lname,u.registration_date,s.title,s.description,c.name,c.description,u.global_status_id,u.user_s_c_t_id,s.price FROM `user_s_c_t` as u inner join"
                + " `s_c_t` as s on u.`user_s_c_t_s_c_t_id` = s.`s_c_t_id` inner join `sub_course` as c on s.`sub_course_id` = c.sub_course_id inner join user as us on s.`teacher_id` = us.`user_id` inner join"
                + " user_information as i on us.`user_information_id` = i.`user_information_id` where `s_c_t_id` = "+_id);
        try {
            _o = "[";
            while (rs.next()) {
                _date = rs.getString("u.registration_date");
                _name = rs.getString("s.title");
                _course = rs.getString("c.name");
                _teacher = rs.getString("i.fname") + " " + rs.getString("i.lname");
                _status = rs.getString("u.global_status_id");
                _pid = rs.getString("u.user_s_c_t_id");
                _short = rs.getString("s.description");
                _course_description = rs.getString("c.description");
                _price = rs.getString("s.price");
                _subCourseID= rs.getString("subCourseID");
                _error = false;
                _o += "{'flag':'0','id':'" + _pid + "','title':'" + _name + "','course':'" + _course + "','date':'" + _date + "','teacher':'" + _teacher + "','status':'" + _status + "','short':'" + _short + "','course_desc':'" + _course_description + "','price':'" + _price + "','subCourseID':'" + _subCourseID + "'}";
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
