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
	
	//������,���� ������
	public int recipeCount();
	public List<RecipeVO> recipeListData(int start, int end);
	public int recipeTotalpage();
		
	//������ �󼼺���
				
	//���� ������
	public List<ChefVO> chefListData(int start, int end);
	public int chefTotalpage();
	
	//���� �󼼺���
	public List<RecipeVO> chefDetailData(Map map);
	public int chefDetailTotalPage(int cno);
		
    //�󼼺���
	public RecipeDetailVO recipeDetailData(int no);
	
	//��� Ŭ���� ��ǰ
	public List<GoodsVO> reipeGoodsData(String goods_name);
}
