package com.donggyo.booksearch.controller.api;

import com.donggyo.booksearch.dto.BookSearchUserDto;
import com.donggyo.booksearch.dto.BookSearchUserRequestDto;
import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.service.BookSearchUserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/api/accounts/login", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<Boolean> logInUser(HttpServletRequest request) {

		BookSearchUserRequestDto bookSearchUserRequestDto = new BookSearchUserRequestDto(request.getParameter("userId"), request.getParameter("password"));

		ResponseDto<Boolean> logInResult = bookSearchUserService.logInWithUser(bookSearchUserRequestDto);

		if(logInResult.isSuccess()) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", bookSearchUserRequestDto.getUserId());
		}

		return logInResult;
	}

	@RequestMapping(value = "/api/accounts/logout", method = RequestMethod.GET)
	@ResponseBody
	public Boolean logOutUser(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.removeAttribute("userId");

		return true;
	}
}
