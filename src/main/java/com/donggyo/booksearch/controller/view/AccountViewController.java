package com.donggyo.booksearch.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountViewController {

	@RequestMapping("/account")
	public String accounts() {
		return "account_view";
	}
}
