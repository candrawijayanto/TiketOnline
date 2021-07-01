package com.tugas.manpro.controller;

import java.util.List;

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

    @GetMapping("/showAllTikets")
    public ModelAndView getAllTikets(int idEvent) {
        ModelAndView mv = new ModelAndView("tiket.jsp");
        List<Tiket> tikets = tiketService.getTiketByIdEvent(idEvent);
        int limitTiketEvent = eService.getLimitEvent(idEvent);
        int jumlahSaatIni = tiketService.currentAmount(idEvent);

        mv.addObject("jumlahSaatIni", jumlahSaatIni);
        mv.addObject("limitTiketEvent", limitTiketEvent);
        mv.addObject("tiket", tikets);
        return mv;
    }

    @GetMapping("/showAllUserTiket")
    public ModelAndView getAllUserTiket (int idUser){
        ModelAndView mv = new ModelAndView("user_tiket.jsp");
        User user = userService.getUserById(idUser);
        List<Tiket> tiket = tiketService.getTiketByUser(user);
        mv.addObject("userTiket", tiket);
        return mv;
    }

    @GetMapping("showNewTiketForm")
    public ModelAndView showNewTiketForm(int id) {
        ModelAndView mv = new ModelAndView("tiket_form.jsp");
        List<Tiket> tiket = tiketService.getTiketByIdEvent(id);
        mv.addObject("tiket", tiket);
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

}