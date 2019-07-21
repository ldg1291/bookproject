package com.donggyo.booksearch.dto.page;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class PagedObjectDto<T> implements Serializable {

	private Pagination pagination;
	private List<T> result;

	public PagedObjectDto (Pagination pagination, List<T> result) {
		this.pagination = pagination;
		this.result = result;
	}

	public static <T> PagedObjectDto<T> of(Pagination pagination, List<T> results) {
		return new PagedObjectDto<>(pagination, results);
	}
}
