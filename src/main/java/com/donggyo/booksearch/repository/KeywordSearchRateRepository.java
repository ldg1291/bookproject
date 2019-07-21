package com.donggyo.booksearch.repository;

import com.donggyo.booksearch.entity.KeywordSearchRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordSearchRateRepository extends JpaRepository<KeywordSearchRate, Long> {

	List<KeywordSearchRate> findTop10ByOrderBySearchNumberDesc();

	boolean existsByKeyword(String keyword);

	KeywordSearchRate findByKeyword(String keyword);

	@Query("update KeywordSearchRate r set r.searchNumber = ?2 where r.keyword = ?1")
	KeywordSearchRate updateSearchNumberByKeyword(String keyword, Long searchNumber);
}
