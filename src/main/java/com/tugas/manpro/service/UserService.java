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

    public UserService() {
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(int idUser) {
        return userRepository.findByIdUser(idUser);
    }

    // method untuk cek apakah email sdh pernah dipakai?
    // untuk lebih detail cek UserRepository.java -> findByEmail(...);
    // digunakan pada halaman new_user_form.jsp
    // digunakan pada UserController.java -> addUser();
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    // method untuk cek apakah email sdh pernah dipakai?
    // untuk lebih detail cek UserReposotory.java -> cekUpdateEmail(...);
    // digunakan pada halaman update_user_form.jsp
    // digunakan pada UserController.java -> updateUser();
    public boolean cekUpdateEmail(String email, int idUser) {
        // 0 atau false berarti email belum pernah dipakai di DB
        if (userRepository.cekUpdateEmail(email, idUser) == 0) {
            return false;
        }
        // 1 atau true berarti email sudah pernah dipakai, silahkan gunakan email lain
        return true;
    }

    public void deleteUserByIdUser(int idUser) {
        userRepository.deleteById(idUser);
    }

}
