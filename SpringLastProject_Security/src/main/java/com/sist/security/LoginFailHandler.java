package com.sist.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
/*
 *   [security로 수정하는 과정]
 *   1. web.xml 수정
 *   2. application-security.xml 생성
 *   3. @Getmapping("member/login_ok_vue.do") 제거하기
 *   4. 매개변수 수정 : HttpSession session ==> Principal p로 수정 : 세션으로 값 안받음
 *   5. header 변경 (권한별 메뉴 출력범위 조정)
 *   6. 댓글 controller부분 수정
 *   7. persistent_logins 테이블 만들어서 자동로그인 되도록 하기
 */
public class LoginFailHandler implements AuthenticationFailureHandler {
   
   private String defaultFailureUrl;
   
   public void setDefaultFailureUrl(String defaultFailureUrl) {
      this.defaultFailureUrl = defaultFailureUrl;
   }
   
   @Override
   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
         AuthenticationException exception) throws IOException, ServletException {
      // TODO Auto-generated method stub
      String errorMsg="아이디 또는 비밀번호가 일치하지 않습니다";
      try
      {
         if(exception instanceof BadCredentialsException)
         {
            //비밀번호가 틀린 경우
            errorMsg="아이디 또는 비밀번호가 일치하지 않습니다";
         }
         else if(exception instanceof InternalAuthenticationServiceException)
         {
            // 아이디가 없는 경우
            errorMsg="아이디 또는 비밀번호가 일치하지 않습니다";
         }
         else if(exception instanceof DisabledException)
         {
            // 계정이 비활성화 되었을 때
            errorMsg="휴면계정입니다";
         }
      }catch(Exception ex) {}
      request.setAttribute("message", errorMsg);
      request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
   }
   

}