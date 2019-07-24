package com.donggyo.booksearch.dto.bookinfo;

import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoDto;
import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoMeta;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class BookSearchResultDto implements Serializable {

	private List<BookInfoDto> documents;
	private BookInfoMeta meta;

	public BookSearchResultDto (List<BookInfoDto> documents, BookInfoMeta meta) {
		this.documents = documents;
		this.meta = meta;
	}
}
