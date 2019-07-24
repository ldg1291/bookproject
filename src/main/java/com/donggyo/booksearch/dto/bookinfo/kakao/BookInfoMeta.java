package com.donggyo.booksearch.dto.bookinfo.kakao;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class BookInfoMeta implements Serializable {

	private Boolean is_end;
	private Long pageable_count;
	private Long total_count;

	public BookInfoMeta(Boolean is_end, Long pageable_count, Long total_count) {
		this.is_end = is_end;
		this.pageable_count = pageable_count;
		this.total_count = total_count;
	}
}
