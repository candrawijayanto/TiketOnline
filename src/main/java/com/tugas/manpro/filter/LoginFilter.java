package com.tugas.manpro.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// @Component
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest hRequest = (HttpServletRequest) request;
        HttpServletResponse hResponse = (HttpServletResponse) response;

        HttpSession session = hRequest.getSession();

        String path = hRequest.getServletPath();
        String userSession = (String) session.getAttribute("userSession");

        if (path.equals("/showLoginForm") || path.equals("/login") || userSession != null) {
            chain.doFilter(request, response);
        } else {
            hResponse.sendRedirect("/showLoginForm");
        }

    }

}
