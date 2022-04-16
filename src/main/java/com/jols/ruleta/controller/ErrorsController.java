package com.jols.ruleta.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorsController implements ErrorController {
    public static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "error";
    }
}
