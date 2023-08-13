package com.linhtran.statelessauthentication.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    public  String generate(String data) throws InvalidKeyException, NoSuchAlgorithmException {
        String secret = "My secret 123";
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] hashBytes = mac.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public boolean validateHash(String originalHash, String data) throws NoSuchAlgorithmException, InvalidKeyException {
        String calculatedHash = generate(data);
        return originalHash.equals(calculatedHash);
    }
}
