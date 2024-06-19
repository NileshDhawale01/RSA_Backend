package com.nsd.rsa_encryption.service.impl;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nsd.rsa_encryption.service.RSA_Service;
import com.nsd.rsa_encryption.util.KeyStrore;
import com.nsd.rsa_encryption.util.Keys;
import com.nsd.rsa_encryption.util.RSAHandler;

@Service
public class RSA_ServiceImpl implements RSA_Service {

	@Override
	public ResponseEntity<Map<String, Object>> generateKeyPair() {

		Map<String, Object> map = new HashMap<>();
		try {

			KeyPair keyPair = Keys.getKeypair();
			String base64PublicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
			String base64PrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
			KeyStrore.setBase64PublicKey(base64PublicKey);
			KeyStrore.setBase64PrivateKey(base64PrivateKey);

			map.put("Data", "key pair generates");
			map.put("publicKey", base64PublicKey);
			map.put("success", true);

		} catch (Exception e) {
			map.put("Data", "Something went wrong please check code");
			map.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> decryptData(String data) {

		Map<String, Object> map = new HashMap<>();
		try {

			System.out.println("start :: ");
			String base64PricvateKeyString = KeyStrore.getBase64PrivateKey();
			System.out.println("base64 private key String :: " + base64PricvateKeyString);
			PrivateKey privateKey = Keys.getPrivateKeyFromBase64(base64PricvateKeyString);
			System.out.println("Private key :: " + privateKey);

			String decryptedData = RSAHandler.decryptData(data, privateKey);
			System.out.println("Decrypted Data :: " + decryptedData);
			map.put("decryptedData", decryptedData);
			map.put("data", "decryption success");
			map.put("success", true);
		} catch (Exception e) {
			map.put("data", "decryption false");
			map.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> encryptData(String data, String base64PublicKey) {

		Map<String, Object> map = new HashMap<>();
		try {
			String encryptedData = RSAHandler.encryptData(data, Keys.getPublicKeyFromBase64(base64PublicKey));
			System.out.println("Encrypted Data :: " + encryptedData);
			map.put("encryptedData", encryptedData);
			map.put("data", "encryption success");
			map.put("success", true);
		} catch (Exception e) {
			map.put("data", "encryption false");
			map.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
