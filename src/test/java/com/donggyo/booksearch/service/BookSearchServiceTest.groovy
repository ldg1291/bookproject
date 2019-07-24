package com.donggyo.booksearch.service

import com.donggyo.booksearch.adapter.BookSearchExternalApiAdapter
import com.donggyo.booksearch.enums.SearchType
import com.donggyo.booksearch.enums.SortType
import com.donggyo.booksearch.exception.BookSearchException
import spock.lang.Specification

class BookSearchServiceTest extends Specification {

	BookSearchService sut
	BookSearchExternalApiAdapter bookSearchExternalApiAdapter

	void setup () {
		bookSearchExternalApiAdapter = Mock(BookSearchExternalApiAdapter)
		sut = new BookSearchService(bookSearchExternalApiAdapter: bookSearchExternalApiAdapter)
	}


	def "if no search result then throws BookSearchException"() {
		given:
		bookSearchExternalApiAdapter.getBook(_, _, _, _, _) >> null

		when:
		sut.searchBookByQuery("q", SortType.ACCURACY, 1, 2, SearchType.ISBN)

		then:
		thrown BookSearchException

	}
}
