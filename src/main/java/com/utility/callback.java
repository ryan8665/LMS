/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utility;

import com.bps.sw.pgw.service.IPaymentGateway;
import com.bps.sw.pgw.service.PaymentGatewayImplService;
import com.dbHelper.Model;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "callback", urlPatterns = {"/callback"})
public class callback extends HttpServlet {

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
        String a, b, c, d, m = "0";
        a = request.getParameter("RefId");
        b = request.getParameter("ResCode");
        c = request.getParameter("SaleOrderId");
        d = request.getParameter("SaleReferenceId");
       
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            int flag = Integer .parseInt(m);
            /* TODO output your page here. You may use following sample code. */
            out.println(a);
            out.println(b);
            out.println(c);
            if (Integer.parseInt(b) == 0) {
                if (Integer.parseInt(doBanck(b, c, d)) == 0) {

                    doDB(c, a);
                    response.sendRedirect("/Error/bank_successful.xhtml");

                } else {
                    response.sendRedirect("/Error/bank_error.xhtml");
                }

            } else {
                response.sendRedirect("/Error/bank_error.xhtml");
            }

        }
    }

    public String doBanck(String b, String c, String d) {
        IPaymentGateway service = new PaymentGatewayImplService().getPaymentGatewayImplPort();
        String responseMessage = "";
        long terminalId = 2174497;
        String userName = "mand25";
        String userPassword = "37414569";
        long orderId = Long.valueOf(c).longValue();
        long saleOrderId = Long.valueOf(c).longValue();
        long saleReferenceId = Long.valueOf(d).longValue();

        try {
            responseMessage = service.bpVerifyRequest(terminalId, userName, userPassword,
                    orderId, saleOrderId, saleReferenceId);
            return responseMessage;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public void doDB(String oid, String ref) {
        String user = "";
        String amount = "";
        Model om = new Model();
        try {
            user = om.select("SELECT b_PayerId FROM `bank` WHERE `b_OrderId` = " + oid);
            amount = om.select("SELECT `b_Amount` FROM `bank` WHERE `b_OrderId` = " + oid);
            String msg = "افزایش موجودی از درگاه بانک ";
            msg += amount + " ";
            msg += " R";
            msg += ref;

            om.insert("INSERT INTO `lms`.`invoice` (`invoice_id`, `description`, `invoice_status_id`, `user_id`, `amount`, `dtime`) VALUES "
                    + "(NULL, '" + ref + " افزایش موجودی از درگاه بانک', '0', '" + user + "', '" + amount + "', CURRENT_TIMESTAMP);");
            com.log.logParent.iLog(msg, user);
        } catch (Exception e) {

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
