package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;

@Repository
public class MemberDAO {
	//복호화를위해 가져옴
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MemberMapper mapper;
	
	//1-1.아이디 중복체크
	public int memberIdCount(String userid)
	{
		return mapper.memberIdCount(userid);
	}
	//1-2.회원가입 추가
	public void memberInsert(MemberVO vo)
	{
		mapper.memberInsert(vo);
	}
	//1-3.일반회원추가?
	public void memberAutorityInsert(String userId)
	{
		mapper.memberAutorityInsert(userId);
	}
	
	//2-2
	public MemberVO memberLogin(String userId, String userPwd)
	{
		MemberVO dbVO=new MemberVO();
		int count=mapper.memberIdCount(userId);
		if(count==0)
		{
			//아이디 없을때
			dbVO.setMsg("NOID");
		}
		else
		{
			dbVO=mapper.memberLogin(userId);
			//암호화되어있는부분 복호화해서 붙여줘야함
			if(encoder.matches(userPwd, dbVO.getUserPwd()))
			{
				//로그인성공
				dbVO.setMsg("OK");
			}
			else
			{
				//비밀번호불일치
				dbVO.setMsg("NOPWD");
			}
		}
		return dbVO;
	}
	
	//3.session 대신 principal에 저장
	public MemberVO memberSessionData(String userId)
	{
		return mapper.memberSessionData(userId);
	}
	public void lastLoginUpdate(String userId)
	{
		mapper.lastLoginUpdate(userId);
	}
	
	//채팅
	public MemberVO memberInfo(String userId)
	{
		return mapper.memberInfo(userId);
	}
}
