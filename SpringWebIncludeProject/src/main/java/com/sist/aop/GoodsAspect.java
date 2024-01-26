package com.sist.aop;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.dao.GoodsDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.GoodsAllVO;

@Aspect
@Component
public class GoodsAspect {
	@Autowired
	private GoodsDAO dao;
	
	@After("execution(* com.sist.web.GoodsController.goods_list(..))")
	public void cookieSend() //여기는 매개변수 사용불가능
	{
		HttpServletRequest request=
	            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				
		Cookie[] cookies=request.getCookies();
		List<GoodsAllVO> gList=new ArrayList<GoodsAllVO>();
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("goods_"))
				{
					String no=cookies[i].getValue();
					GoodsAllVO vo=dao.goodsCookieData(Integer.parseInt(no));
					gList.add(vo);
				}
			}
		}
		request.setAttribute("count", gList.size());
		request.setAttribute("gList", gList);
	}
}
