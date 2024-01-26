package com.sist.mapper;

import org.apache.ibatis.annotations.*;

import com.sist.vo.MemberVO;

public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM springmanager "
			+ "WHERE id=#{id}")
	public int idCount(String id);
	
	@Select("SELECT id,pwd,name,sex FROM springmanager "
			+ "WHERE id=#{id}")
	public MemberVO memberLogin(MemberVO vo);
}
