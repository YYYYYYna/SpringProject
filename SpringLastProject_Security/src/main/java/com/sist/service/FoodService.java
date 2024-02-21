package com.sist.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;
import com.sist.vo.RecipeVO;

public interface FoodService {
	//find
	public List<FoodVO> foodFindList(Map map);
	public int foodFindCount(Map map);
	//dialog
	public FoodVO foodDetailData(int fno);
	//list
	public List<FoodVO> foodListData(Map map);
	public int foodListCount();
	//recipe => ����Ʈ ��½� ������ ��õ����
	public FoodVO foodListDetailData(int fno);
	
	//main ���
	public List<FoodVO> foodHome12();
	
	public List<String> foodAllData();
	public List<FoodVO> foodNameInfoData(String name);
	
	public List<RecipeVO> foodRecipeData(String title);
	
}
