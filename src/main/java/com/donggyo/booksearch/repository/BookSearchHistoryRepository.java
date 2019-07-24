package com.donggyo.booksearch.repository;

import com.donggyo.booksearch.entity.BookSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookSearchHistoryRepository extends JpaRepository<BookSearchHistory, Long> {

	List<BookSearchHistory> findAllByUserIdOrderBySearchDateDesc(String userId);
}
