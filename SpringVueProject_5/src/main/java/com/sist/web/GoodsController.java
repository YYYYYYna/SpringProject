package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.dao.FoodVO;
import com.sist.dao.GoodsDAO;
import com.sist.dao.GoodsVO;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("goods/list.do")
	public String goods_list()
	{
		return "goods/list";
	}
	
	@GetMapping(value = "goods/list_vue.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String goods_list_vue(int page)
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<GoodsVO> list=dao.goodsListData(map);
		int totalPage=dao.goodsTotalPage();
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
		for(GoodsVO vo:list)
		{
			JSONObject obj=new JSONObject();//vo를 담는 객체
			
			obj.put("no", vo.getNo()); 
			obj.put("name", vo.getGoods_name());
			obj.put("poster", vo.getGoods_poster());
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
	
	@GetMapping("goods/page_vue.do")
	public String goods_page_vue(int page)
	{
		return "";
	}
	
}
