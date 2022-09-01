package com.study.global.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SHA512 {
	public static String decryption(String password) {
		String hex = null;
		try {
			MessageDigest msg = MessageDigest.getInstance("SHA-512");
			msg.update(password.getBytes());

			hex = String.format("%128x", new BigInteger(1, msg.digest()));

		} catch (NoSuchAlgorithmException e) {
			log.info(e.getMessage());
		}
		return hex;
	}
}
