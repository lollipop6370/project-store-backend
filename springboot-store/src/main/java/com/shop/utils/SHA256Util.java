package com.shop.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class SHA256Util {
    //sha256 加密
    public static String sha256(String data) {
        byte[] hash = new byte[]{};
        // 使用 SHA-256 算法
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(data.getBytes("UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }

        // 將雜湊結果轉換為十六進制
        return bytesToHex_sha(hash);
    }
    // 將字節數組轉換為十六進制字符串
    public static String bytesToHex_sha(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));  // %02X 將每個字節轉換為大寫十六進制
        }
        return sb.toString();
    }
}
