package com.donggyo.booksearch.crypto;

import com.donggyo.booksearch.exception.CryptogramException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class CryptoService {

	private static final String UNSUPPORTED_ALGORITHM = "암호화가 불가능한 알고리즘입니다.";

	private MessageDigest messageDigest;

	@PostConstruct
	private void postConstruct() {
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			log.error("CryptoService.postConstruct : {}", e.getMessage());
			messageDigest = null;
		}
	}

	public String toCypher(String plainText) {
		if(messageDigest == null) {
			throw new CryptogramException(UNSUPPORTED_ALGORITHM);
		}

		byte[] cypher = messageDigest.digest(plainText.getBytes());

		return new String(cypher);
	}
}
