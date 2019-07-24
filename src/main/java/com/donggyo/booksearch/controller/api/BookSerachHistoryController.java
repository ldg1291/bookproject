package com.donggyo.booksearch.controller.api;

import com.donggyo.booksearch.dto.BookSearchHistoryDto;
import com.donggyo.booksearch.dto.ResponseDto;
import com.donggyo.booksearch.service.BookSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookSerachHistoryController {

	@Autowired
	private BookSearchHistoryService bookSearchHistoryService;

	@RequestMapping(value = "/api/book/history", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<List<BookSearchHistoryDto>> getHistory(HttpServletRequest req) {

		String userId = (String)req.getSession().getAttribute("userId");

		return bookSearchHistoryService.findRecentHistories(userId);
	}

}
