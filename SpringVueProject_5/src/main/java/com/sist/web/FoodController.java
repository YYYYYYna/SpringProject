package com.sist.web;
import java.util.*;
import com.sist.dao.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodController {
	
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/list.do")
	public String food_list()
	{
		return "food/list";
	}
	
	@GetMapping(value = "food/list_vue.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody //json형식으로 넘김!!
	//@RestController가 @ResponseBody의 발전형
	public String food_list_vue(int page)
	{
		//VueJS는 연결전에 초기값설정 가능 그래서 String이 아니라 int로 설정 가능
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<FoodVO> list=dao.foodListData(map);
		int totalPage=dao.foodTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage)
			endPage=totalPage;
		int i=0;
		/*
		 * [] 만들어주면 JSONArray
		 * {} 만들어주면 JSONObject
		 */
		JSONArray arr=new JSONArray();
		for(FoodVO vo:list)
		{
			JSONObject obj=new JSONObject();//vo를 담는 객체
			
			obj.put("fno", vo.getFno()); 
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			//{"fno":1,"name":"aaa","poster":"..."... 이렇게 만드는 과정
			
			if(i==0)
			{
				obj.put("curpage", page);
				obj.put("totalPage", totalPage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	//데이터랑 페이지를 한번에 변수받을수 없으니까 페이지를 따로만듬!!
	@GetMapping("food/page_vue.do")
	public String food_page_vue(int page)
	{
		return "";
	}
}
