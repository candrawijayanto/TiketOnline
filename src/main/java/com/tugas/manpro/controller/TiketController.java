package com.tugas.manpro.controller;

import java.util.List;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.model.Tiket;
import com.tugas.manpro.model.User;
import com.tugas.manpro.service.EventService;
import com.tugas.manpro.service.TiketService;
import com.tugas.manpro.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/addTiket2")
    public String addTiket2(int userSelect[], int jumlahSaatIni, int idEvent) {
        int hasil = tiketService.currentAmount(idEvent) + jumlahSaatIni;

        Event event = eService.getEventById(idEvent);

        if (hasil > eService.getLimitEvent(idEvent)) {
            System.out.println("Tiket sudah habis");
        } else {
            for (int idUser : userSelect) {
                User user = userService.getUserById(idUser);
                tiketService.save(event, user);
            }
        }
        return "redirect:/showAllEventTikets?idEvent=" + idEvent;
    }

    @GetMapping("/deleteTiket")
    public String deleteTiket(int idTiket, int idEvent){
        tiketService.deleteTiketByIdTiket(idTiket);
        return "redirect:/showAllEventTikets?idEvent=" + idEvent;
    }
}