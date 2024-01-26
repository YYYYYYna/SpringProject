package com.sist.service;

import java.util.*;

import com.sist.dao.*;

public interface FoodService {
	
	public List<FoodVO> foodListData(String type);
	public FoodVO foodDetailData(int fno);
}
