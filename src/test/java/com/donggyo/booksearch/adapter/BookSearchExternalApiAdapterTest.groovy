package com.donggyo.booksearch.adapter

import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoMeta
import com.donggyo.booksearch.dto.bookinfo.BookSearchResultDto
import com.donggyo.booksearch.dto.bookinfo.naver.NaverBookInfoMeta
import com.donggyo.booksearch.dto.bookinfo.naver.NaverBookSearchResultDto
import com.donggyo.booksearch.enums.SearchType
import com.donggyo.booksearch.enums.SortType
import com.donggyo.booksearch.exception.BookSearchException
import org.assertj.core.util.Lists
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

class BookSearchExternalApiAdapterTest extends Specification {
	BookSearchExternalApiAdapter sut
	RestTemplate restTemplate

	void setup() {
		restTemplate = Mock(RestTemplate)
		sut = new BookSearchExternalApiAdapter(restTemplate: restTemplate)
	}

	def "kakao api call success"() {
		given:
		restTemplate.exchange(_,_,_, BookSearchResultDto.class) >> new ResponseEntity<BookSearchResultDto>(new BookSearchResultDto(documents: Lists.newArrayList(), meta:new BookInfoMeta(is_end: true, pageable_count: 0, total_count: 0)),HttpStatus.OK)

		when:
		def res = sut.getBook("query", SortType.ACCURACY,2,3, SearchType.ISBN)

		then:
		res.documents.size() == 0
		res.meta.pageable_count == 0
		res.meta.total_count == 0
		res.meta.is_end

	}
}
