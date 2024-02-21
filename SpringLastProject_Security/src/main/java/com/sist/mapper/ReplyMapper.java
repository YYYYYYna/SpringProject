package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.sist.vo.MemberVO;
import com.sist.vo.ReplyVO;

public interface ReplyMapper {

	//���
	@Select("SELECT no,rno,userId,userName,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM projectRecipeReply "
			+ "WHERE rno=#{rno} "
			+ "ORDER BY no DESC ")
	public List<ReplyVO> replyListData(int rno);
	//�߰�
	 @Insert("INSERT INTO projectRecipeReply(no,rno,userId,userName,msg) "
			 +"VALUES(prr_no_seq.nextval,#{rno},#{userId},#{userName},#{msg})")
	  public void replyInsert(ReplyVO vo);
	//����
	@Update("UPDATE projectRecipeReply SET msg=#{msg} WHERE no=#{no} ")
	public void replyUpdate(ReplyVO vo);
	//����
	@Delete("DELETE FROM projectRecipeReply WHERE no=#{no} ")
	public void replyDelete(int no);
	
	//����������б�
	@Select("SELECT userId, userName FROM projectMember "
			+ "WHERE userId=#{userId}")
	public MemberVO memberInfoData(String userId);
}
