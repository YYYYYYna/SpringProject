package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface TestMapper {
	
	//목록불러오기
	@Select("SELECT no,name FROM textSample")
	public List<TestVO> testListData();
	
	//상세보기
	@Select("SELECT * FROM textSample")
	public TestVO testDetailListData();
	
	//검색하기
	@Select("SELECT * FROM textSample WHERE no=#{no}")
	public void testFindData(int no);
	
	//추가하기
	@Insert("INSERT INTO textSample VALUES(ts_no_seq.nextval,#{name},#{test1},#{test2},#{test3})")
	public void testInsertData(TestVO vo);
	
	//수정하기
	@Update("UPDATE textSample SET name=#{name},test1=#{test1},test2=#{test2},test3=#{test3} WHERE no=#{no}")
	public void testUpdateData(int no);
	
	//삭제하기
	@Delete("DELETE FROM testSample WHERE no=#{no}")
	public void testDeleteData(int no);
	
}
