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
 *  mapper : ���̺� 1���� �ٷ�� ���
 *  Service : ���õ� Mapper�� ������ �ִ� ���
 *  ==================================== DB���� (MyBatis�� �����ͺ��̽� ����)
 *  View(JSP) : ȭ�����
 *  ==================================== ��û (form, a, axios, ajax)
 *                                           ========  ===========
 *                                           |ȭ�麯��    |������̵������б�
 *  list.do ==========> DispatcherServlet (�� ������ XML Ȥ�� Annotation���� �ؾ���)
 *                          ======> list.doó�� �޼ҵ带 ã�ƶ�
 *                                  HandlerMapping
 *                        ---------------------------------------
 *                          ======> list.do�� ���� ó�� =====> ������
 *                                  @GetMapping("list.do")
 *                          ======> JSP�� ���䰪�� ���� / ȭ�麯��
 *                        --------------------------------------- Model
 *                                                 @controllor, @RestController
 *                          ======> JSP�� ã�Ƽ� request�� ����
 *                                  =======================
 *                                   ViewResolver ==> ��θ� , Ȯ���� Ȯ�� => ���
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
	
	//���̾�α� mapping
	@GetMapping(value = "detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail (int fno) throws Exception
	{
		FoodVO vo=service.foodDetailData(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	//list ���
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
				"��ٱ�","�޽�","�ް�","����","����̺�",
				"��å","�"
		};
		String[] menu2 = {
				"�����ȯ","�ܷο�","����","�̺�","��ħ",
				"����","����","��Ʈ����","�׸���","���",
				"�ູ","�Ҿ�","���"
		};
		String[] menu3 = {
				"����","�ų���","�����","������","������",
				"�ÿ���"
		};
		String[] menu4 = {
				"��","����","����","�ܿ�","������",
				"�߿","�帰��","����³�","���","�Ȱ�����","�����³�"
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
		    *   1. �ܾ� => constains()
		    *   2. ��ȣ : * (0�̻�)      ���ִ� , ���ְ� , ���ִ�  => ��*
		    *            + (1�̻�)      ��+
		    *            ? (0,1)       
		    *            | (����) 
		    *            . ������ �ѱ���  ��.
		    *            => ���� ��ȣ : \\+
		    *            [] => ���� 
		    *               ���� [0-3] => [0-9] 
		    *               ���� [A-Z] [a-z] => [A-Za-z]
		    *               �ѱ� [��-�R]
		    *            {} => ���� 
		    *            {3} , {1,3} 
		    *            [0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3} : IP
		    *            ^ : ���� 
		    *            ^[��-�R] => �ѱ� ���� 
		    *            [^��-�R] => �ѱ��� �����ϰ�
		    *            $ : �� 
		    *            [��-�R]$ => �ѱ۷� ���� ...
		    *            ������ ���� 
		    */
		   
		   // �ʱ�ȭ => ���Խ� 
		   
		   List<String> rList=new ArrayList<String>();
		   int[] count=new int[fList.size()];
		   for(String s:list) // ��õ 
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
			   set.add(rList.get(i)); // �ߺ����� 
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
