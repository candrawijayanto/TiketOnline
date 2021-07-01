package com.tugas.manpro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.tugas.manpro.model.User;
import com.tugas.manpro.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/showAllUsers")
    public String showAllUsers(HttpSession session) {
        List<User> user = userService.getAllUsers();
        session.setAttribute("user", user);
        return "user.jsp";
    }

}
