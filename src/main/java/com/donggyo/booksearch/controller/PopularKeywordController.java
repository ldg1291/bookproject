package com.donggyo.booksearch.controller;

import com.donggyo.booksearch.entity.KeywordSearchRate;
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

	@GetMapping("/popular-keywords")
	@ResponseBody
	public List<KeywordSearchRate> mostPopularKeywords() {
		return keywordSearchRateService.getTop10MostSearchedKeywords();
	}
}
