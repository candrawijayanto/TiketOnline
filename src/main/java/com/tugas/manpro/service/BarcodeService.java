package com.tugas.manpro.service;

import java.io.IOException;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import org.springframework.stereotype.Component;

@Component
public class BarcodeService {

    public void generate(String data) {
        String path = "./barcode/" + data + ".jpg";

        BitMatrix matrix;
        try {
            matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("QR " + data + " berhasil dibuat");
    }

}
