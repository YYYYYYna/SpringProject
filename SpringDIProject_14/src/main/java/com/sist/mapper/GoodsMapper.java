package com.sist.mapper;
import java.util.*;
import org.apache.ibatis.annotations.*;
import com.sist.dao.*;

public interface GoodsMapper {
	
	@Select("SELECT no,goods_name as name FROM ${table_name}")
	public List<GoodsVO> goodsListData(Map map);
	
	@Select("SELECT no,goods_name as name,goods_price as price, "
			+ "goods_sub as sub, goods_discount as discount "
			+ "FROM ${table_name} WHERE no=#{no}")
	public GoodsVO goodDetailData(Map map); //param사용 안하면 map에 이제 2개 채워야함 
}
