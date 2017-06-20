/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filters;

import com.controler.login;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author amirk
 */
@WebFilter(filterName = "studentWebsite", urlPatterns = {"/web/invoice.xhtml"})
public class studentWebsite implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        login loginBean3 = null;
        try {
            loginBean3 = (login) ((HttpServletRequest) request).getSession().getAttribute("login");
            System.out.println("sssssssssssssssssssssssss    " + loginBean3.isIsStudent());
        } catch (Exception e) {
            System.out.println("sssssssssssssssssssssssss    ");
        }

        String contextPath = ((HttpServletRequest) request).getContextPath();
        try {
            if (loginBean3 == null || loginBean3.isIsStudent() == false) {

                ((HttpServletResponse) response).sendRedirect(contextPath + "/Error/access-denied.xhtml");
            } else {

            }
        } catch (Exception e) {
            ((HttpServletResponse) response).sendRedirect(contextPath + "/Error/access-denied.xhtml");
        }

        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }

    public void destroy() {
        // Nothing to do here!
    }

}