package com.tugas.manpro.rest;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.ws.rs.core.MediaType;

import com.tugas.manpro.model.Absen;
import com.tugas.manpro.model.Tiket;
import com.tugas.manpro.repository.AbsenRepository;
import com.tugas.manpro.service.TiketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/tiket")
public class TiketRest {

    @Autowired
    TiketService tiketService;

    @Autowired
    AbsenRepository absenRepository;

    @GetMapping("/{idTiket}")
    public Tiket getTiket(@PathVariable("idTiket") int idTiket) {
        Tiket tiket = tiketService.getTiketByIdTiket(idTiket);
        return tiket;
    }

    // REST untuk android
    @GetMapping(value = "/android/{idTiket}", produces = MediaType.TEXT_PLAIN)
    public String getAbsen(@PathVariable("idTiket") int idTiket) {
        // ambil data tiket
        Tiket tiket = tiketService.getTiketByIdTiket(idTiket);

        // cek apakah ada data?
        if (tiket != null) {
            // try catch untuk cek apakah sudah melakukan absen sebelumnya?
            try {
                absenRepository.save(new Absen(tiket, java.time.LocalDate.now()));
            } catch (EntityExistsException | DataIntegrityViolationException e) {
                return "sudah absen";
            }
        } else {
            return "tiket tidak ada!";
        }
        return "berhasil";
    }

    @GetMapping("/")
    public List<Tiket> getAllTiket() {
        List<Tiket> tikets = tiketService.getAllTikets();
        return tikets;
    }
}
