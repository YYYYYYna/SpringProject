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
 *   [security�� �����ϴ� ����]
 *   1. web.xml ����
 *   2. application-security.xml ����
 *   3. @Getmapping("member/login_ok_vue.do") �����ϱ�
 *   4. �Ű����� ���� : HttpSession session ==> Principal p�� ���� : �������� �� �ȹ���
 *   5. header ���� (���Ѻ� �޴� ��¹��� ����)
 *   6. ��� controller�κ� ����
 *   7. persistent_logins ���̺� ���� �ڵ��α��� �ǵ��� �ϱ�
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
      String errorMsg="���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�";
      try
      {
         if(exception instanceof BadCredentialsException)
         {
            //��й�ȣ�� Ʋ�� ���
            errorMsg="���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�";
         }
         else if(exception instanceof InternalAuthenticationServiceException)
         {
            // ���̵� ���� ���
            errorMsg="���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�";
         }
         else if(exception instanceof DisabledException)
         {
            // ������ ��Ȱ��ȭ �Ǿ��� ��
            errorMsg="�޸�����Դϴ�";
         }
      }catch(Exception ex) {}
      request.setAttribute("message", errorMsg);
      request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
   }
   

}