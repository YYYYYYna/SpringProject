package com.sist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.FoodService;
import com.sist.service.RecipeService;
import com.sist.vo.ChefVO;
import com.sist.vo.FoodVO;
import com.sist.vo.RecipeVO;

@Controller
public class MainController {
	
	@Autowired
	private FoodService fservice;
	@Autowired
	private RecipeService rservice;
	
	
	@GetMapping("main/main.do")
	   public String main_main(Model model)
	   {
		   //JSP�� ���� ���� ��ü => model
		   //model�ȿ� httpServletRequest�� ���ԵǾ�����
		   List<FoodVO> foodList=fservice.foodHome12();
		   model.addAttribute("foodList", foodList);
		   
		   List<RecipeVO> recipeList=rservice.recipeHome12();
		   model.addAttribute("recipeList",recipeList);
		   
		   List<ChefVO> chefList=rservice.chefHome12();
		   model.addAttribute("chefList", chefList);
 		
		   return "main";
	   }
	
	
}
