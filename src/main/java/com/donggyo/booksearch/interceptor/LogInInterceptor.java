package com.donggyo.booksearch.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class LogInInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object handler) {

		HttpSession httpSession = req.getSession();

		if(httpSession == null) {
			log.error("empty session");
			return false;
		}

		String userId = (String) httpSession.getAttribute("userId");

		if(StringUtils.isEmpty(userId)) {
			log.error("empty userId");
			return false;
		}

		return true;
	}
}
