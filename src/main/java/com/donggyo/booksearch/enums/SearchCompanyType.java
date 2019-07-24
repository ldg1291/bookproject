package com.donggyo.booksearch.enums;

import lombok.Getter;

@Getter
public enum SearchCompanyType {

	NAVER("https://openapi.naver.com/v1/search/book.json?"),
	KAKAO("https://dapi.kakao.com/v3/search/book?");

	private String url;

	SearchCompanyType(String url) {
		this.url = url;
	}
}
