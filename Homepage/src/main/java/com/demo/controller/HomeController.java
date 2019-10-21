package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 홈 컨트롤러 클래스 (GET/POST 메소드만 사용한다.)
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * @GET: 메인 (테스트 통과)
     */
    @GetMapping("/")
    public String homeGET() {
        logger.info("homeGET()");

        return "home";
    }

    /**
     * @GET: 테스트 (테스트 통과)
     */
    @GetMapping("/test")
    public String testGET() {
        logger.info("testGET()");

        return "test";
    }
}