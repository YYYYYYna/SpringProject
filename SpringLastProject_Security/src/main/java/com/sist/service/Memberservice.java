package com.sist.service;

import com.sist.vo.MemberVO;

public interface Memberservice {
	//1-1.아이디 중복체크
	public int memberIdCount(String userid);
	//1-2.회원가입 추가
	public void memberInsert(MemberVO vo);
	//1-3.일반회원추가?
	public void memberAutorityInsert(String userId);
	
	//2-2
	public MemberVO memberLogin(String userId, String userPwd);
	
	//3.session 대신 principal에 저장
	public MemberVO memberSessionData(String userId);
	public void lastLoginUpdate(String userId);
}
