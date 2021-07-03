package com.tugas.manpro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.tugas.manpro.model.User;
import com.tugas.manpro.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/showAllUsers")
    public String showAllUsers(HttpSession session) {
        List<User> user = userService.getAllUsers();
        session.setAttribute("user", user);
        return "/user/user.jsp";
    }

    @RequestMapping("/showNewUserForm")
    public String showNewUserForm() {
        return "/user/new_user_form.jsp";
    }

    @RequestMapping("/showUpdateUserForm")
    public ModelAndView showUpdateUserForm(int idUser) {
        ModelAndView mv = new ModelAndView("/user/update_user_form.jsp");
        mv.addObject("user", userService.getUserById(idUser));
        return mv;
    }

    // method untuk tambah user
    @PostMapping("/addUser")
    public String addUser(User user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            System.out.println("Email " + user.getEmail() + " sudah dipakai, silahkan gunakan email yang lain!");
        } else {
            userService.addUser(user);
        }
        return "redirect:/showAllUsers";
    }

    // method untuk update/edit user
    @PostMapping("/updateUser")
    public String updateUser(User user) {
        System.out.println("hasil user dari form: " + user);
        if (userService.cekUpdateEmail(user.getEmail(), user.getIdUser())) {
            System.out.println("Email " + user.getEmail() + " sudah dipakai, silahkan gunakan email yang lain!");
        } else {
            // userService.addUser(user) bisa digunakan untuk update atau create user
            userService.addUser(user);
        }
        return "redirect:/showAllUsers";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(int idUser) {
        try {
            userService.deleteUserByIdUser(idUser);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("user dengan id " + idUser + " sudah tidak ada/sudah dihapus!");
        }
        return "redirect:/showAllUsers";
    }

}
