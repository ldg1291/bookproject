package com.donggyo.booksearch.service;

import com.donggyo.booksearch.dto.BookSearchUserDto;
import com.donggyo.booksearch.dto.BookSearchUserRequestDto;
import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.entity.BookSearchUser;
import com.donggyo.booksearch.repository.BookSearchUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookSearchUserService {

	private static final String ALREADY_EXISTS_USER = "이미 존재하는 ID입니다. 다른 아이디를 사용해주세요";
	private static final String WRONG_LOG_IN_DATA = "잘못된 로그인 정보입니다. 올바른 정보를 입력해주세요";

	@Autowired
	private BookSearchUserRepository bookSearchUserRepository;

	public ResponseDto<BookSearchUserDto> createUser(BookSearchUserRequestDto requestDto) {

		if (checkIfUserExistsWithUserId(requestDto.getUserId())) {
			return ResponseDto.failResponseOf(ALREADY_EXISTS_USER);
		}

		BookSearchUser newUser = makeBookSearchUser(requestDto);
		BookSearchUserDto newUserDto = tranformFrom(newUser);

		return new ResponseDto<>(newUserDto);
	}

	public boolean logInWithUser(BookSearchUserRequestDto requestDto) {

		return checkIfUserExistsWithUserIdAndPassword(requestDto);
	}

	private boolean checkIfUserExistsWithUserId(String userId) {
		return bookSearchUserRepository.existsByUserId(userId);
	}

	private boolean checkIfUserExistsWithUserIdAndPassword(BookSearchUserRequestDto requestDto) {
		return bookSearchUserRepository.existsByUserIdAndPassword(requestDto.getUserId(), requestDto.getPassword());
	}

	private BookSearchUser makeBookSearchUser(BookSearchUserRequestDto requestDto) {

		LocalDateTime nowDate = LocalDateTime.now();

		return new BookSearchUser(requestDto.getUserId(), requestDto.getPassword(), nowDate, nowDate);
	}

	private BookSearchUserDto tranformFrom(BookSearchUser bookSearchUser) {
		return new BookSearchUserDto(bookSearchUser.getUserId(), bookSearchUser.getCreatedAt(), bookSearchUser.getModifiedAt());
	}
}
