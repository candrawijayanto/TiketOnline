package com.tugas.manpro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdmin")
    private int idAdmin;
    private String name;
    private String password;

    public Admin() {
    }

    public Admin(int id_admin, String name, String password) {
        this.idAdmin = id_admin;
        this.name = name;
        this.password = password;
    }

    public int getId_admin() {
        return idAdmin;
    }

    public void setId_admin(int id_admin) {
        this.idAdmin = id_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [id_admin=" + idAdmin + ", name=" + name + ", password=" + password + "]";
    }

}
