package com.donggyo.booksearch.dto.bookinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class BookInfoDto implements Serializable {

	private String title;
	private String thumb_nail;
	private String introduction;
	private String isbn;
	private String author;
	private String publisher;
	private String datetime;
	private BigDecimal sale_price;
	private BigDecimal price;

	public BookInfoDto(String title, String thumb_nail, String introduction, String isbn, String author, String publisher, String datetime, BigDecimal sale_price, BigDecimal price) {

		this.title = title;
		this.thumb_nail = thumb_nail;
		this.introduction = introduction;
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		this.datetime = datetime;
		this.sale_price = sale_price;
		this.price = price;

	}
}
