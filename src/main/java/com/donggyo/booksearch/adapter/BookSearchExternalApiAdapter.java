package com.donggyo.booksearch.adapter;

import com.donggyo.booksearch.dto.bookinfo.BookSearchResultDto;
import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoDto;
import com.donggyo.booksearch.dto.bookinfo.kakao.BookInfoMeta;
import com.donggyo.booksearch.dto.bookinfo.naver.NaverBookSearchResultDto;
import com.donggyo.booksearch.enums.SearchCompanyType;
import com.donggyo.booksearch.enums.SearchType;
import com.donggyo.booksearch.enums.SortType;
import com.donggyo.booksearch.exception.BookSearchException;
import com.donggyo.booksearch.mapper.BookMetaMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BookSearchExternalApiAdapter {

	@Value("${kakao.api.key}")
	private String kakaoApiKey;
	@Value("${naver.api.key}")
	private String naverApiKey;
	@Value("${naver.api.password}")
	private String naverApiPassword;

	private static final String NO_SEARCH_RESULT = "No Search Result";

	@Autowired
	private RestTemplate restTemplate;

	public BookSearchResultDto getBook(String query, SortType sort, Integer page, Integer size, SearchType target) {
		String queryParam = buildQuery(query, sort, page, size, target, SearchCompanyType.KAKAO);
		HttpEntity<Map<String, String>> header = makeKakaoHttpEntity();

		ResponseEntity responseEntity;

		try {
			responseEntity = restTemplate.exchange(queryParam, HttpMethod.GET, header, BookSearchResultDto.class);
		} catch (Exception e) {
			try {
				queryParam = buildQuery(query, sort, page, size, target, SearchCompanyType.NAVER);
				header = makeNaverHttpEntity();
				responseEntity = restTemplate.exchange(queryParam, HttpMethod.GET, header, NaverBookSearchResultDto.class);
			} catch (RestClientException ex) {
				log.warn("BookSearchExternalApiAdapter.getBook : {}", ex.getMessage());
				throw new BookSearchException(ex.getMessage());
			}
		}

		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			if(responseEntity.getBody() instanceof NaverBookSearchResultDto) {
				return transformFrom((NaverBookSearchResultDto)responseEntity.getBody());
			}
			return (BookSearchResultDto)responseEntity.getBody();
		} else {
			throw new BookSearchException(responseEntity.getStatusCode().toString());
		}
	}

	private BookSearchResultDto transformFrom(NaverBookSearchResultDto body) {
		if( CollectionUtils.isEmpty(body.getDocuments()) || body.getMeta()==null) {
			throw new BookSearchException(NO_SEARCH_RESULT);
		}

		List<BookInfoDto> list = body.getDocuments().stream().map(BookMetaMapper::transformFrom).collect(Collectors.toList());
		BookInfoMeta bookInfoMeta = BookMetaMapper.transformFrom(body.getMeta());
		return new BookSearchResultDto(list, bookInfoMeta);
	}

	private HttpEntity<Map<String, String>> makeKakaoHttpEntity() {

		MultiValueMap<String, String> header =
			new LinkedMultiValueMap(Maps.newHashMap(
				new ImmutableMap.Builder<String, List<String>>().put("Authorization", Lists.newArrayList("KakaoAK " + kakaoApiKey)).build()));

		return new HttpEntity<>(header);
	}

	private HttpEntity<Map<String, String>> makeNaverHttpEntity() {

		MultiValueMap<String, String> header =
			new LinkedMultiValueMap(Maps.newHashMap(
				new ImmutableMap.Builder<String, List<String>>().put("X-Naver-Client-Id", Lists.newArrayList(naverApiKey)).put("X-Naver-Client-Secret", Lists.newArrayList(naverApiPassword)).build()));

		return new HttpEntity<>(header);
	}

	private String buildQuery(String query, SortType sort, Integer page, Integer size, SearchType target, SearchCompanyType type) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(type.getUrl());

		if(type == SearchCompanyType.NAVER) {
			if (target != SearchType.NONE) {
				stringBuilder.append(SearchType.getDescByEnumAndCompany(type, target)+"=").append(query);
			} else {
				stringBuilder.append("query=").append(query);
			}
			if(page != null) {
				stringBuilder.append("&start=").append((page-1)*size+1);
			}
			if(size != null) {
				stringBuilder.append("&display=").append(size.toString());
			}
		} else {
			if (!StringUtils.isEmpty(query)) {
				stringBuilder.append("query=").append(query);
			}
			if (!StringUtils.isEmpty(target)) {
				stringBuilder.append("&target=").append(target);
			}
			if(page != null) {
				stringBuilder.append("&page=").append(page.toString());
			}
			if(size != null) {
				stringBuilder.append("&size=").append(size.toString());
			}
		}
		if (!StringUtils.isEmpty(sort)) {
			stringBuilder.append("&sort=").append(SortType.getDescByEnumAndCompany(type, sort));
		}


		return stringBuilder.toString();
	}
}
