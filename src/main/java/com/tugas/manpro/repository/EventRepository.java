package com.tugas.manpro.repository;

import com.tugas.manpro.model.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT jml FROM Event WHERE idEvent=?1")
    public int limitTiket(int idEvent);

}
