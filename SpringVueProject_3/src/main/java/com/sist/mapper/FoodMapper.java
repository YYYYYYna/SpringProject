package com.sist.mapper;

import java.util.List;
import com.sist.dao.*;
import org.apache.ibatis.annotations.Select;

public interface FoodMapper {
	@Select("SELECT fno,name,poster,rownum "
			+ "FROM food_menu_house "
			+ "WHERE address LIKE '%'||#{address}||'%' "
			+ "AND rownum<=20 ")
	public List<FoodVO> foodFindData(String address);
}
