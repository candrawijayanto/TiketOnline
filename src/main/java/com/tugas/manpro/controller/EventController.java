package com.tugas.manpro.controller;

import java.util.List;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView showNewEventForm(){
        ModelAndView mv = new ModelAndView("new_event_form.jsp");
        return mv;
    }

    @GetMapping("/showUpdateEventForm")
    public ModelAndView showUpdateEventForm(int id){
        ModelAndView mv = new ModelAndView("update_event_form.jsp");
        Event event = eventService.getEventById(id); 
        mv.addObject("event", event);
        return mv;
    }

    @GetMapping("/showAllEvents")
    public ModelAndView showAllEvents() {
        ModelAndView mv = new ModelAndView("event.jsp");
        List<Event> events = eventService.getAllEvents();
        mv.addObject("event", events);
        return mv;
    }

    @PostMapping("/addEvent")
    public String addEvent(Event event) {
        System.out.println("Data yg ditambah: " + event);
        eventService.addEvent(event);
        return "redirect:/showAllEvents";
    }

    @RequestMapping("/deleteEvent")
    public String deleteEvent(int id) {
        System.out.println("data yang dihapus: " + id);
        eventService.deleteEvent(id);
        return "redirect:/showAllEvents";
    }

}