package com.donggyo.booksearch.service;

import com.donggyo.booksearch.dto.BookSearchUserDto;
import com.donggyo.booksearch.dto.BookSearchUserRequestDto;
import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.entity.BookSearchUser;
import com.donggyo.booksearch.exception.UserException;
import com.donggyo.booksearch.repository.BookSearchUserRepository;
import com.donggyo.booksearch.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookSearchUserService {

	private static final String WRONG_LOG_IN_DATA = "잘못된 로그인 정보입니다. 올바른 정보를 입력해주세요";

	@Autowired
	private BookSearchUserRepository bookSearchUserRepository;
	@Autowired
	private UserValidator userValidator;

	public ResponseDto<BookSearchUserDto> createUser(BookSearchUserRequestDto requestDto) {

		try {
			userValidator.validateUser(requestDto);
		} catch (UserException e){
			return ResponseDto.failResponseOf(e.getMessage());
		}

		BookSearchUser newUser = makeBookSearchUser(requestDto);
		BookSearchUserDto newUserDto = tranformFrom(newUser);

		return new ResponseDto<>(newUserDto);
	}

	public ResponseDto<Boolean> logInWithUser(BookSearchUserRequestDto requestDto) {

		if(checkIfUserExistsWithUserIdAndPassword(requestDto)) {
			return new ResponseDto<>(Boolean.TRUE);
		}

		return ResponseDto.failResponseOf(WRONG_LOG_IN_DATA);
	}

	private boolean checkIfUserExistsWithUserIdAndPassword(BookSearchUserRequestDto requestDto) {
		return bookSearchUserRepository.existsByUserIdAndPassword(requestDto.getUserId(), requestDto.getPassword());
	}

	private BookSearchUser makeBookSearchUser(BookSearchUserRequestDto requestDto) {

		LocalDateTime nowDate = LocalDateTime.now();

		String bookSearchUserId = UUID.randomUUID().toString()+"_"+requestDto.getUserId();

		return bookSearchUserRepository.save(new BookSearchUser(bookSearchUserId, requestDto.getUserId(), requestDto.getPassword(), nowDate, nowDate));
	}

	private BookSearchUserDto tranformFrom(BookSearchUser bookSearchUser) {
		return new BookSearchUserDto(bookSearchUser.getUserId(), bookSearchUser.getCreatedAt(), bookSearchUser.getModifiedAt());
	}
}
