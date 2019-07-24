package com.donggyo.booksearch.dto.bookinfo.naver;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class NaverBookInfoDto {

	private String title;
	private String image;
	private String description;
	private String isbn;
	private String author;
	private String publisher;
	private String pubdate;
	private BigDecimal discount;
	private BigDecimal price;
	private String link;

	public NaverBookInfoDto(String title, String image, String description, String isbn, String author, String publisher, String pubdate, BigDecimal discount, BigDecimal price, String link) {

		this.title = title;
		this.image = image;
		this.description = description;
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		this.pubdate = pubdate;
		this.discount = discount;
		this.price = price;
		this.link = link;

	}
}
