package com.tugas.manpro.service;

import java.util.List;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.model.Tiket;
import com.tugas.manpro.model.User;
import com.tugas.manpro.repository.TiketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TiketService {

    @Autowired
    TiketRepository tiketRepository;

    public List<Tiket> getAllTikets() {
        return (List<Tiket>) tiketRepository.findAll();
    }

    public List<Tiket> getTiketsByEvent(Event event) {
        return tiketRepository.findByEvent(event);
    }

    public List<Tiket> getTiketByIdEvent(int idEvent) {
        return tiketRepository.findByIdEvent(idEvent);
    }

    public Tiket getTiketByIdTiket(int idTiket) {
        return tiketRepository.findByIdTiket(idTiket);
    }

    public List<Tiket> getTiketByUser(User user) {
        return tiketRepository.findByUser(user);
    }

    public int currentAmount(int idEvent) {
        return tiketRepository.currentAmount(idEvent);
    }

    public void addTiket(int idEvent) {
        tiketRepository.addTiket(idEvent);
    }

    public void save(Event event, User user) {
        tiketRepository.save(new Tiket(event, user));
    }

    public void deleteTiketByIdTiket(int idTiket) {
        tiketRepository.deleteById(idTiket);
    }

}
