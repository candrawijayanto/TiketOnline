package com.tugas.manpro.controller;

import java.util.List;

import javax.mail.MessagingException;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.model.Tiket;
import com.tugas.manpro.model.User;
import com.tugas.manpro.repository.AbsenRepository;
import com.tugas.manpro.service.BarcodeService;
import com.tugas.manpro.service.EmailService;
import com.tugas.manpro.service.EventService;
import com.tugas.manpro.service.TiketService;
import com.tugas.manpro.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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

    @Autowired
    private AbsenRepository absenRepository;

    @Autowired
    private BarcodeService barcodeService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/showAllEventTikets")
    public ModelAndView getAllTikets(int idEvent, String pesan) {
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
        mv.addObject("pesan", pesan);
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
    public ModelAndView getAllTikets(String pesan) {
        ModelAndView mv = new ModelAndView("/tiket/tiket.jsp");
        List<Tiket> tiket = tiketService.getAllTikets();
        mv.addObject("tiket", tiket);
        mv.addObject("pesan", pesan);
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
    public ModelAndView addTiket2(int userSelect[], int jumlahSaatIni, int idEvent, int kirimEmail) {
        ModelAndView mv = new ModelAndView("redirect:/showAllEventTikets?idEvent=" + idEvent);
        String pesan = "";
        Event event = eService.getEventById(idEvent);

        // forEach untuk ekstrak user yang dipilih
        for (int idUser : userSelect) {
            jumlahSaatIni += 1;
            User user = userService.getUserById(idUser);

            // untuk cek apakah tiket masih ada?
            if (jumlahSaatIni > event.getJml()) {
                pesan = "Tiket sudah habis, user " + user.getFirstName() + user.getLastName() + " tidak jadi terdaftar";
                System.err.println("Tiket sudah habis, user " + user + " tidak jadi terdaftar");
                mv.addObject("pesan", pesan);
                return mv;
            } else {
                // cek apakah user sudah terdaftar sebelumnya?
                if (tiketService.cekUser(idUser, idEvent) >= 1) {
                    pesan = "User dengan email" + user.getEmail() + " sudah terdaftar";
                    System.err.println("User: " + user + " sudah terdaftar");
                    jumlahSaatIni -= 1;
                } else {
                    // buat dan simpan tiket ke database
                    tiketService.save(event, user);

                    // buat barcode dari tiket yg disimpan
                    Tiket tiket = tiketService.getTiketByIdEventIdUser(idEvent, idUser);
                    FileSystemResource barcodeTiket = barcodeService.generate(String.valueOf(tiket.getIdTiket()));

                    if (kirimEmail == 1) {
                        // kirim barcode yg berhasil dibuat ke email user
                        emailService.sendTiketToEmail(user.getEmail(), barcodeTiket, user.getFirstName(), idEvent);
                    }
                    pesan += "berhasil tambah user: " + user.getEmail();
                    System.out.println("user " + user + " berhasil didaftarkan");
                }
            }
        }
        mv.addObject("pesan", pesan);
        return mv;
    }

    // untuk hapus tiket dari halaman event_tiket.jsp
    @GetMapping("/deleteEventTiket")
    public ModelAndView deleteEventTiket(int idTiket, int idEvent) {
        ModelAndView mv = new ModelAndView("redirect:/showAllEventTikets?idEvent=" + idEvent);
        // try catch untuk cek apakah tiket yg diapus itu beneran ada di DB?
        try {
            tiketService.deleteTiketByIdTiket(idTiket);
        } catch (EmptyResultDataAccessException e) {
            String pesan = "tiket dengan id: " + idTiket + " tidak ada / sudah dihapus bos!";
            System.err.println("tiket dengan id: " + idTiket + " tidak ada / sudah dihapus bos!");
            mv.addObject("pesan", pesan);
        }
        return mv;
    }

    // untuk hapus tiket dari halaman user_tiket.jsp
    @GetMapping("deleteUserTiket")
    public ModelAndView deleteUserTiket(int idTiket, int idUser) {
        ModelAndView mv = new ModelAndView("redirect:/showAllUserTiket?idUser=" + idUser);
        // try catch untuk cek apakah tiket yg diapus itu beneran ada di DB?
        try {
            tiketService.deleteTiketByIdTiket(idTiket);
        } catch (EmptyResultDataAccessException e) {
            String pesan = "tiket dengan id: " + idTiket + " tidak ada / sudah dihapus  bos!";
            System.err.println("tiket dengan id: " + idTiket + " tidak ada / sudah dihapus  bos!");
            mv.addObject("pesan", pesan);
        }
        return mv;
    }

    // untuk hapus tiket dari halaman tiket.jsp
    @GetMapping("deleteTiket")
    public ModelAndView deleteTiket(int idTiket) {
        ModelAndView mv = new ModelAndView("redirect:/showAllTikets");
        // try catch untuk cek apakah tiket yg diapus itu beneran ada di DB?
        try {
            tiketService.deleteTiketByIdTiket(idTiket);
        } catch (EmptyResultDataAccessException e) {
            String pesan = "tiket dengan id: " + idTiket + " tidak ada / sudah dihapus  bos!";
            System.err.println("tiket dengan id: " + idTiket + " tidak ada / sudah dihapus  bos!");
            mv.addObject("pesan", pesan);
        }
        return mv;
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

    // untuk hapus absen
    @GetMapping("/deleteAbsen")
    public ModelAndView deleteAbsen(int idAbsen) {
        ModelAndView mv = new ModelAndView("redirect:/showAllTikets");
        try {
            absenRepository.deleteById(idAbsen);
        } catch (EmptyResultDataAccessException e) {
            String pesan = "data absen untuk id " + idAbsen + " tidak ada / sudah dihapus bos!";
            System.out.println("data absen untuk id " + idAbsen + " tidak ada / sudah dihapus bos!");
            mv.addObject("pesan", pesan);
        }

        return mv;
    }

}