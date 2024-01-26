package com.sist.dao;
import java.util.List;

import org.apache.ibatis.annotations.*;
public interface SeoulMapper {
	@Select("SELECT no,title FROM seoul_shop ORDER BY no ASC")
	public List<SeoulVO> seoulShopListData();
	
	@Select("SELECT no,title,msg,address FROM seoul_shop WHERE no=#{no}")
	public SeoulVO seoulShopDetailData(int no);
	
	@Select("SELECT no,title,msg,address FROM seoul_shop WHERE title LIKE '%'||#{title}||'%'")
	public List<SeoulVO> seoulshopTitleDetailData(String title);
}
