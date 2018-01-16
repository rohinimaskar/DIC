package com.mongodb.twitter.SupportingFiles;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

//Http Helper
public class Helper {
	private static String timaValue = "";
	private static String nullValue = "";

	public static String returnTime() {
		if (timaValue.equals("")) {
			timaValue = String.valueOf(System.currentTimeMillis() / 1000);
		}
		return timaValue;
	}

	public static String changeNull() {
		
		String dashValue = "-";
		if (nullValue.equals("")) {
			nullValue = UUID.randomUUID().toString().replaceAll(dashValue, "");
		}
		return nullValue;
	}

	public static String code(String linkUrl) {
		try {
			String format = "UTF-8";
			String plusSymbol = "\\+";
			String plusReplacement = "%20";
			String asterikSymbol = "\\*";
			String asterikReplacement = "%2A";
			String stringSymbol = "\\s";
			String valueToReturn = URLEncoder.encode(linkUrl, format).replaceAll(plusSymbol, plusReplacement)
					.replaceAll(asterikSymbol, asterikReplacement).replaceAll(stringSymbol, plusReplacement);
			
			return valueToReturn;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String calculateSign(String strBase, String strKey)
			throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKey key = null;
		String shaValue = "HmacSHA1";
		byte[] bytes = strKey.getBytes();
		key = new SecretKeySpec(bytes,shaValue);
		Mac mac = Mac.getInstance(shaValue);
		mac.init(key);
		byte[] string = strBase.getBytes();

		return new String(Base64.encodeBase64(mac.doFinal(string))).trim();
	}
}
