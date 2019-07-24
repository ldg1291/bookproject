package com.donggyo.booksearch.service;

import com.donggyo.booksearch.dto.KeywordSearchRateDto;
import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.entity.KeywordSearchRate;
import com.donggyo.booksearch.repository.KeywordSearchRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class KeywordSearchRateService {

	private static final Long FIRST_SEARCH = 1L;

	@Autowired
	private KeywordSearchRateRepository keywordSearchRateRepository;

	public void saveOrUpdate(String keyword) {
		KeywordSearchRate keywordSearchRate = keywordSearchRateRepository.findByKeyword(keyword);

		if (keywordSearchRate != null) {
			keywordSearchRateRepository.save(
				new KeywordSearchRate(keywordSearchRate.getKeywordSearchRateId(), keyword, keywordSearchRate.getSearchNumber() + 1));
		} else {
			keywordSearchRateRepository.save(new KeywordSearchRate(UUID.randomUUID().toString() + "_" + keyword, keyword, FIRST_SEARCH));
		}
	}

	public ResponseDto<List<KeywordSearchRateDto>> getTop10MostSearchedKeywords() {

		List<KeywordSearchRateDto> searchRates =
			keywordSearchRateRepository.findTop10ByOrderBySearchNumberDesc().stream().map(r -> new KeywordSearchRateDto(r.getKeyword(), r.getSearchNumber())).collect(Collectors.toList());

		return new ResponseDto(searchRates);
	}
}
