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
@WebServlet(name = "svDoPurchest", urlPatterns = {"/svDoPurchest"})
public class svDoPurchest extends HttpServlet {


   private String price, bank;

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
        boolean flag = false;
        boolean error = true;
         
          String _id, _user;

        try {
            _id = request.getParameter("i");
            _user = request.getParameter("u");
            Model om = new Model();
            price = om.select("SELECT s.price  FROM `s_c_t` as s   where  s.`s_c_t_id` = " + _id + " ");
            bank = om.select("SELECT sum(`amount`) FROM `invoice` where `user_id` = " + _user);
            if (Integer.parseInt(price) <= Integer.parseInt(bank)) {

                
                om.insert("INSERT INTO `lms`.`invoice` (`invoice_id`, `description`, `invoice_status_id`, `user_id`, `amount`, `dtime`) VALUES (NULL, 'خرید بسته اموزشی', '0', '" + _user + "', '-" + Integer.parseInt(price) + "', CURRENT_TIMESTAMP);");
                om.insert("INSERT INTO `lms`.`user_s_c_t` (`user_s_c_t_id`, `description`, `user_sub_course_user_id`, `user_s_c_t_s_c_t_id`, `registration_date`, `global_status_id`) VALUES (NULL, 'no desc', '" + _user + "', '" + _id + "', CURRENT_TIMESTAMP, '1');");
                flag = true;
            } else {
                flag = false;
            }
            error = false;
        } catch (Exception e) {
            error = true;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (error) {
                out.println("[{'flag':'2'}]");
            } else if (flag) {
                out.println("[{'flag':'0'}]");
            } else {
                out.println("[{'flag':'1'}]");
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
