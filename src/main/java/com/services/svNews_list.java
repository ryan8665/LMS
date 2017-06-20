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
@WebServlet(name = "svNews_list", urlPatterns = {"/svNews_list"})
public class svNews_list extends HttpServlet {

    String _title, _brif, _id, _date, _user, _sub;
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

        ResultSet _rs = om.result("SELECT a.flag,t.value , i.fname , i.lname , n.news_id , n.title , n.content_lite , n.news_date "
                + "FROM `news` as n inner join admin as a on n.admin_id =  a.`admin_id` "
                + "inner join user_information as i on a.`user_information_id` = i.`user_information_id` "
                + "inner join user as u on i.`user_information_id` = u.`user_information_id` "
                + "inner join user_type as t on u.user_type_id = t.`user_type_id` order  by `news_id` DESC limit 15");
        try {
            _o = "[";
            while (_rs.next()) {
                _title = _rs.getString("n.title");
                _brif = _rs.getString("n.content_lite");
                _id = _rs.getString("n.news_id");
                _date = _rs.getString("n.news_date");
                _user = _rs.getString("i.fname") + " " + _rs.getString("i.lname");
                _sub = _rs.getString("t.value");
                 int flag = _rs.getInt("a.flag");
                 if(flag == 1 ){
                    _sub = "مدیریت" ;
                 }else{
                     _sub = "معلم ارشد";
                 }
                 

                _o += "{'news_id':'" + _id + "','title':'" + _title + "','content_lite':'" + _brif + "','date':'" + _date + "','user':'" + _user + "','sub':'" + _sub + "'}";
                if (!_rs.isLast()) {
                    _o += ",";
                }
            }
            _o += "]";
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
