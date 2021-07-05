package com.tugas.manpro.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "absen")
public class Absen {

    @Id
    @Column(name = "idTiket")
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idTiket")
    private Tiket tiket;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "absen")
    private LocalDate absen;

    public Absen(int id, Tiket tiket, LocalDate absen) {
        this.id = id;
        this.tiket = tiket;
        this.absen = absen;
    }

    public Absen(Tiket tiket) {
        this.tiket = tiket;
    }

    public Absen(Tiket tiket, LocalDate absen) {
        this.tiket = tiket;
        this.absen = absen;
    }

    public Absen() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tiket getTiket() {
        return tiket;
    }

    public void setTiket(Tiket tiket) {
        this.tiket = tiket;
    }

    public LocalDate getAbsen() {
        return absen;
    }

    public void setAbsen(LocalDate absen) {
        this.absen = absen;
    }

}
