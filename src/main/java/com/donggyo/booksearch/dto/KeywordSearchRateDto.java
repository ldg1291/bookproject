package com.donggyo.booksearch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeywordSearchRateDto {

	private String keyword;
	private Long searchNumber;

	public KeywordSearchRateDto(String keyword, Long searchNumber) {
		this.keyword = keyword;
		this.searchNumber = searchNumber;
	}
}
