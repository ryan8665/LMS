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
@WebServlet(name = "svHomework", urlPatterns = {"/svHomework"})
public class svHomework extends HttpServlet {

    private String _id;
    private String _o = "";
    private boolean _error = true;
    private boolean _empaty = true;
    private int level = 0;

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
        rs = om.result("SELECT su.`sub_chapter_id` as subchap , s.title as title , s.sub_course_id ,`s_c_t_id` as id , max(su.`sequential_number`) as max, min(su.`sequential_number`) as min , c.name as cource "
                + "FROM user_s_c_t as u "
                + "inner join s_c_t as s on u.`user_s_c_t_s_c_t_id` = s.`s_c_t_id` "
                + "inner join sub_course as c on s.sub_course_id = c.sub_course_id "
                + "inner join chapter as ch on c.`sub_course_id` = ch.`sub_course_id` "
                + "inner join sub_chapter as su on su.`chapter_id` = ch.`chapter_id` "
                + "WHERE u.`registration_date` + INTERVAL 365 DAY >= NOW() and u.`global_status_id` = 1 "
                + "and `user_sub_course_user_id` = " + _id + " group by `s_c_t_id`");

        try {
            if (!rs.isBeforeFirst()) {
                _empaty = true;
                //hich packagi nadare
                System.err.println("hich packagi nadare!!!!!!!!!!!!!!!!");
            } else {
                // packagi dare
                _empaty = false;
                while (rs.next()) {
                    System.err.println("packagi dare!!!!!!!!!!!!!!!!");
                    _error = false;
                    if (isToday(rs.getString("s.sub_course_id"), _id)) {
                        switch (isFirst(rs.getInt("s.sub_course_id"), rs.getInt("max"), _id)) {
                            case 0:
                                System.err.println("min!!!!!!!!!!!!!!!!!!!");
                                //min
                                _o += "{'flag':'3','level':'" + userLevel(rs.getString("s.sub_course_id"), _id) + "','sub_course':'" + rs.getInt("s.sub_course_id") + "','max':'" + rs.getInt("max") + "','min':'"
                                        + rs.getInt("min") + "','course':'" + rs.getString("cource") + "','title':'" + rs.getString("title")
                                        + "','hardness':'1','backLevel':'0','backNumber':'0','NumberQ':'" + getNumberQ(rs.getString("su.subchap")) + "','isTest':'0','leveln':'" + sendUserLevel() + "'}";
                                System.err.println(_o);
                                if (!rs.isLast()) {
                                    _o += ",";
                                }
                                break;
                            case 1:
                                // max
                                System.err.println("maaxxxxxxxxxx!!!!!!!!!!!!!!!!");
                                _empaty = true;
                                break;
                            default:
                                System.err.println("normaalll!!!!!!!!!!!!!!!!");
                                // normal
                                String test = "0";
                                String lv = "0";
                                if (Integer.parseInt(isTest(rs.getString("s.sub_course_id"), _id)) == 3) {
                                    test = "0";
                                    lv = (Integer.parseInt(sendUserLevel()) + 1) + "";

                                } else {
                                    test = isTest(rs.getString("s.sub_course_id"), _id);
                                    lv = sendUserLevel();
                                }
                                _o += "{'flag':'4','level':'" + userLevel(rs.getString("s.sub_course_id"), _id) + "','sub_course':'" + rs.getInt("s.sub_course_id") + "','max':'" + rs.getInt("max") + "','min':'"
                                        + rs.getInt("min") + "','course':'" + rs.getString("cource") + "','title':'" + rs.getString("title")
                                        + "','hardness':'" + getHardness() + "','backLevel':'" + getBackLevel() + "','backNumber':'" + getBackNumber() + "','NumberQ':'" + getNumberQ(rs.getString("su.subchap")) + "','isTest':'" + test + "','leveln':'" + lv + "'}";
                                if (!rs.isLast()) {
                                    _o += ",";
                                }
                                break;
                        }

                    }
                }
            }

        } catch (Exception e) {
            _error = true;
            _o = e.toString();
            System.err.println(e.toString());
        }
        om.closeConnection();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             System.err.println(_o);
            if (_empaty) {
                out.println("[{'flag':'2'}]");
            } else if (_error) {
                out.println("[{'flag':'1'}]");
            } else {
                out.println("[" + _o + "]");
            }
            _o = "";
        }
        om.closeConnection();
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
// inja bayad begam ke user chie , ID ezafe shavad vagarna vase hamaro myare

    private int isFirst(int sub_course_id, int max, String user) {
        Model om = new Model();
        String temp = om.select("SELECT max(`sequential_number`) FROM sub_course as suc "
                + "inner join chapter as c on suc.`sub_course_id` = c.`sub_course_id` "
                + "inner join `sub_chapter` as sc on c.`chapter_id` = sc.`chapter_id` "
                + "inner join question as q on sc.`sub_chapter_id` = q.`sub_chapter_id` "
                + "inner join exam_slot as sl on q.`question_id` = sl.`question_id` "
                + "inner join exam_detail as ed on sl.`exam_id` = ed.`exam_detail_id` "
                + "inner join exam as e on ed.`exam_id` = e.`exam_id` where e.`creator_id` = " + user + " and suc.`sub_course_id` = " + sub_course_id);
        int id;
        try {
            level = id = Integer.parseInt(temp);
        } catch (Exception e) {
            return 0;
        }
        if (id >= max) {
            return 1;
        } else {
            return id;
        }

    }

    public String getHardness() {
        Model om = new Model();
        String res;
        try {
            res = om.select("");
            Integer.parseInt(res);
        } catch (Exception e) {
            res = "1";
        }
        return res;

    }

    public String getBackLevel() {
        Model om = new Model();
        String res;
        try {
            res = om.select("");
            Integer.parseInt(res);
        } catch (Exception e) {
            res = "1";
        }
        return res;
    }

    public String getBackNumber() {
        Model om = new Model();
        String res;
        try {
            res = om.select("");
            Integer.parseInt(res);
        } catch (Exception e) {
            res = "4";
        }
        return res;
    }

    public String getNumberQ(String q) {
        Model om = new Model();
        String res;

        try {

            res = om.select("SELECT `number` FROM `sub_chapter` WHERE `sub_chapter_id` = " + q);

            Integer.parseInt(res);
        } catch (Exception e) {
            res = "10";
        }
        return res;
    }

    public String isTest(String sub_course_id, String user) {
        //0 test va 2 azmon age 3 bod yani bayad bere marhale badi va next level
        Model om = new Model();
        String temp = om.select("SELECT count(e.`exam_id`) FROM sub_course"
                + " as suc inner join chapter as c on suc.`sub_course_id` = c.`sub_course_id` "
                + "inner join `sub_chapter` as sc on c.`chapter_id` = sc.`chapter_id` "
                + "inner join question as q on sc.`sub_chapter_id` = q.`sub_chapter_id` "
                + "inner join exam_slot as sl on q.`question_id` = sl.`question_id` "
                + "inner join exam_detail as ed on sl.`exam_id` = ed.`exam_detail_id` "
                + "inner join exam as e on ed.`exam_id` = e.`exam_id` "
                + "where e.`creator_id` = " + user + " and suc.`sub_course_id` = " + sub_course_id + " group by e.`exam_id` ");
        try {
            int c = Integer.parseInt(temp);
            if (c >= 7) {
                //bayad bere level badi
                System.err.println("bayad bere level badi!!!!!!!!!!!!!!!!");
                return "3";
            }
            if (c <= 6) {
                //hanoz bayad tamrin kone
                System.err.println("hanoz bayad tamrin kone!!!!!!!!!!!!!!!!");
                return "0";
            } else {
                System.err.println("tamrin baseshe dge azmon bayad bede!!!!!!!!!!!!!!!!");
                //tamrin baseshe dge azmon bayad bede
                return "2";
            }
        } catch (Exception e) {
            //tamrin va tamrin
            System.err.println("tamrin va tamrin!!!!!!!!!!!!!!!!");
            return "0";
        }

    }

    public String sendUserLevel() {

        return "55";
    }

    private int userLevel(String sub_course_id, String user) {
        Model om = new Model();
        String temp = om.select("SELECT max(`sequential_number`) FROM sub_course as suc "
                + "inner join chapter as c on suc.`sub_course_id` = c.`sub_course_id` "
                + "inner join `sub_chapter` as sc on c.`chapter_id` = sc.`chapter_id` "
                + "inner join question as q on sc.`sub_chapter_id` = q.`sub_chapter_id` "
                + "inner join exam_slot as sl on q.`question_id` = sl.`question_id` "
                + "inner join exam_detail as ed on sl.`exam_id` = ed.`exam_detail_id` "
                + "inner join exam as e on ed.`exam_id` = e.`exam_id` where e.`creator_id` = " + user + " and suc.`sub_course_id` = " + sub_course_id);
        try {
            return Integer.parseInt(temp);
        } catch (Exception e) {
            return 0;
        }

    }
    //chek mikone bebine emroz daneshamoz tamrin ya emtehani dashte yana age dashte bashe mige false age na ke true barmigardone

    public boolean isToday(String sub_course_id, String user) {
        Model om = new Model();
        String temp = om.select("SELECT max(`sequential_number`) FROM "
                + "sub_course as suc inner join chapter as c on suc.`sub_course_id` = c.`sub_course_id` "
                + "inner join `sub_chapter` as sc on c.`chapter_id` = sc.`chapter_id` "
                + "inner join question as q on sc.`sub_chapter_id` = q.`sub_chapter_id` "
                + "inner join exam_slot as sl on q.`question_id` = sl.`question_id` "
                + "inner join exam_detail as ed on sl.`exam_id` = ed.`exam_detail_id` "
                + "inner join exam as e on ed.`exam_id` = e.`exam_id` "
                + "where e.`creator_id` = " + user + " and suc.`sub_course_id` = " + sub_course_id + " and DATE(e.`creation_date`) = CURDATE()");
        
        System.out.println("SELECT max(`sequential_number`) FROM "
                + "sub_course as suc inner join chapter as c on suc.`sub_course_id` = c.`sub_course_id` "
                + "inner join `sub_chapter` as sc on c.`chapter_id` = sc.`chapter_id` "
                + "inner join question as q on sc.`sub_chapter_id` = q.`sub_chapter_id` "
                + "inner join exam_slot as sl on q.`question_id` = sl.`question_id` "
                + "inner join exam_detail as ed on sl.`exam_id` = ed.`exam_detail_id` "
                + "inner join exam as e on ed.`exam_id` = e.`exam_id` "
                + "where e.`creator_id` = " + user + " and suc.`sub_course_id` = " + sub_course_id + " and DATE(e.`creation_date`) = CURDATE()");
        try {
            Integer.parseInt(temp);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

}
