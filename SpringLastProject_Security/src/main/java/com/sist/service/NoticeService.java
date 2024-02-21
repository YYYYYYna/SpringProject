package com.sist.service;

import java.util.List;

import com.sist.vo.FoodVO;
import com.sist.vo.NoticeVO;

public interface NoticeService {
	public List<NoticeVO> noticeTop7();
	public List<FoodVO> foodTop7();
}
