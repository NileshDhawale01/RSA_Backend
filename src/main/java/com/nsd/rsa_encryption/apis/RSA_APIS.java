package com.nsd.rsa_encryption.apis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsd.rsa_encryption.service.RSA_Service;

@RestController
@RequestMapping("/rsa")
public class RSA_APIS {

	@Autowired
	RSA_Service rsa_Service;

	@GetMapping("generate_Keys")
	public ResponseEntity<Map<String, Object>> generateKeyPair() {

		return rsa_Service.generateKeyPair();
	}

	// just test
	@PostMapping("/encrypt")
	public ResponseEntity<Map<String, Object>> encryptData(@RequestBody Map<String, String> map) {

		String sampleData = map.get("dataString");
		String base64PublicKey = map.get("base64PublicKey");
		return rsa_Service.encryptData(sampleData, base64PublicKey);
	}

	@PostMapping("/decrypt")
	public ResponseEntity<Map<String, Object>> decryptData(@RequestBody Map<String, String> map) {
		String encryptedData = map.get("encrytedData");
		System.out.println("API :: " + encryptedData);
		return rsa_Service.decryptData(encryptedData);
	}
}
