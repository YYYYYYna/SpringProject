package com.sist.mapper;

import org.apache.ibatis.annotations.*;

import com.sist.vo.*;

public interface MemberMapper {

	//1.회원가입
	//1-1.아이디 중복체크
	@Select("SELECT COUNT(*) FROM projectMember "
			+ "WHERE userid=#{userid} ")
	public int memberIdCount(String userid);
	//1-2.회원가입 추가
	//#{}부분은 vo랑 대소문자 맞춰야하고 그냥 쓰는부분은 col이라서 대소문자 안맞춰도됨
	@Insert("INSERT INTO projectMember(userid, username, userpwd, sex, birthday, email, post, "
			+ "addr1,addr2,phone,content) VALUES(#{userId},#{userName},#{userPwd},#{sex}, "
			+ "#{birthday},#{email},#{post},#{addr1},#{addr2},#{phone},#{content}) ")
	public void memberInsert(MemberVO vo);
	//1-3.일반회원추가?
	@Insert("INSERT INTO projectAuthority VALUES(#{userId}, 'ROLE_USER')")
	public void memberAutorityInsert(String userId);
	
	//2.로그인
	//2-1.ID존재여부 확인
	//이미잇음?>.? 아 1-1 가져다 쓰면 됨~
	//2-2.비밀번호 확인
	@Select("SELECT pm.userId,userName,userPwd,enabled,authority "
			+ "FROM projectMember pm, projectAuthority pa "
			+ "WHERE pm.userId=pa.userId "//vo에서불러오고 이렇게 join 조건설정하면됨~!~!
			+ "AND pm.userId=#{userId} ") 
	public MemberVO memberLogin(String userId);
	
	//3.session 대신 principal에 저장
	@Select("SELECT userId,userName,sex,email,phone,addr1,addr2 "
			+ "FROM projectMember "
			+ "WHERE userId=#{userId} ")
	public MemberVO memberSessionData(String userId);
	
	@Update("UPDATE projectMember SET lastlogin=SYSDATE "
			+ "WHERE userId=#{userId}")
	public void lastLoginUpdate(String userId);
	
	//채팅
	 @Select("SELECT pm.userId,userName,userPwd,enabled,authority "
	           +"FROM projectMember pm,projectAuthority pa "
	           +"WHERE pm.userId=pa.userId "
	           +"AND pm.userId=#{userId}")
	   public MemberVO memberInfo(String userId);
}
