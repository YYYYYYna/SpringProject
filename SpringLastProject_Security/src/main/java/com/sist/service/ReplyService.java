package com.sist.service;

import java.util.List;

import com.sist.vo.MemberVO;
import com.sist.vo.ReplyVO;

public interface ReplyService {
	// 목록
	public List<ReplyVO> replyListData(int rno);

	// 추가
	public void replyInsert(ReplyVO vo);

	// 수정
	public void replyUpdate(ReplyVO vo);

	// 삭제
	public void replyDelete(int no);
	
	//사용자정보추가
	public MemberVO memberInfoData(String userId);
}
