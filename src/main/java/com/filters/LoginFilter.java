/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filters;

import com.controler.login;
import java.io.IOException;
import javax.servlet.DispatcherType;
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
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/dashboard.xhtml", "/About/*", "/Education/*", "/Finans/*", "/News/*" , "/Messages/*", "/Profile/*",
    "/Statics/*", "/Tusers/*" , "/Users/*" , "/alarm/*" , "/mediaCenter/*" , "/package-exam/*" , "/Template/*", "/settings/*" , "/webSetting/*" , "/toterial/*", "/statice/*"}, dispatcherTypes = {DispatcherType.ERROR, DispatcherType.REQUEST})
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

       
        login loginBean = null;
        try {
            loginBean = (login) ((HttpServletRequest) request).getSession().getAttribute("login");
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa    " + loginBean.isIsLogin());
        } catch (Exception e) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa    ");
        }

   
        String contextPath = ((HttpServletRequest) request).getContextPath();
        try {
            if (loginBean == null || loginBean.isIsLogin() == false) {

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
