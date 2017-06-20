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
@WebServlet(name = "svComprehensive", urlPatterns = {"/svComprehensive"})
public class svComprehensive extends HttpServlet {

    private int _flag = 1;
    private boolean _error = true;
    String _title, _content, _id, _date , _course,_courseID,_hardness,_number;
    String _o;
    private int userInfoID;

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
        userInfoID= Integer.parseInt(request.getParameter("uid"));
        ResultSet rs;
        rs = om.result("SELECT number , `c_course` as course ,`c_hardness` as hardness ,`c_id` as id , `c_title` as title , `c_short_description` as short , name , c_execute_date as date FROM `comprehensive` inner join `sub_course` on `comprehensive`.`c_course` = `sub_course`.sub_course_id order by `c_id` desc limit 20 ");
        try {
            _o = "[";
            while (rs.next()) {
               
                _title = rs.getString("title");
                _content = rs.getString("short");
                _id = rs.getString("id");
                _date = rs.getString("date");
                _courseID = rs.getString("course");
                _hardness = rs.getString("hardness");
                _number= rs.getString("number");
                _error = false;
                String temp =  om.select("SELECT `cu_id` FROM `comprehensive_user` WHERE cu_comprehensive = "+_id+" and `cu_user` = "+userInfoID);
                 
                if(temp.equals("")){
                   _o += "{'flag':'0','id':'" + _id + "','title':'" + _title + "','content':'" + _content + "','date':'" + _date + "','course':'" + _course + "','courseID':'" + _courseID + "','hardness':'" + _hardness + "','number':'" + _number + "'}";
                    if (!rs.isLast()) {
                    _o += ",";
                }
                }else{
                   // _o += "{'flag':'0','id':'" + _id + "','title':'" + _title + "','content':'" + _content + "','date':'" + _date + "','course':'" + _course + "','courseID':'" + _courseID + "','hardness':'" + _hardness + "'}";
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
