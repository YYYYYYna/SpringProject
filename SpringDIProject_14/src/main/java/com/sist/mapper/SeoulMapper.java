package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.sist.dao.*;

public interface SeoulMapper {
	
	//¸ñ·Ï
	@Select("SELECT no,title FROM ${table_name}")
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT no,title,msg,address FROM ${table_name} WHERE no=#{no}")
	public SeoulVO seoulDetailData(Map map);
}
