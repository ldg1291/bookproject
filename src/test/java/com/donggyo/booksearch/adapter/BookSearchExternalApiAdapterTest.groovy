package com.donggyo.booksearch.adapter

import com.donggyo.booksearch.dto.bookinfo.BookInfoMeta
import com.donggyo.booksearch.dto.bookinfo.BookSearchResultDto
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

	def "kakao api call throws RestClientException"() {
		given:
		restTemplate.exchange(_, _ as HttpMethod, _ as HttpEntity<?>, BookSearchResultDto.class) >> {throw new RestClientException("message") }

		when:
		sut.getBook("query", SortType.ACCURACY,2,3, SearchType.ISBN)

		then:
		thrown BookSearchException

	}

	@Unroll
	def "kakao api call with no 200 code"() {
		given:
		restTemplate.exchange(_, _ as HttpMethod, _ as HttpEntity<?>, BookSearchResultDto.class) >> new ResponseEntity<BookSearchResultDto>(null, HTTP_STATUS)

		when:
		def result = sut.getBook("query", SortType.ACCURACY,2,3, SearchType.ISBN)

		then:
		result == null

		where:
		HTTP_STATUS << [HttpStatus.BAD_REQUEST, HttpStatus.GATEWAY_TIMEOUT, HttpStatus.INTERNAL_SERVER_ERROR]
	}
}
