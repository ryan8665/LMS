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
@WebServlet(name = "svNews", urlPatterns = {"/svNews"})
public class svNews extends HttpServlet {

    String _title, _content, _id, _date;
    int _view = 0;
    String _o;

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
        String id = request.getParameter("i");
        ResultSet _rs = om.result("SELECT * FROM `news` where news_id = " + id);
        try {
            _o = "[";
            while (_rs.next()) {
                _title = _rs.getString("title");
                _content = _rs.getString("content");
                _id = _rs.getString("news_id");
                 _date = _rs.getString("news_date");
                 _view = _rs.getInt("view");
                _o += "{'news_id':'" + _id + "','title':'" + _title + "','content':'" + _content + "','date':'" + _date + "','view':'" + _view + "'}";
                if (!_rs.isLast()) {
                    _o += ",";
                }
            }
            _o += "]";
            _view ++;
            om.Update("UPDATE `lms`.`news` SET `view` = '"+_view+"' WHERE `news`.`news_id` = "+_id+"; ");
 om.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(svStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PrintWriter out = response.getWriter()) {

            out.println(_o);
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
