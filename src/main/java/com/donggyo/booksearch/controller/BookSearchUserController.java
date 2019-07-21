package com.donggyo.booksearch.controller;

import com.donggyo.booksearch.dto.BookSearchUserDto;
import com.donggyo.booksearch.dto.BookSearchUserRequestDto;
import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.service.BookSearchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BookSearchUserController {

	@Autowired
	private BookSearchUserService bookSearchUserService;

	@PostMapping("/user/create")
	public ResponseDto<BookSearchUserDto> createUser (@RequestBody BookSearchUserRequestDto requestDto) {
		return bookSearchUserService.createUser(requestDto);
	}

	@PostMapping("/user/login")
	public boolean logInUser(@RequestBody BookSearchUserRequestDto requestDto, HttpServletResponse response) {

		if(bookSearchUserService.logInWithUser(requestDto)) {
			Cookie cookie = new Cookie("userId", requestDto.getUserId());
			response.addCookie(cookie);
		}

		return bookSearchUserService.logInWithUser(requestDto);
	}
}
