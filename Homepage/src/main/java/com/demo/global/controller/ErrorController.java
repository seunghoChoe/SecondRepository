package com.demo.global.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("errorController")
@RequestMapping("/error/*")
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
    private final String httpErrorPath = "error/httpError";

//    @GetMapping(value = "/400")
//    public String pageError400(HttpServletRequest request, Model model) {
//        logger.info("pageError400()");
//        handleErrorMsg(request, model, "400", "잘못된 요청입니다.");
//        return httpErrorPath;
//    }

    @GetMapping(value = "/403")
    public String pageError403(HttpServletRequest request, Model model) {
        logger.info("pageError403()");
        handleErrorMsg(request, model, "403", "페이지 접근 권한이 없습니다.");
        return httpErrorPath;
    }

    @GetMapping(value = "/404")
    public String pageError404(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("pageError404()");

        response.setStatus(HttpServletResponse.SC_OK);

        handleErrorMsg(request, model, "404", "요청한 페이지를 찾을 수 없습니다.");
        return httpErrorPath;
    }
//
//    @GetMapping(value = "/405")
//    public String pageError405(HttpServletRequest request, Model model) {
//        logger.info("pageError405()");
//        handleErrorMsg(request, model, "405", "요청된 메소드가 허용되지 않습니다.");
//        return httpErrorPath;
//    }
//
//    @GetMapping(value = "/500")
//    public String pageError500(HttpServletRequest request, Model model) {
//        logger.info("pageError500()");
//        handleErrorMsg(request, model, "500", "서버에 오류가 발생하였습니다.");
//        return httpErrorPath;
//    }
//
//    @GetMapping(value = "/503")
//    public String pageError503(HttpServletRequest request, Model model) {
//        logger.info("pageError503()");
//        handleErrorMsg(request, model, "503", "서비스를 이용할 수 없습니다.");
//        return httpErrorPath;
//    }

    private void handleErrorMsg(HttpServletRequest request, Model model, String code, String msg) {
        // pageErrorLog(request);
        model.addAttribute("code", code);
        model.addAttribute("msg", msg);
    }

    private void pageErrorLog(HttpServletRequest request) {
        logger.info("status_code : " + request.getAttribute("javax.servlet.error.status_code"));
        logger.info("exception_type : " + request.getAttribute("javax.servlet.error.exception_type"));
        logger.info("message : " + request.getAttribute("javax.servlet.error.message"));
        logger.info("request_uri : " + request.getAttribute("javax.servlet.error.request_uri"));
        logger.info("exception : " + request.getAttribute("javax.servlet.error.exception"));
        logger.info("servlet_name : " + request.getAttribute("javax.servlet.error.servlet_name"));
    }
}