package com.nsd.rsa_encryption.util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAHandler {

	// encryption
	public static String encryptData(String data, PublicKey publicKey) throws Exception {

		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(result);
	}

	// decryption
	public static String decryptData(String base64Data, PrivateKey privateKey) throws Exception {

		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(Base64.getDecoder().decode(base64Data));
		return new String(result);
	}

}
