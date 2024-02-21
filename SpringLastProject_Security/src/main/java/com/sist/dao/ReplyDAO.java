package com.sist.dao;

import com.sist.vo.*;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;

@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;

	// 목록
	public List<ReplyVO> replyListData(int rno)
	{
		return mapper.replyListData(rno);
	}

	// 추가
	public void replyInsert(ReplyVO vo)
	{
		mapper.replyInsert(vo);
	}

	// 수정
	public void replyUpdate(ReplyVO vo)
	{
		mapper.replyUpdate(vo);
	}

	// 삭제
	public void replyDelete(int no)
	{
		mapper.replyDelete(no);
	}
	
	//사용자정보추가
	public MemberVO memberInfoData(String userId)
	{
		return mapper.memberInfoData(userId);
	}
}
