package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public class SeoulDAO {
	
	private SeoulMapper mapper;
    public void setMapper(SeoulMapper mapper) {
		this.mapper = mapper;
	}

	public List<SeoulVO> natureListData()
    {
    	return mapper.natureListData();
    }

	public SeoulVO natureDetailData(int no)
	{
		return mapper.natureDetailData(no);
	}
	
	//제목으로 디테일 검색
	public List<SeoulVO> natureTitleDetailData(String title)
	{
		return mapper.natureTitleDetailData(title);
	}
	
	public void natureInsertData(SeoulVO vo)
	{
		mapper.natureInsertData(vo);
	}
	
	public void natureDeleteData(int no)
	{
		mapper.natureDeleteData(no);
	}
	
	public void natureUpdateData(SeoulVO vo)
	{
		mapper.natureUpdateData(vo);
	}
}
