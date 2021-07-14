package com.tugas.manpro.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.tugas.manpro.model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EventService eventService;

    public void sendSimpleEmail(String toEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("vieririzki19@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail send..");
    }

    public void sendEmailWithAtt(String toEmail, String body, String subject, String att) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("vieririzki19@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(att));

        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

        mailSender.send(mimeMessage);
        System.out.println("Email Terkirim...");
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendTiketToEmail(String toEmail, FileSystemResource barcodeTiket, String userName, int idEvent) {
        //ambil detail event
        Event e = eventService.getEventById(idEvent);

        // buat class untuk email message
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // clas pembantu
        MimeMessageHelper mimeMessageHelper;

        // body email
        String content = "<b>Hallo " + userName + "</b>,<br>";
        content += "Anda Telah menerima tiket event!";
        content += "<br> Detail event:"
                + "<ul>"
                + "<li>Nama Event: " + e.getName() + "</li>"
                + "<li>Lokasi: " + e.getLocation() + "</li>"
                + "<li>Tanggal: " + e.getDate() + "</li>"
                + "</ul>";
        
        content += "<br><b><i>Berikut adalah barcode tiket anda:</i></b>"; 
        content += "<br><img src='cid:barcodeTiket'/><br><b>Salam,<br>Kelompok 3.</b>";

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("vieririzki19@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject("Tiket Anda");
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.addInline("barcodeTiket", barcodeTiket);

            mailSender.send(mimeMessage);
        } catch (MessagingException exception) {
            exception.printStackTrace();
            System.err.println("email gagal dikirim!");
        }
    }
}
