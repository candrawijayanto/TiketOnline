package com.tugas.manpro.rest;

import java.util.List;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/event/")
public class EventRest {

    private EventService eventService;

    @Autowired
    public EventRest(EventService eventSerice){
        this.eventService = eventSerice;
    }

    public EventRest(){}

    @PostMapping
    public Event addEvent(@RequestBody Event event){
        System.out.println(event);
        eventService.addEvent(event);
        return event;
    }

    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

}
