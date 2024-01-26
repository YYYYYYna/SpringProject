package com.sist.web;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.GoodsDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.GoodsAllVO;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO gdao;
	
	@GetMapping("goods/list.do")
	   public String goods_list(String page,Model model)
	   {
		  if(page==null)
			  page="1";
		  int curpage=Integer.parseInt(page);
		  int rowSize=12;
		  int start=(rowSize*curpage)-(rowSize-1);
		  int end=rowSize*curpage;
		  
		  List<GoodsAllVO> glist=gdao.goodsListData(start, end);
		  int totalpage=gdao.goodsTotalPage();
		  
		  final int BLOCK=10;
		  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		  
		  if(endPage>totalpage)
			  endPage=totalpage;
		  
		  model.addAttribute("curpage", curpage);
		  model.addAttribute("totalpage", totalpage);
		  model.addAttribute("startPage", startPage);
		  model.addAttribute("endPage", endPage);
		  model.addAttribute("glist", glist);
		  
		  //쿠키전송 => 출력부가 많으면 aop로 반복출력하는것도 방법
		  
		  model.addAttribute("main_jsp", "../goods/list.jsp");
		  return "main/main";
	   }
	
	@GetMapping("goods/detail_before.do")
	public String goods_detail_before(int no, HttpServletResponse response, RedirectAttributes ra)
	{
		Cookie cookie=new Cookie("goods_"+no, String.valueOf(no));
		   cookie.setPath("/");
		   cookie.setMaxAge(60*60*24);
		   response.addCookie(cookie);
		   //sendRedirect => 값 전송
		   ra.addAttribute("no", no);
		   return "redirect:../goods/detail.do";
	}
	
	@GetMapping("goods/detail.do")
	public String goods_detail(int no, Model model)
	{
		GoodsAllVO gvo=gdao.goodsDetailData(no);
		model.addAttribute("gvo", gvo);
		model.addAttribute("main_jsp", "../goods/detail.jsp");
		return "main/main";
	}
	
	@RequestMapping("goods/find.do")
	public String goods_find(String page, String tablename, String ss, Model model)
	{
		if(tablename==null)
			   tablename="goods_all";
		   if(ss==null)
			   ss="홍삼";
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   int rowSize=12;
		   int start=(rowSize*curpage)-(rowSize-1);
	   	   int end=rowSize*curpage;
	   	   //map사용할때 이렇게 정리하면됨!!
	   	   Map map=new HashMap();
	   	   map.put("table_name", tablename);
	   	   map.put("ss", ss);
	   	   map.put("start", start);
	   	   map.put("end", end);
		   List<GoodsAllVO> glist=gdao.goodsFindData(map);
		   
		   int totalpage=gdao.goodsFindTotalPage(map);
		   
		   final int BLOCK=10;
			  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			  
			  if(endPage>totalpage)
				  endPage=totalpage;
			  
			  model.addAttribute("curpage", curpage);
			  model.addAttribute("totalpage", totalpage);
			  model.addAttribute("startPage", startPage);
			  model.addAttribute("endPage", endPage);
			  model.addAttribute("glist", glist);
			  model.addAttribute("ss", ss);
			  model.addAttribute("tablename", tablename);
			  
			  //쿠키전송 => 출력부가 많으면 aop로 반복출력하는것도 방법
			  
			  model.addAttribute("main_jsp", "../goods/find.jsp");
			  return "main/main";
	}
}
