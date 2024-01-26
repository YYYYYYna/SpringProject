package com.sist.dao;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;

@Repository("gDAO")
public class GoodsDAO {
	
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsListData(Map map)
	{
		return mapper.goodsListData(map);
	}
	public GoodsVO goodDetailData(Map map)
	{
		return mapper.goodDetailData(map);
	}
}
