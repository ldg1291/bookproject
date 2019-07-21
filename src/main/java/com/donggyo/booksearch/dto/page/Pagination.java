package com.donggyo.booksearch.dto.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@Getter
public class Pagination {

	private Integer page = 1;
	private Integer countPerPage = 10;
	private Long totalCount;

	public Pagination(Integer page, Integer countPerPage, Long totalCount) {
		this.page = page;
		this.countPerPage = countPerPage;
		this.totalCount = totalCount;
	}

	public int getTotalPages() {
		Integer count = this.totalCount.intValue();
		return count % countPerPage == 0 ? count / countPerPage : (count / countPerPage) + 1;
	}

	public static Pagination of(Page p) {
		return new Pagination(p.getNumber() + 1, p.getSize(), p.getTotalElements());
	}
}
