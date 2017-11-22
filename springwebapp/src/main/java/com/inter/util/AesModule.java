package com.inter.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class AesModule {
	private static final String key = "BoychaERikAjh1JJ";
	private static final String ivKey = "Iris12aSUkAC8sae";

	public static String aesEncrypt(String text) {

		String result = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");

			byte[] ivBytes = new byte[16];
			byte[] b_iv = ivKey.getBytes("UTF-8");

			int len = b.length;
			if (len > keyBytes.length)
				len = keyBytes.length;
			System.arraycopy(b, 0, keyBytes, 0, len);
			System.arraycopy(b_iv, 0, ivBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

			byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
			Base64 encoder = new Base64();
			result = encoder.encodeToString(results);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;
	}

	public static String aesDecrypt(String text) {

		String result = "";

		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");

			byte[] ivBytes = new byte[16];
			byte[] b_iv = ivKey.getBytes("UTF-8");

			int len = b.length;
			if (len > keyBytes.length)
				len = keyBytes.length;
			System.arraycopy(b, 0, keyBytes, 0, len);
			System.arraycopy(b_iv, 0, ivBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

			Base64 decoder = new Base64();
			byte[] results = cipher.doFinal(decoder.decode(text));
			result = new String(results, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;

	}
}
