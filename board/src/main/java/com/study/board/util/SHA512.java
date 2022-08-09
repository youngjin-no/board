package com.study.board.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512 {
	public static String decryption(String password) {
		String hex = null;
		try {
			MessageDigest msg = MessageDigest.getInstance("SHA-512");
			msg.update(password.getBytes());

			hex = String.format("%128x", new BigInteger(1, msg.digest()));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hex;
	}
}
