package com.sist.mapper;
import java.util.*;
import org.apache.ibatis.annotations.*;
import com.sist.dao.FoodVO;
public interface FoodMapper {
	//카테고리별 맛집 보여주기
	@Select("SELECT fno,name,address,phone,type FROM food_house WHERE cno=#{cno}")
	public List<FoodVO> foodCategoryListData(int cno);
	
	//실제 맛집 정보 보여주기
	@Select("SELECT fno,name,address,score,phone,type,parking,time,menu,price FROM food_house WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
