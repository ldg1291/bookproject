package com.donggyo.booksearch.repository;

import com.donggyo.booksearch.entity.BookSearchUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSearchUserRepository extends JpaRepository<BookSearchUser, String> {

	BookSearchUser getUserByUserId(String userId);

	boolean existsByUserId(String userId);
	boolean existsByUserIdAndPassword(String userId, String password);
}
