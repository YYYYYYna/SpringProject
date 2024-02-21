package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.RecipeMapper;
import com.sist.vo.ChefVO;
import com.sist.vo.FoodVO;
import com.sist.vo.GoodsVO;
import com.sist.vo.RecipeDetailVO;
import com.sist.vo.RecipeVO;

@Repository
public class RecipeDAO {
	
	@Autowired
	private RecipeMapper mapper;
	
	public List<RecipeVO> recipeHome12()
	{
		return mapper.recipeHome12();
	}
	
	public List<ChefVO> chefHome12()
	{
		return mapper.chefHome12();
	}
	
	//레시피,셰프 목록출력
	public int recipeCount() {
		return mapper.recipeCount();
	}
	public List<RecipeVO> recipeListData(int start, int end)
	{
		return mapper.recipeListData(start, end);
	}
	public int recipeTotalpage()
	{
		return mapper.recipeTotalpage();
	}
	
    //레시피 상세보기
			
    //셰프 목록출력
	public List<ChefVO> chefListData(int start, int end)
	{
		return mapper.chefListData(start, end);
	}
	public int chefTotalpage()
	{
		return mapper.chefTotalpage();
	}
	
	//셰프 상세보기
	public List<RecipeVO> chefDetailData(Map map)
	{
		return mapper.chefDetailData(map);
	}
	public int chefDetailTotalPage(int cno)
	{
		return mapper.chefDetailTotalPage(cno);
	}
	
	//상세보기
	public RecipeDetailVO recipeDetailData(int no)
	{
		return mapper.recipeDetailData(no);
	}
	
	//재료 클릭시 상품
	public List<GoodsVO> reipeGoodsData(String goods_name)
	{
		return mapper.reipeGoodsData(goods_name);
	}
}
