package com.tugas.manpro.controller;

import javax.servlet.http.HttpSession;

import com.tugas.manpro.service.TiketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    TiketService ts;

    @RequestMapping("/home")
    public String home(String nama, HttpSession session) {
        return "home.jsp";
    }

    @RequestMapping("/")
    public String index() {
        return "home.jsp";
    }

}