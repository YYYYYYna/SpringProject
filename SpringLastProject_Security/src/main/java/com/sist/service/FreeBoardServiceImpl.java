package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FreeBoardDAO;
import com.sist.vo.FreeBoardVO;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	@Autowired
	private FreeBoardDAO dao;

	@Override
	public List<FreeBoardVO> freeboardListData(int start, int end) {
		// TODO Auto-generated method stub
		return dao.freeboardListData(start, end);
	}

	@Override
	public int freeboardTotalPage() {
		// TODO Auto-generated method stub
		return dao.freeboardTotalPage();
	}

	@Override
	public void freeboardInsert(FreeBoardVO vo) {
		// TODO Auto-generated method stub
		dao.freeboardInsert(vo);
	}

	@Override
	public FreeBoardVO FreeBoardDetailData(int no) {
		// TODO Auto-generated method stub
		return dao.FreeBoardDetailData(no);
	}

	@Override
	public String FreeboardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		return dao.FreeboardDelete(no, pwd);
	}

	@Override
	public FreeBoardVO FreeBoardUpdateData(int no) {
		// TODO Auto-generated method stub
		return dao.FreeBoardUpdateData(no);
	}

	@Override
	public String freeboardUpdate(FreeBoardVO vo) {
		// TODO Auto-generated method stub
		return dao.freeboardUpdate(vo);
	}
}
