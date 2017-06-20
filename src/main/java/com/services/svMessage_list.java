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
@WebServlet(name = "svMessage_list", urlPatterns = {"/svMessage_list"})
public class svMessage_list extends HttpServlet {

    private String _id;
    private String _o;
    private boolean _error = true;
    private String _title, _date, _mid, _isRead,_fname,_lname;

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
        rs = om.result("SELECT m.`message_id`,m.`title`,m.`date`,m.`is_read`,i.fname,i.lname FROM `message` as m inner join user as u on m.`sender_id` = u.`user_id` inner join user_information as i on u.`user_information_id` = i.`user_information_id` WHERE m.`is_read` = 1 AND m.`reciver_id` = " + _id +" ORDER BY message_id DESC");
        try {
            _o = "[";
            while (rs.next()) {
                _title = rs.getString("title");
                _date = rs.getString("date");
                _mid = rs.getString("message_id");
                _isRead = rs.getString("is_read");
                _fname = rs.getString("i.fname");
                _lname = rs.getString("i.lname");

                _error = false;

                _o += "{'flag':'0'" + ",'_title':'" + _title + "','_date':'" + _date + "','_mid':'" + _mid + "','_isRead':'" + _isRead + "','_user':'" + _fname +" "+_lname+ "'}";

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
