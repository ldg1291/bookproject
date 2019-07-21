package com.donggyo.booksearch.service

import com.donggyo.booksearch.dto.BookSearchUserRequestDto
import com.donggyo.booksearch.repository.BookSearchUserRepository
import spock.lang.Specification

class BookSearchUserServiceTest extends Specification {

	def ALREADY_EXISTS_USER = "이미 존재하는 ID입니다. 다른 아이디를 사용해주세요";
	def WRONG_LOG_IN_DATA = "잘못된 로그인 정보입니다. 올바른 정보를 입력해주세요";

	BookSearchUserService sut
	BookSearchUserRepository bookSearchUserRepository

	void setup() {
		bookSearchUserRepository = Mock(BookSearchUserRepository)

		sut = new BookSearchUserService(bookSearchUserRepository: bookSearchUserRepository)
	}

	def "create new user fails when the user put existing value for userId" () {
		given:
		bookSearchUserRepository.existsByUserId("existing_id") >> true

		when:
		def res = sut.createUser(new BookSearchUserRequestDto(userId: "existing_id", password: "password"))

		then:
		res.success == Boolean.FALSE
		res.message == ALREADY_EXISTS_USER
		res.data == null
	}

	def "create new user succeed when the user put not existing value for userId"() {

		given:
		bookSearchUserRepository.existsByUserId("not_existing_id") >> false

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
		res == false
	}



	def "create new user fails when the user put wrong userId and password"() {
		given:
		bookSearchUserRepository.existsByUserIdAndPassword("existing_id", "password") >> true

		when:
		def res = sut.logInWithUser(new BookSearchUserRequestDto(userId: "existing_id", password: "password"))

		then:
		res == true
	}
}
