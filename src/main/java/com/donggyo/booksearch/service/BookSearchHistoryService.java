package com.donggyo.booksearch.service;

import com.donggyo.booksearch.dto.BookSearchHistoryDto;
import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.entity.BookSearchHistory;
import com.donggyo.booksearch.repository.BookSearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookSearchHistoryService {

	@Autowired
	private BookSearchHistoryRepository bookSearchHistoryRepository;

	public ResponseDto<List<BookSearchHistoryDto>> findRecentHistories (String userId) {

		List<BookSearchHistoryDto> bookSearchHistoryDtos = findAllOrderBySearchDate(userId).stream().map(this::transformTo).collect(
			Collectors.toList());

		return new ResponseDto(bookSearchHistoryDtos);
	}

	BookSearchHistory save(BookSearchHistory history) {
		return bookSearchHistoryRepository.save(history);
	}

	private List<BookSearchHistory> findAllOrderBySearchDate(String userId) {
		return bookSearchHistoryRepository.findAllByUserIdOrderBySearchDateDesc(userId);
	}

	private BookSearchHistoryDto transformTo(BookSearchHistory bookSearchHistory) {
		return new BookSearchHistoryDto(bookSearchHistory.getUserId(), bookSearchHistory.getKeyword(), bookSearchHistory.getSearchDate());
	}
}
