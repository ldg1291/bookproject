package com.donggyo.booksearch.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookInfoViewController {

	@RequestMapping("/books")
	public String bookPage() {
		return "/search_view";
	}
}
