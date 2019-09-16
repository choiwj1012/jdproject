package com.facebookc.spring.common;

import java.security.MessageDigest;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Encrypt {
	private static Logger log = Logger.getLogger(Encrypt.class.getName());
	public final String accountSeperator = ":";

	private String key = "2t0a1b7s";

	public String decrypt(String message) throws Exception {
		byte[] bytesrc = convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));

		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte);
	}

	public String encrypt(String message) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		return toHexString(cipher.doFinal(message.getBytes("UTF-8")));
	}

	private byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}
		return digest;
	}

	private String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}
		return hexString.toString();
	}

	public String[] decodeAccount(String cookieValue) {
		try {
			String origi = decrypt(cookieValue);
			String[] parts = origi.split(accountSeperator);
			if (parts.length == 2 && !parts[0].equals("") && !parts[1].equals("")) {
				return parts;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warning(e.getMessage());
		}
		return null;
	}

	public String encodeAccount(String username, String password) {
		String encryptString = null;
		try {
			encryptString = encrypt(username + accountSeperator + password);
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
		return encryptString;
	}
	
	public String pwdEncrypt(String pwd) {
	    StringBuffer hexString = new StringBuffer();
	 
	    try {
	 
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
	 
	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	 
	            if (hex.length() == 1) {
	                hexString.append('0');
	            }
	 
	            hexString.append(hex);
	        }
	 
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
	 
	    return hexString.toString();
	}

}
