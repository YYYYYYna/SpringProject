package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository("cDAO") //���̵�����ؼ��޸��Ҵ�
public class CategoryDAO {
	
	@Autowired//mapper�� �޸��Ҵ��̶��ּҳֱ�
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
