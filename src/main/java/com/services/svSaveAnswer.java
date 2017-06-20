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
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author ryan
 */
@WebServlet(name = "svSaveAnswer", urlPatterns = {"/svSaveAnswer"})
public class svSaveAnswer extends HttpServlet {

    private Model om;
    private String _ans, _user;
    String temp1,temp2;
    

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
        
        _ans = request.getParameter("a");
        _user = request.getParameter("u");
        boolean flag = true;
        try {
            flag = false;
            JSONArray Jres = new JSONArray(_ans);
            String q, a;
            String[][] toInsert =  new String[Jres.length()][2];
            for (int i = 0; i < Jres.length(); i++) {
                JSONObject jsonData = Jres.getJSONObject(i);
                q = jsonData.getString("q");
                a = jsonData.getString("a");
                toInsert[i][0] = q;
                toInsert[i][1] = a;
            }
            Model om = new Model();
          temp1 =   om.transaction("INSERT INTO `lms`.`exam` (`exam_id`, `title`, `creation_date`, `exhibition_date`, `describtion`, `creator_id`) VALUES (NULL, 'not titled', CURRENT_TIMESTAMP, NULL, NULL, '"+_user+"');","SELECT LAST_INSERT_ID();");
          temp2 =   om.transaction("INSERT INTO `lms`.`exam_detail` (`exam_detail_id`, `title`, `exam_id`, `taking_date`) VALUES (NULL, 'not titled', '"+temp1+"', NULL);","SELECT LAST_INSERT_ID();");
          flag = om.transaction(toInsert,temp2);
        } catch (Exception e) {
            flag = true;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (flag) {
                out.println("[{'flag':'1'}]");
            } else {
                com.log.logParent.iLog("انجام تکالیف سیستمی", _user);
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
