package com.sist.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.sist.vo.*;
import java.util.*;
import com.sist.service.*;
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
   private RequestCache requestCache=new HttpSessionRequestCache(); // 임시메모리에 저장해놓을까 생가중..
   private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
   private String defaultUrl;
   
   
   public void setDefaultUrl(String defaultUrl) {
      this.defaultUrl = defaultUrl;
   }
   
//   @Autowired
//   private MemberService mService; 

   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
         Authentication authentication) throws IOException, ServletException {
      // TODO Auto-generated method stub
      HttpSession session=request.getSession();
      
//      mService.lastLoginUpdate(authentication.getName());
      
//      MemberVO vo=mService.memberSessionData(authentication.getName());
//      SessionInfo info=new SessionInfo();
//      info.setUserId(vo.getUserId());
//      info.setUserName(vo.getUserName());
//      info.setEmail(vo.getEmail());
//      info.setPhone(vo.getPhone());
//      info.setAddress(vo.getAddr1()+" "+vo.getAddr2());
//      info.setSex(vo.getSex());
//      session.setAttribute("member", info);
      try {
         resultRedirectStrategy(request, response, authentication);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
       
   }
   
   protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
         Authentication authentication) throws Exception
   {
      SavedRequest savedRequest=requestCache.getRequest(request,response);
      if(savedRequest!=null)
      {
         String targetUrl=savedRequest.getRedirectUrl();
         redirectStrategy.sendRedirect(request, response, targetUrl);
      }
      else
      {
         redirectStrategy.sendRedirect(request, response, defaultUrl);
      }
   }

}