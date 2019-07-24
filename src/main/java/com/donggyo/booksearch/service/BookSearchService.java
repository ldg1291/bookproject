package com.donggyo.booksearch.service;

import com.donggyo.booksearch.adapter.BookSearchExternalApiAdapter;
import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoDto;
import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoMeta;
import com.donggyo.booksearch.dto.bookinfo.BookSearchResultDto;
import com.donggyo.booksearch.dto.page.PagedObjectDto;
import com.donggyo.booksearch.dto.page.Pagination;
import com.donggyo.booksearch.enums.SearchType;
import com.donggyo.booksearch.enums.SortType;
import com.donggyo.booksearch.exception.BookSearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookSearchService {
	@Autowired
	private BookSearchExternalApiAdapter bookSearchExternalApiAdapter;

	private static final String NO_SEARCH_RESULT = "No Search Result";

	PagedObjectDto<BookInfoDto> searchBookByQuery(String query, SortType sort, Integer page, Integer size, SearchType target) throws
		BookSearchException {

		BookSearchResultDto bookSearchResultDto = Optional.ofNullable(bookSearchExternalApiAdapter.getBook(query, sort, page, size, target)).orElseThrow(() ->new BookSearchException(NO_SEARCH_RESULT));

		BookInfoMeta meta = bookSearchResultDto.getMeta();
		List<BookInfoDto> bookInfoDtos = bookSearchResultDto.getDocuments();

		return PagedObjectDto.of(new Pagination(page, size, meta.getPageable_count()), bookInfoDtos);
	}
}
