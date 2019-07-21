package com.donggyo.booksearch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookSearchHistoryDto {

	private String userId;
	private String keyword;
	private LocalDateTime searchDate;

	public BookSearchHistoryDto(String userId, String keyword, LocalDateTime searchDate) {
		this.userId = userId;
		this.keyword = keyword;
		this.searchDate = searchDate;
	}
}
