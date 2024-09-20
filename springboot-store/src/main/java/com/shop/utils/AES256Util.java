package com.shop.utils;

import com.shop.pojo.Order;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Component
public class AES256Util {
    // AES-256-CBC 模式加密
    public static String encrypt(String data, String key, String iv) {
        // 初始化密鑰和 IV
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

        // 使用 AES 加密
        byte[] encrypted = new byte[]{};
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            // 加密數據
            encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        // 將加密結果轉換為十六進制
        return bytesToHex_aes(encrypted);
    }
    // 將二進制數據轉換為十六進制字符串
    public static String bytesToHex_aes(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // 解密函數，傳入要解密的資料，金鑰和 IV
    public static String decrypt(byte[] encryptedData, String hashKey, String hashIV) throws Exception {
        // 轉換 HashKey 和 HashIV 為字節數組
        byte[] keyBytes = hashKey.getBytes("UTF-8");
        byte[] ivBytes = hashIV.getBytes("UTF-8");

        // 準備 AES 解密
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        // 解密數據
        byte[] decryptedBytes = cipher.doFinal(encryptedData);

        // 移除 PKCS7 Padding
        decryptedBytes = removePKCS7Padding(decryptedBytes);

        return new String(decryptedBytes, "UTF-8");
    }
    // 移除 PKCS7 Padding
    public static byte[] removePKCS7Padding(byte[] data) {
        int padValue = data[data.length - 1];
        byte[] unpaddedData = new byte[data.length - padValue];
        System.arraycopy(data, 0, unpaddedData, 0, unpaddedData.length);
        return unpaddedData;
    }
    // 將十六進制字串轉換為 byte array
    public static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] bytes = new byte[length / 2];  // 每兩個字符是一個字節

        for (int i = 0; i < length; i += 2) {
            // 解析兩個字符為一個字節
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }

        return bytes;
    }
}
