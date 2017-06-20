package com.dbHelper;

import java.sql.*;

/**
 *
 * @author Ryan This Class is for connecting to DB
 */
public class Model {

    private Connection c1;
    private String url;

    public Model() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/lms?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
            // c1 = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public boolean insert(String sql) {
        try {
            c1 = DriverManager.getConnection(url);
            String q = sql;
            Statement st = c1.createStatement();
            st.executeUpdate(q);
            st.close();
            c1.close();
            return false;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return true;
        } finally {

        }
    }

    public boolean Delete(String sql) {
        try {
            c1 = DriverManager.getConnection(url);
            String q = sql;
            Statement st = c1.createStatement();

            st.executeUpdate(q);
            st.close();
            c1.close();
            return false;
        } catch (Exception ex) {
            return true;
        } finally {

        }
    }

    public boolean Update(String sql) {
        try {
            c1 = DriverManager.getConnection(url);
            String q = sql;
            Statement st = c1.createStatement();

            st.executeUpdate(q);
            st.close();
            c1.close();
            return false;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return true;
        } finally {

        }
    }

    public String select(String sql) {
        try {
            c1 = DriverManager.getConnection(url);
            Statement st = c1.createStatement();
            ResultSet rs2 = st.executeQuery(sql);
            String select = "";
            while (rs2.next()) {
                select = rs2.getString(1);
            }
            rs2.close();
            st.close();
            c1.close();
            return select;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public Date Date(String sql) {
        try {
            c1 = DriverManager.getConnection(url);
            Statement st = c1.createStatement();
            ResultSet rs2 = st.executeQuery(sql);
            Date select = null;
            while (rs2.next()) {
                select = rs2.getDate(1);
            }

            rs2.close();
            st.close();
            c1.close();
            return select;
        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet result(String sql) {
        try {
            c1 = DriverManager.getConnection(url);
            Statement st = c1.createStatement();
            ResultSet rs2 = st.executeQuery(sql);
            return rs2;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public void closeConnection() {
        try {
            c1.close();
        } catch (Exception e) {

        }

    }

    public String transaction(String q, String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        String select = "";
        try {
            connection.setAutoCommit(false);

            Statement statement1 = null;
            try {
                statement1 = connection.createStatement();
                statement1.executeUpdate(q);
            } finally {
                if (statement1 != null) {
                    statement1.close();
                }
            }

            Statement statement2 = null;
            try {
                statement2 = connection.createStatement();
                ResultSet rs2 = statement2.executeQuery(sql);

                while (rs2.next()) {
                    select = rs2.getString(1);
                }
            } finally {
                if (statement2 != null) {
                    statement2.close();
                }
            }

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return select;
    }

    // ar String 
    public boolean transaction(String[][] a,String b) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        String select = "";
        try {
            connection.setAutoCommit(false);

            Statement statement1 = null;
            for (int i = 0; i < a.length; i++) {
                try {
                    statement1 = connection.createStatement();
                    statement1.executeUpdate("INSERT INTO `lms`.`exam_slot` (`exam_slot_id`, `title`, `exam_id`, `question_id`, `answer`) VALUES (NULL, 'not titled', '"+b+"', '"+a[i][0]+"', '"+a[i][1]+"');");
                } finally {
                    if (statement1 != null) {
                        statement1.close();
                    }
                }
            }

            connection.commit();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return true;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

}
