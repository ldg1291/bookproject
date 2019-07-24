package com.donggyo.booksearch.dto.bookinfo.naver;

import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoDto;
import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoMeta;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class NaverBookSearchResultDto implements Serializable {

	private List<NaverBookInfoDto> documents;
	private NaverBookInfoMeta meta;
}
