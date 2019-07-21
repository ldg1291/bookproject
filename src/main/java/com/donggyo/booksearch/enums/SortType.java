package com.donggyo.booksearch.enums;

import lombok.Getter;

@Getter
public enum SortType {
	ACCURACY("accuracy", "sim"),
	LATEST("latest", "date");

	private String kakaoDesc;
	private String naverDesc;

	SortType(String kakaoDesc, String naverDesc) {
		this.kakaoDesc = kakaoDesc;
		this.naverDesc = naverDesc;
	}

}
