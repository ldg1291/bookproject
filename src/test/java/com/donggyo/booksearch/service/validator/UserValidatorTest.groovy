package com.donggyo.booksearch.service.validator

import com.donggyo.booksearch.dto.BookSearchUserRequestDto
import com.donggyo.booksearch.exception.UserException
import com.donggyo.booksearch.repository.BookSearchUserRepository
import spock.lang.Specification

class UserValidatorTest extends Specification {

	UserValidator sut
	BookSearchUserRepository bookSearchUserRepository;

	void setup() {
		bookSearchUserRepository = Mock(BookSearchUserRepository)
		sut = new UserValidator(bookSearchUserRepository: bookSearchUserRepository)
	}

	def "existing userId" () {
		given :
		bookSearchUserRepository.existsByUserId("userId") >> true

		when :
		sut.validateUser(new BookSearchUserRequestDto(userId: "userId", password: "password"))

		then :
		thrown UserException
	}

	def "userId empty" () {
		given :

		when :
		sut.validateUser(new BookSearchUserRequestDto(userId: "", password: "password"))

		then :
		thrown UserException
	}

	def "password empty" () {
		given :

		when :
		sut.validateUser(new BookSearchUserRequestDto(userId: "userId", password: "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"))

		then :
		thrown UserException
	}

	def "all normal case" () {
		given :
		bookSearchUserRepository.existsByUserId("userId") >> false

		when :
		sut.validateUser(new BookSearchUserRequestDto(userId: "userId", password: "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b85"))

		then :
		noExceptionThrown()
	}
}
