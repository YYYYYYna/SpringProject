package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.FoodVO;

public interface FoodMapper {
	 // XML
	  @Select("<script>"
			 +"SELECT fno,name,type,address "
			 +"FROM food_menu_house "
			 +"WHERE "
			 +"<trim prefix=\"(\" suffix=\")\" prefixOverrides=\"OR\">"
			 +"<foreach collection=\"fsArr\" item=\"fd\">"
			 +"<trim prefix=\"OR\">"
			 +"<choose>"
			 +"<when test=\"fd=='N'.toString()\">"
			 +"name LIKE '%'||#{ss}||'%'"
			 +"</when>"
			 +"<when test=\"fd=='A'.toString()\">"
			 +"address LIKE '%'||#{ss}||'%'"
			 +"</when>"
			 +"<when test=\"fd=='T'.toString()\">"
			 +"type LIKE '%'||#{ss}||'%'"
			 +"</when>"
			 +"</choose>"
			 +"</trim>"
			 +"</foreach>"
			 +"</trim>"
			 +"</script>"
			)
	  public List<FoodVO> foodFindData(Map map);
}
