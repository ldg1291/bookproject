package com.donggyo.booksearch.service;

import com.donggyo.booksearch.dto.bookinfo.BookInfoDto;
import com.donggyo.booksearch.dto.page.PagedObjectDto;
import com.donggyo.booksearch.entity.BookSearchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BookSearchAggregateService {

	@Autowired
	private BookSearchHistoryService bookSearchHistoryService;
	@Autowired
	private BookSearchService bookSearchService;
	@Autowired
	private KeywordSearchRateService keywordSearchRateService;

	public PagedObjectDto<BookInfoDto> searchBookInfo(String query, String sort, Integer page, Integer size, String target) {

		PagedObjectDto<BookInfoDto> pagedBookInfoDto = bookSearchService.searchBookByQuery(query, sort, page, size, target);

		bookSearchHistoryService.save(new BookSearchHistory(new Random().nextLong(), "userId", query));
		keywordSearchRateService.saveOrUpdate(query);

		return pagedBookInfoDto;
	}
}
