package com.tugas.manpro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private int idUser;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    private String email;

    @OneToMany
    @JoinTable(name = "userTiket", joinColumns = @JoinColumn(name = "idUser", referencedColumnName = "idUser"), inverseJoinColumns = @JoinColumn(name = "idTiket", referencedColumnName = "idTiket"))
    private List<Tiket> tiket = new ArrayList<>();

    public List<Tiket> getTiket() {
        return tiket;
    }

    public void setTiket(List<Tiket> tiket) {
        this.tiket = tiket;
    }

    public User() {
    }

    public User(int idUser, String firstName, String lastName, String email) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
