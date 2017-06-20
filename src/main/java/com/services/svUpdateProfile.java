/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dbHelper.Model;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author amirk
 */
@WebServlet(name = "svUpdateProfile", urlPatterns = {"/svUpdateProfile"})
public class svUpdateProfile extends HttpServlet {

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
        String name, family, gender, nid, email, id;
        boolean flag = true;

        try {
            name = request.getParameter("n");
            byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
            name = new String(bytes, StandardCharsets.UTF_8);

            family = request.getParameter("f");
            byte[] bytes2 = family.getBytes(StandardCharsets.ISO_8859_1);
            family = new String(bytes2, StandardCharsets.UTF_8);

            gender = request.getParameter("g");
            nid = request.getParameter("ni");
            email = request.getParameter("e");
            id = request.getParameter("i");
            Model om = new Model();
            flag = om.Update("UPDATE `lms`.`user_information` SET "
                    + "`fname` = '" + name + "' , "
                    + "`lname` = '" + family + "' , "
                    + "`user_gender_id` = '" + gender + "' , "
                    + "`national_id` = '" + nid + "' , "
                    + "`email` = '" + email + "' "
                    + "WHERE `user_information`.`user_information_id` = " + id + "; ");
        } catch (Exception e) {
            flag = true;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (flag) {
                out.println("[{'flag':'1'}]");
            } else {
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
