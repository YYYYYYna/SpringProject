package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;
public interface GoodsMapper {
	                               //테이블이 여러개라서 그렇지 mapper가 두개라서 이렇게 보내는게 아님
	@Select("SELECT no,goods_name as name FROM ${table_name}") 
	public List<GoodsVO> goodsListData(Map map);
	
	@Select("SELECT no,goods_name as name,goods_price as price, "
			+ "goods_sub as sub, goods_discount as discount "
			+ "FROM ${table_name} WHERE no=#{no}")
	public GoodsVO goodDetailList(Map map);
}
