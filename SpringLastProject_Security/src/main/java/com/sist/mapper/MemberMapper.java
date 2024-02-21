package com.sist.mapper;

import org.apache.ibatis.annotations.*;

import com.sist.vo.*;

public interface MemberMapper {

	//1.ȸ������
	//1-1.���̵� �ߺ�üũ
	@Select("SELECT COUNT(*) FROM projectMember "
			+ "WHERE userid=#{userid} ")
	public int memberIdCount(String userid);
	//1-2.ȸ������ �߰�
	//#{}�κ��� vo�� ��ҹ��� ������ϰ� �׳� ���ºκ��� col�̶� ��ҹ��� �ȸ��絵��
	@Insert("INSERT INTO projectMember(userid, username, userpwd, sex, birthday, email, post, "
			+ "addr1,addr2,phone,content) VALUES(#{userId},#{userName},#{userPwd},#{sex}, "
			+ "#{birthday},#{email},#{post},#{addr1},#{addr2},#{phone},#{content}) ")
	public void memberInsert(MemberVO vo);
	//1-3.�Ϲ�ȸ���߰�?
	@Insert("INSERT INTO projectAuthority VALUES(#{userId}, 'ROLE_USER')")
	public void memberAutorityInsert(String userId);
	
	//2.�α���
	//2-1.ID���翩�� Ȯ��
	//�̹�����?>.? �� 1-1 ������ ���� ��~
	//2-2.��й�ȣ Ȯ��
	@Select("SELECT pm.userId,userName,userPwd,enabled,authority "
			+ "FROM projectMember pm, projectAuthority pa "
			+ "WHERE pm.userId=pa.userId "//vo�����ҷ����� �̷��� join ���Ǽ����ϸ��~!~!
			+ "AND pm.userId=#{userId} ") 
	public MemberVO memberLogin(String userId);
	
	//3.session ��� principal�� ����
	@Select("SELECT userId,userName,sex,email,phone,addr1,addr2 "
			+ "FROM projectMember "
			+ "WHERE userId=#{userId} ")
	public MemberVO memberSessionData(String userId);
	
	@Update("UPDATE projectMember SET lastlogin=SYSDATE "
			+ "WHERE userId=#{userId}")
	public void lastLoginUpdate(String userId);
	
	//ä��
	 @Select("SELECT pm.userId,userName,userPwd,enabled,authority "
	           +"FROM projectMember pm,projectAuthority pa "
	           +"WHERE pm.userId=pa.userId "
	           +"AND pm.userId=#{userId}")
	   public MemberVO memberInfo(String userId);
}
