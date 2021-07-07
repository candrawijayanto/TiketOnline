package com.tugas.manpro.service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tugas.manpro.model.Event;
import com.tugas.manpro.model.Tiket;
import com.tugas.manpro.model.User;
import com.tugas.manpro.repository.TiketRepository;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TiketService {

    @Autowired
    TiketRepository tiketRepository;

    @Autowired
    EmailService emailService;

    public List<Tiket> getAllTikets() {
        return (List<Tiket>) tiketRepository.findAll();
    }

    public List<Tiket> getTiketsByEvent(Event event) {
        return tiketRepository.findByEvent(event);
    }

    public List<Tiket> getTiketByIdEvent(int idEvent) {
        return tiketRepository.findByIdEvent(idEvent);
    }

    public Tiket getTiketByIdTiket(int idTiket) {
        return tiketRepository.findByIdTiket(idTiket);
    }

    public List<Tiket> getTiketByUser(User user) {
        return tiketRepository.findByUser(user);
    }

    public int currentAmount(int idEvent) {
        return tiketRepository.currentAmount(idEvent);
    }

    public void addTiket(int idEvent) {
        tiketRepository.addTiket(idEvent);
    }

    public void save(Event event, User user) {
        tiketRepository.save(new Tiket(event, user));
    }

    public void deleteTiketByIdTiket(int idTiket) {
        tiketRepository.deleteById(idTiket);
    }

    public int cekUser(int idUser, int idEvent) {
        return tiketRepository.cekUser(idUser, idEvent);
    }

    //cari tiket dari idEvent && idUser
    public Tiket getTiketByIdEventIdUser(int idEvent, int idUser){
        return tiketRepository.findByIdEventAndIdUser(idEvent, idUser);
    }

    public void tiketJsonToXml() {
        // buat variabel penampung
        String stringJSON;
        String hasilXML;

        // fetch seluruh data
        List<Tiket> tiket = tiketRepository.findAll();

        // convert data (List) menjadi Map
        Map<String, ArrayList<Tiket>> map = new HashMap<>();
        map.put("tiket", (ArrayList<Tiket>) tiket);

        // buat JSON Object Mapper + register modul java time
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            // buat JSON-String
            stringJSON = mapper.writeValueAsString(map);

            // buat JSON-Object dari JSON-String
            JSONObject json = new JSONObject(stringJSON);

            // convert JSON-Object menjadi XML
            hasilXML = XML.toString(json);

            // simpan XML ke file dalam direktori
            FileWriter fileWriter = new FileWriter("./coba.xml");
            fileWriter.write("<daftarTiket>" + hasilXML + "</daftarTiket>");
            fileWriter.close();

            // mapper.writeValue(new File("./coba.json"), tiket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 4000L)
    public void job() throws InterruptedException, MessagingException {
        // buat file xml
        tiketJsonToXml();

        // muat file xml yang telah dibuat
        File file = new File("./coba.xml");

        // kirim file xml ke email
        // try{
        //     emailService.sendEmailWithAtt("candrawijayanto56@gmail.com", "ini body atau isi email", "ini subjek", "./coba.xml");
        // } catch(MailException e) {
        //     System.out.println("Email Gagal Dikirim!");
        // }

        // hapus file yg berhasil dikirim
        Thread.sleep(1000L);
        if (file.delete()) {
            System.out.println("File Terkirim!");
        } else {
            System.out.println("Error bosku");
        }
    }

}
