package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface SeoulMapper {
	@Select("SELECT no,title FROM seoul_nature ORDER BY no ASC")
	public List<SeoulVO> natureListData();
	
	@Select("SELECT no,title,msg,address FROM seoul_nature WHERE no=#{no}")
	public SeoulVO natureDetailData(int no);
	
	//제목으로 디테일 검색
	@Select("SELECT no,title,msg,address FROM seoul_nature WHERE title LIKE '%'||#{title}||'%'")
	public List<SeoulVO> natureTitleDetailData(String title);
	
	@Insert("INSERT INTO seoul_nature VALUES(#{no}, #{title}, #{msg}, #{address})")
	public void natureInsertData(SeoulVO vo);
	
	@Delete("DELETE FROM seoul_nature WHERE no=#{no}")
	public void natureDeleteData(int no);
	
	@Update("UPDATE seoul_nature SET title=#{title}, msg=#{msg}, address=#{address} WHERE no=#{no}")
	public void natureUpdateData(SeoulVO vo);
}
