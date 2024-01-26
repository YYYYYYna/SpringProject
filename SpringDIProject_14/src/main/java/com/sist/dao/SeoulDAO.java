package com.sist.dao;
import java.util.*;
import com.sist.mapper.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//�޸��Ҵ�
@Repository("sDAO")
public class SeoulDAO {
	//������ ���� (������ ���� ����)
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map)
	{
		return mapper.seoulListData(map);
	}
	public SeoulVO seoulDetailData(Map map)
	{
		return mapper.seoulDetailData(map);
	}
}
