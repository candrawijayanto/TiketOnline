package com.tugas.manpro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @ManyToMany(mappedBy = "tiket")
    private List<User> user = new ArrayList<>();

    public Tiket() {
    }

    public Tiket(int idTiekt, Event event) {
        this.idTiket = idTiekt;
        this.event = event;
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

}
