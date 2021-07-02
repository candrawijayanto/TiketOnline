package com.tugas.manpro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(String nama, HttpSession session) {
        return "home.jsp";
    }

    @RequestMapping("/")
    public String index() {
        return "home.jsp";
    }

}