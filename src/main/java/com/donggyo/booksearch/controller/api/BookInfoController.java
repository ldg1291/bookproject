package com.donggyo.booksearch.controller.api;

import com.donggyo.booksearch.dto.bookinfo.BookInfoDto;
import com.donggyo.booksearch.dto.page.PagedObjectDto;
import com.donggyo.booksearch.enums.SearchType;
import com.donggyo.booksearch.enums.SortType;
import com.donggyo.booksearch.service.BookSearchAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookInfoController {

	@Autowired
	private BookSearchAggregateService bookSearchAggregateService;

	@GetMapping("/api/book/search")
	@ResponseBody
	public PagedObjectDto<BookInfoDto> getBook(@RequestParam(required = true)String query, @RequestParam(required = false) SortType sort, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size, @RequestParam(required = false) SearchType target) {
		return bookSearchAggregateService.searchBookInfo(query, sort, page, size, target);
	}
}
