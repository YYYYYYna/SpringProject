package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.FoodVO;

public interface FoodMapper {
	@Select("SELECT name, poster, fno, phone, rownum "
			+ "FROM food_menu_house "
			+ "WHERE address LIKE '%'||#{address}||'%' "
			+ "AND rownum<=20 ")
	public List<FoodVO> foodFindData(String address);
}
