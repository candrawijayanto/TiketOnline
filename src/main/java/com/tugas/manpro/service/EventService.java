package com.tugas.manpro.service;

import java.util.List;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventService() {
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(int idEvent) {
        return eventRepository.getById(idEvent);
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }

    public int getLimitEvent(int idEvent){
        return eventRepository.limitTiket(idEvent);
    }

}
