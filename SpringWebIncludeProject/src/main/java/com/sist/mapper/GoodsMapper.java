package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.sist.vo.GoodsAllVO;


public interface GoodsMapper {
	@Select("SELECT no,goods_name,goods_poster,goods_price,num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price,rownum as num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price "
			+ "FROM goods_all ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end} ")
	public List<GoodsAllVO> goodsListData(@Param("start") int start,
			  @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
	
	@Update("UPDATE goods_all SET hit=hit+1 WHERE no=#{no} ")
	public void goodshitIncrement(int no);
	
	@Select("SELECT no,goods_name,goods_poster,goods_price,goods_discount, "
			+ "goods_first_price,goods_delivery,goods_sub,hit "
			+ "FROM goods_all WHERE no=#{no} ")
	public GoodsAllVO goodsDetailData(int no);
	/*
	 * 
	 */
	//검색 카테고리
	@Select("SELECT no,goods_name,goods_poster,goods_price,num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price,rownum as num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price "
			+ "FROM ${table_name} "
			+ "WHERE goods_name LIKE '%'||#{ss}||'%' "
			+ "ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end} ")
	public List<GoodsAllVO> goodsFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) "
			+ "FROM ${table_name} "
			+ "WHERE goods_name LIKE '%'||#{ss}||'%' ")
	public int goodsFindTotalPage(Map map);
}
