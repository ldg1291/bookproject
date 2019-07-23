package com.donggyo.booksearch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class BookSearchUserRequestDto implements Serializable {

	private String userId;
	private String password;

	public BookSearchUserRequestDto(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
}
