package com.donggyo.booksearch.dto.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@Getter
public class Pagination {

	private Integer page = 1;
	private Integer countPerPage = 10;
	private Long totalCount;
	private Long startPage = 0L;
	private Long endPage = 0L;
	private Long totalPages = 0L;

	public Pagination(Integer page, Integer countPerPage, Long totalCount) {
		this.page = page;
		this.countPerPage = countPerPage;
		this.totalCount = totalCount;
		this.startPage = getStartPage();
		this.endPage = getEndPage();
		this.totalPages = getTotalPages().longValue();
	}

	public Long getStartPage() {

		long pageLong = page.longValue();

		if(this.page%10 ==0) {
			return pageLong-9L;
		}
		return pageLong-(pageLong%10)+1L;
	}

	public Long getEndPage() {
		long pageLong = page.longValue();

		if(this.page%10 ==0) {
			return pageLong;
		}
		return Long.min(pageLong-(pageLong%10)+10L, this.totalPages);
	}

	public Integer getTotalPages() {
		Integer count = this.totalCount.intValue();
		return count % countPerPage == 0 ? count / countPerPage : (count / countPerPage) + 1;
	}
}
