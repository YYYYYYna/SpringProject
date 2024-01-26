package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
  @Autowired
  private FoodMapper mapper;
  
  public List<FoodVO> foodListData(int start,int end)
  {
	  return mapper.foodListData(start, end);
  }
  public int foodTotalPage()
  {
	  return mapper.foodTotalPage();
  }
  
  public FoodVO foodDetailData(int fno)
  {
	  //1.조회수증가
	  mapper.hitIncrement(fno);
	  //2.데이터가져오기
	  return mapper.foodDetailData(fno);
  }
  //쿠키들어가는데 조회수가 증가하면 안되니까 mapper는 공통으로 사용하되 dao는 따로만듬
  public FoodVO foodCookieData(int fno)
  {
	  //2.데이터가져오기
	  return mapper.foodDetailData(fno);
  }
  public List<FoodVO> foodFindData(Map map)
  {
	  return mapper.foodFindData(map);
  }
  public int foodFindTotalPage(Map map)
  {
	  return mapper.foodFindTotalPage(map);
  }
}