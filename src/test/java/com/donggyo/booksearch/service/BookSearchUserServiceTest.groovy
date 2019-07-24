package com.donggyo.booksearch.service

import com.donggyo.booksearch.dto.BookSearchUserRequestDto
import com.donggyo.booksearch.entity.BookSearchUser
import com.donggyo.booksearch.exception.UserException
import com.donggyo.booksearch.repository.BookSearchUserRepository
import com.donggyo.booksearch.service.validator.UserValidator
import spock.lang.Specification

import java.time.LocalDateTime

class BookSearchUserServiceTest extends Specification {

	BookSearchUserService sut
	BookSearchUserRepository bookSearchUserRepository
	UserValidator userValidator

	void setup() {
		bookSearchUserRepository = Mock(BookSearchUserRepository)
		userValidator = Mock(UserValidator)

		sut = new BookSearchUserService(bookSearchUserRepository: bookSearchUserRepository, userValidator: userValidator)
	}

	def "create new user fails when the validator throws exception "() {
		given:
		userValidator.validateUser(_) >> {throw new UserException("error")}

		when:
		def res = sut.createUser(new BookSearchUserRequestDto(userId: "existing_id", password: "password"))

		then:
		res.success == Boolean.FALSE
		res.message == "error"
		res.data == null
	}

	def "create new user succeed when the validator not throws exception"() {

		given:
		bookSearchUserRepository.save(_) >> new BookSearchUser(bookSearchUserId: "id", userId: "not_existing_id", createdAt: LocalDateTime.now(), modifiedAt: LocalDateTime.now())

		when:
		def res = sut.createUser(new BookSearchUserRequestDto(userId: "not_existing_id", password: "password"))

		then:
		res.success == Boolean.TRUE
		res.message == ""
		res.data.userId == "not_existing_id"
	}

	def "create new user succeed when the user put right userId and password"() {
		given:
		bookSearchUserRepository.existsByUserIdAndPassword("not_existing_id", "password") >> false

		when:
		def res = sut.logInWithUser(new BookSearchUserRequestDto(userId: "not_existing_id", password: "password"))

		then:
		res.isSuccess() == false
	}


	def "create new user fails when the user put wrong userId and password"() {
		given:
		bookSearchUserRepository.existsByUserIdAndPassword("existing_id", "password") >> true

		when:
		def res = sut.logInWithUser(new BookSearchUserRequestDto(userId: "existing_id", password: "password"))

		then:
		res.isSuccess() == true
	}
}
