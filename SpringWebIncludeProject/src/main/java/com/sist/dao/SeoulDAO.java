package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class SeoulDAO {
	
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map)
	{
		mapper.seoulListData(map);
		return (List<SeoulVO>)map.get("pResult");
	}
	//프로시저로 만들었는데 안돼서
	public Integer seoulTotalPage(Map map)
	{
		mapper.seoulTotalPage(map);
		return (Integer)map.get("pTotal");
	}
	//아래로 실행함
	public int seoulLocationTotalPage()
	{
		return mapper.seoulLocationTotalPage();
	}
	
	//서울 검색 부분
	public List<SeoulVO> seoulFindData(Map map)
	{
		return mapper.seoulFindData(map);
	}
	public SeoulVO seoulFindPage(Map map)
	{
		return mapper.seoulFindPage(map);
	}
}
