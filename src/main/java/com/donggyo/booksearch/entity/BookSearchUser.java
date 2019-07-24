package com.donggyo.booksearch.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "book_service_user")
@EqualsAndHashCode(of = {"userId"})
public class BookSearchUser implements Serializable {

	@Id
	@Column(name = "book_search_user_id")
	private String bookSearchUserId;

	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "modified_at", nullable = false)
	private LocalDateTime modifiedAt;

	public BookSearchUser(String bookSearchUserId, String userId, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
		this.bookSearchUserId = bookSearchUserId;
		this.userId = userId;
		this.password = password;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
}
