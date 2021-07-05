package com.tugas.manpro.repository;

import java.util.List;

import com.tugas.manpro.model.Event;
import com.tugas.manpro.model.Tiket;
import com.tugas.manpro.model.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TiketRepository extends CrudRepository<Tiket, Integer> {

    List<Tiket> findAll();

    List<Tiket> findByEvent(Event event);

    @Query("FROM Tiket WHERE idEvent=?1")
    List<Tiket> findByIdEvent(int idEvent);

    Tiket findByIdTiket(int idTiket);

    List<Tiket> findByUser(User user);

    // untuk menhitung jumlah tiket event yang sudah terdaftar/dibuat
    @Query("SELECT COUNT(*) FROM Tiket WHERE idEvent=?1")
    public int currentAmount(int idEvent);

    @Transactional
    @Modifying
    @Query(value = "insert into tiket(idEvent) select :idEvent", nativeQuery = true)
    public void addTiket(@Param("idEvent") int idEvent);

    //cek untuk cegah 1 event memiliki lebih 1 peserta yg sama
    @Query(value = "SELECT COUNT(*) FROM Tiket WHERE idUser=?1 AND idEvent=?2")
    public int cekUser(int idUser, int idEvent);


}