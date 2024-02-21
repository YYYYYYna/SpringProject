package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;
import com.sist.vo.RecipeVO;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodFindList(Map map){
		return mapper.foodFindList(map);
	}
	public int foodFindCount(Map map) {
		return mapper.foodFindCount(map);
	}
	public FoodVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
	public List<FoodVO> foodListData(Map map)
	{
		return mapper.foodListData(map);
	}
	public int foodListCount()
	{
		return mapper.foodListCount();
	}
	//food_list_detail.jsp
	public FoodVO foodListDetailData(int fno)
	{
		mapper.foodHitIncrement(fno);
		return mapper.foodDetailData(fno);
	}
	
	 public List<FoodVO> foodNameInfoData(String name)
	   {
		   return mapper.foodNameInfoData(name);
	   }
	
	//메인용 순위출력
	public List<FoodVO> foodHome12()
	{
		return mapper.foodHome12();
	}
	
	public List<String> foodAllData()
	{
		return mapper.foodAllData();
	}
	
	public List<RecipeVO> foodRecipeData(String title)
	{
		return mapper.foodRecipeData(title);
	}
}
