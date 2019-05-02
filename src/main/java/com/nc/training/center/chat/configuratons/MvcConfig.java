package com.nc.training.center.chat.configuratons;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

public class MvcConfig {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
