package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.RecipeDAO;
import com.sist.vo.ChefVO;
import com.sist.vo.FoodVO;
import com.sist.vo.GoodsVO;
import com.sist.vo.RecipeDetailVO;
import com.sist.vo.RecipeVO;

@Service
public class RecipeSerciveImpl implements RecipeService{
	
	@Autowired
	private RecipeDAO rdao;

	@Override
	public List<RecipeVO> recipeHome12() {
		// TODO Auto-generated method stub
		return rdao.recipeHome12();
	}

	@Override
	public List<ChefVO> chefHome12() {
		// TODO Auto-generated method stub
		return rdao.chefHome12();
	}

	@Override
	public List<RecipeVO> recipeListData(int start, int end) {
		// TODO Auto-generated method stub
		return rdao.recipeListData(start, end);
	}

	@Override
	public int recipeTotalpage() {
		// TODO Auto-generated method stub
		return rdao.recipeTotalpage();
	}

	@Override
	public List<ChefVO> chefListData(int start, int end) {
		// TODO Auto-generated method stub
		return rdao.chefListData(start, end);
	}

	@Override
	public int chefTotalpage() {
		// TODO Auto-generated method stub
		return rdao.chefTotalpage();
	}

	@Override
	public int recipeCount() {
		// TODO Auto-generated method stub
		return rdao.recipeCount();
	}

	@Override
	public List<RecipeVO> chefDetailData(Map map) {
		// TODO Auto-generated method stub
		return rdao.chefDetailData(map);
	}

	@Override
	public int chefDetailTotalPage(int cno) {
		// TODO Auto-generated method stub
		return rdao.chefDetailTotalPage(cno);
	}

	@Override
	public RecipeDetailVO recipeDetailData(int no) {
		// TODO Auto-generated method stub
		return rdao.recipeDetailData(no);
	}

	@Override
	public List<GoodsVO> reipeGoodsData(String goods_name) {
		// TODO Auto-generated method stub
		return rdao.reipeGoodsData(goods_name);
	}
	
}
