package com.donggyo.booksearch.enums;

import lombok.Getter;

@Getter
public enum SearchType {
	NONE("", ""),
	TITLE("title", "d_titl"),
	ISBN("isbn", "d_isbn"),
	PUBLISHER("publisher", "d_publ"),
	PERSON("person", "d_auth");

	private String kakaoDesc;
	private String naverDesc;

	SearchType(String kakaoDesc, String naverDesc) {
		this.kakaoDesc = kakaoDesc;
		this.naverDesc = naverDesc;
	}

	public static String getDescByEnumAndCompany (SearchCompanyType companyType, SearchType type) {
		if(companyType == SearchCompanyType.NAVER) {
			return type.getNaverDesc();
		}
		return type.getKakaoDesc();
	}
}
