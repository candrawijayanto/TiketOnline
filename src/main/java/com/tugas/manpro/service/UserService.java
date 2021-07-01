package com.tugas.manpro.service;

import java.util.List;

import com.tugas.manpro.model.User;
import com.tugas.manpro.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserService(){}

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(int idUser) {
        return userRepository.findByIdUser(idUser);
    }


}
