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
@WebServlet(name = "svSendMessage", urlPatterns = {"/svSendMessage"})
public class svSendMessage extends HttpServlet {

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
        String id, teacher, subject, body, pack;
        String a = null, b = null;
        boolean flag = true;
        try {
            Model om = new Model();
            id = request.getParameter("i");
            subject = request.getParameter("s");
            byte[] bytes2 = subject.getBytes(StandardCharsets.ISO_8859_1);
            subject = new String(bytes2, StandardCharsets.UTF_8);
            body = request.getParameter("b");
            byte[] bytes = body.getBytes(StandardCharsets.ISO_8859_1);
            body = new String(bytes, StandardCharsets.UTF_8);

            pack = request.getParameter("p");

            System.out.print(a);
            teacher = om.select("SELECT `teacher_id` FROM `s_c_t` WHERE `s_c_t_id` = " + pack);
            System.out.print(teacher);

            b = "INSERT INTO `lms`.`message` (`message_id`, `title`, `message`, `sender_id`, `reciver_id`, `is_read`, `date`) VALUES "
                    + "(NULL, 're " + subject + "', '" + body + "', '" + id + "', '" + teacher + "', '1', CURRENT_TIMESTAMP);";
            boolean res = om.insert(b);
            if (res) {
                flag = true;
            } else {
                flag = false;
            }
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
