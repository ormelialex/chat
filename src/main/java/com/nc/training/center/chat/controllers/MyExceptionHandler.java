package com.nc.training.center.chat.controllers;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    public ModelAndView handleError(HttpServletRequest req, DuplicateKeyException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "логин занят, придумай другой");
        mav.addObject("err", true);
        mav.setViewName("registration");
        return mav;
    }
}
