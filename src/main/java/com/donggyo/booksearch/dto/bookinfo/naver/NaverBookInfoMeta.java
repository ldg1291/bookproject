package com.donggyo.booksearch.dto.bookinfo.naver;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class NaverBookInfoMeta implements Serializable {
	private Long display;
	private Long start;
	private Long total;

	public NaverBookInfoMeta(Long display, Long start, Long total) {
		this.display = display;
		this.start = start;
		this.total = total;
	}
}
