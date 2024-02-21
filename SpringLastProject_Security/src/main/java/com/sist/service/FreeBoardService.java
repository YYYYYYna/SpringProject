package com.sist.service;

import java.util.List;

import com.sist.vo.FreeBoardVO;

public interface FreeBoardService {
	
	//����Ʈ ���
	public List<FreeBoardVO> freeboardListData(int start, int end);
	//��������
	public int freeboardTotalPage();
	//�߰�
	public void freeboardInsert(FreeBoardVO vo);
	//�󼼺���
	public FreeBoardVO FreeBoardDetailData(int no);
	//�����ϱ�
	public String FreeboardDelete(int no, String pwd);
	//�����ϱ�
	public FreeBoardVO FreeBoardUpdateData(int no);
	//update_ok
	public String freeboardUpdate(FreeBoardVO vo);
}
