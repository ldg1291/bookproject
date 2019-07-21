package com.donggyo.booksearch.service

import com.donggyo.booksearch.repository.KeywordSearchRateRepository
import spock.lang.Specification

class KeywordSearchRateServiceTest extends Specification {

	KeywordSearchRateService sut
	KeywordSearchRateRepository keywordSearchRateRepository

	void setup() {
		keywordSearchRateRepository = Mock(KeywordSearchRateRepository)
		sut = new KeywordSearchRateService(keywordSearchRateRepository: keywordSearchRateRepository)
	}

	def "saveOrUpdate save if keywordSearchRate does not exists"() {
		given:
		keywordSearchRateRepository.existsByKeyword(_) >> false

		when:
		sut.saveOrUpdate("keyword")

		then:
		1 * keywordSearchRateRepository.save(_)
	}

	def "saveOrUpdate update if keywordSearchRate exists"() {
		given:
		keywordSearchRateRepository.existsByKeyword(_) >> true

		when:
		sut.saveOrUpdate("keyword")

		then:
		1 * keywordSearchRateRepository.save(_)
	}
}
