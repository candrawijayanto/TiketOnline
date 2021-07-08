package com.tugas.manpro.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class BarcodeService {

    public FileSystemResource generate(String data) {
        String path = "./barcode/" + data + ".jpg";

        BitMatrix matrix;
        try {
            matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 250, 250);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }


        System.out.println("QR " + data + " berhasil dibuat");

        return new FileSystemResource(new File(path));
    }

}
