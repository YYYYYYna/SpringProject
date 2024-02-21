package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.ReplyDAO;
import com.sist.vo.MemberVO;
import com.sist.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDAO dao;

	// 목록
	public List<ReplyVO> replyListData(int rno)
	{
		return dao.replyListData(rno);
	}

	// 추가
	public void replyInsert(ReplyVO vo)
	{
		dao.replyInsert(vo);
	}

	// 수정
	public void replyUpdate(ReplyVO vo)
	{
		dao.replyUpdate(vo);
	}

	// 삭제
	public void replyDelete(int no)
	{
		dao.replyDelete(no);
	}

	@Override
	public MemberVO memberInfoData(String userId) {
		// TODO Auto-generated method stub
		return dao.memberInfoData(userId);
	}
}
