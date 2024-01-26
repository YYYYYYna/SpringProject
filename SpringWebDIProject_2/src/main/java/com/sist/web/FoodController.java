package com.sist.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;

@Controller
public class FoodController {
	
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping("food/list.do")
	public String food_list(String page, Model model)
	                        /*
	                         * request랑 response가 없어지고
	                         * Model이란 전송객체가 새로 생김
	                         * 이걸 dispatcherServlet이 대신해줌..
	                         */
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		List<FoodVO> list=dao.foodListData(start, end);
		
		int totalpage=dao.foodTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("list",list);
		
		return "food/list";
	}
	
	@RequestMapping("food/detail.do")
	public String food_detail(int fno, Model model)
	                         /*
	                          * fno는 null값이 없어서 int형이구..
	                          * page는 맨처음한번이 null이라서 String...
	                          * 값을 받는걸 직관적으로 받을수 잇게된듯...
	                          */
	{
		FoodVO vo=dao.foodDetailData(fno);
		model.addAttribute("vo",vo);
		return "food/detail";
	}
	
	//검색하기
	@RequestMapping("food/find.do")
	public String food_find()
	{
		return "food/find";
	}
}
