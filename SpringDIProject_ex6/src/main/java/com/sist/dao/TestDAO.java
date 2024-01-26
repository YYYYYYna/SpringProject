package com.sist.dao;

import java.util.List;

public class TestDAO {
	
	private TestMapper mapper;
	public void setMapper(TestMapper mapper) {
		this.mapper = mapper;
	}
	
	//��Ϻҷ�����
	public List<TestVO> testListData()
	{
		return mapper.testListData();
	}
	
	//�󼼺���
	public TestVO testDetailListData()
	{
		return mapper.testDetailListData();
	}
	
	//�˻��ϱ�
	public void testFindData(int no)
	{
		mapper.testFindData(no);
	}
	
	//�߰��ϱ�
	public void testInsertData(TestVO vo)
	{
		mapper.testInsertData(vo);
	}
	
	//�����ϱ�
	public void testUpdateData(int no)
	{
		mapper.testUpdateData(no);
	}
	
	//�����ϱ�
	public void testDeleteData(int no)
	{
		mapper.testDeleteData(no);
	}
	
	
}
