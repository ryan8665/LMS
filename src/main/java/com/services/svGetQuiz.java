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
@WebServlet(name = "svGetQuiz", urlPatterns = {"/svGetQuiz"})
public class svGetQuiz extends HttpServlet {

   

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
        String _id, _name, _date, _desc, _user, _sct, _subChapter, _hardness, _number;
         String o = null;
        boolean error = true;
        _user = request.getParameter("u");
        Model om = new Model();

        ResultSet _rs = om.result("SELECT * FROM `quiz` where `q_flag` = 0 and `q_student` = " + _user);
        try {
            o = "[";
            while (_rs.next()) {
                error = false;
                _id = _rs.getString("q_id");
                _name = _rs.getString("q_name");
                _date = _rs.getString("q_date_in");
                _desc = _rs.getString("q_des");
                _sct = _rs.getString("q_sct");
                _hardness = _rs.getString("q_hardness");
                _number = _rs.getString("q_number");
                _subChapter = _rs.getString("q_subChapter");
                o += "{'flag':'0','id':'" + _id + "','name':'" + _name + "','date':'" + _date + "','des':'" 
                        + _desc + "','sct':'" + _sct + "','sub':'" + _subChapter + "','hardness':'" + _hardness + "','number':'" + _number + "'}";
                if (!_rs.isLast()) {
                    o += ",";
                }
            }
            o += "]";
            om.closeConnection();

        } catch (SQLException ex) {
            om.closeConnection();
            error = true;
            Logger.getLogger(svStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (error) {
                out.println("[{'flag':'1'}]");
            } else {
                out.println(o);
            }

        }
        om.closeConnection();
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
