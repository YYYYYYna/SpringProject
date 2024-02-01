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
	@ResponseBody //json�������� �ѱ�!!
	//@RestController�� @ResponseBody�� ������
	public String food_list_vue(int page)
	{
		//VueJS�� �������� �ʱⰪ���� ���� �׷��� String�� �ƴ϶� int�� ���� ����
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
		 * [] ������ָ� JSONArray
		 * {} ������ָ� JSONObject
		 */
		JSONArray arr=new JSONArray();
		for(FoodVO vo:list)
		{
			JSONObject obj=new JSONObject();//vo�� ��� ��ü
			
			obj.put("fno", vo.getFno()); 
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			//{"fno":1,"name":"aaa","poster":"..."... �̷��� ����� ����
			
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
	
	//�����Ͷ� �������� �ѹ��� ���������� �����ϱ� �������� ���θ���!!
	@GetMapping("food/page_vue.do")
	public String food_page_vue(int page)
	{
		return "";
	}
}
