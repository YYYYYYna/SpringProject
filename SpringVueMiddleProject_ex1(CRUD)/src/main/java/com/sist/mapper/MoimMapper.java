package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.MoimVO;

public interface MoimMapper {
	@Select("SELECT rno,img,loc,type,rn "
			+ "FROM (SELECT rno,img,loc,type,rownum as rn "
			+ "FROM (SELECT rno,img,loc,type "
			+ "FROM moimlist ORDER BY rn DESC)) "
			+ "WHERE rn BETWEEN #{start} AND #{end} ")
	public List<MoimVO> moimListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM moimlist ")
	public int moimTotalPage();
}
