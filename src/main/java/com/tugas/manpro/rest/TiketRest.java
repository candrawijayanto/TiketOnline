package com.tugas.manpro.rest;

import com.tugas.manpro.model.Tiket;
import com.tugas.manpro.service.TiketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/tiket/")
public class TiketRest {

    @Autowired
    TiketService tiketService;

    @GetMapping("/{idTiket}")
    public Tiket getTiket(@PathVariable("idTiket") int idTiket) {
        Tiket tiket = tiketService.getTiketByIdTiket(idTiket);
        System.out.println("hasil rest: " + tiket);
        return tiket;
    }
}
