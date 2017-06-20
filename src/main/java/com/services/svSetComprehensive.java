/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dbHelper.Model;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ryan
 */
@WebServlet(name = "svSetComprehensive", urlPatterns = {"/svSetComprehensive"})
public class svSetComprehensive extends HttpServlet {

    String _mark, _user, _id;

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
        _mark = request.getParameter("m");
        _user = request.getParameter("u");
        _id = request.getParameter("i");
         _mark = _mark.replace("p", ".");
        boolean flag = om.insert("INSERT INTO `lms`.`comprehensive_user` (`cu_id`, `cu_user`, `cu_mark`, `cu_date`, `cu_comprehensive`) VALUES "
                + "(NULL, '" + _user + "', '" + _mark + "', CURRENT_TIMESTAMP, '" + _id + "');");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (flag) {
                out.println("[{'flag':'1'}]");
            } else {
                String msg = "شرکت در آزمون جامع با نمره ";
                msg += _mark+" ";
                msg += "اندروید";
                com.log.logParent.iLog(msg, _user);
                out.println("[{'flag':'0'}]");
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
