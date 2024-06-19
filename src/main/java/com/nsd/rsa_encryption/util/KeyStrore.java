package com.nsd.rsa_encryption.util;

public class KeyStrore {

	private static String base64PublicKey = null;
	private static String base64PrivateKey = null;
	
	public static String getBase64PublicKey() {
		return base64PublicKey;
	}
	public static void setBase64PublicKey(String base64PublicKey) {
		KeyStrore.base64PublicKey = base64PublicKey;
	}
	public static String getBase64PrivateKey() {
		return base64PrivateKey;
	}
	public static void setBase64PrivateKey(String base64PrivateKey) {
		KeyStrore.base64PrivateKey = base64PrivateKey;
	}
	
	
}
