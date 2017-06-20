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
@WebServlet(name = "svGetPackage", urlPatterns = {"/svGetPackage"})
public class svGetPackage extends HttpServlet {

    private String _id;
    private String _o;
    private String _name, _course, _teacher, _pid, _description, _price;
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
        rs = om.result("SELECT s.price , i.fname , i.lname , s.s_c_t_id , c.name , s.description , s.title FROM `s_c_t` as s inner join sub_course as c on s.`sub_course_id` = c.`sub_course_id` inner join user as us on s.`teacher_id` = us.`user_id` inner join user_information as i on us.`user_information_id` = i.`user_information_id` "
                + "where s.`global_status_id` = 1 and s.`sub_course_id` = " + _id + " order by c.name ");
        try {
            _o = "[";
            while (rs.next()) {
                _name = rs.getString("s.title");
                _teacher = rs.getString("i.fname") + " " + rs.getString("i.lname");
                _pid = rs.getString("s.s_c_t_id");
                _description = rs.getString("s.description");
                _price = rs.getString("s.price");
                _error = false;

                _o += "{'flag':'0','id':'" + _pid + "','title':'" + _name + "','teacher':'" + _teacher + "','price':'" + _price + "'}";

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
