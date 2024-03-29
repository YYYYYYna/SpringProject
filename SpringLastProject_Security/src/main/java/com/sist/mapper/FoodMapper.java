package com.sist.mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

import java.util.*;
public interface FoodMapper {
//음식찾기출력
@Select("SELECT fno,poster,name,num "
      + "FROM(SELECT fno,poster,name,ROWNUM AS num "
      + "FROM(SELECT fno,poster,name "
      + "FROM food_menu_house WHERE address LIKE '%'||#{address}||'%' "
      + "ORDER BY fno ASC)) "
      + "WHERE num BETWEEN #{start} AND #{end}")
public List<FoodVO> foodFindList(Map map);
@Select("SELECT CEIL(COUNT(*)/20.0) FROM food_menu_house WHERE address LIKE '%'||#{address}||'%'")
public int foodFindCount(Map map);

//음식 상세보기 다이얼로그
@Select("SELECT fno,score,poster,name,type,address,phone,theme,price,time,seat "
		+ "FROM food_menu_house "
		+ "WHERE fno=#{fno} ")
public FoodVO foodDetailData(int fno);

@Update("UPDATE food_menu_house SET hit=hit+1 WHERE fno=#{fno} ")
public void foodHitIncrement(int fno);

//음식목록출력
@Select("SELECT fno,poster,name,num "
	      + "FROM(SELECT fno,poster,name,ROWNUM AS num "
	      + "FROM(SELECT fno,poster,name "
	      + "FROM food_menu_house "
	      + "ORDER BY fno ASC)) "
	      + "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);

	@Select("SELECT CEIL(COUNT(*)/20.0) FROM food_menu_house ")
	public int foodListCount();
	
	//실시간 가게 조회 순위 (main)
	@Select("SELECT fno,name,poster,rownum "
		  + "FROM(SELECT fno,name,poster "
		  + "FROM food_menu_house ORDER BY hit DESC) WHERE rownum<=12")
	public List<FoodVO> foodHome12();
	
	//food_recommend.jsp
	 @Select("SELECT name FROM food_menu_house WHERE length(name)>1 ORDER BY fno ASC")
	  public List<String> foodAllData();
	  
	  @Select("SELECT fno,name,poster "
			 +"FROM food_menu_house "
			 +"WHERE name=#{name}")
	  public List<FoodVO> foodNameInfoData(String name);
	
	  //food_list.jsp
	  @Select("SELECT no,title,poster,rownum "
	           +"FROM recipe "
	           +"WHERE no IN(SELECT no FROM recipe "
	           +"INTERSECT SELECT no FROM recipeDetail) "
	           +"AND REGEXP_LIKE(title,#{title}) "
	           +"AND rownum<=12")
	     public List<RecipeVO> foodRecipeData(String title);
}