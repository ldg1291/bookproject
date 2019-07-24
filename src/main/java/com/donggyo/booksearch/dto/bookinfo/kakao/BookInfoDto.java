package com.donggyo.booksearch.dto.bookinfo.kakao;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class BookInfoDto implements Serializable {

	private String title;
	private String thumbnail;
	private String contents;
	private String isbn;
	private List<String> authors;
	private String publisher;
	private String datetime;
	private BigDecimal sale_price;
	private BigDecimal price;
	private String status;
	private String url;
	private List<String> translators;

	public BookInfoDto(String title, String thumbnail, String contents, String isbn, List<String> authors, String publisher, String datetime, BigDecimal sale_price, BigDecimal price, String url, String status, List<String> translators) {

		this.title = title;
		this.thumbnail = thumbnail;
		this.contents = contents;
		this.isbn = isbn;
		this.authors = authors;
		this.publisher = publisher;
		this.datetime = datetime;
		this.sale_price = sale_price;
		this.price = price;
		this.url = url;
		this.status = status;
		this.translators = translators;

	}
}
