package com.tugas.manpro.controller;

import com.tugas.manpro.service.TiketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    TiketService ts;

    @RequestMapping("/home")
    public ModelAndView home(String pesan) {
        ModelAndView mv = new ModelAndView("home.jsp");
        mv.addObject("pesan", pesan);
        return mv;
    }

    @RequestMapping("/")
    public String index() {
        return "home.jsp";
    }

}