package com.sist.dao;

import java.util.List;

public class SeoulDAO {
	
	private SeoulMapper mapper;
	public void setMapper(SeoulMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<SeoulVO> seoulShopListData()
	{
		return mapper.seoulShopListData();
	}
	public SeoulVO seoulShopDetailData(int no)
	{
		return mapper.seoulShopDetailData(no);
	}
	public List<SeoulVO> seoulshopTitleDetailData(String title)
	{
		return mapper.seoulshopTitleDetailData(title);
	}
}
