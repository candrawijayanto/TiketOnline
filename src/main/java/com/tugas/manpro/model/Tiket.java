package com.tugas.manpro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tiket")
public class Tiket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTiket")
    private int idTiket;

    @ManyToOne
    @JoinColumn(name = "idEvent")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "idUser")
    User user;


    @JsonIgnore
    @OneToOne(mappedBy = "tiket")
    @PrimaryKeyJoinColumn
    private Absen absen;

    

    public Absen getAbsen() {
        return absen;
    }

    public void setAbsen(Absen absen) {
        this.absen = absen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tiket() {
    }

    public Tiket(int idTiekt, Event event) {
        this.idTiket = idTiekt;
        this.event = event;
    }

    public Tiket(Event event, User user) {
        this.event = event;
        this.user = user;
    }

    public int getIdTiket() {
        return idTiket;
    }

    public void setIdTiekt(int idTiekt) {
        this.idTiket = idTiekt;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Tiket [idTiket=" + idTiket + "]";
    }

}
