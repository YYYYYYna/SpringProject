package com.sist.dao;
import com.sist.vo.*;
import com.sist.mapper.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(int start, int end)
	{
		return mapper.boardListData(start, end);
	}
	
	public int boardTotalPage()
	{
		return mapper.boardTotalPage();
	}
	
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	
	public BoardVO boardDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	public BoardVO boardUpdateData(int no)
	{
		return mapper.boardDetailData(no);
	}
	
	//아이디 동일여부를 확인해야해서 void형에서 String형으로 임의로 리턴형을 바꾼거임
	public String boardUpdate(BoardVO vo)
	{
		String result="no";
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			result="yes";
			mapper.boardUpdate(vo);
		}
		return result;
	}
	public String boardDelete(int no,String pwd)
	{
		String result="no";
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd))
		{
			result="yes";
			mapper.boardDelete(no);
		}
		return result;
	}
}
