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
@WebServlet(name = "svGetSubChapter", urlPatterns = {"/svGetSubChapter"})
public class svGetSubChapter extends HttpServlet {

    String _id, _o;

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
        boolean flag = true;
        String id,value;
        try {
            Model om = new Model();
            _id = request.getParameter("i");
            ResultSet temp = om.result("SELECT s.`sub_chapter_id` ,s.`title` FROM `sub_chapter` as s inner join"
                    + " chapter as c on s.`chapter_id` = c.`chapter_id` inner join"
                    + " sub_course as sc on c.`sub_course_id` = sc.`sub_course_id` inner join"
                    + " s_c_t as ast on sc.`sub_course_id` = ast.`sub_course_id` where `s_c_t_id` =  "+_id);
            _o = "[";
            while (temp.next()) {
                 id = temp.getString("sub_chapter_id");
                value = temp.getString("title");
                _o += "{'flag':'0','id':'" + id + "','value':'" + value + "'}";
                if (!temp.isLast()) {
                    _o += ",";
                }
                flag = false;
            }
            _o += "]";
        } catch (SQLException ex) {
            flag = true;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (flag) {
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
