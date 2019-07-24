package com.donggyo.booksearch.service;

import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoDto;
import com.donggyo.booksearch.dto.page.PagedObjectDto;
import com.donggyo.booksearch.enums.SearchType;
import com.donggyo.booksearch.enums.SortType;
import com.donggyo.booksearch.exception.BookSearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookSearchAggregateService {

	@Autowired
	private BookSearchHistoryService bookSearchHistoryService;
	@Autowired
	private BookSearchService bookSearchService;
	@Autowired
	private KeywordSearchRateService keywordSearchRateService;

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public ResponseDto<PagedObjectDto<BookInfoDto>> searchBookInfo(String query, SortType sort, Integer page, Integer size, SearchType target,
		String userId) {

		try {
			PagedObjectDto<BookInfoDto> pagedBookInfoDto = bookSearchService.searchBookByQuery(query, sort, page, size, target);

			bookSearchHistoryService.save(userId, query);
			keywordSearchRateService.saveOrUpdate(query);

			return new ResponseDto<>(pagedBookInfoDto);
		} catch (BookSearchException e) {
			return ResponseDto.failResponseOf(e.getMessage());
		}
	}
}
