package com.hendri.noted.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @author HENDRIYAWAN
 * 211011401536
 * kelas ini berisi Utilitas
 * mendapatkan tanggal sekarang
 * mengkoversi password ke md5
 */
public class Util {

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }
    public static String convertToMD5(char[] password) {
        try {
            // Membuat instance MessageDigest dengan algoritma MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Mengonversi char[] password menjadi byte array
            byte[] passwordBytes = new byte[password.length];
            for (int i = 0; i < password.length; i++) {
                passwordBytes[i] = (byte) password[i];
            }
            // Menghitung hash MD5 dari byte array password
            byte[] digest = md.digest(passwordBytes);
            // Mengonversi byte array menjadi string heksadesimal
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
