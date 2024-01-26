package com.sist.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FoodInterceptor extends HandlerInterceptorAdapter{
    
	//main.do 찾기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("===== preHandle() Call... =====");
		return super.preHandle(request, response, handler);
	}

	//jsp로 넘어가기 전
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("===== afterComplement =====");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	
}
