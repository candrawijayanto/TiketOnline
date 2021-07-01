package com.tugas.manpro.repository;

import com.tugas.manpro.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

   public User findByIdUser(int idUser);
}
