package com.tugas.manpro.rest;

import java.util.List;

import com.tugas.manpro.model.Admin;
import com.tugas.manpro.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admin/")
public class AdminRest {

    private AdminService aService;

    public AdminRest() {
    }

    @Autowired
    public AdminRest(AdminService a) {
        this.aService = a;
    }

    @PostMapping
    public void addAdmin(@RequestBody Admin admin) {
        aService.addAdmin(admin);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return aService.getAdmin();
    }
}
