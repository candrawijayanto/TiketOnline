package com.tugas.manpro.controller;

import java.util.List;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/showNewEventForm")
    public ModelAndView showNewEventForm() {
        ModelAndView mv = new ModelAndView("/event/new_event_form.jsp");
        return mv;
    }

    @GetMapping("/showUpdateEventForm")
    public ModelAndView showUpdateEventForm(int id) {
        ModelAndView mv = new ModelAndView("/event/update_event_form.jsp");
        Event event = eventService.getEventById(id);
        mv.addObject("event", event);
        return mv;
    }

    @GetMapping("/showAllEvents")
    public ModelAndView showAllEvents(String pesan) {
        ModelAndView mv = new ModelAndView("/event/event.jsp");
        List<Event> events = eventService.getAllEvents();
        mv.addObject("event", events);
        mv.addObject("pesan", pesan);
        return mv;
    }

    // method untuk tambah ATAU edit event
    @PostMapping("/addEvent")
    public String addEvent(Event event) {
        System.out.println("Data yg ditambah: " + event);

        // eventService.addEvent(event); bisa untuk update atau create event
        eventService.addEvent(event);
        return "redirect:/showAllEvents";
    }

    @RequestMapping("/deleteEvent")
    public ModelAndView deleteEvent(int idEvent) {
        ModelAndView mv = new ModelAndView("redirect:/showAllEvents");
        String pesan = "";
        // try catch untuk cek apakah event yg diapus itu beneran ada di DB?
        try {
            eventService.deleteEvent(idEvent);
            pesan = "Event dengan id " + idEvent + " berhasil dihapus!"; 
        } catch (EmptyResultDataAccessException e) {
            pesan = "Event dengan id " + idEvent + " tidak ada/sudah dihapus!";
            mv.addObject("pesan", pesan);
            System.err.println(pesan);
        }
        mv.addObject("pesan", pesan);
        return mv;
    }

}
