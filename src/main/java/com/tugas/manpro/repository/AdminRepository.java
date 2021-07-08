package com.tugas.manpro.repository;

import com.tugas.manpro.model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
 
   public Admin findByName(String name);
}
