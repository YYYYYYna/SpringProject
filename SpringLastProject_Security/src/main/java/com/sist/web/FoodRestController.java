package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.manager.FoodRecommendManager;
import com.sist.service.FoodService;
import com.sist.vo.FoodVO;
import com.sist.vo.RecipeVO;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
/*
 *  mapper : Å×ÀÌºí 1°³¸¦ ´Ù·ç´Â °æ¿ì
 *  Service : °ü·ÃµÈ Mapper°¡ ¿©·¯°³ ÀÖ´Â °æ¿ì
 *  ==================================== DB¿¬µ¿ (MyBatis´Â µ¥ÀÌÅÍº£ÀÌ½º ¿¬°á)
 *  View(JSP) : È­¸éÃâ·Â
 *  ==================================== ¿äÃ» (form, a, axios, ajax)
 *                                           ========  ===========
 *                                           |È­¸éº¯°æ    |º¯°æ¾øÀÌµ¥ÀÌÅÍÀÐ±â
 *  list.do ==========> DispatcherServlet (ÀÌ ¿ªÇÒÀ» XML È¤Àº Annotation¿¡¼­ ÇØ¾ßÇÔ)
 *                          ======> list.doÃ³¸® ¸Þ¼Òµå¸¦ Ã£¾Æ¶ó
 *                                  HandlerMapping
 *                        ---------------------------------------
 *                          ======> list.do¿¡ ´ëÇÑ Ã³¸® =====> °³¹ßÀÚ
 *                                  @GetMapping("list.do")
 *                          ======> JSP¸¦ ÀÀ´ä°ªÀ» Àü¼Û / È­¸éº¯°æ
 *                        --------------------------------------- Model
 *                                                 @controllor, @RestController
 *                          ======> JSP¸¦ Ã£¾Æ¼­ request¸¦ Àü¼Û
 *                                  =======================
 *                                   ViewResolver ==> °æ·Î¸í , È®ÀåÀÚ È®ÀÎ => µî·Ï
 */
@RestController
@RequestMapping("food/")
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@Autowired
	private FoodRecommendManager mgr;
	
	@GetMapping(value = "find_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_find_vue(int page, String fd) throws Exception
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("address", fd);
		List<FoodVO> list=service.foodFindList(map);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
		
	}
	
	@GetMapping(value = "page_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_page_vue(int page, String fd) throws Exception
	{
		   final int BLOCK=10;
		   int startPage=((page-1)/BLOCK*BLOCK)+1;
		   int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		   Map map=new HashMap();
		   map.put("address", fd);
		   int totalpage=service.foodFindCount(map);
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   map=new HashMap();
		   map.put("curpage", page);
		   map.put("totalpage", totalpage);
		   map.put("startPage", startPage);
		   map.put("endPage", endPage);
		   
		   ObjectMapper mapper=new ObjectMapper();
		   String json=mapper.writeValueAsString(map);
		   return json;
	}
	
	//´ÙÀÌ¾ó·Î±× mapping
	@GetMapping(value = "detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail (int fno) throws Exception
	{
		FoodVO vo=service.foodDetailData(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	//list Ãâ·Â
	@GetMapping(value = "food_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list_vue(int page) throws Exception
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<FoodVO> list=service.foodListData(map);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food_page_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list_page_vue(int page) throws Exception
	{
		   final int BLOCK=10;
		   int startPage=((page-1)/BLOCK*BLOCK)+1;
		   int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		   int totalpage=service.foodListCount();
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
	
	//food_list_detail.jsp 
	@GetMapping(value = "food_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list_detail_vue(int fno) throws Exception
	{
		FoodVO vo=service.foodListDetailData(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	//food_list.jsp cookie
	@GetMapping(value="food_cookie_vue.do",produces = "text/plain;charset=UTF-8")
	   public String food_cookie(HttpServletRequest request) throws Exception
	   {
		   Cookie[] cookies=request.getCookies();
		   List<FoodVO> list=new ArrayList<FoodVO>();
		   int k=0;
		   if(cookies!=null)
		   {
			   for(int i=cookies.length-1;i>=0;i--)
			   {
			       if(k<9)
			       {
			    	   // new Cookie("food_"+fno, String.valueOf(fno))
			    	   //            =======getName() ======= getValue()
			    	   if(cookies[i].getName().startsWith("food_"))
			    	   {
			    		   String fno=cookies[i].getValue();
			    		   FoodVO vo=service.foodDetailData(Integer.parseInt(fno));
			    		   list.add(vo);
			    	   }
			    	   k++;
			       }
			   }
		   }
		   ObjectMapper mapper=new ObjectMapper();
		   String json=mapper.writeValueAsString(list);
		   return json;
	   }
	
	@GetMapping(value = "food_recommend_sub.do",produces = "text/plain;charset=UTF-8")
	public String food_recommend(int no) throws Exception
	{
		String[] menu1 = {
				"Åð±Ù±æ","ÈÞ½Ä","ÈÞ°¡","¿©Çà","µå¶óÀÌºê",
				"»êÃ¥","¿îµ¿"
		};
		String[] menu2 = {
				"±âºÐÀüÈ¯","¿Ü·Î¿ò","½½ÇÄ","ÀÌº°","ÁöÄ§",
				"¼³·½","À§·Î","½ºÆ®·¹½º","±×¸®¿ò","¿ì¿ï",
				"Çàº¹","ºÒ¾È","±â»Ý"
		};
		String[] menu3 = {
				"¹àÀº","½Å³ª´Â","Æí¾ÈÇÑ","µû¶æÇÑ","´ÞÄÞÇÑ",
				"½Ã¿øÇÑ"
		};
		String[] menu4 = {
				"º½","¿©¸§","°¡À»","°Ü¿ï","¸¼Àº³¯",
				"Ãß¿î³¯","Èå¸°³¯","ºñ¿À´Â³¯","´õ¿î³¯","¾È°³³¤³¯","´«¿À´Â³¯"
		};
		
		ObjectMapper mapper=new ObjectMapper();
		String json="";
		
		if(no==1)
		{
			json=mapper.writeValueAsString(menu1);
		}
		else if(no==2)
		{
			json=mapper.writeValueAsString(menu2);
		}
		else if(no==3)
		{
			json=mapper.writeValueAsString(menu3);
		}
		else if(no==4)
		{
			json=mapper.writeValueAsString(menu4);
		}
		return json;
	}
	
	@GetMapping(value="food_recommend_data.do",produces = "text/plain;charset=UTF-8")
	public String food_recommand_data(String fd) throws Exception
	   {
		   List<String> list=mgr.newsFind(fd);
		   List<String> fList=service.foodAllData();
		   
		   /*
		    *   1. ´Ü¾î => constains()
		    *   2. ±âÈ£ : * (0ÀÌ»ó)      ¸ÀÀÖ´Ù , ¸ÀÀÖ°í , ¸ÀÀÖ´Â  => ¸À*
		    *            + (1ÀÌ»ó)      ¸À+
		    *            ? (0,1)       
		    *            | (¼±ÅÃ) 
		    *            . ÀÓÀÇÀÇ ÇÑ±ÛÀÚ  ¸À.
		    *            => ½ÇÁ¦ ±âÈ£ : \\+
		    *            [] => ¹üÀ§ 
		    *               ¼ýÀÚ [0-3] => [0-9] 
		    *               ¿µ¹® [A-Z] [a-z] => [A-Za-z]
		    *               ÇÑ±Û [°¡-ÆR]
		    *            {} => °¹¼ö 
		    *            {3} , {1,3} 
		    *            [0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3} : IP
		    *            ^ : ½ÃÀÛ 
		    *            ^[°¡-ÆR] => ÇÑ±Û ½ÃÀÛ 
		    *            [^°¡-ÆR] => ÇÑ±ÛÀ» Á¦¿ÜÇÏ°í
		    *            $ : ³¡ 
		    *            [°¡-ÆR]$ => ÇÑ±Û·Î ³¡³­ ...
		    *            ¹®ÀÚÀÇ ÇüÅÂ 
		    */
		   
		   // ÃÊ±âÈ­ => Á¤±Ô½Ä 
		   
		   List<String> rList=new ArrayList<String>();
		   int[] count=new int[fList.size()];
		   for(String s:list) // ÃßÃµ 
		   {
			   //System.out.println(s);
			   for(int i=0;i<fList.size();i++)
			   {
				    if(s.contains(fList.get(i)))
				    {
				    	//System.out.println(fList.get(i));
				    	rList.add(fList.get(i));
				    	count[i]++;
				    }
					
			   }
		   }
		   
		   Set set=new HashSet();
		   for(int i=0;i<rList.size();i++)
		   {
			   set.add(rList.get(i)); // Áßº¹Á¦°Å 
		   }
		   
		   List<FoodVO> dList=new ArrayList<FoodVO>();
		   Iterator iter=set.iterator();
		   while(iter.hasNext())
		   {
			   String ss=iter.next().toString();
			   List<FoodVO> vo=service.foodNameInfoData(ss);
			   dList.add(vo.get(0));
		   }
		   
		   ObjectMapper mapper=new ObjectMapper();
		   String json=mapper.writeValueAsString(dList);
		   return json;
	   }
	
	@GetMapping(value = "food_detail_recipe.do", produces = "text/plain;charset=UTF-8")
	public String food_detail_recipe(String title) throws Exception
	{
		String s=title.replace("/", "|");
		List<RecipeVO> list=service.foodRecipeData(title);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
}
