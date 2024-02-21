package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;

@Repository
public class MemberDAO {
	//��ȣȭ������ ������
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MemberMapper mapper;
	
	//1-1.���̵� �ߺ�üũ
	public int memberIdCount(String userid)
	{
		return mapper.memberIdCount(userid);
	}
	//1-2.ȸ������ �߰�
	public void memberInsert(MemberVO vo)
	{
		mapper.memberInsert(vo);
	}
	//1-3.�Ϲ�ȸ���߰�?
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
			//���̵� ������
			dbVO.setMsg("NOID");
		}
		else
		{
			dbVO=mapper.memberLogin(userId);
			//��ȣȭ�Ǿ��ִºκ� ��ȣȭ�ؼ� �ٿ������
			if(encoder.matches(userPwd, dbVO.getUserPwd()))
			{
				//�α��μ���
				dbVO.setMsg("OK");
			}
			else
			{
				//��й�ȣ����ġ
				dbVO.setMsg("NOPWD");
			}
		}
		return dbVO;
	}
	
	//3.session ��� principal�� ����
	public MemberVO memberSessionData(String userId)
	{
		return mapper.memberSessionData(userId);
	}
	public void lastLoginUpdate(String userId)
	{
		mapper.lastLoginUpdate(userId);
	}
	
	//ä��
	public MemberVO memberInfo(String userId)
	{
		return mapper.memberInfo(userId);
	}
}
