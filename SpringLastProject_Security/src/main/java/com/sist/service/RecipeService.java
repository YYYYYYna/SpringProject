package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.ChefVO;
import com.sist.vo.GoodsVO;
import com.sist.vo.RecipeDetailVO;
import com.sist.vo.RecipeVO;

public interface RecipeService {
	public List<RecipeVO> recipeHome12();
	public List<ChefVO> chefHome12();
	
	//레시피,셰프 목록출력
	public int recipeCount();
	public List<RecipeVO> recipeListData(int start, int end);
	public int recipeTotalpage();
		
	//레시피 상세보기
				
	//셰프 목록출력
	public List<ChefVO> chefListData(int start, int end);
	public int chefTotalpage();
	
	//셰프 상세보기
	public List<RecipeVO> chefDetailData(Map map);
	public int chefDetailTotalPage(int cno);
		
    //상세보기
	public RecipeDetailVO recipeDetailData(int no);
	
	//재료 클릭시 상품
	public List<GoodsVO> reipeGoodsData(String goods_name);
}
