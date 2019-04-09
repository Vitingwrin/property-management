package com.property.util;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    public static String digest(String data) {
        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md5.digest();
            result = new char[32];
            int index = 0;

            // 将结果的16字节转成16进制
            for (byte b : bytes) {
                result[index++] = hex[b >>> 4 & 0xf];
                result[index++] = hex[b & 0x0f];
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result == null ? null : new String(result);
    }


}
