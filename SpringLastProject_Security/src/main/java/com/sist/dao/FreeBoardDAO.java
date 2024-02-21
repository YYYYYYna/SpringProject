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
	
	//����Ʈ ���
	public List<FreeBoardVO> freeboardListData(int start, int end)
	{
		return mapper.freeboardListData(start, end);
	}
	//��������
	public int freeboardTotalPage()
	{
		return mapper.freeboardTotalPage();
	}
	//�߰�
	public void freeboardInsert(FreeBoardVO vo)
	{
		mapper.freeboardInsert(vo);
	}
	//�󼼺���
	public FreeBoardVO FreeBoardDetailData(int no)
	{
		mapper.hitIncreasement(no);
		return mapper.FreeBoardDetailData(no);
	}
	//���� (mapper�� �޶����κ������� ����)
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
	//update=> �󼼺��⸦ ��ȸ�� �κ� ���� �̸��� �ٲ�
	public FreeBoardVO FreeBoardUpdateData(int no)
	{
		return mapper.FreeBoardDetailData(no);
	}
	
	//update_ok (mapper�� �޶����κ������� ����)
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
