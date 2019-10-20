package com.demo.global.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("indexController")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = "/")
    public String getIndex() throws Exception {
        logger.info("getIndex()");

        return "index";
    }

    @PostMapping(value = "/")
    public String postIndex() throws Exception {
        logger.info("postIndex()");

        return "index";
    }
}