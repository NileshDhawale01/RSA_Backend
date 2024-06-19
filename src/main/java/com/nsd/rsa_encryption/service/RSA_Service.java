package com.nsd.rsa_encryption.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface RSA_Service {

	ResponseEntity<Map<String, Object>> generateKeyPair();

	ResponseEntity<Map<String, Object>> encryptData(String data,String base64PublicKey);

	ResponseEntity<Map<String, Object>> decryptData(String data);
}
