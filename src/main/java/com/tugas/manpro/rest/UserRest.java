package com.tugas.manpro.rest;

import java.util.List;

import com.tugas.manpro.model.User;
import com.tugas.manpro.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user/")
public class UserRest {
    
    private UserService service;

    @Autowired
    public UserRest(UserService service){
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }
}
