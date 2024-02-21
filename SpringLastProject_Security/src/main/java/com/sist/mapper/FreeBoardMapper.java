package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.*;

import com.sist.vo.FreeBoardVO;

public interface FreeBoardMapper {
	//목록출력
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD-HH24-MI-SS') as dbday, hit, num "
			+ "FROM (SELECT no,subject,name,regdate, hit, rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(projectFreeBoard pfb_no_pk)*/no,subject,name,regdate,hit "
			+ "FROM projectFreeBoard)) "
			+ "WHERE num BETWEEN #{start} AND #{end} ")
	public List<FreeBoardVO> freeboardListData(@Param("start") int start, @Param("end")int end);
	
	//총페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM projectFreeBoard ")
	public int freeboardTotalPage();
	
	//추가
	@Insert("INSERT INTO projectFreeboard(no,name,subject,content,pwd) "
			+ "VALUES(pfb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
	public void freeboardInsert(FreeBoardVO vo);
	
	//상세보기
	@Update("UPDATE projectFreeBoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no} ")
	public void hitIncreasement(int no);
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday "
			+ "FROM projectFreeBoard "
			+ "WHERE no=#{no} ")
	public FreeBoardVO FreeBoardDetailData(int no);
	
	//수정하기
	//detail부분으로대체함
	//수정하기_ok
	@Update("UPDATE projectFreeBoard SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no} ")
	public void freeboardUpdate(FreeBoardVO vo);
	
	//삭제
	@Select("SELECT pwd FROM projectFreeBoard WHERE no=#{no} ")
	public String freeboardGetPassword(int no);
	@Delete("Delete FROM projectFreeBoard WHERE no=#{no} ")
	public void FreeboardDelete(int no);
}
