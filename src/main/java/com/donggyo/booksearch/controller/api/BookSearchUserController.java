package com.donggyo.booksearch.controller.api;

import com.donggyo.booksearch.dto.BookSearchUserDto;
import com.donggyo.booksearch.dto.BookSearchUserRequestDto;
import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.service.BookSearchUserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BookSearchUserController {

	@Autowired
	private BookSearchUserService bookSearchUserService;

	@PostMapping("/api/user/create")
	@ResponseBody
	public ResponseDto<BookSearchUserDto> createUser (@RequestBody BookSearchUserRequestDto requestDto) {
		return bookSearchUserService.createUser(requestDto);
	}

	@PostMapping("/api/user/login")
	@ResponseBody
	public boolean logInUser(@RequestBody BookSearchUserRequestDto requestDto, HttpServletRequest request) {

		if(bookSearchUserService.logInWithUser(requestDto)) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", requestDto.getUserId());
		}

		return bookSearchUserService.logInWithUser(requestDto);
	}
}
