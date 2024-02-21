package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/*
 * 프론트에서는 라우터로 화면전환을 함
 *   예시:  <route "/recipe/list" component="RecipeList"/>
 */

/*
 * Controller에서는 EL/JSTL 로 보내는 부분
 * RestController에서는 vue/react 로 값을 보냄
 */
@Controller
public class RecipeController {
	
	@GetMapping("recipe/recipe_detail.do")
	public String recipe_detail(int no, Model model,HttpSession session)
	{
		String userId=(String)session.getAttribute("userId");
		String sessionId="";
		if(userId==null)
			sessionId="";
		else
			sessionId=userId;
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("no", no);
		
		return "recipe/recipe_detail";
	}
	
	@GetMapping("recipe/chef_detail.do")
	public String chef_detail(int cno, Model model)
	{
		model.addAttribute("cno", cno);
		return "recipe/chef_detail";
	}
	
	@GetMapping("recipe/recipe_list.do")
	public String recipe_list()
	{
		return "recipe/recipe_list";
	}
	
	@GetMapping("recipe/chef_list.do")
	public String chef_list()
	{
		return "recipe/chef_list";
	}
	
	@GetMapping("recipe/recipe_list_detail.do")
	public String recipe_list_detail(int no, Model model)
	{
		model.addAttribute("no", no);
		return "recipe/recipe_detail";
	}
	
	//reply부분
	
}
