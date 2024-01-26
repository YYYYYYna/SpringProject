package com.sist.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;
import com.sist.vo.SeoulVO;

@Controller
public class SeoulController {
	
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping
	public String seoul_list(String page, Model model)
	{
		if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   int rowSize=12;
		   int start=(rowSize*curpage)-(rowSize-1);
	   	   int end=rowSize*curpage;
	   	   
	   	   //map사용할때 이렇게 정리하면됨!!
	   	   Map map=new HashMap();
	   	   map.put("pStart", start);
	   	   map.put("pEnd", end);
	   	   List<SeoulVO> list=dao.seoulListData(map);
	   	   
	   	   int totalpage=dao.seoulTotalPage(map);
	   	   
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
		model.addAttribute("main_jsp","../seoul/list.jsp");
		return "main/main";
	}
	/*
	@RequestMapping("seoul/find.do")
	public String seoul_find(String page, String tablename, String ss, Model model)
	{
		if(tablename==null)
			tablename="seoul_location";
		if(ss==null)
			ss="봉은사";
		
	}
	*/
}
