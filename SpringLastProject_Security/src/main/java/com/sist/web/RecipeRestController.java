package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.RecipeService;
import com.sist.service.ReplyService;
import com.sist.vo.*;

@RestController
@RequestMapping("recipe/")
public class RecipeRestController {
	
	@Autowired
	private RecipeService service;
	
	@Autowired
	private ReplyService rservice;
	
	//레시피
	@GetMapping(value = "recipe_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String recipe_list_vue(int page) throws Exception
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		List<RecipeVO> list=service.recipeListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	@GetMapping(value = "recipe_page_vue.do", produces = "text/plain;charset=UTF-8")
	public String recipe_page(int page) throws Exception
	{
		int totalpage=service.recipeTotalpage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		int count=service.recipeCount();
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("count", count);
		   
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	//쉐프
	@GetMapping(value = "chef_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_list_vue(int page) throws Exception
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		List<ChefVO> list=service.chefListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	@GetMapping(value = "chef_page_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_page(int page) throws Exception
	{
		int totalpage=service.chefTotalpage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		   
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	@GetMapping(value = "chef_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_detail_vue(int page,int cno) throws Exception
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		
		Map map=new HashMap();
		map.put("cno", cno);
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list=service.chefDetailData(map);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "chef_detail_page_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_detail_page_vue(int page, int cno) throws Exception
	{
		int totalpage=service.chefDetailTotalPage(cno);
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		   
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	//~~front 요청 처리 부분~~
	@GetMapping(value = "recipe_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_detail_vue(int no) throws Exception
	{
		RecipeDetailVO vo=service.recipeDetailData(no);
		
		//구매라는 단어 자르기 시작 : 잘못크롤링함
		String s=vo.getStuff();
		s=s.replace("구매", "");
		vo.setStuff(s);
		//구매라는 단어 자르기 종료
		
		//reply
		List<ReplyVO> list=rservice.replyListData(no);
		Map map=new HashMap();
		map.put("detail_data", vo);
		map.put("reply_list", list);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	//재료 클릭시 상품
	@GetMapping(value = "goods_vue.do", produces = "text/plain;charset=UTF-8")
	public String recipe_goods(String stuff) throws Exception
	{
		List<GoodsVO> list=service.reipeGoodsData(stuff);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
}
