package com.sist.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 사이트 접속 시 자동 로그인
// xml <mvc:interceptor>
/*                                                  | ---> preHandle 이 여기서 사용됨
 *   사용자가 main.do 요청 ===> DispatcherServlet ===> HandlerMapping
 *                                    url주소를 이용해서 해당 메소드를 찾아준다
 *                                     구분자를 주기 위해 @GetMapping / @PostMapping 사용
 *                                           @GetMapping("main/main.do")
 *                                           public String main_main()
 *                                                      |
 *                                                      | ==> return ""
 *                                                      V ---> postHander 이 여기서 사용됨
 *                                                   main.do
 *                                                      |
 *                                ;                       | ==> Model(request)
 *                                                      V ---> afterCompletion 이 여기서 사용됨
 *                                                     JSP
 *     ====> 정리하면.. main.do를 찾기전에 session에 등록을 해줘야? 자동로그인이 된다
 *           맨 처음에 한번은 로그인을 해줘야 하고... 아이디랑 비밀번호를 쿠키?에 저장해둬야한다                                                 
 *                                                      
 */
public class AutoLoginInterceptor extends HandlerInterceptorAdapter{

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
         throws Exception {
      // TODO Auto-generated method stub
	   
	   //"food_"+no는 쿠키 고유번호 만들려고 적은부분임 
	   Cookie[] cookies=request.getCookies();
	   if(cookies!=null)
	   {
		   for(int i=0;i<cookies.length;i++)
		   {
			   String key=cookies[i].getName();
			   if(key.equals("userId")) //우리는 세션아이디 id로 설정하면됨
			   {
				   String id=cookies[i].getValue();
				   request.setAttribute("userId", id);
				   break;
			   }
		   }
	   }
      return super.preHandle(request, response, handler);
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
         ModelAndView modelAndView) throws Exception {
      // TODO Auto-generated method stub
      super.postHandle(request, response, handler, modelAndView);
   }

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
         throws Exception {
      // TODO Auto-generated method stub
      super.afterCompletion(request, response, handler, ex);
   }
   
}