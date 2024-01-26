package com.sist.web;

import javax.servlet.http.Cookie;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.vo.*;

@Controller
public class FoodController {
	
	//주소값 필요해서 불러옴
	@Autowired
	private FoodDAO dao;
	
	@Autowired
	private ReplyDAO rdao;
	
   // 쿠키저장과정
   // food/detail_before.do?fno=${vo.fno }
   @GetMapping("food/detail_before.do")
   public String food_detail_before(int fno,HttpServletResponse response,RedirectAttributes ra)
   {
	   //쿠키는 일반객체라서 매개변수로 받을수 없다.
	   Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno));
	   cookie.setPath("/");
	   cookie.setMaxAge(60*60*24);
	   response.addCookie(cookie);
	   //sendRedirect => 값 전송
	   ra.addAttribute("fno", fno);
	   return "redirect:../food/detail.do";
   }
   
   //get이랑 post중에 어떤건지 구별하는거 중요함!!
   @GetMapping("food/detail.do")
   public String food_detail(int fno, Model model)
   {
	   FoodVO vo=dao.foodDetailData(fno);
	   List<ReplyVO> rList=rdao.replyListData(fno);
	   model.addAttribute("vo", vo);
	   model.addAttribute("rList", rList);
	   model.addAttribute("main_jsp", "../food/detail.jsp");
	   return "main/main";
   }
   
   //검색에는 get(페이지나누기),post(form데이터받기)둘다 쓰임
   @RequestMapping("food/find.do")
   public String food_find(String page,String colname,String ss,Model model)
   {
	   if(colname==null)
		   colname="type";
	   if(ss==null)
		   ss="한식";
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1);
   	   int end=rowSize*curpage;
   	   //map사용할때 이렇게 정리하면됨!!
   	   Map map=new HashMap();
   	   map.put("col_name", colname);
   	   map.put("ss", ss);
   	   map.put("start", start);
   	   map.put("end", end);
   	   List<FoodVO> list=dao.foodFindData(map);
   	   
   	   int totalpage=dao.foodFindTotalPage(map);
   	   
   	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	  
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  model.addAttribute("curpage", curpage);
	  model.addAttribute("totalpage", totalpage);
	  model.addAttribute("startPage", startPage);
	  model.addAttribute("endPage", endPage);
	  model.addAttribute("list", list);
	  model.addAttribute("ss", ss);
	  model.addAttribute("colname", colname);
	  
	  //쿠키전송 => 출력부가 많으면 aop로 반복출력하는것도 방법
	  
	  model.addAttribute("main_jsp", "../food/find.jsp");
	  return "main/main";
   }
   
}