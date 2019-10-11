package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

  public final String viewDir = "home";

  @RequestMapping("/")
  public String index() {
    return viewDir + "/index";
  }
}
