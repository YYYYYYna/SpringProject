package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository("cDAO") //아이디생성해서메모리할당
public class CategoryDAO {
	
	@Autowired//mapper에 메모리할당이랑주소넣기
	private CategoryMapper mapper;
	
	public List<CategoryVO> foodCategoryData()
	{
		return mapper.foodCategoryData();
	}
	
	public CategoryVO categoryInfoData(int cno)
	{
		return mapper.categoryInfoData(cno);
	}
}
