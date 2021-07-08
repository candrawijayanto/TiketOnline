package com.tugas.manpro.controller;

import javax.servlet.http.HttpSession;

import com.tugas.manpro.model.Admin;
import com.tugas.manpro.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginLogoutController {

    @Autowired
    AdminService adminService;

    @GetMapping("/showLoginForm")
    public ModelAndView showLoginForm(String pesan) {
        ModelAndView mv = new ModelAndView("login_form.jsp");
        mv.addObject("pesan", pesan);
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(String username, String password, HttpSession session) {
        Admin admin = adminService.getAdminByName(username);
        ModelAndView mv = new ModelAndView();
        String pesan = "";

        if (admin == null) {
            pesan = "Username Salah!";
            mv.addObject("pesan", pesan);
        } else if (admin.getPassword().equals(password)) {
            session.setAttribute("userSession", admin.getName());
            pesan = "berhasil login";
            mv.addObject("pesan", pesan);
            mv.setViewName("redirect:/home");
            return mv;
        } else {
            pesan = "Password Salah!";
            System.out.println("password salah");
        }
        mv.setViewName("redirect:/showLoginForm");
        mv.addObject("pesan", pesan);
        return mv;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userSession");
        return "redirect:/showLoginForm";
    }
}
