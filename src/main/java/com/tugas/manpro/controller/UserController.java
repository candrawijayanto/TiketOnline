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
    public ModelAndView showAllUsers(HttpSession session, String pesan) {
        ModelAndView mv = new ModelAndView("/user/user.jsp");
        List<User> user = userService.getAllUsers();
        session.setAttribute("user", user);
        mv.addObject("pesan", pesan);
        return mv;
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
    public ModelAndView addUser(User user) {
        ModelAndView mv = new ModelAndView("redirect:/showAllUsers");
        // cek apakai email sudah pernah digunakan?
        // null berarti belum pernah dipakai, !null berarti sudah pernah dipakai
        if (userService.getUserByEmail(user.getEmail()) != null) {
            String pesan = "Email " + user.getEmail() + " sudah dipakai, silahkan gunakan email yang lain!";
            System.out.println("Email " + user.getEmail() + " sudah dipakai, silahkan gunakan email yang lain!");
            mv.addObject("pesan", pesan);
        } else {
            userService.addUser(user);
        }
        return mv;
    }

    // method untuk update/edit user
    @PostMapping("/updateUser")
    public ModelAndView updateUser(User user) {
        ModelAndView mv = new ModelAndView("redirect:/showAllUsers");
        // cek apakah email sudah pernah digunakan?
        // true berarti sudah dipakai, false berarti blm pernah dipakai
        if (userService.cekUpdateEmail(user.getEmail(), user.getIdUser())) {
            String pesan = "Email " + user.getEmail() + " sudah dipakai, silahkan gunakan email yang lain!";
            System.out.println("Email " + user.getEmail() + " sudah dipakai, silahkan gunakan email yang lain!");
            mv.addObject("pesan", pesan);
        } else {
            // userService.addUser(user) bisa digunakan untuk update atau create user
            userService.addUser(user);
        }
        return mv;
    }

    @GetMapping("/deleteUser")
    public ModelAndView deleteUser(int idUser) {
        ModelAndView mv = new ModelAndView("redirect:/showAllUsers");
        // try catch untuk cek apakah user yg diapus itu beneran ada di DB?
        try {
            userService.deleteUserByIdUser(idUser);
        } catch (EmptyResultDataAccessException e) {
            String pesan = "user dengan id " + idUser + " sudah tidak ada/sudah dihapus!";
            System.err.println("user dengan id " + idUser + " sudah tidak ada/sudah dihapus!");
            mv.addObject("pesan", pesan);
        }
        return mv;
    }

}
