/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dbHelper.Model;
import com.stuff.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ryan
 */
@WebServlet(name = "svGetHomework", urlPatterns = {"/svGetHomework"})
public class svGetHomework extends HttpServlet {

    private int qNumber, levelNumber, backNumber, hardnessNumber, subcourse, backNumberQQ, Examflag, subChapter;
    ArrayList<Question> arrlist;
    String ur;

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
        arrlist = new ArrayList<Question>(qNumber + backNumber);

        qNumber = Integer.parseInt(request.getParameter("q"));
        hardnessNumber = Integer.parseInt(request.getParameter("h"));
        Examflag = Integer.parseInt(request.getParameter("flag"));
        levelNumber = Integer.parseInt(request.getParameter("l"));
        backNumber = Integer.parseInt(request.getParameter("b"));
        backNumberQQ = Integer.parseInt(request.getParameter("bq"));

        subcourse = Integer.parseInt(request.getParameter("c"));

        if (Examflag == 3 || Examflag == 4 || Examflag == 5) {
            subChapter = Integer.parseInt(request.getParameter("subchapter"));
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            ArrayList<Question> arrlistTemp;
            try {
                if (levelNumber == getMinQuestion(subcourse)) {
                    arrlistTemp = getQuestion(levelNumber, subcourse, qNumber, hardnessNumber);

                } else {
                    if (Examflag == 0 || Examflag == 2) {
                        //  getReviewQuestion(levelNumber, subcourse, backNumberQQ, backNumber, getMinQuestion(subcourse));
                    }
                    arrlistTemp = getQuestion(levelNumber, subcourse, qNumber, hardnessNumber);
                }
                if (arrlistTemp.isEmpty()) {
                    out.println("[{'flag':'2'}]");
                } else {
                    out.print("[");
                    boolean flag = false;
                    for (Question q : arrlistTemp) {
                        if (!flag) {
                            out.print("{'flag':'0',");
                            flag = true;

                        } else {
                            out.print(",{'flag':'0',");
                        }
                        out.print("'qid':'" + q.getId() + "',");
                        out.print("'question':'" + q.getQuestion() + "',");
                        out.print("'tip':'" + q.getTip() + "',");
                        out.print("'inst':'" + q.getSolution() + "',");
                        out.print("'ans1':'" + q.getAnswer1() + "',");
                        out.print("'ansid1':'" + q.getAid1() + "',");
                        out.print("'ans2':'" + q.getAnswer2() + "',");
                        out.print("'ansid2':'" + q.getAid2() + "',");
                        out.print("'ans3':'" + q.getAnswer3() + "',");
                        out.print("'ansid3':'" + q.getAid3() + "',");
                        out.print("'ans4':'" + q.getAnswer4() + "',");
                        out.print("'ansid4':'" + q.getAid4() + "',");
                        out.print("'sc':'" + q.getSc() + "',");
                        out.print("'time':'" + q.getTime() + "',");
                        out.print("'right':'" + q.getRight() + "'}");
                    }

                    out.print("]");
                }

            } catch (Exception ex) {
                out.println("[{'flag':'1'}]" + ex.toString());

            }

        }

    }

    public ArrayList<Question> getQuestion(int level, int subCourse, int number, int hardness) throws SQLException {

        Model om = new Model();
        ResultSet rs1, rs2, rs3, rs4;
        int easy,
                medium,
                hard,
                hardest;

        switch (hardness) {
            case 1:

                easy = Math.round((number * 60) / 100);
                medium = Math.round((number * 20) / 100);
                hard = Math.round((number * 10) / 100);
                hardest = Math.round((number * 10) / 100);
                // soale ason

                break;
            case 2:
                // motevaset
                easy = Math.round((number * 30) / 100);
                medium = Math.round((number * 40) / 100);
                hard = Math.round((number * 20) / 100);
                hardest = Math.round((number * 10) / 100);

                break;
            case 3:
                //doshvar
                easy = Math.round((number * 20) / 100);
                medium = Math.round((number * 30) / 100);
                hard = Math.round((number * 30) / 100);
                hardest = Math.round((number * 20) / 100);

                break;
            case 4:
                //kheili doshvar
                easy = Math.round((number * 10) / 100);
                medium = Math.round((number * 20) / 100);
                hard = Math.round((number * 40) / 100);
                hardest = Math.round((number * 30) / 100);

                break;
            default:
                easy = (number * 25) / 100;
                medium = (number * 25) / 100;
                hard = (number * 25) / 100;
                hardest = (number * 25) / 100;
                break;

        }
        switch (Examflag) {
            case 0:
                ur = "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 1 and sc.`sequential_number` = " + level + " ORDER BY RAND() LIMIT " + easy + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 2 and sc.`sequential_number` = " + level + " ORDER BY RAND() LIMIT " + medium + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 3 and sc.`sequential_number` = " + level + " ORDER BY RAND() LIMIT " + hard + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 4 and sc.`sequential_number` = " + level + " ORDER BY RAND() LIMIT " + hardest + ")";

                break;
            case 1:
                ur = "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 1 ORDER BY RAND() LIMIT " + easy + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 2 ORDER BY RAND() LIMIT " + medium + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 3 ORDER BY RAND() LIMIT " + hard + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 4 ORDER BY RAND() LIMIT " + hardest + ")";

                break;
            case 3:
                System.err.println("33333333333333333333333333333333333333333333333333333333333333333");
                ur = "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sc.`sub_chapter_id` = " + subChapter + " and `hardness_id` = 1 ORDER BY RAND() LIMIT " + easy + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sc.`sub_chapter_id` = " + subChapter + " and `hardness_id` = 2 ORDER BY RAND() LIMIT " + medium + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sc.`sub_chapter_id` = " + subChapter + " and `hardness_id` = 3 ORDER BY RAND() LIMIT " + hard + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sc.`sub_chapter_id` = " + subChapter + " and `hardness_id` = 4 ORDER BY RAND() LIMIT " + hardest + ")";
                break;
            case 4:
                // moror eshtebahat
                System.err.println("444444444444444444444444444444444");

                ur = "SELECT * FROM `exam_slot` as e inner join question as q on e.`question_id` = q.`question_id` inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` where `answer` = 0 and sc.`sub_chapter_id` = " + subChapter;

                break;

            case 5:
                // ostad soal dade
                System.err.println("55555555555555555555555555555");

                ur = "(SELECT * FROM `question` WHERE `sub_chapter_id` = "+subChapter+" and `hardness_id` = 1  ORDER BY RAND() LIMIT " + easy + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` WHERE `sub_chapter_id` = "+subChapter+" and `hardness_id` = 2  ORDER BY RAND() LIMIT " + medium + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` WHERE `sub_chapter_id` = "+subChapter+" and `hardness_id` = 3  ORDER BY RAND() LIMIT " + hard + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` WHERE `sub_chapter_id` = "+subChapter+" and `hardness_id` = 4  ORDER BY RAND() LIMIT " + hardest + ")";

                break;
            default:
                ur = "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 1 and sc.`sequential_number` = " + level + " ORDER BY RAND() LIMIT " + easy + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 2 and sc.`sequential_number` = " + level + " ORDER BY RAND() LIMIT " + medium + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 3 and sc.`sequential_number` = " + level + " ORDER BY RAND() LIMIT " + hard + ")";
                ur += " UNION ALL ";
                ur += "(SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = " + subCourse + " and `hardness_id` = 4 and sc.`sequential_number` = " + level + " ORDER BY RAND() LIMIT " + hardest + ")";
                break;
        }

        rs1 = om.result(ur);
        System.err.println(ur);
        while (rs1.next()) {
            String url = "SELECT * FROM `answer` WHERE `question_id` = " + rs1.getString("question_id") + " ORDER BY RAND() LIMIT 4";
            ResultSet temp = om.result(url);
            int loop = 0;
            String[][] ans = new String[4][3];

            while (temp.next()) {
                ans[loop][1] = temp.getString("answer_id");
                ans[loop][2] = temp.getString("value");
                loop++;
            }
            //inja be loop bayad taghir peyda kone
            if (ans.length < 4) {

            } else {
                arrlist.add(new Question(rs1.getString("question_id"), rs1.getString("title"), rs1.getString("descriptive_answer"), rs1.getString("descriptive_solution"), ans[0][2], ans[1][2], ans[2][2], ans[3][2], rs1.getString("right_answer_id"), ans[0][1], ans[1][1], ans[2][1], ans[3][1], rs1.getString("sub_chapter_id") , rs1.getString("time")));
            }

        }
        om.closeConnection();
        return arrlist;
    }

    public ArrayList<Question> getReviewQuestion(int level, int subchapter, int number, int back, int min) {
        try {
            int a, b;
            a = subchapter - 1;
            b = subchapter - back;
            Model om = new Model();
            ResultSet rs1, rs2, rs3, rs4;
            int easy,
                    medium,
                    hard,
                    hardest;
            String ur;
            if (level - back < min) {
                //tedade levele negahe be gozashte az hadeaghal kamtare pas lahaz nemishe az hame gozashte soal myad
            } else {
                ur = "SELECT * FROM `question` as q inner join sub_chapter as sc on q.`sub_chapter_id` = sc.`sub_chapter_id` inner join chapter as c on sc.`chapter_id` = c.`chapter_id` inner join sub_course as sub on c.`sub_course_id` = sub.`sub_course_id` where sub.`sub_course_id` = 4 and `hardness_id` = 1 and sc.`sequential_number` BETWEEN " + a + " AND " + b + " ORDER BY RAND() LIMIT " + number + ")";

                rs1 = om.result(ur);
                System.err.println(rs1.getString("question_id"));
                while (rs1.next()) {
                    String url = "SELECT * FROM `answer` WHERE `question_id` = " + rs1.getString("question_id") + " ORDER BY RAND() LIMIT 4";
                    ResultSet temp = om.result(url);
                    int loop = 0;
                    String[][] ans = new String[4][3];
                    while (temp.next()) {
                        ans[loop][1] = temp.getString("answer_id");
                        ans[loop][2] = temp.getString("value");
                        loop++;
                    }
                    //inja be loop bayad taghir peyda kone
                    if (ans.length < 4) {

                    } else {
                        arrlist.add(new Question(rs1.getString("question_id"), rs1.getString("title"), rs1.getString("descriptive_answer"), rs1.getString("descriptive_solution"), ans[0][2], ans[1][2], ans[2][2], ans[3][2], rs1.getString("right_answer_id"), ans[0][1], ans[1][1], ans[2][1], ans[3][1], rs1.getString("sub_chapter_id") , rs1.getString("time")));
                    }

                }
                
            }
            om.closeConnection();
            return arrlist;
        } catch (SQLException ex) {

            return null;
        }

    }

    public int getMaxQuestion(int subcourse) {
        Model om = new Model();
        return Integer.parseInt(om.select("SELECT max(`sequential_number`) FROM `sub_course` as sc inner join chapter as c on sc.`sub_course_id` = c.`sub_course_id` inner join sub_chapter as s on c.`chapter_id` = s.`chapter_id` where sc.`sub_course_id` in (select sc.`sub_course_id` FROM `sub_course` as sc inner join chapter as c on sc.`sub_course_id` = c.`sub_course_id` inner join sub_chapter as s on c.`chapter_id` = s.`chapter_id` where sc.`sub_course_id` = " + subcourse + ")"));
    }

    public int getMinQuestion(int subcourse) {
        Model om = new Model();
        return Integer.parseInt(om.select("SELECT min(`sequential_number`) FROM `sub_course` as sc inner join chapter as c on sc.`sub_course_id` = c.`sub_course_id` inner join sub_chapter as s on c.`chapter_id` = s.`chapter_id` where sc.`sub_course_id` in (select sc.`sub_course_id` FROM `sub_course` as sc inner join chapter as c on sc.`sub_course_id` = c.`sub_course_id` inner join sub_chapter as s on c.`chapter_id` = s.`chapter_id` where sc.`sub_course_id` = " + subcourse + ")"));
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
