package com.donggyo.booksearch.dto.bookinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class BookSearchResultDto implements Serializable {

	private List<BookInfoDto> documents;
	private BookInfoMeta meta;
}
