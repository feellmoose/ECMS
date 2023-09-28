package com.example.demo.mqtt.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MessageUtil {
    private final static Random random = new Random();
    private static String salt = "URlMPnK3F2rw";
    public final static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] digest(long timestamp, String messageId) {
        return md.digest((timestamp + messageId + salt).getBytes());
    }

    public static String getRandomHex() {
        long randomNum = random.nextLong();
        String hexString = Long.toHexString(randomNum);
        return String.format("%16s", hexString).replace(" ", "0");
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
