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
import javax.xml.ws.Service;

/**
 *
 * @author ryan
 */
@WebServlet(name = "svSubmitQuiz", urlPatterns = {"/svSubmitQuiz"})
public class svSubmitQuiz extends HttpServlet {

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
        boolean error = true;
        String id = request.getParameter("i");
        String mark = request.getParameter("m");
        Model om = new Model();
        mark = mark.replace("p", ".");
        error = om.Update("UPDATE `lms`.`quiz` SET `q_date_fill` = CURRENT_TIMESTAMP(), `q_mark` = '" + mark + "', `q_flag` = '1' WHERE `quiz`.`q_id` = " + id);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (error) {
                out.println("[{'flag':'1'}]");
            } else {
                String t = om.select("SELECT `q_teacher` FROM `quiz` WHERE `q_id` = " + id);
                String u = om.select("SELECT `q_student` FROM `quiz` WHERE `q_id` = " + id);
                String n = " ";
                n += om.select("SELECT`lname` FROM `quiz` as q inner join user as u on q.`q_teacher` = u.`user_id` inner join `user_information` as i on u.`user_information_id` = i.`user_information_id` WHERE `q_id` = " + id);
                n += "-";
                double value = Double.parseDouble(mark);
                if (value < 50) {
                    om.insert("INSERT INTO `lms`.`alarm` (`a_id`, `a_title`, `a_description`, `a_flag`, `a_sender`, `a_reciver`, `a_date`) VALUES "
                            + "(NULL, 'انجام تکلیف با نمره کم', '" + mark + "انجام تکلیف با نمره ', '1', '" + u + "', '" + t + "', CURRENT_TIMESTAMP);");
                }
                com.log.logParent.iLog("انجام تکالیف استاد" + n + "  اندروید", u);
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
