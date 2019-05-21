package com.nc.training.center.chat.controllers;


import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        if(e.getClass().equals(LockedException.class)){
            httpServletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            httpServletResponse.setHeader("Location", "/login?msg=denied");
            httpServletResponse.sendRedirect("/login?msg=denied");
            return;
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        httpServletResponse.setHeader("Location", "/login?msg=failure");
        httpServletResponse.sendRedirect("/login?msg=failure");
    }
}