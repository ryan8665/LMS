
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
@WebServlet(name = "svGetProfile", urlPatterns = {"/svGetProfile"})
public class svGetProfile extends HttpServlet {

    private String _id;
    private String _o;
    private String _user_id, _imei, _title, _registration, _last_login, _u_information, _name, _family, _national_id, _mobile, _email;
    private boolean _error = true;

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
        rs = om.result("SELECT u.user_id , u.imei , u.title , u.registration_date , u.last_login_time , u.user_information_id "
                + " , i.fname , i.lname , i.national_id , i.mobile , i.email ,i.state_id FROM user as u inner join "
                + "user_information as i on i.user_information_id = u.user_information_id WHERE u.user_id = " + _id);
        try {
            _o = "[";
            while (rs.next()) {
                _user_id = rs.getString("u.user_id");
                _imei = rs.getString("u.imei");
                _title = rs.getString("u.title");
                _registration = rs.getString("u.registration_date");
                _last_login = rs.getString("u.last_login_time");
                _u_information = rs.getString("u.user_information_id");
                _name = rs.getString("i.fname");
                _family = rs.getString("i.lname");
                _national_id = rs.getString("i.national_id");
                _mobile = rs.getString("i.mobile");
                _email = rs.getString("i.email");

                _error = false;

                _o += "{'flag':'0','_user_id':'" + _user_id + "','_imei':'" + _imei + "','_title':'" + _title
                        + "','_registration':'" + _registration + "','_last_login':'" + _last_login + "','_u_information':'"
                        + _u_information + "','_name':'" + _name + "','_family':'" + _family + "','_national_id':'" + _national_id
                        + "','_mobile':'" + _mobile + "','_email':'" + _email + "'}";

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
