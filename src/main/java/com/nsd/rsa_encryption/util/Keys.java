package com.nsd.rsa_encryption.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Keys {

	
	public static KeyPair getKeypair() throws Exception {

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		return keyPairGenerator.generateKeyPair();
	}
	
	public static PublicKey getPublicKeyFromBase64(String base64PublicKey) throws Exception {

		base64PublicKey = base64PublicKey.replaceAll("\\s", "");
		byte[] keyByte = Base64.getDecoder().decode(base64PublicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyByte);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(keySpec);
	}

	public static PrivateKey getPrivateKeyFromBase64(String base64PrivateKey) throws Exception {

		base64PrivateKey = base64PrivateKey.replaceAll("\\s", "");
		byte[] keyByte = Base64.getDecoder().decode(base64PrivateKey);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyByte);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(spec);
	}
}
