package com.sist.dao;

import java.util.List;

public class TestDAO {
	
	private TestMapper mapper;
	public void setMapper(TestMapper mapper) {
		this.mapper = mapper;
	}
	
	//목록불러오기
	public List<TestVO> testListData()
	{
		return mapper.testListData();
	}
	
	//상세보기
	public TestVO testDetailListData()
	{
		return mapper.testDetailListData();
	}
	
	//검색하기
	public void testFindData(int no)
	{
		mapper.testFindData(no);
	}
	
	//추가하기
	public void testInsertData(TestVO vo)
	{
		mapper.testInsertData(vo);
	}
	
	//수정하기
	public void testUpdateData(int no)
	{
		mapper.testUpdateData(no);
	}
	
	//삭제하기
	public void testDeleteData(int no)
	{
		mapper.testDeleteData(no);
	}
	
	
}
