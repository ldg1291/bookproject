package com.donggyo.booksearch.query;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookInfoSearchPredicate {

	public static final Integer MAX_COUNT_PER_PAGE = 100;
	private static final Integer DEFAULT_COUNT_PER_PAGE = 10;

	private String title;
	private Integer page = 1;
	private Integer countPerPage = DEFAULT_COUNT_PER_PAGE;

	public BookInfoSearchPredicate(String title, Integer page, Integer countPerPage) {
		this.title = title;
		this.page = page;
		this.countPerPage = countPerPage;
	}
}
