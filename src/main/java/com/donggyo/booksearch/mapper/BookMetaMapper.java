package com.donggyo.booksearch.mapper;

import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoDto;
import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoMeta;
import com.donggyo.booksearch.dto.bookinfo.naver.NaverBookInfoDto;
import com.donggyo.booksearch.dto.bookinfo.naver.NaverBookInfoMeta;
import com.google.common.collect.Lists;

public class BookMetaMapper {

	public static BookInfoMeta transformFrom(NaverBookInfoMeta naverBookInfoMeta) {

		boolean isEnd = naverBookInfoMeta.getDisplay() < naverBookInfoMeta.getTotal();

		return new BookInfoMeta(isEnd, naverBookInfoMeta.getDisplay(), naverBookInfoMeta.getTotal());
	}

	public static BookInfoDto transformFrom(NaverBookInfoDto naverBookInfoDto) {
		return new BookInfoDto(naverBookInfoDto.getTitle(), naverBookInfoDto.getImage(), naverBookInfoDto.getDescription(), naverBookInfoDto.getIsbn(),
			Lists.newArrayList(naverBookInfoDto.getAuthor()), naverBookInfoDto.getPublisher(), naverBookInfoDto.getPubdate(), naverBookInfoDto.getDiscount(), naverBookInfoDto.getPrice(), "", "", Lists.newArrayList());
	}
}
