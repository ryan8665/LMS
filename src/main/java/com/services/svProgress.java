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
@WebServlet(name = "svProgress", urlPatterns = {"/svProgress"})
public class svProgress extends HttpServlet {

    private String _id,_user;
    private String _o = "";
    boolean _error = false;



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
        _user = request.getParameter("u");
        Model om = new Model();
        

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (_error) {
                out.println("[{'flag':'1'}]");
            } else {
                out.println("[{'flag':'0','max':'"+maxLevel(_id)+"','min':'"+minLevel(_id)+"','level':'"+userLevel(_id, _user)+"'}]");
            }
            _o = "";
            om.closeConnection();
        }
    }

    private int maxLevel(String sub_course_id) {
         Model om = new Model();
        String temp = om.select("SELECT max(su.`sequential_number`) FROM  sub_course as c \n"
                + "inner join chapter as ch on c.`sub_course_id` = ch.`sub_course_id` \n"
                + "inner join sub_chapter as su on su.`chapter_id` = ch.`chapter_id` \n"
                + "inner join question as q on su.`sub_chapter_id` = q.`sub_chapter_id` \n"
                + "where c.`sub_course_id` = "+sub_course_id);
        return Integer.parseInt(temp);
    }
    
    private int minLevel(String sub_course_id) {
         Model om = new Model();
        String temp = om.select("SELECT min(su.`sequential_number`) FROM  sub_course as c \n"
                + "inner join chapter as ch on c.`sub_course_id` = ch.`sub_course_id` \n"
                + "inner join sub_chapter as su on su.`chapter_id` = ch.`chapter_id` \n"
                + "inner join question as q on su.`sub_chapter_id` = q.`sub_chapter_id` \n"
                + "where c.`sub_course_id` = "+sub_course_id);
        return Integer.parseInt(temp);
    }
    
    private int userLevel(String sub_course_id,String user) {
         Model om = new Model();
        String temp = om.select("SELECT max(`sequential_number`) FROM sub_course as suc "
                + "inner join chapter as c on suc.`sub_course_id` = c.`sub_course_id` "
                + "inner join `sub_chapter` as sc on c.`chapter_id` = sc.`chapter_id` "
                + "inner join question as q on sc.`sub_chapter_id` = q.`sub_chapter_id` "
                + "inner join exam_slot as sl on q.`question_id` = sl.`question_id` "
                + "inner join exam_detail as ed on sl.`exam_id` = ed.`exam_detail_id` "
                + "inner join exam as e on ed.`exam_id` = e.`exam_id` where e.`creator_id` = "+user+" and suc.`sub_course_id` = "+sub_course_id);
        try{
           return Integer.parseInt(temp);
        }catch(Exception e){
            return 0;
        }
         
    }

    // ghabliaro beriz dor az inja bebad jadde
    // havaset bashe sql haro del nakoni!
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
