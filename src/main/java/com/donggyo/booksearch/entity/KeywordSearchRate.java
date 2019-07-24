package com.donggyo.booksearch.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "key_word_search_rate")
public class KeywordSearchRate {

	@Id
	@Column(name = "keyword_search_rate_id", nullable = false)
	private String keywordSearchRateId;

	@Column(name = "keyword", nullable = false)
	private String keyword;

	@Column(name = "search_number", nullable = false)
	private Long searchNumber;

	public KeywordSearchRate(String keywordSearchRateId, String keyword, Long searchNumber) {
		this.keywordSearchRateId = keywordSearchRateId;
		this.keyword = keyword;
		this.searchNumber = searchNumber;
	}
}
