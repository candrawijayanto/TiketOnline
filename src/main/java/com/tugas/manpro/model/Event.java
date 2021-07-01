package com.tugas.manpro.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvent")
    private int idEvent;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String location;
    private int jml;

    @OneToMany(mappedBy = "event")
    private List<Tiket> tiket = new ArrayList<>();

    public Event(int idEvent, String name, LocalDate date, String location, int jml) {
        this.idEvent = idEvent;
        this.name = name;
        this.date = date;
        this.location = location;
        this.jml = jml;
    }

    public Event(){}

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getJml() {
        return jml;
    }

    public void setJml(int jml) {
        this.jml = jml;
    }

    public List<Tiket> getTiket() {
        return tiket;
    }

    public void setTiket(List<Tiket> tiket) {
        this.tiket = tiket;
    }
    
}
