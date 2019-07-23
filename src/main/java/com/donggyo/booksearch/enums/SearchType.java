package com.donggyo.booksearch.enums;

import lombok.Getter;

@Getter
public enum SearchType {
	NONE(""),
	TITLE("title"),
	ISBN("isbn"),
	PUBLISHER("publisher"),
	PERSON("person");

	private String kakaoDesc;

	SearchType(String kakaoDesc) {
		this.kakaoDesc = kakaoDesc;
	}
}
