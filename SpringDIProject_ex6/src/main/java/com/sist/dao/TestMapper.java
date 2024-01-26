package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface TestMapper {
	
	//��Ϻҷ�����
	@Select("SELECT no,name FROM textSample")
	public List<TestVO> testListData();
	
	//�󼼺���
	@Select("SELECT * FROM textSample")
	public TestVO testDetailListData();
	
	//�˻��ϱ�
	@Select("SELECT * FROM textSample WHERE no=#{no}")
	public void testFindData(int no);
	
	//�߰��ϱ�
	@Insert("INSERT INTO textSample VALUES(ts_no_seq.nextval,#{name},#{test1},#{test2},#{test3})")
	public void testInsertData(TestVO vo);
	
	//�����ϱ�
	@Update("UPDATE textSample SET name=#{name},test1=#{test1},test2=#{test2},test3=#{test3} WHERE no=#{no}")
	public void testUpdateData(int no);
	
	//�����ϱ�
	@Delete("DELETE FROM testSample WHERE no=#{no}")
	public void testDeleteData(int no);
	
}
