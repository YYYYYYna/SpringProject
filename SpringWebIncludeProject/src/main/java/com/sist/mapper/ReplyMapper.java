package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.*;

import com.sist.vo.*;
public interface ReplyMapper {
	
	//1-1.시퀀스 대신 자동증가번호 만드는 sql
	@SelectKey(keyProperty = "no", resultType = int.class, before = true ,
			statement = "SELECT NVL(MAX(no)+1,1) as no FROM springReply ")
	//1-2.댓글 추가
	@Insert("INSERT INTO springReply VALUES( "
			+ "#{no},#{fno},#{id},#{name},#{msg},SYSDATE) ")
	public void replyInsert(ReplyVO vo);
	
	//2.댓글 목록출력
	@Select("SELECT no,fno,id,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,msg "
			+ "FROM springReply "
			+ "WHERE fno=#{fno} "
			+ "ORDER BY no DESC ")
	public List<ReplyVO> replyListData(int fno);
	
	//3.댓글 수정
	@Update("UPDATE springReply SET msg=#{msg} WHERE no=#{no} ")
	public void replyUpdate(ReplyVO vo);
	
	//4.댓글 삭제
	@Delete("DELETE FROM springReply "
			+ "WHERE no=#{no} ")
	public void replyDelete(int no);
}