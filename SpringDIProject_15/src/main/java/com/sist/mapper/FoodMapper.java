package com.sist.mapper;
/*
 *   name => N
 *   address => A
 *   type => T
 *   name+address=> NA
 *   name+type => NT
 *   address+type => AT
 *   name+address+type => NAT
 *   
 *   if(ss=='N')
 *   OR name LIKE ~
 *   else if(ss=='A')
 *   OR address LIKE ~
 *   else if(ss=='T')
 *   OR type LIKE ~
 */
import java.util.*;
// OR (name LIKE '%'||#{ss}||'%') OR (address LIKE '%'||#{ss}||'%')
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