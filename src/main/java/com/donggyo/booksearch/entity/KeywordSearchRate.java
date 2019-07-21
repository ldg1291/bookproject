package com.donggyo.booksearch.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "key_word_search_rate")
public class KeywordSearchRate {

	@Id
	@Column(name = "keyword")
	private String keyword;

	@Column(name = "search_number")
	private Long searchNumber;

	public KeywordSearchRate(String keyword, Long searchNumber) {
		this.keyword = keyword;
		this.searchNumber = searchNumber;
	}
}
