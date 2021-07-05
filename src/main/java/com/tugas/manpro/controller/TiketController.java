package com.tugas.manpro.controller;

import java.util.List;

import javax.mail.MessagingException;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.model.Tiket;
import com.tugas.manpro.model.User;
import com.tugas.manpro.service.EventService;
import com.tugas.manpro.service.TiketService;
import com.tugas.manpro.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TiketController {

    @Autowired
    private TiketService tiketService;

    @Autowired
    private EventService eService;

    @Autowired
    private UserService userService;

    @GetMapping("/showAllEventTikets")
    public ModelAndView getAllTikets(int idEvent) {
        ModelAndView mv = new ModelAndView("/event/event_tiket.jsp");
        List<Tiket> tikets = tiketService.getTiketByIdEvent(idEvent);
        int limitTiketEvent = eService.getLimitEvent(idEvent);
        int jumlahSaatIni = tiketService.currentAmount(idEvent);

        if (tikets.size() <= 0) {
            tikets = null;
            Event event = eService.getEventById(idEvent);
            mv.addObject("event", event);
        }
        mv.addObject("idEvent", idEvent);
        mv.addObject("jumlahSaatIni", jumlahSaatIni);
        mv.addObject("limitTiketEvent", limitTiketEvent);
        mv.addObject("tiket", tikets);
        return mv;
    }

    @GetMapping("/showAllUserTiket")
    public ModelAndView getAllUserTiket(int idUser) {
        ModelAndView mv = new ModelAndView("/user/user_tiket.jsp");
        User user = userService.getUserById(idUser);
        mv.addObject("user", user);
        return mv;
    }

    @GetMapping("/showAllTikets")
    public ModelAndView getAllTikets() {
        ModelAndView mv = new ModelAndView("/tiket/tiket.jsp");
        List<Tiket> tiket = tiketService.getAllTikets();
        mv.addObject("tiket", tiket);

        return mv;
    }

    // Tambah tiket dari menu manajemen event
    // dipakai di event_tiket.jsp
    @GetMapping("showNewTiketForm")
    public ModelAndView showNewTiketForm(int idEvent) {
        ModelAndView mv = new ModelAndView("/tiket/new_tiket_form.jsp");
        List<User> user = userService.getAllUsers();
        int jumlahSaatIni = tiketService.currentAmount(idEvent);

        mv.addObject("jumlahSaatIni", jumlahSaatIni);
        mv.addObject("user", user);
        mv.addObject("idEvent", idEvent);
        return mv;
    }

    @GetMapping("showNewTiketMenu")
    public ModelAndView showNewTiketForm2() {
        ModelAndView mv = new ModelAndView("/tiket/new_tiket_menu.jsp");
        mv.addObject("event", eService.getAllEvents());
        return mv;
    }

    // udah nggak kepakek keknya, ganti ke addTiket2()
    @PostMapping("/addTiket")
    public String addTiket(int idEvent, int jumlah) {
        int hasil = tiketService.currentAmount(idEvent) + jumlah;

        if (hasil > eService.getLimitEvent(idEvent)) {
            System.out.println("Tiket sudah habis");
        } else {
            for (int i = 0; i < jumlah; i++) {
                tiketService.addTiket(idEvent);
            }
        }
        return "redirect:/showAllTikets?idEvent=" + idEvent;
    }

    // method untuk tambah tiket
    @PostMapping("/addTiket2")
    public String addTiket2(int userSelect[], int jumlahSaatIni, int idEvent) {
        Event event = eService.getEventById(idEvent);

        // forEach untuk ekstrak user yang dipilih
        for (int idUser : userSelect) {
            jumlahSaatIni += 1;
            User user = userService.getUserById(idUser);

            // untuk cek apakah tiket masih ada?
            if (jumlahSaatIni > event.getJml()) {
                System.err.println("Tiket sudah habis, user " + user + " tidak jadi terdaftar");
                return "redirect:/showAllEventTikets?idEvent=" + idEvent;
            } else {
                // cek apakah user sudah terdaftar sebelumnya?
                if (tiketService.cekUser(idUser, idEvent) >= 1) {
                    System.err.println("User: " + user + " sudah terdaftar");
                    jumlahSaatIni -= 1;
                } else {
                    tiketService.save(event, user);
                    System.out.println("user " + user + " berhasil didaftarkan");
                }
            }
        }

        return "redirect:/showAllEventTikets?idEvent=" + idEvent;
    }

    // untuk hapus tiket dari halaman event_tiket.jsp
    @GetMapping("/deleteEventTiket")
    public String deleteEventTiket(int idTiket, int idEvent) {
        // try catch untuk cek apakah tiket yg diapus itu beneran ada di DB?
        try {
            tiketService.deleteTiketByIdTiket(idTiket);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("tiket dengan id: " + idTiket + " tidak ada / sudah dihapus bos!");
        }
        return "redirect:/showAllEventTikets?idEvent=" + idEvent;
    }

    // untuk hapus tiket dari halaman user_tiket.jsp
    @GetMapping("deleteUserTiket")
    public String deleteUserTiket(int idTiket, int idUser) {
        // try catch untuk cek apakah tiket yg diapus itu beneran ada di DB?
        try {
            tiketService.deleteTiketByIdTiket(idTiket);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("tiket dengan id: " + idTiket + " tidak ada / sudah dihapus  bos!");
        }
        return "redirect:/showAllUserTiket?idUser=" + idUser;
    }

    // untuk hapus tiket dari halaman tiket.jsp
    @GetMapping("deleteTiket")
    public String deleteTiket(int idTiket) {
        // try catch untuk cek apakah tiket yg diapus itu beneran ada di DB?
        try {
            tiketService.deleteTiketByIdTiket(idTiket);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("tiket dengan id: " + idTiket + " tidak ada / sudah dihapus  bos!");
        }
        return "redirect:/showAllTikets";
    }

    // controller untuk memulai automatic process (tugas tambahan)
    @GetMapping("/automaticEmail")
    public String automaticEmail() {
        try {
            tiketService.job();
        } catch (InterruptedException | MessagingException e) {
            e.printStackTrace();
        }

        return "home.jsp";
    }

}