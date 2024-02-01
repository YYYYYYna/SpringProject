package com.sist.mapper;
import java.util.*;
import com.sist.dao.*;
import org.apache.ibatis.annotations.*;

//Mybatis���� �ڵ� ����
// => �޼ҵ带 ����� => �ڵ� sql����
// insert update delete findAll(Page page)
public interface FoodMapper {
	@Select("SELECT fno,name,poster,num "
			+ "FROM (SELECT fno,name,poster,rownum as num "
			+ "FROM (SELECT fno,name,poster "
			+ "FROM food_menu_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end} ")
	public List<FoodVO> foodListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM food_menu_house")
	public int foodTotalPage();
	
}
