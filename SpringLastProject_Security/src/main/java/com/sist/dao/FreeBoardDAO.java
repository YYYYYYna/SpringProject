package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FreeBoardMapper;
import com.sist.vo.FreeBoardVO;

@Repository
public class FreeBoardDAO {
	@Autowired
	private FreeBoardMapper mapper;
	
	//리스트 출력
	public List<FreeBoardVO> freeboardListData(int start, int end)
	{
		return mapper.freeboardListData(start, end);
	}
	//총페이지
	public int freeboardTotalPage()
	{
		return mapper.freeboardTotalPage();
	}
	//추가
	public void freeboardInsert(FreeBoardVO vo)
	{
		mapper.freeboardInsert(vo);
	}
	//상세보기
	public FreeBoardVO FreeBoardDetailData(int no)
	{
		mapper.hitIncreasement(no);
		return mapper.FreeBoardDetailData(no);
	}
	//삭제 (mapper랑 달라진부분있으니 주의)
	public String FreeboardDelete(int no, String pwd)
	{
		String result="no";
		String db_pwd=mapper.freeboardGetPassword(no);
		if(db_pwd.equals(pwd))
		{
			result="yes";
			mapper.FreeboardDelete(no);
		}
		return result;
	}
	//update=> 상세보기를 조회수 부분 빼고 이름만 바꿈
	public FreeBoardVO FreeBoardUpdateData(int no)
	{
		return mapper.FreeBoardDetailData(no);
	}
	
	//update_ok (mapper랑 달라진부분있으니 주의)
	public String freeboardUpdate(FreeBoardVO vo)
	{
		String result="no";
		String db_pwd=mapper.freeboardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			result="yes";
			mapper.freeboardUpdate(vo);
		}
		return result;
	}
}
