package com.sist.service;

import com.sist.vo.MemberVO;

public interface Memberservice {
	//1-1.���̵� �ߺ�üũ
	public int memberIdCount(String userid);
	//1-2.ȸ������ �߰�
	public void memberInsert(MemberVO vo);
	//1-3.�Ϲ�ȸ���߰�?
	public void memberAutorityInsert(String userId);
	
	//2-2
	public MemberVO memberLogin(String userId, String userPwd);
	
	//3.session ��� principal�� ����
	public MemberVO memberSessionData(String userId);
	public void lastLoginUpdate(String userId);
}
