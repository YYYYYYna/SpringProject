package com.sist.service;

import java.util.List;

import com.sist.vo.FreeBoardVO;

public interface FreeBoardService {
	
	//리스트 출력
	public List<FreeBoardVO> freeboardListData(int start, int end);
	//총페이지
	public int freeboardTotalPage();
	//추가
	public void freeboardInsert(FreeBoardVO vo);
	//상세보기
	public FreeBoardVO FreeBoardDetailData(int no);
	//삭제하기
	public String FreeboardDelete(int no, String pwd);
	//수정하기
	public FreeBoardVO FreeBoardUpdateData(int no);
	//update_ok
	public String freeboardUpdate(FreeBoardVO vo);
}
