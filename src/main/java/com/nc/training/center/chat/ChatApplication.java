package com.nc.training.center.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ChatApplication {
    private final static Logger LOGGER = LoggerFactory.getLogger(ChatApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(ChatApplication.class, args);
    }

//    пример логирования
    @PostConstruct
    public void foo(){
        LOGGER.info("пример логирования");
    }

}
