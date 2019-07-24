package com.donggyo.booksearch.controller.api;

import com.donggyo.booksearch.dto.KeywordSearchRateDto;
import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.service.KeywordSearchRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PopularKeywordController {

	@Autowired
	private KeywordSearchRateService keywordSearchRateService;

	@GetMapping("/api/book/popular-keywords")
	@ResponseBody
	public ResponseDto<List<KeywordSearchRateDto>> mostPopularKeywords() {
		return keywordSearchRateService.getTop10MostSearchedKeywords();
	}
}
