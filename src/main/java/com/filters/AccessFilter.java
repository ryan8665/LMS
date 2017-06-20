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
@WebFilter(filterName = "AccessFilter", urlPatterns = {"/Messages/sms.xhtml", "/webSetting/*" , "/Users/s-teacher.xhtml", "/Education/group.xhtml", "/Education/course.xhtml", "/Finans/finans.xhtml", "/settings/setting.xhtml" , "/package-exam/package.xhtml"  }, dispatcherTypes = {DispatcherType.ERROR, DispatcherType.REQUEST})

public class AccessFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

       
        login loginBean2 = null;
        try {
            loginBean2 = (login) ((HttpServletRequest) request).getSession().getAttribute("login");
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbb    " + loginBean2.isIsAdmin());
        } catch (Exception e) {
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbb    ");
        }

       
        String contextPath = ((HttpServletRequest) request).getContextPath();
        try {
            if (loginBean2 == null || loginBean2.isIsAdmin() == false) {

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
