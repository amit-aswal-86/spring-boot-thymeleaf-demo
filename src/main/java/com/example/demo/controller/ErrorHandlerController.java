package com.example.demo.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlerController implements ErrorController {

    final String viewName = "error";

    public ErrorHandlerController() {}

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
        
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return viewName + "/404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return viewName + "/500";
            }
        }
        return viewName + "/index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}