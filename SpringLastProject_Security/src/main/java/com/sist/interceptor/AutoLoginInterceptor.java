package com.sist.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// ����Ʈ ���� �� �ڵ� �α���
// xml <mvc:interceptor>
/*                                                  | ---> preHandle �� ���⼭ ����
 *   ����ڰ� main.do ��û ===> DispatcherServlet ===> HandlerMapping
 *                                    url�ּҸ� �̿��ؼ� �ش� �޼ҵ带 ã���ش�
 *                                     �����ڸ� �ֱ� ���� @GetMapping / @PostMapping ���
 *                                           @GetMapping("main/main.do")
 *                                           public String main_main()
 *                                                      |
 *                                                      | ==> return ""
 *                                                      V ---> postHander �� ���⼭ ����
 *                                                   main.do
 *                                                      |
 *                                ;                       | ==> Model(request)
 *                                                      V ---> afterCompletion �� ���⼭ ����
 *                                                     JSP
 *     ====> �����ϸ�.. main.do�� ã������ session�� ����� �����? �ڵ��α����� �ȴ�
 *           �� ó���� �ѹ��� �α����� ����� �ϰ�... ���̵�� ��й�ȣ�� ��Ű?�� �����ص־��Ѵ�                                                 
 *                                                      
 */
public class AutoLoginInterceptor extends HandlerInterceptorAdapter{

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
         throws Exception {
      // TODO Auto-generated method stub
	   
	   //"food_"+no�� ��Ű ������ȣ ������� �����κ��� 
	   Cookie[] cookies=request.getCookies();
	   if(cookies!=null)
	   {
		   for(int i=0;i<cookies.length;i++)
		   {
			   String key=cookies[i].getName();
			   if(key.equals("userId")) //�츮�� ���Ǿ��̵� id�� �����ϸ��
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