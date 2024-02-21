package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.manager.NewsManager;
import com.sist.service.*;
import com.sist.vo.*;

@Aspect
@Component
public class CommonsSendAop {
	
	@Autowired
	private NoticeService nservice;
	
	@Autowired
	private NewsManager mgr;
	
	//Controller��� �̸����� ������ ��Ұ� ȣ��Ǹ� ������ �����ϵ�����
	 @After("execution(* com.sist.web.*Controller.*(..))")
	public void footerSend()
	{
		List<FoodVO> flist=nservice.foodTop7();
		List<NoticeVO> nlist=nservice.noticeTop7();
		List<NewsVO> newslist=mgr.newsFind("����");
		
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		request.setAttribute("flist", flist);
		request.setAttribute("nlist", nlist);
		request.setAttribute("newslist", newslist);
	}
}
