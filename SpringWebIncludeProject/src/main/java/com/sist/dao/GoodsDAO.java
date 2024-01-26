package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GoodsMapper;
import com.sist.vo.GoodsAllVO;

@Repository
public class GoodsDAO {
	
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsAllVO> goodsListData(int start,int end)
	{
		return mapper.goodsListData(start, end);
	}
	public int goodsTotalPage()
	{
		return mapper.goodsTotalPage();
	}
	public GoodsAllVO goodsDetailData(int no)
	{
		mapper.goodshitIncrement(no);
		return mapper.goodsDetailData(no);
	}
	public GoodsAllVO goodsCookieData(int no)
	{
		return mapper.goodsDetailData(no);
	}
	
	public List<GoodsAllVO> goodsFindData(Map map)
	{
		return mapper.goodsFindData(map);
	}
	public int goodsFindTotalPage(Map map)
	{
		return mapper.goodsFindTotalPage(map);
	}
}
