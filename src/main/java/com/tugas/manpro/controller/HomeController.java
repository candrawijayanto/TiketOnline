package com.tugas.manpro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private EventService eService;

    @RequestMapping("/home")
    public String home(String nama, HttpSession session) {
        List<Event> event = eService.getAllEvents();
        session.setAttribute("name", nama);
        session.setAttribute("event", event);
        return "home.jsp";
    }

    @RequestMapping("/")
    public String index() {
        return "home.jsp";
    }

}