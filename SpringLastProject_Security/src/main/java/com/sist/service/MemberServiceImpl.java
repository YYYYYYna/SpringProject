package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

@Service
public class MemberServiceImpl implements Memberservice{
	
	@Autowired
	private MemberDAO dao;
	
	@Override
	public int memberIdCount(String userid) {
		// TODO Auto-generated method stub
		return dao.memberIdCount(userid);
	}

	@Override
	public void memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.memberInsert(vo);
	}

	@Override
	public void memberAutorityInsert(String userId) {
		// TODO Auto-generated method stub
		dao.memberAutorityInsert(userId);
	}

	@Override
	public MemberVO memberLogin(String userId, String userPwd) {
		// TODO Auto-generated method stub
		return dao.memberLogin(userId, userPwd);
	}

	@Override
	public MemberVO memberSessionData(String userId) {
		// TODO Auto-generated method stub
		return dao.memberSessionData(userId);
	}

	@Override
	public void lastLoginUpdate(String userId) {
		// TODO Auto-generated method stub
		dao.lastLoginUpdate(userId);
	}

}
