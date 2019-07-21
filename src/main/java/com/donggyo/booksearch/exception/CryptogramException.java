package com.donggyo.booksearch.exception;

import lombok.Getter;

@Getter
public class CryptogramException extends RuntimeException {

	public CryptogramException(String message) {
		super(message);
	}
}
