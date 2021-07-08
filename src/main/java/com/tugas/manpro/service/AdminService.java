package com.tugas.manpro.service;

import java.util.List;

import com.tugas.manpro.model.Admin;
import com.tugas.manpro.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminService {
    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository ar) {
        this.adminRepository = ar;
    }

    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Admin getAdminByName(String name){
        return adminRepository.findByName(name);
    }
}
