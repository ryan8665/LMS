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
 * @author amirk
 */
@WebServlet(name = "svRedeem", urlPatterns = {"/svRedeem"})
public class svRedeem extends HttpServlet {

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
        String id = null, code = null;
        Model om = new Model();
        String temp = "n";
        String temp2 = "n";
        String pid = "n";
        String amu = "0";
        boolean flag = true;
        try {
            
            id = request.getParameter("i");
            code = request.getParameter("r");
            String to = code + " کارت هدیه";
            temp = om.select("SELECT `code` FROM `promotion` WHERE `code` = '" + code + "'");
             amu = om.select("SELECT `value` FROM `promotion` WHERE `code` = '" + code + "'");
            if (temp.trim().equals(code.trim())) {
                flag = false;
                
                temp2 = om.select("SELECT `code` FROM `promotion` as p inner join `promotion_user` as u on u.`promotion_id` = p.`promotion_id` where p.`code` = '" + code + "'");
                 
                if (temp2.trim().equals(code.trim())) {
                    flag = true;
                } else {
                    flag = false;
                    pid = om.select("SELECT p.`promotion_id` FROM `promotion` as p where p.`code` = '" + code + "'");
                        boolean res = om.insert("INSERT INTO `lms`.`promotion_user` (`promotion_user_id`, `title`, `description`, `promotion_id`, `user_id`) VALUES "
                                + "(NULL, 'کارت هدیه', '" + to + "', '" + pid + "', '" + id + "');");
                       
                        if (res) {
                            flag = true;
                        } else {
                            om.insert("INSERT INTO `lms`.`invoice` (`invoice_id`, `description`, `invoice_status_id`, `user_id`, `amount`, `dtime`) VALUES (NULL, '"+to+"', '0', '" + id + "', '1000', CURRENT_TIMESTAMP);");
                            flag = false;
                        }      
                }

            } else {
                flag = true;
            }

        } catch (Exception e) {
            flag = true;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (flag) {
                out.println("[{'flag':'1'}]");
            } else {
                String msg = "اعمال کد تخفیف با شماره ";
                msg += code+" ";
                msg += "اندروید";
                com.log.logParent.iLog(msg, id);
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
