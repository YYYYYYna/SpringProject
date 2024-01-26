package com.sist.dao;
import java.util.*;
import com.sist.mapper.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;

@Repository
public class FoodDAO {
	
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData (int start, int end)
	{
		return mapper.foodListData(start, end);
	}
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
	public FoodVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
	
	//검색하기
	public List<FoodVO> foodFindData (Map map)
	{
		return mapper.foodFindData(map);
	}
	public int foodFindTotalPage(String address)
	{
		return mapper.foodFindTotalPage(address);
	}
}
