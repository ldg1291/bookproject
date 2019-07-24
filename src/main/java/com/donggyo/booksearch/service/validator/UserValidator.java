package com.donggyo.booksearch.service.validator;

import com.donggyo.booksearch.dto.BookSearchUserRequestDto;
import com.donggyo.booksearch.exception.UserException;
import com.donggyo.booksearch.repository.BookSearchUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class UserValidator {

	private static final String ALREADY_EXISTS_USER = "이미 존재하는 ID입니다. 다른 아이디를 사용해주세요";
	private static final String EMPTY_PASSWORD = "비밀번호는 빈 값이 될 수 없습니다";
	private static final String EMPTY_USER_ID = "ID는 빈 값이 될 수 없습니다";

	@Autowired
	private BookSearchUserRepository bookSearchUserRepository;

	private final String EMPTY_SHA_256 = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

	public void validateUser(BookSearchUserRequestDto requestDto) {

		if (StringUtils.isEmpty(requestDto.getPassword()) || requestDto.getPassword().equals(EMPTY_SHA_256)) {
			throw new UserException(EMPTY_PASSWORD);
		}

		if (StringUtils.isEmpty(requestDto.getUserId())) {
			throw new UserException(EMPTY_USER_ID);
		}

		if (checkIfUserExistsWithUserId(requestDto.getUserId())) {
			throw new UserException(ALREADY_EXISTS_USER);
		}
	}

	private boolean checkIfUserExistsWithUserId(String userId) {
		return bookSearchUserRepository.existsByUserId(userId);
	}
}
