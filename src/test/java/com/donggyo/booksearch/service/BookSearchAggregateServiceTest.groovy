package com.donggyo.booksearch.service


import spock.lang.Specification

class BookSearchAggregateServiceTest extends Specification {

	BookSearchAggregateService sut
	BookSearchHistoryService bookSearchHistoryService
	BookSearchService bookSearchService
	KeywordSearchRateService keywordSearchRateService

	void setup() {
		bookSearchHistoryService = Mock(BookSearchHistoryService)
		bookSearchService = Mock(BookSearchService)
		keywordSearchRateService = Mock(KeywordSearchRateService)

		sut = new BookSearchAggregateService(keywordSearchRateService: keywordSearchRateService, bookSearchHistoryService: bookSearchHistoryService, bookSearchService: bookSearchService)
	}

	def "for each view service there is one history save and one search"() {

		when:
		sut.searchBookInfo("q", "s", 1, 2, "t")

		then:
		1 * bookSearchHistoryService.save(_)
		1 * bookSearchService.searchBookByQuery("q", "s", 1, 2, "t")
		1 * keywordSearchRateService.saveOrUpdate(_)

	}
}
