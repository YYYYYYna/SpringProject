package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;
import com.sist.vo.FoodVO;

/*
 * dispatcherServlet을 이용해서 데이터를 매개변수로 받는 클래스는 2개밖에 없음
 * @Controller, @RestController 즉 Model 클래스에서만 매개변수를 사용할수 있음
 */

@Aspect
@Component
public class FoodAspect {
	
	@Autowired
	private FoodDAO dao;
	
	//finally역할수행 => 무조건 전송함                        
	@After("execution(* com.sist.web.MainController.main_main(..))")
	public void cookieSend() //여기는 매개변수 사용불가능
	{
		HttpServletRequest request=
	            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				
		Cookie[] cookies=request.getCookies();
		List<FoodVO> cList=new ArrayList<FoodVO>();
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("food_"))
				{
					String fno=cookies[i].getValue();
					FoodVO vo=dao.foodCookieData(Integer.parseInt(fno));
					cList.add(vo);
				}
			}
		}
		request.setAttribute("count", cList.size());
		request.setAttribute("clist", cList);
	}
	

}
